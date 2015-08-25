<?php
 //echo "<pre>".print_r($GLOBALS['wpsc_cart']->cart_items[0], true)."</pre>";
?>
<div id="small-cart" class="shopping-cart-wrapper widget_wp_shopping_cart">
<div id="small-cart-header">
<div class="cart-message">
<?php if(count($cart_messages) > 0): ?>
		<?php foreach((array)$cart_messages as $cart_message) { ?>
		  <h4><?php echo $cart_message; ?></h4>
		<?php } ?>
	<?php else: ?>
		<h4><?php _e( 'Shopping Cart', 'flexishop' ); ?></h4>
	<?php endif; ?>
		</div>
		<div class='cart-items'>
			<span class='cartcount'>
				<?php echo wpsc_cart_item_count(); ?>
			</span>
		</div>
	</div>
	<?php if(wpsc_cart_item_count() > 0): ?>
	<table class='shoppingcart'>
		<tr>
			<th id='product'><?php echo __('Product', 'flexishop'); ?></th>
			<th id='quantity'><?php echo __('Qty', 'flexishop'); ?></th>
			<th id='price'><?php echo __('Price', 'flexishop'); ?></th>
		</tr>
		<?php while(wpsc_have_cart_items()): wpsc_the_cart_item(); ?>
			<tr>
					<td><?php echo wpsc_cart_item_name(); ?></td>
					<td><?php echo wpsc_cart_item_quantity(); ?></td>
					<td><?php echo wpsc_cart_item_price(); ?></td>
			</tr>	
		<?php endwhile; ?>
	</table>

<?php if(wpsc_cart_has_shipping() && !wpsc_cart_show_plus_postage()) : ?>
		<span class='total'>
		  <span class="pricedisplay checkout-shipping"><?php echo wpsc_cart_shipping(); ?></span>
		<span class='totalhead'>
			<?php echo __('Shipping', 'flexishop'); ?>:
	  </span>
	
	</span>
	<?php endif; ?>
<?php if( (wpsc_cart_tax(false) >0) && !wpsc_cart_show_plus_postage()) : ?>
		<span class='total'>
		  <span class="pricedisplay checkout-tax"><?php echo wpsc_cart_tax(); ?></span>
		<span class='totalhead'>
			<?php echo wpsc_display_tax_label(true); ?>:
	  </span>
	
	</span>
	<?php endif; ?>
		
	<span class='total'>
		<span class="pricedisplay checkout-total">
			<?php echo wpsc_cart_total_widget(); ?>
			<?php if(wpsc_cart_show_plus_postage()) : ?>
				<span class='pluspostagetax'> + <?php echo __('Postage &amp; Tax ', 'flexishop'); ?></span>
			<?php endif; ?>
		</span>
		<span class='totalhead'>
			<?php echo __('Total', 'flexishop'); ?>:
	  </span>
	</span>
	
	<div class="small-cart-links"><a class="checkout-link" target='_parent' href='<?php echo get_option('shopping_cart_url'); ?>'><?php echo __('Checkout', 'flexishop'); ?></a>
		<form action='' method='post' class='wpsc_empty_the_cart'>
		<input type='hidden' name='wpsc_ajax_action' value='empty_cart' />
		<span class='emptycart'>
			<a target='_parent' href='<?php echo htmlentities(add_query_arg('wpsc_ajax_action', 'empty_cart', remove_query_arg('ajax')), ENT_QUOTES); ?>'><?php echo __('Empty Cart', 'flexishop'); ?></a>
		</span>                                                                                             
	</form>
	
	</div>
<?php else: ?>
	<p class="empty"><?php echo __('Your shopping cart is empty', 'flexishop'); ?></p>
	<p class="visitshop">
	  <a target='_parent' href="<?php echo get_option('product_list_url'); ?>"><?php echo __('Visit the shop', 'flexishop'); ?></a>
	</p>
<?php endif; ?>

<?php
wpsc_google_checkout();


?>
</div>