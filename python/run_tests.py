import sys
import time
from optparse import OptionParser

class_by_name = {
    'json': ('json_runner.JSONRunner', 1000),
    'json-two-way': ('json_runner.JSONRunnerTwoWay', 1000),
    'json-builtin': ('json_runner.PyJSONRunner', 1000),
    'log-parser': ('log_parser.LogParser', 10),
    'log-parser-nore': ('log_parser.LogParserNoRE', 10),
    'log-parser-numpy': ('log_parser.LogParserNumpy', 10),
    'memdb': ('memdb.Runner', 1000),
    'memdb-sorting': ('memdb.SortingRunner', 1000),
    'memcached': ('memcache_runner.Runner', 1000),
    'memcached-string': ('memcache_runner.StringRunner', 1000),
    'router': ('routing.Router', 1000),
    'templating-jinja': ('templating.JinjaRunner', 1000),
    'templating-jinja-nocache': ('templating.JinjaRunnerNoCache', 1000),
    'templating-builder': ('templating.ListBuilderRunner', 200),
    'tsv': ('tsv_parser.TSVRunner', 5),
    'tsv-nodates': ('tsv_parser.TSVRunnerNoDates', 5),
    'tsv-numpy': ('tsv_parser.TSVRunnerNumpy', 1),
    'josephus-classes': ('josephus_classes.JosephusRunner', 10000),
    'josephus-deque': ('josephus_deque.JosephusRunner', 10000),
    'josephus-linked-list': ('josephus_linked_list.JosephusRunner', 10000),
    'josephus-go': ('josephus_go.JosephusRunner', 10000),
    'casting': ('casting.CastingRunner', 100000),
}

def main():
    parser = OptionParser()
    parser.add_option('-a', '--action', help='The name of the action to run. Choices: ' + ','.join(class_by_name.keys()), choices=class_by_name.keys())
    parser.add_option('-n', '--number', default=None, help='The number of iterations to run')
    options = parser.parse_args()[0]
    if not options.action:
        parser.print_help()
        return
    
    cls_str, default_number = class_by_name[options.action]
    module_name, cls_name = cls_str.split('.')
    module = __import__(module_name)
    cls = getattr(module, cls_name)
    runner = cls()
    number = options.number or default_number
    start = time.time()
    for x in xrange(0, int(number)):
        runner.do_once()
    #runner.do_many(options.number)
    end = time.time()
    elapsed = end - start
    per_iteration = (1000000.0 * elapsed) / float(number)
    prefix = 'Micros ' 
    if per_iteration > 10000:
        per_iteration = per_iteration / 1000.0
        prefix = "Milliseconds "
    print 'Ran %s for %s iterations in %s seconds' % (options.action, number, elapsed)
    print prefix + 'per iteration: %s' % per_iteration


if __name__ == "__main__":
    main()
