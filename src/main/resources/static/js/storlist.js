
$('body').on('click','#cpnresultsln', function(){
 $("#cpnresultsln").hide();
 $("#cpnresldim").show();
 var formData = $("#cpnresultsfm").serializeArray();
var URL = $("#cpnresultsfm").attr("action");
$.post(URL,
formData,
function(data, textStatus, jqXHR)
{    
	if(textStatus == 'success'){ 			    
	var newcurvl = $($.parseHTML(data)).filter("#curnewval").val();
	if(newcurvl == 'end'){
		$("#cpnresultsln").hide();
		$("#cpnresldim").hide();
	}else{
		$("#cpnresldim").hide();
 	    $("#cpnresultsln").show();
	}
	$("#curval").val(newcurvl);

	$('#cpnresults').append(data);
	}else{
		$("#cpnresldim").hide();
 	    $("#cpnresultsln").show();
	}
}).fail(function(jqXHR, textStatus, errorThrown) 
{$("#cpnresldim").hide();
 $("#cpnresultsln").show();  });
}); 

$('body').on('change','input[type=radio]', function(){
	$.fn.procFiltDta();
});

$.fn.procFiltDta = function() { 

	if($("input:radio[name='subca']").is(":checked")){
		 $("#subctgry").val($('input[name = "subca"]:checked').val());
	}else{
		$("#subctgry").val('');
	}   

    $("#curval").val('0');

    var frmfildata = $("#cpnresultsfm").serializeArray();
    var URL = $("#cpnresultsfm").attr("action");
    $.post(URL,
    frmfildata,
    function(data, textStatus, jqXHR)
    {    
    	if(textStatus == 'success'){ 			    
    	var newcurvl = $($.parseHTML(data)).filter("#curnewval").val();
    	if(newcurvl == 'end' || typeof newcurvl == "undefined"){
    		$("#cpnresultsln").hide();
    		$("#cpnresldim").hide();
    	}else{
    		$("#cpnresldim").hide();
     	    $("#cpnresultsln").show();
    	}
    	$("#curval").val(newcurvl);
    	$('#cpnresults').html(data);
    	}else{
    		$("#cpnresldim").hide();
     	    $("#cpnresultsln").show();
    	}
    }).fail(function(jqXHR, textStatus, errorThrown) 
    {$("#cpnresldim").hide();
     $("#cpnresultsln").show();  });   
};
$(".nano").nanoScroller({ alwaysVisible: true, sliderMaxHeight: 80 });
function cleraprdsfltrs(){
    $('input[type=radio]').each(function (){
    	$(this).prop('checked', false);
});
}
