const percentage = 55;

$(window).on('resize', function(){
    var win = $(this); //this = window
    if (win.width() < (screen.width * percentage) / 100) {
        $(".hidden_resize").css("display", "none");
        $(".no_hidden_resize").removeClass("col-5");
        $(".no_hidden_resize").addClass("col");
        $("#normal_button").css("display", "none");
        $("#mini_button").css("display", "block");
    }
    else{
        $(".hidden_resize").css("display", "block");
        $(".no_hidden_resize").removeClass("col");
        $(".no_hidden_resize").addClass("col-5");
        $("#mini_button").css("display", "none");
        $("#normal_button").css("display", "block");
    }
});

function resizeOnLoad(){
    if (window.innerWidth < (screen.width * percentage) / 100) {
        $(".hidden_resize").css("display", "none");
        $(".no_hidden_resize").removeClass("col-5");
        $(".no_hidden_resize").addClass("col");
        $("#normal_button").css("display", "none");
        $("#mini_button").css("display", "block");
    }
}

function forumResizeOnLoad(){
    resizeOnLoad();
}