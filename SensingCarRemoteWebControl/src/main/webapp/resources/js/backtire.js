function backtire(command, direction, speed) {
	$("#backtireSpeed").val(speed);
	$("#backtireDirection").val(direction);
//	console.log(direction);
//	console.log(speed);
	var json = {"command":command, "direction":direction,"speed":speed };
	
	$.ajax({
		url:"http://" + location.host + "/SensingCarRemoteWebControl/backtire",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#backtireStatus").html("direction: " + data.direction + " / speed: " + data.speed);								
			}
		}
	});	
}