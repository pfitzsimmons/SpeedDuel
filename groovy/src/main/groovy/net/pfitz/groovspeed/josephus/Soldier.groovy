package net.pfitz.groovspeed.josephus
import groovy.transform.CompileStatic

/**
 * Created with IntelliJ IDEA.
 * User: patrick
 * Date: 6/23/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
class Soldier {
    int count;
    Soldier prev;
    Soldier next;
    def Soldier(int count) {
        this.count = count;
    }
    int shout(int shout, int deadif) {
        if (shout < deadif ) {
            return shout + 1;
        }
        this.prev.next = this.next;
        this.next.prev = this.prev;
        return 1;
    }

}
