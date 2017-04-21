var icon_valid = "/images/check.png";
var icon_invalid = "/images/cross.png";

/*******	OBJECTS		***********/

var username_error;
var username_icon;
var passwd_error;
var passwd_icon;
var confirm_error;
var confirm_icon;

var confirm_error_parrent;


var validate = function() {
	$.post("/api/validation/user", user, result, "json");
}


var invalid = function(msg, text ,icon) {
	
	msg.text(text);
	msg.show();
	icon.attr("src", icon_invalid);
	console.log("Invalid triggered");
} 


var valid = function(msg, icon) {
	msg.hide();
	icon.attr("src", icon_valid);
	console.log("Valid triggered");
}


var result = function(data, textStatus, jqXHR) {
				
	if(jqXHR.status == 200){
			
		console.log(data, data === null);	
		data.username? invalid(username_error, data.username ,username_icon) : valid(username_error, username_icon);
		
		data.passwd? invalid(passwd_error, data.passwd ,passwd_icon) : valid(passwd_error, passwd_icon);
		
		data.confirmPasswd? invalid(confirm_error, data.confirmPasswd ,confirm_icon) : valid(confirm_error, confirm_icon);
		
	}
	else{
		alert("AJAX FAIL");
		console.log("AJAX FAIL", jqXHR);
	}
	
}


var user = {
	username : "",
	passwd : "",
	confirmPasswd : ""
};


var username_handler = function() {
	
	user.username = $("#username").val();
	username_error.show(); 
	username_icon.show();	 
	validate();	  
}

var passwd_handler = function() {

	user.passwd = $("#passwd").val();
	passwd_error.show();
	passwd_icon.show();	 
	validate();	
}

var confirm_handler = function() {
	//confirm input
	user.confirmPasswd = $("#confirm").val();
	//error div
	if( Object.keys(confirm_error).length === 0){
		confirm_error_parrent.html('<div class="fieldError" id="confirm_error"></div>');
		confirm_error = $( "#confirm_error" ); 
	}
	confirm_error.show();
	confirm_icon.show(); 
	validate();
}

$(document).ready(function(){
		
	username_error = $( "#username_error" ); 
	username_icon =	$( "#username_icon" );
	passwd_error = $( "#passwd_error" );
	passwd_icon = $( "#passwd_icon" );
	confirm_error = $( "#confirm_error" ); 
	confirm_icon = $( "#confirm_icon" );
	
	confirm_error_parrent = $( "#confirm_error_parrent" );
	
	username_error.hide();
	username_icon.hide();
	passwd_error.hide();
	passwd_icon.hide();
	confirm_error.hide();
	confirm_icon.hide();
	
		
	$( "#username" ).on("input", username_handler);
	
	$( "#passwd" ).on("input", passwd_handler);
	
	$( "#confirm" ).on("input", confirm_handler);
});