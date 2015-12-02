# Created by IntelliJ IDEA.
# User: James
# Date: 15-Aug-2007
# Time: 19:58:03
require 'java'
include_package 'org.newbiehacker.conlib.conn'
include_package 'org.newbiehacker.conlib.event'
include_package 'org.newbiehacker.conlib.irc'
include_package 'org.newbiehacker.conlib.irc.event'
include_package 'org.newbiehacker.conlib.event'
include_package 'org.newbiehacker.conlib.util'

class Listener < EventListener

    def onEvent(event)
    end
end

l = Listener.new
GlobalManager.addEventListener(l)
$con.sendMessage('#mopar', 'Listener added')