/**
 * 
 */

$(document).ready(function() {
	$('tbody tr:odd').hide();
    $(":button").click(function(){
    	var name = $(this).attr('name');
    	$("."+name).slideToggle();
    });
});