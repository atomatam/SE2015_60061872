/**
 * 
 */

$(document).ready(function() {
	$('#serviceTitle').hide();
	$('#alreadyTable').hide();
	var title = document.getElementById("serviceTitle").innerHTML;
	$('<div class="serviceButton theadColor">'+title+'</div>')
	.insertBefore('#notYetTable');
	$('<div class="toggleButton theadColor">'+'신청 현황'+'</div>')
	.insertBefore('#alreadyTable');
	$('.toggleButton').click(function() {
		$('#alreadyTable').slideToggle('slow');
	});
	$('#notYetTable table tr:odd').addClass('serviceButtonColor');
	$('#alreadyTable table tr:odd').addClass('toggleButtonColor');
	$('#notYetTable table tr:first').addClass('theadColor');
	$('#alreadyTable table tr:first').addClass('theadColor');
	$('.toggleButton').hover(function() {
		$(this).addClass('hoverClass');
	}, function() {
		$(this).removeClass('hoverClass');
	});
});
