function pressNewPost(){

    let titolo = document.getElementById("titolo").value;
    let sottotitolo = document.getElementById("sottotitolo").value;
    let body = document.getElementById("bodyPost").value;
    let link = document.getElementById("link").value;
    let reward = document.getElementById("reward").value;

    var params = new reqParams("");
    params.addParams("titolo",titolo)
          .addParams("sottotitolo",sottotitolo)
          .addParams("bodyPost",body)
          .addParams("link",link)
          .addParams("reward",reward);
    
    newpostRequest("newpost.jsp", params.getParams);
}

function newpostRequest(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

        	if(http.responseText == "1"){
                alert("Un errore e' occorso durante lo spam");
            }
            else if(http.responseText == "2"){
                alert("Il tuo balance e' insufficente!");
            }
            else if(http.responseText == "3"){
                alert("Devi essere loggato per spammare!");
            }
            else{
                location.href = http.responseText;
            }
        
        }
    }
    http.send(params);
}