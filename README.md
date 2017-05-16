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
 <p> {
  "data": {
    "account": "admin",
    "age": 0,
    "avatarPic": "yoxi.jpg",
    "createTime": 1493953325000,
    "id": 1,
    "lastLoginTime": 1494399772000,
    "name": "admin",
    "status": "ONLINE",//用户状态，在线 or 离线
    "statusType": "在线",
    "updateTime": 1493953327000,
    "validFlag": "ENABLE"
  },
  "errMsg": "登录成功！",
  "success": true
}
</p>

 
