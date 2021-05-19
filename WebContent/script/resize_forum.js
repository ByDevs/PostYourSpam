$(window).on('resize', function(){
    var win = $(this); //this = window
    //if (win.height() >= 820) { /* ... */ }
    if (win.width() < 1000) {
        $(".hidden_resize").css("display", "none");
    }
    else{
        $(".hidden_resize").css("display", "block");
    }
});

function resizeOnLoad(){
    if (screen.width < 1000)  {
        // if screen size width is less than 1024px
        // here you can also use show();
        $(".hidden_resize").css("display", "none");
    }
}