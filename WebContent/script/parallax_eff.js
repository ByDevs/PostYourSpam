function init(){

    let l1 = document.getElementById("l1");
        let lc = document.getElementById("lcloud");
        let lw = document.getElementById("lwel");
        let l2 = document.getElementById("l2");
        let l3 = document.getElementById("l3");
        let l4 = document.getElementById("l4");
        let l5 = document.getElementById("l5");

        let divElm = document.getElementById("px-container");
        divElm.style.maxHeight = window.outerWidth * 0.6 + 'px';

        let minTop = 50;

        window.addEventListener('scroll', function() {

            let value = window.scrollY;

            l2.style.top = (value * 0.4) + minTop + 'px';
            l2.style.left = value * 0.25 + 'px';
            
            lc.style.top = (-value * 0.2) + minTop + 'px';
            lw.style.top = (value * 0.3) + minTop + 'px';

            l4.style.top = (value * 0.13) + minTop + 'px';
            l5.style.top = (value * 0.08) + minTop + 'px';

        });

        window.addEventListener('resize', function() {
                
                divElm.style.maxHeight = window.outerWidth * 0.6 + 'px';

        })    

}

window.onload = function() {
    init();
};