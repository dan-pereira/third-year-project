from flask import Flask, request, jsonify, render_template
import bsoup
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
# if student == 'student': 
# 	if tester1 not in counter_dict: 
# 		counter_dict[tester1] = 1
# 	else: 
# 		counter_dict[tester1] += 1 

# elif student == 'lecturer': 
def lec_testertester(tester1):
	if tester1 not in counter_dict: 
		counter_dict[tester1] = 0
	return str(counter_dict[tester1])

# ---------------------------------------------------
@app.route('/timetables/locations/<tester1>/student')
# if student == 'student': 
# 	if tester1 not in counter_dict: 
# 		counter_dict[tester1] = 1
# 	else: 
# 		counter_dict[tester1] += 1 

# elif student == 'lecturer': 
def tester(tester1):
	# x = "yurt" 
	# return(x)
	if tester1 not in counter_dict: 
		counter_dict[tester1] = 1
	else: 
		counter_dict[tester1] += 1 
	# elif student == 'lecturer': 
	# 	return null
	
	room_id = 'GLA.' + tester1
	x = bsoup.get_url(room_id)
	return jsonify(bsoup.html_to_json(x))

if __name__ == '__main__':
	app.run(host='192.168.43.132', port = '5000')

