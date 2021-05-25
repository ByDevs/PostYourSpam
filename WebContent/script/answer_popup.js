function toggleAnswerPopup(){
    var blur = document.getElementById('blur');
    blur.classList.toggle('active');
    var popup = document.getElementById('question_popup');
    popup.classList.toggle('active');
    
    var top_value = $("#question_popup").css('top');
    if(top_value == "-100px"){
        top_value = "50%";
        disableScroll();
    }
    else {
        top_value = "-100px";
        enableScroll();
    }
    $("#question_popup").animate({top: top_value}, 200);
}

function disableScroll() {
    $('body').css("overflow","hidden");
}

function enableScroll() {
    $('body').css("overflow","initial");
}