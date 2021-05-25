function loadPost(){

	let postID = -1;
    var http = sendRequest("getpost.jsp");
    var params = new reqParams("");
    

	if(window.location.hash != ""){
        postID = Number((window.location.hash).substring(1));
    }
	else{
		alert("Si è verificato un errore");
		return;
	}


    params.addParams("post_id",postID);

    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

            //Initialize the page
            //Check if the post exists
            if(http.responseText == "1")
            {
                $(".post_box").empty();
                $("#id_post").html("Error 404 : Non abbiamo trovato il tuo post :(");
            }
            //Check for generic error
            else if(http.responseText == "2"){
                alert("Si è verificato un errore col server");
            }
            else{
                
                //Load the post if everything is ok
                
                var post = http.responseText;
                
                let body = post.split("|")[0];
                let titolo = post.split("|")[1];
                let sottotitolo = post.split("|")[2];
                let reward = post.split("|")[3];
                let user = post.split("|")[4];
                let data = post.split("|")[5];
                let views = post.split("|")[6];
                let link = post.split("|")[7];


                //Setting up page
                $("#id_post").html("Post #"+postID);
                $("#body_post").html(body);
                $("#titolo_post").html(titolo);
                $("#sintesi_post").html(sottotitolo);
                $("#reward_post").html(reward);
                $("#user_post").html(user);
                $("#views_post").html(views);
                $("#data_post").html(data);
                $("#link_post").html(link);
            
            }
        }
    }
    http.send(params.getParams);
}

function getReward(){


    /* TRY MAKE A TRANSACTION */
    if(isLogged())
    {
        let postid = "";
        postid = document.getElementById("id_post").innerText;
        var params = new reqParams("");
        params.addParams("post_id",postid.substring(6));
        rewardRequest("getreward.jsp",params.getParams);
    }
    else
        alert("Devi prima effettuare il login!");
        
}

function rewardRequest(url,params){

    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

            if(http.responseText == "1"){
                alert("Il creatore non ha abbastanza saldo");
            }
            else if(http.responseText == "2"){
                alert("Si è verificato un errore col server");
            }
            else if(http.responseText == "3"){
                alert("ERRORE: Prova a rieffettuare l'accesso");
            }
            else if(http.responseText == "4"){
                alert("Hai già riscosso la ricompensa");
            }
            else{
                window.open(document.getElementById("link_post").innerText);
                showUserStats();
            }
        }
    }
    http.send(params);
}