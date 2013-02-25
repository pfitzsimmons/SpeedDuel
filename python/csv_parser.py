
from runner import Runner

class TSVRunner(Runner):
    def __init__(self):
        path = self.get_data_file_path('data.csv')
        
