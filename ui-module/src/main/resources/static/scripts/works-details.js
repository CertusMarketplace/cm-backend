document.addEventListener('DOMContentLoaded', () => {
    const commentSection = document.getElementById('comment-section');
    if (!commentSection) return;

    const workId = commentSection.dataset.workId;
    const commentForm = document.getElementById('comment-form');
    const commentTextarea = document.getElementById('comment-textarea');
    const commentsList = document.getElementById('comments-list');
    const submitBtn = document.getElementById('submit-comment-btn');
    const authMessage = document.getElementById('comment-auth-message');

    const token = localStorage.getItem('jwt_token');

    if (!token) {
        if(commentTextarea) commentTextarea.disabled = true;
        if(submitBtn) submitBtn.disabled = true;
        if(authMessage) authMessage.classList.remove('hidden');
    }

    const formatDate = (dateString) => {
        if (!dateString) return '';
        const date = new Date(dateString);
        return date.toLocaleDateString('es-ES', {
            year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit'
        });
    };

    const createCommentHTML = (comment) => {
        const userName = comment.userName || `Usuario Anónimo`;
        const userAvatar = comment.userAvatarUrl || '/img/works/perfil-generico.png';

        const repliesHTML = comment.replies && comment.replies.length > 0
            ? `<div class="mt-4 pl-6 border-l-2 border-gray-200 space-y-4">
                 ${comment.replies.map(reply => createCommentHTML(reply)).join('')}
               </div>`
            : '';

        return `
            <div class="flex items-start gap-3" data-comment-id="${comment.id}">
                <img class="w-10 h-10 rounded-full object-cover" src="${userAvatar}" alt="${userName}" referrerpolicy="no-referrer">
                <div class="flex-1">
                    <div class="bg-gray-100 p-3 rounded-lg">
                        <p class="font-semibold text-sm text-gray-800">${userName}</p>
                        <p class="text-sm text-gray-600" style="white-space: pre-wrap;">${comment.commentBody}</p>
                    </div>
                    <div class="text-xs text-gray-500 mt-1 flex items-center gap-3">
                        <span>${formatDate(comment.createdAt)}</span>
                        <button class="font-medium hover:underline reply-btn">Responder</button>
                    </div>
                    <div class="reply-form-container mt-2"></div>
                    ${repliesHTML}
                </div>
            </div>
        `;
    };

    const renderComments = (comments) => {
        if (!comments || comments.length === 0) {
            commentsList.innerHTML = '<p class="text-gray-500">Aún no hay comentarios. ¡Sé el primero!</p>';
            return;
        }
        commentsList.innerHTML = comments.map(comment => createCommentHTML(comment)).join('');
    };

    const fetchAndRenderComments = async () => {
        try {
            const response = await fetch(`/api/v1/works/${workId}`);
            if (!response.ok) throw new Error('No se pudo cargar la información del trabajo.');
            const workData = await response.json();
            renderComments(workData.comments || []);
        } catch (error) {
            console.error('Error fetching comments:', error);
            commentsList.innerHTML = '<p class="text-red-500">Error al cargar los comentarios.</p>';
        }
    };

    const handleCommentSubmit = async (event, parentId = null) => {
        event.preventDefault();
        const form = event.target;
        const textarea = form.querySelector('textarea');
        const commentBody = textarea.value.trim();

        if (!commentBody || !token) return;

        const payload = {
            workId: parseInt(workId),
            parentCommentId: parentId,
            commentBody: commentBody,
        };

        const currentSubmitBtn = form.querySelector('button[type="submit"]');
        const originalButtonText = currentSubmitBtn.innerHTML;
        currentSubmitBtn.disabled = true;
        currentSubmitBtn.innerHTML = 'Publicando...';

        try {
            const response = await fetch('/api/v1/comments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Error al publicar el comentario.');
            }

            textarea.value = '';
            if (parentId) {
                const replyContainer = document.querySelector(`[data-comment-id="${parentId}"] .reply-form-container`);
                if(replyContainer) replyContainer.innerHTML = '';
            }
            await fetchAndRenderComments();

        } catch (error) {
            console.error('Error submitting comment:', error);
            alert(error.message);
        } finally {
            currentSubmitBtn.disabled = false;
            currentSubmitBtn.innerHTML = originalButtonText;
        }
    };

    commentsList.addEventListener('click', e => {
        if (e.target.classList.contains('reply-btn')) {
            if (!token) {
                authMessage.scrollIntoView({ behavior: 'smooth', block: 'center' });
                return;
            }

            const parentCommentDiv = e.target.closest('[data-comment-id]');
            const parentId = parentCommentDiv.dataset.commentId;
            const replyContainer = parentCommentDiv.querySelector('.reply-form-container');

            if (replyContainer.innerHTML) {
                replyContainer.innerHTML = '';
            } else {
                document.querySelectorAll('.reply-form-container').forEach(c => c.innerHTML = '');
                replyContainer.innerHTML = `
                    <form class="reply-form mt-2" data-parent-id="${parentId}">
                        <textarea id="reply-textarea-${parentId}" rows="2" class="block p-2 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500" placeholder="Escribe tu respuesta..." required></textarea>
                        <button type="submit" class="mt-2 text-xs font-medium text-white bg-[#00205b] py-1 px-3 rounded-lg hover:bg-[#001a4d]">Responder</button>
                    </form>
                `;
                document.getElementById(`reply-textarea-${parentId}`).focus();
            }
        }
    });

    commentsList.addEventListener('submit', e => {
        if (e.target.classList.contains('reply-form')) {
            e.preventDefault();
            const parentId = e.target.dataset.parentId;
            handleCommentSubmit(e, parentId);
        }
    });

    if (commentForm) {
        commentForm.addEventListener('submit', (e) => handleCommentSubmit(e, null));
    }

    fetchAndRenderComments();
});