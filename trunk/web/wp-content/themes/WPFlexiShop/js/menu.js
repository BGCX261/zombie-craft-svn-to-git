jQuery(document).ready(function($) {
	var dropWidth = 0;
	$("div.header-categories-drop div.categories-group").each(function(index){
		dropWidth += 156;
	});
	$("div.header-categories-drop").width(dropWidth-29);
	$("div.header-categories-drop div.categories-group:last").addClass("last");
});