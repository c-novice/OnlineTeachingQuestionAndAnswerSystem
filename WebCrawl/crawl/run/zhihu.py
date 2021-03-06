import json
import requests
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
create table if not exists zhihu (
    id    int,
    title VARCHAR(2000) CHARACTER SET utf8mb4,
    link  VARCHAR(2000) CHARACTER SET utf8mb4,
    PRIMARY KEY(id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
""")


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
def parseJson(text, num):
    json_data = json.loads(text)
    lst = json_data['data']
    nextUrl = json_data['paging']['next']

    if not lst:
        return None, num;

    for item in lst:
        type = item['target']['type']

        if type == 'answer':
            # 回答
            question = item['target']['question']
            id = question['id']
            title = question['title']
            url = 'https://www.zhihu.com/question/' + str(id)
            cursor.execute("INSERT INTO zhihu VALUES (%s, %s, %s)", (str(num), title, url))
            conn.commit()

        elif type == 'article':
            # 专栏
            zhuanlan = item['target']
            title = zhuanlan['title']
            url = zhuanlan['url']
            cursor.execute("INSERT INTO zhihu VALUES (%s, %s, %s)", (str(num), title, url))
            conn.commit()

        elif type == 'question':
            # 问题
            question = item['target']
            id = question['id']
            title = question['title']
            url = 'https://www.zhihu.com/question/' + str(id)
            cursor.execute("INSERT INTO zhihu VALUES (%s, %s, %s)", (str(num), title, url))
            conn.commit()

        num += 1

    return nextUrl, num


if __name__ == '__main__':
    topicID = {'19555634', '19584970', '19561633', '19791242', '19551323', '19552521', '19561132', '19557964',
               '19587715', '19552832', '19550516', '19550901', '19621319', '19552686', '19559450', '19813032',
               '19553510', '19658134', '19572894', '19562409'}
num = 1
for ID in topicID:
    url = 'https://www.zhihu.com/api/v4/topics/' + ID + '/feeds/essence?include=data%5B%3F%28target.type%3Dtopic_sticky_module%29%5D.target.data%5B%3F%28target.type%3Danswer%29%5D.target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%3F%28target.type%3Dtopic_sticky_module%29%5D.target.data%5B%3F%28target.type%3Danswer%29%5D.target.is_normal%2Ccomment_count%2Cvoteup_count%2Ccontent%2Crelevant_info%2Cexcerpt.author.badge%5B%3F%28type%3Dbest_answerer%29%5D.topics%3Bdata%5B%3F%28target.type%3Dtopic_sticky_module%29%5D.target.data%5B%3F%28target.type%3Darticle%29%5D.target.content%2Cvoteup_count%2Ccomment_count%2Cvoting%2Cauthor.badge%5B%3F%28type%3Dbest_answerer%29%5D.topics%3Bdata%5B%3F%28target.type%3Dtopic_sticky_module%29%5D.target.data%5B%3F%28target.type%3Dpeople%29%5D.target.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F%28type%3Dbest_answerer%29%5D.topics%3Bdata%5B%3F%28target.type%3Danswer%29%5D.target.annotation_detail%2Ccontent%2Chermes_label%2Cis_labeled%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Canswer_type%3Bdata%5B%3F%28target.type%3Danswer%29%5D.target.author.badge%5B%3F%28type%3Dbest_answerer%29%5D.topics%3Bdata%5B%3F%28target.type%3Danswer%29%5D.target.paid_info%3Bdata%5B%3F%28target.type%3Darticle%29%5D.target.annotation_detail%2Ccontent%2Chermes_label%2Cis_labeled%2Cauthor.badge%5B%3F%28type%3Dbest_answerer%29%5D.topics%3Bdata%5B%3F%28target.type%3Dquestion%29%5D.target.annotation_detail%2Ccomment_count%3B&limit=10&after_id=0'
    cnt = 0
    while url:
        cnt = cnt + 1
        if (cnt > 500):
            break
        text = fetchHotel(url)
        url, num = parseJson(text, num)
print("爬虫结束")
