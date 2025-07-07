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

        if (!token) {
            const password = formData.get('password');
            const confirmPassword = formData.get('confirmPassword');
            if (password !== confirmPassword) {
                showMessage('Las contraseñas no coinciden.', true);
                return;
            }
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
        }
    });
});