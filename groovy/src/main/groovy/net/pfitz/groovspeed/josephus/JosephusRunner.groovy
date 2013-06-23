package net.pfitz.groovspeed.josephus

import groovy.transform.CompileStatic
import net.pfitz.groovspeed.BaseRunner

/**
 * Created with IntelliJ IDEA.
 * User: patrick
 * Date: 6/23/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
class JosephusRunner extends BaseRunner{
    void runOnce() {
        Chain chain = new Chain(40);
        chain.kill(3);
    }
}
