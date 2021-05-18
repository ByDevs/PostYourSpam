function togglePopup(){
    var blur = document.getElementById('blur');
    blur.classList.toggle('active');
    var popup = document.getElementById('popup');
    popup.classList.toggle('active');
    
    var top_value = $("#popup").css('top');
    if(top_value == "-100px"){
        top_value = "50%";
        disableScroll();
    }
    else {
        top_value = "-100px";
        enableScroll();
    }
    $("#popup").animate({top: top_value}, 200);
}

function disableScroll() {
    document.body.style.overflow = 'hidden';
    //document.querySelector('html').scrollTop = window.scrollY;
}

function enableScroll() {
    document.body.style.overflow = null;
}

function pressLogin() {
    togglePopup();
    $('#mini_user_stats').empty();
    var username = "DrTranzoc";
    var balance = "50.32";
    var substitute_div = " <div style='display: inline-block; padding-right:10px; vertical-align:middle;'> \
        <div> \
        <img src='images/user_icon.png' width='22' height='22'> \
        <a style='color:#ffffff'>" + username +"</a> \
        </div> \
        <div> \
        <img src='images/spam-logo.png' width='22' height='22'> \
        <a style='color:#ffffff' href='#'>" + balance + "</a> \
        </div> \
        </div> \
    ";
    $('#mini_user_stats').append(substitute_div);
}