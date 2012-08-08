var idBloco = 0;
$(function(){
	$(".createButton").click( function() {
		
		$(".canvas").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		
		$("#bloco-"+idBloco).draggable({ containment: [100, 100, 700, 400] , cursor: "crosshair" },
				{drag: function(event, ui) {
			        // Show the current dragged position of image
			        var currentPos = $(this).position();
			        $(".label").text("Position: \nLeft: " + currentPos.left + "\nTop: " + currentPos.top);
			    }
			}).resizable();
		
		idBloco++;
	});
});
