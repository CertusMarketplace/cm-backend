document.addEventListener('DOMContentLoaded', () => {
    const API_BASE_URL = '/api/v1/auth';

    // --- Selectores de elementos del DOM ---
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const errorMessageDiv = document.getElementById('error-message');
    const successMessageDiv = document.getElementById('success-message');
    const customGoogleBtn = document.getElementById('custom-google-btn');
    const googleClientIdSpan = document.getElementById('google-client-id-span');

    // --- Funciones de utilidad ---
    const showMessage = (element, message, isError = false) => {
        if (element) {
            element.textContent = message;
            element.classList.remove('hidden');
            element.className = isError
                ? 'text-center text-red-700 text-sm p-3 bg-red-100 border border-red-400 rounded-lg my-4'
                : 'text-center text-green-700 text-sm p-3 bg-green-100 border border-green-400 rounded-lg my-4';
        }
    };

    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('registrationSuccess')) {
        showMessage(successMessageDiv, '¡Registro exitoso! Por favor, inicia sesión.');
    }

    const handleFormSubmit = async (url, body, isRegister = false) => {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(body),
            });
            const data = await response.json();
            if (response.ok) {
                if (isRegister) {
                    window.location.href = '/marketplace/auth/login?registrationSuccess=true';
                } else {
                    localStorage.setItem('jwt_token', data.jwt);
                    window.location.href = '/marketplace/works';
                }
            } else {
                showMessage(errorMessageDiv, data.error || data.message || 'Ocurrió un error.', true);
            }
        } catch (error) {
            showMessage(errorMessageDiv, 'No se pudo conectar con el servidor.', true);
        }
    };

    // --- Listeners para formularios manuales ---
    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            e.preventDefault();
            handleFormSubmit(`${API_BASE_URL}/login`, {
                email: document.getElementById('email').value,
                password: document.getElementById('password').value
            });
        });
    }
    if (registerForm) {
        registerForm.addEventListener('submit', (e) => {
            e.preventDefault();
            handleFormSubmit(`${API_BASE_URL}/register`, {
                email: document.getElementById('correo').value,
                password: document.getElementById('contrasena').value,
                name: document.getElementById('nombre').value,
                lastname: document.getElementById('apellido').value
            }, true);
        });
    }

    // --- Lógica de Autenticación con Google ---
    function setupGoogleAuth() {
        if (!customGoogleBtn || !googleClientIdSpan) {
            return; // No estamos en una página con el botón de Google
        }
        const googleClientId = googleClientIdSpan.dataset.clientId;
        if (!googleClientId) {
            console.error("Google Client ID no está definido en el span.");
            return;
        }

        // Esta es la función que se llamará después de que el usuario inicie sesión en Google
        const handleGoogleCredentialResponse = async (googleResponse) => {
            try {
                const response = await fetch(`${API_BASE_URL}/google-login`, {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    // El backend espera un objeto con la clave "idToken"
                    body: JSON.stringify({idToken: googleResponse.credential}),
                });
                const data = await response.json();
                if (response.ok && data.jwt) {
                    localStorage.setItem('jwt_token', data.jwt);
                    window.location.href = '/marketplace/works';
                } else {
                    showMessage(errorMessageDiv, data.error || 'Error en el inicio de sesión con Google.', true);
                }
            } catch (error) {
                showMessage(errorMessageDiv, 'No se pudo conectar con el servidor para el login de Google.', true);
            }
        };

        // CORRECCIÓN: Esperamos a que la librería de Google esté lista
        const checkGoogleReady = setInterval(() => {
            if (typeof google !== 'undefined' && google.accounts) {
                clearInterval(checkGoogleReady); // Dejamos de verificar

                // Inicializamos el cliente de Google
                google.accounts.id.initialize({
                    client_id: googleClientId,
                    callback: handleGoogleCredentialResponse,
                    ux_mode: 'popup', // Forzamos que siempre sea un popup
                });

                // Añadimos el listener a nuestro botón personalizado
                customGoogleBtn.addEventListener('click', () => {
                    // Lanzamos el popup de Google
                    google.accounts.id.prompt();
                });
                console.log("Botón de Google inicializado correctamente.");
            }
        }, 100); // Verificamos cada 100ms
    }

    setupGoogleAuth();
});