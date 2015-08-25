<?php $options = get_option('site_basic_options'); ?>
<?php $file = $options['leaderimage'];
$bodysetting = $options['bodysetting']; ?>
<style type="text/css">
	#footer, #copyright{
		background-color:<?php echo $options['footercol'] ?> !important;
		color:<?php echo $options['footerfontcol'] ?> !important;
	}
	<?php if($bodysetting == "default") : ?>
	body#full{
		background:#ebebeb url(<?php bloginfo('template_url') ?>/images/background.jpg) repeat !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "color") : ?>
	body#full{
		background-image:none !important;
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "image") : ?>
	body#full{
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php endif; ?>
	#full #footer p{
		color:<?php echo $options['footerfontcol'] ?> !important;
	}
	
	<?php if($options['sliderbackground'] == "image") : ?>
	body.home #header-wrapper{
		background-image:url(<?php echo $file['url'] ?>) !important;
		background-repeat: <?php echo $options['sliderbackrepeat'] ?> !important;
		background-position: <?php echo $options['sliderbackpos'] ?> bottom !important;
		background-color:<?php echo $options['headercol'] ?> !important;
	}
	body.home #header, body.home #leader{
		background:none !important;
	}
	<?php elseif ($options['sliderbackground'] == "color") : ?>
	body.home #header, body.home #leader{
		background:none !important;
	}
	body.home #header-wrapper{
		background-image:url(<?php bloginfo('template_url') ?>/images/radial-gradient.png) center center !important;
		background-repeat:no-repeat !important;
		background-position:center bottom !important;
		background-color:<?php echo $options['headercol'] ?> !important;
	}
	<?php elseif ($options['sliderbackground'] == "default") : ?>
	body.home #header, body.home #leader{
		background:none !important;
	}
	<?php endif; ?>
	#header{
		background-color:<?php echo $options['headercol'] ?> !important;
	}
	
	#full #footer-bottom h3, #full #footer-bottom h4, #full #footer h3 a, #full #footer-top h3.widget-title{
		color:<?php echo $options['footerheadercol'] ?> !important;
	}
	
	#footer a{
		color:<?php echo $options['footerlinkcol'] ?> !important;
	}
	
	#header a, #user-nav a{
		color:<?php echo $options['headerlinkcol'] ?> !important;
	}
	
	a{
		color:<?php echo $options['linkcol'] ?> !important;
	}
	
	#full p{
		color:<?php echo $options['paracolor'] ?> !important;
	}
	#full h1, #full h2, #full h3, #full h4, #full h1 a, #full h2 a, #full h3 a, #full table.logdisplay strong, #full table.logdisplay tr.toprow td, #full table.logdisplay tr.toprow2 td, table.customer_details td:first-child{
		color:<?php echo $options['headingcol'] ?> !important;
	}
	#full #copyright p{
		color:<?php echo $options['copyrightfontcol'] ?> !important;
	}
	
	#full div.blog-overview div.post-meta{
		border-top:1px dotted <?php echo $options['paracolor'] ?> !important;
		border-bottom:1px dotted <?php echo $options['paracolor'] ?> !important;
	}
	
	div.wpsc_page_numbers{
		border-top:1px dotted <?php echo $options['paracolor'] ?> !important;
	}

</style>