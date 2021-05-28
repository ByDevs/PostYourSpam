var navbar_html = '\
    <nav class="navbar navbar-expand-lg navbar-light bg-light op"> \
        <a href="home.html"><img class="navbar-brand" src="images/page-logo.png" width="55px" height="55px"></a> \
        <div class="d-flex order-lg-1 ml-auto pr-2" id="mini_user_stats"> \
            <button class="btn btn-primary" onclick="togglePopup()" id="login_button">Login</button> \
            <a style="margin-left: 10px;" class="btn btn-primary" href="signup.html" id="login_button">Registrati</a> \
        </div> \
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> \
            <span class="navbar-toggler-icon"></span> \
        </button> \
        <div class="collapse navbar-collapse" id="navbarSupportedContent"> \
            <ul class="navbar-nav mr-auto"> \
                <li class="nav-item active"> \
                    <button onclick="location.href=' + "'posts.html'" + '" class="navButtons nav-item">SPAM</button> \
                </li> \
                <li class="nav-item active"> \
                    <button onclick="location.href=' + "'newPost.html'" + '"class="navButtons nav-item">CREA SPAM</button> \
                </li> \
                <li class="nav-item active"> \
                    <button onclick="location.href=' + "'info.html'" + '"class="navButtons nav-item">INFO</button> \
                </li> \
                <li class="nav-item active"> \
                    <button onclick="location.href=' + "'daily.html'" + '" class="navButtons nav-item">DAILY REWARDS</button> \
                </li> \
            </ul> \
        </div> \
    </nav> \
';

var footer_html = ' \
    <footer> \
        <div class="social"> \
            <a href="https://instagram.com"><i class="bi bi-instagram"></i></a> \
            <a href="https://github.com"><i class="bi bi-github"></i></a> \
            <a href="https://twitter.com"><i class="bi bi-twitter"></i></a> \
            <a href="https://facebook.com"><i class="bi bi-facebook"></i></a> \
        </div> \
        <ul class="list-inline"> \
            <li class="list-inline-item"><a href="home.html">Home</a></li> \
            <li class="list-inline-item"><a href="#">Info</a></li> \
            <li class="list-inline-item"><a href="faq.html">FAQ</a></li> \
        </ul> \
        <p class="copyright">Alessio Tranzocchi&emsp;&emsp;&emsp;&emsp;Matteo Catalano</p> \
    </footer> \
';

var login_popup_html = ' \
    <h2 style="font-family: ' + "Roboto" +', sans-serif; font-size: calc(20px + 1vw ); font-weight: bold; color:#1d1d1d">Login</h2> \
    <div> \
        <form action="" onsubmit="pressLogin(); return false"> \
            <div class="form-group"> \
                <label for="login-email">Email</label> \
                <input type="email" class="form-control" id="login-email" required> \
            </div> \
            <div class="form-group"> \
                <label for="login-password">Password</label> \
                <input type="password" class="form-control" id="login-password" required> \
            </div> \
            <div style="float: right"> \
                <button class="btn btn-primary" href="#" onclick="togglePopup(); return false;">Annulla</button> \
                <button type="submit" class="btn btn-primary">Accedi</button> \
            </div> \
        </form> \
    </div> \
';

function loadNavbar(){
    $(".blur_container").prepend(navbar_html);
}

function loadFooter(){
    $(".footer-basic").empty();
    $(".footer-basic").append(footer_html);
}

function loadLoginPopup(){
    $("#popup").empty();
    $("#popup").append(login_popup_html);
}

function loadBody(){
    loadNavbar();
    loadFooter();
    loadLoginPopup();
}
