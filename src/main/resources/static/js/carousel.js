document.addEventListener('DOMContentLoaded', () => {
    const carouselImages = document.querySelector('.carousel-images');
    const items = document.querySelectorAll('.carousel-item');
    const itemCount = items.length;
    let index = 0;

    function moveToSlide(newIndex) {
        index = (newIndex + itemCount) % itemCount;
        const offset = -index * 100;
        carouselImages.style.transform = `translateX(${offset}%)`;
    }

    function moveToNextSlide() {
        moveToSlide(index + 1);
    }

    function moveToPrevSlide() {
        moveToSlide(index - 1);
    }

    document.querySelector('.next').addEventListener('click', moveToNextSlide);
    document.querySelector('.prev').addEventListener('click', moveToPrevSlide);

    setInterval(moveToNextSlide, 7000); // Автоматическая смена изображения каждые 3 секунды
});
