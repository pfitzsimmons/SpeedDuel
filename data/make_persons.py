import json
from uuid import uuid4
import random
import string

f = open('persons.json', 'w')
persons = []
for x in xrange(0, 1000):
    persons.append(dict(
        firstName=str(uuid4()),
        lastName=str(uuid4()),
        email=str(uuid4()) + '@%s%s.com' % (string.ascii_letters[random.randint(0, 5)], random.randint(10, 99)),
        id=random.randint(0, 50000000000),
        accountId=random.randint(0, 50000000000),
        married=False,
        city='%s%s' % (string.ascii_letters[random.randint(6, 20)], random.randint(10, 99)),
        country='%s%s' % (string.ascii_letters[random.randint(21, 24)], random.randint(10, 30)),
        ))
json.dump(persons, f)
f.close()
        
