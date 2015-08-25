<?php
	get_currentuserinfo() ;
	global $user_login, $user_level, $current_user;
?><!DOCTYPE html>

<html <?php language_attributes(); ?>>
<head>
<meta charset="<?php bloginfo( 'charset' ); ?>" />
<title><?php wp_title(''); ?><?php if(wp_title('', false)) { echo ' :: '; } ?><?php bloginfo('name'); if(is_front_page()) { echo ' :: '; bloginfo('description'); } ?></title>
	<?php
	$options = get_option('site_basic_options');
	?>
<link rel="profile" href="http://gmpg.org/xfn/11" />
<link rel="stylesheet" type="text/css" media="all" href="<?php bloginfo( 'stylesheet_url' ); ?>" />
<link rel="pingback" href="<?php bloginfo( 'pingback_url' ); ?>" />
<?php

	if ( is_singular() && get_option( 'thread_comments' ) )
		wp_enqueue_script( 'comment-reply' );

	wp_head();
?>

<script src="<?php bloginfo( 'template_url' ); ?>/js/superfish.js" type="text/javascript"></script>
<script src="<?php bloginfo( 'template_url' ); ?>/js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="<?php bloginfo( 'template_url' ); ?>/js/jquery.bxSlider.min.js" type="text/javascript"></script>
<script src="<?php bloginfo( 'template_url' ); ?>/js/prettyphoto/js/jquery.prettyPhoto.js" type="text/javascript"></script>
<?php get_template_part('css/fonts/fonts'); ?>
<?php if($options['themelayout'] != 'simple') : ?>
<link rel="stylesheet" type="text/css" media="all" href="<?php bloginfo('template_url'); ?>/css/<?php echo $options['themelayout'] ?>layout.css" />
<?php endif; ?>
<link rel="stylesheet" type="text/css" media="all" href="<?php bloginfo('template_url'); ?>/js/prettyphoto/css/prettyPhoto.css" />
<?php if ($options['themelayout'] == "full") :
		get_template_part('css/userstyles/fulllayout', 'products');
	elseif ($options['themelayout'] == "simple") :
		get_template_part('css/userstyles/simplelayout', 'products');
	elseif ($options['themelayout'] == "boxed") :
		get_template_part('css/userstyles/boxedlayout', 'products');
	endif;
	if (empty($options['slidertrans']))
		$transition = "fade";
	else
		$transition = $options['slidertrans'];
?>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("a#checkout-next").click(function(){
			$("#shopping-cart-form").fadeIn();
			var checkoutWidth = <?php if($options['themelayout'] == "boxed") : ?>950<?php else: ?>1010<?php endif; ?>;
			$("#checkout-bar-in").animate({width:'+=50%'});
			$("#checkout-slider").animate({marginLeft:'-=' + checkoutWidth});
		});
		$("a#checkout-back").click(function(){
			$("#shopping-cart-form").fadeOut();
			var checkoutWidth = <?php if($options['themelayout'] == "boxed") : ?>950<?php else: ?>1010<?php endif; ?>;
			$("#checkout-bar-in").animate({width:'-=50%'});
			$("#checkout-slider").animate({marginLeft:'+=' + checkoutWidth});
		});
		<?php $post_count = wp_count_posts('slider'); if($post_count->publish > 1 ) : ?>
		$('ul.feature-list').bxSlider({
            mode: '<?php echo $transition ?>',
            auto: true,
            easing: 'jswing',
            autoControls: true,
            autoHover: true,
            pager: true,
            speed: 1500,
            pause: 4000,
            controls: true,
            pagerSelector: '#slider-controls'
    	});
    	<?php endif; ?>
    	$('div.slideable div.front-category-slider').bxSlider({
    		easing: 'jswing',
    		speed: 1500,
    		pager: false,
    		infiniteLoop: true, 
            pause: 5000,
            autoHover: true,
    		auto: true
    	});
    	$('a.large-blog-image, a.category-thumbnail img, li.latest-product div.padding').hover(function(){
    		$(this).fadeTo('slow', 0.5);
    	},
    	function(){
    		$(this).fadeTo('slow', 1);
    	});
    	$('ul.product-list li div.product-meta').hover(function(){
    		$(this).find('img').fadeTo('slow', 0.3);
    	},
    	function(){
    		$(this).find('img').fadeTo('slow', 1);
    	});
    	$('#sidebar ul.sidebar-widgets li ul li').hover(function(){
    		$(this).animate({
    			paddingLeft: "+10px"
    		}, 100, 'jswing');
    	},
    	function(){
    		$(this).animate({
    			paddingLeft: "0px"
    		}, 100, 'jswing');
    	});
    	$('li.feature').hover(function(){
    		$(this).find('div.feature-post-wrapper').slideDown();
    	},
    	function(){
    		$(this).find('div.feature-post-wrapper').slideUp();
    	});
    	
    	$('input.wpsc_buy_button').click(function(){
    		var cartCount = $('#cart-top a span.cartcount').text();
    		var cartInt = parseInt(cartCount);
    		var quantity = parseInt($('#wpsc_quantity_update').val());
    		if (quantity > 1)
    			cartInt += quantity;
    		else
    			cartInt++;
 			$('#cart-top a span.cartcount').text(cartInt + " items");
    	});
    	$('span.emptycart a').click(function(){
    		$('#cart-top a span.cartcount').text("0 items");
    	});
    	$('#header #top-navigation ul li, #header #top-navigation ul li').hover(function(){
    		$(this).find('ul.children, ul.sub-menu').fadeIn();
    	},
    	function(){
    		$(this).find('ul.children, ul.sub-menu').fadeOut();	
    	});
    	$('input.wpsc_product_search').val("Search Products...");
    	$('input.wpsc_product_search').click(function(){
    		$('input.wpsc_product_search').val("");
    	});
	});
</script>
</head>
<body id="<?php echo $options['themelayout'] ?>" <?php body_class(); ?>>
	<div id="top-header">
		<div class="margin">
			<div id="top-header-nav">
				<div id="cart-top">
					<span class='checkout-top cartcount'><a target='_parent' href='<?php echo get_option('shopping_cart_url'); ?>'><img src="<?php bloginfo('template_url') ?>/images/basket.png" alt="shopping basket" /><span class='cartcount'><?php echo wpsc_cart_item_count(); ?> <?php _e( 'items', 'flexishop' ); ?></span></a></span>
					<span class='amount-top items'></span>
					<?php getCart(); ?>
				</div>
				<div id="header-categories">
					<h4 class="top-nav-header"><?php _e( 'Products', 'flexishop' ); ?></h4>
					<div class="header-categories-drop"><?php getGroupedCategories(); ?></div>
				</div>
			</div>
			
		</div>
	</div>
	<div id="user-nav">
		<div class="margin">
		<ul>
			<?php if ($current_user->user_level > -1) : ?>
				<li class="no"><a class="black" href="<?php echo wp_logout_url(get_option('home')); ?>"><?php _e( 'Logout', 'flexishop' ); ?></a></li>
                <li><a href="<?php echo get_option('user_account_url'); ?>"><?php _e( 'Your Account', 'flexishop' ); ?></a></li>
			<?php else : ?>
				<li><a href="<?php echo get_option('home'); ?>/wp-login.php?action=register"><?php _e( 'Register', 'flexishop' ); ?></a></li>
                <li class="no"><a href="<?php echo get_option('user_account_url'); ?>"><?php _e( 'Sign In', 'flexishop' ); ?></a></li>
			<?php endif; ?>
		</ul>
		</div>
	</div>
	<div id="header-wrapper">
	<div id="header" class="container">
		<div class="margin">
			<div id="logo">
				<a href="<?php echo home_url( '/' ); ?>" title="<?php echo esc_attr( get_bloginfo( 'name', 'display' ) ); ?>" rel="home"><?php if (!empty($options['sitelogo']['url'])) : ?><img src="<?php echo $options['sitelogo']['url'] ?>" alt="<?php bloginfo( 'name' ); ?>" /><?php else : ?><?php bloginfo( 'name' ); ?><?php endif; ?></a>
			</div>
			<div id="topnav" role="navigation">
			 <?php if ( has_nav_menu( 'primary' ) ) : ?>
                <?php wp_nav_menu( array( 'menu_class' => 'superfish', 'theme_location' => 'primary' ) ); ?>
             <? else : ?>
                <ul class="superfish">
                <li><a href="<?php echo get_settings('home'); ?>/"><?php _e('Home','flexishop'); ?></a></li>
                <?php wp_list_pages('title_li=&sort_column=menu_order'); ?>
                </ul>
             <? endif; ?>
			</div>
			<br class="clear" />
		</div>
	</div>
	<div id="leader" class="container">
		<div class="margin">
		    <?php if (is_page_template('frontpage.php')) :
				include 'leaders/featured.php';
		  	elseif (is_page_template('slideshow.php')) :
				include 'leaders/slideshow.php';
			elseif (is_category()) : ?>
				<h1><?php printf( __( 'Category Archives: %s', 'flexishop' ), '' . single_cat_title( '', false ) . '' ); ?></h1>
				<?php $category_description = category_description();
					if ( ! empty( $category_description ) )
						echo $category_description ?>
			<?php elseif (is_archive()) : ?>
				<?php if ( is_day() ) : ?>
								<h1><?php printf( __( 'Daily Archives: %s', 'flexishop' ), get_the_date() ); ?></h1>
				<?php elseif ( is_month() ) : ?>
								<h1><?php printf( __( 'Monthly Archives: %s', 'flexishop' ), get_the_date('F Y') ); ?></h1>
				<?php elseif ( is_year() ) : ?>
								<h1><?php printf( __( 'Yearly Archives: %s', 'flexishop' ), get_the_date('Y') ); ?></h1>
				<?php else : ?>
								<h1	><?php _e( 'Blog Archives', 'flexishop' ); ?></h1>
				<?php endif; ?>
			<?php else: ?>
				<?php if(wpsc_display_products()): ?>
					<?php if(wpsc_is_in_category() && ((get_option('wpsc_category_description') &&  wpsc_category_description()) || (get_option('show_category_thumbnails') && wpsc_category_image()))) : ?>				
						<?php if(get_option('show_category_thumbnails') && wpsc_category_image()) : ?>
						<div class='group-thumbnail'>
								<img src='<?php echo wpsc_category_image(); ?>' alt='<?php echo wpsc_category_name(); ?>' title='<?php echo wpsc_category_name(); ?>' class="thumbnail" />
						</div>
						<?php endif; ?>
					<?php endif; ?>
					<?php if(wpsc_has_breadcrumbs()) :?>
							<div class='breadcrumb'>
								<a href='<?php echo get_option('product_list_url'); ?>'><?php echo get_option('blogname'); ?></a> &raquo;
								<?php while (wpsc_have_breadcrumbs()) : wpsc_the_breadcrumb(); ?>
									<?php if(wpsc_breadcrumb_url()) :?> 	   
										<a href='<?php echo wpsc_breadcrumb_url(); ?>'><?php echo wpsc_breadcrumb_name(); ?></a> &raquo;
									<?php else: ?> 
										<?php echo wpsc_breadcrumb_name(); ?>
									<?php endif; ?> 
								<?php endwhile; ?>
							</div>
						<?php endif; ?>
				<?php endif; ?>
				<h1><?php wp_title(''); ?><?php if(is_page('checkout')) : ?><span class="checkout_totals"> / <?php echo wpsc_cart_total(); ?></span> <?php endif; ?></h1>
				<?php if(wpsc_display_products()): ?>
					<?php if(wpsc_is_in_category() && ((get_option('wpsc_category_description') &&  wpsc_category_description()) || (get_option('show_category_thumbnails') && wpsc_category_image()))) : ?>
						<div class='wpsc_category_details'>
							<?php if(get_option('wpsc_category_description') &&  wpsc_category_description()) : ?>
								<p class="category-description"><?php echo wpsc_category_description(); ?></p>
							<?php endif; ?>
						</div>
					<?php endif; ?>
				<?php endif; ?>
			<?php endif; ?>
			<br class="clear" />
		</div>
	</div>
	</div>