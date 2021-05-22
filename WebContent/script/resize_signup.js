function billboardResizeOnLoad(){
    let size = window.outerWidth;

    if(size <= screen.width/100*55){
        $("#billb").css("display","none");
        $("#mainbox").css("width","100%");
    }
    window.addEventListener("resize", function() {

        let size = window.outerWidth;
     
        if(size <= screen.width/100*55){
            $("#billb").css("display","none");
            $("#mainbox").css("width","100%");
        }
        else{
            $("#billb").css("display","initial");
            $("#mainbox").css("width","50%");
        }
    });
}