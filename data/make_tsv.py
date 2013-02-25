from datetime import datetime
import random

f = open('data.csv', 'w')

for x in xrange(0, 10000):
    d = datetime(
        random.randint(2010, 2012), 
        random.randint(1, 11),
        random.randint(1, 25)
        )
    line = "%s\t%s\t%s\t%s\n" % (
        d.strftime('%Y-%m-%d'), 
        random.randint(10000, 90000),
        random.randint(10000, 90000),
        random.randint(10000, 90000),
        )
    f.write(line)
f.close()
