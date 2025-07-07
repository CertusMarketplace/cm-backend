document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt_token');
    if (!token) {
        window.location.href = '/marketplace/auth/login';
        return;
    }

    const contentArea = document.getElementById("all-content");

    const loadContent = (view) => {
        if (!contentArea) return;
        fetch(`/marketplace/fragments/${view}`)
            .then(res => res.ok ? res.text() : Promise.reject(res))
            .then(html => {
                contentArea.innerHTML = html;
                if (view === 'seller-works' || view === 'seller-works-under-review') {
                    loadSellerWorks();
                }
            })
            .catch(err => console.error(`Error loading view ${view}:`, err));
    };

    window.loadView = (view) => {
        let activeLinkId = '';
        if (view === 'seller-works') activeLinkId = 'stateLinkWork';
        if (view === 'seller-works-under-review') activeLinkId = 'stateLinkReview';
        if (view === 'seller-sales') activeLinkId = 'stateLinkSales';
        setActiveSidebarLink(activeLinkId);
        loadContent(view);
    };

    const setActiveSidebarLink = (activeId) => {
        const links = ['stateLinkWork', 'stateLinkReview', 'stateLinkSales', 'stateLinkSettings', 'stateLinkAccount', 'stateLinkLogout'];
        links.forEach(id => {
            const element = document.getElementById(id);
            if (element) {
                element.classList.toggle('bg-skyBlueCertus', id === activeId);
                element.classList.toggle('hover:bg-blue-900', id !== activeId);
            }
        });
    };

    if(contentArea) {
        loadView('seller-works');
        setActiveSidebarLink('stateLinkWork');
    }

    const modal = document.getElementById('modalAddWork');
    const form = document.getElementById('createWorkForm');

    if (modal && form) {
        const imageDropZone = document.getElementById('image-drop-zone');
        const imageInput = document.getElementById('imageUpload');
        const imagePreviewContainer = document.getElementById('image-preview-container');
        const projectDropZone = document.getElementById('project-drop-zone');
        const projectInput = document.getElementById('projectUpload');
        const projectPreviewContainer = document.getElementById('project-preview-container');
        const categorySelect = document.getElementById('idWorkCategory');
        const errorMessageDiv = document.getElementById('create-work-error-message');
        let selectedImageFiles = [];

        const handleImageFiles = (files) => {
            for (const file of files) { if (selectedImageFiles.length < 5 && file.type.startsWith('image/')) { selectedImageFiles.push(file); } }
            if (selectedImageFiles.length > 5) { selectedImageFiles = selectedImageFiles.slice(0, 5); alert('Puedes seleccionar un máximo de 5 imágenes.'); }
            updateImagePreviews();
        };
        const updateImagePreviews = () => {
            imagePreviewContainer.innerHTML = '';
            selectedImageFiles.forEach((file, index) => {
                const reader = new FileReader();
                reader.onload = (e) => {
                    const previewWrapper = document.createElement('div');
                    previewWrapper.className = 'relative w-full h-24';
                    previewWrapper.innerHTML = `<img src="${e.target.result}" class="w-full h-full object-cover rounded-lg"><button type="button" class="absolute top-1 right-1 bg-red-600 text-white rounded-full w-5 h-5 flex items-center justify-center text-xs" data-index="${index}">×</button>`;
                    imagePreviewContainer.appendChild(previewWrapper);
                };
                reader.readAsDataURL(file);
            });
        };
        const handleProjectFile = (file) => {
            if (!file) return;
            projectPreviewContainer.innerHTML = `<div class="flex items-center p-2 border rounded-lg bg-gray-50"><i class="fa-solid fa-file-zipper text-2xl text-gray-500 mr-3"></i><span class="text-sm font-medium text-gray-700 flex-grow truncate">${file.name}</span><button type="button" id="remove-project-file" class="text-red-600 hover:text-red-800 text-lg font-bold">×</button></div>`;
            const dataTransfer = new DataTransfer();
            dataTransfer.items.add(file);
            projectInput.files = dataTransfer.files;
        };

        imageDropZone.addEventListener('click', () => imageInput.click());
        imageInput.addEventListener('change', () => handleImageFiles(imageInput.files));
        ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => { imageDropZone.addEventListener(eventName, e => { e.preventDefault(); e.stopPropagation(); if (['dragenter', 'dragover'].includes(eventName)) { imageDropZone.classList.add('border-blue-500', 'bg-blue-50'); } else { imageDropZone.classList.remove('border-blue-500', 'bg-blue-50'); } }, false); });
        imageDropZone.addEventListener('drop', e => handleImageFiles(e.dataTransfer.files));
        imagePreviewContainer.addEventListener('click', e => { if (e.target.tagName === 'BUTTON') { const index = parseInt(e.target.dataset.index, 10); selectedImageFiles.splice(index, 1); updateImagePreviews(); } });
        projectDropZone.addEventListener('click', () => projectInput.click());
        projectInput.addEventListener('change', () => handleProjectFile(projectInput.files[0]));
        ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => { projectDropZone.addEventListener(eventName, e => { e.preventDefault(); e.stopPropagation(); if (['dragenter', 'dragover'].includes(eventName)) { projectDropZone.classList.add('border-blue-500', 'bg-blue-50'); } else { projectDropZone.classList.remove('border-blue-500', 'bg-blue-50'); } }, false); });
        projectDropZone.addEventListener('drop', e => handleProjectFile(e.dataTransfer.files[0]));
        projectPreviewContainer.addEventListener('click', e => { if (e.target.id === 'remove-project-file') { projectInput.value = ''; projectPreviewContainer.innerHTML = ''; } });

        form.addEventListener('submit', async (e) => {
            e.preventDefault();
            errorMessageDiv.classList.add('hidden');
            const token = localStorage.getItem('jwt_token');

            const formData = new FormData();
            formData.append('idWorkCategory', categorySelect.value);
            formData.append('workTitle', document.getElementById('workTitle').value);
            formData.append('workDescription', document.getElementById('workDescription').value);
            formData.append('workPrice', document.getElementById('workPrice').value);
            formData.append('projectFile', projectInput.files[0]);
            selectedImageFiles.forEach(file => {
                formData.append('imageFiles', file);
            });

            try {
                const response = await fetch('/marketplace/dashboard/seller/works/create', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                    body: formData
                });

                if (response.redirected) {
                    window.location.href = response.url;
                    return;
                }

                if (response.ok) {
                    alert('¡Trabajo enviado a revisión con éxito!');
                    if (modal.close) modal.close();
                    window.loadView('seller-works-under-review');
                } else {
                    const errorText = await response.text();
                    errorMessageDiv.textContent = `Error: ${errorText || 'Error desconocido al crear el trabajo.'}`;
                    errorMessageDiv.classList.remove('hidden');
                }
            } catch (error) {
                console.error('Error de red al crear el trabajo:', error);
                errorMessageDiv.textContent = 'Hubo un error de red. Inténtalo de nuevo.';
                errorMessageDiv.classList.remove('hidden');
            }
        });
    }
});

async function loadSellerWorks() {
    const token = localStorage.getItem('jwt_token');
    const worksContainer = document.getElementById('works-container');
    const reviewContainer = document.getElementById('review-container');

    if (!token) return;

    try {
        const sellerId = JSON.parse(atob(token.split('.')[1])).sub;
        const response = await fetch(`/api/v1/works/seller/${sellerId}`, {
            headers: {'Authorization': `Bearer ${token}`}
        });

        if (!response.ok) throw new Error('No se pudieron cargar los trabajos del vendedor.');
        const works = await response.json();

        const publishedWorks = works.filter(w => w.workStatus === 'PUBLICADO');
        const reviewWorks = works.filter(w => w.workStatus === 'EN_REVISION');

        if (worksContainer) {
            worksContainer.innerHTML = publishedWorks.length > 0 ? publishedWorks.map(createWorkCard).join('') : '<p class="col-span-full text-center text-gray-500">No tienes trabajos publicados.</p>';
        }
        if (reviewContainer) {
            reviewContainer.innerHTML = reviewWorks.length > 0 ? reviewWorks.map(createWorkCard).join('') : '<p class="col-span-full text-center text-gray-500">No tienes trabajos en revisión.</p>';
        }

    } catch (error) {
        console.error("Error al cargar los trabajos del vendedor:", error);
        if (worksContainer) worksContainer.innerHTML = '<p class="col-span-full text-center text-red-500">Error al cargar trabajos.</p>';
        if (reviewContainer) reviewContainer.innerHTML = '<p class="col-span-full text-center text-red-500">Error al cargar trabajos.</p>';
    }
}

function createWorkCard(work) {
    const statusClass = work.workStatus === 'PUBLICADO' ? 'text-green-600' : 'text-yellow-500';
    return `
        <div class="max-w-xs p-3 space-y-2 border-2 rounded-md bg-white">
            <div class="flex justify-center">
                <img src="${work.workImageUrl || '/img/placeholder.png'}" class="h-48 w-full object-cover rounded-lg" alt="${work.workTitle}">
            </div>
            <div class="text-sm space-y-1.5">
                <h5><strong>Trabajo:</strong> ${work.workTitle}</h5>
                <p><strong>Precio:</strong> S/${work.workPrice.toFixed(2)}</p>
                <p class="font-bold ${statusClass}"><strong>Estado:</strong> ${work.workStatus.replace('_', ' ')}</p>
            </div>
            <div class="flex space-x-1">
                <button onclick="openEditModal(${work.workId})" class="rounded-full text-white text-sm w-full py-1.5 bg-greenCertus">Editar</button>
                ${work.workStatus === 'PUBLICADO' ? `<button onclick="openRequestsModal(${work.workId})" class="rounded-full text-white text-sm w-full py-1.5 bg-blueCertus">Solicitudes</button>` : ''}
                <button onclick="confirmDelete(${work.workId})" class="rounded-full text-white text-sm w-full py-1.5 bg-redCertus">Eliminar</button>
            </div>                   
        </div>
    `;
}

function openEditModal(workId) { console.log(`Editar trabajo ${workId}`); }
function openRequestsModal(workId) { console.log(`Ver solicitudes para ${workId}`); }
function confirmDelete(workId) { console.log(`Eliminar trabajo ${workId}`); }