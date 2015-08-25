<?php
/**
 * Template Name: Checkout
 
 */
 
 get_header();
?>
<div id="content-wrapper">
	<div id="checkout" class="row container">
		<div class="margin">
			<?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?>			
						<?php the_content(); ?>
			<?php endwhile; ?>
		</div>
	</div>
</div>

<?php get_footer(); ?>