document.addEventListener('DOMContentLoaded', () => {
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
                    body: JSON.stringify({ workIds: workIds })
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
});