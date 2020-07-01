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

$("#compare-text").click(function(e){
var insertData = {
        'a': $('#left').val() ,
        'b': $('#right').val() ,
    };
  $.ajax({
  	    type: 'POST',
  	    headers: {
          	        'Content-Type': 'application/json'
          	    },
  	    url: "/compare",
  	    data: JSON.stringify(insertData),
  	    success: function(response) {
            $('#compare-res').removeClass("hide");
            if(response.equal != "No Differences") {
            var i;
                var ins = response.insertIndex;
                var del = response.deleteIndex;
                var eq = response.deleteIndex;
                for (i = 0; i < response.result.length; i++) {
                var text ='';
                    if(ins.includes(i)) {
                       text += ("<span class='green'>"+response.result.charAt(i)+"</span>");
                    }
                    else if(del.includes(i)) {
                        text += ("<span class='red'>"+response.result.charAt(i)+"</span>");
                    }
                    else {
                         text += ("<span class=''>"+response.result.charAt(i)+"</span>");
                    }
                    $("#compare-result").append(text);
                    }

            }else {
                $("#compare-result").text(response.equal);
            }
            $("#compare-text-reset").removeClass("hide");
            $("#compare-text").addClass("hide");
         },
        error: function(response) {
            console.log(response);
        }
  	});
});

$("#compare-text-reset").click(function(e){
      $('#left').val("");
       $('#right').val("");
       $("#compare-result").text("");
       $('#compare-res').addClass("hide");
       $("#compare-text-reset").addClass("hide")
       $("#compare-text").removeClass("hide");
});
