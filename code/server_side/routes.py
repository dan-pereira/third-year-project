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


@app.route('/timetables/locations/<tester1>')
def tester(tester1):
    # x = "yurt" 
    # return(x)
    room_id = 'GLA.' + tester1
    x = bsoup.get_url(room_id)
    return jsonify(bsoup.html_to_json(x))


if __name__ == '__main__':
    app.run(host='136.206.255.215', port = '5000')
