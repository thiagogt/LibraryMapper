var idBloco = 0;
var actualX=0,actualY=0;
var escala = 1;
var velocidade = 0.01;
var VALOR_LEFT_MAXIMO_DO_GRID = 1160;
var VALOR_TOP_MAXIMO_DO_GRID = 560;
var VALOR_DE_CADA_QUADRADO = 10;
var allMapItens = [];

blocoNumbers =0;
qrCodesNumbers = 0;

$(document).ready(function(){
	
	$(".bloco").draggable({
        helper: 'clone',scroll:false,
        cursor: 'move',
        tolerance: 'fit',
        revert: "invalid"
        
    });
	$(".qrCode").draggable({
        helper: 'clone',scroll:false,
        cursor: 'move',
        tolerance: 'fit',
        revert: "invalid"
        
    });
//	$(".createBackgroundButton").click( function() {
//		
//		$(".menu").append( "<div class='bloco' id='bloco-"+idBloco+"'></div>");
//		actualX=$("#bloco-"+idBloco).width();
//		actualY=$("#bloco-"+idBloco).height();
//		
		
		
		$("#DrawArea").droppable({
			
            drop: function (e, ui) {
            	
                if ($(ui.draggable)[0].id == "") {
                	x = ui.helper.clone();
                                      
                    if($(x).attr("id") == undefined){
                		$(x).attr("id","Map"+idBloco);
                		idBloco++;
                	}
                    
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

		$("#SaveButton").click( function(){
						
			putBlocoOnQuery();
			putQrCodeOnQuery();
			printAllMapItens();
			
			sendQueryToBean();
			
		});
		
});
function sendQueryToBean(){
	document.getElementById('MapForm:hiddenInput').value = JSON.stringify(allMapItens);
}

function printAllMapItens(){

	for ( var i=0;i<idBloco ;i++) {
	alert(	"Object.id: "+allMapItens[i].id+"\n"+
			"type: "+allMapItens[i].type+"\n"+
			"top: "+allMapItens[i].top+"\n"+
			"left: "+allMapItens[i].left+"\n"+
			"height: "+allMapItens[i].height+"\n"+
			"width: "+allMapItens[i].width+"\n");
	
	}
}
function putBlocoOnQuery(){
	var mapObject;
	
	
	if($(".bloco").length-1 > blocoNumbers){
	for ( var i=blocoNumbers;i<$(".bloco").length-1 ;i++) {
			
			mapObject = new Object();
			var tempType = $(".bloco").eq(i).attr("class").split(' ');
			mapObject.type = tempType[0]; 
			mapObject.id = $(".bloco").eq(i).attr("id");
			mapObject.top = $(".bloco").eq(i).position().top;
			mapObject.left = $(".bloco").eq(i).position().left;
			mapObject.height = $(".bloco").eq(i).height();
			mapObject.width = $(".bloco").eq(i).width();
			blocoNumbers++;
			
			allMapItens.push(mapObject);
		}
	}
	
}
	
function putQrCodeOnQuery(){
	var mapObject;
	if($(".qrCode").length -1 > qrCodesNumbers){
		for ( var i=qrCodesNumbers;i<$(".qrCode").length -1 ;i++) {
			mapObject = new Object();
			mapObject.id = $(".qrCode").eq(i).attr("id");
			var tempType = $(".qrCode").eq(i).attr("class").split(' ');
			mapObject.type = tempType[0]; 
			
			mapObject.top = $(".qrCode").eq(i).position().top;
			mapObject.left = $(".qrCode").eq(i).position().left;
			mapObject.height = $(".qrCode").eq(i).height();
			mapObject.width = $(".qrCode").eq(i).width();
			qrCodesNumbers++;
			allMapItens.push(mapObject);
		}
	}
	
}
//});


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
