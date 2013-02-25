import json
import os

data_folder = os.path.join(os.path.dirname(__file__), '../data')

class Runner(object):
    def get_data_file_path(self, file_name):
        return data_folder + '/' + file_name
        
    def get_json_data(self, file_name):
        path = self.get_data_file_path(file_name)
        f = open(path, 'r')
        data = json.load(f)
        f.close()
        return data
