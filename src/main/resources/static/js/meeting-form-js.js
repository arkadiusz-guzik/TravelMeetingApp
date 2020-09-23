let inputFile = document.getElementById('fimg');
let fileNameField = document.getElementById('file-name');

inputFile.addEventListener('change', function(event){
	let uploadedFileName = event.target.files[0].name;
	fileNameField.textContent = uploadedFileName;
})
		
let textField = document.getElementById("fdesc");
let numberField = document.getElementById("length");
textField.addEventListener('keydown', function(event){
	let amount = event.target.value.length;
	numberField.textContent = 200-amount;
})		