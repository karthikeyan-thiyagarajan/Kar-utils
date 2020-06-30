$( document ).ready(function() {

 $(".tiles").click(function(){
     var idArray = [];
         $('.tiles').each(function () {
             idArray.push(this.id);
         });
    var ids= ["qr-main","qr-main1"];
    var headings= ["QR Code Generator","Text Difference Checker"];
       for (var i = 0; i < idArray.length; i++) {
            if(idArray[i] === this.id){
                $("#"+ids[i]+"").removeClass("hide");
                $("#"+(i+1)+idArray[i]+"").addClass("active");
               $(".sub-heading").text(headings[i]);
            }else{
                $("#"+ids[i]+"").addClass("hide");
                 $("#"+(i+1)+idArray[i]+"").removeClass("active");
            }
       }
 });
});
$("#button-addon2").click(function(e){
var $btn = $(this).button('loading')
var name =  $('#qr-data').val() != "" ? $('#qr-data').val() : "empty";
  $.ajax({
  	    type: 'GET',
  	    headers: {
  	        'Content-Type': 'application/json'
  	    },
  	    url: "/qr/"+name,
  	    data: "",
  	    success: function(response) {
  	    document.getElementById("qr-image").src = "data:image/png;base64," + response;
        $btn.button('reset')
         },
        error: function(response) {
            console.log(response);
        }
  	});
});