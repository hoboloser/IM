# IM
springboot+websocket
# Document
## 登录
* 请求url：
  > {API_Url}/socket/user/login
  > POST
* 参数说明：
  > account:”admin”
  > passowrd:”123456”
* 返回示例：
  > <p> { "data": {
  >  "account": "admin",
  >  "age": 0,
  >  "avatarPic": "yoxi.jpg",
  >  "createTime": 1493953325000,
  >  "id": 1,
  >  "lastLoginTime": 1494399772000,
  >  "name": "admin",
  >  "status": "ONLINE",//用户状态，在线 or 离线
  >  "statusType": "在线",
  >  "updateTime": 1493953327000,
  >  "validFlag": "ENABLE"
  > },
  > "errMsg": "登录成功！",
  > "success": true
  > }
  > </p>

 
## 注册
* 请求url:
  > {API_Url}/socket/user/register
  > POST
* 参数说明：
  >  code : “验证码”
  >  name: “姓名”
  >  nickname:”昵称”
  >  email:”邮箱”
  >  sex:”性别，MALE/FEMAIE”
  >  age:10
  >  signature:”签名”
  >  password:”密码”
* 返回示例
<html>
  {
  "data": {
    "age": 10,
    "account": "666666", //登录账号
    "id": 8,
    "name": "wo",
    "nickname": "123",
    "sex": "MALE",
    "signature": "签名牛逼的很",
    "status": "OFFLINE",
    "statusType": "离线",
    "validFlag": "ENABLE"
  },
  "errMsg": "注册成功,激活邮件已发送,请前往邮箱激活！",
  "success": true
}
<html>

