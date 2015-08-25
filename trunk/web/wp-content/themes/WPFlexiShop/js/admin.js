jQuery(document).ready(function($) {
    $(".hidden-field").css("display","none");
    var field = "#" + $('.con-check:checked').val();
    $(field).show();
    $(".con-check").click(function(){
    	var field = "#" + $(this).val();
    	$(".hidden-field").hide();
		$(field).show();
		var name = $(field).find("select :selected").text();
		$("#slider-name").val(name);
 	}); 	
 	$('div.select select').change(function(){
 		var name = $(this).find(":selected").text();
		$("#slider-name").val(name);
 	});
 	$("input.color").ColorPicker({
	color: $("input.color").val(),
	onShow: function (colpkr) {
		$(colpkr).fadeIn(500);
		return false;
	},
	livePreview: true,
	onSubmit: function(hsb, hex, rgb, el) {
		$(el).val("#" + hex);
		$(el).parent().find('div.colorSelector div').css('backgroundColor', '#' + hex);
		$(el).ColorPickerHide();
	},
	onHide: function (colpkr) {
		$(colpkr).fadeOut(500);
		return false;
	}
	});
	$('div.theme-options fieldset h3').click(function(){
		$(this).next().slideToggle();
	});
	$("input.date-input").date_input();
	
	var hiddenFields = "." + $("#theme-layout").val() + "-only";
	$(hiddenFields).show();
	
	$("#theme-layout").change(function(){
		$(".boxed-only, .full-only").hide();
		var hiddenFields = "." + $(this).val() + "-only";
		$(hiddenFields).show();	
		if ($(this).val() == "full"){
			if($("#slider-background input:checked").val() == "image")
				$('.slider-background-hide').show();
			else
				$('.slider-background-hide').hide();
		}
	});
	if ($("#theme-layout").val() == "full"){
	if($("#slider-background input:checked").val() == "image")
		$('.slider-background-hide').show();
	else
		$('.slider-background-hide').hide();
	}
	$("#slider-background input").click(function(){
		if($(this).val() == "image")
			$('.slider-background-hide').show();
		else
			$('.slider-background-hide').hide();
	});	
});