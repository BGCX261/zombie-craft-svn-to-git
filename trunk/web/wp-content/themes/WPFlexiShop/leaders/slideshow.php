<div id="featured-slider">
    <div id="feature-wrapper">
    <div id="features">
        <ul class="feature-list">
<?php 
global $post; 
$attachments = get_children( array( 'post_parent' => $post->ID, 'post_status' => 'inherit', 'post_type' => 'attachment', 'post_mime_type' => 'image') );
if ( !empty($attachments) ) {
	$i=0;
	foreach ( $attachments as $id => $attachment ) {
		list($src) = wp_get_attachment_image_src($id,'promotion');
		$url =  $attachment->post_content ? esc_url($attachment->post_content) : '';
		echo '<li class="promotion"><div class="promotion-header" style="text-align:center;">';
		if($url) echo '<a href="'.$url.'" >';
		echo '<img src="'.$src.'" alt="'.esc_attr($attachment->post_title).'"/>';
		if($url) echo '</a>';
		echo '</div></li>';
	}
}
else {
	echo '<li class="promotion"><div class="promotion-header" style="text-align:center;">';
	echo __( 'No Image Available', 'flexishop' );
	echo '</div></li>';
}
?>
        </ul>
    </div>
    
    <!-- <div class="feature-nav">
        <a href="#" class="previous">Previous</a>
        <a href="#" class="next">Next</a>
    </div> -->
    </div>
</div>
<div id="slider-controls"></div>
