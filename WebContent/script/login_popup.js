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
    $('body').css("overflow","hidden");
}

function enableScroll() {
    $('body').css("overflow","initial");
}


//#####LOGIN-LOGOUT HANDLERS#####
function pressLogin() {

    var email = document.getElementById("login-email").value;
    var password = document.getElementById("login-password").value;
    var params = new reqParams("");
    params.addParams("email",email).addParams("password",password);
    loginRequest("login.jsp", params.getParams);

}

function loginRequest(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            if(http.responseText == "true"){
                togglePopup();
                showUserStats();
            }
            else if(http.responseText == "false"){
                alert("I dati non sono corretti");
            }
        }
    }
    http.send(params);
    console.log(http.url);
}

function logout() {
    var http = sendRequest("logout.jsp");
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            showUserStats();
        }
    }
    http.send(null);
}


/*Check if the user is logged in the session, and updates the navbar, if not reload the original buttons*/
function showUserStats(){

    var http = sendRequest("getusrstat.jsp");
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            if(http.responseText == "notLogged"){
                $('#mini_user_stats').empty();

                var original_div = 
                    "<button class='btn btn-primary' onclick='togglePopup()' id='login_button'>Login</button> \
                    <a  style='margin-left: 10px;' class='btn btn-primary' href='signup.html' id='login_button'>Registrati</a>'";
                
                $('#mini_user_stats').append(original_div);
            }
            else
            {
                res = JSON.parse(http.responseText);

                username = res["username"];
                balance = res["balance"];
                
                $('#mini_user_stats').empty();
                var substitute_div = 
                "<div style='display: inline-block; padding-right:25px; vertical-align:middle;'> \
	                <div >\
	                    <div class='dropdown'>\
	                        <img src='images/user_icon.png' width='22' height='22'>\
	                        <a href='#' class='dropdown-toggle' style='color:#ffffff' id='navbarDropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" + username +"</a>\
	                        <div style='min-width: 50px !important' class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>\
	                            <a class='dropdown-item' href='#'>Profilo</a>\
	                            <a class='dropdown-item' onclick='logout()' href='#'>Logout</a>\
	                        </div>\
	                    </div> \
	                </div>\
	                    <div>\
	                        <img src='images/spam-logo.png' width='22' height='22'>\
	                        <a style='color:#ffffff' href='#'>" + balance + "</a>\
	                    </div>\
	                </div>";
                $('#mini_user_stats').append(substitute_div);
            }
        }
    }
    http.send(null);
}