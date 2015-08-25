<?php $options = get_option('site_basic_options'); ?>
<?php $file = $options['leaderimage'];
$bodysetting = $options['bodysetting']; ?>
<style type="text/css">
	<?php if($bodysetting == "default") : ?>
	body#simple{
		background:#ebebeb url(<?php bloginfo('template_url') ?>/images/background.jpg) repeat !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "color") : ?>
	body#simple{
		background-image:none !important;
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php elseif ($bodysetting == "image") : ?>
	body#simple{
		background-color:<?php echo $options['backgroundcol'] ?> !important;
		color:<?php echo $options['paracolor'] ?> !important;
	}
	<?php endif; ?>
	
	a{
		color:<?php echo $options['linkcol'] ?> !important;
	}
	
	#simple p{
		color:<?php echo $options['paracolor'] ?> !important;
	}
	#simple h1, #simple h2, #simple h3, #simple h4, #simple h1 a, #simple h2 a, #simple h3 a, #simple table.logdisplay strong, #simple table.logdisplay tr.toprow td, #simple table.logdisplay tr.toprow2 td, table.customer_details td:first-child{
		color:<?php echo $options['headingcol'] ?> !important;
	}
	#simple #copyright p{
		color:<?php echo $options['copyrightfontcol'] ?> !important;
	}
	
	#header a{
		color:<?php echo $options['headerlinkcol'] ?> !important;
	}

</style>