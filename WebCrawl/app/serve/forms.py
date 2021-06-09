from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, RadioField
from wtforms.validators import DataRequired


class SearchForm(FlaskForm):
    searchkey = StringField('searchkey', validators=[DataRequired(message="请输入关键字")], id='searchkey')
    submit = SubmitField('搜索', id='submit')


class OptionForm(FlaskForm):
    option = RadioField(id='option', choices=[
        ('论坛', u'论坛'),
        ('B站',u'B站'),
        ('CSDN',u'CSDN'),
        ('知乎', u'知乎'),
        ('掘金', u'掘金'),
        ('必应学术', u'必应学术'),
        ('开发者搜索', u'开发者搜索'),
        ('菜鸟教程', u'菜鸟教程'),
    ], default='论坛', validators=[DataRequired()])
