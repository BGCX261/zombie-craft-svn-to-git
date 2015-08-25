
<ul class="sidebar-widgets">

<?php

	if ( ! dynamic_sidebar( 'primary-widget-area' ) ) : ?>

			<li>
				<h3><?php _e( 'Archives', 'flexishop' ); ?></h3>
				<ul>
					<?php wp_get_archives( 'type=monthly' ); ?>
				</ul>
			</li>

		<?php endif; // end primary widget area ?>
			</ul>

<?php
	// A second sidebar for widgets, just because.
	if ( is_active_sidebar( 'secondary-widget-area' ) ) : ?>

			<ul class="xoxo">
				<?php dynamic_sidebar( 'secondary-widget-area' ); ?>
			</ul>

<?php endif; ?>