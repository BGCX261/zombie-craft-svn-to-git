
<?php

	if (   ! is_active_sidebar( 'first-footer-widget-area'  )
		&& ! is_active_sidebar( 'second-footer-widget-area' )
		&& ! is_active_sidebar( 'third-footer-widget-area'  )
		&& ! is_active_sidebar( 'fourth-footer-widget-area' )
	)
?>

<?php if ( is_active_sidebar( 'first-footer-widget-area' ) ) : ?>
					<ul class="col-5">
						<?php dynamic_sidebar( 'first-footer-widget-area' ); ?>
					</ul>
<?php else : ?>
	<div class="col-5">
		<?php getCategories(false); ?>
	</div>
<?php endif; ?>

<?php if ( is_active_sidebar( 'second-footer-widget-area' ) ) : ?>
					<ul class="col-5">
						<?php dynamic_sidebar( 'second-footer-widget-area' ); ?>
					</ul>
<?php else : ?>
	<div class="col-5">
		<h3><?php _e( 'Shop Categories', 'flexishop' ); ?></h3>
		<?php getCategories(false); ?>
	</div>
<?php endif; ?>

<?php if ( is_active_sidebar( 'third-footer-widget-area' ) ) : ?>
					<ul class="col-5">
						<?php dynamic_sidebar( 'third-footer-widget-area' ); ?>
					</ul>
<?php else : ?>
	<div class="col-5">
		<div class="widget-container">
		<?php getBlogCategories(); ?>
		</div>
	</div>
<?php endif; ?>

<?php if ( is_active_sidebar( 'fourth-footer-widget-area' ) ) : ?>
					<ul class="col-5">
						<?php dynamic_sidebar( 'fourth-footer-widget-area' ); ?>
					</ul>
<?php else : ?>
	<div class="col-5">
		<div id="footer-about">
			<h3><?php _e( 'About Us', 'flexishop' ); ?></h3>
			<p><?php
				$options = get_option('site_basic_options');
				echo $options['aboutus']; ?></p>
		</div>
	</div>
<?php endif; ?>

<?php if ( is_active_sidebar( 'fifth-footer-widget-area' ) ) : ?>
					<ul class="col-5 col-right">
						<?php dynamic_sidebar( 'fifth-footer-widget-area' ); ?>
					</ul>
<?php else : ?>
	<div class="col-5 col-right">
		
	</div>
<?php endif; ?>