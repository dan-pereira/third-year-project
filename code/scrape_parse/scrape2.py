import requests



'''
>>> payload = {'key1': 'value1', 'key2': ['value2', 'value3']}

>>> r = requests.get('https://httpbin.org/get', params=payload)
>>> print(r.url)
https://httpbin.org/get?key1=value1&key2=value2&key2=value3
'''

location = "GLA.L1.114a"
payload = {"room":location,"week1":"19", "hour":"1-20","day":"1-5","template":"location"}
http_raw = requests.get('https://www101.dcu.ie/timetables/feed.php', params=payload, verify=False)
print("connection-made")
print(http_raw.url)
print((http_raw.status_code)) #use to check status of url request..






print(len(http_raw.content))
print(type(http_raw.content))
print(http_raw.content)
print("end")
pass
