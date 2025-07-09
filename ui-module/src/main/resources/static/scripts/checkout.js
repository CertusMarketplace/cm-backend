document.addEventListener('DOMContentLoaded', () => {
    // Listener para el botón de checkout
    document.body.addEventListener('click', async (e) => {
        if (e.target.closest('#checkout-button')) {
            const token = localStorage.getItem('jwt_token');
            if (!token) {
                alert("Debes iniciar sesión para poder comprar.");
                window.location.href = '/marketplace/auth/login?redirect=cart';
                return;
            }

            const workIdsJson = document.getElementById('work-ids-json')?.textContent;
            if (!workIdsJson) {
                alert("Error interno: No se encontraron los IDs de los productos para procesar el pago.");
                return;
            }
            try {
                const workIds = JSON.parse(workIdsJson);
                if (!workIds || workIds.length === 0) {
                    alert("Tu carrito está vacío.");
                    return;
                }
                const checkoutButton = e.target.closest('#checkout-button');
                checkoutButton.disabled = true;
                checkoutButton.innerHTML = 'Procesando... <i class="fas fa-spinner fa-spin"></i>';

                const response = await fetch('/api/v1/paypal/create-order-from-cart', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({ workIds: workIds })
                });

                const data = await response.json();

                if (response.ok && data.approvalUrl) {
                    sessionStorage.setItem('paypal_work_ids', JSON.stringify(workIds));
                    window.location.href = data.approvalUrl;
                } else {
                    alert(data.message || "No se pudo iniciar el pago. Por favor, intenta de nuevo.");
                    checkoutButton.disabled = false;
                    checkoutButton.innerHTML = 'Comprar Ahora';
                }
            } catch (err) {
                console.error("Error al crear la orden de PayPal:", err);
                alert("Hubo un problema al contactar a PayPal. Por favor, intenta más tarde.");
                e.target.closest('#checkout-button').disabled = false;
                e.target.closest('#checkout-button').innerHTML = 'Comprar Ahora';
            }
        }
    });

    const handlePaymentStatus = async () => {
        const urlParams = new URLSearchParams(window.location.search);
        const paymentStatus = urlParams.get('payment_status');
        const orderId = urlParams.get('token');

        if (paymentStatus === 'success' && orderId) {
            const authToken = localStorage.getItem('jwt_token');
            const workIds = JSON.parse(sessionStorage.getItem('paypal_work_ids') || '[]');

            if (!authToken || workIds.length === 0) {
                alert('No se pudo completar la compra por falta de información. Por favor, contacta a soporte.');
                return;
            }

            try {
                const response = await fetch('/api/v1/paypal/capture-order', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${authToken}`
                    },
                    body: JSON.stringify({
                        orderId: orderId,
                        workIds: workIds
                    })
                });

                if (response.ok) {
                    const paymentDetails = await response.json();

                    const successModalElement = document.getElementById('success-modal');
                    if(successModalElement) {
                        const successModal = new Modal(successModalElement);
                        successModal.show();
                        document.querySelector('#success-modal button[data-modal-hide]').addEventListener('click', () => {
                            successModal.hide();
                            prepareAndShowRatingModal(workIds, paymentDetails.orderId);
                        }, { once: true });
                    } else {
                        alert("¡Pago exitoso!");
                        prepareAndShowRatingModal(workIds, paymentDetails.orderId);
                    }

                    window.cartManager.clearCart();
                    sessionStorage.removeItem('paypal_work_ids');

                } else {
                    const errorData = await response.json();
                    throw new Error(errorData.message || 'Error al capturar el pago.');
                }
            } catch (error) {
                console.error('Error en la captura del pago:', error);
                alert('Hubo un error al confirmar tu pago. Por favor, contacta a soporte.');
            } finally {
                const newUrl = window.location.pathname;
                window.history.replaceState({}, document.title, newUrl);
            }

        } else if (paymentStatus === 'cancelled') {
            const cancelModalElement = document.getElementById('cancel-modal');
            if(cancelModalElement) {
                const cancelModal = new Modal(cancelModalElement);
                cancelModal.show();
            } else {
                alert("El pago fue cancelado.");
            }
            sessionStorage.removeItem('paypal_work_ids');
            const newUrl = window.location.pathname;
            window.history.replaceState({}, document.title, newUrl);
        }
    };

    async function prepareAndShowRatingModal(workIds, orderId) {
        if (!workIds || workIds.length === 0) return;

        const modalId = 'rating-modal-dynamic';
        const existingModal = document.getElementById(modalId);
        if (existingModal) existingModal.remove();

        const modalHtml = `
            <div id="${modalId}" tabindex="-1" class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-full max-h-full">
                <div class="relative w-full max-w-2xl max-h-full">
                    <div class="relative bg-white rounded-lg shadow">
                        <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t">
                            <h3 class="text-xl font-semibold text-gray-900">
                                ¡Gracias por tu compra! Valora tus productos
                            </h3>
                            <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center" data-modal-hide="${modalId}">
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14"><path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/></svg>
                                <span class="sr-only">Cerrar modal</span>
                            </button>
                        </div>
                        <div id="rating-works-container" class="p-4 md:p-5 space-y-4 max-h-[60vh] overflow-y-auto">
                            <p>Cargando productos...</p>
                        </div>
                        <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b">
                            <button id="submit-ratings-button" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">Enviar Reseñas</button>
                            <button data-modal-hide="${modalId}" type="button" class="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100">Omitir por ahora</button>
                        </div>
                    </div>
                </div>
            </div>`;
        document.body.insertAdjacentHTML('beforeend', modalHtml);

        const ratingModalElement = document.getElementById(modalId);
        const ratingWorksContainer = ratingModalElement.querySelector('#rating-works-container');
        const submitButton = ratingModalElement.querySelector('#submit-ratings-button');
        const ratingModal = new Modal(ratingModalElement, {
            onHide: () => {
                const modalBackdrop = document.querySelector('[modal-backdrop]');
                if(modalBackdrop) modalBackdrop.remove();
                ratingModalElement.remove();
            }
        });

        try {
            const workIdParams = workIds.map(id => `ids=${id}`).join('&');
            const response = await fetch(`/api/v1/works/by-ids?${workIdParams}`);
            if (!response.ok) throw new Error("No se pudieron cargar los trabajos para valorar.");

            const purchasedWorks = await response.json();

            ratingWorksContainer.innerHTML = purchasedWorks.map(work => `
                <div class="border p-4 rounded-lg" data-work-id="${work.workId}">
                    <div class="flex items-start gap-4">
                        <img src="${work.workImageUrl}" alt="${work.workTitle}" class="w-20 h-20 object-cover rounded-md">
                        <div>
                            <p class="font-semibold">${work.workTitle}</p>
                            <fieldset class="rating-stars mt-2">
                                <legend class="sr-only">Calificación</legend>
                                <div class="flex flex-row-reverse justify-end items-center text-2xl">
                                    ${[5, 4, 3, 2, 1].map(star => `
                                        <input type="radio" id="star-${work.workId}-${star}" name="rating-${work.workId}" value="${star}" class="sr-only peer" required />
                                        <label for="star-${work.workId}-${star}" class="cursor-pointer text-gray-300 peer-hover:text-yellow-400 hover:!text-yellow-400 peer-checked:text-yellow-400">
                                            <i class="fas fa-star"></i>
                                        </label>
                                    `).join('')}
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <textarea name="comment-${work.workId}" class="mt-3 w-full p-2 border rounded-md" placeholder="Escribe un comentario opcional..."></textarea>
                </div>
            `).join('');

            ratingModal.show();

            submitButton.onclick = async () => {
                const ratings = [];
                let allValid = true;
                ratingWorksContainer.querySelectorAll('[data-work-id]').forEach(workEl => {
                    const workId = workEl.dataset.workId;
                    const selectedRating = workEl.querySelector(`input[name="rating-${workId}"]:checked`);
                    const comment = workEl.querySelector(`textarea[name="comment-${workId}"]`).value;

                    if (!selectedRating) {
                        allValid = false;
                        return;
                    }
                    ratings.push({
                        workId: parseInt(workId),
                        orderId: orderId,
                        ratingScore: parseInt(selectedRating.value),
                        ratingComment: comment || null
                    });
                });

                if (!allValid) {
                    alert("Por favor, califica todos los productos con estrellas.");
                    return;
                }

                try {
                    submitButton.disabled = true;
                    submitButton.textContent = 'Enviando...';
                    await fetch('/api/v1/ratings/batch', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`
                        },
                        body: JSON.stringify(ratings)
                    });
                    ratingModal.hide();
                    alert("¡Gracias por tus reseñas!");
                } catch (err) {
                    alert("No se pudieron enviar las reseñas. Intenta más tarde.");
                } finally {
                    submitButton.disabled = false;
                    submitButton.textContent = 'Enviar Reseñas';
                }
            };
        } catch (error) {
            console.error("Error al preparar el modal de valoración:", error);
            ratingWorksContainer.innerHTML = '<p class="text-red-500">No se pudieron cargar los productos para valorar.</p>';
            ratingModal.show();
        }
    }

    handlePaymentStatus();
});