var idBloco = 0;
var actualX=0,actualY=0;
var escala = 1;
var velocidade = 0.01;
var VALOR_LEFT_MAXIMO_DO_GRID = 1160;
var VALOR_TOP_MAXIMO_DO_GRID = 560;
var VALOR_DE_CADA_QUADRADO = 10;

$(function(){
	$(".createBackgroundButton").click( function() {
		
		$(".menu").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		actualX=$("#bloco-"+idBloco).width();
		actualY=$("#bloco-"+idBloco).height();
		
		
		$("#bloco-"+idBloco).draggable({
	        helper: 'clone',scroll:false,
	        cursor: 'move',
	        tolerance: 'fit',
	        revert: "invalid"
	        
	    });
		$("#DrawArea").droppable({
			
            drop: function (e, ui) {

                if ($(ui.draggable)[0].id != "") {
                    x = ui.helper.clone();
                    
                    var leftPos = parseInt(ui.offset.left - $(this).offset().left) - parseInt(ui.offset.left - $(this).offset().left)%10;
                    var topPos =  parseInt(ui.offset.top - $(this).offset().top) -  parseInt(ui.offset.top - $(this).offset().top)%10;
                    if(leftPos < 0)
                    	leftPos +=VALOR_DE_CADA_QUADRADO;
                    if(leftPos > VALOR_LEFT_MAXIMO_DO_GRID)
                    	leftPos -=VALOR_DE_CADA_QUADRADO;
                    if(topPos > VALOR_TOP_MAXIMO_DO_GRID)
                    	topPos -=VALOR_DE_CADA_QUADRADO;
                    $(x).css("top",topPos);
                    $(x).css("left",leftPos);
                      
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
	$("#bloco-0").click(function(){
        alert("#bloco-0 clicado");
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
