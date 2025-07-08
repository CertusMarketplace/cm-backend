document.addEventListener('DOMContentLoaded', () => {
    const API_BASE_URL = '/api/v1/auth';

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    // --- Selectores de elementos del DOM ---
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const errorMessageDiv = document.getElementById('error-message');
    const successMessageDiv = document.getElementById('success-message');
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    const customGoogleBtn = document.getElementById('custom-google-btn');
    const googleClientIdSpan = document.getElementById('google-client-id-span');

    // --- Funciones de utilidad ---
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
    const showMessage = (element, message, isError = false) => {
        if (element) {
            element.textContent = message;
            element.classList.remove('hidden');
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            element.className = isError
                ? 'text-center text-red-700 text-sm p-3 bg-red-100 border border-red-400 rounded-lg my-4'
                : 'text-center text-green-700 text-sm p-3 bg-green-100 border border-green-400 rounded-lg my-4';
=======
=======
>>>>>>> Stashed changes
            if (isError) {
                element.classList.add('text-red-700', 'bg-red-100', 'border-red-400');
                element.classList.remove('text-green-700', 'bg-green-100', 'border-green-400');
            } else {
                element.classList.add('text-green-700', 'bg-green-100', 'border-green-400');
                element.classList.remove('text-red-700', 'bg-red-100', 'border-red-400');
            }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(body),
            });
            const data = await response.json();
=======
=======
>>>>>>> Stashed changes
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body),
            });

            const data = await response.json();

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            if (response.ok) {
                if (isRegister) {
                    window.location.href = '/marketplace/auth/login?registrationSuccess=true';
                } else {
                    localStorage.setItem('jwt_token', data.jwt);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                    window.location.href = '/marketplace/works';
                }
            } else {
                showMessage(errorMessageDiv, data.error || data.message || 'Ocurrió un error.', true);
=======
=======
>>>>>>> Stashed changes
                    const urlParams = new URLSearchParams(window.location.search);
                    const redirectTarget = urlParams.get('redirect');

                    if (redirectTarget === 'cart') {
                        window.location.href = '/marketplace/works?openCart=true';
                    } else {
                        window.location.href = '/marketplace/works';
                    }
                }
            } else {
                showMessage(errorMessageDiv, data.message || data.error || 'Ocurrió un error.', true);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            }
        } catch (error) {
            showMessage(errorMessageDiv, 'No se pudo conectar con el servidor.', true);
        }
    };

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
=======
>>>>>>> Stashed changes
    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            handleFormSubmit(`${API_BASE_URL}/login`, { userEmail: email, userPassword: password });
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const name = document.getElementById('nombre').value;
            const lastname = document.getElementById('apellido').value;
            const email = document.getElementById('correo').value;
            const password = document.getElementById('contrasena').value;

            handleFormSubmit(`${API_BASE_URL}/register`, {
                userEmail: email,
                userPassword: password,
                personName: name,
                personLastname: lastname
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            }, true);
        });
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
=======
>>>>>>> Stashed changes
    const handleGoogleCredentialResponse = async (googleResponse) => {
        try {
            const response = await fetch(`${API_BASE_URL}/google-login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id_token: googleResponse.credential }),
            });

            if (response.ok) {
                const data = await response.json();
                if (data.jwt) {
                    localStorage.setItem('jwt_token', data.jwt);
                    const urlParams = new URLSearchParams(window.location.search);
                    const redirectTarget = urlParams.get('redirect');

                    if (redirectTarget === 'cart') {
                        window.location.href = '/marketplace/works?openCart=true';
                    } else {
                        window.location.href = '/marketplace/works';
                    }
                }
            } else {
                const errorData = await response.json();
                showMessage(errorMessageDiv, errorData.error || 'Error en el inicio de sesión con Google.', true);
            }
        } catch (error) {
            showMessage(errorMessageDiv, 'No se pudo conectar con el servidor para el login de Google.', true);
        }
    };

    const googleButtonContainer = document.getElementById('google-btn');
    const googleClientId = googleButtonContainer?.dataset.clientId;

    if (googleButtonContainer && googleClientId) {
        window.onGoogleLibraryLoad = () => {
            if (typeof google !== 'undefined') {
                google.accounts.id.initialize({
                    client_id: googleClientId,
                    callback: handleGoogleCredentialResponse,
                });
                google.accounts.id.renderButton(
                    googleButtonContainer,
                    { theme: "outline", size: "large", type: 'standard', text: 'continue_with' }
                );
            }
        };
    }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
});