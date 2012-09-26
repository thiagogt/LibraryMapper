var idBloco = 0;
var actualX=0,actualY=0;
var escala = 1;
var velocidade = 0.01;
var porcentagemMinima = 0.1;
var porcentagemMaxima = 1.1;
$(function(){
	$(".createButton").click( function() {
		
		$("#mapa").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
		actualX=$("#bloco-"+idBloco).width();
		actualY=$("#bloco-"+idBloco).height();
		$("#bloco-"+idBloco).draggable({ cursor: "crosshair" },
				{drag: function(event, ui) {
			        // Show the current dragged position of image
			        var currentPos = $(this).position();
			        $(".label").text("Position: \nLeft: " + currentPos.left + "\nTop: " + currentPos.top);
			        actualY = $(this).height();
					actualX = $(this).width();
					
					if(currentPos.left == 100){
						//cria o codigo dentro do canvas.....mas tava dando delay e pau
					}
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
	  
	   $('#mapa').bind('mousewheel', function(event,delta){
		   
		   var div = document.getElementById('mapa');
		   var property = getTransformProperty(div);
		   if (delta > 0) {
		   	 $(".label").text("Position: just whell up"); 
		   	if (property) {
		   	    
		   		escala =getEscala(-velocidade);
		   		var retorno = getEscala(-velocidade);
		   		
   	            div.style[property] = 'scale('+escala+','+escala+')';
   	    
   		}
		   
		   } else {
		   	 $(".label").text("Position: just whell down");
		   	if (property) {
		   		
		   	    		escala =getEscala(velocidade);
		   	            div.style[property] = 'scale('+escala+','+escala+')';
		   	    
		   		}
		   	 
		   	
		   	 
		   	}
	   });
});
function getEscala(elemento){
	var retorno = escala+elemento;
	$(".label").text("Position: just whell up: "+retorno);
	if(elemento < 0){
		$(".label").text("Position: just whell up: "+retorno +"no if");
		if(retorno <= porcentagemMinima.valueOf())
			retorno = porcentagemMinima;
		
	}
	else{
		$(".label").text("Position: just whell up: "+retorno +"no else");
		if(retorno >= porcentagemMaxima.valueOf())
			retorno = porcentagemMaxima;
	}
}
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
