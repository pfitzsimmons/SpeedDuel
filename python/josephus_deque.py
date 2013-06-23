from collections import deque
from runner import Runner


class JosephusRunner(object):
    def do_once(self):
        persons = deque(xrange(0, 40))
        while True:
            try:
                persons.pop()
                persons.rotate(3)
            except IndexError:
                return

