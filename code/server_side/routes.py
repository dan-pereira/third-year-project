from app import app
from flask import Flask, request, jsonify


@app.route('/')
@app.route('/index')
@app.route('/index/html')
def index():
    return "Hello, Home directory!"
    
