from flask import Flask, request, jsonify
import bsoup

app = Flask(__name__)

@app.route('/')
def index():
	return ('Welcome to TouchTime Local Server')

@app.route('/timetables/')
def timetables(): 
	return("see directories below for timetables")

# anyinput = input()

@app.route('/timetables/locations/any')
def timetable():
    # return("Hello, Home directory!")
    room_id = 'GLA.L101'
    x = bsoup.get_url(room_id)
    return (bsoup.html_to_json(x))

