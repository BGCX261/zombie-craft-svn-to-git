<?php

$username = $_GET["user"];
$mapid = $_GET["mapid"];

if ($mapid == 0) {
    echo "true";
    die();
}

$link = mysql_connect('localhost', '414405_zc', 'f62mG7weU');
if (!$link) {
    
    die('Not connected : ' . mysql_error());
}

// make foo the current db
$db_selected = mysql_select_db('modzilla_zymichost_zc', $link);
if (!$db_selected) {
    
    die ('Can\'t use db : ' . mysql_error());
}

$table = "maps_bought";
$q="select * from " . $table . " where ign = '" .  $username . "'";
$results = mysql_query($q, $link);

if ($results) { 

    //echo "Found user<br>";
    
    //echo mysql_num_fields($results);
    
    $row=mysql_fetch_row($results);
    
    $ids = split(",",$row[1]);
    
    //print_r($ids);
    
    for ($i = 0; $i < count($ids); $i++) {
        if ($ids[$i] == $mapid || $ids[$i] == -1) {
            echo "true";
            die();
        }
    }
    
    echo "false";

    
} else {
    echo "false";
    //echo "user Not found<br>";
}


?>
