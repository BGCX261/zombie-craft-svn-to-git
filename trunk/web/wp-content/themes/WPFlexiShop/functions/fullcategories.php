<?php
global $wpdb;

/**
* modified wpsc end category query function
*/
function return_wpsc_end_category_query() {
	global $wpdb, $wpsc_category_query;
  $category_html = ob_get_clean();
  return wpsc_display_category_loop($wpsc_category_query, $category_html);
  unset($GLOBALS['wpsc_category_query']);
}

$categorisation_groups =  $wpdb->get_results("SELECT * FROM `".WPSC_TABLE_CATEGORISATION_GROUPS."` WHERE `active` IN ('1')", ARRAY_A);
foreach($categorisation_groups as $categorisation_group) {
	$category_settings = array();
	$category_settings['category_group'] = $categorisation_group['id'];
	$category_settings['show_thumbnails'] = $instance['image'];
	$category_settings['order_by'] =  array("column" => 'name', "direction" =>'asc');
	$provided_classes = array();
	if($category_settings['show_thumbnails'] == 1) {
		$provided_classes[] = "category_images";
	}
	?>
	<div class='categories-group' id='categorisation_group_<?php echo $categorisation_group['id']; ?>'>
		<?php if(count($categorisation_groups) > 1) :  // no title unless multiple category groups ?>
		<h4 class='wpsc_category_title col'><?php echo $categorisation_group['name']; ?></h4>
		<?php endif; ?>
			<ul class='wpsc_categories col wpsc_top_level_categories <?php echo implode(" ", (array)$provided_classes); ?>'>
				<?php $catCount = 0; ?>
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
				<?php $categories = return_wpsc_end_category_query(); ?>
			
				<?php $cat_array = explode("</li>", $categories);
				  foreach ($cat_array as $i => $cat){
				  	if($i%4 == 0) echo "<ul class='split-category-list col'>";
				  	echo $cat;
				  	if($i%4 == 3) echo "</ul>";
				  }
					if($i%4 != 0) echo "</ul>"; ?>
			</ul>
		<div class='clear_category_group'></div>
	</div>
<?php
}

?>