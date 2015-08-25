<?php

add_action( 'after_setup_theme', 'flexishop_setup' );

if ( ! function_exists( 'flexishop_setup' ) ):

function flexishop_setup() {

	// This theme styles the visual editor with editor-style.css to match the theme style.
	add_editor_style();

	// This theme uses post thumbnails
	add_theme_support( 'post-thumbnails' );

	// Add default posts and comments RSS feed links to head
	add_theme_support( 'automatic-feed-links' );

	// Make theme available for translation
	// Translations can be filed in the /languages/ directory
	load_theme_textdomain( 'flexishop', TEMPLATEPATH . '/languages' );

	$locale = get_locale();
	$locale_file = TEMPLATEPATH . "/languages/$locale.php";
	if ( is_readable( $locale_file ) )
		require_once( $locale_file );

	// This theme uses wp_nav_menu() in one location.
	register_nav_menus( array(
		'primary' => __( 'Primary Navigation', 'flexishop' ),
	) );

	// This theme allows users to set a custom background
	add_custom_background();

	define( 'HEADER_TEXTCOLOR', '' );

	define( 'HEADER_IMAGE_WIDTH', apply_filters( 'flexishop_header_image_width', 75 ) );
	define( 'HEADER_IMAGE_HEIGHT', apply_filters( 'flexishop_header_image_height', 75 ) );

	// Larger images will be auto-cropped to fit, smaller ones will be ignored. See header.php.
	set_post_thumbnail_size( HEADER_IMAGE_WIDTH, HEADER_IMAGE_HEIGHT, true );
	add_image_size( 'promotion', 980, 375, true); // Feature size
	add_image_size( 'blog', 690, 282, true); // Feature Blog Size
	// Don't support text inside the header image.
	define( 'NO_HEADER_TEXT', true );

}
endif;

function flexishop_page_menu_args( $args ) {
	$args['show_home'] = true;
	return $args;
}
add_filter( 'wp_page_menu_args', 'flexishop_page_menu_args' );

function flexishop_continue_reading_link() {
	return ' <a href="'. get_permalink() . '">' . __( 'Continue reading <span class="meta-nav">&rarr;</span>', 'flexishop' ) . '</a>';
}

function flexishop_auto_excerpt_more( $more ) {
	return ' &hellip;' . flexishop_continue_reading_link();
}
add_filter( 'excerpt_more', 'flexishop_auto_excerpt_more' );

function content($num) {  
  $limit = $num+1;  
  $permalink = get_permalink();
  $content = str_split(get_the_content()); 
  $length = count($content);
  if ($length>=$num) {
    $content = array_slice( $content, 0, $num);  
    $content = implode("",$content)."<a href='$permalink'>".__( 'Read More', 'flexishop' )."</a>";  
    echo $content;  
  } else { 
    the_content();
  }
}
if ( ! function_exists( 'flexishop_comment' ) ) :

function flexishop_comment( $comment, $args, $depth ) {
	$GLOBALS['comment'] = $comment;
	switch ( $comment->comment_type ) :
		case '' :
	?>
	<li <?php comment_class(); ?> id="li-comment-<?php comment_ID(); ?>">
		<div id="comment-<?php comment_ID(); ?>" class="comment-wrapper">
		<div class="comment-author vcard">
			<?php echo get_avatar( $comment, 60 ); ?>
			<?php printf( __( '%s', 'flexishop' ), sprintf( '<cite class="fn">%s</cite>', get_comment_author_link() ) ); ?>
		</div><!-- .comment-author .vcard -->
		<?php if ( $comment->comment_approved == '0' ) : ?>
			<em><?php _e( 'Your comment is awaiting moderation.', 'flexishop' ); ?></em>
			<br />
		<?php endif; ?>
		<div class="comment-body">
			<div class="comment-meta commentmetadata"><a href="<?php echo esc_url( get_comment_link( $comment->comment_ID ) ); ?>">
				<?php
					/* translators: 1: date, 2: time */
					printf( __( '%1$s at %2$s', 'flexishop' ), get_comment_date(),  get_comment_time() ); ?></a><?php edit_comment_link( __( '(Edit)', 'flexishop' ), ' ' );
				?>
			</div><!-- .comment-meta .commentmetadata -->
	
			<div class="comment-text"><?php comment_text(); ?></div>
	
			<div class="reply">
				<?php comment_reply_link( array_merge( $args, array( 'depth' => $depth, 'max_depth' => $args['max_depth'] ) ) ); ?>
			</div><!-- .reply -->
		</div>
		<br class="clear" />
		</div><!-- #comment-##  -->

	<?php
			break;
		case 'pingback'  :
		case 'trackback' :
	?>
	<li class="post pingback">
		<p><?php _e( 'Pingback:', 'flexishop' ); ?> <?php comment_author_link(); ?><?php edit_comment_link( __('(Edit)', 'flexishop'), ' ' ); ?></p>
	<?php
			break;
	endswitch;
}
endif;

/**
 * Register widgetized areas
 */
 
function flexishop_widgets_init() {
	// Area 1, located at the top of the sidebar.
	register_sidebar( array(
		'name' => __( 'Primary Widget Area', 'flexishop' ),
		'id' => 'primary-widget-area',
		'description' => __( 'The primary widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	// Area 2, located below the Primary Widget Area in the sidebar. Empty by default.
	register_sidebar( array(
		'name' => __( 'Secondary Widget Area', 'flexishop' ),
		'id' => 'secondary-widget-area',
		'description' => __( 'The secondary widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	// Area 3, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'First Footer Widget Area', 'flexishop' ),
		'id' => 'first-footer-widget-area',
		'description' => __( 'The first footer widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	// Area 4, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'Second Footer Widget Area', 'flexishop' ),
		'id' => 'second-footer-widget-area',
		'description' => __( 'The second footer widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	// Area 5, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'Third Footer Widget Area', 'flexishop' ),
		'id' => 'third-footer-widget-area',
		'description' => __( 'The third footer widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	// Area 6, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'Fourth Footer Widget Area', 'flexishop' ),
		'id' => 'fourth-footer-widget-area',
		'description' => __( 'The fourth footer widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	
	// Area 7, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'Fifth Footer Widget Area', 'flexishop' ),
		'id' => 'fifth-footer-widget-area',
		'description' => __( 'The fifth footer widget area', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	
	// Area 8, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'First Footer Top Widget Area', 'flexishop' ),
		'id' => 'first-footer-top-widget-area',
		'description' => __( 'The first footer top widget area', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	
	// Area 9, located in the footer. Empty by default.
	register_sidebar( array(
		'name' => __( 'Second Footer Top Widget Area', 'flexishop' ),
		'id' => 'second-footer-top-widget-area',
		'description' => __( 'The second footer top widget area', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	
	// Area 10, located on the homepage left column.
	register_sidebar( array(
		'name' => __( 'Home Left', 'flexishop' ),
		'id' => 'home-left',
		'description' => __( 'The left home column', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	// Area 11, located on the homepage middle column.
	register_sidebar( array(
		'name' => __( 'Home Middle', 'flexishop' ),
		'id' => 'home-middle',
		'description' => __( 'The center home column', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	// Area 12, located on the homepage right column.
	register_sidebar( array(
		'name' => __( 'Home Right', 'flexishop' ),
		'id' => 'home-right',
		'description' => __( 'The right home column', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	// Area 13, located in the footer, where the blog posts are.
	register_sidebar( array(
		'name' => __( 'Footer Top Left', 'flexishop' ),
		'id' => 'blog-footer-top-widget-area',
		'description' => __( 'The left panel in the top footer', 'flexishop' ),
		'before_widget' => '<div id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</div>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	// Area 14, located in the footer, where the blog posts are.
	register_sidebar( array(
		'name' => __( 'Products Sidebar', 'flexishop' ),
		'id' => 'products-sidebar',
		'description' => __( 'The sidebar on the products page', 'flexishop' ),
		'before_widget' => '<li id="%1$s" class="widget-container %2$s">',
		'after_widget' => '</li>',
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

}

add_action( 'widgets_init', 'flexishop_widgets_init' );

function flexishop_remove_recent_comments_style() {
	global $wp_widget_factory;
	remove_action( 'wp_head', array( $wp_widget_factory->widgets['WP_Widget_Recent_Comments'], 'recent_comments_style' ) );
}
add_action( 'widgets_init', 'flexishop_remove_recent_comments_style' );

if ( ! function_exists( 'flexishop_posted_on' ) ) :

function flexishop_posted_on() {
	printf( __( '<span class="%1$s">Posted on</span> %2$s <span class="meta-sep">by</span> %3$s', 'flexishop' ),
		'meta-prep meta-prep-author',
		sprintf( '<a href="%1$s" title="%2$s" rel="bookmark"><span class="entry-date">%3$s</span></a>',
			get_permalink(),
			esc_attr( get_the_time() ),
			get_the_date()
		),
		sprintf( '<span class="author vcard"><a class="url fn n" href="%1$s" title="%2$s">%3$s</a></span>',
			get_author_posts_url( get_the_author_meta( 'ID' ) ),
			sprintf( esc_attr__( 'View all posts by %s', 'flexishop' ), get_the_author() ),
			get_the_author()
		)
	);
}
endif;

if ( ! function_exists( 'flexishop_posted_in' ) ) :

function flexishop_posted_in() {
	// Retrieves tag list of current post, separated by commas.
	$tag_list = get_the_tag_list( '', ', ' );
	if ( $tag_list ) {
		$posted_in = __( 'This entry was posted in %1$s and tagged %2$s. Bookmark the <a href="%3$s" title="Permalink to %4$s" rel="bookmark">permalink</a>.', 'flexishop' );
	} elseif ( is_object_in_taxonomy( get_post_type(), 'category' ) ) {
		$posted_in = __( 'This entry was posted in %1$s. Bookmark the <a href="%3$s" title="Permalink to %4$s" rel="bookmark">permalink</a>.', 'flexishop' );
	} else {
		$posted_in = __( 'Bookmark the <a href="%3$s" title="Permalink to %4$s" rel="bookmark">permalink</a>.', 'flexishop' );
	}
	// Prints the string, replacing the placeholders.
	printf(
		$posted_in,
		get_the_category_list( ', ' ),
		$tag_list,
		get_permalink(),
		the_title_attribute( 'echo=0' )
	);
}
endif;


//Register Admin Styles and Scripts

add_action('admin_init', 'my_theme_styles');

function my_theme_styles()
    {
        /* Register our stylesheet. */
        wp_register_style('FlexiStoreStyle', get_bloginfo('template_url') . '/css/admin.css');
        wp_register_style('ColorPicker', get_bloginfo('template_url') . '/css/admin/colorpicker.css');
        wp_register_style('DatePicker', get_bloginfo('template_url') . '/css/admin/date_input.css');
        wp_register_script('FlexiStoreScript', get_bloginfo('template_url') . '/js/admin.js');
        wp_register_script('GoogleFont', get_bloginfo('template_url') . '/js/admin/google-font-api-font-chooser.min.js');
        wp_register_script('ColorPickerScript', get_bloginfo('template_url') . '/js/admin/colorpicker.js');
        wp_register_script('DatePickerScript', get_bloginfo('template_url') . '/js/admin/jquery.date_input.min.js');
        wp_register_script('AdminScript', get_bloginfo('template_url') . '/js/admin.js');
    }

require_once ( get_stylesheet_directory() . '/theme-options.php' );

add_action('admin_print_styles-post.php','FlexiStoreAdmin');
add_action('admin_print_styles-post-new.php','FlexiStoreAdmin');


//Get Methods, easy access to various lists of products, posts and categories.

function getCategories($split){
	if (!$split)
		get_template_part( 'functions/categories', 'products' );
	else
		get_template_part( 'functions/splitcategories', 'products' );
}

function getBestSellers(){
	get_template_part('functions/bestsellers', 'products');
}

function getLatestProducts(){
	get_template_part('functions/latestproducts', 'products');
}

function getFrontCategories(){
	get_template_part('functions/frontcategories', 'products');
}

function getFeaturedProducts(){
	get_template_part('functions/featuredproducts', 'products');
}

function getFeaturedBlog(){
	get_template_part('functions/featuredblog', 'products');
}

function getCart(){
	get_template_part('functions/cart', 'products');
}

function getFullCategories(){
	get_template_part('functions/fullcategories', 'products');
}

function getGroupedCategories(){
	get_template_part('functions/groupedcategories', 'products');
}

function getTwitter(){
	get_template_part('functions/twitter', 'products');
}

function getProductGallery($product_id, $invisible = false){
	require_once ( get_stylesheet_directory() . '/functions/productgallery.php' );
}



function getBlogCategories(){
	$get_cats = wp_list_categories( 'echo=0&title_li=&depth=1' );
	$cat_array = explode('</li>',$get_cats);
	echo "<h3>".__( 'Blog Categories', 'flexishop' )."</h3><ul class='category-list'>";
	foreach($cat_array as $category) {
		echo $category;
	}	
	echo "</ul>";

}

function getTestimonials(){
	get_template_part('functions/testimonials', 'products');
}

function getPromotions(){
	get_template_part('functions/promotions', 'products');
}

function getSliderPosts($posts){
	$options = get_option('site_basic_options'); ?>
	<?php $args = array(
		'post__in'  => explode(",", $posts),
		'post_type' => array('post','page')
	); ?>
	<?php $feature_query = new WP_Query($args); ?>
	<?php if ($feature_query->have_posts()) : while ($feature_query->have_posts()) : $feature_query->the_post(); ?>
	<?php $postid = get_the_ID(); ?>
	<li class="feature">
		<div class="post-background">
			<?php 
				$thumb = get_the_post_thumbnail($postid, 'promotion');
				$pattern= "/(?<=src=['|\"])[^'|\"]*?(?=['|\"])/i";
				preg_match($pattern, $thumb, $thePath);
				$theSrc = get_timthumb_src( $thePath[0] ); 
			?>
			<a href="<?php the_permalink() ?>" title="<?php the_title(); ?>" class="featured-blog-image"><?php if ($options['themelayout'] == 'boxed') : ?><img src="<?php bloginfo('template_url') ?>/timthumb.php?src=<?php echo $theSrc ?>&w=896" alt=""><?php else: ?><?php the_post_thumbnail( 'promotion' ); ?><?php endif; ?></a>
		</div>
		<div class="feature-post-wrapper">
			<div class="post-header">
				<h3><a href="<?php the_permalink() ?>" rel="bookmark"><?php the_title(); ?></a></h3>
			</div>
			<div class="post-excerpt">
				<?php the_excerpt();?>
			</div>
		</div>
	</li>
	<?php endwhile; endif;
}

function getSliderPromotions($promotions){
	$options = get_option('site_basic_options'); ?>
	<?php $args = array(
		'post__in'  => explode(",", $promotions),
		'post_type' => 'promotion'
	);
	?>
	<?php $promotion_query = new WP_Query($args); ?>
	<?php if ($promotion_query->have_posts()) : while ($promotion_query->have_posts()) : $promotion_query->the_post(); ?>
	<?php $postid = get_the_ID(); ?>
	<?php 
		$thumb = get_the_post_thumbnail($postid, 'promotion');
		$pattern= "/(?<=src=['|\"])[^'|\"]*?(?=['|\"])/i";
		preg_match($pattern, $thumb, $thePath);
		$theSrc = get_timthumb_src( $thePath[0] ); 
	?>
	<li class="promotion">
		<div class="promotion-text">
			<h3><?php the_title(); ?></h3>
			<div class="promotion-meta">
			<?php $postid = get_the_ID(); ?>
			<?php $custom = get_post_custom($postid);
					$saving = $custom["saving"][0];
					$external_link = $custom["link"][0];
					$link_type = $custom["link_type"][0];
					$start_date = $custom["start_date"][0];
					$end_date = $custom["end_date"][0];
					$promotion_link = $custom["promotion_link"][0];
					$promotion_link_category = $custom["promotion_link_category"][0];
					echo strlen($link_type);
					if ($link_type == "category")
						$link = $promotion_link_category;
					elseif ($link_type =="product")
						$link = $promotion_link;
					elseif ($link_type == "external")
						$link = $external_link;
					else
						$link = "#";
					?>
				<span class="saving"><?php echo $saving ?></span>
				<span class="promotion_link"><?php echo $link ?></span>
				<span class="start-date"><?php echo $start_date ?></span>
				<span class="end-date"><?php echo $end_date ?></span>
			</div>
			<div class="promotion-content">
				<?php the_content(__('Read more', 'flexishop' ));?>
			</div>
		</div>
		<div class="promotion-header">
			<a href="<?php echo $link ?>" <?php if ($custom['pretty_photo'][0] == 'yes' && $link_type == 'external') : ?>rel="prettyPhoto"<?php endif; ?> title="<?php the_title(); ?>"><?php if ($options['themelayout'] == 'boxed') : ?><img src="<?php bloginfo('template_url') ?>/timthumb.php?src=<?php echo $theSrc ?>&w=896" alt=""><?php else: ?><?php the_post_thumbnail( 'promotion' ); ?><?php endif; ?></a>
		</div>
	</li>
	<?php endwhile; endif;
}

function getSliderProducts($products){
	$products = explode(",", $products);
	global $wpsc_query, $wpdb;
	foreach($products as $product){
		$image_width = get_option('single_view_image_width');
		$image_height = get_option('single_view_image_height');
		$options = get_option('site_basic_options');
		$wpsc_query = new WPSC_Query(array('product_id' => $product));
		while (wpsc_have_products()) : wpsc_the_product(); ?>
			<li class="feature-product">
			<?php if(wpsc_the_product_thumbnail()) :?>
			<div class="product-image">
				<a rel="<?php echo str_replace(array(" ", '"',"'", '&quot;','&#039;'), array("_", "", "", "",''), wpsc_the_product_title()); ?>" class="thickbox preview_link" href="<?php echo wpsc_the_product_image(); ?>">
					<img class="product_image" id="product_image_<?php echo wpsc_the_product_id(); ?>" alt="<?php echo wpsc_the_product_title(); ?>" title="<?php echo wpsc_the_product_title(); ?>" src="<?php if ($options['themelayout'] == 'boxed') : ?><?php bloginfo('template_url') ?>/timthumb.php?src=<?php echo wpsc_the_product_image($image_width, $image_height); ?>&w=457<?php else: ?><?php echo wpsc_the_product_image($image_width, $image_height); ?><?php endif; ?>" />
				</a>
			</div>
			<?php else: ?>
				<div class="product-image product-thumb item_no_image">
					<a href="<?php echo wpsc_the_product_permalink(); ?>">
					<span><?php _e( 'No Image Available', 'flexishop' ); ?></span>
					</a>
				</div>
			<?php endif; ?>
			<div class="product-content">
			<h2 class="prodtitles">
				<?php if(get_option('hide_name_link') == 1) : ?>
					<span><?php echo wpsc_the_product_title(); ?></span>
				<?php else: ?> 
					<a class="wpsc_product_title" href="<?php echo wpsc_the_product_permalink(); ?>"><?php echo wpsc_the_product_title(); ?></a>
				<?php endif; ?> 				
			</h2>
			<!-- <div class="wpsc_product_price">
			<?php if(wpsc_product_is_donation()) : ?>
				<label for='donation_price_<?php echo wpsc_the_product_id(); ?>'><?php echo __('Donation', 'flexishop'); ?>:</label>
				<input type='text' id='donation_price_<?php echo wpsc_the_product_id(); ?>' name='donation_price' value='<?php echo $wpsc_query->product['price']; ?>' size='6' />
				<br />
			
			
			<?php else : ?>
				<?php if(wpsc_product_on_special()) : ?>
					<span class='oldprice'><?php echo __('Price', 'flexishop'); ?>: <?php echo wpsc_product_normal_price(get_option('wpsc_hide_decimals')); ?></span><br />
				<?php endif; ?>
				<span id="product_price_<?php echo wpsc_the_product_id(); ?>" class="pricedisplay <?php if(wpsc_product_on_special()) echo "sale-price" ?>"><?php if(wpsc_product_on_special()) echo __( "Sale Price: ", 'flexishop' ); ?><?php echo wpsc_the_product_price(get_option('wpsc_hide_decimals')); ?></span>					
			<?php endif; ?>
			</div> -->
			<?php if(wpsc_the_product_additional_description()) : ?>
			<div class="description">
			<?php
				$value = '';
				$the_addl_desc = wpsc_the_product_additional_description();
				if( is_serialized($the_addl_desc) ) {
					$addl_descriptions = @unserialize($the_addl_desc);
				} else {
					$addl_descriptions = array('addl_desc', $the_addl_desc);
				}
				
				if( isset($addl_descriptions['addl_desc']) ) {
					$value = $addl_descriptions['addl_desc'];
				}
	
	        	if( function_exists('wpsc_addl_desc_show') ) {
	        		echo wpsc_addl_desc_show( $addl_descriptions );
	        	} else {
								echo stripslashes( wpautop($the_addl_desc, $br=1));
	        	}
	        ?>
			</div>
			<?php endif; ?>
			<a href="<?php echo wpsc_the_product_permalink(); ?>" class="buy-now"><?php _e( 'Buy Now', 'flexishop' ); ?></a>
			</div>
		</li>
		<?php endwhile;
	}
}

function getPosts(){
	get_template_part('functions/sliderposts', 'products');
}


function change_category_description($description) {
	$new_category = str_replace("<p>", "<p class='category-description'>", $description);
	return $new_category;
}
add_filter('category_description','change_category_description');

function enqueue_scripts() {
    wp_enqueue_script('menu.js', get_bloginfo('template_directory') . '/js/menu.js', array('jquery'));
}
add_action('init', 'enqueue_scripts');

//add custom post types
//create promotion post type
add_action( 'init', 'create_promotion_type' );
function create_promotion_type() {
  register_post_type( 'promotion',
    array(
      'labels' => array(
        'name' => __('Promotions', 'flexishop' ),
		'singular_name' => __('Shop Promotion', 'flexishop' ),
		'add_new' => __('Add New', 'flexishop' ),
		'add_new_item' => __('Add New Shop Promotion', 'flexishop' ),
		'edit_item' => __('Edit Shop Promotion', 'flexishop' ),
		'new_item' => __('New Shop Promotion', 'flexishop' ),
		'view_item' => __('View Shop Promotion', 'flexishop' ),
		'search_items' => __('Search Promotions', 'flexishop' ),
		'not_found' =>  __('Nothing found', 'flexishop' ),
		'not_found_in_trash' => __('Nothing found in Trash', 'flexishop' ),
		'parent_item_colon' => ''
      ),
      'public' => true,
      'rewrite' => array('slug' => 'promotions'),
      'show_ui' => true,
		'capability_type' => 'post',
		'supports' => array('title', 'editor', 'thumbnail')
    )
  );
}

add_action("admin_init", "admin_init");
 
function admin_init(){
  //promotion fields
  add_meta_box("promotion_meta", __( "Promotion Details", 'flexishop' ), "promotion_meta", "promotion", "normal", "low");
   //slider fields
  add_meta_box("slider_meta", __( "Slider Type", 'flexishop' ), "slider_meta", "slider", "normal", "low");
}
 
function promotion_meta() {
	
global $post;
global $wpsc_query, $wpdb;
$custom = get_post_custom($post->ID);
$link = $custom["link"][0];
$saving = $custom["saving"][0];
$start_date = $custom["start_date"][0];
$end_date = $custom["end_date"][0];
$link_type = $custom["link_type"][0];
$promotion_link = $custom["promotion_link"][0];
$promotion_link_category = $custom["promotion_link_category"][0];
$category_data = $wpdb->get_results("SELECT  `id`, `name`, `nice-name`, `description`, `image` FROM `".WPSC_TABLE_PRODUCT_CATEGORIES,ARRAY_A);
$prettyPhoto = $custom["pretty_photo"][0];
$link_type_options = array(
	'Product' => array(
	'value' => 'product',
	'label' => __( 'Link To Product', 'flexishop' )
	),
	'Category' => array(
	'value' => 'category',
	'label' => __( 'Link To Category', 'flexishop' )
	),
	'External' => array(
	'value' => 'external',
	'label' => __( 'External Link', 'flexishop' )
	)
);
$lightbox_options = array(
	'Enable' => array(
	'value' => 'yes',
	'label' => __( 'Enable Lightbox', 'flexishop' )
	),
	'Disable' => array(
	'value' => 'no',
	'label' => __( 'Disable Lightbox', 'flexishop' )
	)
);

?>
<div class="input radio">
<?php
if ( ! isset( $checked ) )
	$checked = '';
foreach ( $link_type_options as $option ) {
	if ( '' != $link_type ) {
		if ( $link_type == $option['value'] ) {
			$checked = "checked=\"checked\"";
		} else {
			$checked = '';
		}
	}
	?>
	<label class="description"><input type="radio" name="link_type" value="<?php esc_attr_e( $option['value'] ); ?>" <?php echo $checked; ?> class="con-check" /> <?php echo $option['label']; ?></label>
	<?php
}
?>
</div>
<div id="external" class="input text hidden-field">
	<label>Links To External:</label>
	<input cols="50" name="link" value="<?php echo $link; ?>" />
	<div class="input radio">
	<?php
	if ( ! isset( $checked ) )
		$checked = '';
	foreach ( $lightbox_options as $option ) {
		if ( '' != $prettyPhoto ) {
			if ( $prettyPhoto == $option['value'] ) {
				$checked = "checked=\"checked\"";
			} else {
				$checked = '';
			}
		}
		?>
		<label class="description"><input type="radio" name="pretty_photo" value="<?php esc_attr_e( $option['value'] ); ?>" <?php echo $checked; ?> /> <?php echo $option['label']; ?></label>
		<?php
	}
	?>
	</div>
</div>
<div id="product" class="input select hidden-field">
	<label><?php _e( 'Links To Product:', 'flexishop' ); ?></label>
	<select name="promotion_link">
		<?php $wpsc_query = new WPSC_Query();
		while (wpsc_have_products()) : wpsc_the_product(); ?>
			<?php $currentlink = str_replace("&amp;", "&", wpsc_the_product_permalink()); ?>
			<option value="<?php echo wpsc_the_product_permalink(); ?>" <?php if($promotion_link == $currentlink) echo "selected = 'selected'"; ?>><?php echo wpsc_the_product_title(); ?></option>
		<?php endwhile; ?>
	</select>
</div>
<div id="category" class="input select hidden-field">
	<label><?php _e( 'Links To Category:', 'flexishop' ); ?></label>
	<select name="promotion_link_category">
	<?php foreach($category_data as $category_row) {
	$name = $category_row['name'];
	$url = str_replace("&amp;", "&", wpsc_category_url($category_row['id']));
	?>
	<option value="<?php echo $url ?>" <?php if($promotion_link_category == $url) echo "selected = 'selected'" ?>><?php echo $name ?></option>
	<?php } ?>
	</select>
</div>
<div class="input text">
	<label><?php _e( 'Savings:', 'flexishop' ); ?></label>
	<input cols="50" name="saving" value="<?php echo $saving; ?>" />
</div>
<div class="input text">
	<label><?php _e( 'Start Date:', 'flexishop' ); ?></label>
	<input name="start_date" value="<?php echo $start_date; ?>" class="date-input" />
</div>
<div class="input text">
	<label><?php _e( 'End Date:', 'flexishop' ); ?></label>
	<input name="end_date" value="<?php echo $end_date; ?>" class="date-input" />
</div>
<?php
}

add_action('save_post', 'save_details');

function save_details(){
  global $post;
 if ( defined('DOING_AUTOSAVE') && DOING_AUTOSAVE ) 
    return $post_id;
  if ( $_POST["author"] ) update_post_meta($post->ID, "testimonial_author", $_POST["author"]);
  else delete_post_meta($post->ID, "testimonial_author");
  if ( $_POST["saving"] ) update_post_meta($post->ID, "saving", $_POST["saving"]);
  else delete_post_meta($post->ID, "saving");
  if ( $_POST["start_date"] ) update_post_meta($post->ID, "start_date", $_POST["start_date"]);
  else delete_post_meta($post->ID, "start_date");
  if ( $_POST["end_date"] ) update_post_meta($post->ID, "end_date", $_POST["end_date"]);
  else delete_post_meta($post->ID, "end_date");
  if ( $_POST["link_type"] ) update_post_meta($post->ID, "link_type", $_POST["link_type"]);
  else delete_post_meta($post->ID, "link_type");
  if ( $_POST["link"] ) update_post_meta($post->ID, "link", $_POST["link"]);
  else delete_post_meta($post->ID, "link");
  if ( $_POST["promotion_link"] ) update_post_meta($post->ID, "promotion_link", $_POST["promotion_link"]);
  else delete_post_meta($post->ID, "promotion_link");
  if ( $_POST["promotion_link_category"] ) update_post_meta($post->ID, "promotion_link_category", $_POST["promotion_link_category"]);
  else delete_post_meta($post->ID, "promotion_link_category");
  if ( $_POST["sliderType"] ) update_post_meta($post->ID, "sliderType", $_POST["sliderType"]);
  else delete_post_meta($post->ID, "sliderType");
  if ( $_POST["sliderPostId"] ) update_post_meta($post->ID, "sliderPostId", $_POST["sliderPostId"]);
  else delete_post_meta($post->ID, "sliderPostId");
  if ( $_POST["sliderProductId"] ) update_post_meta($post->ID, "sliderProductId", $_POST["sliderProductId"]);
  else delete_post_meta($post->ID, "sliderProductId");
  if ( $_POST["sliderPromotionId"] ) update_post_meta($post->ID, "sliderPromotionId", $_POST["sliderPromotionId"]);
  else delete_post_meta($post->ID, "sliderPromotionId");
  if ( $_POST["sliderName"] ) update_post_meta($post->ID, "sliderName", $_POST["sliderName"]);
  else delete_post_meta($post->ID, "sliderName");
  if ( $_POST["pretty_photo"] ) update_post_meta($post->ID, "pretty_photo", $_POST["pretty_photo"]);
  else delete_post_meta($post->ID, "pretty_photo");
}

add_action("manage_posts_custom_column",  "promotion_custom_columns");
add_filter("manage_edit-promotion_columns", "promotion_edit_columns");
 
function promotion_edit_columns($columns){
  $columns = array(
    "cb" => "<input type=\"checkbox\" />",
    "title" => __( "Promotion Title", 'flexishop' ),
    "promotion" => __( "Promotion", 'flexishop' ),
    "saving" => __( "Saving", 'flexishop' ),
    "link" => __( "Links To", 'flexishop' ),
    "start_date" => __( "Start Date", 'flexishop' ),
    "end_date" => __( "End Date", 'flexishop' ),
  );
 
  return $columns;
}
function promotion_custom_columns($column){
  global $post;
 
  switch ($column) {
    case "promotion":
      the_content();
      break;
    case "saving":
      $custom = get_post_custom();
      echo $custom["saving"][0];
      break;
    case "link":
      $custom = get_post_custom();
      echo $custom["link"][0];
      break;
    case "end_date":
      $custom = get_post_custom();
      echo $custom["end_date"][0];
      break;
    case "start_date":
      $custom = get_post_custom();
      echo $custom["start_date"][0];
      break;
  }
}

//create slider post type

add_action( 'init', 'create_slider_type' );
function create_slider_type() {
  register_post_type( 'slider',
    array(
      'labels' => array(
        'name' => __('Sliders', 'flexishop' ),
		'singular_name' => __('Slider', 'flexishop' ),
		'add_new' => __('Add New', 'flexishop' ),
		'add_new_item' => __('Add New Slider', 'flexishop' ),
		'edit_item' => __('Edit Slider', 'flexishop' ),
		'new_item' => __('New Slider', 'flexishop' ),
		'view_item' => __('View Slider', 'flexishop' ),
		'search_items' => __('Search Sliders', 'flexishop' ),
		'not_found' =>  __('Nothing found', 'flexishop' ),
		'not_found_in_trash' => __('Nothing found in Trash', 'flexishop' ),
		'parent_item_colon' => ''
      ),
      'public' => true,
      'rewrite' => array('slug' => 'sliders'),
      'show_ui' => true,
		'capability_type' => 'post',
		'supports' => array('title')
    )
  );
}

function slider_meta() {
	
global $post;
global $wpsc_query, $wpdb;
$custom = get_post_custom($post->ID);
$slider_type = $custom['sliderType'][0];
$slider_post_id = $custom['sliderPostId'][0];
$slider_product_id = $custom['sliderProductId'][0];
$slider_promotion_id = $custom['sliderPromotionId'][0];
$slider_name = $custom['sliderName'][0];
$posts = $wpdb->get_results("SELECT  `id`, `post_title` FROM $wpdb->posts WHERE (post_type = 'post' OR post_type = 'page') AND post_status = 'publish'", ARRAY_A);
$promotions = $wpdb->get_results("SELECT  `id`, `post_title` FROM $wpdb->posts WHERE post_type = 'promotion' AND post_status = 'publish'", ARRAY_A);
$products = $wpdb->get_results("SELECT  `id`, `name` FROM `".WPSC_TABLE_PRODUCT_LIST . "` WHERE `active` = '1'",ARRAY_A);
$slider_type_options = array(
	'Product' => array(
	'value' => 'product',
	'label' => __( 'Featured Product', 'flexishop' )
	),
	'Post' => array(
	'value' => 'news',
	'label' => __( 'Featured Post or Page', 'flexishop' )
	),
	'Promotion' => array(
	'value' => 'promotion',
	'label' => __( 'Featured Promotion', 'flexishop' )
	)
);

?>
<div class="input radio">
<?php
if ( ! isset( $checked ) )
	$checked = '';
foreach ( $slider_type_options as $option ) {
	if ( '' != $slider_type ) {
		if ( $slider_type == $option['value'] ) {
			$checked = "checked=\"checked\"";
		} else {
			$checked = '';
		}
	}
	?>
	<label class="description"><input type="radio" name="sliderType" value="<?php esc_attr_e( $option['value'] ); ?>" <?php echo $checked; ?> class="con-check" /> <?php echo $option['label']; ?></label>
	<?php
}
?>
</div>
<div id="product" class="input select hidden-field">
	<label><?php _e( 'Product:', 'flexishop' ); ?></label>
	<select name="sliderProductId">
		<?php foreach($products as $product) {
			$name = $product['name'];
			$id = $product['id'];
			?>
			<option value="<?php echo $id ?>" <?php if($slider_product_id == $id) echo "selected = 'selected'" ?>><?php echo $name ?></option>
		<?php } ?>
	</select>
</div>
<div id="news" class="input select hidden-field">
	<label><?php _e( 'Post:', 'flexishop' ); ?></label>
	<select name="sliderPostId">
		<?php foreach($posts as $post) {
			$name = $post['post_title'];
			$id = $post['id'];
			?>
			<option value="<?php echo $id ?>" <?php if($slider_post_id == $id) echo "selected = 'selected'" ?>><?php echo $name ?></option>
		<?php } ?>
	</select>
</div>

<div id="promotion" class="input select hidden-field">
	<label><?php _e( 'Promotion:', 'flexishop' ); ?></label>
	<select name="sliderPromotionId">
		<?php foreach($promotions as $promotion) {
			$name = $promotion['post_title'];
			$id = $promotion['id'];
			?>
			<option value="<?php echo $id ?>" <?php if($slider_promotion_id == $id) echo "selected = 'selected'" ?>><?php echo $name ?></option>
		<?php } ?>
	</select>
</div>
<input type="hidden" name="sliderName" id="slider-name" value="<?php echo $slider_name ?>" />
<?php
}

add_action("manage_posts_custom_column",  "slider_custom_columns");
add_filter("manage_edit-slider_columns", "slider_edit_columns");
 
function slider_edit_columns($columns){
  $columns = array(
    "cb" => "<input type=\"checkbox\" />",
    "title" => __( "Slider Title", 'flexishop' ),
    "slider_type" => __( "Slider Type", 'flexishop' ),
    "slider_id" => __( "Slider Id", 'flexishop' ),
    "slider_name" => __( "Slider Link Name", 'flexishop' ),
  );
 
  return $columns;
}
function slider_custom_columns($column){
  global $post;
 
  switch ($column) {
    case "title":
      $custom = get_post_custom();
      the_title();
    case "slider_type":
      $custom = get_post_custom();
      echo $custom["sliderType"][0];
      break;
    case "slider_id":
      $custom = get_post_custom();
      if ($custom["sliderType"][0] == 'product')
      	echo $custom["sliderProductId"][0];
      elseif ($custom["sliderType"][0] == 'promotion')
      	echo $custom["sliderPromotionId"][0];
      elseif ($custom["sliderType"][0] == 'news')
      	echo $custom['sliderPostId'][0];
      break;
    case "slider_name":
      $custom = get_post_custom();
      echo $custom["sliderName"][0];
      break;
  }
}

function pagination($pages = '', $range = 2)
{  
     $showitems = ($range * 2)+1;  

     global $paged;
     if(empty($paged)) $paged = 1;

     if($pages == '')
     {
         global $wp_query;
         $pages = $wp_query->max_num_pages;
         if(!$pages)
         {
             $pages = 1;
         }
     }   

     if(1 != $pages)
     {
         echo "<div class='pagination'>";
         if($paged > 2 && $paged > $range+1 && $showitems < $pages) echo "<a href='".get_pagenum_link(1)."'>&laquo;</a>";
         if($paged > 1 && $showitems < $pages) echo "<a href='".get_pagenum_link($paged - 1)."'>&lsaquo;</a>";

         for ($i=1; $i <= $pages; $i++)
         {
             if (1 != $pages &&( !($i >= $paged+$range+1 || $i <= $paged-$range-1) || $pages <= $showitems ))
             {
                 echo ($paged == $i)? "<span class='current'>".$i."</span>":"<a href='".get_pagenum_link($i)."' class='inactive' >".$i."</a>";
             }
         }

         if ($paged < $pages && $showitems < $pages) echo "<a href='".get_pagenum_link($paged + 1)."'>&rsaquo;</a>";  
         if ($paged < $pages-1 &&  $paged+$range-1 < $pages && $showitems < $pages) echo "<a href='".get_pagenum_link($pages)."'>&raquo;</a>";
         echo "</div>\n";
     }
}

/* added Timthumb support for WP Multisite */
function get_timthumb_src( $src ) {
	$src = clean_source( $src );
	// Check if WPMU and set correct path AND that image isn't external
	if ( function_exists('get_current_site') && strpos($src,"http://") !== 0 ) {
		get_current_site();
		//global $blog_id; Breaks with WP3 MS
		if ( !$blog_id ) {
			global $current_blog;
			$blog_id = $current_blog->blog_id;				
		}
		if ( isset($blog_id) && $blog_id > 0 ) {
			$imageParts = explode( 'files/', $src );
			if ( isset($imageParts[1]) ) 
				$src = '/blogs.dir/' . $blog_id . '/files/' . $imageParts[1];
		}
	}
	return $src;
}
function clean_source($src) {
	// remove slash from start of string
	if(strpos($src, "/") == 0) {
		$src = substr($src, -(strlen($src) - 1));
	}
	// Check if same domain so it doesn't strip external sites
	$host = str_replace('www.', '', $_SERVER['HTTP_HOST']);
	if ( !strpos($src,$host) )
		return $src;
	$regex = "/^((ht|f)tp(s|):\/\/)(www\.|)" . $host . "/i";
	$src = preg_replace ($regex, '', $src);
	$src = htmlentities ($src);
    // remove slash from start of string
    if (strpos($src, '/') === 0) {
        $src = substr ($src, -(strlen($src) - 1));
    }
	return $src;
}

add_filter( 'wpsc_jpeg_quality', 'flexishop_jpeg_quality' );
function flexishop_jpeg_quality( $quality ) {
	return 90;
}

?>