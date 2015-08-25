			<ul class="category-list">
			<?php wpsc_start_category_query($category_settings); ?>
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
			<?php wpsc_end_category_query(); ?>
			</ul>