package gossip.stat.server;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
*
* @author Tomasz
*/
@XStreamAlias("spell")
public class Spell {
    @XStreamAsAttribute
    @XStreamAlias(value="start")
    private long joined;
    
    @XStreamAsAttribute
    @XStreamAlias(value="end")
    private Long left = null;
    
    public long getJoined() {
        return joined;
    }

    public long getLeft() {
    	if(left==null) return Long.MAX_VALUE;
        return left;
    }
    public Spell(long joined) {
    	this.joined=joined;
    }
    public Spell(long joined, long left) {
    	this.joined=joined;
    	this.left=left;
    }
    public void setJoined(long timestamp) {
    	this.joined=timestamp;
    }
    
    public void setLeft(long timestamp) {
    	this.left=timestamp;
    }
}
