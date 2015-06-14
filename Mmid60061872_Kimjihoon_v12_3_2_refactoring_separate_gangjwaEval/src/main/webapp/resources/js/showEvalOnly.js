/**
 * 
 */

$(document).ready(function() {
	$('table tr:odd').addClass('serviceButtonColor');
	$('table tr:first').addClass('theadColor');
	$('table tr:first').addClass('theadColor');
	$('table tr').hover(function() {
		$(this).addClass('hoverClass');
	}, function() {
		$(this).removeClass('hoverClass');
	});
});