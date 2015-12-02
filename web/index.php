<?php
    // Filter out any unwanted SQL injections
    for(reset($_GET); $key = key($_GET); next($_GET)) {
        $$key = addslashes($_GET[$key]);
    }
    if(!isset($server) && !isset($msgid) && !isset($overview))
        $showframes = true;
    if(!isset($page))
        $page = 1;
    if(!is_numeric($page))
        die('<p>Page must be a number</p>');
    if(isset($msgid) && !is_numeric($msgid))
        die('<p>Message id must be a number</p>');
    mysql_connect('localhost', 'irclogs', 'PASSWORD') or die('<p>Could not connect to MySQL Server</p>');
    mysql_select_db('irclogs') or die('<p>Could not select MySQL database</p>');
    $pre = "SELECT *,DATE_FORMAT(date, '%D') AS day,DATE_FORMAT(date, '%M') AS month,DATE_FORMAT(date, '%Y') AS year,DATE_FORMAT(date, '[%T]') AS timestamp FROM messages";
    if(isset($msgid))
        $results = mysql_query($pre." WHERE id=".$msgid) or die('<p>MySQL says: '.mysql_error().'</p>');
    else if(!isset($showframes) && !isset($overview))
        $results = mysql_query($pre." WHERE server='".$server."'".(isset($nick)? " AND nick='".$nick."'": "").(isset($recipient)? " AND recipient='".$recipient."'": "")." ORDER BY date ASC LIMIT ".(($page - 1) * 200).", 200") or die('<p>MySQL says: '.mysql_error().'</p>');
    else
        $results = mysql_query("SELECT DISTINCT server, recipient FROM messages") or die('<p>MySQL says: '.mysql_error().'</p>');
        
    if(isset($msgid))
        $lel = 1;
    else if(!isset($showframes) && !isset($overview)){
        $countresult = mysql_query("SELECT COUNT(*) FROM messages WHERE server='".$server."'".(isset($nick)? " AND nick='".$nick."'": "").(isset($recipient)? " AND recipient='".$recipient."'": "")) or die('<p>MySQL says: '.mysql_error().'</p>');
        $lel = mysql_fetch_array($countresult);
        $lel = $lel['COUNT(*)'];
    }
    $title = 'IRC logs';
    if(isset($server))
        $title .= ' - '.$server;
    if(isset($recipient))
        $title .= ' - '.$recipient;
    if(isset($nick))
        $title .= ' - '.$nick;
    if($page != 1)
        $title .= ' - Page '. $page;
    if(isset($showframes))
        $title .= ' - Network list';
?>
<?php if(isset($showframes)) {?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN"
"http://www.w3.org/TR/html4/frameset.dtd">
<?php } else {?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<?php }?>
<html>
<head>
      <?php if(isset($_GET['overview'])) {?>
<script type="text/javascript">
function show_hide(network) {
  var ele = document.getElementById(network + '_div');
  var ele2 = document.getElementById(network + '_control')
  if(ele != null)
    if(ele.style.display == 'none') {
      ele.style.display = 'inline';
      if(ele2 != null)
          ele2.innerHTML = '-';
    } else {
      ele.style.display = 'none';
      if(ele2 != null)
          ele2.innerHTML = '+';
    }
}
</script>
<?php }?>
	<title><?php echo$title; ?></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="style.css" media="all">
</head>
<?php if(!isset($showframes)) {?>
<body>
<?php
    }
    if(!isset($showframes) && !isset($overview)) {
    echo '<p><a href="javascript:back()">Back</a></p>';
    $colors = array (
        0xff0000,
        0x7f0000,
        0xa000a0,
        0xff8000,
        0xffff00,
        0x00fc00,
        0x81c88c,
        0x2d914b,
        0xff00ff,
        0x909090,
        0xc4c4c4
    );
    $colormap = array();
    $colIdx = 0;
    echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page=1">First Page</a> | ';
    if($page > 1) {
        echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;='.($page - 1).'">Previous Page</a> | ';
    }
    if(($page * 200) + mysql_affected_rows() < $lel)
        echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page='.($page + 1).'">Next Page</a> | ';
    echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page='.ceil($lel / 200).'">Last Page</a>';
    $lastday = '';
    $lastmonth = '';
    $lastyear = '';
    while($row = mysql_fetch_array($results)) {
        if($row['recipient'] != $lastrecipient) {
            if($lastrecipient != '')
                echo "\t\t</tbody>\n\t\t</table>\n\t</div>";
            echo "\t<div class=\"ircsession\">\n\t\t<ul>\n\t\t\t<li><a href=\"rss.php?channel=".urlencode($row['recipient'])."\" target=\"_blank\"><img style=\"border:none\" src=\"feed-icon-16x16.png\" alt=\"RSS Feed for channel\"/></a><a href=\"index.php?server=".urlencode($row['server'])."&amp;recipient=".urlencode($row['recipient'])."\">".$row['recipient']."</a></li>\n\t\t</ul>\n\t\t<table cellspacing=\"0\">\n\t\t<tbody>\n";
            $lastrecipient = $row['recipient'];
        }
        $action = $row['type'] != 'privmsg';
        if($row['year'] != $lastyear || $row['month'] != $lastmonth || $row['day'] != $lastday)
            echo "\t\t\t<tr class=\"action\"><th></th><td><span style=\"color:green;\">The log date has changed to ".$row['day'].' '.$row['month'].' '.$row['year']."</span></td></tr>\n";
        $lastyear = $row['year'];
        $lastmonth = $row['month'];
        $lastday = $row['day'];
        if(isset($row['nick']) && !isset($colormap[$row['nick']])) {
            $colormap[$row['nick']] = $colors[$colIdx];
            $colIdx = ($colIdx + 1) % 11;
        }
        $nickline = (isset($row['nick'])? (isset($row['name'])? '<a href="nickstats.php?nick='.urlencode($row['nick']).'"><span style="color:#'.dechex($colormap[$row['nick']]).'">&lt;'.$row['nick'].'&gt;</span><span class="tt">'.$row['nick'].(isset($row['name'])? '!'.$row['name'].'@'.$row['host']: '').'</span></a>': ''): $row['host']);
        $precontent = "\t\t\t<tr".($action? " class=\"action\"": "")."><th><span class=\"time\">".$row['timestamp'].'</span>'.$nickline."</th><td>".($action? "<span style=\"color:green;\">": "");
        $postcontent = ($action? "</span>": "")."</td></tr>\n";
        $row['content'] = ereg_replace("[[:alpha:]]+://[^<>[:space:]]+[[:alnum:]/]", "<a href=\"\\0\" target=\"_blank\">\\0</a>", $row['content']);
        if($row['type'] == 'privmsg')
            echo $precontent.$row['content'].$postcontent;
        else if($row['type'] == 'notice')
            echo $precontent.'-'.$row['content'].'-'.$postcontent;
        else if($row['type'] == 'action')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.$row['content'].$postcontent;
        else if($row['type'] == 'ctcp')
            echo $precontent.'-CTCP-'.$row['content'].$postcontent;
        else if($row['type'] == 'join')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' joins '.$row['recipient'].$postcontent;
        else if($row['type'] == 'part')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' parts '.$row['recipient'].' ('.$row['content'].')'.$postcontent;
        else if($row['type'] == 'quit')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' quits IRC  ('.$row['content'].')'.$postcontent;
        else if($row['type'] == 'mode')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' sets mode '.$row['content'].' on '.$row['recipient'].$postcontent;
        else if($row['type'] == 'kick')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' kicks '.$row['target'].' from '.$row['recipient'].' ('.$row['content'].')'.$postcontent;
        else if($row['type'] == 'nick')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".$nickline.' changes his/her nickname to '.$row['target'].$postcontent;
        else if($row['type'] == 'topic')
            echo "\t\t\t<tr class=\"action\"><th><span class=\"time\">".$row['timestamp']."</span></th><td><span style=\"color:green;\">* ".(isset($row['nick'])? $nickline.' changes the channel topic to ': 'The channel topic is ').$row['content'].$postcontent;
        else echo 'Unhandled entry type: '.$row['type'];
    }
    if($lastrecipient != '')
        echo "\t\t</tbody>\n\t\t</table>\n\t</div>";
    echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page=1">First Page</a> | ';
    if($page > 1) {
        echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page='.($page - 1).'">Previous Page</a> | ';
    }
    if(($page * 200) + mysql_affected_rows() < $lel)
        echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page='.($page + 1).'">Next Page</a> | ';
    echo '<a href="index.php?server='.urlencode($server).(isset($recipient)? '&amp;recipient='.urlencode($recipient): '').(isset($nick)? '&amp;nick='.urlencode($nick): '').'&amp;page='.ceil($lel / 200).'">Last Page</a>';
        echo '<p><a href="javascript:back()">Back</a></p>';
    } else if(isset($showframes) && !isset($overview)){?>
    <FRAMESET cols="20%,80%" title="">
        <FRAME src="index.php?overview=true">
        <FRAME src="placeholder.php" name="_frame" scrolling="yes">
        <NOFRAMES>
            <p>You need a browser that supports frames. sorry</p>
        </NOFRAMES>
    </FRAMESET>
<?php
    } else if(isset($overview)) {
        while($row = mysql_fetch_array($results)) {
            if(!isset($lastserv) || $lastserv != $row['server']) {
             if(isset($lastserv)) {
                ?>
      </tbody>
      </table>
    </div>
  </div>
                <?php
            }
             ?>
  <div class="ircsession">
		<ul>
			<li><?php echo ' <a href="javascript:show_hide(\''.$row['server'].'\');"><span style="color:white" id="'.$row['server'].'_control">+</span>'.$row['server'].'</a>';?></li>
		</ul>
		  <div id="<?php echo $row['server'].'_div';?>" style="display:none">
		    <table cellspacing="0">
		    <tbody>
		        <tr><td><a href="index.php?server=<?php echo urlencode($row['server']);?>" target="_frame">View all logs for this network</a></td></tr>
            <?php
            $lastserv = $row['server'];
            }
          echo '<tr><td><a href="index.php?server='.$row['server'].'&amp;recipient='.urlencode($row['recipient']).'" target="_frame">'.$row['recipient'].'</a></td></tr>';
        }
        if(isset($lastserv)) {
                ?>
      </tbody>
      </table>
    </div>
  </div>
  <?php
    }
    }
  if(!isset($showframes)) {
?>
  <p>
    <a href="http://validator.w3.org/check?uri=referer" target="_blank"><img
        src="http://www.w3.org/Icons/valid-html401"
        alt="Valid HTML 4.01 Transitional" height="31" width="88"></a>
  </p>
</body><?php }?>
</html>