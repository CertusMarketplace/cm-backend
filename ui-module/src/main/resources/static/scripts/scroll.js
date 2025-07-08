<<<<<<< Updated upstream
document.addEventListener("DOMContentLoaded", function () {
=======
document.addEventListener("DOMContentLoaded", function() {
>>>>>>> Stashed changes
    // Animaciones que se repiten
    const animatables = document.querySelectorAll('[data-animate]');
    // Animaciones que solo ocurren una vez
    const animatablesOnce = document.querySelectorAll('[data-animate-once]');

    function handleAnimation(element, animationClass) {
        const rect = element.getBoundingClientRect();
        if (rect.top < window.innerHeight && rect.bottom > 0) {
            if (!element.classList.contains(animationClass)) {
                element.classList.add(animationClass);
            }
        } else {
            element.classList.remove(animationClass);
        }
    }

    function handleAnimationOnce(element, animationClass) {
        if (element.classList.contains('animated-once')) return;
        const rect = element.getBoundingClientRect();
        if (rect.top < window.innerHeight && rect.bottom > 0) {
            element.classList.add(animationClass, 'animated-once');
        }
    }

    function onScroll() {
        animatables.forEach(el => {
            const animation = el.getAttribute('data-animate');
            handleAnimation(el, animation);
        });
        animatablesOnce.forEach(el => {
            const animation = el.getAttribute('data-animate-once');
            handleAnimationOnce(el, animation);
        });
    }

    window.addEventListener('scroll', onScroll);
    onScroll();
});