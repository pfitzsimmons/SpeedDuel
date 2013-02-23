
import simplejson as json
import time

f = open('emails.json', 'r')
email_json = f.read()
f.close()

COUNT = 100

def run():
    counter = 0
    start = time.time()
    for x in range(0, COUNT):
        email_data = json.loads(email_json)
        for email in email_data['objects']:
            if '{{ contact.firstname }}' in email['email_body']:
                counter += 1
    end = time.time()
    print 'PER CALL ', 1000000*(end-start)/float(COUNT)
    print 'COUNTER ', counter
    
run()


