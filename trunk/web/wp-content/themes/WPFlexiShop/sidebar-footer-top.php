
<?php
	if (   ! is_active_sidebar( 'first-footer-top-widget-area'  )
		&& ! is_active_sidebar( 'second-footer-top-widget-area' )
	)
	
?>

<?php if ( is_active_sidebar( 'first-footer-top-widget-area' ) ) : ?>
					<div class="col-2 col-right">
						<div class="col-wrapper">
							<?php dynamic_sidebar( 'first-footer-top-widget-area' ); ?>
						</div>
					</div>
<?php else : ?>
	<div class="col-2">
		<div class="col-wrapper">
		<h3 class="widget-title"><?php _e( 'Twitter', 'flexishop' ); ?></h3>
		<div id="footer-twitter" class="widget-container">
			<?php getTwitter(); ?>
		</div>
		</div>
	</div>
<?php endif; ?>

<?php if ( is_active_sidebar( 'second-footer-top-widget-area' ) ) : ?>
					<div class="col-2 col-right">
						<div class="col-wrapper">
							<?php dynamic_sidebar( 'second-footer-top-widget-area' ); ?>
						</div>
					</div>
<?php else : ?>
	
	<div class="col-2 col-right">
		<div class="col-wrapper">
		<h3 class="widget-title"><?php _e( 'Categories', 'flexishop' ); ?></h3>
		<div class="widget-container"><?php getCategories(false); ?></div>
		</div>
	</div>
<?php endif; ?>