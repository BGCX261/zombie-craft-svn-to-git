					<ul class="testimonials">
					<?php $testimonial_query = new WP_Query('posts_per_page=3&post_type=testimonial'); ?>
					<?php if ($testimonial_query->have_posts()) : while ($testimonial_query->have_posts()) : $testimonial_query->the_post(); ?>
					<li class="testimonial">
						<h3><?php the_title(); ?></h3>
						<div class="testimonial-content">
							<?php the_content(__( 'Read more', 'flexishop' ));?>
						</div>
						<div class="testimonial-meta">
						<?php $postid = get_the_ID(); ?>
						<?php $custom = get_post_custom($postid);
  							$author = $custom["testimonial_author"][0]; ?>
							<span class="testimonial_author"><?php echo $author ?></span>
						</div>
					</li>
					<?php endwhile; endif; ?>
					</ul>