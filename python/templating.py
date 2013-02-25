

from jinja2 import Environment

from runner import Runner

class JinjaRunner(Runner):
    def __init__(self):
        self.e = Environment()
        self.template = self.e.from_string(self.jinja_template_source)
        self.persons = self.get_json_data('persons.json')

    def do_once(self):
        self.template.render({'persons': self.persons})


    jinja_template_source = """
{% for person in persons %}
    <div class="{{ loop.cycle('odd', 'even') }}">
        <div>{{ person.firstName }}</div>
        <div>{% if person.married %}<img src="married.png">{% endif %}</div>
        <div>{{ person.email }}</div>
        <div>{{ person.accountId }}</div>
    </div>
{% endfor %}
"""


class ListBuilderRunner(Runner):
    def __init__(self):
        self.persons = self.get_json_data('persons.json')        

    def do_once(self):
        builder = []
        builder.append('\n')
        cycles = ['odd', 'even']
        len_cycles = len(cycles)
        for x, person in enumerate(self.persons):
           cycle = cycles[x % len(cycles)]
           builder.append('''\
 <div class="''' + cycle + '''">
        <div>''' + person['firstName'] + '''</div>
        <div>''')
           if person['married']:
               builder.append('''<img src="married.png">''')
        builder.append('''<div>''' + person['email'] + '''</div>''')
        builder.append('''<div>''' + str(person['accountId']) + '''</div>''')
        builder.append('''</div>''')
        html = '\n'.join(builder)
        
