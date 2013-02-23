import itertools
from operator import itemgetter
import simplejson as json
import time

import random

#f = open('data.csv', 'w')
#print 'WRITING'
#for x in xrange(0, 100000):
#    line = '%s\t%s\t%s\t%s\n' % (random.randint(0, 100000000), random.randint(0, 100000000), random.randint(0, 100000000), random.randint(0, 100000000))
#    f.write(line)
#f.close()
print 'DONE WRITING'
f = open('../data/data.csv', 'r')
data_str = f.read()
f.close()

COUNT = 10


def mapper(line):
    parts = line.split('\t')
    return (long(parts[2]), long(parts[0]))

def sumit(mystr):
    lines = mystr.split('\n')
    lines = filter(bool, lines)
    line_tuples = []
    new_lines = []
    for line in lines:
        new_lines.append('[' + line.replace('\t', ',') + ']')
        #results = [map(int, itertools.chain(itertools.imap(str.split, lines)))]
        #print 'RESULTS ', results[:100]
        #line_tuples.append('')
        #parts = line.split('\t')
        #parts = ['123', '321', '323']
        #line_tuples.append((long(parts[2]), long(parts[0])))
    new_txt = '[' + ', '.join(new_lines) + ']'
    line_tuples = json.loads(new_txt)

   # line_tuples = map(mapper, lines)
    #line_tuples = lines
    line_tuples.sort()
    line_tuples = line_tuples[:50000]
    numbers = map(itemgetter(1), line_tuples)
    total = sum(numbers)
    return total
        

        
# Read a csv file of 100,000 entries
# sort the third column, and sum the entries of the top 50,000 rows
def run():
    start = time.time()
    total_total = 0
    for x in range(0, COUNT):
        total_total += sumit(data_str)
    end = time.time()
    print 'MILLIS PER CALL ', 1000*(end-start)/float(COUNT)
    print 'TOTAL ', total_total
    
run()



"""
    numbers = data_str.split()
    numbers = map(int, numbers)
    y = 0
    parts = []
    line_tuples = []
    for num in numbers:
        parts.append(num)
        y += 1
        if y == 3:
            y = 0
            line_tuples.append(parts)
            parts = []
"""
