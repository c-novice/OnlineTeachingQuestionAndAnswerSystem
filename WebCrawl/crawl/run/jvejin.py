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
# 创建测试表jvejin，包含字段：title、link
cursor.execute("""
create table if not exists jvejin (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")

list = {'jQuery', 'JSON', 'JSP', 'Go', 'Eclipse', 'iOS', 'java', 'MySQL', 'Linux', 'HTTP', 'Markdown', 'Git', 'C++',
        'C语言', 'HTML', 'JavaScript', 'Ajax', 'Andriod', 'ASP', 'Bootstrap', 'Csharp', 'CSS', 'jQuery', 'JSON', 'JSP',
        'Go', 'Eclipse', 'iOS', 'java', 'MySQL', 'Linux', 'HTTP', 'Markdown', 'Git'}


def fetchHotel(url, load):
    # 发起网络请求，获取数据
    headers = {
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36',
    }

    # 发起网络请求
    r = requests.post(url, json=load, headers=headers)
    r.encoding = 'Unicode'
    return r.text


# 解析json
def parseJson(text, num):
    json_data = json.loads(text)
    Cursor = json_data['cursor']
    data = json_data['data']

    for i in data:
        if i['result_type'] != 2:
            continue
        title = i['result_model']['article_info']['brief_content']
        url = i['result_model']['article_info']['link_url']
        if not url:
            continue
        cursor.execute("INSERT INTO jvejin VALUES (%s, %s, %s)", (str(num), title, url))
        conn.commit()
        num += 1

    return Cursor, num


if __name__ == '__main__':
    num = 1
    for key in list:
        Cursor = '0'
        url = 'https://api.juejin.cn/search_api/v1/search'
        cnt = 0
        while Cursor:
            cnt = cnt + 1
            if (cnt > 50):
                cnt = 0
                break
            payload = {'cursor': Cursor, 'id_type': 0, 'key_word': key, 'limiti': 20, 'search_type': 0,
                       'sort_type': 0, 'uuid': '6944172738484766244', 'version': 1}
            text = fetchHotel(url, payload)
            Cursor, num = parseJson(text, num)

cursor.close()  # 关闭数据库
print("爬虫结束")
