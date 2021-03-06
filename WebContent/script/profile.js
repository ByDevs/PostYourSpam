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
    var nickname;
    var address;
    var country;
    var email;
    var balance;

    var http = sendRequest("getprofile.jsp");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            //Initialize the page
            //Check if there is atleast 1 posts available
            if(http.responseText == "1"){
                alert("Devi prima loggarti per accedere a questa sezione!");
                location.replace("home.html");
                return;
            }

            //Check for generic error
            else if(http.responseText == "2"){
                alert("Si ?? verificato un errore col server");
                return;
            }
            else{
                var response = http.responseText;
                email = response.split("|")[0];
                nickname = response.split("|")[1];
                country = response.split("|")[2];
                address = response.split("|")[3];
                balance = response.split("|")[4];

                $(".title_box").text(nickname);
                $("#address").attr("placeholder", address);
                $('#country option[value="' + country + '"]').attr("selected", "selected");
                $("#email").attr("placeholder", email);
                //$("#password").attr("placeholder", password);
                $("#balance_spam").text(balance);
                $("#limit_withdraw_text").text("limite max : " + balance);
                $("#quantity").prop("max", balance);
                $("#balance_spam").fadeIn("slow");
            }
        }
    }
    http.send(null);
}


//funzione chiamata quando viene premuto il tasto "modifica"
function toggleEdit(){
    if($("#confirm").prop("disabled")){
        $("#confirm").prop("disabled", false);
        $("#new_password").prop("disabled", false);
        $("#password").prop("disabled", false);
        $("#country").prop("disabled", false);
        $("#address").prop("disabled", false);
    }
    else{
        $("#confirm").prop("disabled", true);
        $("#new_password").prop("disabled", true);
        $("#password").prop("disabled", true);
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
    toggleDepositPopup();
}

//funzione chiamata dal submit del popip withdraw
function confirmWithdraw(){
    var wallet;
    var quantity;

    wallet = $("#wallet_address").val();
    quantity = $("#quantity").val();
    console.log(wallet + ", " + quantity);

    var params = new reqParams("");
    params.addParams("wallets", wallet)
          .addParams("quantity", quantity);

    var http = sendRequest("withdraw.jsp");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            //Initialize the page
            if(http.responseText == "1"){
                alert("Devi prima loggarti per accedere a questa sezione!");
                location.replace("home.html");
                return;
            }

            //Check for generic error
            else if(http.responseText == "2"){
                alert("Si ?? verificato un errore col server");
                return;
            }
            else{
                console.log("withdraw complete");
            }
        }
    }
    http.send(params.getParams);
    toggleWithdrawPopup();
    loadLogs();
}

//funzione chiamata al caricamento della pagina
function loadProfile(){
    resizeWindow();
    loadProfileData();
}