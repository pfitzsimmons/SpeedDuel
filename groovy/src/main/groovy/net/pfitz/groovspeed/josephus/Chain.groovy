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
class Chain {
    Soldier first;
    public Chain(int size) {
        Soldier last = null;
        Soldier current = null;
        for (int i = 0; i < size; i++) {
            current = new Soldier(i);
            if (first == null) {
                first = current;
            }
            if (last != null ) {
                last.next = current;
                current.prev = last;
            }
            last = current;
        }
        first.prev = last;
        last.next = first;
    }

    Soldier kill(int nth) {
        Soldier current = first;
        int shout = 1;
        int counter = 0;
        while(!current.equals(current.next)) {
        // Wow, using != makes this run in 20.8 microseconds instead of 1.3 microseconds!!!!
        // while (current != current.next) {
            shout = current.shout(shout, nth);
            current = current.next;
            counter++;
        }
        first = current;
        assert counter == 117
        assert current.count == 27;
        return current;
    }



}

