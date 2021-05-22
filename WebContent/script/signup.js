//#####SIGNUP HANDLERS#####
function presSignup() {

    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var cPassword = document.getElementById("confirm_password").value;
    var indirizzo = document.getElementById("indirizzo").value;
    var nazione = document.getElementById("nazione").value;

    if(password != cPassword){
        alert("Le due password inserite non coincidono! Riprovare")
        return;
    }

    var params = new reqParams("");
    params.addParams("email",email)
          .addParams("password",password)
          .addParams("username",username)
          .addParams("indirizzo",indirizzo)
          .addParams("nazione",nazione);
    
    signupRequest("signup.jsp", params.getParams);
}

function signupRequest(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

        	if(http.responseText == "1"){
                alert("email gia' esistente!");
            }
            else if(http.responseText == "2"){
                alert("username gia' esistente!");
            }
            else if(http.responseText == "3"){
                alert("Un errore e' occorso durante la registrazione");
            }
            else{
                location.href = http.responseText;
            }
        
        }
    }
    http.send(params);
}