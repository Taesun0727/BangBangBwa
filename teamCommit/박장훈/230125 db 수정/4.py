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

cnt = 0
for d_data in housedealdata:
    temp = str(d_data[1])
    temp = temp.replace(",", "")
    dealAmount = int(temp)
    aptCode = d_data[8]

    try:
        i_data = houseinfo[aptCode]
    except:
        continue

    cnt += 1
    P = 'INSERT INTO item_price VALUES(NULL, '
    P += str(cnt) + ', ' + str(dealAmount) + ', NULL, NULL, NULL)'
    cur.execute(P)
    
conn.commit()
conn.close()
