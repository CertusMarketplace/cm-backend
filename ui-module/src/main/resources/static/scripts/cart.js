const cartManager = (() => {
    // ... (resto de las funciones de cartManager se mantienen igual) ...
    const getUserId = () => { /* ... */ };
    const getCartKey = () => `shoppingCart_${getUserId()}`;
    const getCart = () => JSON.parse(localStorage.getItem(getCartKey())) || [];
    const saveCart = (cart) => localStorage.setItem(getCartKey(), JSON.stringify(cart));
    const updateBadge = () => { /* ... */ };
    const clearCart = () => { saveCart([]); updateBadge(); loadCartContent(); };
    const clearCartOnLogout = () => { updateBadge(); };

    const addToCart = async (workId) => {
        const token = localStorage.getItem('jwt_token');
        if (!token) {
            window.location.href = `/marketplace/auth/login?redirect=cart`;
            return;
        }
        try {
            let cart = getCart();
            if (cart.some(item => item.id === workId)) {
                alert("Este producto ya está en el carrito.");
                return;
            }
            const response = await fetch(`/api/v1/works/${workId}`, {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            if (!response.ok) throw new Error('No se pudo obtener la información del producto.');
            const productData = await response.json();
            const cartItem = {
                id: productData.workId,
                title: productData.workTitle,
                price: productData.workPrice,
                image: productData.workImageUrl,
            };
            cart.push(cartItem);
            saveCart(cart);
            updateBadge();
            alert("Producto añadido al carrito.");
            loadCartContent();
        } catch (error) {
            console.error("Error al añadir al carrito:", error);
            alert("Hubo un problema al añadir el producto. Inténtalo de nuevo.");
        }
    };

    const removeFromCart = (workId) => {
        let cart = getCart();
        cart = cart.filter(item => item.id !== workId);
        saveCart(cart);
        updateBadge();
        loadCartContent(); // Importante: recargar el carrito
    };

    return { getCart, addToCart, removeFromCart, clearCart, updateBadge, getUserId, clearCartOnLogout };
})();

async function loadCartContent() {
    const cartContentWrapper = document.getElementById('cart-content-wrapper');
    if (!cartContentWrapper) return;

    const token = localStorage.getItem('jwt_token');
    const cart = cartManager.getCart();

    if (!token) {
        cartContentWrapper.innerHTML = '<div class="text-center py-10 text-gray-500">Inicia sesión para ver tu carrito.</div>';
        return;
    }

    const workIds = cart.map(item => item.id);

    try {
        const response = await fetch('/marketplace/cart/content', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ workIds: workIds })
        });
        if (!response.ok) {
            throw new Error('Error al cargar el contenido del carrito');
        }
        cartContentWrapper.innerHTML = await response.text();
    } catch (error) {
        console.error("Error en loadCartContent:", error);
        cartContentWrapper.innerHTML = '<div class="text-center py-10 text-red-500">No se pudo cargar el carrito.</div>';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    cartManager.updateBadge();

    const cartDrawerButton = document.querySelector('[data-drawer-show="cart-drawer"]');
    if (cartDrawerButton) {
        cartDrawerButton.addEventListener('click', loadCartContent);
    }

    document.body.addEventListener('click', (event) => {
        const addBtn = event.target.closest('.add-to-cart-btn');
        if (addBtn) {
            event.preventDefault();
            const productId = parseInt(addBtn.dataset.id, 10);
            cartManager.addToCart(productId);
        }

        const removeBtn = event.target.closest('.remove-from-cart-btn');
        if (removeBtn) {
            const workId = parseInt(removeBtn.dataset.id, 10);
            cartManager.removeFromCart(workId);
        }
    });
});