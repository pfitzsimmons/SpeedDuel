from cStringIO import StringIO
from collections import defaultdict
import numpy
import re

from runner import Runner



class LogParser(Runner):
    line_re = re.compile(
        r'(\d+\.\d+\.\d+\.\d+) - - \[([^]]+) \-\d+\] "(GET|POST) [^"]+ HTTP/1.1" \d+ (\d+)')
    def __init__(self):
        f = open(self.get_data_file_path('webserver.log'))
        self.content = f.read()
        f.close()
        
    def do_once(self):
        total_time_by_month = defaultdict(int)
        for line in self.content.split('\n'):
            if not line.strip():
                continue
            m = self.line_re.match(line)
            date_str = m.group(2)
            millis = int(m.group(4))
            parts = date_str.split('/')
            month, year = parts[1], parts[2]
            total_time_by_month[(month, year)] += int(millis)
            
                 
class LogParserNoRE(Runner):
    def __init__(self):
        f = open(self.get_data_file_path('webserver.log'))
        self.content = f.read()
        f.close()
        
    def do_once(self):
        total_time_by_month = defaultdict(int)
        for line in self.content.split('\n'):
            if not line:
                continue
            parts = line.split(' ')
            date_parts = parts[3].split('/')
            month, year = date_parts[1], date_parts[2]
            total_time_by_month[(month, year)] += int(parts[-1])
            
class LogParserNumpy(Runner):
    line_re = re.compile(
        r'(\d+\.\d+\.\d+\.\d+) - - \[([^/:]+)/([^/:]+)/([^/:]+):([^]]+)] "(GET|POST) ([^"]+) HTTP/1.1" \d+ (\d+)'
        )


    def __init__(self):
        f = open(self.get_data_file_path('webserver.log'))
        self.content = f.read()
        f.close()
        self.file = StringIO(self.content)
        self.file.seek(0)

    def do_once(self):
        self.file.seek(0)
        output = numpy.fromregex(
            self.file, 
            self.line_re, 
            [('ip', 'S20'), ('day', 'S25'), ('month', 'S20'), ('year', 'S4'), ('time', 'S20'), ('method', 'S7'), ('path', 'S100'), ('size', numpy.int32)]
            )
        total_time_by_month = defaultdict(int)
        for row in output:
            total_time_by_month[(row[2], row[1])] += row[7]
