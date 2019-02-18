from app import app
from flask import Flask, request, jsonify
import bsoup

@app.route('/')
@app.route('/index')
# @app.route('/timetables/locations/')
def index():
    # return("Hello, Home directory!")
    room_id = 'GLA.LG27'
    x = bsoup.get_url(room_id)
    return (bsoup.html_to_json(x))

@app.route('/timetables')
def timetables(): 
	return("see directories below for timetables")


