 function ChangeColor(tableRow, highLight)
    {
    if (highLight)
    {
      tableRow.style.backgroundColor = '#dcfac9';
    }
    else
    {
      tableRow.style.backgroundColor = 'white';
    }
	
}
function goToTheBook(element){
	
	document.getElementById('selectForm:hiddenInput').value = JSON.stringify(element);
	document.getElementById('selectForm:hiddenButton').click();
	
	
}
