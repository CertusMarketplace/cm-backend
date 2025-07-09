const cartManager = (() => {
    const getUserIdFromToken = () => {
        const token = localStorage.getItem('jwt_token');
        if (!token) return null;
        try {
            return JSON.parse(atob(token.split('.')[1])).sub;
        } catch (e) {
            return null;
        }
    };

    const getCartKey = () => {
        const userId = getUserIdFromToken();
        return userId ? `shoppingCart_${userId}` : 'shoppingCart_guest';
    };

    const getCart = () => {
        const cartData = JSON.parse(localStorage.getItem(getCartKey())) || [];
        // Filtra y mapea para asegurar que solo tengamos IDs numéricos.
        // Esto soluciona el problema de datos corruptos o antiguos.
        return cartData
            .map(item => (typeof item === 'object' && item !== null ? item.id : item))
            .filter(id => typeof id === 'number' && !isNaN(id));
    };

    const saveCart = (cart) => {
        localStorage.setItem(getCartKey(), JSON.stringify(cart));
    };

    const updateBadge = () => {
        const cart = getCart();
        const badge = document.getElementById('cart-icon-badge');
        if (badge) {
            badge.textContent = cart.length;
            badge.classList.toggle('hidden', cart.length === 0);
        }
    };

    const clearCart = () => {
        saveCart([]);
        updateBadge();
        loadCartContent();
    };

    const clearCartOnLogout = () => {
        // Al cerrar sesión, no hacemos nada con el localStorage,
        // simplemente la próxima vez se usará la llave 'shoppingCart_guest'.
        updateBadge();
    };

    const addToCart = async (workId) => {
        let cart = getCart();
        if (cart.includes(workId)) {
            alert("Este producto ya está en el carrito.");
            return;
        }
        cart.push(workId);
        saveCart(cart);
        updateBadge();
        alert("Producto añadido al carrito.");
        loadCartContent(); // Recargamos para mostrar el nuevo item
    };

    const removeFromCart = (workId) => {
        let cart = getCart();
        cart = cart.filter(id => id !== workId);
        saveCart(cart);
        updateBadge();
        loadCartContent();
    };

    return {
        getCart,
        addToCart,
        removeFromCart,
        clearCart,
        updateBadge,
        clearCartOnLogout,
    };
})();

window.cartManager = cartManager; // Exponer para otros scripts

async function loadCartContent() {
    const cartContentWrapper = document.getElementById('cart-content-wrapper') || document.querySelector('#cart-drawer > div');
    if (!cartContentWrapper) return;

    const token = localStorage.getItem('jwt_token');
    const workIds = cartManager.getCart();

    try {
        const response = await fetch('/marketplace/cart/content', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Es seguro enviarlo aunque sea nulo
            },
            body: JSON.stringify({ workIds: workIds })
        });

        if (!response.ok) {
            throw new Error(`Error del servidor: ${response.status}`);
        }
        cartContentWrapper.innerHTML = await response.text();
    } catch (error) {
        console.error("Error al cargar el contenido del carrito:", error);
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
            if (!isNaN(productId)) {
                cartManager.addToCart(productId);
            }
        }

        const removeBtn = event.target.closest('.remove-from-cart-btn');
        if (removeBtn) {
            event.preventDefault();
            const workId = parseInt(removeBtn.dataset.id, 10);
            if (!isNaN(workId)) {
                cartManager.removeFromCart(workId);
            }
        }
    });
});