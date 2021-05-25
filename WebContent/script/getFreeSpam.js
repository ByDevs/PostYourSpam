var seconds = 86400;

function loadCounter(){
    var http = sendRequest("getfree.jsp");
        
        http.onreadystatechange = function() {
            if(http.readyState == 4 && http.status == 200) {

                if(http.responseText == "1"){
                    $("#countdown_container").html("Log-in first");
                    $("#claim_btn").css("display","none");
                    return;
                }
                else if(http.responseText == "2"){
                    alert("Si è verificato un errore col server");
                    return;
                }

                let data = JSON.parse(http.responseText); 
                if(data != null){
                    if(data["message"] == "WAIT"){
                        //Init countdown
                        seconds = Number(data["seconds"]);
                        updateCountdown();
                        setInterval(updateCountdown,1000);
                        $("#claim_btn").css("display","none");
                    }
                    else if(data["message"] == "CLAIM"){
                        $("#countdown_container").html("CLAIM");
                        $("#claim_btn").css("display","block");
                        $("#captcha_question").html(data["domanda"]);
                    }
                }
            }
        }
        //Get the time
        let params = new reqParams("");
        params.addParams("req_type","remaining_time");
        http.send(params.getParams);
}

function updateCountdown(){

    if(seconds == 0)
        loadCounter();

    let ore = Math.floor(seconds / 3600);
    let minuti = Math.floor( (seconds % 3600) / 60);
    let secondi = seconds % 60;

    var countdown = (ore < 10 ? "0" + ore : "" + ore) + ":" + (minuti < 10 ? "0" + minuti : minuti)+ ":" + (secondi < 10 ? "0" + secondi : secondi);

    $("#countdown_container").html(countdown);
    seconds--;
}

function claimReward(){

    console.log("RECLAIMED");
    var http = sendRequest("getfree.jsp");
        
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

            if(http.responseText == "1"){
                $("#countdown_container").html("Log-in first");
                $("#claim_btn").css("display","none");
            }
            else if(http.responseText == "2"){
                alert("Si è verificato un errore col server");
            }
            else if(http.responseText == "wrong"){
                alert("Il captcha non è corretto");
            }
            else{
                location.reload();
            }
        }
    }
    let params = new reqParams("");
    params.addParams("req_type","claim_reward");
    params.addParams("answer", "" + document.getElementById("answer_daily").value);
    http.send(params.getParams);

}