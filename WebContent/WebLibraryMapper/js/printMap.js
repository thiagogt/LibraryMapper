
function init() {
	var canvas = document.getElementById("canvas");
	if (canvas.getContext) {
		var ctx = canvas.getContext("2d");
		for ( var intI = 0; intI < 100; intI++) {
			for ( var intJ = 0; intJ < 100; intJ++) {
				if(intI == intJ){
					ctx.beginPath();
					ctx.fillStyle = "green";
					ctx.fillRect(intI*6,intJ*6,6,6);
					
					ctx.closePath();
				}
				else if( intI > intJ ){
	
					ctx.beginPath();
					ctx.fillStyle = "red";
					ctx.fillRect(intI*6,intJ*6,6,6);
					
					ctx.closePath();
				}
				else{
	
					ctx.beginPath();
					ctx.fillStyle = "yellow";
					ctx.fillRect(intI*6,intJ*6,6,6);
							
					ctx.closePath();
				}
				
			}
		}
	}
}
  