<?php

define("SECOND", 1);
define("MINUTE", 60 * SECOND);
define("HOUR", 60 * MINUTE);
define("DAY", 24 * HOUR);
define("MONTH", 30 * DAY);
function relativeTime($time)
{
	$delta = strtotime('+2 hours') - $time;
	if ($delta < 2 * MINUTE) {
		return "1 min ago";
	}
	if ($delta < 45 * MINUTE) {
		return floor($delta / MINUTE) . " min ago";
	}
	if ($delta < 90 * MINUTE) {
		return "1 hour ago";
	}
	if ($delta < 24 * HOUR) {
		return floor($delta / HOUR) . " hours ago";
	}
	if ($delta < 48 * HOUR) {
		return "yesterday";
	}
	if ($delta < 30 * DAY) {
		return floor($delta / DAY) . " days ago";
	}
	if ($delta < 12 * MONTH) {
		$months = floor($delta / DAY / 30);
		return $months <= 1 ? "1 month ago" : $months . " months ago";
	} else {
		$years = floor($delta / DAY / 365);
		return $years <= 1 ? "1 year ago" : $years . " years ago";
	}
}

function parse_cache_feed($usernames, $limit) {
	$username_for_feed = str_replace(" ", "+OR+from%3A", $usernames);
	$feed = "http://twitter.com/statuses/user_timeline/" . $username_for_feed . ".atom?count=" . $limit;
	$usernames_for_file = str_replace(" ", "-", $usernames);
	$cache_file = dirname(__FILE__).'/cache/' . $usernames_for_file . '-twitter-cache';
	$last = filemtime($cache_file);
	$now = time();
	$interval = 600; // ten minutes
	// check the cache file
	if ( !$last || (( $now - $last ) > $interval) ) {
		// cache file doesn't exist, or is old, so refresh it
		$cache_rss = file_get_contents($feed);
		if (!$cache_rss) {
			// we didn't get anything back from twitter
			echo "<!-- ".__( 'ERROR: Twitter feed was blank! Using cache file.', 'flexishop' )." -->";
		} else {
			// we got good results from twitter
			echo "<!-- ".__( 'SUCCESS: Twitter feed used to update cache file.', 'flexishop' )." -->";
			$cache_static = fopen($cache_file, 'wb');
			fwrite($cache_static, serialize($cache_rss));
			fclose($cache_static);
		}
		// read from the cache file
		$rss = @unserialize(file_get_contents($cache_file));
	}
	else {
		// cache file is fresh enough, so read from it
		echo "<!-- ".__( 'SUCCESS: Cache file was recent enough to read from.', 'flexishop' )." -->";
		$rss = @unserialize(file_get_contents($cache_file));
	}
	// clean up and output the twitter feed
	$feed = str_replace("&amp;", "&", $rss);
	$feed = str_replace("&lt;", "<", $feed);
	$feed = str_replace("&gt;", ">", $feed);
	$clean = explode("<entry>", $feed);
	$clean = str_replace("&quot;", "'", $clean);
	$clean = str_replace("&apos;", "'", $clean);
	$amount = count($clean) - 1;
	if ($amount) { // are there any tweets?
		for ($i = 1; $i <= $amount; $i++) {
			$entry_close = explode("</entry>", $clean[$i]);
			$clean_content_1 = explode("<content type=\"html\">", $entry_close[0]);
			$clean_content = explode("</content>", $clean_content_1[1]);
			$clean_name_2 = explode("<name>", $entry_close[0]);
			$clean_name_1 = explode("(", $clean_name_2[1]);
			$clean_name = explode(")</name>", $clean_name_1[1]);
			$clean_user = explode(" (", $clean_name_2[1]);
			$clean_lower_user = strtolower($clean_user[0]);
			$clean_uri_1 = explode("<uri>", $entry_close[0]);
			$clean_uri = explode("</uri>", $clean_uri_1[1]);
			$clean_time_1 = explode("<published>", $entry_close[0]);
			$clean_time = explode("</published>", $clean_time_1[1]);
			$unix_time = strtotime($clean_time[0]);
			$pretty_time = relativeTime($unix_time);
			$tweetmessage = preg_replace("/(http:\/\/[^\s]+)/", "<a href='$1' rel='nofollow'>$1</a>", $clean_content[0]);
			$tweetmessage = preg_replace("/(@[^\s]+)/", "<a href='http://twitter.com/$1' rel='nofollow'>$1</a>", $tweetmessage);
			?>
					<p class="twitter-message">
						<?php echo $tweetmessage; ?>
						<br /><small>
							<?php echo $pretty_time; ?>
						</small>
					</p>
			<?php
		}
	} else { // if there aren't any tweets
		?>
				<p class="twitter-message">
					<?php _e( 'No updates available.', 'flexishop' ); ?> 
				</p>
		<?php
	}
}

// Example: Get a single tweet.
$options = get_option('site_basic_options');
$username = $options['twitter'];
if ( empty ( $username ) ) {
	echo __( 'Please add your twitter username to WPFlexiShop Theme Option', 'flexishop' );
}
else {
	parse_cache_feed($username, 3);
}
?>