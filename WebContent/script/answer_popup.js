function toggleAnswerPopup(){
    const reward_button = document.getElementById("reward_button");
    reward_button.disabled = true;
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
        reward_button.disabled = false;
    }
    $("#question_popup").animate({top: top_value}, 200);
}

function disableScroll() {
    document.body.style.overflow = 'hidden';
    //document.querySelector('html').scrollTop = window.scrollY;
}

function enableScroll() {
    document.body.style.overflow = null;
}