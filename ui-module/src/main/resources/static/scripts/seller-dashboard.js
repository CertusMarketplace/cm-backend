document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt_token');
    if (!token) {
        window.location.href = '/marketplace/auth/login';
        return;
    }

    const contentArea = document.getElementById("all-content");
    const sidebar = document.getElementById('default-sidebar');

    const loadView = async (view) => {
        if (!contentArea) return;

        contentArea.innerHTML = `<div class="flex items-center justify-center min-h-[80vh]"><p class="text-gray-500">Cargando...</p></div>`;

        try {
            const response = await fetch(`/marketplace/fragments/${view}`);
            if (!response.ok) throw new Error(`Error al cargar la vista: ${response.statusText}`);

            contentArea.innerHTML = await response.text();

            if (view === 'seller-works') {
                await loadSellerWorks('PUBLICADO');
            } else if (view === 'seller-works-under-review') {
                await loadSellerWorks('EN_REVISION');
            } else if (view === 'seller-sales') {
                await loadSellerSales();
            }
        } catch (err) {
            console.error(`Error al cargar la vista ${view}:`, err);
            contentArea.innerHTML = `<div class="flex items-center justify-center min-h-[80vh]"><p class="text-center text-red-500">No se pudo cargar la sección. Por favor, intenta de nuevo.</p></div>`;
        }
    };

    const setActiveSidebarLink = (activeId) => {
        document.querySelectorAll('#default-sidebar [data-view]').forEach(link => {
            link.classList.remove('bg-skyBlueCertus');
            link.classList.add('hover:bg-blue-900');
        });
        const activeLink = document.getElementById(activeId);
        if (activeLink) {
            activeLink.classList.add('bg-skyBlueCertus');
            activeLink.classList.remove('hover:bg-blue-900');
        }
    };

    if (sidebar) {
        sidebar.addEventListener('click', (e) => {
            const link = e.target.closest('a[data-view]');
            if (link) {
                e.preventDefault();
                const view = link.getAttribute('data-view');
                setActiveSidebarLink(link.id);
                loadView(view);
            }
        });
    }

    loadView('seller-works');
    setActiveSidebarLink('stateLinkWork');
});


function getUserIdFromToken() {
    const token = localStorage.getItem('jwt_token');
    if (!token) return null;
    try {
        return JSON.parse(atob(token.split('.')[1])).sub;
    } catch (e) {
        console.error("Error decodificando token:", e);
        return null;
    }
}

async function loadSellerWorks(statusFilter) {
    const token = localStorage.getItem('jwt_token');
    const sellerId = getUserIdFromToken();
    if (!sellerId) return;

    const isPublishedView = statusFilter === 'PUBLICADO';
    const containerId = isPublishedView ? 'works-container' : 'review-container';
    const container = document.getElementById(containerId);
    if (!container) return;

    try {
        const response = await fetch(`/api/v1/works/seller/${sellerId}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!response.ok) throw new Error('No se pudieron cargar los trabajos.');

        const allWorks = await response.json();

        const statusesToShow = isPublishedView ? ['PUBLICADO', 'RECHAZADO'] : ['EN_REVISION'];
        const worksToDisplay = allWorks.filter(w => statusesToShow.includes(w.workStatus));

        if (worksToDisplay.length > 0) {
            container.innerHTML = worksToDisplay.map(createWorkCard).join('');
        } else {
            const message = isPublishedView
                ? '¡Aún no tienes publicaciones!'
                : 'No tienes trabajos en revisión en este momento.';
            container.innerHTML = `<div class="col-span-full flex flex-col items-center justify-center min-h-[60vh]"><p class="text-xl text-gray-600">${message}</p></div>`;
        }

    } catch (error) {
        console.error("Error al cargar trabajos:", error);
        container.innerHTML = `<div class="col-span-full text-center py-10 text-red-500">Error al cargar los trabajos.</div>`;
    }
}

async function loadSellerSales() {
}

function createWorkCard(work) {
    let statusClass = '';
    let statusText = work.workStatus.replace('_', ' ');

    switch (work.workStatus) {
        case 'PUBLICADO': statusClass = 'bg-green-100 text-green-800'; break;
        case 'EN_REVISION': statusClass = 'bg-yellow-100 text-yellow-800'; break;
        case 'RECHAZADO': statusClass = 'bg-red-100 text-red-800'; break;
    }

    return `
        <div class="bg-white rounded-lg shadow-md overflow-hidden flex flex-col transform hover:-translate-y-1 transition-transform duration-300">
            <a href="/marketplace/works/${work.workId}" class="block">
                <img src="${work.workImageUrl || '/img/works/placeholder.png'}" alt="${work.workTitle}" class="w-full h-40 object-cover">
            </a>
            <div class="p-4 flex flex-col flex-grow">
                <h3 class="text-md font-bold text-gray-800 truncate" title="${work.workTitle}">${work.workTitle}</h3>
                <p class="text-sm text-gray-600 mb-2">S/${work.workPrice.toFixed(2)}</p>
                <span class="text-xs font-medium px-2.5 py-0.5 rounded-full self-start ${statusClass}">${statusText}</span>
                <div class="mt-auto pt-4 flex gap-2">
                    <button onclick="alert('Función no implementada')" class="flex-1 text-sm bg-gray-200 hover:bg-gray-300 text-gray-800 py-2 px-3 rounded-md transition">Editar</button>
                    <button onclick="alert('Función no implementada')" class="flex-1 text-sm bg-red-500 hover:bg-red-600 text-white py-2 px-3 rounded-md transition">Eliminar</button>
                </div>
            </div>
        </div>
    `;
}

document.addEventListener('DOMContentLoaded', () => {
    const observer = new MutationObserver((mutations) => {
        for (const mutation of mutations) {
            if (mutation.type === 'childList') {
                const createWorkForm = document.getElementById('createWorkForm');
                if (createWorkForm && !createWorkForm.dataset.initialized) {
                    initializeCreateWorkForm(createWorkForm);
                    createWorkForm.dataset.initialized = 'true';
                }
            }
        }
    });

    const contentArea = document.getElementById('all-content');
    if (contentArea) {
        observer.observe(contentArea, { childList: true, subtree: true });
    }

    const initialForm = document.getElementById('createWorkForm');
    if (initialForm) {
        initializeCreateWorkForm(initialForm);
    }
});

function initializeCreateWorkForm(form) {
    const imageDropZone = document.getElementById('image-drop-zone');
    const imageUploadInput = document.getElementById('imageUpload');
    const imagePreviewContainer = document.getElementById('image-preview-container');

    const projectDropZone = document.getElementById('project-drop-zone');
    const projectUploadInput = document.getElementById('projectUpload');
    const projectPreviewContainer = document.getElementById('project-preview-container');

    const errorMessageDiv = document.getElementById('create-work-error-message');

    const showMessage = (message, isError = true) => {
        if (!errorMessageDiv) return;
        errorMessageDiv.textContent = message;
        errorMessageDiv.className = isError
            ? 'block text-center text-red-700 text-sm p-3 bg-red-100 border border-red-400 rounded-lg'
            : 'block text-center text-green-700 text-sm p-3 bg-green-100 border border-green-400 rounded-lg';
    };

    const handleFiles = (files, container, isImage) => {
        container.innerHTML = '';
        if (isImage && files.length > 5) {
            showMessage('Puedes subir un máximo de 5 imágenes.');
            imageUploadInput.value = '';
            return;
        }
        for (const file of files) {
            const preview = document.createElement('div');
            preview.className = 'relative w-full h-24 rounded-lg overflow-hidden border';
            if (isImage) {
                const img = document.createElement('img');
                img.src = URL.createObjectURL(file);
                img.className = 'w-full h-full object-cover';
                preview.appendChild(img);
            } else {
                preview.className += ' p-2 flex flex-col items-center justify-center bg-gray-100';
                const icon = document.createElement('i');
                icon.className = 'fa-solid fa-file-zipper text-3xl text-gray-500 mb-1';
                const name = document.createElement('p');
                name.className = 'text-xs text-gray-600 truncate w-full text-center';
                name.textContent = file.name;
                preview.appendChild(icon);
                preview.appendChild(name);
            }
            container.appendChild(preview);
        }
    };

    const setupDropZone = (zone, input) => {
        ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
            zone.addEventListener(eventName, e => {
                e.preventDefault();
                e.stopPropagation();
            });
        });
        ['dragenter', 'dragover'].forEach(eventName => {
            zone.addEventListener(eventName, () => zone.classList.add('bg-blue-50', 'border-blue-500'));
        });
        ['dragleave', 'drop'].forEach(eventName => {
            zone.addEventListener(eventName, () => zone.classList.remove('bg-blue-50', 'border-blue-500'));
        });
        zone.addEventListener('drop', e => {
            input.files = e.dataTransfer.files;
            input.dispatchEvent(new Event('change'));
        });
    };

    if (imageDropZone && imageUploadInput) {
        setupDropZone(imageDropZone, imageUploadInput);
        imageUploadInput.addEventListener('change', () => handleFiles(imageUploadInput.files, imagePreviewContainer, true));
    }

    if(projectDropZone && projectUploadInput) {
        setupDropZone(projectDropZone, projectUploadInput);
        projectUploadInput.addEventListener('change', () => handleFiles(projectUploadInput.files, projectPreviewContainer, false));
    }


    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('jwt_token');
        const formData = new FormData(form);
        const submitButton = form.querySelector('button[type="submit"]');
        submitButton.disabled = true;
        submitButton.innerHTML = 'Subiendo...';
        showMessage('', false);

        try {
            const response = await fetch('/marketplace/dashboard/seller/works/create', {
                method: 'POST',
                headers: { 'Authorization': `Bearer ${token}` },
                body: formData
            });

            const result = await response.json();

            if (!response.ok) {
                throw new Error(result.errorMessage || 'Ocurrió un error inesperado.');
            }

            showMessage(result.successMessage, false);
            setTimeout(() => {
                const modal = document.getElementById('modalAddWork');
                if (modal) modal.close();
                form.reset();
                if(imagePreviewContainer) imagePreviewContainer.innerHTML = '';
                if(projectPreviewContainer) projectPreviewContainer.innerHTML = '';

                const event = new CustomEvent('work-created');
                document.dispatchEvent(event);

            }, 2000);

        } catch (error) {
            showMessage(error.message, true);
        } finally {
            submitButton.disabled = false;
            submitButton.innerHTML = 'Pasar a Revisión';
        }
    });

    document.addEventListener('work-created', () => {
        const worksLink = document.getElementById('stateLinkWork');
        if (worksLink) {
            worksLink.click();
        }
    });
}