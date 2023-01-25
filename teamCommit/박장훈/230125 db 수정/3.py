import pymysql

# 기존 데이터 불러오기
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

print('data download complete')

# 데이터 가공 및 입력
conn = pymysql.connect(host='localhost', user='root', password='rootroot', db='bangbang', charset='utf8')
cur = conn.cursor()

# # 임의 user 생성
# cur.execute("INSERT INTO user VALUES(NULL, 'admin@admin.com', 'admin', 'admin', 'admin', 1, 1)")

# # 임의 broker 생성
# cur.execute("INSERT INTO broker VALUES(NULL, 1, 'admin', 'admin@admin.com', 'admin', 1)")


cnt = 0
for d_data in housedealdata:
    cnt += 1
    temp = str(d_data[1])
    temp = temp.replace(",", "")
    dealAmount = int(temp)
    area = int(float(d_data[5]))
    floor = d_data[6]
    aptCode = d_data[8]
    try:
        i_data = houseinfo[aptCode]
    except:
        continue
    buildYear = i_data[1]
    roadName = i_data[2]
    roadNameBonbun = i_data[3]
    roadNameBubun = i_data[4]
    roadNameCode = i_data[5]
    dong = i_data[6]
    bonbun = i_data[7]
    bubun = i_data[8]
    sigunguCode = i_data[9]
    eubmyundongCode = i_data[10]
    dongCode = i_data[11]
    buildingName = i_data[12]
    jibun = i_data[13]
    lng = i_data[14]
    lat = i_data[15]

    S = 'INSERT INTO item VALUES(NULL, '           # item_id는 NULL로 두어 자동으로 입력
    S += '1, '                                      # item_type은 매물 : 1로 설정
    S += 'NULL, '                                   # item_building_type
    S += 'NULL, '                                   # item_zonecode
    S += 'NULL, '                                   # item_deal_type
    S += str(area) + ', '                           # item_supply_area
    S += 'NULL, '                                   # item_exclusive_area
    S += 'NULL, '                                   # item_total_floor
    S += str(floor) + ', '                          # item_floor
    S += 'NULL, '                                   # item_heating
    S += 'NULL, '                                   # item_move_in_type
    S += 'NULL, '                                   # item_move_in_date
    S += 'NULL, '                                   # item_manage_fee
    S += 'NULL, '                                   # item_manage_type
    S += 'NULL, '                                   # item_title
    S += 'NULL, '                                   # item_description
    S += "'" + str(aptCode) + "'" + ', '                        # item_buildingcode
    S += "'" + str(buildYear) + "'" + ', '                      # item_build_year
    S += "'" + str(roadName) + "'" + ', '                       # item_road_name
    S += "'" + str(roadNameBonbun) + "'" + ', '                 # item_road_name_bonbun
    S += "'" + str(roadNameBubun) + "'" + ', '                  # item_roadname_bubun
    S += "'" + str(roadNameCode) + "'" + ', '                   # item_roadname_code
    S += "'" + str(dong) + "'" + ', '                           # item_dong
    S += "'" + str(bonbun) + "'" + ', '                         # item_bonbun
    S += "'" + str(bubun) + "'" + ', '                          # item_bubun
    S += "'" + str(sigunguCode) + "'" + ', '                    # item_sigungucode
    S += "'" + str(eubmyundongCode) + "'" + ', '                # item_eubmyundongcode
    S += "'" + str(dongCode) + "'" + ', '                       # item_dongcode
    buildingName = str(buildingName).replace("'", "")
    S += "'" + str(buildingName) + "'" + ', '                   # item_building_name
    S += "'" + str(jibun) + "'" + ', '                          # item_jibun
    S += "'" + str(lng) + "'" + ', '                            # item_lng
    S += "'" + str(lat) + "'" + ', '                            # item_lat
    S += '1, '                                    # item_deal_complete 디폴트 1 입력
    S += '1)'                                     # broker_id 디폴트 1 입력

    P = 'INSERT INTO item_price VALUES(NULL, '
    P += str(cnt) + ', ' + str(dealAmount) + ', NULL, NULL, NULL)'

    # print(S)
    # print(P)
    # print()
    # if cnt == 5:
    #     break
    cur.execute(S)
    # cur.execute(P)
    
conn.commit()
conn.close()




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
broker_id int

'''
