
import collections
import string
import time

def get_people():
    return 
# http://blog.dhananjaynene.com/2008/07/performance-comparison-c-java-python-ruby-jython-jruby-groovy/

CHAIN_SIZE = 40
def do_trial_with_deque():
    letters = string.ascii_letters * (CHAIN_SIZE // 40)
    table = collections.deque(list(letters[:CHAIN_SIZE]))
    x = 0
    last_popped = ''
    for x in xrange(0, 1000):
        table.rotate(3)
        try:
            last_popped = table.popleft()
        except IndexError:
            return

def do_trial_with_list():
    letters = string.ascii_letters * (CHAIN_SIZE // 40)
    table = list(letters[:CHAIN_SIZE])
    x = 0
    last_popped = ''
    for x in xrange(0, 1000):
        x += 3
        if len(table) == 0:
            return
        table.pop(x % len(table))
        


class Person(object):
    def __init__(self,count):
        self.count = count;
        self.prev = None


        self.next = None
    def shout(self,shout,deadif):
        if (shout < deadif): return (shout + 1)
        self.prev.next = self.next
        self.next.prev = self.prev
        return 1

class Chain(object):
    def __init__(self,size):
        self.first = None
        last = None
        for i in range(size):
            current = Person(i)
            if self.first == None : self.first = current
            if last != None :
                last.next = current
                current.prev = last
            last = current
        self.first.prev = last
        last.next = self.first
    def kill(self,nth):
        current = self.first
        shout = 1
        while current.next != current:
            shout = current.shout(shout,nth)
            current = current.next
        self.first = current
        return current

def do_trial_with_classes():
    chain = Chain(CHAIN_SIZE)
    chain.kill(3)
    
def main(num_times=1000):
    meth = do_trial_with_classes
    start = time.time()* 1000000
    for x in range(0, num_times):
        meth()
    end = time.time()*1000000
    print 'Micros per run ', (end-start) / num_times

if __name__ == "__main__":
    main()
