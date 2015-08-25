<?php $options = get_option('site_basic_options'); ?>
<?php $feature_query = new WP_Query('posts_per_page=2&post_type=post&category_name=featured'); ?>
<?php if ($feature_query->have_posts()) : while ($feature_query->have_posts()) : $feature_query->the_post(); ?>
<?php $postid = get_the_ID(); ?>
<li class="feature">
	<div class="post-background">
		<?php 
			$thumb = get_the_post_thumbnail($postid, 'promotion');
			$pattern= "/(?<=src=['|\"])[^'|\"]*?(?=['|\"])/i";
			preg_match($pattern, $thumb, $thePath);
			$theSrc = $thePath[0];
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
<?php endwhile; endif; ?>