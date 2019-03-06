#!/usr/bin/bash
for i in {1..1000}
do
	curl http://209.97.184.103/timetables/locations/L101/student	
	curl http://209.97.184.103/timetables/locations/LG26/student
	curl http://209.97.184.103/timetables/locations/L114/student
done
