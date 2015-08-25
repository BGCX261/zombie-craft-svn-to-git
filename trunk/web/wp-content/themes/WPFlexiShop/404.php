<?php

get_header(); ?>

<div id="content-wrapper">
	<div id="main-content" class="container">	
		<div class="margin">
			<div id="main-col" class="col-1">
				<p><?php _e( 'Apologies, but the page you requested could not be found. Perhaps searching will help.', 'flexishop' ); ?></p>
				<?php get_search_form(); ?>
				<script type="text/javascript">
					// focus on search field after it has loaded
					document.getElementById('s') && document.getElementById('s').focus();
				</script>
			</div>
		</div>
	</div>
</div>

<?php get_footer(); ?>