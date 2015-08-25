<div id="featured-slider">
	<div id="feature-wrapper">
	<div id="features">
		<ul class="feature-list">
					<?php $products = array(); $promotions = array(); $posts = array(); ?>
					<?php $options = get_option('site_basic_options'); ?>
					<?php $slider_query = new WP_Query('post_type=slider'); ?>
					<?php if ($slider_query->have_posts()) : while ($slider_query->have_posts()) : $slider_query->the_post();
						$postid = get_the_ID();
						$custom = get_post_custom($postid); 
						$slider_type = $custom["sliderType"][0];
						if ($slider_type == 'product'){
							$slider_id = $custom["sliderProductId"][0];
							getSliderProducts($slider_id);
						}
						elseif ($slider_type == 'news'){
							$slider_id = $custom["sliderPostId"][0];
							getSliderPosts($slider_id);
						}
						elseif ($slider_type == 'promotion'){
							$slider_id = $custom["sliderPromotionId"][0];
							getSliderPromotions($slider_id);
						}
					endwhile; endif;
					?>
		</ul>
	</div>
	
	<!-- <div class="feature-nav">
		<a href="#" class="previous">Previous</a>
		<a href="#" class="next">Next</a>
	</div> -->
	</div>
</div>
<div id="slider-controls"></div>
