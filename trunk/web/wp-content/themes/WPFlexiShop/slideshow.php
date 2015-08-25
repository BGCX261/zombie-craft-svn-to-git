<?php
/**
 * Template Name: Slideshow
 
 */
 
 get_header();
 
	$options = get_option('site_basic_options');
?>

<div id="content-wrapper">
	<div id="main-content" class="container">	
		<div class="margin">
			<div id="main-col">
				<?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?>

							<?php the_content(); ?>
							<?php wp_link_pages( array( 'before' => '' . __( 'Pages:', 'flexishop' ), 'after' => '' ) ); ?>
							<?php edit_post_link( __( 'Edit', 'flexishop' ), '', '' ); ?>
	
				<?php endwhile; ?>
			</div>
			<div id="sidebar">
				<div class="sidebar-inner">
					<?php get_sidebar(); ?>
				</div>
			</div>
		</div>
	</div>
</div>

<?php get_footer(); ?>