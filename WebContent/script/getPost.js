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
                return;
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