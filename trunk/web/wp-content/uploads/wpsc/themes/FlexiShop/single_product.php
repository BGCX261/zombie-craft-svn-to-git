<?php
global $wpsc_query, $wpdb;
$image_width = get_option('single_view_image_width');
$image_height = get_option('single_view_image_height');
?>
<div id='products_page_container' class="wrap wpsc_container single_container">
	
	<div class="single-product_display_wrap">
	<?php /** start the product loop here, this is single products view, so there should be only one */?>
		<?php while (wpsc_have_products()) :  wpsc_the_product(); ?>
			<div class="single_product_display product_view_<?php echo wpsc_the_product_id(); ?>">
					<?php if(get_option('show_thumbnails')) :?>
					<?php
						if(function_exists('gold_shpcrt_display_gallery') && get_option('show_gallery') == 1 && !isset($_GET['range']) && function_exists("getimagesize")) : ?>
						<div class="single-imagecol">				
							<?php getProductGallery(wpsc_the_product_id()); ?>
							<?php if(wpsc_product_on_special()) : ?>
								<span class="sale-icon-single"><?php _e( 'Sale!', 'flexishop' ); ?></span>
							<?php endif; ?> 
						</div>
						
						<?php else:
					?>
					<div class="single-imagecol">
						<?php if(wpsc_the_product_thumbnail()) :?>
								<a rel="prettyPhoto" class="preview_link" href="<?php echo wpsc_the_product_image(); ?>" title="<?php echo str_replace(array(" ", '"',"'", '&quot;','&#039;'), array("_", "", "", "",''), wpsc_the_product_title()); ?>">
									<img class="product_image" id="product_image_<?php echo wpsc_the_product_id(); ?>" alt="<?php echo wpsc_the_product_title(); ?>" title="<?php echo wpsc_the_product_title(); ?>" src="<?php echo wpsc_the_product_image($image_width, $image_height); ?>" />
								</a>
						<?php else: ?> 
							<div class="item_no_image">
								<a href="<?php echo wpsc_the_product_permalink(); ?>">
								<span><?php _e( 'No Image Available', 'flexishop' ); ?></span>
								</a>
							</div>
						<?php endif; ?>
						<?php if(wpsc_product_on_special()) : ?>
							<span class="sale-icon-single"><?php _e( 'Sale!', 'flexishop' ); ?></span>
						<?php endif; ?> 
					</div>
					<?php endif; ?>
					<?php endif; ?> 
					
					<div class="single-producttext">
						<div class="wpsc_product_price">
							<?php if(wpsc_product_is_donation()) : ?>
								<label for='donation_price_<?php echo wpsc_the_product_id(); ?>'><?php echo __('Donation', 'flexishop'); ?>:</label>
								<input type='text' id='donation_price_<?php echo wpsc_the_product_id(); ?>' name='donation_price' value='<?php echo $wpsc_query->product['price']; ?>' size='6' />
								<br />
							
							
							<?php else : ?>	
								<?php if(wpsc_product_on_special()) : ?>
									<span class='oldprice'><?php echo __('Price', 'flexishop'); ?>: <?php echo wpsc_product_normal_price(); ?></span><br />
                                    <h3 class="sale-price-header"><?php _e( 'On Sale:', 'flexishop' ); ?>  
                                    <span id="product_price_<?php echo wpsc_the_product_id(); ?>" class="pricedisplay sale-price">
									<?php echo wpsc_the_product_price(); ?></span>
                                    </h3>
								<?php else : ?>
                                    <h3 class=""><?php _e( 'Price:', 'flexishop' ); ?> 
                                    <span id="product_price_<?php echo wpsc_the_product_id(); ?>" class="pricedisplay">
									<?php echo wpsc_the_product_price(); ?></span>
                                    </h3>
								<?php endif; ?>
								<!-- multi currency code -->
								<?php if(wpsc_product_has_multicurrency()) : ?>
								<?php echo wpsc_display_product_multicurrency(); ?>
								<?php endif; ?>
								<!-- end multi currency code -->
														
							<?php endif; ?>
						</div>
							<?php				
								do_action('wpsc_product_before_description', wpsc_the_product_id(), $wpsc_query->product);
							?>
						<div class="wpsc_description"><?php echo wpsc_the_product_description(); ?></div>
		
						<?php
							do_action('wpsc_product_addons', wpsc_the_product_id());
						?>
						<?php if(wpsc_the_product_additional_description()) : ?>
						<div class="single_additional_description">
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
				
					<?php do_action('wpsc_product_addon_after_descr', wpsc_the_product_id()); ?>

					<?php /** the custom meta HTML and loop */ ?>
					<div class="custom_meta">
						<?php while (wpsc_have_custom_meta()) : wpsc_the_custom_meta(); 	
								if (stripos(wpsc_custom_meta_name(),'g:') !== FALSE){
									continue;
								}
							?>
							<strong><?php echo wpsc_custom_meta_name(); ?>: </strong><?php echo wpsc_custom_meta_value(); ?><br />
						<?php endwhile; ?>
					</div>
					<?php /** the custom meta HTML and loop ends here */?>
					
					
					<form class='product_form' enctype="multipart/form-data" action="<?php echo wpsc_this_page_url(); ?>" method="post" name="1" id="product_<?php echo wpsc_the_product_id(); ?>">
					<?php if(wpsc_product_has_personal_text()) : ?>
						<div class='custom_text'>
							<h4><?php echo __('Personalize your product', 'flexishop'); ?></h4>
							<?php echo __('Complete this form to include a personalized message with your purchase.', 'flexishop'); ?><br />
							<input type='text' name='custom_text' value=''  />
						</div>
					<?php endif; ?>
					
					<?php if(wpsc_product_has_supplied_file()) : ?>
						<div class='custom_file'>
							<h4><?php echo __('Upload a File', 'flexishop'); ?></h4>
							<?php echo __('Select a file from your computer to include with this purchase.  ', 'flexishop'); ?><br />
							<input type='file' name='custom_file' value=''  />
						</div>
					<?php endif; ?>
					<?php if((wpsc_has_multi_adding()) || (wpsc_have_variation_groups()) || (function_exists('wpsc_akst_share_link') && (get_option('wpsc_share_this') == 1))) : ?>
					<div class="single-product-meta">
						<?php /** the variation group HTML and loop */?>
						<div class="wpsc_variation_forms">
							<ul class="variation-groups">
							<?php while (wpsc_have_variation_groups()) : wpsc_the_variation_group(); ?>
								<li>
									<label for="<?php echo wpsc_vargrp_form_id(); ?>"><?php echo wpsc_the_vargrp_name(); ?>:</label>
									<?php /** the variation HTML and loop */?>
									<select class='wpsc_select_variation' name="variation[<?php echo wpsc_vargrp_id(); ?>]" id="<?php echo wpsc_vargrp_form_id(); ?>">
									<?php while (wpsc_have_variations()) : wpsc_the_variation(); ?>
										<option value="<?php echo wpsc_the_variation_id(); ?>" <?php echo wpsc_the_variation_out_of_stock(); ?>><?php echo wpsc_the_variation_name(); ?></option>
									<?php endwhile; ?>
									</select> 
								</li>
							<?php endwhile; ?>
							</ul>
						</div>
						<?php /** the variation group HTML and loop ends here */?>
										
						<div class="quantity-meta">
						<!-- THIS IS THE QUANTITY OPTION MUST BE ENABLED FROM ADMIN SETTINGS -->
							<?php if(wpsc_has_multi_adding()): ?>
								<fieldset>
								<label class='wpsc_quantity_update' for='wpsc_quantity_update[<?php echo wpsc_the_product_id(); ?>]'><?php echo __('Quantity', 'flexishop'); ?>:</label>
								
								<input type="text" id='wpsc_quantity_update' name="wpsc_quantity_update[<?php echo wpsc_the_product_id(); ?>]" size="2" value="1"/>
								<input type="hidden" name="key" value="<?php echo wpsc_the_cart_item_key(); ?>"/>
								<input type="hidden" name="wpsc_update_quantity" value="true"/>
								</fieldset>
							<?php endif ;?>
						</div>
						
						<?php if(function_exists('wpsc_akst_share_link') && (get_option('wpsc_share_this') == 1)) {
							echo wpsc_akst_share_link('return');
						} ?>
					</div>
					<?php endif; ?>
					<input type="hidden" value="add_to_cart" name="wpsc_ajax_action"/>
					<input type="hidden" value="<?php echo wpsc_the_product_id(); ?>" name="product_id"/>
							
					<?php if(wpsc_product_is_customisable()) : ?>				
						<input type="hidden" value="true" name="is_customisable"/>
					<?php endif; ?>
					
					
					<!-- END OF QUANTITY OPTION -->
					<?php if((get_option('hide_addtocart_button') == 0) && (get_option('addtocart_or_buynow') !='1')) : ?>
						<?php if(wpsc_product_has_stock()) : ?>
							<?php if(wpsc_product_external_link(wpsc_the_product_id()) != '') : ?>
										<?php	$action =  wpsc_product_external_link(wpsc_the_product_id()); ?>
										<input class="wpsc_buy_button" type='button' value='<?php echo __('Buy Now', 'flexishop'); ?>' onclick='gotoexternallink("<?php echo $action; ?>")'>
										<?php else: ?>
									<input type="submit" value="<?php echo __('Add To Cart', 'flexishop'); ?>" name="Buy" class="wpsc_buy_button" id="product_<?php echo wpsc_the_product_id(); ?>_submit_button"/>
										<?php endif; ?>
							
							<div class='wpsc_loading_animation'>
								<img title="Loading" alt="Loading" src="<?php echo WPSC_URL ;?>/images/indicator.gif" class="loadingimage" />
								<?php echo __('Updating cart...', 'flexishop'); ?>
							</div>
							
						<?php else : ?>
							<p class='soldout'><?php echo __('This product has sold out.', 'flexishop'); ?></p>
						<?php endif ; ?>
					<?php endif ; ?>
					<?php if(get_option('display_pnp') == 1) : ?>
						<h4 class="shipping"><?php echo __('Shipping', 'flexishop'); ?>: <?php echo wpsc_product_postage_and_packaging(); ?></h4>
					<?php endif; ?>
					</form>
					
					<?php if((get_option('hide_addtocart_button') == 0) && (get_option('addtocart_or_buynow')=='1')) : ?>
						<?php echo wpsc_buy_now_button(wpsc_the_product_id()); ?>
					<?php endif ; ?>
					
					<?php echo wpsc_product_rater(); ?>
					</div>
		
					<form onsubmit="submitform(this);return false;" action="<?php echo wpsc_this_page_url(); ?>" method="post" name="product_<?php echo wpsc_the_product_id(); ?>" id="product_extra_<?php echo wpsc_the_product_id(); ?>">
						<input type="hidden" value="<?php echo wpsc_the_product_id(); ?>" name="prodid"/>
						<input type="hidden" value="<?php echo wpsc_the_product_id(); ?>" name="item"/>
					</form>
				</div>
		</div>
		
		<?php echo wpsc_product_comments(); ?>
<?php endwhile; ?>
<?php /** end the product loop here */?>

		<?php
		if(function_exists('fancy_notifications')) {
			echo fancy_notifications();
		}
		?>
	<br class="clear" />

</div>