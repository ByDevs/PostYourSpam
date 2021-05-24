var posts = [];
var maxPostPerPage = 4;

//#####GET POSTS HANDLERS#####
function getPosts() {
    postsRequest("getposts.jsp", null);
}

function postsRequest(url , params) {

    var http = sendRequest(url);
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            //Initialize the page

            //Check if there is atleast 1 posts available
            if(http.responseText == "1")
                return;

            //Check for generic error
            else if(http.responseText == "2"){
                alert("Si è verificato un errore col server");
                return;
            }
            else{
                var res = JSON.parse(http.responseText);
                if(!isNaN(res["npost"])){
                    console.log("getting posts");
                    var npost = Number(res["npost"]);

                    //Append all posts

                    for(i=0;i<npost;i++){
                        
                        var post = res[""+ i];
                        console.log(post);
                        posts.push(post);
                    }
                    
                    //Load the first page
                    getPage(1);

                    //Append page indexes element
                    var nIndexes = Math.trunc(npost/maxPostPerPage);
                    console.log(nIndexes);
                    
                    let nextNeighbor = "#1";
                    for(x = 2; x <= nIndexes;x++){

                        let pageIndex = 
                        '<li class="page-item" id="'+x+'">\
                            <button class="page-link" onclick="getPage('+x+');">'+x+'</button>\
                        </li>';

                        $(nextNeighbor).after(pageIndex);
                        nextNeighbor = "#"+x;
                    }
                }
            }
        }
    }
    http.send(params);
}

function addPost(post){
    
    let postID = post.split("|")[0];
    let titolo = post.split("|")[1];
    let sottotitolo = post.split("|")[2];
    let reward = post.split("|")[3];
    let autore = post.split("|")[4];
    let data = post.split("|")[5];
    let views = post.split("|")[6];

    let newPost = 
        '<div class="post_box"">\
            <div class="row">\
                <div class="col-5 space_title no_hidden_resize"> \
                    <a class="title_post" href="post.html#'+postID+'" >'+titolo+'</a>\
                    <div class="subtitle_post">'+sottotitolo+'</div>\
                    </div>\
                    <div class="col hidden_resize numbers_post"> '+reward+' <div class="subtitle_number_post">SPAM</div> </div>\
                        <div class="col hidden_resize numbers_post"> '+views+' <div class="subtitle_number_post">VIEWS</div></div>\
                        <div class="col-3 hidden_resize author_post">\
                            <div style="display: inline-block; vertical-align: auto;">\
                                <img src="images/user_icon.png" width="22" height="22">\
                                ' + autore + '\
                            </div>\
                            <div class="date_post">\
                                ' + data + '\
                            </div>\
                        </div>\
                    </div>';
                    
    $("#posts_container").append(newPost);               
}

function getPage(pageIndex){
	console.log("clicked "+pageIndex);

    let firstPost = (pageIndex -1) * maxPostPerPage;

    $("#posts_container").empty();
    
    //Change active button
    $(".page-item.active").removeClass("active");
    $("#"+pageIndex).addClass("active");
    
    for(i = firstPost; i < Math.min(firstPost + maxPostPerPage, posts.length) ; i++){
        addPost(posts[i]);
    }
}