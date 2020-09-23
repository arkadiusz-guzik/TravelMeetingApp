var audiences;
var idMeetingAudience=0;
var idMeetingSeat=0;
var idAudienceSeat=0;

let currentMeeting = {element: null};
let currentAudience = {element: null};
let currentSeat = {element: null};

function selectElement(currentElement, newElement){
	if(currentElement.element != null){
		currentElement.element.style.removeProperty("background-color");
	}
	currentElement.element = newElement;
	newElement.style.backgroundColor = "rgba(0,0,0,.075)";
}

function clearElement(element){
	
	if(element.element != null){
		element.element.style.removeProperty("background-color");
	}
}

function setAudiences(meetingId, element){
	
	selectElement(currentMeeting, element);
	clearElement(currentAudience);
	clearElement(currentSeat);
	
	document.getElementsByClassName("table table-bordered table-hover hide")[idMeetingSeat].getElementsByClassName("tbody-seat")[idAudienceSeat].style.display="none";
	
	document.getElementsByClassName("tbody-audience")[idMeetingAudience].style.display ="none";
	document.getElementsByClassName("tbody-audience")[meetingId].style.display ="table-row-group";
	idMeetingAudience = meetingId;
	
	idAudienceSeat=0;
}


window.onload = function load(){
	document.getElementsByClassName("table table-bordered table-hover hide")[idMeetingSeat].style.display="table";
}

function setSeats(meetingId, audienceId, element){
	
	selectElement(currentAudience, element);
	clearElement(currentSeat);
	
	document.getElementsByClassName("table table-bordered table-hover hide")[idMeetingSeat].style.display="none";
	document.getElementsByClassName("table table-bordered table-hover hide")[meetingId].style.display="table";
	idMeetingSeat = meetingId;
	
	document.getElementsByClassName("table table-bordered table-hover hide")[idMeetingSeat].getElementsByClassName("tbody-seat")[idAudienceSeat].style.display="none";
	document.getElementsByClassName("table table-bordered table-hover hide")[meetingId].getElementsByClassName("tbody-seat")[audienceId].style.display="table-row-group";
	idAudienceSeat = audienceId;
}

function setSeatElement(element){
	selectElement(currentSeat, element);
}

function addMeeting(){
	window.location.href='/repertoire/manage/meeting/add';
}




