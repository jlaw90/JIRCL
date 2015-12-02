<?php echo'<?xml version="1.0" encoding="utf-8"?>';?>
<!DOCTYPE rss PUBLIC "-//Netscape Communications//DTD RSS 0.91//EN" "http://my.netscape.com/publish/formats/rss-0.91.dtd">  
<rss version="2.0">
<channel>
    <title>Recent IRC Log updates</title>
		<link>http://74.52.183.18/~newbieha/irclogs/</link>
		<description>An RSS feed of specified node</description>
		<language>en-us</language>
		<docs>http://blogs.law.harvard.edu/tech/rss</docs>
		<generator>NebNet RSS Generator</generator>
    <?php
        for(reset($_GET); $key = key($_GET); next($_GET)) {
            $$key = addslashes($_GET[$key]);
        if(!isset($nick) && !isset($channel))
            echo '<item><title>No source specified</title><description>FKN HAXXORZ</description></item>';
        else {
            mysql_connect('localhost', 'irclogs', 'PASSWORD');
            mysql_select_db('irclogs');
        }
        if(isset($channel))
            $results = mysql_query("SELECT *,DATE_FORMAT(date, '%a, %d %b %Y %T GMT') AS fdate FROM messages WHERE recipient='".$channel."' ORDER BY date DESC LIMIT 10");
        else if(isset($nick))
            $results = mysql_query("SELECT *,DATE_FORMAT(date, '%a, %d %b %Y %T GMT') AS fdate FROM messages WHERE nick='".$nick."' ORDER BY date DESC LIMIT 10");
        
        if(isset($results)) {
            while($row = mysql_fetch_array($results)) {
                echo "\t<item><title>".$row['content']."</title><description>".$row['content']."</description><pubDate>".$row['fdate']."</pubDate><link>http://74.52.183.18/~newbieha/irclogs/?msgid=".$row['id']."</link><guid>http://74.52.183.18/~newbieha/irclogs/?msgid=".$row['id']."</guid></item>\n";
            }
        }
    }
    ?>
</channel>
</rss>