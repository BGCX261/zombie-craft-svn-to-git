					<?php $options = get_option('site_basic_options'); ?>
					<?php $promotion_query = new WP_Query('posts_per_page=3&post_type=promotion'); ?>
					<?php if ($promotion_query->have_posts()) : while ($promotion_query->have_posts()) : $promotion_query->the_post(); ?>
					<?php $postid = get_the_ID(); ?>
					<?php 
						$thumb = get_the_post_thumbnail($postid, 'promotion');
						$pattern= "/(?<=src=['|\"])[^'|\"]*?(?=['|\"])/i";
						preg_match($pattern, $thumb, $thePath);
						$theSrc = $thePath[0];
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
								<?php the_content(__( 'Read more', 'flexishop' ));?>
							</div>
						</div>
						<div class="promotion-header">
							<a href="<?php echo $link ?>" title="<?php the_title(); ?>"><?php if ($options['themelayout'] == 'boxed') : ?><img src="<?php bloginfo('template_url') ?>/timthumb.php?src=<?php echo $theSrc ?>&w=896" alt=""><?php else: ?><?php the_post_thumbnail( 'promotion' ); ?><?php endif; ?></a>
						</div>
					</li>
					<?php endwhile; endif; ?>