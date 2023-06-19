import pymysql

conn = pymysql.connect(host='localhost', user='root', password='rootroot', db='bangbang', charset='utf8')
cur = conn.cursor()

# 임의 broker 생성
cur.execute("INSERT INTO broker VALUES(NULL, 1, 'admin', 'admin@admin.com', 'admin', 1)")

conn.commit()
conn.close()