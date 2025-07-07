// Función Header
function headerHome() {
    const burguer = document.getElementById('burguer');
    const sidebar = document.getElementById('sidebar');
    const closeSidebar = document.getElementById('closeSidebar');

    if (burguer && sidebar && closeSidebar) {
        burguer.addEventListener('click', () => {
            sidebar.classList.remove('translate-x-full');
        });

        closeSidebar.addEventListener('click', () => {
            sidebar.classList.add('translate-x-full');
        });

        window.addEventListener('resize', () => {
            if (window.innerWidth >= 1100) {
                sidebar.classList.add('translate-x-full');
            }
        });
    }
}

// Función Contenido de Dashboard
function loadView(view) {
    const contentArea = document.getElementById("all-content");
    if (contentArea) {
        fetch(`/marketplace/dashboard/seller/fragments/${view}`)
            .then(res => res.text())
            .then(html => {
                contentArea.innerHTML = html;
            })
            .catch(err => console.error(`Error loading view ${view}:`, err));
    }
}

// Función Estado de Filtro Sidebar
function setActiveSidebarLink(activeId) {
    const links = ['stateLinkWork', 'stateLinkReview', 'stateLinkSales', 'stateLinkSettings', 'stateLinkAccount', 'stateLinkLogout'];
    links.forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            if (id === activeId) {
                element.classList.add('bg-skyBlueCertus');
                element.classList.remove('hover:bg-blue-900');
            } else {
                element.classList.remove('bg-skyBlueCertus');
                element.classList.add('hover:bg-blue-900');
            }
        }
    });
}

// CORRECCIÓN: Agregar listeners solo si los elementos existen
document.addEventListener('DOMContentLoaded', () => {
    headerHome();
    greetingExpression();

    ['stateLinkWork', 'stateLinkReview', 'stateLinkSales', 'stateLinkSettings', 'stateLinkAccount', 'stateLinkLogout'].forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.addEventListener('click', () => setActiveSidebarLink(id));
        }
    });

    // CORRECCIÓN: Inicializar el estado activo del sidebar si estamos en el dashboard
    if(document.getElementById('stateLinkWork')) {
        setActiveSidebarLink('stateLinkWork');
    }
});

// Función Saludo
function greetingExpression() {
    const greeting = document.getElementById('greeting');
    // CORRECCIÓN: Solo ejecutar si el elemento existe.
    if (greeting) {
        const date = new Date();
        const hour = date.getHours();

        if (hour >= 6 && hour < 12) {
            greeting.textContent = 'Buenos días ';
        } else if (hour >= 12 && hour < 18) {
            greeting.textContent = 'Buenas tardes ';
        } else {
            greeting.textContent = 'Buenas noches ';
        }
    }
}