var logs = [];
var maxLogsPerPage = 5;
var nlogs;

function loadLogs(){
    getLogs();
}

function getLogs(){
    var http = sendRequest("getlogs.jsp");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            if(http.responseText == "1"){
                    //case no posts
                    alert("no post");
                    return;
            }
            else{
                var response = JSON.parse(http.responseText);
                if(!isNaN(response["nlog"])){
                    nlogs = Number(response["nlog"]);
                    for(i=0;i<nlogs;i++){
                        var log = response[""+ i];
                        logs.push(log);
                    }
                    getPage(1);
                    var nIndexes = Math.ceil(nlogs/maxLogsPerPage);
                    
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
    http.send(null);
}

function addLog(log){
    let amount = log.split("|")[0];
    let text = log.split("|")[1];
    let date = log.split("|")[2];

    let newLog = '\
    <div class="post_box">\
        <div class="row">\
            <div class="col center_text table_text">\
                ' + amount + ' SPAM\
            </div>\
            <div class="col-5 table_text">\
                <div>\
                    <p>' + text +' </p>\
                </div>\
            </div>\
            <div class="col center_text table_text">\
                ' + date + '\
            </div>\
        </div>\
    </div>\
    ';

    $("#logs").append(newLog);
}

function getPage(npage){
    let firstLog = (npage - 1) * maxLogsPerPage;
    $("#logs").empty();
    $(".page-item.active").removeClass("active");
    $("#" + npage).addClass("active");

    for(i = firstLog; i < Math.min(firstLog + maxLogsPerPage, logs.length) ; i++){
        addLog(logs[i]);
    }
}