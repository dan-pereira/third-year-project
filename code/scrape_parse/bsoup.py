import requests 
from bs4 import BeautifulSoup
def get_url(location):

	# file = open("location_list.txt")
	# if str(location+"\n") not in file:
	# 	return ("Not in file")
	# file.close()

	# GET CURRENT WEEK METHOD

	current_week = "24"

	payload = {"room": location, "week1": current_week, "hour": "1-20", "day": "1-5", "template": "location"}
	http_raw = requests.get('https://www101.dcu.ie/timetables/feed.php', params=payload, verify=False)

	name = str(location)+".html"
	file = open(name, 'wb')
	file.write(http_raw.content)
	file.close()

	return name

def html_to_json(file_name):
	raw_html = open(file_name).read()
	table_raw = BeautifulSoup(raw_html, 'html.parser').contents
	week = {}
	table_data_num = ["8:00"]
	# allows indexing on available times
	for line in table_raw:
		if line.name == 'td':
			table_data_num.append(str(line.string))

	for table in table_raw:
		if table.name == 'tr':

			count = 0  # is counting each column on table
			day_name = "error"
			day_data = table.contents
			# day data is each row
			for data in day_data:
				if data.name == "td":
					try:
						length = int(data["colspan"])
						event = data.contents
						item_number = 0
						cell_info = []
						# pulls all details out of cells
						for detail in event:
							if detail.name == "table":
								for detail1 in detail.contents:
									if detail1.name == "tr":
										for detail2 in detail1:
											if detail2.name == "td":
												if item_number < 4:
													cell_info.append(str(detail2.string))
												item_number += 1

						hour = []
						# appends hours
						for _ in range(length):
							hour.append(table_data_num[count])

						dic = {"module": cell_info[0], "name": cell_info[1], "lec": cell_info[2], "num": cell_info[3], "hours": hour}
						week[day_name].append(dic)
					except:
						# for day of week
						try:
							null = data["rowspan"] #will fail if no rowspan
							day_name = str(data.string)
							if day_name not in week:
								week[day_name] = []
						# blank cell
						except:
							count += 1
							pass

	# for day in week:
<<<<<<< HEAD
	# 	print(day, "\n", week[day])
	return week
=======
		# print(day, "\n", week[day])
	print("success")
	if week == {}:	
		return(str('Class not found please check spelling and try again.'))
	else:	
		return(week)
>>>>>>> adf33cd142a9596bdef8821d73ff217afd842cbd

# Testing 

if __name__ == '__main__':


	room_id = 'GLA.LG27'

	# file_name = get_url(room_id)
	file_name = get_url(room_id)
	try:
		print(html_to_json(file_name))
	except:
		print("Invalid Timetable")

	print("ll")


	pass