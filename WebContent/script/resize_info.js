var percentage = 30;


$(window).on('resize', function(){
    resizeInfo();
});


function resizeInfo() {
    if (window.innerWidth < (screen.width * percentage) / 100) {
        $("#1").removeClass("card-horizontal");
        $("#2").removeClass("card-horizontal");
    }
    else{
        $("#1").addClass("card-horizontal");
        $("#2").addClass("card-horizontal");
    }
}