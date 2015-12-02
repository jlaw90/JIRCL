importPackage(org.newbiehacker.conlib.conn);
importPackage(org.newbiehacker.conlib.util);
importPackage(org.newbiehacker.conlib.event);
importPackage(org.newbiehacker.conlib.irc.event);
importPackage(org.newbiehacker.conlib.irc);

var BOLD = "\u0002";
var COL = "\u0003";
var ORIG = "\u000f";

var SUITS = new Array("special", "r", "g", "b", "y");
var TYPES = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "d2", "s", "r", "w", "wd4");

function Card(suit, type) {
    this.suit = suit;
    this.type = type;

    this.toString = function() {
        var s = "";
        if(type == 13)
            s += COL + 8 + "W" + COL + 2 + "I" + COL + 4 + "L" + COL + 3 + "D" + (suit != 0? ORIG + BOLD + " [": "");
        if(type == 14)
            s += COL + "2W" + COL + "8D" + COL + "034" + (suit != 0? ORIG + BOLD + " [": "");
        if(suit == 1)
            s += COL + 4 + "R";
        else if(suit == 2)
            s += COL + 3 + "G";
        else if(suit == 3)
            s += COL + 2 + "B";
        else if(suit == 4)
            s += COL + 8 + "Y";
        if(type <= 9)
            s += type;
        else if(type == 10)
            s += "D2";
        else if(type == 11)
            s += "S";
        else if(type == 12)
            s += "R";
        else if(type == 13 && suit != 0)
            s += ORIG + BOLD + "]";
        else if(type == 14 && suit != 0)
            s += ORIG + BOLD + "]";
        s += ORIG;
        return s;
    }

    this.matches = function(card) {
        return this.suit == 0 || this.suit == card.suit || this.type == card.type;
    }

    this.equals = function(card) {
        if((this.type == 13 || this.type == 14) && card.type == this.type)
            return true;
        return this.suit == card.suit && this.type == card.type;
    }
}

function Deck() {
    this.cards = new java.util.LinkedList();

    this.shuffle = function() {
        for(var i = 0; i < 4; i++)
            java.util.Collections.shuffle(this.cards);
    }

    this.pop = function() {
        return this.cards.poll();
    }
    
    this.peek = function() {
        return this.cards.get(this.cards.size() - 1);
    }

    this.push = function(card) {
        this.cards.add(card);
    }

    this.create = function() {
        for(var i1 = 1; i1 < 5; i1++) {
            for(var i = 0; i < 2; i++) {
                for(var i2 = 1; i2 < 10; i2++)
                    this.push(new Card(i1, i2));
                this.push(new Card(i1, 10));
                this.push(new Card(i1, 11));
                this.push(new Card(i1, 12));
            }
            this.push(new Card(i1, 0));
        }
        for(var i = 0; i < 4; i++) {
            this.push(new Card(0, 13));
            this.push(new Card(0, 14));
        }
    }

    this.contains = function(card) {
        for(var i = 0; i < this.cards.size(); i++)
            if(this.cards.get(i).equals(card))
                return true;
        return false;
    }

    this.remove = function(card) {
        for(var i = 0; i < this.cards.size(); i++)
            if(this.cards.get(i).equals(card)) {
                this.cards.remove(this.cards.get(i));
                return;
            }
    }
}

function Player() {
    this.cards = new Deck();
    this.drawncard = null;
    this.warnings = 0;

    this.reset = function() {
        this.drawncard = null;
    }
}

// Game states
    var RECRUITING = 0;
    var PLAYING = 1;
    var CHOOSING_COLOR = 2;

var games = new java.util.HashMap();

var gameStartID = 234;
var gameWaitTimeoutID = 235;

function Game(owner) {
    this.players = new java.util.HashMap();
    this.deck = new Deck();
    this.deck.create();
    this.deck.shuffle();
    this.discard = new Deck();
    this.state = RECRUITING;
    this.currentPlayer = null;
    this.admin = owner;

    this.currentPlayer = null;
    this.playerIndex = 0;
    this.direction = 1;
    this.drawfour = false;

    this.deal = function() {
        var pa = this.players.values().toArray();
        for(var i = 0; i < 7; i++)
            for(var i1 = 0; i1 < java.lang.reflect.Array.getLength(pa); i1++) {
                pa[i1].cards.push(this.deck.pop());
            }
        this.discard.push(this.deck.pop());
        while(this.discard.peek().type == 14) {
            this.deck.push(this.discard.pop());
        }
    }

    this.tellCards = function(nick) {
        var player = this.players.get(nick);
        var s = "Your Cards: [";
        for(var i = 0; i < player.cards.cards.size(); i++)
            s += player.cards.cards.get(i) + (i + 1 == player.cards.cards.size()? "": ", ");
        s += "]";
        con.sendNotice(nick, s);
    }

    this.next = function() {
        var keys = this.players.keySet().toArray();
        this.playerIndex = this.playerIndex + this.direction;
        if(this.playerIndex < 0)
            this.playerIndex = this.players.size() - 1;
        else
            this.playerIndex %= this.players.size();
        this.currentPlayer = keys[this.playerIndex];
        this.players.get(this.currentPlayer).reset();
    }

    this.prev = function() {
       var keys = this.players.keySet().toArray();
       this.playerIndex = this.playerIndex - this.direction;
       if(this.playerIndex < 0)
           this.playerIndex = this.players.size() - 1;
       else
           this.playerIndex %= this.players.size();
       this.currentPlayer = keys[this.playerIndex];
       this.players.get(this.currentPlayer).reset();
    }

    this.drawCards = function(nick, count) {
        var player = this.players.get(nick);
        var s = "You have drawn: ";
        for(var i = 0; i < count; i++) {
            var card = this.popCard();
            s += card.toString() + (i + 1 == count? "": ", ");
            player.cards.push(card);
        }
        con.sendNotice(nick, s);
    }
    
    this.handleCard = function(card, channel) {
        if(card.type == 10) {
            con.sendMessage(channel, this.currentPlayer + " has drawn 2 cards and been skipped!");
            this.drawCards(this.currentPlayer, 2);
            this.next();
        } else if(card.type == 11) {
            con.sendMessage(channel, this.currentPlayer + " was skipped!");
            this.next();
        } else if(card.type == 12) {
            con.sendMessage(channel, "The turn order has been reversed!");
            this.direction = this.direction == 1? -1: 1;
            this.next();
            this.next();
        } else if(card.type == 13) {
            if(card.suit == 0) {
                this.prev();
                con.sendMessage(channel, "Please choose a color with !choose");
                this.state = CHOOSING_COLOR;
            } else {
                con.sendMessage(channel, this.currentPlayer + " has drawn 2 cards and been skipped!");
                this.drawCards(this.currentPlayer, 2);
                this.next();
                this.notifyChannel(channel);
                this.tellCards(this.currentPlayer);
            }
        } else if(card.type == 14) {
            if(card.suit == 0) {
                this.prev();
                con.sendMessage(channel, "Please choose a color with !choose");
                this.drawfour = true;
                this.state = CHOOSING_COLOR;
            } else {
                con.sendMessage(channel, this.currentPlayer + " has drawn 4 cards and been skipped!");
                this.drawCards(this.currentPlayer, 4);
                this.next();
                this.notifyChannel(channel);
                this.tellCards(this.currentPlayer);
            }
        }
        if((card.type != 13 && card.suit != 0) && (card.type != 14 && card.suit != 0)) {
            this.notifyChannel(channel);
            this.tellCards(this.currentPlayer);
        }
    }

    this.notifyChannel = function(channel) {
        con.sendMessage(channel, this.currentPlayer + " is up, Top card: [" + this.discard.peek().toString() + "]");
    }

    this.warnPlayer = function(warning, nick, channel) {
        var player = this.players.get(nick);
        player.warnings += 1;
        if(player.warnings != 3)
            con.sendNotice(nick, warning + " [" + (3 - player.warnings) + " warnings left until kick]");
        else {
            con.sendMessage(channel, nick + " has been kicked from the game after 3 warnings");
            this.players.remove(nick);
            while(player.cards.cards.size() > 0)
                this.deck.push(player.cards.pop());
            this.deck.shuffle();
        }
        if(this.players.size() == 1) {
            con.sendMessage(channel, this.players.keySet().toArray()[0] + " has won by default");
            games.remove(channel);
        }
    }

    this.popCard = function() {
        if(this.deck.cards.size() == 0) {
            var top = this.discard.pop();
            this.discard.shuffle();
            for(var i = 0; i < this.discard.size(); i++)
                this.deck.push(this.discard.pop());
            this.discard.push(top);
        }
        return this.deck.pop();
    }
}

function cardFromString(string) {
    var suit = 0;
    string = string.toLowerCase();
    var arr = string.split(" ");
    if(arr.length > 1) {
        arr[1] = arr[1].substring(0, 1).toLowerCase();
        if(arr[1].equals("r"))
            suit = 1;
        else if(arr[1].equals("g"))
            suit = 2;
        else if(arr[1].equals("b"))
            suit = 3;
        else if(arr[1].equals("y"))
            suit = 4;
    }
    if(arr[0].startsWith("wd4"))
        return new Card(suit, 14);
    else if(arr[0].equals("w") || string.equals("wild"))
        return new Card(suit, 13);
    var c = arr[0].substring(0, 1);
    if(c.equals("r"))
        suit = 1;
    else if(c.equals("g"))
        suit = 2;
    else if(c.equals("b"))
        suit = 3;
    else if(c.equals("y"))
        suit = 4;
    else
        return null;


    var type = -1;
    arr[0] = arr[0].substring(1, arr[0].length());
    for(var i = 0; i < TYPES.length; i++)
        if(arr[0].equals(TYPES[i])) {
            type = i;
            break;
        }
    if(type == -1)
        return null;
    return new Card(suit, type);
}

var listener = new EventListener() {
    onEvent: function(e) {
        if(e instanceof IRCMessageEvent && e.message.recipient.startsWith("#")) {
            e.message.content = e.message.content.toLowerCase();
            var mLength = e.message.content.length();
            var c = e.message.content.substring(0, e.message.content.indexOf(" ") == -1? mLength: e.message.content.indexOf(" "));
            var cLength = c.length();
            var extra = (cLength == mLength? "": e.message.content.substring(cLength + 1));
            var ch = e.message.recipient;
            var s = e.message.sender.nick;
            if(c.equals("!uno")) {
                if(!games.containsKey(ch)) {
                    var time = 30;
                    con.sendMessage(ch, "A game of " + COL + "8U" + COL + "3N" + COL + 4 + "O" + ORIG + " will be starting in 30 seconds, please type !play to join in!");
                    games.put(ch, new Game(s));
                    GlobalManager.registerTimer(new Timer(30, 0, gameStartID));
                } else
                    con.sendNotice(s, "A game of UNO is already in progress!");
            } else if(games.containsKey(ch)) {
                var game = games.get(ch);
                if(game.state == RECRUITING) {
                    if(c.equals("!play") && !game.players.containsKey(s)) {
                        con.sendMessage(ch, s + " will play!");
                        game.players.put(s, new Player());
                    }
                } else if(game.players.containsKey(s)) {
                    var player = game.players.get(s);
                    if((c.equals("!play") || c.equals("!p")) && game.state == PLAYING) {
                        if(!s.equals(game.currentPlayer))
                            game.warnPlayer("It is not your turn!", s, ch);
                        else {
                            var card = cardFromString(extra);
                            if(card == null)
                                con.sendNotice(s, "Invalid card!");
                            else if(!player.cards.contains(card))
                                con.sendNotice(s, "You do not own that card!");
                            else if(player.drawcard != null && !card.equals(player.drawncard))
                                con.sendNotice(s, "You can only play the card you have drawn or !skip");
                            else if(card.matches(game.discard.peek())) {
                                player.cards.remove(card);
                                if(player.cards.cards.size() == 1)
                                    con.sendMessage(ch, s + " has UNO!");
                                else if(player.cards.cards.size() == 0) {
                                    con.sendMessage(ch, s + " has won the game!");
                                    games.remove(ch);
                                    return;
                                }
                                game.discard.push(card);
                                game.next(); 
                                game.handleCard(card, ch);
                            } else
                                con.sendNotice(s, "You cannot play that card");
                        }
                    } else if(c.equals("!draw") && game.state == PLAYING) {
                        if(!s.equals(game.currentPlayer))
                            game.warnPlayer("It is not your turn!", s, ch);
                        else {
                            if(player.drawncard != null)
                                game.warnPlayer("You may only draw one card per turn", s, ch);
                            else {
                                var card = game.popCard();
                                con.sendNotice(s, "You drew " + card.toString());
                                player.drawncard = card;
                                player.cards.push(card);
                                var canPlay = false;
                                for(var i = 0; i < player.cards.cards.size(); i++)
                                    if(player.cards.cards.get(i).matches(game.discard.peek())) {
                                        canPlay = true;
                                        break;
                                    }
                                if(!canPlay) {
                                    game.next();
                                    game.notifyChannel(ch);
                                    game.tellCards(game.currentPlayer);
                                }
                            }
                        }
                    } else if(c.equals("!choose") && game.state == CHOOSING_COLOR) {
                        if(!s.equals(game.currentPlayer))
                            game.warnPlayer("It is not your turn!", s, ch);
                        else {
                            if(extra.startsWith("r"))
                                game.discard.peek().suit = 1;
                            else if(extra.startsWith("g"))
                                game.discard.peek().suit = 2;
                            else if(extra.startsWith("b"))
                                game.discard.peek().suit = 3;
                            else if(extra.startsWith("y"))
                                game.discard.peek().suit = 4;
                            else {
                                game.warnPlayer("Please choose either 'red', 'green', 'blue or 'yellow'", s, ch);
                                return;
                            }
                            game.state = PLAYING;
                            game.next();
                            if(game.drawfour) {
                                con.sendMessage(ch, game.currentPlayer + " has drawn 4 cards and been skipped!");
                                game.drawCards(game.currentPlayer, 4);
                                game.next();
                                game.drawfour = false;
                            }
                            game.notifyChannel(ch);
                            game.tellCards(game.currentPlayer);
                        }
                    } else if(c.equals("!cancel") && game.admin.equals(s)) {
                        con.sendMessage(ch, "Game cancelled by game owner");
                        games.remove(ch);
                    } else if(c.equals("!boot") && game.admin.equals(s)) {
                        var p = game.players.get(extra);
                        if(p == null) {
                            con.sendNotice(s, "USAGE: !boot PLAYER");
                            return;
                        }
                        for(var i = 0; i < p.cards.cards.size(); i++)
                            game.deck.push(p.cards.pop());
                        game.deck.shuffle();
                        game.players.remove(extra);
                    }
                }
            }
        } else if(e instanceof TimerEvent) {
            if(e.source.timerID == gameStartID) {
                var array = games.keySet().toArray();
                for(var i = 0; i < java.lang.reflect.Array.getLength(array); i++) {
                    var game = games.get(array[i]);
                    if(game.state == RECRUITING) {
                        if(game.players.size() < 2) {
                            con.sendMessage(array[i], "Not enough players, game cancelled.");
                            games.remove(array[i]);
                            return;
                        } else if(game.players.size() > 10) {
                            con.sendMessage(array[i], "Too many players, game cancelled.");
                            games.remove(array[i]);
                            return;
                        }
                        con.sendMessage(array[i], "Dealing cards...");
                        game.state = PLAYING;
                        game.deal();
                        game.next();
                        game.handleCard(game.discard.peek(), array[i]);
                        return;
                    }
                }
            }
        } else if(e instanceof UncaughtExceptionEvent) {
            e.exception.printStackTrace();
        }
    }
};
GlobalManager.addEventListener(listener);