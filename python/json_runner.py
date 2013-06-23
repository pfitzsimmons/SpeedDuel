import json
import simplejson
from runner import Runner

class JSONRunner(Runner):
    def __init__(self):
        self.path = self.get_data_file_path('persons.json')
        f = open(self.path)
        self.content = f.read()
        f.close()

    def do_once(self):
        email_data = simplejson.loads(self.content)
        assert len(email_data) == 1000

class JSONRunnerTwoWay(Runner):
    def __init__(self):
        self.path = self.get_data_file_path('persons.json')
        f = open(self.path)
        self.content = f.read()
        f.close()

    def do_once(self):
        email_data = simplejson.loads(self.content)
        assert len(email_data) == 1000
        content2 = simplejson.dumps(email_data)
        assert 259571 == len(content2)

class PyJSONRunner(Runner):
    def __init__(self):
        self.path = self.get_data_file_path('persons.json')
        f = open(self.path)
        self.content = f.read()
        f.close()

    def do_once(self):
        email_data = json.loads(self.content)
        assert len(email_data) == 1000
