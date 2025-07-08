<<<<<<< Updated upstream
document.addEventListener('DOMContentLoaded', function () {
=======
document.addEventListener('DOMContentLoaded', function() {
>>>>>>> Stashed changes
    const token = localStorage.getItem('jwt_token');

    const userMenuDesktop = document.getElementById('user-menu-desktop');
    const guestMenuDesktop = document.getElementById('guest-menu-desktop');
    const userMenuMobile = document.getElementById('user-menu-mobile');
    const guestMenuMobile = document.getElementById('guest-menu-mobile');
    const logoutButton = document.getElementById('logout-button');

    if (token) {
        if (userMenuDesktop) userMenuDesktop.classList.remove('hidden');
        if (guestMenuDesktop) guestMenuDesktop.classList.add('hidden');
        if (userMenuMobile) userMenuMobile.classList.remove('hidden');
        if (guestMenuMobile) guestMenuMobile.classList.add('hidden');
    } else {
        if (userMenuDesktop) userMenuDesktop.classList.add('hidden');
        if (guestMenuDesktop) guestMenuDesktop.classList.remove('hidden');
        if (userMenuMobile) userMenuMobile.classList.add('hidden');
        if (guestMenuMobile) guestMenuMobile.classList.remove('hidden');
    }

    if (logoutButton) {
        logoutButton.addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('jwt_token');
            window.location.href = '/auth/login';
        });
    }
});