<?php
    for(reset($_GET); $key = key($_GET); next($_GET)) {
        $$key = addslashes($_GET[$key]);
    }
    if(!isset($nick))
        die('<p>No nickname specified</p>');
    mysql_connect('localhost', 'irclogs', 'PASSWORD') or die('<p>Could not connect to MySQL server</p>');
    mysql_select_db('irclogs') or die('<p>Could not select MySQL db</p>');
    
function cleanstring($string)
{
    // replace numeric entities
    $string = preg_replace('~&#x([0-9a-f]+);~ei', 'chr(hexdec("\\1"))', $string);
    $string = preg_replace('~&#([0-9]+);~e', 'chr("\\1")', $string);
    // replace literal entities
    $trans_tbl = get_html_translation_table(HTML_ENTITIES);
    $trans_tbl = array_flip($trans_tbl);
    return trim(strtr($string, $trans_tbl), ",!.?#:;");
}
    function getStatsFor($lnick) {
        $stats = array();
        $messages = mysql_query("SELECT *,DATE_FORMAT(date, '%D %M %Y %T') AS fdate FROM messages WHERE nick='".$lnick."' AND (type='privmsg' OR type='notice' OR type='ctcp' OR type='action') ORDER BY date DESC") or DIE('<p>MySQL says: '.mysql_error().'</p>');
        if(mysql_affected_rows() > 0) {
            $lastMessage = mysql_fetch_array($messages);
            $stats['lastmsgid'] = $lastMessage['id'];
            $stats['lastmsgdate'] = $lastMessage['fdate'];
            $stats['lastlogin'] = $lastMessage['name'];
            $stats['lasthost'] = $lastMessage['host'];
            $stats['lastserver'] = $lastMessage['server'];
            $stats['messages'] = mysql_affected_rows();
            mysql_free_result($messages);
            // Count joins
            $joinsR = mysql_query("SELECT COUNT(*) AS count FROM messages WHERE nick='".$lnick."' AND type='join'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $joins = mysql_fetch_array($joinsR);
            mysql_free_result($joinsR);
            $stats['joins'] = $joins['count'];
            // Count parts
            $partsR = mysql_query("SELECT COUNT(*) AS count FROM messages WHERE nick='".$lnick."' AND type='part'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $parts = mysql_fetch_array($partsR);
            mysql_free_result($partsR);
            $stats['parts'] = $parts['count'];
            // Count quits
            $quitsR = mysql_query("SELECT COUNT(*) AS count FROM messages WHERE nick='".$lnick."' AND type='quit'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $quits = mysql_fetch_array($quitsR);
            mysql_free_result($quitsR);
            $stats['quits'] = $quits['count'];
            // Count notices
            $noticesR = mysql_query("SELECT COUNT(*) AS count FROM messages WHERE nick='".$lnick."' AND type='notice'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $notices = mysql_fetch_array($noticesR);
            mysql_free_result($noticesR);
            $stats['notices'] = $notices['count'];
            // Count actions
            $actionsR = mysql_query("SELECT COUNT(*) AS count FROM messages WHERE nick='".$lnick."' AND type='action'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $actions = mysql_fetch_array($actionsR);
            mysql_free_result($actionsR);
            $stats['actions'] = $actions['count'];
            
            // Get all words, then we can count swears/laughs and mostly used words
            $results = mysql_query("SELECT * FROM messages WHERE nick='".$lnick."'") or DIE('<p>MySQL says: '.mysql_error().'</p>');
            $words = array();
            $swearwords = array();
            $laughwords = array();
            $excluded = array(
                "i", "the", "it", "at", "to", "is", "of", "a", "that", "", "have", "and", "you", "but", "in", "was", "like", "me", "with", "for", "dont", "they", "so", "im", "this", "its", "not", "be", "if", "do", "what", "can", "too", "on", "my", "are", "just", "did", "get", "had", "he", "as", "some", "your", "about", "one", "or", "there", "them", "know", "thats", "up", "when", "no", "oh", "then", "how", "from", "it's", "i'm", "that's", "u", "an", "we", "only"
            );
            $laughs = array(
                'lol', 'lmfao', 'lerl', 'lel', 'lal', 'lil', 'lul', 'lawl',' lmao', 'rofl', 'haha'
            );
            $swears = array(
                'fuck', 'crap', 'shit', 'cunt', 'dick', 'twat', 'bollocks', 'bastard', 'bitch', 'prick'
            );
            $swearcount = 0;
            $laughcount = 0;
            while($result = mysql_fetch_array($results)) {
                if(!isset($result['content']) || $result['content'] == null)
                    continue;
                $contentWords = explode(" ", $result['content']);
                foreach($contentWords as $word) {
                    $word = strtolower(cleanstring($word));
                    foreach($excluded as $exclude)
                        if($word == $exclude)
                            continue 2;
                    foreach($laughs as $laugh)
                        if($word == $laugh) {
                            $laughcount++;
                            $laughwords[$laugh]++;
                            continue 2;
                        }
                    foreach($swears as $swear)
                        if($word == $swear) {
                            $swearcount++;
                            $swearwords[$swear]++;
                            continue 2;
                        }
                    $words[$word]++;
                }
            }
            arsort($words);
            arsort($laughwords);
            arsort($swearwords);
            $words = array_slice($words, 0, 5);
            $swearwords = array_slice($swearwords, 0, 5);
            $laughwords = array_slice($laughwords, 0, 5);
            $words = array_keys($words);
            $swearwords = array_keys($swearwords);
            $laughwords = array_keys($laughwords);
            $fin = '';
            for($i = 0; $i < 5; $i++) {
                $fin .= htmlentities($words[$i]) . ($i + 1 >= 5? "": ", ");
                if($i + 1 < 5 && !isset($words[$i + 1]))
                    break;
            }
            unset($words);
            $stats['comwords'] = $fin;
            $fin = '';
            for($i = 0; $i < 5; $i++) {
                $fin .= htmlentities($swearwords[$i]) . ($i + 1 >= 5? "": ", ");
                if($i + 1 < 5 && !isset($swearwords[$i + 1]))
                    break;
            }
            unset($swearwords);
            $stats['comswears'] = $fin;
            $fin = '';
            for($i = 0; $i < 5; $i++) {
                $fin .= htmlentities($laughwords[$i]) . ($i + 1 >= 5? "": ", ");
                if($i + 1 < 5 && !isset($laughwords[$i + 1]))
                    break;
            }
            unset($laughwords);
            $stats['comlaughs'] = $fin;
            $stats['swears'] = $swearcount;
            $stats['laughs'] = $laughcount;
            mysql_free_result($results);
            // Todo: get mostly use word, amount of swears, most frequent time, most highlighted etc...
        }
        return $stats;
    }
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Nickname statistics - <?php echo $nick; ?></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<?php if(isset($nick)) echo "<link rel=\"alternate\" type=\"application/rss+xml\" title=\"$nick's recent events\" href=\"rss.php?nick=$nick\">";?>
	<link rel="stylesheet" type="text/css" href="style.css" media="all">
</head>
<body>
  <div class="ircsession">
    <ul>
      <li><?php echo '<a href="rss.php?nick='.$nick.'" target="_blank"><img style="border:none" src="feed-icon-16x16.png" alt="RSS Feed for this nick"/></a>'.$nick;?></li>
    </ul>
    <table cellspacing="0">
    <tbody>
    <?php
        $stats = getStatsFor($nick);
        if(!isset($stats['messages']))
            die('<tr><th>Error</th><td>That nickname has not been logged posting a message</td></tr>');
       echo '<tr><th>Messages from this user: </th><td>'.$stats['messages'].'</td></tr>';
       echo '<tr><th>Last seen: </th><td><a href="index.php?msgid='.$stats['lastmsgid'].'">'.$stats['lastmsgdate'].'</a></td></tr>';
       echo '<tr><th>Last Login: </th><td>'.$stats['lastlogin'].'</td></tr>';
       echo '<tr><th>Last Host: </th><td>'.$stats['lasthost'].'</td></tr>';
       echo '<tr><th>Number of joins logged: </th><td>'.$stats['joins'].'</td></tr>';
       echo '<tr><th>Number of parts logged: </th><td>'.$stats['parts'].'</td></tr>';
       echo '<tr><th>Number of quits logged: </th><td>'.$stats['quits'].'</td></tr>';
       echo '<tr><th>Number of notices logged: </th><td>'.$stats['notices'].'</td></tr>';
       echo '<tr><th>Number of actions logged: </th><td>'.$stats['actions'].'</td></tr>';
       echo '<tr><th>Number of laughs: </th><td>'.$stats['laughs'].'</td></tr>';
       echo '<tr><th>Number of swearwords: </th><td>'.$stats['swears'].'</td></tr>';
       echo '<tr><th>Laughs/Swears</th><td>'.($stats['laughs'] / ($stats['swears'] == 0? 1: $stats['swears'])).'</td></tr>';
       echo '<tr><th>Most commonly used words:</th><td>'.$stats['comwords'].'</td></tr>';
        echo '<tr><th>Most commonly used laughs:</th><td>'.$stats['comlaughs'].'</td></tr>';
       echo '<tr><th>Most commonly used swearwords:</th><td>'.$stats['comswears'].'</td></tr>';
       ?>
    </tbody>
    </table>
    </div>
    <?php
       echo '<a href="index.php?server='.urlencode($stats['lastserver']).'&amp;nick='.urlencode($nick).'">View logs of this user on '.$stats['lastserver'].'</a>';
       echo '';
    ?>
    <p>This page is still under construction<br/>
    Please provide feedback (e.g. more statistics, less statistics) by contacting newbiehacker on <a href="irc://irc.deltaanime.net/#mopar">IRC</a></p>
  <p>
    <a href="http://validator.w3.org/check?uri=referer" target="_blank"><img
        src="http://www.w3.org/Icons/valid-html401"
        alt="Valid HTML 4.01 Transitional" height="31" width="88"></a>
  </p>
</body>
</html>