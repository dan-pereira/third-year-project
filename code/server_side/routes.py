import sys
sys.path.append('/var/www/FlaskApp/FlaskApp')
import bsoup
import authentication
from flask import Flask, request, jsonify, render_template
#import bsoup

app = Flask(__name__)

counter_dict = {}

#for key in counter_dict: 
#       counter_dict[key] = 0 

def check_location(vari):
        file_loc = "/var/www/FlaskApp/FlaskApp/location_list.txt"
        file = open(file_loc, 'r')
        if str(vari+"\n") not in file:
                return (False)
        else:
                return True 
        file.close()

@app.route("/")
def hello():
        return "Welcome to TouchTime Server!"

@app.route('/timetables/')
@app.route('/timetables/locations/')
def timetable(): 
        return("see directories below for timetables")

@app.route('/timetables/locations/<tester1>/')
def timetable_empty(tester1): 
        room_id = 'GLA.' + tester1
        if check_location(room_id) == True:
                x = bsoup.get_url(room_id)
                return jsonify(bsoup.html_to_json(x))
        else:
                return ("00") 

@app.route('/timetables/locations/<tester1>/lecturer') 
def lec_tester(tester1):
        room_id = 'GLA.' + tester1
        if check_location(room_id) == True:
                if tester1 not in counter_dict:
                        counter_dict[tester1] = 0
                return str(counter_dict[tester1])
        else: 
                return ('00')

@app.route('/timetables/locations/<tester1>/student') 
def tester(tester1):
        if tester1 not in counter_dict: 
                counter_dict[tester1] = 1
        else: 
                counter_dict[tester1] += 1 
        room_id = 'GLA.' + tester1
        if check_location(room_id) == True:
                x = bsoup.get_url(room_id)
                return jsonify(bsoup.html_to_json(x))
        else:
                return ("0") 
        x = bsoup.get_url(room_id)
        return jsonify(bsoup.html_to_json(x))

@app.route('/authentication/<usr>/<pas>')
def authentication_check(usr, pas):
    if authentication.check_user(usr,pas) == True:
        return("1")
    else:
        return("0")


if __name__ == "__main__":
    app.run()


