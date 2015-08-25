			<?php $group_id = 1;
            $category_data = $wpdb->get_results("SELECT  `id`, `name`, `nice-name`, `description`, `image` FROM `".WPSC_TABLE_PRODUCT_CATEGORIES . "` WHERE `active` = '1' AND `group_id` = '".(int)$group_id."'",ARRAY_A); ?>
			<div class="slider-mask<?php if(count($category_data) > 5) echo ' slideable'; ?>">
				<div class="front-category-slider">
					<?php $i = 0; foreach($category_data as $category_row) {
						$name = $category_row['name'];
						$id = $category_row['id'];
						$description = $category_row['description'];
						$url = wpsc_category_url($category_row['id']);
						$image = wpsc_category_image($id);
						?>
							<?php if(($i%5)==0) echo '<div class="full-width"><ul class="front-category-list">' ?>
							<li <?php if(($i%5)==4) echo 'class="col-right"' ?>>
								<div class="padding">
									<a href="<?php echo $url ?>" class="category-thumbnail <?php if(empty($image)) echo 'no-cat-image'; ?>"><?php if(!empty($image)) : ?><img src="<?php echo $image ?>" alt="<?php echo $name ?>" /><?php endif; ?><span class="category-name"><?php echo $name ?></span></a>
								</div>
							</li>
							<?php if(($i%5)==4) echo '</ul></div>' ?>
					<?php $i++; } ?>
					<?php if(($i%5)!=0) echo '</ul></div>' ?>
					</div>
			</div>