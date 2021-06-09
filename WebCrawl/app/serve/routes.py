from app import app
from flask import render_template, redirect, url_for

from app.serve.forms import SearchForm
from app.serve.forms import OptionForm
from matchAnalyse.run.searchInterface import returnSearchOut

@app.route('/', methods=['GET', 'POST'])
def get():
    return redirect(url_for('search'))


@app.route('/search', methods=['GET', 'POST'])
def search():
    global option_form
    global searchkey
    global search_form
    global option
    search_form = SearchForm()
    option_form = OptionForm()
    if search_form.validate_on_submit():
        searchkey = search_form.searchkey.data  # 得到关键字
        option = option_form.option.data  # 得到选项
        return redirect(url_for('result', webName=option, page=1))
    return render_template('search.html', titlt='search', form=search_form, option_form=option_form)


@app.route('/result/<webName>/<page>', methods=['GET', 'POST'])
def result(webName, page):
    global option_form
    global searchkey
    global search_form
    global option
    searchkey = search_form.searchkey.data  # 搜索框的关键字
    option = webName  # case1:重定向访问

    res_form = returnSearchOut(searchkey,webName)  # 从数据库接口得到数据


    page = int(page)  # 显示到第几页
    perResForm = res_form[(page - 1) * 10:page * 10]  # 显示每页的10个数据
    pagetotle = int(len(res_form) / 10)  # 共要显示的页数
    if (pagetotle > 10): pagetotle = 10  # 最大显示10页
    pageNum = {}  # 可显示的页数组1-page
    for i in range(1, pagetotle):
        pageNum[i] = i

    if search_form.validate_on_submit():
        search_form = SearchForm()
        option_form = OptionForm()
        searchkey = search_form.searchkey.data  # 关键字
        option = option_form.option.data  # case2:内部页面访问
        print(searchkey, option)
        return redirect(url_for('result', webName=option, page=page))

    options = []
    options.append(option)
    return render_template('result.html', titlt='result', options=options, ress=perResForm, form=search_form,
                           pageNum=pageNum,
                           option_form=option_form)
