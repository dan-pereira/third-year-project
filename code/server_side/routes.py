from flask import Flask, request, jsonify, render_template
import bsoup
# from app import app
app = Flask(__name__)

@app.route('/')
def index():
    return ('Welcome to TouchTime Local Server')

@app.route('/timetables/')
def timetable(): 
    return("see directories below for timetables")


@app.route('/timetables/locations/L101')
def timetable101():
    # return("Hello, Home directory!")
    room_id = 'GLA.L101'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))


@app.route('/timetables/locations/L114')
def timetable114():
    # return("Hello, Home directory!")
    room_id = 'GLA.L114'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/L125')
def timetable125():
    # return("Hello, Home directory!")
    room_id = 'GLA.L125'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/L128')
def timetable128():
    # return("Hello, Home directory!")
    room_id = 'GLA.L128'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/LG25')
def timetable25():
    # return("Hello, Home directory!")
    room_id = 'GLA.LG25'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/LG26')
def timetable26():
    # return("Hello, Home directory!")
    room_id = 'GLA.LG26'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/LG27')
def timetable27():
    # return("Hello, Home directory!")
    room_id = 'GLA.LG27'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

@app.route('/timetables/locations/CG12')
def timetable28():
    # return("Hello, Home directory!")
    room_id = 'GLA.CG12'
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))

if __name__ == '__main__':
    app.run(host='136.206.255.215')

