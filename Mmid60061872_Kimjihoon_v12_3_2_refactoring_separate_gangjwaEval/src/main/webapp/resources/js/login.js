/**
 * 
 */


$(document).ready(function() {
	$('#submitbox').hover(function() {
		$(this).animate(
			{width:'60px'},
			{duration:80, easing:'swing'}
		)
	}, function() {
		$(this).animate(
			{width:'50px'},
			{duration:80, easing:'swing'}
		)
	});
});


