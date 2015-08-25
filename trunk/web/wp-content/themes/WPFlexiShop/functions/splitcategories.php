			<?php 
			/**
			* modified wpsc end category query function
			*/
			function return_wpsc_end_category_query() {
				global $wpdb, $wpsc_category_query;
			  $category_html = ob_get_clean();
			  return wpsc_display_category_loop($wpsc_category_query, $category_html);
			  unset($GLOBALS['wpsc_category_query']);
			}
			
			$catCount = 0;
			wpsc_start_category_query($category_settings);  ?>
					<li class='wpsc_category_<?php wpsc_print_category_id();?>'>
						<a href="<?php wpsc_print_category_url();?>" class='wpsc_category_image_link'>
							<?php wpsc_print_category_image(45, 25); ?>
						</a>

						<a href="<?php wpsc_print_category_url();?>" class="wpsc_category_link <?php wpsc_print_category_classes(); ?>">
							<?php wpsc_print_category_name();?>
							<?php if (get_option('show_category_count') == 1) : ?>
								<?php wpsc_print_category_products_count("(",")"); ?>
							<?php endif;?>
						</a>
						<?php wpsc_print_product_list(); ?>
						<?php wpsc_print_subcategory("<ul>", "</ul>"); ?>
					</li>
					
					
			<?php $categories = return_wpsc_end_category_query(); ?>
			
			<?php $cat_array = explode("</li>", $categories);
				  foreach ($cat_array as $i => $cat){
				  	if($i%4 == 0) echo "<ul class='category-list'>";
				  	echo $cat;
				  	if($i%4 == 3) echo "</ul>";
				  }
					if($i%4 != 0) echo "</ul>"; ?>