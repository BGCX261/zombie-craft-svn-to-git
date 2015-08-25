<?php
		global $wpdb;
		$sql = "select prodid, count(prodid) as prodnum from " . $wpdb->prefix. "wpsc_cart_contents group by prodid order by prodnum desc LIMIT 5";
			$best_sellers_list = $wpdb->get_results($sql,ARRAY_A);			
			
			echo "<ul class='best-seller-list'>";
			foreach((array)$best_sellers_list as $i => $best_sellers) {
  			$sql="SELECT list.id,list.name,list.price,image.image,list.special,list.special_price
        FROM ".$wpdb->prefix."wpsc_product_list AS list
        LEFT JOIN ".$wpdb->prefix."wpsc_product_images AS image
        ON list.image=image.id WHERE list.active = '1' AND list.id=" . $best_sellers['prodid'];
  			
  			$product_list = $wpdb->get_results($sql,ARRAY_A);				
  			$product = $product_list[0];
  			if(!$product['image']) $image = " no-image"; else $image = " yes-image";
  			if($i==4) $rightCol = " col-right";
			echo "<li class='best-seller" . $image . $rightCol . "'>";
			if($product['price'] - $product['special_price'] > 0 && $product['special_price'] > 0) : ?><span class="sale-icon">Sale!</span><?php endif; ?>
			<?php echo "<div class='padding'>";
  			if($product['image']):
				$theSrc = get_timthumb_src( WPSC_THUMBNAIL_URL.$product['image'] ); 
  				if($options['themelayout'] == 'boxed'):
		  			$output = "<img src='" . get_bloginfo('template_url') . "/timthumb.php?src=".$theSrc."&w=174&q=100' title='".$product['name']."' alt='".$product['name']."' />";
		  		else:
		  			$output = "<img src='" . get_bloginfo('template_url') . "/timthumb.php?src=".$theSrc."&w=186&q=100' title='".$product['name']."' alt='".$product['name']."' />";
		  		endif;
  			else :
  			$output = __( "No Image Available", 'flexishop' );
  			endif;
  			echo '<div class="imagecol"><a href="' . wpsc_product_url($product['id']) . '">' . $output . '</a></div>';
  			echo "<div class='producttext'><h3><a href='" . wpsc_product_url($product['id']) . "'>" . stripslashes($product['name']) . "</a></h3></div>";
  			echo "</div></li>";  				  	
			}	
			echo "</ul>";
			
?>