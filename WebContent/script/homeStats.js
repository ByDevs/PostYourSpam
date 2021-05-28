function loadHomeStats(){
    var http = sendRequest("gethomestats.jsp");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            //Initialize the page
            if(http.responseText == "1"){
                return;
            }

            //Check for generic error
            else if(http.responseText == "2"){
                alert("Si è verificato un errore col server");
                return;
            }
            else{
                console.log("ok");

                var response = http.responseText;
                var countUser = response.split("|")[0];
                var countPost = response.split("|")[1];
                var sumBalance = response.split("|")[2];

                $("#users_number").text(countUser);
                $("#posts_number").text(countPost);
                $("#balance_sum").text(sumBalance);
            }
        }
    }
    http.send(null);
}