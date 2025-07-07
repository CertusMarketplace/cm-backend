document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwt_token');

    // --- Elementos del Main Header ---
    const userMenu = document.getElementById('user-menu');
    const guestMenu = document.getElementById('guest-menu');
    const userDropdownName = document.getElementById('user-dropdown-name');
    const userDropdownEmail = document.getElementById('user-dropdown-email');
    const userDropdownImage = document.querySelector('#user-menu-button img');
    const logoutButton = document.getElementById('logout-button');
    const dashboardLinkContainer = document.getElementById('dashboard-link-container');
    const sellLink = document.getElementById('sell-link');

    // --- Elementos del Dashboard Header ---
    const dashboardUserName = document.getElementById('dashboard-user-name');
    const dashboardUserImage = document.getElementById('dashboard-user-image');
    const userDropdownNameDashboard = document.getElementById('user-dropdown-name-dashboard');
    const userDropdownEmailDashboard = document.getElementById('user-dropdown-email-dashboard');
    const logoutButtonDashboard = document.getElementById('logout-button-dashboard');

    const updateUI = (isAuthenticated, userData, personData) => {
        if (isAuthenticated && userData) {
            if (guestMenu) guestMenu.classList.add('hidden');
            if (userMenu) userMenu.classList.remove('hidden');

            const displayName = personData ? `${personData.personName} ${personData.personLastname}` : 'Mi Cuenta';
            const displayEmail = userData.userEmail;
            const displayImage = personData && personData.personProfileImageUrl ? personData.personProfileImageUrl : '/img/works/perfil-generico.png';

            if (userDropdownName) userDropdownName.textContent = displayName;
            if (userDropdownEmail) userDropdownEmail.textContent = displayEmail;
            if (userDropdownImage) userDropdownImage.src = displayImage;

            const isSeller = userData.idRole === 2;
            if (dashboardLinkContainer) dashboardLinkContainer.classList.toggle('hidden', !isSeller);
            if (sellLink) sellLink.classList.toggle('hidden', isSeller);

            const dashboardDisplayName = personData ? personData.personName : 'Usuario';
            if (dashboardUserName) dashboardUserName.textContent = dashboardDisplayName;
            if (dashboardUserImage) dashboardUserImage.src = displayImage;
            if (userDropdownNameDashboard) userDropdownNameDashboard.textContent = displayName;
            if (userDropdownEmailDashboard) userDropdownEmailDashboard.textContent = displayEmail;

        } else {
            if (userMenu) userMenu.classList.add('hidden');
            if (guestMenu) guestMenu.classList.remove('hidden');
            if (dashboardLinkContainer) dashboardLinkContainer.classList.add('hidden');
            if (sellLink) sellLink.classList.remove('hidden');
            if (dashboardUserName) dashboardUserName.closest('.lg\\:flex-col')?.classList.add('hidden');
            if (dashboardUserImage) dashboardUserImage.closest('nav')?.classList.add('hidden');
        }
        // Actualizar el contador del carrito después de cambiar de estado de sesión
        if (window.cartManager) {
            window.cartManager.updateBadge();
        }
    };

    const logout = () => {
        localStorage.removeItem('jwt_token');
        if (window.cartManager) {
            window.cartManager.clearCartOnLogout(); // Limpia el estado del carrito
        }
        updateUI(false, null, null);
        const protectedPaths = ['/dashboard', '/profile', '/request-seller-role'];
        if (protectedPaths.some(path => window.location.pathname.includes(path))) {
            window.location.href = '/marketplace/auth/login';
        }
    };

    if (token) {
        try {
            const [userResponse, personResponse] = await Promise.all([
                fetch('/api/v1/users/me', {headers: {'Authorization': `Bearer ${token}`}}),
                fetch('/api/v1/people/me', {headers: {'Authorization': `Bearer ${token}`}})
            ]);

            if (!userResponse.ok) throw new Error('User session invalid or expired');

            const userData = await userResponse.json();
            let personData = null;
            if (personResponse.ok) personData = await personResponse.json();

            updateUI(true, userData, personData);

        } catch (error) {
            console.error("Authentication check failed:", error.message);
            logout();
        }
    } else {
        updateUI(false, null, null);
    }

    const setupLogout = (button) => {
        if (button) {
            button.addEventListener('click', (e) => {
                e.preventDefault();
                logout();
                window.location.href = '/marketplace/auth/login';
            });
        }
    };

    setupLogout(logoutButton);
    setupLogout(logoutButtonDashboard);

    // --- Registro ---
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.onsubmit = async function(e) {
            e.preventDefault();
            const data = Object.fromEntries(new FormData(this));
            const res = await fetch('/api/v1/auth/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(data)
            });
            if (res.ok) {
                alert('¡Registro exitoso!');
                window.location.href = '/marketplace/auth/login';
            } else {
                alert('Error: ' + await res.text());
            }
        };
    }

    // --- Login ---
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.onsubmit = async function(e) {
            e.preventDefault();
            const data = Object.fromEntries(new FormData(this));
            const res = await fetch('/api/v1/auth/login', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(data)
            });
            if (res.ok) {
                const token = await res.text();
                localStorage.setItem('jwt_token', token);
                alert('¡Bienvenido!');
                window.location.href = '/';
            } else {
                alert('Error: ' + await res.text());
            }
        };
    }

    function handleGoogleCredentialResponse(response) {
        fetch('/api/v1/auth/login-google', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ idToken: response.credential })
        })
        .then(res => res.ok ? res.json() : Promise.reject(res))
        .then(data => {
            if (data.jwt) {
                localStorage.setItem('jwt_token', data.jwt);
                alert('¡Bienvenido con Google!');
                window.location.href = '/';
            } else {
                alert('Error: ' + (data.error || 'Error de autenticación con Google.'));
            }
        })
        .catch(async err => {
            let msg = 'Error de red o autenticación con Google.';
            if (err.text) msg = await err.text();
            alert(msg);
        });
    }

    window.onload = function() {
        const googleBtn = document.getElementById('google-signin-btn');
        if (googleBtn && window.google) {
            google.accounts.id.initialize({
                client_id: 'TU_CLIENT_ID_GOOGLE',
                callback: handleGoogleCredentialResponse
            });
            google.accounts.id.renderButton(googleBtn, { theme: 'outline', size: 'large' });
        }
    };
});