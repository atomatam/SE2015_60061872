/**
 * 
 */

$(document).ready(function() {
	$('#title_toggle').hide();
	$('#alreadyTable').hide();
	var title = document.getElementById('title_toggle').innerHTML;
	$('<div id="toggleButton">'+title+'</div>')
	.insertBefore('#alreadyTable');
	$('#toggleButton').hover(function() {
		$(this).addClass('hoverColor');
	}, function() {
		$(this).removeClass('hoverColor');
	});
	$('#toggleButton').click(function() {
		$('#alreadyTable').slideToggle('slow');
	});
	$('#alreadyTable tr:even').addClass('alreadyTableColor');
	$('#toggleButton').addClass('alreadyTableColor');
});
