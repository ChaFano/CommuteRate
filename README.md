# springboot + thymeleaf + layui（CommuteRate）


#### 介绍
通勤率后台管理系统,基于海康威视的门禁系统项目模仿学习的后台系统demo。项目中相关的数据是我屏蔽了敏感信息在上传的，
包括学生姓名、学号是用代码自动生成的。这个项目是由于本人不会前端，所以借助于layui这个前端框架学习所完成的demo,
持续更新并完善一些功能。

#### 功能描述

1、登录、添加管理员、删除管理员。

2、添加新用户、删除用户。

3、计算班级通勤率和年级通勤率，展示成柱状图。

#### 实现思路

1、用户和管理员信息都是通过 layui 的 form 表单实现添加和删除

2、获取每个天消息记录，将每个班的扫脸成功率计算出来。

>采用模糊查询可以查出每天的推送消息总记录
>        根据年级编号计算出年级通勤率
>	    根据班级编号计算出班级通勤率

3、计算每个年级总人数
```
[
  "{2019=302, 2018=280, 2017=257, 2016=220, 2021=315, 2020=298}"
]
```
4、Highcharts 表格数据渲染格式
```
series: [{
               name: '一班',
               data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0]
           }, {
               name: '二班',
               data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]
           }, {
               name: '三班',
               data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3]
           }, {
               name: '四班',
               data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5]
           }, {
               name: '五班',
               data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5]
           }, {
               name: '六班',
               data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5]
           }
      ]
```

#### 页面效果

登录页面
<img src="https://img-blog.csdnimg.cn/e2875f396134496d98260fb91ecd0185.png" width="100%" height="60%" />

管理员页面

<img src="https://img-blog.csdnimg.cn/01fe2c37760045bf8984bedff5d76442.png" width="80%" height="60%" />

用户页面

<img src="https://img-blog.csdnimg.cn/ba369dfb3c4d40049dfe6d6c33ae15b4.png" width="80%" height="60%" />

班级通勤率页面

<img src="https://img-blog.csdnimg.cn/cfc91787d43d438ba13918647f470665.png" width="100%" height="60%" />

年级通勤率页面

<img src="https://img-blog.csdnimg.cn/c27296a240b948fd9ed12a97fd5c35ac.png" width="100%" height="60%" />


#### 最近跟新
1、更新了 shiro 的用户认证，资源访问验证。
2、更新了修改密码功能
#### 后续计划

1、添加验证码功能

2、完善布局


#### 特技
CSDN 茶凡_Matrix [https://blog.csdn.net/weixin_45833112?type=blog](https://blog.csdn.net/weixin_45833112?type=blog)
