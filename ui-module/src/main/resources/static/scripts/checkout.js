document.addEventListener('DOMContentLoaded', () => {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    document.body.addEventListener('click', async (e) => {
        if (e.target.closest('#checkout-button')) {
            const token = localStorage.getItem('jwt_token');
            if (!token) {
                window.location.href = '/marketplace/auth/login?redirect=cart';
                return;
            }
            const workIdsJson = document.getElementById('work-ids-json')?.textContent;
            if (!workIdsJson) {
                alert("Error interno: No se encontraron los IDs de los productos.");
                return;
            }
            try {
                const workIds = JSON.parse(workIdsJson);
                if (!workIds || workIds.length === 0) {
                    alert("Tu carrito está vacío.");
                    return;
                }
                const response = await fetch('/api/v1/paypal/create-order-from-cart', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({workIds: workIds})
                });
                const data = await response.json();
                if (response.ok && data.approvalUrl) {
                    // Guardar los workIds en sessionStorage para usarlos después de la redirección
                    sessionStorage.setItem('paypal_work_ids', JSON.stringify(workIds));
                    window.location.href = data.approvalUrl;
                } else {
                    alert(data.message || "No se pudo iniciar el pago.");
                }
            } catch (err) {
                console.error("Error al procesar la orden:", err);
                alert("Hubo un problema al iniciar el pago.");
            }
        }
    });

    const handlePaymentStatus = async () => {
        const urlParams = new URLSearchParams(window.location.search);
        const paymentStatus = urlParams.get('payment_status');
        const orderId = urlParams.get('token'); // PayPal envía el orderId como 'token'

        if (paymentStatus === 'success' && orderId) {
            const authToken = localStorage.getItem('jwt_token');
            const workIds = JSON.parse(sessionStorage.getItem('paypal_work_ids') || '[]');
            const buyerUserId = JSON.parse(atob(authToken.split('.')[1])).sub;

            try {
                const response = await fetch('/api/v1/paypal/capture-order', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${authToken}`
                    },
                    body: JSON.stringify({
                        orderId: orderId,
                        workIds: workIds,
                        buyerUserId: buyerUserId
                    })
                });

                if (response.ok) {
                    const successModal = new Modal(document.getElementById('success-modal'));
                    successModal.show();
                    cartManager.clearCart();
                    sessionStorage.removeItem('paypal_work_ids');
                } else {
                    throw new Error('Error al capturar el pago.');
                }
            } catch (error) {
                console.error('Error en la captura del pago:', error);
                alert('Hubo un error al confirmar tu pago. Por favor, contacta a soporte.');
            }

        } else if (paymentStatus === 'cancelled') {
            const cancelModal = new Modal(document.getElementById('cancel-modal'));
            cancelModal.show();
            sessionStorage.removeItem('paypal_work_ids');
        }

        // Limpia la URL después de procesar
        if (paymentStatus) {
            const newUrl = window.location.pathname;
            window.history.replaceState({}, document.title, newUrl);
        }
    };

    handlePaymentStatus();
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    const cartDrawer = document.getElementById('cart-drawer');
    const cartItemsContainer = document.getElementById('cart-items-container');
    const cartTotalElement = document.getElementById('cart-total');
    const checkoutButton = document.getElementById('checkout-button');

    const renderCartItems = () => {
        const cart = JSON.parse(localStorage.getItem('shoppingCart')) || [];
        cartItemsContainer.innerHTML = '';

        if (cart.length === 0) {
            cartItemsContainer.innerHTML = `<div class="text-center py-10 text-gray-500">Tu carrito está vacío.</div>`;
            if(checkoutButton) checkoutButton.classList.add('hidden');
            if(cartTotalElement) cartTotalElement.closest('div').classList.add('hidden');
            return;
        }

        if(checkoutButton) checkoutButton.classList.remove('hidden');
        if(cartTotalElement) cartTotalElement.closest('div').classList.remove('hidden');

        let total = 0;
        cart.forEach(item => {
            const itemElement = document.createElement('div');
            itemElement.className = 'flex justify-between items-center p-2';
            itemElement.innerHTML = `
                <div>
                    <p class="font-medium text-sm">${item.title}</p>
                    <p class="text-gray-500 text-sm">S/ ${item.price.toFixed(2)}</p>
                </div>
                <div id="paypal-button-container-${item.id}"></div>
            `;
            cartItemsContainer.appendChild(itemElement);
            total += item.price;

            initiatePaypalPayment(item.id, item.price);
        });

        cartTotalElement.textContent = `S/ ${total.toFixed(2)}`;
    };

    const initiatePaypalPayment = async (workId, price) => {
        const token = localStorage.getItem('jwt_token');
        if (!token) {
            // No se renderiza el botón si no está logueado
            const container = document.getElementById(`paypal-button-container-${workId}`);
            container.innerHTML = `<a href="/marketplace/auth/login" class="text-xs text-blue-600 hover:underline">Inicia sesión para comprar</a>`;
            return;
        }

        try {
            const response = await fetch('/api/v1/paypal/create-order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ workId: workId })
            });

            if (!response.ok) throw new Error('Failed to create PayPal order');

            const orderData = await response.json();
            renderPaypalButton(orderData.orderId, workId);

        } catch (error) {
            console.error('Error initiating PayPal payment:', error);
            const container = document.getElementById(`paypal-button-container-${workId}`);
            container.innerHTML = `<span class="text-xs text-red-500">Error al cargar pago</span>`;
        }
    };

    const renderPaypalButton = (orderId, workId) => {
        const containerId = `paypal-button-container-${workId}`;
        const container = document.getElementById(containerId);
        container.innerHTML = '';

        paypal.Buttons({
            createOrder: function(data, actions) {
                return orderId;
            },
            onApprove: async function(data, actions) {
                const token = localStorage.getItem('jwt_token');
                const decodedToken = JSON.parse(atob(token.split('.')[1]));
                const buyerUserId = decodedToken.sub;

                try {
                    const response = await fetch('/api/v1/paypal/capture-order', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify({
                            orderId: data.orderID,
                            workId: workId,
                            buyerUserId: buyerUserId
                        })
                    });

                    if (!response.ok) throw new Error('Failed to capture payment');

                    const details = await response.json();
                    alert('¡Transacción completada por ' + details.payerName + '!');

                    let cart = JSON.parse(localStorage.getItem('shoppingCart')) || [];
                    cart = cart.filter(item => item.id !== workId);
                    localStorage.setItem('shoppingCart', JSON.stringify(cart));
                    renderCartItems();

                } catch (error) {
                    console.error('Error capturing payment:', error);
                    alert('Hubo un error al procesar tu pago. Por favor, intenta de nuevo.');
                }
            },
            onError: function (err) {
                console.error('PayPal button error:', err);
                alert('Ocurrió un error con PayPal.');
            }
        }).render('#' + containerId);
    };

    const observer = new MutationObserver((mutations) => {
        mutations.forEach((mutation) => {
            if (mutation.attributeName === 'aria-hidden' && cartDrawer.getAttribute('aria-hidden') === 'false') {
                renderCartItems();
            }
        });
    });

    if (cartDrawer) {
        observer.observe(cartDrawer, { attributes: true });
    }

    if(checkoutButton) {
        checkoutButton.addEventListener('click', () => {
            const token = localStorage.getItem('jwt_token');
            if(!token) {
                window.location.href = '/marketplace/auth/login?redirect=cart';
            }
        });
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
});