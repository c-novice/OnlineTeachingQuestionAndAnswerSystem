import csv
import time

import requests
from bs4 import BeautifulSoup
import pymysql

# sql服务器名，这里(127.0.0.1)是本地数据库IP
serverName = '127.0.0.1'
# 登陆用户名和密码
userName = 'root'
passWord = '12345678'
# 建立连接并获取cursor
conn = pymysql.connect(host=serverName, user=userName, password=passWord, database="Crawl",
                       charset='utf8mb4')  # practice:数据库名称
# 使用cursor()方法获取操作游标
cursor = conn.cursor()
# 创建测试表microBing，包含字段：title、link
cursor.execute("""
create table if not exists microBing (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")

list = {'C++', 'C语言', 'HTML', 'JavaScript', 'Ajax', 'Andriod', 'ASP', 'Bootstrap', 'Csharp', 'CSS', 'jQuery', 'JSON',
        'JSP', 'Go', 'Eclipse', 'iOS', 'java', 'MySQL', 'Linux', 'HTTP', 'Markdown', 'Git', 'pycharm', 'IDEA', 'python'}
num = 1

for key in list:
    cnt = 0
    while (cnt < 50):
        url = 'https://cn.bing.com/academic/search?q=' + key + '&first=' + str(10 * cnt + 1) + '&FORM=PENR'
        headers = {
            'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'
        }
        try:
            response = requests.get(url=url, timeout=10).content
            print(url)
        except Exception as e:
            print('error')
            time.sleep(10)
            continue
        soup = BeautifulSoup(response, 'html.parser')
        h2 = soup.findAll('h2')
        for i in h2:
            T = i.get_text()
            a = i.find('a')
            if not a:
                continue
            U = a.get('href')
            print(U)
            if U[0] != 'h':
                print("ao")
            else:
                cursor.execute("INSERT INTO microBing VALUES (%s, %s, %s)", (str(num), T, U))
                conn.commit()
                num += 1
                print(num)
        cnt = cnt + 1

cursor.close()  # 关闭数据库
print("爬取完毕!")
