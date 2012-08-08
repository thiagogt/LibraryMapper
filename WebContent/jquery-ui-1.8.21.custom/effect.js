var idBloco = 0;
var actualX,actualY;
$(function(){
	$(".createButton").click( function() {
		
		$(".canvas").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		
		$("#bloco-"+idBloco).draggable({ containment: [100, 100, 700, 400] , cursor: "crosshair" },
				{drag: function(event, ui) {
			        // Show the current dragged position of image
			        var currentPos = $(this).position();
			        $(".label").text("Position: \nLeft: " + currentPos.left + "\nTop: " + currentPos.top);
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