# -*- coding: utf-8 -*-

import jieba
import numpy as np
import pymysql
import requests
import jieba.analyse
from bs4 import BeautifulSoup
import time

jieba.setLogLevel(jieba.logging.INFO)

serverName = '127.0.0.1'
userName = 'root'
passWord = '12345678'
conn = pymysql.connect(host=serverName, user=userName, password=passWord, database="Crawl", charset='utf8mb4')
cursor = conn.cursor()

headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36'
}


# 读取数据库中的所有元组和title
def getRow():
    sql = "SELECT * FROM cainiao"
    if not sql:
        return
    cursor.execute(sql)
    print('ok')
    return cursor.fetchall()


# 对元组的url进行访问，返回的html进行jieba分词，返回正排
def cut(rows):
    map = {}
    j = 1
    for row in rows:
        print(j)
        j += 1
        try:
            response = requests.get(url=row[2], stream=True).content
        except (requests.exceptions.ConnectionError, requests.exceptions.ChunkedEncodingError) as err:
            print(row[2])
            continue
        soup = BeautifulSoup(response, 'html.parser')
        text = ""
        all = soup.findAll()
        for i in all:
            text = text + i.text
        keys = jieba.analyse.extract_tags(text, topK=20, withWeight=True)
        map.update({row[2]: keys})
    return map


# 建立url和数据库元组的映射
def reflect(rows):
    hash = {}
    for row in rows:
        hash.update({row[2]: row})
    return hash


# 根据正排，首先建立词典
def createDic(map):
    # 首先过滤jieba分词结果
    Dictionary = []
    for i in map:
        for j in map[i]:
            Dictionary.append(j[0])
    return np.unique(Dictionary)


# 排序
def takeSecond(elem):
    return elem[1]


# 根据词典和正排，建立倒排
def reverseSort(map, dics, hash):
    List = {}
    for dic in dics:
        tmp = []
        for i in map:
            for key in map[i]:
                if key[0] == dic:
                    tmp.append((hash[i], key[1]))
        tmp.sort(key=takeSecond, reverse=True)
        lister = []
        for j in tmp:
            lister.append(j[0])
            List.update({dic: lister})

    return List


# 对倒排进行过滤
def filter(List):
    map = {}
    for i in List:
        if len(List[i]) >= 20:
            lister = List[i][0:100]
            map.update({i: lister})
    return map


# 倒排索引写入数据库
def writeToDatabase(List):
    serverName2 = '127.0.0.1'
    userName2 = 'root'
    passWord2 = '12345678'
    conn2 = pymysql.connect(host=serverName2, user=userName2, password=passWord2, database="cainiao2", charset='utf8mb4')
    cursor2 = conn2.cursor()
    for key in List:
        print(key)
        sql = "create table if not exists table_%s" %key + """(
                        id    int,
                        title VARCHAR(2000) CHARACTER SET utf8mb4,
                        link  VARCHAR(2000) CHARACTER SET utf8mb4,
                        PRIMARY KEY(id)
                    )ENGINE = InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT = Dynamic;
                    """
        try:
            cursor2.execute(sql)
            conn2.commit()
        except:
            print('error')
            continue

        for row in List[key]:
            try:
                cursor2.execute("INSERT INTO table_" + key + " VALUES (%s,%s, %s)", (str(row[0]), str(row[1]), str(row[2])))
            except:
                print('***********')
                print('ERROR'+str(row[0])+str(row[1])+str(row[2]))
                print('***********')
                continue
            conn2.commit()


if __name__ == '__main__':
    rows = getRow()
    hash = reflect(rows)
    map = cut(rows)
    dics = createDic(map)
    List = reverseSort(cut(rows), dics, hash)  # List:{key:list[row]}
    List = filter(List)
    writeToDatabase(List)
