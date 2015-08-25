<?php
$username = $_GET["user"];
$mapid = $_GET["mapid"];
$md5 = $_GET["md5"];

$link = mysql_connect('localhost', '414405_zc', 'f62mG7weU');
if (!$link) {
    
    die('Not connected : ' . mysql_error());
}

$db_selected = mysql_select_db('modzilla_zymichost_zc', $link);
if (!$db_selected) {
    
    die ('Can\'t use db : ' . mysql_error());
}

//lazy copy paste, pre check for -1 before free map check
$table = "maps_bought";
$q=sprintf("SELECT * FROM %s WHERE ign='%s'",
            mysql_real_escape_string($table),
            mysql_real_escape_string($username));
$results = mysql_query($q, $link);
$results3 = $results;

while($row = mysql_fetch_array($results)) {
    $ids = split(",",$row[2]);
    
    //print_r($ids);
    for ($i = 0; $i < count($ids); $i++) {
        if ($ids[$i] == -1) {
            echo "true";
            kill();
        }
    }
}



//free map check
if ($mapid == null || $mapid == 0){
    $table = "maps_id_to_md5";
    $q=sprintf("SELECT * FROM %s WHERE mapid='-1'",
                mysql_real_escape_string($table));
    $results2 = mysql_query($q, $link);

    while($row = mysql_fetch_array($results2)) {
        $ids = split(",",$row[3]);
        
        //print_r($ids);
        for ($i = 0; $i < count($ids); $i++) {
            if ($ids[$i] == $md5) {
                echo "true";
                kill();
            }
        }
    }
    echo "false";
    kill();
}

//otherwise check for user map ownership with mapid given
/*$table = "maps_bought";
$q=sprintf("SELECT * FROM %s WHERE ign='%s'",
            mysql_real_escape_string($table),
            mysql_real_escape_string($username));
$results = mysql_query($q, $link);*/

$q=sprintf("SELECT * FROM %s WHERE ign='%s'",
            mysql_real_escape_string($table),
            mysql_real_escape_string($username));
$results = mysql_query($q, $link);

while($row = mysql_fetch_array($results)) {
    $ids = split(",",$row[2]);
    
    //print_r($ids);
    for ($i = 0; $i < count($ids); $i++) {
        if ($ids[$i] == $mapid || $ids[$i] == -1) {
            echo "true";
            kill();
        }
    }
}

echo "false";

function kill() {
    global $link;
    mysql_close($link);
    die();
}
?>
