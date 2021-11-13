$( document ).ready(function() {

 $(".tiles").click(function(){
     var idArray = [];
         $('.tiles').each(function () {
             idArray.push(this.id);
         });
    var ids= ["qr-main","qr-main1","qr-main2","qr-main3","qr-main4"];
    var headings= ["QR Code Generator","Text Difference Checker","PDF to Text Converter","Text Matcher","Gold and Silver Rates"];
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

 $("#download-content").click(function(e){
     var file = window.btoa($("#result-pdf").val());
     $(this).attr("href","data:application/octet-stream;charset=utf-8;base64,"+file);
     $(this).attr("download",$(".custom-file-label").text().replace("Uploaded File : ","Converted-").replace("pdf","txt"));
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

$("#qr5").click(function(e){

  $.ajax({
  	    type: 'GET',
  	    headers: {
  	        'Content-Type': 'application/json'
  	    },
  	    url: "/rates",
  	    data: "",
  	    success: function(response) {
  	    $("#b-rate-5").text(response[1]);
  	    $("#g-rate-5").text(response[0]);
  	    $("#s-rate-5").text(response[2]);
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

$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html("Uploaded File : "+fileName);
  $("#pdf-parse").removeClass("hide");
});


$("#pdf-parse").click(function(e){
var $btn = $(this).button('loading')
var fd = new FormData();

var file_data = $('input[type="file"]')[0].files; // for multiple files
for(var i = 0;i<file_data.length;i++){
    fd.append("file", file_data[i]);
}
var other_data = $('form').serializeArray();
$.each(other_data,function(key,input){
    fd.append(input.name,input.value);
});

  $.ajax({
  	    type: 'POST',
  	    url: "/pdf-text",
        contentType: false,
        processData: false,
        data: fd,
  	    success: function(response) {
//            console.log(response);

        $("#result-pdf").append(response);
        $("#result-pdf").removeClass("hide");
        $("#download-content").removeClass("hide");
            $btn.button('reset')
         },
        error: function(response) {
            console.log(response);
        }
  	});
});

$("#match-text").click(function(e){
var insertData = {
        'a': $('#match-left').val() ,
        'b': $('#match-right').val() ,
    };
  $.ajax({
  	    type: 'POST',
  	    headers: {
          	        'Content-Type': 'application/json'
          	    },
  	    url: "/match",
  	    data: JSON.stringify(insertData),
  	    success: function(response) {
  	    console.log(response);
  	     $('#match-res').removeClass("hide");
                    $("#match-text-reset").removeClass("hide");
                    $("#match-text").addClass("hide");
  	    if(response != 0){
            $('#match-result').css("width", response+"%");
            $('#match-result').text(response+"%");
        }else {
            $('#match-result').css("color", "black");
            $('#match-result').css("width", response+"%");
            $('#match-result').text(response+"%");
         }
         },
        error: function(response) {
            console.log(response);
        }
  	});
});
$("#match-text-reset").click(function(e){
      $('#match-left').val("");
       $('#match-right').val("");
       $('#match-res').addClass("hide");
       $("#match-text-reset").addClass("hide")
       $("#match-text").removeClass("hide");
});

});
