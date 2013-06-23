
from runner import Runner


class JosephusRunner(object):
    def do_once(self):
        chain = Chain(40)
        chain.kill(3)

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
