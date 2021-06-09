import requests
from bs4 import BeautifulSoup
import pymysql

# sql服务器名，这里(127.0.0.1)是本地数据库IP
serverName = '127.0.0.1'
# 登陆用户名和密码
userName = 'root'
passWord = '12345678'
# 建立连接并获取cursor
conn = pymysql.connect(host=serverName, user=userName, password=passWord, database="Crawl", charset='utf8mb4')

# 使用cursor()方法获取操作游标
cursor = conn.cursor()
# 创建测试表bzhan，包含字段：title、link
cursor.execute("""
create table if not exists test (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")
list = {'java'}
# list={'Andriod开发','C语言','HTML','JavaScript''java','jQuery','OpenCV','Python','Web开发','前端开发','前端开发框架和库','操作系统','机器学习','深度学习','算法','网络工程','计算机网络','软件测试'}
num = 1
for key in list:
    for cnt in range(1, 10):
        url = 'https://search.bilibili.com/all?keyword=' + key + '&from_source=nav_suggest_new&page=' + str(cnt)
        headers = {
            'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'
        }
        response = requests.get(url=url, headers=headers).content
        soup = BeautifulSoup(response, 'html.parser')

        ul = soup.find('ul', class_='video-list clearfix')
        if not ul:
            break
        a = ul.findAll('a', class_='title')

        for i in a:
            U = 'http:' + i.get("href")
            T = i.get("title")
            cursor.execute("INSERT INTO test VALUES (%s,%s, %s)", (str(num), T, U))
            conn.commit()
            num += 1
    # 关闭连接
    conn.close()
print("爬取完毕!")
