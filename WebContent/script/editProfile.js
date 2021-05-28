function changeData(){
    var address = $("#address").val();
    var country = $("#country option:selected").text();
    var oldPassword = $("#password").val();
    var newPassword = $("#new_password").val();

    var params = new reqParams("");
    params.addParams("address", address)
          .addParams("country", country)
          .addParams("oldPassword", oldPassword)
          .addParams("newPassword", newPassword);
        
    editProfileRequest("editProfile.jsp", params.getParams);
    toggleEdit();
}

function editProfileRequest(url, params){
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {

        	if(http.responseText == "1"){
                alert("Errore del server");
            }
        }
    }
    http.send(params);
}