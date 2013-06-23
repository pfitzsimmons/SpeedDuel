

from jinja2 import Environment, DictLoader

from runner import Runner

class JinjaRunner(Runner):
    def __init__(self):
        self.e = Environment(loader=DictLoader({'jinja.html': self.jinja_template_source}))
        self.template = self.e.get_template('jinja.html')
        self.persons = self.get_json_data('persons.json')

    def do_once(self):
        self.template.render({'persons': self.persons})


    jinja_template_source = """
<table>
{% for p in persons %}
  <tr>
   <td>{{ p.firstName }} {{ p.lastName}}</td>
   <td>{% if p.married %}married{% else %}single{% endif %}</td>
   <td>{{ p.id }}</td>
   <td>{{ p.accountId }}</td>
  </tr>
{%endfor %}
"""

class JinjaRunnerNoCache(JinjaRunner):
    def __init__(self):
        self.persons = self.get_json_data('persons.json')

    def do_once(self):
        e = Environment()
        template = e.from_string(self.jinja_template_source)
        template.render({'persons': self.persons})


class ListBuilderRunner(Runner):
    def __init__(self):
        self.persons = self.get_json_data('persons.json')        

    def do_once(self):
        builder = []
        builder.append('<html>\n')
        for p in self.persons:
            builder.append('<tr>')
            builder.append('<td>%s %s</td>' % (p['firstName'], p['lastName']))
            if p['married']:
                builder.append('<td>married</td>')
            else:
                builder.append('<td>single</td>')
            builder.append('<td>%s</td>' % p['id'])
            builder.append('<td>%s</td>' % p['accountId'])
            builder.append('</tr>')
        builder.append('</html>')
        html = '\n'.join(builder)
        
