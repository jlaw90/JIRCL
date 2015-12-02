<?php
    if(!isset($_GET['user']) || !isset($_GET['pass']))
        die ('ERR: No authentification provided!');
    if($_GET['user'] != 'ourgay' || $_GET['pass'] != 'IRC_CONNECTION_REQUEST_PASSWORD')
        die ('ERR: Invalid authentification!');
    if(!isset($_GET['server']) || !isset($_GET['recipient']) || !isset($_GET['type']) || !isset($_GET['date']))
        die('ERR: Not all required properties were provided!');
    if (get_magic_quotes_gpc()) {
        function undoMagicQuotes($array, $topLevel=true) {
            $newArray = array();
            foreach($array as $key => $value) {
                if (!$topLevel) {
                    $key = stripslashes($key);
                }
                if (is_array($value)) {
                    $newArray[$key] = undoMagicQuotes($value, false);
                }
                else {
                    $newArray[$key] = stripslashes($value);
                }
            }
            return $newArray;
        }
        $_GET = undoMagicQuotes($_GET);
        $_POST = undoMagicQuotes($_POST);
        $_COOKIE = undoMagicQuotes($_COOKIE);
        $_REQUEST = undoMagicQuotes($_REQUEST);
    }

    mysql_connect('localhost', 'irclogs', 'PASSWORD')or die('ERR: Could not connect to MySQL'.mysql_error());
    mysql_select_db('irclogs') or die('ERR: Could not select database'.mysql_error());
    $nick = 'NULL';
    $name = 'NULL';
    $host = 'NULL';
    $content = 'NULL';
    $target = 'NULL';
    
    for(reset($_GET); $key = key($_GET); next($_GET)) {
        $v = '\''.$_GET[$key].'\'';
        $$key = $v;
    }
    mysql_query("INSERT INTO messages (id, server, nick, name, host, recipient, type, content, date, target) VALUES (NULL, ".$server.", ".$nick.", ".$name.", ".$host.", ".$recipient.", ".$type.", ".$content.", ".$date.(isset($_GET['offset'])? " + INTERVAL ".$_GET['offset']." HOUR": "").", ".$target.");") or die('ERR: '.mysql_error());
    echo('OK!');
?>