import urllib2
import re
import csv
import MySQLdb

'''teamAbbr = {'AtlantaHawks' : 'atl', 'BostonCeltics' : 'bos', 'CharlotteHornets' : 'cha',
			'ChicagoBulls' : 'chi', 'ClevelandCavaliers' : 'cle', 'DallasMavericks' : 'dal',
			'DenverNuggets' : 'den', 'DetroitPistons' : 'det', 'GSWarriors' : 'gsw', 
			'HoustonRockets' : 'hou', 'IndianaPacers' : 'ind', 'LAClippers' : 'lac', 
			'LALakers' : 'lal', 'MemphisGrizzlies' : 'mem', 'MiamiHeat' : 'mia', 
			'MilwaukeeBucks' : 'mil', 'MinnesotaT-wolves' : 'min', 'BrooklynNets' : 'bro', 
			'NOrleansPelicans' : 'nor', 'NYKnicks' : 'nyk', 'OrlandoMagic' : 'orl', 
			'Philadelphia76ers' : 'phi', 'PhoenixSuns' : 'pho', 'PortlandTrailBlazers' : 'por', 
			'SacramentoKings' : 'sac', 'SanAntonioSpurs' : 'san', 'OklahomaCityThunder' : 'okl', 
			'TorontoRaptors' : 'tor', 'UtahJazz' : 'uta', 'WashingtonWizards' : 'was',
			'CharlotteBobcats' : 'cha', 'NOrleansHornets' : 'nor', 'NJNets' : 'njn'}'''

teamWebsites = ['http://www.dougstats.com/15-16RD.Team.txt',
				'http://www.dougstats.com/15-16.HomeRD.Team.txt',
				'http://www.dougstats.com/15-16.AwayRD.Team.txt',
				'http://www.dougstats.com/14-15RD.Team.txt',
				'http://www.dougstats.com/14-15.HomeRD.Team.txt',
				'http://www.dougstats.com/14-15.AwayRD.Team.txt',
				'http://www.dougstats.com/13-14RD.Team.txt',
				'http://www.dougstats.com/13-14.HomeRD.Team.txt',
				'http://www.dougstats.com/13-14.AwayRD.Team.txt',
				'http://www.dougstats.com/12-13RD.Team.txt',
				'http://www.dougstats.com/12-13.HomeRD.Team.txt',
				'http://www.dougstats.com/12-13.AwayRD.Team.txt',
				'http://www.dougstats.com/11-12RD.Team.txt',
				'http://www.dougstats.com/11-12.HomeRD.Team.txt',
				'http://www.dougstats.com/11-12.AwayRD.Team.txt',
				'http://www.dougstats.com/10-11RD.Team.txt',
				'http://www.dougstats.com/10-11.HomeRD.Team.txt',
				'http://www.dougstats.com/10-11.AwayRD.Team.txt',
				'http://www.dougstats.com/09-10RD.Team.txt',
				'http://www.dougstats.com/09-10.HomeRD.Team.txt',
				'http://www.dougstats.com/09-10.AwayRD.Team.txt',
				'http://www.dougstats.com/08-09RD.Team.txt',
				'http://www.dougstats.com/08-09.HomeRD.Team.txt',
				'http://www.dougstats.com/08-09.AwayRD.Team.txt',
				'http://www.dougstats.com/07-08RD.Team.txt',
				'http://www.dougstats.com/07-08.HomeRD.Team.txt',
				'http://www.dougstats.com/07-08.AwayRD.Team.txt',
				'http://www.dougstats.com/06-07RD.Team.txt',
				'http://www.dougstats.com/06-07.HomeRD.Team.txt',
				'http://www.dougstats.com/06-07.AwayRD.Team.txt',
				'http://www.dougstats.com/05-06RD.Team.txt',
				'http://www.dougstats.com/05-06.HomeRD.Team.txt',
				'http://www.dougstats.com/05-06.AwayRD.Team.txt',
				'http://www.dougstats.com/04-05RD.Team.txt',
				'http://www.dougstats.com/04-05.HomeRD.Team.txt',
				'http://www.dougstats.com/04-05.AwayRD.Team.txt',
				'http://www.dougstats.com/03-04RD.Team.txt',
				'http://www.dougstats.com/03-04.HomeRD.Team.txt',
				'http://www.dougstats.com/03-04.AwayRD.Team.txt',
				'http://www.dougstats.com/02-03RD.Team.txt',
				'http://www.dougstats.com/02-03.HomeRD.Team.txt',
				'http://www.dougstats.com/02-03.AwayRD.Team.txt',
				'http://www.dougstats.com/01-02RD.Team.txt',
				'http://www.dougstats.com/01-02.HomeRD.Team.txt',
				'http://www.dougstats.com/01-02.AwayRD.Team.txt',
				'http://www.dougstats.com/00-01RD.Team.txt',
				'http://www.dougstats.com/00-01.HomeRD.Team.txt',
				'http://www.dougstats.com/00-01.AwayRD.Team.txt',
				'http://www.dougstats.com/99-00RD.Team.txt',
				'http://www.dougstats.com/99-00.HomeRD.Team.txt',
				'http://www.dougstats.com/99-00.AwayRD.Team.txt',
				'http://www.dougstats.com/98-99RD.Team.txt',
				'http://www.dougstats.com/97-98RD.Team.txt']

teamHTMLs =	   ['http://www.dougstats.com/96-97RD.Team.html',
				'http://www.dougstats.com/95-96RD.Team.html',
				'http://www.dougstats.com/94-95RD.Team.html',
				'http://www.dougstats.com/93-94RD.Team.html',
				'http://www.dougstats.com/92-93RD.Team.html',
				'http://www.dougstats.com/91-92RD.Team.html',
				'http://www.dougstats.com/90-91RD.Team.html',
				'http://www.dougstats.com/89-90RD.Team.html',
				'http://www.dougstats.com/88-89RD.Team.html']


def teamTable(filename):
	mydb = MySQLdb.connect(host = 'localhost', user = 'root', passwd = '*****', db = 'sys')
	cursor = mydb.cursor()
	csv_data = csv.reader(file(filename))
	cursor.execute('DROP TABLE IF EXISTS `'+filename[:-4]+'`')
	create = """CREATE TABLE """+filename[:-4]+""" (
				team VarChar(20) PRIMARY KEY, games_won INT, 
				games_lost INT, minutes INT, fg_made INT, fg_attempts INT, 3s_made INT, 
				3s_attempts INT, ft_made INT, ft_attempts INT, off_rebounds INT, 
				tot_rebounds INT, assists INT, steals INT, turnovers INT, blocks INT, 
				personal_fouls INT, points INT, technicals INT, ejections INT, 
				flagrant_fouls INT)"""
	cursor.execute(create)
	count = 0
	for row in csv_data:
		insert = """INSERT INTO """+filename[:-4]+"""(team, games_won,
					games_lost, minutes, fg_made, fg_attempts, 3s_made, 3s_attempts, 
					ft_made, ft_attempts, off_rebounds, tot_rebounds, assists, steals,
					turnovers, blocks, personal_fouls, points, technicals, ejections,
					flagrant_fouls)
					VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
					%s, %s, %s, %s, %s, %s)"""
		cursor.execute(insert, row)
		count += 1
	mydb.commit()
	cursor.close()

def oldTeamTable(filename):
	mydb = MySQLdb.connect(host = 'localhost', user = 'root', passwd = 'Gladius7210-O', db = 'sys')
	cursor = mydb.cursor()
	csv_data = csv.reader(file(filename))
	cursor.execute('DROP TABLE IF EXISTS `'+filename[:-4]+'`')
	create = """CREATE TABLE """+filename[:-4]+""" (
				team VarChar(20) PRIMARY KEY, games_won INT, 
				games_lost INT, minutes INT, fg_made INT, fg_attempts INT, 3s_made INT, 
				3s_attempts INT, ft_made INT, ft_attempts INT, off_rebounds INT, 
				tot_rebounds INT, assists INT, steals INT, turnovers INT, blocks INT, 
				personal_fouls INT, points INT)"""
	cursor.execute(create)
	count = 0
	for row in csv_data:
		insert = """INSERT INTO """+filename[:-4]+"""(team, games_won,
					games_lost, minutes, fg_made, fg_attempts, 3s_made, 3s_attempts, 
					ft_made, ft_attempts, off_rebounds, tot_rebounds, assists, steals,
					turnovers, blocks, personal_fouls, points)
					VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
					%s, %s, %s)"""
		cursor.execute(insert, row)
		count += 1
	mydb.commit()
	cursor.close()

for n in range(len(teamWebsites)):
	filename = teamWebsites[n][25:]
	filename = filename[:-4]
	filename = filename.replace('.', '').replace('-', '')+'.csv'
	output = open(filename, "w")
	first_line = 1
	count = 0  
	for line in urllib2.urlopen(teamWebsites[n]):
		if first_line == 0:
			row = ''
			for word in line.split():
				'''if word in teamAbbr:
					row = row+teamAbbr[word]+','+word+','
				else:'''
				row = row+word+','
			output.write(row[:-1]+'\n')
			count += 1
		if count == 30:
			break	 
		first_line = 0
	output.close()
	teamTable(filename)

for n in range(len(teamHTMLs)):
	filename = teamHTMLs[n][25:]
	filename = filename[:-5]
	filename = filename.replace('.', '').replace('-', '')+'.csv'
	output = open(filename, "w")
	count = 0
	for line in urllib2.urlopen(teamHTMLs[n]):
		if len(line) > 7:
			if first_line <= 0:
				row = ''
				c = 0
				for word in line.split():
					if c < 18:
						row = row+word+','
					c+=1	
				output.write(row[:-1]+'\n')
				count+=1
			if count == 30:
				break
	output.close()
	oldTeamTable(filename)	

'''output = open("playerStats.csv", "w")
first_line = 1
for line in urllib2.urlopen("http://www.dougstats.com/15-16RD.txt"):
	count = 0
	if first_line == 0:
		row = ''
		for word in line.split():
			temp = ''
			if count == 0:
				for name in word.split(','):
					temp = temp+name+' '
				word = temp[:-1]
			row = row+word+','	
			count+=1
		output.write(row[:-1])	
		output.write('\n')
	first_line = 0
output.close()'''

'''mydb = MySQLdb.connect(host = 'localhost', user = 'root', passwd = 'Gladius7210-O', db = 'sys')
cursor = mydb.cursor()
csv_data = csv.reader(file('playerStats.csv'))
cursor.execute('DROP TABLE IF EXISTS `playerstats`')
create = """CREATE TABLE playerstats (
			Player_ VarChar(25) PRIMARY KEY, Team VarChar(5), position VarChar(5), 
			games_played INT, minutes INT, fg_made INT, fg_attempts INT, 3s_made INT,
			3s_attempts INT, ft_made INT, ft_attempts INT, off_rebounds INT, 
			tot_rebounds INT, assists INT, steals INT, turnovers INT, blocks INT, 
			personal_fouls INT, dqs INT, points INT, technicals INT, ejections INT, 
			flagrant_fouls INT, games_started INT, plus_minus INT)"""
cursor.execute(create)
for row in csv_data:
	insert = """INSERT INTO playerstats(player_, team, position, games_played, 
				minutes, fg_made, fg_attempts, 3s_made, 3s_attempts, ft_made, 
				ft_attempts, off_rebounds, tot_rebounds, assists, steals, 
				turnovers, blocks, personal_fouls, dqs, points, technicals, 
				ejections, flagrant_fouls, games_started, plus_minus)
				VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 
				%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
	cursor.execute(insert, row)
mydb.commit()
cursor.close()'''
