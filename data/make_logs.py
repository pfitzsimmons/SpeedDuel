from datetime import datetime
from random import randint

f = open('webserver.log', 'w')
for x in xrange(0, 100000):
    dt = datetime(
        randint(2010, 2012),
        randint(1, 10),
        randint(1, 25),
        randint(1, 20),
        randint(1, 20),
        randint(1, 50)
        )

    data = {
        'ip': '%s.%s.%s.%s' % (randint(0, 120), randint(0, 120), randint(0, 120), randint(0, 120)),
        'dt_str': dt.strftime('%d/%B/%Y:%H:%M:%S'),
        'path': '/page/edit/%s' % randint(100000, 10000000),
        'ms': randint(1, 1000000)
        }
    f.write('''{ip} - - [{dt_str} -0400] "GET {path} HTTP/1.1" 200 {ms}\n'''.format(**data))
f.close()
