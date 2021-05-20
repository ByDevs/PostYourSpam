function sendRequest(url) {

    var http = new XMLHttpRequest();
    
    http.open("POST", url, true);
    
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    return http;
}

class reqParams{

    parametri = ""

    constructor(parametri) { this.parametri = parametri;}

    get getParams() { return this.parametri; }

    addParams(key,value) { 

        if(this.parametri == "")
            this.parametri += key+ "="+value;

        this.parametri += "&" + key+ "=" + value;
        return this;
    }

}