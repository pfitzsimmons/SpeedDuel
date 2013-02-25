from collections import defaultdict, namedtuple
import itertools
import operator
import os
import random
import time

data_path = os.path.join(os.path.dirname(__file__), '../data')


class Runner(object):
    def __init__(self):
        self.objects = self._build_db()
        
    def _build_db(self):
        print "Building db ..."
        objects = []
        for x in xrange(1, 1000000):
            objects.append(PageVisit.random_visit())
        for x in xrange(1, 10000):
            objects.append(PageVisit.random_visit(2001))
        random.shuffle(objects)
        self.big_list = [random.randint(0, 3000) for x in xrange(0, 100000)]
        print "DB completed"
        return objects

    def do_once(self):
        account_id = 2001
        start = time.time()
        self._find_top_20_pages_for_account(account_id)
        print time.time() - start
        start = time.time()
        self._find_total_visits_for_account(account_id)
        print time.time() - start
        start = time.time()
        self._find_top_20_visited_accounts_overall()
        print time.time() - start
        start = time.time()
        self._find_top_20_visited_pages()
        print time.time() - start

        start = time.time()
        sorted(self.big_list)
        print time.time() - start


    def _find_top_20_pages_for_account(self, account_id):
        pages = [page for page in self.objects if page.account_id == account_id]
        return sorted(pages, key=operator.attrgetter('visits'))[:20]

    def _find_total_visits_for_account(self, account_id):
        pages = [page for page in self.objects if page.account_id == account_id]
        return sum(map(operator.attrgetter('visits'), pages))


    def _find_top_20_visited_accounts_overall(self):
        visits_by_account = defaultdict(int)
        for page in self.objects:
            visits_by_account[page.account_id] += page.visits
        return [account_id for visits, account_id in sorted(visits_by_account.items())[:20]]
        

    def _find_top_20_visited_pages(self):
        #visits_positions = [(p.visits, i) for (i, p) in enumerate(self.objects)]
        #visits_positions.sort()
        #pages = [self.objects[pos] for (visits, pos) in visits_positions[0:20]]
        #return pages
        return sorted(self.objects, key=operator.attrgetter('visits'))[:20]

PageVisitTuple = namedtuple('PageVisitTuple', ['url', 'visits', 'account_id', 'source'])

class PageVisit(object):
    __slots__ = ['url', 'visits', 'account_id', 'source']
    def __init__(
            self,
            url='',
            visits=0,
            account_id=0,
            source=''
            ):
        self.url = url
        self.visits = visits
        self.account_id = 0
        self.source = source

    @classmethod
    def random_visit(cls, account_id=None):
        if not account_id:
            account_id = random.randint(0, 2000)
        page_id = random.randint(0, 100)
        page_cls = cls
        #page_cls = PageVisitTuple
        return page_cls(
            url = 'http://site-%s.sites.com/page/%s' % (account_id, page_id),
            account_id=account_id,
            visits=random.randint(0, 3000),
            source=sources[random.randint(0, len(sources)-1)]
            )
            
sources = [
    'google',
    'yahoo',
    'bing',
    'email',
    'link',
    'twitter',
    'facebook',
    'direct'
]
            
