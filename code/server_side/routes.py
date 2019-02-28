from flask import Flask, request, jsonify, render_template
import bsoup
import socket
# from app import app
app = Flask(__name__)

# CREATE BASH TO REFRESH COUNTER 

counter_dict = {}

@app.route('/')
def index():
	return ('Welcome to TouchTime Local Server')

@app.route('/timetables/')
def timetable(): 
	return("see directories below for timetables")

@app.route('/timetables/locations/<tester1>/')
def timetable_empty(tester1): 
	room_id = 'GLA.' + tester1
	x = bsoup.get_url(room_id)
	return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/<tester1>/lecturer') 
def lec_tester(tester1):
	if tester1 not in counter_dict: 
		counter_dict[tester1] = 0
	return str(counter_dict[tester1])

# ---------------------------------------------------
@app.route('/timetables/locations/<tester1>/student') 
def tester(tester1):
	if tester1 not in counter_dict: 
		counter_dict[tester1] = 1
	else: 
		counter_dict[tester1] += 1 
	room_id = 'GLA.' + tester1
	x = bsoup.get_url(room_id)
	return jsonify(bsoup.html_to_json(x))

def get_ip_address():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    return str(s.getsockname()[0])

if __name__ == '__main__':
	app.run(host=get_ip_address(), port = '5000')

