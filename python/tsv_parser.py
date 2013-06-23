from collections import defaultdict
from cStringIO import StringIO
from datetime import datetime

from runner import Runner
#import numpy


class TSVRunner(Runner):
    def __init__(self):
        path = self.get_data_file_path('data.csv')
        f = open(path, 'r')
        self.content = f.read()
        f.close()

    def do_once(self):
        totals = defaultdict(int)        
        for line in self.content.split('\n'):
            parts = line.split('\t')
            if not parts[0]:
                continue
            dt = datetime.strptime(parts[0], '%Y-%m-%d')
            totals[(dt.year, dt.month)] += int(parts[1])
        


class TSVRunnerNoDates(Runner):
    def __init__(self):
        path = self.get_data_file_path('data.csv')
        f = open(path, 'r')
        self.content = f.read()
        f.close()

    def do_once(self):
        totals = defaultdict(int)        
        for line in self.content.split('\n'):
            parts = line.split('\t')
            if not parts[0]:
                continue
            year, month, day = parts[0].split('-')
            totals[(year, month)] += int(parts[1])
        



class TSVRunnerNumpy(Runner):
    def __init__(self):
        path = self.get_data_file_path('data.csv')
        f = open(path, 'r')
        self.content = f.read()
        f.close()
        self.file = StringIO(self.content)
        self.file.seek(0)

    def do_once(self):
        self.file.seek(0)

        def make_date(*args, **kwargs):
            print 'VAL ', args
            print kwargs
            parts = val.split('/')
            return datetime(int(parts[0]), int(parts[1]), int(parts[2]))

        output = numpy.genfromtxt(
            self.file,
            #dtype="s10,i8,i8",
            dtype="S10,i8,i8",
            names=['mydate','myint1','myint2'],
            delimiter='\t',
            converters={3: make_date}
            )
        total = 0
        for row in output:
            total += row[2]
        return
