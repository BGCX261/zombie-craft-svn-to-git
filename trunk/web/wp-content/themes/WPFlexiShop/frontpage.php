<?php
/**
 * Template Name: Frontpage
 
 */
 
 get_header();
 
	$options = get_option('site_basic_options');
?>
<div id="content-wrapper">
	<?php if(!empty($options['sitedesc'])) : ?>
	<div id="brief" class="container">
		<div class="margin">
			<p>
				<?php echo $options['sitedesc']; ?></p>
		</div>
	</div>
	<?php endif; ?>
	<?php if (is_active_sidebar( 'home-left'  ) || is_active_sidebar( 'home-middle' ) || is_active_sidebar( 'home-right'  )) : ?>
	<div id="front-content">
		<div class="margin">
		<?php if ( is_active_sidebar( 'home-left' ) ) : ?>
			<div class="col-3">
				<?php dynamic_sidebar( 'home-left' ); ?>
			</div>
		<?php endif; ?>
		<?php if ( is_active_sidebar( 'home-middle' ) ) : ?>
			<div class="col-3">
				<?php dynamic_sidebar( 'home-middle' ); ?>
			</div>
		<?php endif; ?>
		<?php if ( is_active_sidebar( 'home-right' ) ) : ?>
			<div class="col-3 col-right">
				<?php dynamic_sidebar( 'home-right' ); ?>
			</div>
		<?php endif; ?>
		</div>
	</div>	
	<?php endif; ?>
	<div id="store-panel" class="row container">
		<div class="margin">
			<?php if ($options['homepagecategories'] == '1') : ?>
			<div class="front-categories front-panel">
				<h2><?php _e( 'Categories', 'flexishop' ); ?></h2>
				<?php getFrontCategories() ?>
				<br class="clear" />
			</div>
			<?php endif; ?>
			<?php if ($options['homepagebestsellers'] == '1') : ?>
			<div class="best-sellers front-panel">
				<h2><?php _e( 'Best Sellers', 'flexishop' ); ?></h2>
				<?php getBestSellers() ?>
				<br class="clear" />
			</div>
			<?php endif; ?>
			<?php if ($options['homepagelatestproducts'] == '1') : ?>
			<div class="latest-products front-panel">
				<h2><?php _e( 'Latest Products', 'flexishop' ); ?></h2>
				<?php getLatestProducts() ?>
				<br class="clear" />
			</div>
			<?php endif; ?>
		</div>
	</div>
</div>

<?php get_footer(); ?>