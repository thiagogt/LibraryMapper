var idBloco = 0;
var actualX=0,actualY=0;
$(function(){
	$(".createButton").click( function() {
		
		$(".body").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		actualX=$("#bloco-"+idBloco).width();
		actualY=$("#bloco-"+idBloco).height();
		$("#bloco-"+idBloco).draggable({ cursor: "crosshair" },
				{drag: function(event, ui) {
			        // Show the current dragged position of image
			        var currentPos = $(this).position();
			        $(".label").text("Position: \nLeft: " + currentPos.left + "\nTop: " + currentPos.top);
			        actualY = $(this).height();
					actualX = $(this).width();
			    }
			}).resizable({resize: function(event, ui) { 
				
					actualY = $(this).height();
					actualX = $(this).width();
			
				}
			});
		
		idBloco++;
	});
});

jQuery(document).ready(function(){
	   $(document).mousemove(function(e){
		   
		   
	      $('#status').html(actualX +', '+ actualY);
	   }); 
});