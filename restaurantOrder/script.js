
function validate() {
	var name1 = document.getElementById("studentName").value;
	var age1 = document.getElementById("studentAge").value;
	var id1 = document.getElementById("studentId").value;
	
	if(name1==""||age1==""||id1=="") {
		alert("Please fill out all fields");
		return false;
	}
	
	if(!isNaN(name1)) {
		alert("Please enter a valid name");
		return false;
	}
	
	if(isNaN(age1)) {
		alert("Please enter a valid age");
		return false;
	}

}

function valAccount() {
	var name1 = document.getElementById("accName").value;
	var type1 = document.getElementById("accType").value;
	var id1 = document.getElementById("accId").value;
	
	if(name1==""||type1==""||id1=="") {
		alert("Please fill out all fields");
		return false;
	}
	
	if(isNaN(id1)) {
		alert("Please enter a valid ID number");
		return false;
	}
	
	if(!isNaN(name1)) {
		alert("Please enter a valid name");
		return false;
	}
	
	if(!isNaN(type1)) {
		alert("Please enter a valid type");
		return false;
	}
}