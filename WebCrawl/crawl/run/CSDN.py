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
cursor = conn.cursor()
# 创建测试表CSDN，包含字段：title、link
cursor.execute("""
create table if not exists CSDN (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    primary key(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")

list = {'Andriod开发', 'HTML', 'java', 'jQuery', 'OpenCV', 'Python', 'Web开发', '前端开发', '前端开发框架和库', '操作系统', '机器学习', '深度学习',
        '算法', '网络工程', '计算机网络', '软件测试'}


def fetchHotel(url):
    # 发起网络请求，获取数据
    headers = {
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36',
    }

    # 发起网络请求
    r = requests.get(url, headers=headers)
    r.encoding = 'Unicode'
    return r.text


# 解析json
def parseJson(key, text, num):
    json_data = json.loads(text)
    result = json_data['result_vos']

    if not result:
        return None, num

    for i in result:
        url = i['url']
        title = i['title']
        title = re.sub("<em>", "", title)
        title = re.sub("</em", "", title)
        title = re.sub(">", "", title)
        page = str(int(json_data['page']) + 1)

        nextUrl = 'https://so.csdn.net/api/v2/search?q=' + key + '&t=blog&p=' + page + '&s=hot&tm=0&lv=-1&ft=0&l=&u=&platform=pc'
        cursor.execute("INSERT INTO CSDN VALUES (%s, %s, %s)", (str(num), title, url))
        conn.commit()
        num += 1

    return nextUrl, num


if __name__ == '__main__':
    num = 1
    for key in list:
        url = 'https://so.csdn.net/api/v2/search?q=' + key + '&t=blog&p=1&s=hot&tm=0&lv=-1&ft=0&l=&u=&platform=pc'
        while url:
            text = fetchHotel(url)
            url, num = parseJson(key, text, num)
cursor.close()  # 关闭数据库
print("爬虫结束")
