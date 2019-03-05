def check_user (user, password):
	file = open("users.txt")
	user_dic = {}
	for line in file:
	    (key, val) = line.split()
	    user_dic[key] = val
	file.close()


	if user in user_dic and user_dic[user] == password:
		return True

	else:
		return False