$(window).on('resize', function(){
    resizeWindow();
});

$(function() {
    $('#switch_button').change(function() {
        onCheckedSwitch();
    })
  })

function onCheckedSwitch(){
    if($("#switch_button").prop('checked')){
        $("#profile_box").css("display","none");
        $("#wallet_box").css("display","block");
        $("#wallet_box").removeClass("col-md-4");

        $(".log_box").css("display", "block");
    }
    else{
        $("#wallet_box").addClass("col-md-4");
        $("#wallet_box").css("display","none");
        $("#profile_box").css("display","block");

        $(".log_box").css("display", "none");
    }
}

function resizeWindow(){
    var percentage = 90;
    if (window.innerWidth < ((screen.width * percentage) / 100)) {
        $("#switch_button_box").css("display", "block");
        $("#wallet_box").css("display","none");
        $("#profile_box").removeClass("col-md-8");
        onCheckedSwitch();
        $(".col-5").css("display", "none");
    }

    else{
        $("#profile_box").css("display","block");
        $("#profile_box").addClass("col-md-8");
        $("#switch_button_box").css("display", "none");
        $("#wallet_box").css("display","block");
        $(".log_box").css("display", "block");
        $(".col-5").css("display", "block");

    }
}

//funzione che inserisce i dati dell'utente nel placeholder delle textbox presenti nel form e imposta gli attributi della form withdraw
function loadProfileData(){
    var nickname = "DrTranzoc";
    var address = "Via dalle palle 69";
    var country = "Italy";
    var email = "DrTranzoc@gmail.com";
    var balance = 69;
    //var password = "******";

    $(".title_box").text(nickname);
    $("#address").attr("placeholder", address);
    $('#country option[value="' + country + '"]').attr("selected", "selected");
    $("#email").attr("placeholder", email);
    //$("#password").attr("placeholder", password);
    $("#balance_spam").attr("placeholder", balance);

    $("#limit_withdraw_text").text("limite max : " + balance);
    $("#quantity").prop("max", balance);
    $("#balance_spam").fadeIn("slow");
}


//funzione chiamata quando viene premuto il tasto "modifica"
function toggleEdit(){
    if($("#confirm").prop("disabled")){
        $("#edit").prop("disabled", true);
        $("#confirm").prop("disabled", false);
        $("#confirm_password").prop("disabled", false);
        $("#password").prop("disabled", false);
        $("#email").prop("disabled", false);
        $("#country").prop("disabled", false);
        $("#address").prop("disabled", false);
    }
    else{
        $("#edit").prop("disabled", false);
        $("#confirm").prop("disabled", true);
        $("#confirm_password").prop("disabled", true);
        $("#password").prop("disabled", true);
        $("#email").prop("disabled", true);
        $("#country").prop("disabled", true);
        $("#address").prop("disabled", true);
    }
}

function toggleDepositPopup(){
    var blur = document.getElementById('blur').classList.toggle('active');
    var deposit_popup = document.getElementById('deposit_popup').classList.toggle('active');
    
    var top_value = $("#deposit_popup").css('top');
    if(top_value == "-100px"){
        top_value = "50%";
        disableScroll();
    }
    else {
        top_value = "-100px";
        enableScroll();
    }
    $("#deposit_popup").animate({top: top_value}, 200);
}

function toggleWithdrawPopup(){
    var blur = document.getElementById('blur').classList.toggle('active');
    var deposit_popup = document.getElementById('withdraw_popup').classList.toggle('active');
    
    var top_value = $("#withdraw_popup").css('top');
    if(top_value == "-100px"){
        top_value = "50%";
        disableScroll();
    }
    else {
        top_value = "-100px";
        enableScroll();
    }
    $("#withdraw_popup").animate({top: top_value}, 200);
}

function disableScroll() {
    $('body').css("overflow","hidden");
}

function enableScroll() {
    $('body').css("overflow","initial");
}

//funzione chiamata dal submit del popup deposito
function confirmDeposit(){
    //cose
    toggleDepositPopup();
}

//funzione chiamata dal submit del popip withdraw
function confirmWithdraw(){
    //cose
    toggleWithdrawPopup();
}

//funzione chiamata alla pressione del tasto "submit"
function changeData(){
    //cose
    toggleEdit();
}

//funzione chiamata al caricamento della pagina
function loadProfile(){
    resizeWindow();
    loadProfileData();
}