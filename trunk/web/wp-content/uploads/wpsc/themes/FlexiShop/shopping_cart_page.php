<div id="checkout-slider-mask">
	<div id="checkout-progress" class="wpsc_checkout_forms">
		<h4><?php _e( 'Checkout Progress', 'flexishop' ); ?></h4>
		<div id="checkout-bar-out">
			<div id="checkout-bar-in"></div>
		</div>
	</div>
<div id="checkout-slider">
<?php
global $wpsc_cart, $wpdb, $wpsc_checkout, $wpsc_gateway, $wpsc_coupons;
$wpsc_checkout = new wpsc_checkout();
$wpsc_gateway = new wpsc_gateways();
$wpsc_coupons = new wpsc_coupons($_SESSION['coupon_numbers']);
 //echo "<pre>".print_r($wpsc_cart, true)."</pre>";
// //echo "<pre>".print_r($wpsc_checkout, true)."</pre>";
if(wpsc_cart_item_count() > 0) :
?>
<div id="shopping-cart" class="wpsc_checkout_forms">
<h2 class="review-order"><?php echo __('Please review your order', 'flexishop'); ?></h2>
<table class="productcart">
	<tr class="firstrow">
		<td class='firstcol'></td>
		<td><?php echo __('Product', 'flexishop'); ?>:</td>
		<td><?php echo __('Quantity', 'flexishop'); ?>:</td>
		<?php if(wpsc_uses_shipping()): ?>
			<td><?php echo __('Shipping', 'flexishop'); ?>:</td>
		<?php endif; ?>
		<td><?php echo __('Price', 'flexishop'); ?>:</td>

		<td></td>
	</tr>
	<?php while (wpsc_have_cart_items()) : wpsc_the_cart_item(); ?>
	
	<?php  //this displays the confirm your order html	?>
		
		<tr class="product_row">
			<td class="firstcol"><img src='<?php echo wpsc_cart_item_image(48,48); ?>' alt='<?php echo wpsc_cart_item_name(); ?>' title='<?php echo wpsc_cart_item_name(); ?>' /></td>
			<td class="firstcol">
			<a href='<?php echo wpsc_cart_item_url();?>'><?php echo wpsc_cart_item_name(); ?></a>
			</td>
			<td>
				<form action="<?php echo get_option('shopping_cart_url'); ?>" method="post" class="adjustform">
					<input type="text" name="quantity" size="2" value="<?php echo wpsc_cart_item_quantity(); ?>" />
					<input type="hidden" name="key" value="<?php echo wpsc_the_cart_item_key(); ?>" />
					<input type="hidden" name="wpsc_update_quantity" value="true" />
					<input type="submit" value="<?php echo __('Update', 'flexishop'); ?>" name="submit" class="regular-button" />
				</form>
			</td>
			<?php if(wpsc_uses_shipping()): ?>
			<td><span class="pricedisplay" id='shipping_<?php echo wpsc_the_cart_item_key(); ?>'><?php echo wpsc_cart_item_shipping(); ?></span></td>
			<?php endif; ?>
			<td><span class="pricedisplay"><?php echo wpsc_cart_item_price(); ?></span></td>
			
			<td>
				<form action="<?php echo get_option('shopping_cart_url'); ?>" method="post" class="adjustform">
					<input type="hidden" name="quantity" value="0" />
					<input type="hidden" name="key" value="<?php echo wpsc_the_cart_item_key(); ?>" />
					<input type="hidden" name="wpsc_update_quantity" value="true" />
					<button class='remove_button' type="submit"><span><?php echo __('Remove', 'flexishop'); ?></span></button>
				</form>
			</td>
		</tr>
	<?php endwhile; ?>
	<?php //this HTML displays coupons if there are any active coupons to use ?>
	<?php if(wpsc_uses_coupons()): ?>
		
		<?php if(wpsc_coupons_error()): ?>
			<tr><td><?php echo __('Coupon is not valid.', 'flexishop'); ?></td></tr>
		<?php endif; ?>
		<tr>
			<td colspan="2"><?php _e('Enter your coupon number'); ?> :</td>
			<td  colspan="3" align='left'>
				<form  method='post' action="<?php echo get_option('shopping_cart_url'); ?>">
					<input type='text' name='coupon_num' id='coupon_num' value='<?php echo $wpsc_cart->coupons_name; ?>' />
					<input type='submit' value='<?php echo __('Update', 'flexishop') ?>' />
				</form>
			</td>
		</tr>
	<?php endif; ?>	
	</table>
	<?php  //this HTML dispalys the calculate your order HTML	?>

	<?php if(isset($_SESSION['nocamsg']) && isset($_GET['noca']) && $_GET['noca'] == 'confirm'): ?>
		<p class='validation-error'><?php echo $_SESSION['nocamsg']; ?></p>
	<?php endif; ?>
	<?php if($_SESSION['categoryAndShippingCountryConflict'] != '') : ?>
		<p class='validation-error'><?php echo $_SESSION['categoryAndShippingCountryConflict']; ?></p>
	<?php
		$_SESSION['categoryAndShippingCountryConflict'] = '';
	endif;
	
	if($_SESSION['WpscGatewayErrorMessage'] != '') :
	?>
		<p class='validation-error'><?php echo $_SESSION['WpscGatewayErrorMessage']; ?></p>
	<?php
	endif;
	?>
	<?php do_action('wpsc_before_shipping_of_shopping_cart'); ?>
	<div id='totals'>
	<?php if(wpsc_uses_shipping()  && wpsc_has_shipping_form()): ?>
		<h3><?php echo __('Calculate Shipping Price', 'flexishop'); ?></h3>
		<?php echo __('Please choose a country below to calculate your shipping costs', 'flexishop'); ?>
		<table class="productcart">
			<?php if (!wpsc_have_shipping_quote()) : // No valid shipping quotes ?>
				<?php if (($_SESSION['wpsc_zipcode'] == '') || ($_SESSION['wpsc_zipcode'] == 'Your Zipcode')) : // No valid shipping quotes ?>
					<?php if ($_SESSION['wpsc_update_location'] == true) :?>
						<tr>
							<td colspan='5' class='shipping_error' >
								<?php echo __('Please provide a Zipcode and click Calculate in order to continue.', 'flexishop'); ?>
							</td>
						</tr>
					<?php endif; ?>
				<?php else: ?>
					<tr>
						<td colspan='5' class='shipping_error' >
							<?php echo __('Sorry, online ordering is unavailable to this destination and/or weight. Please double check your destination details.', 'flexishop'); ?>
						</td>
					</tr>
				<?php endif; ?>
			<?php endif; ?>
			<tr>
				<td colspan='5'>
					<form name='change_country' id='change_country' action='' method='post'>
						<?php echo wpsc_shipping_country_list();?>
						<input type='hidden' name='wpsc_update_location' value='true' />
						<input type='submit' name='wpsc_submit_zipcode' value='Calculate' class="regular-button" />
					</form>
				</td>
			</tr>
			
			<?php if (wpsc_have_morethanone_shipping_quote()) :?>
				<?php while (wpsc_have_shipping_methods()) : wpsc_the_shipping_method(); ?>
						<?php 	if (!wpsc_have_shipping_quotes()) { continue; } // Don't display shipping method if it doesn't have at least one quote ?>
						<tr><td class='shipping_header' colspan='5'><?php echo wpsc_shipping_method_name().' - '.__('Choose a Shipping Rate', 'flexishop'); ?> </td></tr>
						<?php while (wpsc_have_shipping_quotes()) : wpsc_the_shipping_quote();	?>
							<tr>
								<td colspan='3'>
									<label for='<?php echo wpsc_shipping_quote_html_id(); ?>'><?php echo wpsc_shipping_quote_name(); ?></label>
								</td>
								<td style='text-align:center;'>
									<label for='<?php echo wpsc_shipping_quote_html_id(); ?>'><?php echo wpsc_shipping_quote_value(); ?></label>
								</td>
								<td style='text-align:center;'>
									<?php if(wpsc_have_morethanone_shipping_methods_and_quotes()): ?>
										<input type='radio' id='<?php echo wpsc_shipping_quote_html_id(); ?>' <?php echo wpsc_shipping_quote_selected_state(); ?>  onclick='switchmethod("<?php echo wpsc_shipping_quote_name(); ?>", "<?php echo wpsc_shipping_method_internal_name(); ?>")' value='<?php echo wpsc_shipping_quote_value(true); ?>' name='shipping_method' />
									<?php else: ?>
										<input <?php echo wpsc_shipping_quote_selected_state(); ?> disabled='disabled' type='radio' id='<?php echo wpsc_shipping_quote_html_id(); ?>'  value='<?php echo wpsc_shipping_quote_value(true); ?>' name='shipping_method' />
											<?php wpsc_update_shipping_single_method(); ?>
									<?php endif; ?>
								</td>
							</tr>
						<?php endwhile; ?>
				<?php endwhile; ?>
			<?php endif; ?>
			
			<?php wpsc_update_shipping_multiple_methods(); ?>

			
			<?php if (!wpsc_have_shipping_quote()) : // No valid shipping quotes ?>
					</table>
					</div>
				<?php return; ?>
			<?php endif; ?>
		</table>
	<?php endif;  ?>
	
	<table class="productcart">
	<?php if(wpsc_cart_tax(false) > 0) : ?>
		<tr class="total_price total_tax">
			<td colspan="3">
				<?php echo wpsc_display_tax_label(true); ?>

			</td>
			<td colspan="2">
				<span id="checkout_tax" class="pricedisplay checkout-tax"><?php echo wpsc_cart_tax(); ?></span>
			</td>
		</tr>
	<?php endif; ?>
	
	<?php if(wpsc_uses_shipping()) : ?>
		<tr class="total_price total_shipping">
			<td colspan="3">
				<?php echo __('Total Shipping', 'flexishop'); ?>
			</td>
			<td colspan="2">
				<span id="checkout_shipping" class="pricedisplay checkout-shipping"><?php echo wpsc_cart_shipping(); ?></span>
				</td>
		</tr>
	<?php endif; ?>

	  <?php if(wpsc_uses_coupons() && (wpsc_coupon_amount(false) > 0)): ?>
	<tr class="total_price">
		<td colspan="3">
			<?php echo __('Discount', 'flexishop'); ?>
		</td>
		<td colspan="2">
			<span id="coupons_amount" class="pricedisplay"><?php echo wpsc_coupon_amount(); ?></span>
	    </td>
   	</tr>
	  <?php endif ?>

		
	
	<tr class='total_price'>
		<td colspan='3'>
		<?php echo __('Total Price', 'flexishop'); ?>
		</td>
		<td colspan='2'>
			<span id='checkout_total' class="pricedisplay checkout-total"><?php echo wpsc_cart_total(); ?></span>
		</td>
	</tr>
	
	</table>
	</div>
	<a href="#checkout-step-2" id="checkout-next"><?php _e( 'Next', 'flexishop' ); ?></a>
</div>
		<?php do_action('wpsc_before_form_of_shopping_cart'); ?>
	<div id="shopping-cart-form" class="wpsc_checkout_forms">
	<form name='wpsc_checkout_forms' class='wpsc_checkout_forms' action='' method='post' enctype="multipart/form-data">
	
	   <?php 
	   /**  
	    * Both the registration forms and the checkout details forms must be in the same form element as they are submitted together, you cannot have two form elements submit together without the use of JavaScript.
	   */
	   ?>

	 <?php if(!is_user_logged_in() && get_option('users_can_register') && get_option('require_register')) :
			 global $current_user;
    		 get_currentuserinfo();	  ?>
		<h2><?php _e( 'Not yet a member?', 'flexishop' );?></h2>
		<p><?php _e( 'In order to buy from us, you\'ll need an account. Joining is free and easy. All you need is a username, password and valid email address.', 'flexishop' );?></p>
		<?php	if(count($_SESSION['wpsc_checkout_user_error_messages']) > 0) : ?>
			<div class="login_error"> 
				<?php		  
				foreach($_SESSION['wpsc_checkout_user_error_messages'] as $user_error ) {
				  echo $user_error."<br />\n";
				}
				$_SESSION['wpsc_checkout_user_error_messages'] = array();
				?>			
		  </div>
		<?php endif; ?>
		
		
	  <fieldset class='wpsc_registration_form'>
			<label><?php _e( 'Username', 'flexishop' ); ?>:</label><input type="text" name="log" id="log" value="" size="20"/>
			<label><?php _e( 'Password', 'flexishop' ); ?>:</label><input type="password" name="pwd" id="pwd" value="" size="20" />
			<label><?php _e( 'E-mail', 'flexishop' ); ?>:</label><input type="text" name="user_email" id="user_email" value="<?php echo attribute_escape(stripslashes($user_email)); ?>" size="20" />
		</fieldset>
	<?php endif; ?>

	<h2 class="checking-out"><?php echo __('Ready To Check Out?', 'flexishop'); ?></h2>
	<?php/* echo __('Note, Once you press submit, you will need to have your Credit card handy.', 'flexishop'); <br /> */?>
	<p class="required"><?php echo __('Fields marked with an asterisk must be filled in.', 'flexishop'); ?></p>
	<?php
	  if(count($_SESSION['wpsc_checkout_misc_error_messages']) > 0) {
			echo "<div class='login_error'>\n\r";
			foreach((array)$_SESSION['wpsc_checkout_misc_error_messages'] as $user_error ) {
				echo $user_error."<br />\n";
			}
			echo "</div>\n\r";
		}
		$_SESSION['wpsc_checkout_misc_error_messages'] =array();
	?>
	<table class='wpsc_checkout_table'>
		<?php while (wpsc_have_checkout_items()) : wpsc_the_checkout_item(); ?>
			<?php if(wpsc_is_shipping_details()) : ?>
					<tr>
						<td colspan ='2'>
							<br />
							<input type='checkbox' value='true' name='shippingSameBilling' id='shippingSameBilling' />
							<label for='shippingSameBilling'><?php _e( 'Shipping Address same as Billing Address?', 'flexishop' ); ?></label>
						
						</td>
					</tr>
			<?php endif; ?>

		  <?php if(wpsc_checkout_form_is_header() == true) : ?>
		  		<tr <?php echo wpsc_the_checkout_item_error_class();?>>
			<td <?php if(wpsc_is_shipping_details()) echo "class='wpsc_shipping_forms'"; ?> colspan='2'>
				<h4>
					<?php echo wpsc_checkout_form_name();?>
				</h4>
			</td>
				</tr>
		  <?php else: ?>
		  <?php if((!wpsc_uses_shipping()) && $wpsc_checkout->checkout_item->unique_name == 'shippingstate'): ?>
		  <?php else : ?>
		  		<tr <?php echo wpsc_the_checkout_item_error_class();?>>
			<td>
				<label for='<?php echo wpsc_checkout_form_element_id(); ?>'>
				<?php echo wpsc_checkout_form_name();?>
				</label>
			</td>
			<td>
				<?php echo wpsc_checkout_form_field();?>
				
		    <?php if(wpsc_the_checkout_item_error() != ''): ?>
		    <p class='validation-error'><?php echo wpsc_the_checkout_item_error(); ?></p>
		    
			<?php endif; ?>
			</td>
			</tr>
			<?php endif; ?>
		
			<?php endif; ?>
		
		<?php endwhile; ?>
		
		<?php if (get_option('display_find_us') == '1') : ?>
		<tr>
			<td><?php _e( 'How did you find us:', 'flexishop' ); ?></td>
			<td>
				<select name='how_find_us'>
					<option value='<?php _e( 'Word of Mouth', 'flexishop' ); ?>'><?php _e( 'Word of Mouth', 'flexishop' ); ?></option>
					<option value='<?php _e( 'Advertisement', 'flexishop' ); ?>'><?php _e( 'Advertisement', 'flexishop' ); ?></option>
					<option value='<?php _e( 'Internet', 'flexishop' ); ?>'><?php _e( 'Internet', 'flexishop' ); ?></option>
					<option value='<?php _e( 'Customer', 'flexishop' ); ?>'><?php _e( 'Existing Customer', 'flexishop' ); ?></option>
				</select>
			</td>
		</tr>
		<?php endif; ?>		
		<tr>
			<td colspan='2' class='wpsc_gateway_container'>
			
			<?php  //this HTML displays activated payment gateways?>
			  
				<?php if(wpsc_gateway_count() > 1): // if we have more than one gateway enabled, offer the user a choice ?>
					<h3><?php echo __('Select a payment gateway', 'flexishop');?></h3>
					<?php while (wpsc_have_gateways()) : wpsc_the_gateway(); ?>
						<div class="custom_gateway">
							<?php if(wpsc_gateway_internal_name() == 'noca'){ ?>
								<label><input type="radio" id='noca_gateway' value="<?php echo wpsc_gateway_internal_name();?>" <?php echo wpsc_gateway_is_checked(); ?> name="custom_gateway" class="custom_gateway"/><?php echo wpsc_gateway_name();?></label>
							<?php }else{ ?>
								<label><input type="radio" value="<?php echo wpsc_gateway_internal_name();?>" <?php echo wpsc_gateway_is_checked(); ?> name="custom_gateway" class="custom_gateway"/><?php echo wpsc_gateway_name();?></label>
							<?php } ?>

							
							<?php if(wpsc_gateway_form_fields()): ?> 
								<table class='<?php echo wpsc_gateway_form_field_style();?>'>
									<?php echo wpsc_gateway_form_fields();?> 
								</table>		
							<?php endif; ?>			
						</div>
					<?php endwhile; ?>
				<?php else: // otherwise, there is no choice, stick in a hidden form ?>
					<?php while (wpsc_have_gateways()) : wpsc_the_gateway(); ?>
						<input name='custom_gateway' value='<?php echo wpsc_gateway_internal_name();?>' type='hidden' />
						
							<?php if(wpsc_gateway_form_fields()): ?> 
								<table>
									<?php echo wpsc_gateway_form_fields();?> 
								</table>		
							<?php endif; ?>	
					<?php endwhile; ?>				
				<?php endif; ?>				
				
			</td>
		</tr>
		<?php if(get_option('terms_and_conditions') != '') : ?>
		<tr>
			<td colspan='2'>
     			 <input type='checkbox' value='yes' name='agree' /> <?php echo __('I agree to The ', 'flexishop');?><a class='thickbox' target='_blank' href='<?php
      echo site_url('?termsandconds=true&amp;width=360&amp;height=400'); ?>' class='termsandconds'><?php echo __('Terms and Conditions', 'flexishop');?></a>
   		   </td>
 	   </tr>
		<?php endif; ?>	
	</table>

	<?php if(get_option('terms_and_conditions') == '') : ?>
		<input type='hidden' value='yes' name='agree' />
	<?php endif; ?>	
	<?php //exit('<pre>'.print_r($wpsc_gateway->wpsc_gateways[0]['name'], true).'</pre>');
	 if(count($wpsc_gateway->wpsc_gateways) == 1 && $wpsc_gateway->wpsc_gateways[0]['name'] == 'Noca'){}else{?>
		<input type='hidden' value='submit_checkout' name='wpsc_action' />
		<input type='submit' value='<?php echo __('Make Purchase', 'flexishop');?>' name='submit' class='make_purchase' />
	<?php }/* else: ?>
	
	<br /><strong><?php echo __('Please login or signup above to make your purchase', 'flexishop');?></strong><br />
	<?php echo __('If you have just registered, please check your email and login before you make your purchase', 'flexishop');?>
	</td>
	<?php endif;  */?>
	</form>
	<a href="#checkout-step-1" id="checkout-back"><?php _e( 'Back', 'flexishop' ); ?></a>
</div>
<?php
else:
	echo __('Oops, there is nothing in your cart.', 'flexishop') . " <a href=".get_option("product_list_url").">" . __('Please visit our shop', 'flexishop') . "</a>";
endif;
?>
</div>
</div>
<?php do_action('wpsc_bottom_of_shopping_cart');
?>
<script type="text/javascript">
	jQuery(document).ready(function($) {
	    <?php if(isset($_POST['wpsc_action'])) : ?>
	    var loc = window.location.href;
	    var id = loc.split("#");
	    var error = $('#checkout-progress').css("display");
	    if(error != "none"){
		    	$("#shopping-cart-form").fadeIn();
				var checkoutWidth = $("#shopping-cart").width() + 30;
				$("#checkout-bar-in").animate({width:'+=50%'});
				$("#checkout-slider").animate({marginLeft:'-=' + checkoutWidth});
	    }
	    <?php endif; ?>
	});
</script>
