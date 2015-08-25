<?php
// function to display additional images in the image gallery  
    global $wpsc_query, $wpdb;
    $siteurl = get_option('siteurl');
	  /* No GD? No gallery.  No gallery option? No gallery.  Range variable set?  Apparently, no gallery. */
    if(get_option('show_gallery') == 1 && !isset($_GET['range']) && function_exists("getimagesize")) {
		if ( (float)WPSC_VERSION < 3.8 ) {
        /* get data about the base product image */
        $product = $wpdb->get_row("SELECT * FROM `".WPSC_TABLE_PRODUCT_LIST."` WHERE `id`='".$product_id."' LIMIT 1",ARRAY_A);
        $image_link = WPSC_IMAGE_URL.$product['image']."";    
		$image_file_name = $product['image'];
        $imagepath = WPSC_THUMBNAIL_DIR.$image_file_name;
        $base_image_size = @getimagesize($imagepath);
        
        /* get data about the extra product images */
        $images = $wpdb->get_results("SELECT * FROM `".WPSC_TABLE_PRODUCT_IMAGES."` WHERE `product_id` = '$product_id' AND `id` NOT IN('$image_file_name')  ORDER BY `image_order` ASC",ARRAY_A);
		$new_width = get_option('single_view_image_width');
		$new_height = get_option('single_view_image_height');
		
					  ?>
					  	<div class="main_image">
						    <a rel="prettyPhoto" class="preview_link" href="<?php echo wpsc_the_product_image(); ?>" title="<?php echo str_replace(array(" ", '"',"'", '&quot;','&#039;'), array("_", "", "", "",''), wpsc_the_product_title()); ?>"><img src="<?php echo wpsc_the_product_image($new_width, $new_height); ?>" alt="" /></a><br class="clear" />
						</div>
						<div class="image_thumb">
						    <ul>
						    	<li>
						            <a href="<?php echo wpsc_the_product_image($new_width, $new_height); ?>" rel="main image"><img src="<?php echo wpsc_the_product_image(120, 109); ?>" alt="<?php echo wpsc_the_product_title(); ?>" /></a>
						        </li> 
						      	<?php 
						      	$count = 1;
						      	if(count($images) > 0) {
									if($images != null) {
								      	foreach($images as $image) {
								      			$extra_imagepath = WPSC_IMAGE_DIR.$image['image']."";    
												$extra_image_size = @getimagesize($extra_imagepath); 
												$thickbox_link = WPSC_IMAGE_URL.$image['image']."";
												$image_link = "index.php?image_id=".$image['id']."&amp;width=".$new_width."&amp;height=".$new_height.""; 
												$thumb_link = "index.php?image_id=".$image['id']."&amp;width=120&amp;height=109";   
												 ?>
								      			
												        <li <?php if(($count%4) == 3) : ?>class="col-right"<?php endif; ?>>
												            <a href="<?php echo $image_link ?>" rel="<?php echo $thickbox_link ?>"><img src="<?php echo $thumb_link ?>" alt="<?php echo $product_name ?>" /></a>
												        </li>    
										<?php   
										$count++; }
										
									}		
       							}
       							?>
							</ul>
						</div>  
						<?php
	}else {	
	//closes if < 3.8 condition
	$output = '';

	$product_name = $wpdb->get_var($wpdb->prepare("SELECT post_title FROM $wpdb->posts WHERE `ID`='".$product_id."' LIMIT 1"));
	$output .= "<div class='wpcart_gallery'>";
		$args = array(
						'post_type' => 'attachment',
						'post_parent' => $product_id,
						'post_mime_type' => 'image'
						); 
		$attachments = get_posts($args);
		$featured_img = get_post_meta($product_id, '_thumbnail_id');
		if ($attachments) {
			foreach ($attachments as $post) {
				if (in_array($post->ID, $featured_img))
					continue; 
				setup_postdata($post);
				$link = wp_get_attachment_link( $post->ID, array( get_option('wpsc_gallery_image_width'), get_option('wpsc_gallery_image_height') ) );
				$link = str_replace( 'a href' , 'a class="thickbox" rel="'.str_replace(array(" ", '"',"'", '&quot;','&#039;'), array("_", "", "", "",''), $product_name).'" href' , $link );
				$output .= $link;
			}
		}
				$output .= "</div>";
				wp_reset_query();
	
	}	//closes if > 3.8 condition
  }	//closes if gallery setting condition
?>

<script type="text/javascript"> 
jQuery(document).ready(function($) {
	var mainRel = $(".main_image a").attr('href');
 	$(".image_thumb ul li:first a").attr({ rel: mainRel});
	//Show Banner
	$(".main_image .block").animate({ opacity: 0.85 }, 1 ); //Set Opacity
 
	//Click and Hover events for thumbnail list
	$(".image_thumb ul li:first").addClass('active'); 
	$(".image_thumb ul li").click(function(){ 
		//Set Variables
		var imgAlt = $(this).find('img').attr("alt"); //Get Alt Tag of Image
		var imgTitle = $(this).find('a').attr("href"); //Get Main Image URL
		var imgRel = $(this).find('a').attr("rel");
		if ($(this).is(".active")) {  //If it's already active, then...
			return false; // Don't click through
		} else {
			//Animate the Teaser
			  var img = new Image();
			  $(img).load(function () {   
			      $(this).hide();
			      $(".main_image img").fadeOut();
			      $(".main_image a").html(this);
			      $(".main_image a").attr({ href: imgRel});
			      $(".main_image img").fadeIn();
			  }).attr({ src: imgTitle , alt: imgAlt});	
			// when the DOM is ready
			  		
		}
		
		$(".image_thumb ul li").removeClass('active'); //Remove class of 'active' on all lists
		$(this).addClass('active');  //add class of 'active' on this list only
		return false;
		
	}) .hover(function(){
		$(this).addClass('hover');
		}, function() {
		$(this).removeClass('hover');
	});
			
	//Toggle Teaser
	$("a.collapse").click(function(){
		$(".main_image .block").slideToggle();
		$("a.collapse").toggleClass("show");
	});
	
	//Enable thickbox
	
	
});//Close Function
</script> 