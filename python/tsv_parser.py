from collections import defaultdict
from datetime import datetime

from runner import Runner

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
        


class TSVRunner2(Runner):
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
        
