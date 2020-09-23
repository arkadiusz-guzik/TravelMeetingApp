let wrap = document.getElementById("user-menu");
	
wrap.addEventListener('mouseenter', function(event){
	document.getElementById("header").style.height="220px";
	document.getElementById("container").style.minHeight="647px";
	document.getElementById("header").style.transition = "0.3s";
	document.getElementById("container").style.transition="0.3s";
})

wrap.addEventListener('mouseleave', function(event){
	document.getElementById("header").style.height="120px";
	document.getElementById("container").style.minHeight="747px";
})