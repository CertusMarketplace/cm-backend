document.addEventListener('DOMContentLoaded', () => {
    const requestForm = document.getElementById('request-seller-form');
    if (!requestForm) return;

    const messageDiv = document.getElementById('request-message');
    const passwordFieldsContainer = document.getElementById('password-fields-container');
    const token = localStorage.getItem('jwt_token');

    if (!token) {
        passwordFieldsContainer.classList.remove('hidden');
        document.getElementById('password').required = true;
        document.getElementById('confirmPassword').required = true;
    }

    const showMessage = (message, isError = false) => {
        if (!messageDiv) return;
        messageDiv.textContent = message;
        messageDiv.classList.remove('hidden');
        messageDiv.className = isError
            ? 'text-center text-sm p-3 border rounded-lg text-red-700 bg-red-100 border-red-400'
            : 'text-center text-sm p-3 border rounded-lg text-green-700 bg-green-100 border-green-400';
    };

    requestForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const formData = new FormData(requestForm);
        const institutionalEmail = formData.get('email');

        if (!institutionalEmail || !institutionalEmail.toLowerCase().trim().endsWith('@certus.edu.pe')) {
            showMessage('El correo proporcionado no es un correo institucional de Certus válido.', true);
            return;
        }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        if (!token) {
            const password = formData.get('password');
            const confirmPassword = formData.get('confirmPassword');
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        const personData = {
            personName: formData.get('name'),
            personLastname: formData.get('lastname'),
            personDni: formData.get('dni'),
            personMobilePhone: formData.get('number'),
            personGender: formData.get('gender'),
            personInstituteCampus: formData.get('campus'),
            personInstitutionalEmail: institutionalEmail,
            personInstitutionalCareer: formData.get('career'),
            personInstitutionalCycle: parseInt(formData.get('cycle'))
        };

        if (!token) {
            const password = formData.get('password');
            const confirmPassword = formData.get('confirmPassword');

<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            if (password !== confirmPassword) {
                showMessage('Las contraseñas no coinciden.', true);
                return;
            }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        }

        const requestData = {
            password: formData.get('password'),
            name: formData.get('name'),
            lastname: formData.get('lastname'),
            email: institutionalEmail,
            dni: formData.get('dni'),
            mobilePhone: formData.get('number'),
            gender: formData.get('gender'),
            instituteCampus: formData.get('campus'),
            institutionalCareer: formData.get('career'),
            institutionalCycle: parseInt(formData.get('cycle'))
        };

        const endpoint = '/api/v1/users/request-seller-role';
        const method = token ? 'PUT' : 'POST';
        const headers = {'Content-Type': 'application/json'};
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }

        try {
            const response = await fetch(endpoint, {
                method: method,
                headers: headers,
                body: JSON.stringify(requestData)
            });

            const result = await response.json();

            if (!response.ok) {
                throw new Error(result.error || 'Ocurrió un error al procesar la solicitud.');
            }

            showMessage(result.message, false);

            if (token) {
                localStorage.removeItem('jwt_token');
            }

            setTimeout(() => window.location.href = '/marketplace/auth/login', 3000);

        } catch (error) {
            showMessage(error.message, true);
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

            const registrationData = {
                userEmail: institutionalEmail,
                userPassword: password,
                personName: personData.personName,
                personLastname: personData.personLastname,
            };

            try {
                const response = await fetch('/api/v1/auth/register', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(registrationData)
                });
                const result = await response.json();

                if (!response.ok) throw new Error(result.message || 'Error al registrar la cuenta de vendedor.');

                showMessage('¡Cuenta de Vendedor creada! Serás redirigido para iniciar sesión.', false);
                setTimeout(() => window.location.href = '/marketplace/auth/login?registrationSuccess=true', 3000);

            } catch (error) {
                showMessage(error.message, true);
            }

        } else {
            try {
                const decodedToken = JSON.parse(atob(token.split('.')[1]));
                const userId = decodedToken.sub;

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                };

                await fetch(`/api/v1/users/update-role/${userId}/2`, {
                    method: 'PUT',
                    headers: headers
                });

                const personResponse = await fetch(`/api/v1/people/update/${userId}`, {
                    method: 'PUT',
                    headers: headers,
                    body: JSON.stringify(personData)
                });

                if (!personResponse.ok) throw new Error('Error al actualizar tu perfil.');

                showMessage('¡Felicidades! Tu rol ha sido actualizado a Vendedor. Serás redirigido a tu panel.', false);
                setTimeout(() => window.location.href = '/marketplace/dashboard/seller', 3000);

            } catch (error) {
                showMessage(error.message || 'Ocurrió un error. Inténtalo de nuevo.', true);
            }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        }
    });
});