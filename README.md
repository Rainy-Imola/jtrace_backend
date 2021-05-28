# Backend for Course Design on Application Software, 2021 spring

## 数据库设计讨论

#### 数据库主要实体类型
1. 用户(User)，以下为用户实体具有的属性，括号内为json文件中对应的属性名
    - ID(id, pk)
    - 用户名(username)
    - 用户密码(password)
    - 用户邮箱(email)
    - 爱好(hobby)
    - 星座(constellation)
1. 动态(Message)
    - ID(id, pk)
    - 动态作者(author, Integer, @ManyToOne({CascadeType.REFRESH, CascadeType.MERGE}, optional = false), @JoinColumn(name="user_id))
    - 动态内容(content)
    - 发布日期(date, Date)
    - 图片(picture, String)
1. 评论(Comment)
    - ID(id, pk)
    - 评论者(author, Integer)
    - 评论内容(content, String)
    - 评论日期(date, Date)
    - 被评论动态(Message @ManyToOne)
1. 关系(Relationship) // 尚未验证可行性
    - ID(用于查找User)
    - 用户名(Username, String)
    - 朋友(List<FriendsRelation>)
## 后端功能需求分析

#### 用户管理（用户名和邮箱均唯一，且不能修改）
- 用户注册：接收注册请求->查看是否存在相同用户名或者相同的邮箱地址
    ->不存在->添加到数据库中->注册成功
    ->存在->注册失败
- 用户登录：接收登录请求->匹配用户名/密码
    ->匹配成功->登录成功
    ->匹配失败->登录失败
- 用户密码修改：接收用户密码修改请求->校验原密码
    ->第一次输入新密码->第二次输入新密码
    ->两次一致->修改数据库中密码->修改成功
    ->两次不一致->修改失败

#### 动态管理
- 发布动态：接收前端数据(POST,用户，留言内容，日期)->创建新留言->添加到数据库中
- 删除动态：接收前端数据(DELETE, id)->删除数据库中对应留言
- 修改动态：接收前端数据(PUT, 留言内容，日期)->修改数据库中留言
- 查看全部留言
- 查看自己的留言
- 评论留言

## 技术应用
- Spring boot
- Log4j
- docker-compose
- MySQL
- MongoDB
- Prometheus
- Grafana
- websocket

#### Under construction
- Kubernetes
