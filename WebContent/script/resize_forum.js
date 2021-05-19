$(window).on('resize', function(){
    var win = $(this); //this = window
    if (win.width() < 1000) {
        $(".hidden_resize").css("display", "none");
        $(".no_hidden_resize").removeClass("col-5");
        $(".no_hidden_resize").addClass("col");
    }
    else{
        $(".hidden_resize").css("display", "block");
        $(".no_hidden_resize").removeClass("col");
        $(".no_hidden_resize").addClass("col-5");
    }
});

function resizeOnLoad(){
    if (window.innerWidth < 1000) {
        $(".hidden_resize").css("display", "none");
        $(".no_hidden_resize").removeClass("col-5");
        $(".no_hidden_resize").addClass("col");
    }
}

window.onload = function() {
    resizeOnLoad();
};