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
conn = pymysql.connect(host=serverName, user=userName, password=passWord, database="Crawl", charset='utf8mb4')
# 使用cursor()方法获取操作游标
cursor = conn.cursor()
# 创建测试表Github，包含字段：title、link
cursor.execute("""
create table if not exists Github (
    id    bigint,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")


list = {'C++','C语言','HTML','JavaScript','Ajax','Andriod','ASP','Bootstrap','Csharp','CSS','jQuery','JSON','JSP','Go','Eclipse','iOS','java','MySQL','Linux','HTTP','Markdown','Git'}

def fetchHotel(url):
    # 发起网络请求，获取数据
    headers = {
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36',
    }

    # 发起网络请求
    r = requests.get(url, headers=headers,timeout=60)
    r.encoding = 'Unicode'
    return r.text


# 解析
def parse(cnt, list, text):
    global num
    soup = BeautifulSoup(text, 'html.parser')
    a = soup.findAll('a', class_='v-align-middle')
    for key in list:
        if not a:
            return
        nextUrl = 'https://github.com/search?p=' + str(cnt) + '&q=' + key + '&type=Repositories'
        # print(nextUrl)

        for i in a:
            title = i.get("href")
            if len(title) > 1000:
                title = title[0:500]
            title = re.sub("/", " ", title)
            url = 'https://github.com' + i.get("href")
            cursor.execute("INSERT INTO Github VALUES (%s, %s, %s)", (num, title, url))
            conn.commit()
            num += 1
        # csv_writer.writerow(dit)

    return nextUrl


if __name__ == '__main__':
    num = 1
    for key in list:
        # topicContext = 'java'
        url = 'https://github.com/search?p=1&q=' + key+ '&type=Repositories'
        cnt = 1
        while url:
            cnt = cnt + 1
            text = fetchHotel(url)
            url = parse(cnt, key, text)

cursor.close() #关闭数据库
print("爬虫结束")
