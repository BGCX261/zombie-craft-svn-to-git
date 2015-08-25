<?php

get_header(); ?>

<div id="content-wrapper">
	<div id="main-content" class="container">	
		<div class="margin">
			<div id="main-col">
				
				<?php if ( have_posts() ) while ( have_posts() ) : the_post(); ?>	
						<div id="single-post">	
							<div class="post-meta">
								<p><?php _e( 'Posted on', 'flexishop' ); ?> <?php the_time(__( 'F j, Y', 'flexishop' )); ?></p>
							</div>
							<?php if(has_post_thumbnail()) { ?>
							<?php $large_image_url = wp_get_attachment_image_src( get_post_thumbnail_id($post->ID), 'large'); ?>
							<a rel="prettyPhoto" href="<?php echo $large_image_url[0]; ?>" title="<?php the_title_attribute(); ?>" class="large-blog-image"><?php the_post_thumbnail( 'blog' ); ?></a>
							<?php } ?>	

							<div class="post-content">
							<?php the_content(); ?>
							</div>
							<?php wp_link_pages( array( 'before' => '' . __( 'Pages:', 'flexishop' ), 'after' => '' ) ); ?>
							<?php edit_post_link( __( 'Edit', 'flexishop' ), '', '' ); ?>
						</div>
					<?php comments_template( '', true ); ?>
	
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