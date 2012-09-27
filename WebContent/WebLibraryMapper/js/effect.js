var idBloco = 0;
var actualX=0,actualY=0;
var escala = 1;
var velocidade = 0.01;
$(function(){
	$(".createButton").click( function() {
		
		$("body").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		actualX=$("#bloco-"+idBloco).width();
		actualY=$("#bloco-"+idBloco).height();
		
		
		$("#bloco-"+idBloco).draggable({
	        helper: 'clone',
	        cursor: 'move',
	        tolerance: 'fit',
	        revert: "invalid"
	        
	    });
		$("#DrawArea").droppable({

            drop: function (e, ui) {

                if ($(ui.draggable)[0].id != "") {
                    x = ui.helper.clone();
                ui.helper.remove();
                x.draggable({
                    helper: 'original',
                    containment: '#DrawArea',
                    tolerance: 'fit',
                    grid: [ 10,10 ],
                    drag: function(event, ui) {
    			        // Show the current dragged position of image
    			        var currentPos = $(this).position();
    			        $(".label").text("Position: \nLeft: " + currentPos.left + "\nTop: " + currentPos.top);
    			        actualY = $(this).height();
    					actualX = $(this).width();
    					
    					if(currentPos.left == 100){
    						//cria o codigo dentro do canvas.....mas tava dando delay e pau
    					}
    			    }
                });
                x.resizable({
                	containment: "#DrawArea", 
                	scroll: false,
                	grid: [ 10,10 ],
                	resize: function(event, ui) { 
						$("#status").text( actualX + ", "+actualY);
						actualY = $(this).height();
						actualX = $(this).width();
				
					}
                });
                x.appendTo('#DrawArea');
            }

            }
        });


		idBloco++;
	});
	
});


// Zoom in and out method by scrolling
//jQuery(document).ready(function(){
//	
//	   $(document).mousemove(function(e){
//		   
//		   
//	      $('#status').html(actualX +', '+ actualY);
//	   }); 
//	  
//	   $('#mapa').bind('mousewheel', function(event,delta){
//		   
//		   var div = document.getElementById('mapa');
//		   var property = getTransformProperty(div);
//		   if (delta > 0) {
//		   	 $(".label").text("Position: just whell up"); 
//		   	if (property) {
//		   	    
//   	    		escala =escala-velocidade;
//   	            div.style[property] = 'scale('+escala+','+escala+')';
//   	    
//   		}
//		   
//		   } else {
//		   	 $(".label").text("Position: just whell down");
//		   	if (property) {
//		   	    
//		   	    		escala =escala+velocidade;
//		   	            div.style[property] = 'scale('+escala+','+escala+')';
//		   	    
//		   		}
//		   	 
//		   	
//		   	 
//		   	}
//	   });
//});

function getTransformProperty(element) {
    // Note that in some versions of IE9 it is critical that
    // msTransform appear in this list before MozTransform
    var properties = [
        'transform',
        'WebkitTransform',
        'msTransform',
        'MozTransform',
        'OTransform'
    ];
    var p;
    while (p = properties.shift()) {
        if (typeof element.style[p] != 'undefined') {
            return p;
        }
    }
    return false;
}
