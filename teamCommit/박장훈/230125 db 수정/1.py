import pymysql

conn = pymysql.connect(host='localhost', user='root', password='rootroot', db='bangbang', charset='utf8')
cur = conn.cursor()

# 임의 user 생성
cur.execute("INSERT INTO user VALUES(NULL, 'admin@admin.com', 'admin', 'admin', 'admin', 1, 1)")

conn.commit()
conn.close()