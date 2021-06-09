import re

import jieba
import jieba.analyse
import difflib

import pymysql

jieba.setLogLevel(jieba.logging.INFO)

# sql服务器名，这里(127.0.0.1)是本地数据库IP
serverName = '127.0.0.1'
# 登陆用户名和密码
userName = 'root'
passWord = 'lzq2686105'


# 1、将搜索框的词进行jieba分词，过滤去无效词
def cutRow(searchText, webName):
    keys = jieba.analyse.extract_tags(searchText)
    print("*********KEYS")
    print(keys)
    print("*********")

    return calRelative(keys, webName)


# 读取数据库中的所有元组
def getRow(tableName, cursor):
    print(tableName)
    sql = "SELECT * FROM table_" + tableName
    cursor.execute(sql)
    return cursor.fetchall()


# 排序
def takeSecond(elem):
    return elem[1]


# 2、获取一个数据库中的所有表名,枚举每个分词，进行匹配，计算相似度
def calRelative(keys, webName):
    # 维护的数据结构
    map = []
    if webName == 'CSDN':
        webName = 'CSDN2'
        print("dui")
    conn = pymysql.connect(host=serverName, user=userName, password=passWord, database=webName, charset='utf8mb4')
    cursor = conn.cursor()
    cursor.execute('SHOW TABLES')
    tableNames = cursor.fetchall()
    for key in keys:
        for tableName in tableNames:
            tableName = str(tableName)
            tableName = re.sub("table_", "", tableName)
            tableName = re.sub("\(", "", tableName)
            tableName = re.sub("\)", "", tableName)
            tableName = re.sub(",", "", tableName)
            tableName = re.sub("'", "", tableName)
            # similarDegree = difflib.SequenceMatcher(None, key, tableName).quick_ratio()
            similarDegree = similar(key, tableName)
            if similarDegree >= 0.7:
                # 在该table中取出前1000条内容
                lister = getRow(tableName, cursor)
                if (len(lister)) > 1000:
                    lister = lister[0:1000]
                for i in lister:
                    pos = -1
                    for k in range(0, len(map)):
                        if (map[k][0] == i[1]):
                            pos = k
                            break
                    if (pos == -1):
                        # map.append((i[2], 1))
                        map.append((i[1], i[2]))
                    else:
                        # map.append((i[2], map[pos][1] + 1))
                        map.append((i[1], i[2]))

                        map.remove(map[pos])

    # 排序
    map.sort(key=takeSecond, reverse=True)

    # 此时的map即为该webName下的搜索结果
    return interfaceChange(map)


def similar(str1, str2):
    str1 = str1 + ' ' * (len(str2) - len(str1))
    str2 = str2 + ' ' * (len(str1) - len(str2))
    return sum(1 if i == j else 0
               for i, j in zip(str1, str2)) / float(len(str1))


# 接口转化
def interfaceChange(map):
    ans = []
    for row in map:
        ans += [{'title': row[0], 'link': row[1]}]
    return ans


# 接口函数
def returnSearchOut(searchText, webName):
    # 转换
    if (webName == "论坛"):
        webName = "luntan"
    elif (webName == "知乎"):
        webName = "zhihu"
    elif (webName == "掘金"):
        webName = "jvejin"
    elif (webName == "必应学术"):
        webName = "microbing"
    elif (webName == "B站"):
        webName = "bzhan"
    elif (webName == "菜鸟教程"):
        webName = "cainiao2"
    elif (webName == "CSDN"):
        webName = "csdn2"
    elif (webName == "开发者搜索"):
        webName = "kaifazhe2"

    return cutRow(searchText, webName)


# 测试
if __name__ == '__main__':
    searchText = '舞蹈 建模 哪个更好'
    webName = 'test'
    map = cutRow(searchText, webName)  # map:[(_1,_2)]
