<?php $options = get_option('site_basic_options');
$bodysetting = $options['bodysetting']; ?>
<style type="text/css">
	#boxed #copyright .margin{
		background:<?php echo $options['copyrightcol'] ?> !important;
		color:<?php echo $options['copyrightfontcol'] ?> !important;
	}
	
	<?php if($bodysetting == "default") : ?>
	body#boxed{
		background:#ebebeb url(<?php bloginfo('template_url') ?>/images/background.jpg) repeat !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "color") : ?>
	body#boxed{
		background-image:none !important;
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "image") : ?>
	body#boxed{
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php endif; ?>
	
	#boxed .margin{
		background-color:<?php echo $options['boxedcolor'] ?> !important;
	}
	
	a{
		color:<?php echo $options['linkcol'] ?>;
	}
	
	#boxed p{
		color:<?php echo $options['paracolor'] ?>;
	}
	#boxed h1, #boxed h2, #boxed h3, #boxed h4, #boxed h1 a, #boxed h2 a, #boxed h3 a, #boxed table.logdisplay strong, #boxed table.logdisplay tr.toprow td, #boxed table.logdisplay tr.toprow2 td, table.customer_details td:first-child{
		color:<?php echo $options['headingcol'] ?>;
	}
	#boxed #copyright p{
		color:<?php echo $options['copyrightfontcol'] ?>;
	}
</style>