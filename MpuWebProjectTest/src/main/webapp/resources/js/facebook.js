//This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {
		// Logged into your app and Facebook.
		testAPI();
	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		document.getElementById('facebookID').innerHTML = 'Please log '
				+ 'into this app.';
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		document.getElementById('facebookID').innerHTML = 'Please log '
				+ 'into Facebook.';
	}
}

// This function is called when someone finishes with the Login
// Button. See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}
window.fbAsyncInit = function() {
	FB.init({
		appId : '1541339359250690',
		cookie : true,
		xfbml : true,
		version : 'v2.8'
	});
	FB.AppEvents.logPageView();
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI() {
	console.log('Welcome! Fetching your information.... ');
	FB.api('/me', {
		fields : 'email, name'
	}, function(response) {
		console.log('Successful login for: ' + response.name);
		console.log('Successful login for: ' + response.email);
		document.getElementById('facebookID').innerHTML =  response.name+'님이 로그인 하셨습니다.';
	});
	
	FB.Event.subscribe('auth.login',function(response){
		document.location.reload();
	})
	
	FB.getLoginStatus(function(response) {
		  if (response.status === 'connected') {
		    var accessToken = response.authResponse.accessToken;
		    
		  } 
		} );
}