	<?php

	global $wpsc_query, $wpdb;
	$image_width = get_option('single_view_image_width');
	$image_height = get_option('single_view_image_height');
	$options = get_option('site_basic_options');
	
	$i = 0;
	$featured = $wpdb->get_row('SELECT id FROM '. WPSC_TABLE_PRODUCT_CATEGORIES . ' WHERE `nice-name` = "featured" LIMIT 1', ARRAY_A);
	if($featured != null) :
	$wpsc_query = new WPSC_Query(array('category_id' => $featured['id']));
	while (wpsc_have_products()) : wpsc_the_product(); $i++; ?>
		<?php if($i == 3) break; ?>
		<li class="feature-product">
		<?php if(wpsc_the_product_thumbnail()) :?>
		<div class="product-image">
			<a rel="<?php echo str_replace(array(" ", '"',"'", '&quot;','&#039;'), array("_", "", "", "",''), wpsc_the_product_title()); ?>" class="thickbox preview_link" href="<?php echo wpsc_the_product_image(); ?>">
				<img class="product_image" id="product_image_<?php echo wpsc_the_product_id(); ?>" alt="<?php echo wpsc_the_product_title(); ?>" title="<?php echo wpsc_the_product_title(); ?>" src="<?php if ($options['themelayout'] == 'boxed') : ?><?php bloginfo('template_url') ?>/timthumb.php?src=<?php echo wpsc_the_product_image($image_width, $image_height); ?>&w=457<?php else: ?><?php echo wpsc_the_product_image($image_width, $image_height); ?><?php endif; ?>" />
			</a>
		</div>
		<?php else: ?>
			<div class="product-image product-thumb item_no_image">
				<a href="<?php echo wpsc_the_product_permalink(); ?>">
				<span><?php _e( 'No Image Available', 'flexishop' ); ?></span>
				</a>
			</div>
		<?php endif; ?>
		<div class="product-content">
		<h2 class="prodtitles">
			<?php if(get_option('hide_name_link') == 1) : ?>
				<span><?php echo wpsc_the_product_title(); ?></span>
			<?php else: ?> 
				<a class="wpsc_product_title" href="<?php echo wpsc_the_product_permalink(); ?>"><?php echo wpsc_the_product_title(); ?></a>
			<?php endif; ?> 				
		</h2>
		<!-- <div class="wpsc_product_price">
		<?php if(wpsc_product_is_donation()) : ?>
			<label for='donation_price_<?php echo wpsc_the_product_id(); ?>'><?php echo __('Donation', 'flexishop' ); ?>:</label>
			<input type='text' id='donation_price_<?php echo wpsc_the_product_id(); ?>' name='donation_price' value='<?php echo $wpsc_query->product['price']; ?>' size='6' />
			<br />
		
		
		<?php else : ?>
			<?php if(wpsc_product_on_special()) : ?>
				<span class='oldprice'><?php echo __('Price', 'flexishop' ); ?>: <?php echo wpsc_product_normal_price(get_option('wpsc_hide_decimals')); ?></span><br />
			<?php endif; ?>
			<span id="product_price_<?php echo wpsc_the_product_id(); ?>" class="pricedisplay <?php if(wpsc_product_on_special()) echo "sale-price" ?>"><?php if(wpsc_product_on_special()) echo __( "Sale Price:", 'flexishop' ); ?> <?php echo wpsc_the_product_price(get_option('wpsc_hide_decimals')); ?></span>					
		<?php endif; ?>
		</div> -->
		<?php if(wpsc_the_product_additional_description()) : ?>
		<div class="description">
		<?php
			$value = '';
			$the_addl_desc = wpsc_the_product_additional_description();
			if( is_serialized($the_addl_desc) ) {
				$addl_descriptions = @unserialize($the_addl_desc);
			} else {
				$addl_descriptions = array('addl_desc', $the_addl_desc);
			}
			
			if( isset($addl_descriptions['addl_desc']) ) {
				$value = $addl_descriptions['addl_desc'];
			}

        	if( function_exists('wpsc_addl_desc_show') ) {
        		echo wpsc_addl_desc_show( $addl_descriptions );
        	} else {
							echo stripslashes( wpautop($the_addl_desc, $br=1));
        	}
        ?>
		</div>
		<?php endif; ?>
		<a href="<?php echo wpsc_the_product_permalink(); ?>" class="buy-now">Buy Now</a>
		</div>
	</li>
	<?php endwhile; ?>
	<?php endif; ?>