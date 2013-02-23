import sys
import time
from optparse import OptionParser

class_by_name = {
    'memcached': 'memcache_runner.Runner',
    'memcached-string': 'memcache_runner.StringRunner',
}

def main():
    parser = OptionParser()
    parser.add_option('-a', '--action', help='The name of the action to run. Choices: ' + ','.join(class_by_name.keys()), choices=class_by_name.keys())
    parser.add_option('-n', '--number', default=10000, help='The number of iterations to run')
    options = parser.parse_args()[0]
    if not options.action:
        parser.print_help()
        return
    
    cls_str = class_by_name[options.action]
    module_name, cls_name = cls_str.split('.')
    module = __import__(module_name)
    cls = getattr(module, cls_name)
    runner = cls()
    start = time.time()
    for x in xrange(0, int(options.number)):
        runner.do_once()
    #runner.do_many(options.number)
    end = time.time()
    elapsed = end - start
    per_iteration = (1000000.0 * elapsed) / float(options.number)
    print 'Ran %s for %s iterations in %s seconds' % (options.action, options.number, elapsed)
    print 'Micros per iteration: %s' % per_iteration


if __name__ == "__main__":
    main()
