function parallaxInit(){

    let divElm = document.getElementById("px-container");
    divElm.style.maxHeight = window.outerWidth * 0.6 + 'px';

    let minTop = 50;

    window.addEventListener('scroll', function() {

        let value = window.scrollY;

        $("#l2").css("top",(value * 0.4) + minTop + 'px');
        $("#l2").css("left",value * 0.25 + 'px');

        $("#l4").css("top",(value * 0.13) + minTop + 'px');
        $("#l5").css("top",(value * 0.08) + minTop + 'px');
        $("#lc").css("top",-(value * 0.2) + minTop + 'px');
        $("#lw").css("top",(value * 0.3) + minTop + 'px');

    });

    window.addEventListener('resize', function() {
                
        divElm.style.maxHeight = window.outerWidth * 0.6 + 'px';

    });    
}