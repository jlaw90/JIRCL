package org.newbiehacker.conlib.irc;

public interface NumericReplies {
    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  The first message sent after client registration. The text used varies widely </p>
     */
    public static final int RPL_WELCOME = 1;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Part of the post-registration greeting. Text varies widely </p>
     */
    public static final int RPL_YOURHOST = 2;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Part of the post-registration greeting. Text varies widely </p>
     */
    public static final int RPL_CREATED = 3;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Part of the post-registration greeting </p>
     */
    public static final int RPL_MYINFO = 4;

/**
 * <p> Origin: KineIRCd </p>
 * <p> Comments:&lt;server_modes&gt; &lt;server_modes_with_params&gt; Same as RFC2812 however with additional fields to avoid additional 005 burden. </p>
 */
// public static final int RPL_MYINFO = 4;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Sent </p>
     */
    public static final int RPL_BOUNCE = 5;

    /**
     * <p> Origin:  </p>
     * <p> Comments:  Also known as RPL_PROTOCTL (Bahamut, Unreal, Ultimate) </p>
     */
    public static final int RPL_ISUPPORT = 5;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_MAP = 6;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_MAPEND = 7;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Server notice mask (hex) </p>
     */
    public static final int RPL_SNOMASK = 8;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATMEMTOT = 9;

/**
 * <p> Origin:  </p>
 * <p> Comments: Sent to the client to redirect it to another server. Also known as RPL_REDIR </p>
 */
// public static final int RPL_BOUNCE = 10;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATMEM = 10;

    /**
     * <p> Origin: Hybrid? </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_YOURCOOKIE = 14;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAP = 15;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_MAPMORE = 16;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPEND = 17;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_YOURID = 42;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:  Sent to the client when their nickname was forced to change due to a collision </p>
     */
    public static final int RPL_SAVENICK = 43;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ATTEMPTINGJUNC = 50;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ATTEMPTINGREROUTE = 51;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments: &lt;upstream_sendq&gt;] See RFC </p>
     */
    public static final int RPL_TRACELINK = 200;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACECONNECTING = 201;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACEHANDSHAKE = 202;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACEUNKNOWN = 203;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACEOPERATOR = 204;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACEUSER = 205;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments: &lt;nick!user|*!*&gt;@&lt;host|server&gt; [V&lt;protocol_version&gt;] See RFC </p>
     */
    public static final int RPL_TRACESERVER = 206;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACESERVICE = 207;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACENEWTYPE = 208;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACECLASS = 209;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_TRACERECONNECT = 210;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:  Used instead of having multiple stats numerics </p>
     */
    public static final int RPL_STATS = 210;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSLINKINFO = 211;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSCOMMANDS = 212;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSCLINE = 213;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC), Also known as RPL_STATSOLDNLINE (ircu, Unreal) </p>
     */
    public static final int RPL_STATSNLINE = 214;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSILINE = 215;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSKLINE = 216;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSQLINE = 217;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSPLINE = 217;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSYLINE = 218;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  End of RPL_STATS* list. </p>
     */
    public static final int RPL_ENDOFSTATS = 219;

/**
 * <p> Origin: Hybrid </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSPLINE = 220;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSBLINE = 220;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Information </p>
     */
    public static final int RPL_UMODEIS = 221;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_MODLIST = 222;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SQLINE_NICK = 222;

/**
 * <p> Origin: Bahamut </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSBLINE = 222;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSELINE = 223;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSGLINE = 223;

    /**
     * <p> Origin: Hybrid,Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSFLINE = 224;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSTLINE = 224;

    /**
     * <p> Origin: Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSDLINE = 225;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSZLINE = 225;

/**
 * <p> Origin: Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSELINE = 225;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSCOUNT = 226;

/**
 * <p> Origin: Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSNLINE = 226;

/**
 * <p> Origin: Bahamut </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSGLINE = 227;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSVLINE = 227;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSQLINE = 228;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SERVICEINFO = 231;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFSERVICES = 232;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_RULES = 232;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SERVICE = 233;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  A service entry in the service list </p>
     */
    public static final int RPL_SERVLIST = 234;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Termination of an RPL_SERVLIST list </p>
     */
    public static final int RPL_SERVLISTEND = 235;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Verbose server list? </p>
     */
    public static final int RPL_STATSVERBOSE = 236;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Engine name? </p>
     */
    public static final int RPL_STATSENGINE = 237;

/**
 * <p> Origin: ircu </p>
 * <p> Comments: Feature lines? </p>
 */
// public static final int RPL_STATSFLINE = 238;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSIAUTH = 239;

/**
 * <p> Origin: RFC2812 </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSVLINE = 240;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSXLINE = 240;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSLLINE = 241;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSUPTIME = 242;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_STATSOLINE = 243;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to STATS (See RFC) </p>
     */
    public static final int RPL_STATSHLINE = 244;

    /**
     * <p> Origin: Bahamut,IRCnet,Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSSLINE = 245;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSPING = 246;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSTLINE = 246;

    /**
     * <p> Origin: Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSULINE = 246;

/**
 * <p> Origin: RFC2812 </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSBLINE = 247;

/**
 * <p> Origin: Hybrid,PTlink,Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSXLINE = 247;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSGLINE = 247;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSULINE = 248;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSDEFINE = 248;

/**
 * <p> Origin:  </p>
 * <p> Comments: Extension to RFC1459? </p>
 */
// public static final int RPL_STATSULINE = 249;

    /**
     * <p> Origin: Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSDEBUG = 249;

/**
 * <p> Origin: RFC2812 </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSDLINE = 250;

    /**
     * <p> Origin: ircu,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSCONN = 250;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to LUSERS command, other versions exist (eg. RFC2812); Text may vary. </p>
     */
    public static final int RPL_LUSERCLIENT = 251;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to LUSERS command - Number of IRC operators online </p>
     */
    public static final int RPL_LUSEROP = 252;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to LUSERS command - Number of unknown/unregistered connections </p>
     */
    public static final int RPL_LUSERUNKNOWN = 253;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to LUSERS command - Number of channels formed </p>
     */
    public static final int RPL_LUSERCHANNELS = 254;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to LUSERS command - Information about local connections; Text may vary. </p>
     */
    public static final int RPL_LUSERME = 255;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Start </p>
     */
    public static final int RPL_ADMINME = 256;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to ADMIN command (Location, first line) </p>
     */
    public static final int RPL_ADMINLOC1 = 257;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to ADMIN command (Location, second line) </p>
     */
    public static final int RPL_ADMINLOC2 = 258;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to ADMIN command (E-mail address of administrator) </p>
     */
    public static final int RPL_ADMINEMAIL = 259;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  See RFC </p>
     */
    public static final int RPL_TRACELOG = 261;

    /**
     * <p> Origin:  </p>
     * <p> Comments:  Extension to RFC1459? </p>
     */
    public static final int RPL_TRACEPING = 262;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Used to terminate a list of RPL_TRACE* replies </p>
     */
    public static final int RPL_TRACEEND = 262;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  When </p>
     */
    public static final int RPL_TRYAGAIN = 263;

    /**
     * <p> Origin: aircd,Hybrid,Hybrid,Bahamut </p>
     * <p> Comments:  Also known as RPL_CURRENT_LOCAL </p>
     */
    public static final int RPL_LOCALUSERS = 265;

    /**
     * <p> Origin: aircd,Hybrid,Hybrid,Bahamut </p>
     * <p> Comments:  Also known as RPL_CURRENT_GLOBAL </p>
     */
    public static final int RPL_GLOBALUSERS = 266;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_START_NETSTAT = 267;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NETSTAT = 268;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_END_NETSTAT = 269;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_PRIVS = 270;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SILELIST = 271;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFSILELIST = 272;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOTIFY = 273;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDNOTIFY = 274;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_STATSDELTA = 274;

/**
 * <p> Origin: ircu,Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_STATSDLINE = 275;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_VCHANEXIST = 276;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_VCHANLIST = 277;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_VCHANHELP = 278;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_GLIST = 280;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFGLIST = 281;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ACCEPTLIST = 281;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFACCEPT = 282;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_JUPELIST = 282;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ALIST = 283;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFJUPELIST = 283;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFALIST = 284;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_FEATURE = 284;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_GLIST_HASH = 285;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_HANDLE = 285;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NEWHOSTIS = 285;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_USERS = 286;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHKHEAD = 286;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_CHOPS = 287;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANUSER = 287;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_VOICES = 288;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_PATCHHEAD = 288;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_AWAY = 289;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_PATCHCON = 289;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_OPERS = 290;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPHDR = 290;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_DATASTR = 290;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_BANNED = 291;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPOP = 291;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFCHECK = 291;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_BANS = 292;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPTLR = 292;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_INVITE = 293;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPHLP = 293;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_INVITES = 294;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPFWD = 294;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_KICK = 295;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_HELPIGN = 295;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANINFO_KICKS = 296;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_END_CHANINFO = 299;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Dummy reply, supposedly only used for debugging/testing new features, however has appeared in production daemons. </p>
     */
    public static final int RPL_NONE = 300;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used in reply to a command directed at a user who is marked as away </p>
     */
    public static final int RPL_AWAY = 301;

/**
 * <p> Origin: KineIRCd </p>
 * <p> Comments: Identical </p>
 */
// public static final int RPL_AWAY = 301;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply used by USERHOST (see RFC) </p>
     */
    public static final int RPL_USERHOST = 302;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to the ISON command (see RFC) </p>
     */
    public static final int RPL_ISON = 303;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_TEXT = 304;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply from AWAY when no longer marked as away </p>
     */
    public static final int RPL_UNAWAY = 305;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply from AWAY when marked away </p>
     */
    public static final int RPL_NOWAWAY = 306;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_USERIP = 307;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISREGNICK = 307;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SUSERHOST = 307;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOTIFYACTION = 308;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISADMIN = 308;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_RULESSTART = 308;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NICKTRACE = 309;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISSADMIN = 309;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFRULES = 309;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISHELPER = 309;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISSVCMSG = 310;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISHELPOP = 310;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISSERVICE = 310;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - Information about the user </p>
     */
    public static final int RPL_WHOISUSER = 311;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - What server they're on </p>
     */
    public static final int RPL_WHOISSERVER = 312;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - User has IRC Operator privileges </p>
     */
    public static final int RPL_WHOISOPERATOR = 313;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOWAS - Information about the user </p>
     */
    public static final int RPL_WHOWASUSER = 314;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used to terminate a list of RPL_WHOREPLY replies </p>
     */
    public static final int RPL_ENDOFWHO = 315;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISCHANOP = 316;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - Idle information </p>
     */
    public static final int RPL_WHOISIDLE = 317;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - End of list </p>
     */
    public static final int RPL_ENDOFWHOIS = 318;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOIS - Channel list for user (See RFC) </p>
     */
    public static final int RPL_WHOISCHANNELS = 319;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISVIRT = 320;

    /**
     * <p> Origin: Anothernet </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOIS_HIDDEN = 320;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISSPECIAL = 320;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Channel list - Header </p>
     */
    public static final int RPL_LISTSTART = 321;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Channel list - A channel </p>
     */
    public static final int RPL_LIST = 322;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Channel list - End of list </p>
     */
    public static final int RPL_LISTEND = 323;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANNELMODEIS = 324;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_UNIQOPIS = 325;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANNELPASSIS = 325;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOCHANPASS = 326;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHPASSUNKNOWN = 327;

    /**
     * <p> Origin: Bahamut,AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANNEL_URL = 328;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CREATIONTIME = 329;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOWAS_TIME = 330;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISACCOUNT = 330;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Response to TOPIC when no topic is set </p>
     */
    public static final int RPL_NOTOPIC = 331;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Response to TOPIC with the set topic </p>
     */
    public static final int RPL_TOPIC = 332;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_TOPICWHOTIME = 333;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_LISTUSAGE = 334;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_COMMANDSYNTAX = 334;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_LISTSYNTAX = 334;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISBOT = 335;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CHANPASSOK = 338;

    /**
     * <p> Origin: ircu,Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISACTUALLY = 338;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_BADCHANPASS = 339;

/**
 * <p> Origin: ircu </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_USERIP = 340;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int RPL_INVITING = 341;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by a server answering a SUMMON message to indicate that it is summoning that user </p>
     */
    public static final int RPL_SUMMONING = 342;

    /**
     * <p> Origin: GameSurge </p>
     * <p> Comments: invited&gt; has been invited by &lt;user issuing invite&gt; Sent to users on a channel when an INVITE command has been issued </p>
     */
    public static final int RPL_INVITED = 345;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  An invite mask for the invite mask list </p>
     */
    public static final int RPL_INVITELIST = 346;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Termination of an RPL_INVITELIST list </p>
     */
    public static final int RPL_ENDOFINVITELIST = 347;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  An exception mask for the exception mask list. Also known as RPL_EXLIST (Unreal, Ultimate) </p>
     */
    public static final int RPL_EXCEPTLIST = 348;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Termination of an RPL_EXCEPTLIST list. Also known as RPL_ENDOFEXLIST (Unreal, Ultimate) </p>
     */
    public static final int RPL_ENDOFEXCEPTLIST = 349;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply by the server showing its version details, however this format is not often adhered to </p>
     */
    public static final int RPL_VERSION = 351;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to vanilla WHO (See RFC). This format can be very different if the 'WHOX' version of the command is used (see ircu). </p>
     */
    public static final int RPL_WHOREPLY = 352;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to NAMES (See RFC) </p>
     */
    public static final int RPL_NAMREPLY = 353;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_WHOSPCRPL = 354;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_NAMREPLY_ = 355;

/**
 * <p> Origin: AustHex </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAP = 357;

/**
 * <p> Origin: AustHex </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPMORE = 358;

/**
 * <p> Origin: AustHex </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPEND = 359;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_KILLDONE = 361;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CLOSING = 362;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_CLOSEEND = 363;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to the LINKS command </p>
     */
    public static final int RPL_LINKS = 364;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_LINKS list </p>
     */
    public static final int RPL_ENDOFLINKS = 365;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_NAMREPLY list </p>
     */
    public static final int RPL_ENDOFNAMES = 366;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  A ban-list item (See RFC); &lt;time left&gt; and &lt;reason&gt; are additions used by KineIRCd </p>
     */
    public static final int RPL_BANLIST = 367;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_BANLIST list </p>
     */
    public static final int RPL_ENDOFBANLIST = 368;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to WHOWAS - End of list </p>
     */
    public static final int RPL_ENDOFWHOWAS = 369;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to INFO </p>
     */
    public static final int RPL_INFO = 371;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to MOTD </p>
     */
    public static final int RPL_MOTD = 372;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_INFOSTART = 373;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_INFO list </p>
     */
    public static final int RPL_ENDOFINFO = 374;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Start of an RPL_MOTD list </p>
     */
    public static final int RPL_MOTDSTART = 375;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_MOTD list </p>
     */
    public static final int RPL_ENDOFMOTD = 376;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_KICKEXPIRED = 377;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:  Used </p>
     */
    public static final int RPL_SPAM = 377;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_BANEXPIRED = 378;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISHOST = 378;

/**
 * <p> Origin: AustHex </p>
 * <p> Comments: Used </p>
 */
// public static final int RPL_MOTD = 378;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_KICKLINKED = 379;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOISMODES = 379;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_BANLINKED = 380;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_YOURHELPER = 380;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Successful reply from OPER </p>
     */
    public static final int RPL_YOUREOPER = 381;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Successful reply from REHASH </p>
     */
    public static final int RPL_REHASHING = 382;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Sent upon successful registration of a service </p>
     */
    public static final int RPL_YOURESERVICE = 383;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_MYPORTIS = 384;

    /**
     * <p> Origin: AustHex,Hybrid,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOTOPERANYMORE = 385;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_QLIST = 386;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_IRCOPS = 386;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFQLIST = 387;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFIRCOPS = 387;

/**
 * <p> Origin: Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_ALIST = 388;

/**
 * <p> Origin: Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_ENDOFALIST = 389;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Response to the TIME command. The string format may vary greatly. Also see #679. </p>
     */
    public static final int RPL_TIME = 391;

/**
 * <p> Origin: ircu </p>
 * <p> Comments: This extention adds the timestamp and timestamp-offet information for clients. </p>
 */
// public static final int RPL_TIME = 391;

/**
 * <p> Origin: bdq-ircd </p>
 * <p> Comments: Timezone </p>
 */
// public static final int RPL_TIME = 391;

/**
 * <p> Origin:  </p>
 * <p> Comments: Yet another variation, including the time broken down into its components. Time is supposedly relative to UTC. </p>
 */
// public static final int RPL_TIME = 391;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Start of an RPL_USERS list </p>
     */
    public static final int RPL_USERSSTART = 392;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Response to the USERS command (See RFC) </p>
     */
    public static final int RPL_USERS = 393;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Termination of an RPL_USERS list </p>
     */
    public static final int RPL_ENDOFUSERS = 394;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Reply to USERS when nobody is logged in </p>
     */
    public static final int RPL_NOUSERS = 395;

    /**
     * <p> Origin: Undernet </p>
     * <p> Comments:  Reply to a user when user mode +x (host masking) was set successfully </p>
     */
    public static final int RPL_HOSTHIDDEN = 396;

    /**
     * <p> Origin:  </p>
     * <p> Comments:  Sent when an error occured executing a command, but it is not specifically known why the command could not be executed. </p>
     */
    public static final int ERR_UNKNOWNERROR = 400;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used to indicate the nickname parameter supplied to a command is currently unused </p>
     */
    public static final int ERR_NOSUCHNICK = 401;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used to indicate the server name given currently doesn't exist </p>
     */
    public static final int ERR_NOSUCHSERVER = 402;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used to indicate the given channel name is invalid, or does not exist </p>
     */
    public static final int ERR_NOSUCHCHANNEL = 403;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Sent to a user who does not have the rights to send a message to a channel </p>
     */
    public static final int ERR_CANNOTSENDTOCHAN = 404;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Sent to a user when they have joined the maximum number of allowed channels and they tried to join another channel </p>
     */
    public static final int ERR_TOOMANYCHANNELS = 405;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by WHOWAS to indicate there was no history information for a given nickname </p>
     */
    public static final int ERR_WASNOSUCHNICK = 406;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  The given target(s) for a command are ambiguous in that they relate to too many targets </p>
     */
    public static final int ERR_TOOMANYTARGETS = 407;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Returned to a client which is attempting to send an SQUERY (or other message) to a service which does not exist </p>
     */
    public static final int ERR_NOSUCHSERVICE = 408;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOCOLORSONCHAN = 408;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  PING or PONG message missing the originator parameter which is required since these commands must work without valid prefixes </p>
     */
    public static final int ERR_NOORIGIN = 409;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when no recipient is given with a command </p>
     */
    public static final int ERR_NORECIPIENT = 411;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when NOTICE/PRIVMSG is used with no message given </p>
     */
    public static final int ERR_NOTEXTTOSEND = 412;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used when a message is being sent to a mask without being limited to a top-level domain (i.e. * instead of *.au) </p>
     */
    public static final int ERR_NOTOPLEVEL = 413;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Used when a message is being sent to a mask with a wild-card for a top level domain (i.e. *.*) </p>
     */
    public static final int ERR_WILDTOPLEVEL = 414;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Used when a message is being sent to a mask with an invalid syntax </p>
     */
    public static final int ERR_BADMASK = 415;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_TOOMANYMATCHES = 416;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Same as ERR_TOOMANYMATCHES </p>
     */
    public static final int ERR_QUERYTOOLONG = 416;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LENGTHTRUNCATED = 419;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when the given command is unknown to the server (or hidden because of lack of access rights) </p>
     */
    public static final int ERR_UNKNOWNCOMMAND = 421;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Sent when there is no MOTD to send the client </p>
     */
    public static final int ERR_NOMOTD = 422;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_NOADMININFO = 423;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Generic error message used to report a failed file operation during the processing of a command </p>
     */
    public static final int ERR_FILEERROR = 424;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOOPERMOTD = 425;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_TOOMANYAWAY = 429;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:  Returned by NICK when the user is not allowed to change their nickname due to a channel event (channel mode +E) </p>
     */
    public static final int ERR_EVENTNICKCHANGE = 430;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when a nickname parameter expected for a command isn't found </p>
     */
    public static final int ERR_NONICKNAMEGIVEN = 431;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_ERRONEUSNICKNAME = 432;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the NICK command when the given nickname is already in use </p>
     */
    public static final int ERR_NICKNAMEINUSE = 433;

    /**
     * <p> Origin: AustHex? </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_SERVICENAMEINUSE = 434;

    /**
     * <p> Origin: Unreal,Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NORULES = 434;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_SERVICECONFUSED = 435;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BANONCHAN = 435;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by a server to a client when it detects a nickname collision </p>
     */
    public static final int ERR_NICKCOLLISION = 436;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Return when the target is unable to be reached temporarily, eg. a delay mechanism in play, or a service being offline </p>
     */
    public static final int ERR_UNAVAILRESOURCE = 437;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BANNICKCHANGE = 437;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Also known as ERR_NCHANGETOOFAST (Unreal, Ultimate) </p>
     */
    public static final int ERR_NICKTOOFAST = 438;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_DEAD = 438;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Also known as many other things, RPL_INVTOOFAST, RPL_MSGTOOFAST etc </p>
     */
    public static final int ERR_TARGETTOOFAST = 439;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_SERVICESDOWN = 440;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the server to indicate that the target user of the command is not on the given channel </p>
     */
    public static final int ERR_USERNOTINCHANNEL = 441;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the server whenever a client tries to perform a channel effecting command for which the client is not a member </p>
     */
    public static final int ERR_NOTONCHANNEL = 442;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when a client tries to invite a user to a channel they're already on </p>
     */
    public static final int ERR_USERONCHANNEL = 443;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the SUMMON command if a given user was not logged in and could not be summoned </p>
     */
    public static final int ERR_NOLOGIN = 444;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by SUMMON when it has been disabled or not implemented </p>
     */
    public static final int ERR_SUMMONDISABLED = 445;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by USERS when it has been disabled or not implemented </p>
     */
    public static final int ERR_USERSDISABLED = 446;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NONICKCHANGE = 447;

    /**
     * <p> Origin: Undernet </p>
     * <p> Comments:  Returned when a requested feature is not implemented (and cannot be completed) </p>
     */
    public static final int ERR_NOTIMPLEMENTED = 449;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the server to indicate that the client must be registered before the server will allow it to be parsed in detail </p>
     */
    public static final int ERR_NOTREGISTERED = 451;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_IDCOLLISION = 452;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NICKLOST = 453;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_HOSTILENAME = 455;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ACCEPTFULL = 456;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ACCEPTEXIST = 457;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ACCEPTNOT = 458;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Not allowed to become an invisible operator? </p>
     */
    public static final int ERR_NOHIDING = 459;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOTFORHALFOPS = 460;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the server by any command which requires more parameters than the number of parameters given </p>
     */
    public static final int ERR_NEEDMOREPARAMS = 461;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the server to any link which attempts to register again </p>
     */
    public static final int ERR_ALREADYREGISTERED = 462;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_NOPERMFORHOST = 463;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by the PASS command to indicate the given password was required and was either not given or was incorrect </p>
     */
    public static final int ERR_PASSWDMISMATCH = 464;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned to a client after an attempt to register on a server configured to ban connections from that client </p>
     */
    public static final int ERR_YOUREBANNEDCREEP = 465;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Sent by a server to a user to inform that access to the server will soon be denied </p>
     */
    public static final int ERR_YOUWILLBEBANNED = 466;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when the channel key for a channel has already been set </p>
     */
    public static final int ERR_KEYSET = 467;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_INVALIDUSERNAME = 468;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ONLYSERVERSCANCHANGE = 468;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LINKSET = 469;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LINKCHANNEL = 470;

    /**
     * <p> Origin: aircd </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_KICKEDFROMCHAN = 470;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when attempting to join a channel which is set +l and is already full </p>
     */
    public static final int ERR_CHANNELISFULL = 471;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when a given mode is unknown </p>
     */
    public static final int ERR_UNKNOWNMODE = 472;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when attempting to join a channel which is invite only without an invitation </p>
     */
    public static final int ERR_INVITEONLYCHAN = 473;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when attempting to join a channel a user is banned from </p>
     */
    public static final int ERR_BANNEDFROMCHAN = 474;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned when attempting to join a key-locked channel either without a key or with the wrong key </p>
     */
    public static final int ERR_BADCHANNELKEY = 475;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  The given channel mask was invalid </p>
     */
    public static final int ERR_BADCHANMASK = 476;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_NOCHANMODES = 477;

    /**
     * <p> Origin: Bahamut,ircu,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NEEDREGGEDNICK = 477;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Returned when a channel access list (i.e. ban list etc) is full and cannot be added to </p>
     */
    public static final int ERR_BANLISTFULL = 478;

    /**
     * <p> Origin: Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADCHANNAME = 479;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LINKFAIL = 479;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOULINE = 480;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_CANNOTKNOCK = 480;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by any command requiring special privileges (eg. IRC operator) to indicate the operation was unsuccessful </p>
     */
    public static final int ERR_NOPRIVILEGES = 481;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by any command requiring special channel privileges (eg. channel operator) to indicate the operation was unsuccessful </p>
     */
    public static final int ERR_CHANOPRIVSNEEDED = 482;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned by KILL to anyone who tries to kill a server </p>
     */
    public static final int ERR_CANTKILLSERVER = 483;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Sent by the server to a user upon connection to indicate the restricted nature of the connection (i.e. usermode +r) </p>
     */
    public static final int ERR_RESTRICTED = 484;

    /**
     * <p> Origin: Undernet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ISCHANSERVICE = 484;

    /**
     * <p> Origin: Bahamut,Hybrid,PTlink </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_DESYNC = 484;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ATTACKDENY = 484;

    /**
     * <p> Origin: RFC2812 </p>
     * <p> Comments:  Any </p>
     */
    public static final int ERR_UNIQOPRIVSNEEDED = 485;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_KILLDENY = 485;

    /**
     * <p> Origin: PTlink </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_CANTKICKADMIN = 485;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ISREALSERVICE = 485;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NONONREG = 486;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_HTMDISABLED = 486;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ACCOUNTONLY = 486;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_CHANTOORECENT = 487;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_MSGSERVICES = 487;

    /**
     * <p> Origin: IRCnet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_TSLESSCHAN = 488;

    /**
     * <p> Origin: Undernet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_VOICENEEDED = 489;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_SECUREONLYCHAN = 489;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_NOOPERHOST = 491;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOSERVICEHOST = 492;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOFEATURE = 493;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADFEATURE = 494;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADLOGTYPE = 495;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADLOGSYS = 496;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADLOGVALUE = 497;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ISOPERLCHAN = 498;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Works just like ERR_CHANOPRIVSNEEDED except it indicates that owner status (+q) is needed. Also see #482. </p>
     */
    public static final int ERR_CHANOWNPRIVNEEDED = 499;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_UMODEUNKNOWNFLAG = 501;

    /**
     * <p> Origin: RFC1459 </p>
     * <p> Comments:  Error sent to any user trying to view or change the user mode for a user other than themselves </p>
     */
    public static final int ERR_USERSDONTMATCH = 502;

    /**
     * <p> Origin: Hybrid </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_GHOSTEDCLIENT = 503;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:  Warning about Virtual-World being turned off. Obsoleted in favour for RPL_MODECHANGEWARN Also see #662. </p>
     */
    public static final int ERR_VWORLDWARN = 503;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_USERNOTONSERV = 504;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_SILELISTFULL = 511;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:  Also known as ERR_NOTIFYFULL (aircd), I presume they are the same </p>
     */
    public static final int ERR_TOOMANYWATCH = 512;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:  Also </p>
     */
    public static final int ERR_BADPING = 513;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_INVALID_ERROR = 514;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_TOOMANYDCC = 514;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADEXPIRE = 515;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_DONTCHEAT = 516;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_DISABLED = 517;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_NOINVITE = 518;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LONGMASK = 518;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_ADMONLY = 519;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_TOOMANYUSERS = 519;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_OPERONLY = 520;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_MASKTOOWIDE = 520;

    /**
     * <p> Origin: AustHex </p>
     * <p> Comments:  This is considered obsolete in favour of ERR_TOOMANYMATCHES, and should no longer be used. Also see #416. </p>
     */
    public static final int ERR_WHOTRUNC = 520;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_LISTSYNTAX = 521;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_WHOSYNTAX = 522;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_WHOLIMEXCEED = 523;

    /**
     * <p> Origin: ircu </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_QUARANTINED = 524;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_OPERSPVERIFY = 524;

    /**
     * <p> Origin: CAPABUSERCMDPFX </p>
     * <p> Comments:  Proposed. </p>
     */
    public static final int ERR_REMOTEPFX = 525;

    /**
     * <p> Origin: CAPABUSERCMDPFX </p>
     * <p> Comments:  Proposed. </p>
     */
    public static final int ERR_PFXUNROUTABLE = 526;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_BADHOSTMASK = 550;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_HOSTUNAVAIL = 551;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_USINGSLINE = 552;

    /**
     * <p> Origin: QuakeNet </p>
     * <p> Comments:   </p>
     */
    public static final int ERR_STATSSLINE = 553;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_LOGON = 600;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_LOGOFF = 601;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WATCHOFF = 602;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WATCHSTAT = 603;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOWON = 604;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_NOWOFF = 605;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WATCHLIST = 606;

    /**
     * <p> Origin: Bahamut,Unreal </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFWATCHLIST = 607;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WATCHCLEAR = 608;

/**
 * <p> Origin: Unreal </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPMORE = 610;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ISOPER = 610;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ISLOCOP = 611;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ISNOTOPER = 612;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFISOPER = 613;

/**
 * <p> Origin: PTlink </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPMORE = 615;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_WHOISMODES = 615;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_WHOISHOST = 616;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_DCCSTATUS = 617;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_WHOISBOT = 617;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_DCCLIST = 618;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFDCCLIST = 619;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_WHOWASHOST = 619;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_DCCINFO = 620;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_RULESSTART = 620;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_RULES = 621;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_ENDOFRULES = 622;

/**
 * <p> Origin: Ultimate </p>
 * <p> Comments:  </p>
 */
// public static final int RPL_MAPMORE = 623;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_OMOTDSTART = 624;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_OMOTD = 625;

    /**
     * <p> Origin:  </p>
     * <p> Comments:   </p>
     */
    public static final int RPL__ENDOFO_ULTIMATE = 626;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_SETTINGS = 630;

    /**
     * <p> Origin: Ultimate </p>
     * <p> Comments:   </p>
     */
    public static final int RPL_ENDOFSETTINGS = 631;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Never actually used by Unreal - was defined however the feature that would have used this numeric was never created. </p>
     */
    public static final int RPL_DUMPING = 640;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Never actually used by Unreal - was defined however the feature that would have used this numeric was never created. </p>
     */
    public static final int RPL_DUMPRPL = 641;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Never actually used by Unreal - was defined however the feature that would have used this numeric was never created. </p>
     */
    public static final int RPL_EODUMP = 642;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned from the TRACEROUTE IRC-Op command when tracerouting a host </p>
     */
    public static final int RPL_TRACEROUTE_HOP = 660;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Start of an RPL_TRACEROUTE_HOP list </p>
     */
    public static final int RPL_TRACEROUTE_START = 661;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Plain </p>
     */
    public static final int RPL_MODECHANGEWARN = 662;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Used </p>
     */
    public static final int RPL_CHANREDIR = 663;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_SERVMODEIS = 664;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_OTHERUMODEIS = 665;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Generic response for new lists to save numerics. </p>
     */
    public static final int RPL_ENDOF_GENERIC = 666;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned </p>
     */
    public static final int RPL_WHOWASDETAILS = 670;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_WHOISSECURE = 671;

    /**
     * <p> Origin: Ithildin </p>
     * <p> Comments:  Returns a full list of modes that are unknown when a client issues a MODE command (rather than one numeric per mode) </p>
     */
    public static final int RPL_UNKNOWNMODES = 672;

    /**
     * <p> Origin: Ithildin </p>
     * <p> Comments:  Returns a full list of modes that cannot be set when a client issues a MODE command </p>
     */
    public static final int RPL_CANNOTSETMODES = 673;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply </p>
     */
    public static final int RPL_LUSERSTAFF = 678;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Optionally </p>
     */
    public static final int RPL_TIMEONSERVERIS = 679;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  A reply to the NETWORKS command when requesting a list of known networks (within the IIRC domain). </p>
     */
    public static final int RPL_NETWORKS = 682;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply to the LANGUAGE command, informing the client of the language(s) it has set </p>
     */
    public static final int RPL_YOURLANGUAGEIS = 687;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  A language reply to LANGUAGE when requesting a list of known languages </p>
     */
    public static final int RPL_LANGUAGE = 688;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  The </p>
     */
    public static final int RPL_WHOISSTAFF = 689;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply to WHOIS command - A list of languages someone can speak. The language codes are comma delimitered. </p>
     */
    public static final int RPL_WHOISLANGUAGE = 690;

/**
 * <p> Origin: RatBox </p>
 * <p> Comments: Output from the MODLIST command </p>
 */
// public static final int RPL_MODLIST = 702;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Terminates MODLIST output </p>
     */
    public static final int RPL_ENDOFMODLIST = 703;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Start of HELP command output </p>
     */
    public static final int RPL_HELPSTART = 704;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Output from HELP command </p>
     */
    public static final int RPL_HELPTXT = 705;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  End of HELP command output </p>
     */
    public static final int RPL_ENDOFHELP = 706;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Output from 'extended' trace </p>
     */
    public static final int RPL_ETRACEFULL = 708;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Output from 'extended' trace </p>
     */
    public static final int RPL_ETRACE = 709;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Message delivered using KNOCK command </p>
     */
    public static final int RPL_KNOCK = 710;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Message returned from using KNOCK command </p>
     */
    public static final int RPL_KNOCKDLVR = 711;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Message returned when too many KNOCKs for a channel have been sent by a user </p>
     */
    public static final int ERR_TOOMANYKNOCK = 712;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Message returned from KNOCK when the channel can be freely joined by the user </p>
     */
    public static final int ERR_CHANOPEN = 713;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Message returned from KNOCK when the user has used KNOCK on a channel they have already joined </p>
     */
    public static final int ERR_KNOCKONCHAN = 714;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Returned from KNOCK when the command has been disabled </p>
     */
    public static final int ERR_KNOCKDISABLED = 715;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Sent to indicate the given target is set +g (server-side ignore) </p>
     */
    public static final int RPL_TARGUMODEG = 716;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Sent following a PRIVMSG/NOTICE to indicate the target has been notified of an attempt to talk to them while they are set +g </p>
     */
    public static final int RPL_TARGNOTIFY = 717;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Sent </p>
     */
    public static final int RPL_UMODEGMSG = 718;

/**
 * <p> Origin: RatBox </p>
 * <p> Comments: IRC Operator MOTD header, sent upon OPER command </p>
 */
// public static final int RPL_OMOTDSTART = 720;

/**
 * <p> Origin: RatBox </p>
 * <p> Comments: IRC Operator MOTD text (repeated, usually) </p>
 */
// public static final int RPL_OMOTD = 721;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  IRC operator MOTD footer </p>
     */
    public static final int RPL_ENDOFOMOTD = 722;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Returned from an oper command when the IRC operator does not have the relevant operator privileges. </p>
     */
    public static final int ERR_NOPRIVS = 723;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Reply from an oper command reporting how many users match a given user@host mask </p>
     */
    public static final int RPL_TESTMARK = 724;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Reply from an oper command reporting relevant I/K lines that will match a given user@host </p>
     */
    public static final int RPL_TESTLINE = 725;

    /**
     * <p> Origin: RatBox </p>
     * <p> Comments:  Reply from oper command reporting no I/K lines match the given user@host </p>
     */
    public static final int RPL_NOTESTLINE = 726;

    /**
     * <p> Origin: Ithildin </p>
     * <p> Comments:  Used </p>
     */
    public static final int RPL_XINFO = 771;

    /**
     * <p> Origin: Ithildin </p>
     * <p> Comments:  Start of an RPL_XINFO list </p>
     */
    public static final int RPL_XINFOSTART = 773;

    /**
     * <p> Origin: Ithildin </p>
     * <p> Comments:  Termination of an RPL_XINFO list </p>
     */
    public static final int RPL_XINFOEND = 774;

    /**
     * <p> Origin: Unreal </p>
     * <p> Comments:  Works </p>
     */
    public static final int ERR_CANNOTDOCOMMAND = 972;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply to MODE when a user cannot change a user mode </p>
     */
    public static final int ERR_CANNOTCHANGEUMODE = 973;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply to MODE when a user cannot change a channel mode </p>
     */
    public static final int ERR_CANNOTCHANGECHANMODE = 974;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Reply to MODE when a user cannot change a server mode </p>
     */
    public static final int ERR_CANNOTCHANGESERVERMODE = 975;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_CANNOTSENDTONICK = 976;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned by MODE to inform the client they used an unknown server mode character. </p>
     */
    public static final int ERR_UNKNOWNSERVERMODE = 977;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned by MODE to inform the client the server has been set mode +L by an administrator to stop server modes being changed </p>
     */
    public static final int ERR_SERVERMODELOCK = 979;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_BADCHARENCODING = 980;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_TOOMANYLANGUAGES = 981;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned by the LANGUAGE command to tell the client it has specified an unknown language code. </p>
     */
    public static final int ERR_NOLANGUAGE = 982;

    /**
     * <p> Origin: KineIRCd </p>
     * <p> Comments:  Returned </p>
     */
    public static final int ERR_TEXTTOOSHORT = 983;

    /**
     * <p> Origin: Bahamut </p>
     * <p> Comments:  Also known as ERR_NUMERICERR (Unreal) </p>
     */
    public static final int ERR_NUMERIC_ERR = 999;
}