let billboard = document.getElementById("billb");
let box = document.getElementById("mainbox");
let size = window.outerWidth;

window.addEventListener("resize", function() {
     
    if(outerWidth <= screen.width/100*55){
        billboard.style.display = "none";
        box.style.width = 100 + "%";
    }
    else{
        billboard.style.display = "initial";
        box.style.width = 50 + "%";
    }
});

function billboardResizeOnLoad(){

    if(outerWidth <= screen.width/100*55){
        billboard.style.display = "none";
        box.style.width = 100 + "%";
    }
}