import csv
import re
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
# 创建测试表CaiNiao，包含字段：title、link
cursor.execute("""
create table if not exists CaiNiao (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")

list = {'C++', 'C语言', 'HTML', 'JavaScript', 'Ajax', 'Andriod', 'ASP', 'Bootstrap', 'Csharp', 'CSS', 'jQuery', 'JSON',
        'JSP', 'Go', 'Eclipse', 'iOS', 'java', 'MySQL', 'Linux', 'HTTP', 'Markdown', 'Git'}
num = 1

for key in list:
    for cnt in range(1, 100):
        url = 'https://www.runoob.com/?s=' + key + '&page=' + str(cnt)
        headers = {
            'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'
        }
        response = requests.get(url=url, headers=headers, stream=True).content
        soup = BeautifulSoup(response, 'html.parser')

        h2 = soup.findAll('h2')
        if not h2:
            break

        for k in h2:
            a = k.findAll('a')

            for i in a:
                U = i.get("href")
                T = i.get("title")
                T = re.sub("<em>", "", T)
                T = re.sub("</em", "", T)
                T = re.sub(">", "", T)
                cursor.execute("INSERT INTO CaiNiao VALUES (%s, %s, %s)", (str(num), T, U))
                conn.commit()
                num += 1
cursor.close()  # 关闭数据库
print("爬取完毕")
