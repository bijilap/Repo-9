#!flask/bin/python
from flask import Flask, url_for
from flask import request
from flask import json
import csv

app = Flask(__name__)

@app.route('/')
def index():
    return "Elastic Search Service!"

@app.route('/publish/logs', methods = ['POST'])
def parse_bulk_logstash():
    if request.headers['Content-Type'] == 'application/json; charset=UTF-8':
         csv_obj = request.get_json()
         f = csv.writer(open("/home/vineghlinux/Desktop/tabinsight.csv", "wb+"))
         f.writerow(["device", "access_time", "app_name", "use_time"])
         for csv_row in csv_obj:
             f.writerow([csv_obj["device"], csv_obj["access_time"],
                 csv_obj["app_name"], csv_obj["use_time"]])
         return "CSV File written! LogStash Crunching!"
    else:
        return "We support only json format at the moment!"

@app.route('/publish/log',methods = ['POST'])
def parse_update_logstash():
    if request.headers['Content-Type'] == 'application/json; charset=UTF-8':
         csv_obj = request.get_json()
         f = csv.writer(open("/home/vineghlinux/Desktop/tabinsight.csv", "wb+"))
         f.writerow(["device", "access_time", "app_name", "use_time"])
         f.writerow([csv_obj["device"], csv_obj["access_time"], 
                 csv_obj["app_name"], csv_obj["use_time"]])
         return "CSV File written! LogStash Crunching!"
    else:
        return "We support only json format at the moment!"

if __name__ == '__main__':
    app.run(debug=True,host= '0.0.0.0')
