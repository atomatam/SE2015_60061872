/**
 * 
 */

$(document).ready(function() {
	if(document.getElementById('evalAalarm')) {
		$('#evalAalarm').hide();
		$('#evalAalarm').slideDown('slow', function() {
			$('#evalAalarm').fadeIn();
		}
	)};
});