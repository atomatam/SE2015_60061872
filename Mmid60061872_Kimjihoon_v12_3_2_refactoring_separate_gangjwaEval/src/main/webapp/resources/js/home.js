/**
 * 
 */

$(document).ready(function() {
	$('.normalTable tbody tr:odd').addClass('normalTableOdd');
	$('.normalTable thead tr').addClass('normalTableThead');
	$('#navy td').hover(function() {
		$(this).animate(
				{paddingLeft:'15px'},
				{duration:80, easing:'swing'}
		)
	}, function() {
		$(this).animate(
			{paddingLeft:'0px'},
			{duration:30, easing:'swing'}
		)
	});
	$('table td').hover(function() {
		$(this).animate(
				{paddingLeft:'5px'},
				{duration:80, easing:'swing'}
		)
	}, function() {
		$(this).animate(
			{paddingLeft:'0px'},
			{duration:30, easing:'swing'}
		)
	});
	$(window).scroll(function() {
		$('#contentBoby url').css({height:'300px'});
	});
	$('#navy li').hover(function() {
		$(this).animate(
				{paddingLeft:'30px'},
				{duration:80, easing:'swing'}
		)
	}, function() {
		$(this).animate(
			{paddingLeft:'25px'},
			{duration:30, easing:'swing'}
		)
	});
});