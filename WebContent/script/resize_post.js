$(window).on('resize', function(){
    var win = $(this); //this = window
    if (win.width() < 1000) {
        if($("#first_column").css("display") == "block"){
            $("#first_column").css("display", "none");
            $("#third_column").children().appendTo("#resize_value_post");
            $("#third_column").css("display", "none");
            $("#resize_value_post").css("display", "inline-block");
        }   
    }
    else{
        if($("#resize_value_post").children().length != 0){
            $("#resize_value_post").children().appendTo("#third_column");
            $("#resize_value_post").empty();
        }
        if($("#first_column").css("display") == "none"){
            $("#first_column").css("display", "block");
            $("#resize_value_post").css("display", "none");
            $("#third_column").css("display", "block");
        }
        
    }
});

function resizeOnLoad(){
    if (window.innerWidth < 1000) {
        $("#first_column").css("display", "none");
        $("#third_column").children().appendTo("#resize_value_post");
        $("#third_column").css("display", "none");
        $("#resize_value_post").css("display", "inline-block");
    }
}

window.onload = function() {
    resizeOnLoad();
};