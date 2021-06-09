import csv
import re
import requests
import json
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
# 创建测试表kaifazhe，包含字段：title、link
cursor.execute("""
create table if not exists kaifazhe (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")

list = {'C++', 'C语言', 'HTML', 'JavaScript', 'Ajax', 'Andriod', 'ASP', 'Bootstrap', 'Csharp', 'CSS', 'jQuery', 'JSON',
        'JSP', 'Go', 'Eclipse', 'iOS', 'java', 'MySQL', 'Linux', 'HTTP', 'Markdown', 'Git'}


def fetchHotel(url):
    # 发起网络请求，获取数据
    headers = {
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36',
    }

    # 发起网络请求
    r = requests.get(url, headers=headers)
    r.encoding = 'Unicode'
    return r.text


# 解析json
def parseJson(text, num):
    json_data = json.loads(text)
    data = json_data['data']['documents']['data']
    for i in data:
        title = i['title']
        url = i['url']
        cursor.execute("INSERT INTO kaifazhe VALUES (%s, %s, %s)", (str(num), title, url))
        conn.commit()
        num += 1
    return num


if __name__ == '__main__':
    num = 1
    for key in list:
        cnt = 1
        while cnt < 50:
            url = 'https://kaifa.baidu.com/rest/v1/search?wd=' + key + '&pageNum=' + str(cnt) + '&pageSize=10'
            text = fetchHotel(url)
            num = parseJson(text, num)
            cnt = cnt + 1
cursor.close()  # 关闭数据库
print("爬虫结束")
