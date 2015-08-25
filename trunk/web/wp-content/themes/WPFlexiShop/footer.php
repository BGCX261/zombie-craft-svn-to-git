<?php
	$options = get_option('site_basic_options');
	?>
<?php if (($options['footertop'] == '1') || ($options['footerbottom'] == '1')) : ?>
<div id="footer" class="container">
	<div class="margin">
		<?php if ($options['footertop'] == '1') : ?>
		<div id="footer-top">
			<?php if ( is_active_sidebar( 'blog-footer-top-widget-area' ) ) : ?>
					<div class="footer-top-left col-2">
						<div class="col-wrapper">
							<?php dynamic_sidebar( 'blog-footer-top-widget-area' ); ?>
						</div>
					</div>
			<?php else : ?>
			<div id="blog-panel" class="col-2">
				<div class="col-wrapper">
					<h3 class="widget-title"><?php _e( 'Latest News', 'flexishop' ); ?></h3>
					<ul class="blog-list">
						<?php query_posts('posts_per_page=1&post_type=post'); ?>
							<?php
					global $more;
					$more = 0;
					?>
						<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
						<li class="post">
							<div class="post-header">
								<a href="<?php the_permalink() ?>" rel="bookmark" class="thumbnail"><?php the_post_thumbnail(); ?></a>
								<h3><a href="<?php the_permalink() ?>" rel="bookmark"><?php the_title(); ?></a></h3>
								
								<div class="post-meta">
									<?php flexishop_posted_on(); ?>
								</div>
							</div>
							<div class="post-excerpt">
								<?php the_excerpt();?>
							</div>
						</li>
						<?php endwhile; endif; ?>
						<?php wp_reset_query(); ?>
					</ul>
				</div>
			</div>
			<?php endif; ?>
			<div class="footer-top-widgets col-2">
				<?php
					get_sidebar( 'footer-top' );
				?>
			</div>
			<br class="clear" />
		</div>
		<?php endif; ?>
		<?php if ($options['footerbottom'] == '1') : ?>
		<div id="footer-bottom">
			<?php
				get_sidebar( 'footer-bottom' );
			?>
			<br class="clear" />
		</div>
		<?php endif; ?>
	</div>
</div>
<?php endif; ?>
<div id="copyright" class="container">
	<div class="margin">
		<p>
		<?php $options = get_option('site_basic_options'); echo $options['copyright']; ?>
		</p>
	</div>
</div>
	<?php
	wp_footer();
?>
<script type="text/javascript">
	jQuery(document).ready(function($){
		$("a[rel^='prettyPhoto']").prettyPhoto({theme:'light_square'});
	});
</script>
</body>
</html>