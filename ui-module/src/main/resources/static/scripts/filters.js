document.addEventListener('DOMContentLoaded', () => {
    const productContainer = document.getElementById('product-cards-container');
    if (!productContainer) return;

    const API_URL = '/api/v1/works';
<<<<<<< Updated upstream
=======
    const paginationContainer = document.getElementById('pagination-container');
>>>>>>> Stashed changes
    const applyFiltersButton = document.getElementById('apply-filters');
    const clearFiltersButton = document.getElementById('clear-filters');
    const dynamicBanner = document.getElementById('dynamic-banner');
    const bannerTitle = document.getElementById('banner-title');
    const bannerDescription = document.getElementById('banner-description');

    const categoryInfo = {
<<<<<<< Updated upstream
        'todos': {
            title: 'Todos los Productos',
            description: 'Explora todas nuestras soluciones digitales disponibles.',
            bannerImage: '/img/works/banner-todos.jpg'
        },
        'cursos': {
            title: 'Cursos',
            description: 'Aprende y domina nuevas habilidades con nuestros cursos especializados.',
            bannerImage: '/img/works/banner-cursos.jpg'
        },
        'ilustraciones': {
            title: 'Ilustraciones',
            description: 'Descubre arte digital único y personalizable para tus proyectos.',
            bannerImage: '/img/works/banner-ilustraciones.jpg'
        },
        'musica-y-audio': {
            title: 'Música y Audio',
            description: 'Encuentra pistas, efectos y recursos de audio para tus creaciones.',
            bannerImage: '/img/works/banner-music.jpg'
        },
        'software': {
            title: 'Software',
            description: 'Soluciones digitales que transforman tu mundo, hechas a tu medida.',
            bannerImage: '/img/works/banner-software.jpg'
        },
    };

    function createProductCard(product) {
        const starsHtml = Array(5).fill(0).map((_, i) =>
            `<i class="fa-solid fa-star ${i < Math.round(product.averageRating) ? 'text-gray-900' : 'text-gray-300'}"></i>`
        ).join('');

=======
        'todos': { title: 'Todos los Productos', description: 'Explora todas nuestras soluciones digitales disponibles.', bannerImage: '/img/works/banner-todos.jpg' },
        'cursos': { title: 'Cursos', description: 'Aprende y domina nuevas habilidades con nuestros cursos especializados.', bannerImage: '/img/works/banner-cursos.jpg' },
        'ilustraciones': { title: 'Ilustraciones Digitales', description: 'Descubre arte digital único y personalizable para tus proyectos.', bannerImage: '/img/works/banner-ilustraciones.jpg' },
        'musica': { title: 'Música y Audio', description: 'Encuentra pistas, efectos y recursos de audio para tus creaciones.', bannerImage: '/img/works/banner-music.jpg' },
        'software': { title: 'Software', description: 'Soluciones digitales que transforman tu mundo, hechas a tu medida.', bannerImage: '/img/works/banner-software.jpg' },
    };

    let currentPage = 1;
    const itemsPerPage = 8;

    function createProductCard(product) {
        const starsHtml = Array(5).fill().map((_, i) =>
            `<i class="fa-solid fa-star ${i < Math.round(product.averageRating) ? 'text-gray-900' : 'text-gray-300'}"></i>`
        ).join('');
>>>>>>> Stashed changes
        const detailUrl = `/marketplace/works/${product.workId}`;

        return `
            <div class="bg-white rounded-lg shadow-md overflow-hidden transform hover:scale-105 transition duration-300 ease-in-out">
                <a href="${detailUrl}">
                    <img src="${product.workImageUrl}" alt="${product.workTitle}" class="w-full h-48 object-cover">
                </a>
                <div class="p-4">
<<<<<<< Updated upstream
                    <p class="text-sm text-gray-500 mb-2">${product.sellerName || 'Vendedor Anónimo'}</p>
=======
                    <p class="text-sm text-gray-500 mb-2">Vendedor ID: ${product.idSellerUser}</p>
>>>>>>> Stashed changes
                    <a href="${detailUrl}" class="text-lg font-semibold text-gray-800 mb-1 hover:text-[#00205b]">${product.workTitle}</a>
                    <div class="flex items-center mb-3">${starsHtml}</div>
                    <div class="flex justify-between items-center mt-4">
                        <span class="text-xl font-bold text-gray-900">S/${product.workPrice.toFixed(2)}</span>
                        <button class="add-to-cart-btn bg-gray-900 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-200 text-sm" data-id="${product.workId}">Agregar</button>
                    </div>
                </div>
            </div>
        `;
    }

    function renderProductCards(products) {
        productContainer.innerHTML = products.length === 0
            ? '<p class="text-center text-gray-600 col-span-full">No se encontraron productos con los filtros seleccionados.</p>'
            : products.map(createProductCard).join('');
    }

    async function fetchAndDisplayWorks() {
        const filters = getSelectedFilters();
        const params = new URLSearchParams({
<<<<<<< Updated upstream
=======
            page: currentPage - 1,
            size: itemsPerPage,
>>>>>>> Stashed changes
            category: filters.category,
            priceRange: filters.price,
            popularity: filters.popularity === 'all' ? 0 : filters.popularity
        });

        try {
            const response = await fetch(`${API_URL}?${params.toString()}`);
            if (!response.ok) throw new Error('Network response was not ok');
            const products = await response.json();

            renderProductCards(products);
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
            updateBanner(filters.category);

        } catch (error) {
            console.error("Fetch error:", error);
            productContainer.innerHTML = '<p class="text-center text-red-500 col-span-full">Error al cargar los productos.</p>';
        }
    }

    function getSelectedFilters() {
        return {
            category: document.querySelector('input[name="category"]:checked')?.value || 'todos',
            price: document.querySelector('input[name="price"]:checked')?.value || 'all',
            popularity: document.querySelector('input[name="popularity"]:checked')?.value || 'all',
        };
    }

    function updateBanner(category) {
<<<<<<< Updated upstream
        const info = categoryInfo[category.replace(/\s/g, '-').toLowerCase()];
=======
        const info = categoryInfo[category];
>>>>>>> Stashed changes
        if (info && bannerTitle && bannerDescription && dynamicBanner) {
            bannerTitle.textContent = info.title;
            bannerDescription.textContent = info.description;
            dynamicBanner.style.backgroundImage = `url(${info.bannerImage})`;
        }
    }

    applyFiltersButton.addEventListener('click', () => {
<<<<<<< Updated upstream
=======
        currentPage = 1;
>>>>>>> Stashed changes
        fetchAndDisplayWorks();
    });

    clearFiltersButton.addEventListener('click', () => {
        document.querySelector('input[name="category"][value="todos"]').checked = true;
        document.querySelector('input[name="price"][value="all"]').checked = true;
        document.querySelector('input[name="popularity"][value="all"]').checked = true;
<<<<<<< Updated upstream
=======
        currentPage = 1;
>>>>>>> Stashed changes
        fetchAndDisplayWorks();
    });

    fetchAndDisplayWorks();
});