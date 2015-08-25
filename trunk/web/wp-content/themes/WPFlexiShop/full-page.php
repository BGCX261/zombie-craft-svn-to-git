<?php
/**
 * Template Name: Full Width
 */

get_header(); ?>

<div id="content-wrapper">
	<div id="main-content" class="container">	
		<div class="margin">
			<div id="full-col">
				<?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?>

							<?php the_content(); ?>
							<?php wp_link_pages( array( 'before' => '' . __( 'Pages:', 'flexishop' ), 'after' => '' ) ); ?>
							<?php edit_post_link( __( 'Edit', 'flexishop' ), '', '' ); ?>
	
				<?php endwhile; ?>
			</div>
		</div>
	</div>
</div>

<?php get_footer(); ?>