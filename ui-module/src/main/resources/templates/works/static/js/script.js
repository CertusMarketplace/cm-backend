const allProducts = [
    {
        id: 1,
        category: 'software',
        title: 'LANDING PAGE CAFETERÍA',
        author: 'Regina Perez',
        price: 220,
        popularity: 5,
        image: '/img/works/works/landing-cafe.jpg',
        description: 'Diseño y desarrollo de landing page optimizada para cafeterías.',
    },
    {
        id: 2,
        category: 'software',
        title: 'APP MÓVIL PARA TIENDA',
        author: 'Lucia Ramos',
        price: 480,
        popularity: 4,
        image: '/img/works/works/app-tienda.jpg',
        description: 'Aplicación móvil personalizada para la gestión y venta de productos en tiendas.',
    },
    {
        id: 3,
        category: 'software',
        title: 'PORTAL WEB PARA COLEGIO',
        author: 'José Ramirez',
        price: 520,
        popularity: 5,
        image: '/img/works/works/web-colegio.jpg',
        description: 'Desarrollo de portal web completo para la administración y comunicación de colegios.',
    },
    {
        id: 4,
        category: 'software',
        title: 'UI PARA APP DE DELIVERY',
        author: 'Javier Peña',
        price: 400,
        popularity: 4,
        image: '/img/works/works/app-delivery.jpg',
        description: 'Diseño de interfaz de usuario moderna y funcional para aplicaciones de delivery.',
    },
    {
        id: 5,
        category: 'software',
        title: 'SITIO WEB PARA CONSULTORA DE TI',
        author: 'Hugo Medina',
        price: 590,
        popularity: 5,
        image: '/img/works/web-consultoria.jpg',
        description: 'Creación de sitio web profesional para consultoras de tecnología con enfoque en servicios.',
    },
    {
        id: 6,
        category: 'software',
        title: 'PORTAL WEB PARA SERVICIOS LEGALES',
        author: 'César Acuña',
        price: 480,
        popularity: 4,
        image: '/img/works/web-legal.jpg',
        description: 'Desarrollo de portal web especializado para estudios de abogados y servicios legales.',
    },
    {
        id: 7,
        category: 'software',
        title: 'APP WEB SEGUIMIENTO DE PROYECTOS',
        author: 'José Ramirez',
        price: 580,
        popularity: 5,
        image: '/img/works/app-seguimiento.jpg',
        description: 'Aplicación web intuitiva para el seguimiento y gestión de proyectos empresariales.',
    },
    {
        id: 8,
        category: 'software',
        title: 'DISEÑO APP RECETAS SALUDABLES',
        author: 'Javier Peña',
        price: 400,
        popularity: 4,
        image: '/img/works/app-recetas.jpg',
        description: 'Diseño creativo de una aplicación móvil con recetas saludables y funciones interactivas.',
    },

    {
        id: 9,
        category: 'cursos',
        title: 'DISEÑO UX PARA PRINCIPIANTES',
        author: 'Elena Rivas',
        price: 150,
        popularity: 5,
        image: '/img/works/diseño-principiantes.jpg',
        description: 'Curso de diseño para principiantes',
    },
    {
        id: 10,
        category: 'musica',
        title: 'COMPOSICIÓN INSTRUMENTAL JAZZ',
        author: 'Regina Perez',
        price: 75,
        popularity: 4,
        image: '/img/works/jazz.jpg',
        description: 'Composición de jazz',
    },
    {
        id: 11,
        category: 'cursos',
        title: 'INTRODUCCIÓN ANIMACIÓN 2D',
        author: 'Lucía Ramos ',
        price: 280,
        popularity: 5,
        image: '/img/works/animacion-2d.jpg',
        description: 'Aprende los fundamentos de animación 2D.',
    },
    {
        id: 12,
        category: 'ilustraciones',
        title: 'RETRARO DIGITAL INFANTIL',
        author: 'Regina Perez',
        price: 60,
        popularity: 3,
        image: '/img/works/retrato-infantil.jpg',
        description: 'Retrato infantil personalizado',
    },
    {
        id: 13,
        category: 'musica',
        title: 'EFECTO DE SONIDO VIDEOJUEGO',
        author: 'Lucía Ramos ',
        price: 300,
        popularity: 5,
        image: '/img/works/juego.jpg',
        description: 'Colección de melodías personalizadas para videojuegos.',
    },
    {
        id: 14,
        category: 'musica',
        title: 'MÚSICA AMBIENTAL PARA CAFETERÍA',
        author: 'Enrique Peñanieto',
        price: 120,
        popularity: 4,
        image: '/img/works/musica-cafe.jpg',
        description: 'Música ambientada para cafetería relajante.',
    },
    {
        id: 15,
        category: 'musica',
        title: 'CANCIÓN PARA REGALAR',
        author: 'Javier Peña',
        price: 350,
        popularity: 5,
        image: '/img/works/music-regalo.jpg',
        description: 'Música personalizada para regalar.',
    },
    {
        id: 16,
        category: 'ilustraciones',
        title: 'ILUSTRACIÓN PORTADA ESTILO YAKUZA',
        author: 'Lucía Ramos ',
        price: 170,
        popularity: 4,
        image: '/img/works/ilustracion-portada.jpg',
        description: 'Ilustración de portada inspirada en yakuzas.',
    },
    {
        id: 17,
        category: 'ilustraciones',
        title: 'LOGO FRUTAL',
        author: 'Enrique Peñanieto',
        price: 180,
        popularity: 4,
        image: '/img/works/logo-frutal.jpg',
        description: 'Plantilla moderna y responsive para mostrar tus proyectos.',
    },
    {
        id: 18,
        category: 'cursos',
        title: 'MARKETING DIGITAL DESDE CERO',
        author: 'Enrique Peñanieto',
        price: 250,
        popularity: 5,
        image: '/img/works/works/mrkt-digital.jpg',
        description: 'Curso con especialista en marketing digital para potenciar tu negocio.',
    },
    {
        id: 19,
        category: 'cursos',
        title: 'INTRODUCCIÓN A PHOTOSHOP',
        author: 'Javier Peña',
        price: 80,
        popularity: 3,
        image: '/img/works/works/introduccion-photo.jpg',
        description: 'Curso introductorio de Photoshop.',
    },
    {
        id: 20,
        category: 'ilustraciones',
        title: 'MUNDO ANIMAL',
        author: 'José Ramirez',
        price: 110,
        popularity: 4,
        image: '/img/works/works/mundo-animal.jpg',
        description: 'Ilustración inspirada en el encarcelamiento de conejos y liebres.',
    },
];

const categoryInfo = {
    'todos': {
        title: 'Todos los Productos',
        description: 'Explora todas nuestras soluciones digitales disponibles.',
        bannerImage: '/img/works/banner-todos.jpg',
    },
    'cursos': {
        title: 'Cursos',
        description: 'Aprende y domina nuevas habilidades con nuestros cursos especializados.',
        bannerImage: '/img/works/works/banner-cursos.jpg',
    },
    'ilustraciones': {
        title: 'Ilustraciones Digitales',
        description: 'Descubre arte digital único y personalizable para tus proyectos.',
        bannerImage: '/img/works/works/banner-ilustraciones.jpg',
    },
    'musica': {
        title: 'Música y Audio',
        description: 'Encuentra pistas, efectos y recursos de audio para tus creaciones.',
        bannerImage: '/img/works/works/banner-music.jpg',
    },
    'software': {
        title: 'Software',
        description: 'Soluciones digitales que transforman tu mundo, hechas a tu medida.',
        bannerImage: '/img/works/works/banner-software.jpg',
    },
};

let currentFilters = {
    category: 'todos',
    price: 'all',
    popularity: 'all',
};
let currentPage = 1;
const itemsPerPage = 8;

const productCardsContainer = document.getElementById('product-cards-container');
const paginationContainer = document.getElementById('pagination-container');
const categoryFilters = document.getElementById('category-filters');
const priceFilters = document.getElementById('price-filters');
const popularityFilters = document.getElementById('popularity-filters');
const applyFiltersButton = document.getElementById('apply-filters');
const clearFiltersButton = document.getElementById('clear-filters');
const dynamicBanner = document.getElementById('dynamic-banner');
const bannerTitle = document.getElementById('banner-title');
const bannerDescription = document.getElementById('banner-description');


function createProductCard(product) {
    const starsHtml = Array(5).fill().map((_, i) =>
        `<i class="fas fa-star ${i < product.popularity ? 'text-gray-900' : 'text-gray-300'}"></i>`
    ).join('');

    return `
        <div class="bg-white rounded-lg shadow-md overflow-hidden transform hover:scale-105 transition duration-300 ease-in-out">
            <img src="${product.image}" alt="${product.title}" class="w-full h-48 object-cover">
            <div class="p-4">
            <p class="text-sm text-gray-500 mb-2">${product.author}</p>
                <h3 class="text-lg font-semibold text-gray-800 mb-1">${product.title}</h3>
                
                <div class="flex items-center mb-3">
                    ${starsHtml}
                </div>
                <div class="flex justify-between items-center mt-4">
                    <span class="text-xl font-bold text-gray-900">S/${product.price}</span>
                    <button class="bg-gray-900 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-200 text-sm">
                        Agregar
                    </button>
                </div>
            </div>
        </div>
    `;
}


function applyFilters(products, filters) {
    return products.filter(product => {

        if (filters.category !== 'todos' && product.category !== filters.category) {
            return false;
        }

        if (filters.price !== 'all') {
            const [minStr, maxStr] = filters.price.split('-');
            const min = parseInt(minStr);
            const max = maxStr === 'plus' ? Infinity : parseInt(maxStr);

            if (product.price < min || product.price > max) {
                return false;
            }
        }

        if (filters.popularity !== 'all') {
            const minPopularity = parseInt(filters.popularity);
            if (product.popularity < minPopularity) {
                return false;
            }
        }
        return true;
    });
}


function renderProductCards(productsToDisplay) {
    if (productsToDisplay.length === 0) {
        productCardsContainer.innerHTML = '<p class="text-center text-gray-600 col-span-full">No se encontraron productos con los filtros seleccionados.</p>';
    } else {
        productCardsContainer.innerHTML = productsToDisplay.map(createProductCard).join('');
    }
}


function renderPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    paginationContainer.innerHTML = '';


    if (totalPages <= 1 && totalItems <= itemsPerPage) {
        return;
    }

    const prevButton = document.createElement('button');
    prevButton.innerHTML = `<i class="fas fa-chevron-left"></i>`;
    prevButton.className = `px-3 py-1 rounded-md transition duration-200 ${currentPage === 1 ? 'bg-gray-300 text-gray-500 cursor-not-allowed' : 'bg-white text-blue-600 hover:bg-gray-200 border border-gray-300'}`;
    prevButton.disabled = currentPage === 1;
    prevButton.addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            updateDisplay();
        }
    });
    paginationContainer.appendChild(prevButton);


    const maxPagesToShow = 5;
    let startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
    let endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);


    if (endPage - startPage + 1 < maxPagesToShow && totalPages >= maxPagesToShow) {
        startPage = Math.max(1, totalPages - maxPagesToShow + 1);
        endPage = totalPages;
    }


    if (startPage > 1) {
        const firstPageButton = document.createElement('button');
        firstPageButton.textContent = '1';
        firstPageButton.className = `px-3 py-1 rounded-md transition duration-200 bg-white text-blue-600 hover:bg-gray-200 border border-gray-300`;
        firstPageButton.addEventListener('click', () => {
            currentPage = 1;
            updateDisplay();
        });
        paginationContainer.appendChild(firstPageButton);
        if (startPage > 2) {
            const ellipsis = document.createElement('span');
            ellipsis.textContent = '...';
            ellipsis.className = 'px-3 py-1 text-gray-500';
            paginationContainer.appendChild(ellipsis);
        }
    }

    for (let i = startPage; i <= endPage; i++) {
        const pageButton = document.createElement('button');
        pageButton.textContent = i;
        pageButton.className = `px-3 py-1 rounded-md transition duration-200 ${i === currentPage ? 'bg-blue-600 text-white' : 'bg-white text-blue-600 hover:bg-gray-200 border border-gray-300'}`;
        pageButton.addEventListener('click', () => {
            currentPage = i;
            updateDisplay();
        });
        paginationContainer.appendChild(pageButton);
    }

    if (endPage < totalPages) {
        if (endPage < totalPages - 1) {
            const ellipsis = document.createElement('span');
            ellipsis.textContent = '...';
            ellipsis.className = 'px-3 py-1 text-gray-500';
            paginationContainer.appendChild(ellipsis);
        }
        const lastPageButton = document.createElement('button');
        lastPageButton.textContent = totalPages;
        lastPageButton.className = `px-3 py-1 rounded-md transition duration-200 bg-white text-blue-600 hover:bg-gray-200 border border-gray-300`;
        lastPageButton.addEventListener('click', () => {
            currentPage = totalPages;
            updateDisplay();
        });
        paginationContainer.appendChild(lastPageButton);
    }

    // Botón Siguiente
    const nextButton = document.createElement('button');
    nextButton.innerHTML = `<i class="fas fa-chevron-right"></i>`;
    nextButton.className = `px-3 py-1 rounded-md transition duration-200 ${currentPage === totalPages ? 'bg-gray-300 text-gray-500 cursor-not-allowed' : 'bg-white text-blue-600 hover:bg-gray-200 border border-gray-300'}`;
    nextButton.disabled = currentPage === totalPages;
    nextButton.addEventListener('click', () => {
        if (currentPage < totalPages) {
            currentPage++;
            updateDisplay();
        }
    });
    paginationContainer.appendChild(nextButton);
}


function updateBanner(category) {
    const info = categoryInfo[category];
    if (info) {
        bannerTitle.textContent = info.title;
        bannerDescription.textContent = info.description;
        dynamicBanner.style.backgroundImage = `url(${info.bannerImage})`;
    }
}


function getSelectedFilters() {
    const selectedCategory = document.querySelector('input[name="category"]:checked')?.value || 'todos';
    const selectedPrice = document.querySelector('input[name="price"]:checked')?.value || 'all';
    const selectedPopularity = document.querySelector('input[name="popularity"]:checked')?.value || 'all';

    return {
        category: selectedCategory,
        price: selectedPrice,
        popularity: selectedPopularity,
    };
}


function updateDisplay() {
    const filtersToApply = currentFilters;

    const filteredProducts = applyFilters(allProducts, filtersToApply);

    const maxPossiblePage = Math.max(1, Math.ceil(filteredProducts.length / itemsPerPage));
    if (currentPage > maxPossiblePage && filteredProducts.length > 0) {
        currentPage = maxPossiblePage;
    } else if (filteredProducts.length === 0) {
        currentPage = 1;
    }

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const productsToDisplay = filteredProducts.slice(startIndex, endIndex);

    renderProductCards(productsToDisplay);

    renderPagination(filteredProducts.length);

    updateBanner(filtersToApply.category);
}

categoryFilters.addEventListener('change', (event) => {
    if (event.target.name === 'category') {
        currentFilters.category = event.target.value;
        currentPage = 1;
        updateDisplay();
    }
});

applyFiltersButton.addEventListener('click', () => {
    currentFilters = getSelectedFilters();
    currentPage = 1;
    updateDisplay();
});

clearFiltersButton.addEventListener('click', () => {

    document.querySelector('input[name="category"][value="todos"]').checked = true;
    document.querySelector('input[name="price"][value="all"]').checked = true;
    document.querySelector('input[name="popularity"][value="all"]').checked = true;


    currentFilters = {
        category: 'todos',
        price: 'all',
        popularity: 'all',
    };
    currentPage = 1;
    updateDisplay();
});

document.addEventListener('DOMContentLoaded', updateDisplay);