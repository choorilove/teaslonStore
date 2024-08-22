// carouselItem.js
document.addEventListener('DOMContentLoaded', () => {
    const carouselImages = document.querySelector('.carousel-images');
    const items = document.querySelectorAll('.icarousel-item');
    const itemCount = items.length;
    let index = 0;

    function moveToNextSlide() {
        index = (index + 1) % itemCount;
        const offset = -index * 100;
        carouselImages.style.transform = `translateX(${offset}%)`;
    }

    setInterval(moveToNextSlide, 3000); // Change image every 3 seconds
});