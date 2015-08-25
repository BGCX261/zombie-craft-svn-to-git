<?php
		global $wpdb;
		$options = get_option('site_basic_options');
		$sql ="SELECT list.id,list.name,list.price,image.image,list.special,list.special_price, list.date_added FROM ".$wpdb->prefix."wpsc_product_list AS list LEFT JOIN ".$wpdb->prefix."wpsc_product_images AS image ON list.image=image.id WHERE list.active = '1' ORDER BY list.date_added DESC LIMIT 5";
			$latestproducts = $wpdb->get_results($sql,ARRAY_A);			
			
			echo "<ul class='latest-product-list'>";
			foreach((array)$latestproducts as $i => $latest_product) {
  			if(!$latest_product['image']) $image = " no-image"; else $image = " yes-image";
  			if($i==4) $rightCol = " col-right";
			echo "<li class='latest-product" . $image . $rightCol . "'>";
			if($latest_product['price'] - $latest_product['special_price'] > 0 && $latest_product['special_price'] > 0) : ?><span class="sale-icon"><?php _e( 'Sale!', 'flexishop' ); ?></span><?php endif; ?>
			<?php echo "<div class='padding'>";
  			if($latest_product['image']):
				$theSrc = get_timthumb_src( WPSC_THUMBNAIL_URL.$latest_product['image'] ); 
	  			if($options['themelayout'] == 'boxed'):
		  			$output = "<img src='" . get_bloginfo('template_url') . "/timthumb.php?src=".$theSrc."&w=174&q=100' title='".$latest_product['name']."' alt='".$latest_product['name']."' />";
		  		else:
		  			$output = "<img src='" . get_bloginfo('template_url') . "/timthumb.php?src=".$theSrc."&w=186&q=100' title='".$latest_product['name']."' alt='".$latest_product['name']."' />";
		  		endif;
  			else :
  			$output = __( "No Image Available", 'flexishop' );
  			endif;
  			echo '<div class="imagecol"><a href="' . wpsc_product_url($latest_product['id']) . '">' . $output . '</a></div>';
  			echo "<div class='producttext'><h3><a href='" . wpsc_product_url($latest_product['id']) . "'>" . stripslashes($$latest_product['name']) . "</a></h3></div>";
  			echo "</div></li>";  				  	
			}	
			echo "</ul>";
			
?>