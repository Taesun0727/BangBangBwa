import pymysql

conn = pymysql.connect(host='localhost', user='root', password='rootroot', db='happyhouse', charset='utf8')
cur = conn.cursor()

cur.execute("SELECT * FROM houseinfo")
houseinfodata = cur.fetchall()

cur.execute("SELECT * FROM housedeal")
housedealdata = cur.fetchall()

conn.commit()
conn.close()

houseinfo = {}
for data in houseinfodata:
    houseinfo[data[0]] = data



'''
no = housedealdata[0]           # int
dealAmount = housedealdata[1]   # str
area = housedealdata[5]         # str
floor = housedealdata[6]        # str
aptCode = housedealdata[8]      # int
'''


'''
aptCode = houseinfodata[0]
buildYear = houseinfodata[1]
roadName = houseinfodata[2]
roadNameBonbun = houseinfodata[3]
roadNameBubun = houseinfodata[4]
roadNameCode = houseinfodata[5]
dong = houseinfodata[6]
bonbun = houseinfodata[7]
bubun = houseinfodata[8]
sigunguCode = houseinfodata[9]
eubmyundongCode = houseinfodata[10]
dongCode = houseinfodata[11]
buildingName = houseinfodata[12]
jibun = houseinfodata[13]
lng = houseinfodata[14]
lat = houseinfodata[15]

'''
'''
item_id int AI PK 
item_type int 
item_building_type int 
item_zonecode int 
item_deal_type int 
item_supply_area int 
item_exclusive_area int 
item_total_floor int 
item_floor int 
item_heating int 
item_move_in_type int 
item_move_in_date datetime 
item_manage_fee int 
item_manage_type int 
item_title varchar(50) 
item_description varchar(200) 
item_buildingcode varchar(30) 
item_build_year varchar(4) 
item_road_name varchar(10) 
item_road_name_bonbun varchar(10) 
item_roadname_bubun varchar(10) 
item_roadname_code varchar(10) 
item_dong varchar(15) 
item_bonbun varchar(10) 
item_bubun varchar(10) 
item_sigungucode varchar(10) 
item_eubmyundongcode varchar(10) 
item_dongcode varchar(12) 
item_building_name varchar(40) 
item_jibun varchar(10) 
item_lng varchar(30) 
item_lat varchar(30) 
item_deal_complete int 
broker_id

'''


conn = pymysql.connect(host='localhost', user='root', password='rootroot', db='bangbang', charset='utf8')
cur = conn.cursor()

cur.execute("CREATE TABLE newTable (id char(4), userName char(15), email char(20), birthYear int)")

for data in housedealdata:
    cur.execute("INSERT INTO newTable VALUES('hong', '홍지윤', 'hong@naver.com', 1996)")

conn.commit()
conn.close()
