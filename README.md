# IM --> SpringBoot+websocket #



## IM服务器文档 ##

## 目录  
[1.注册](#1)<br/>
[2.登录](#2)<br/>
[3.验证码发送](#3)<br/>
[4.更新密码](#4)<br/>
[5.个人信息更新](#5)<br/>
[6.用户在线状态更新](#6)<br/>
[7.添加好友](#7)<br/>
[8.删除好友](#8)<br/>
[9.好友列表](#9)<br/>
[10.添加分组](#10)<br/>
[11.建群](#11)<br/>
[12.加群](#12)<br/>
[13.群成员列表](#13)<br/>
[14.创建普通聊天室](#14)<br/>
[15.创建群聊天室](#15)<br/>
[16.SOCKET传输message 格式](#16)<br/>
[17.服务器地址](#17)<br/>






### <h2 id='1'>注册</h2>
* 请求URL : {API_Url}/socket/user/register
* 请求类型 ： POST
* 入参 ： 
> 	
		code : “验证码” 
		name: “姓名”
		nickname:”昵称”
		email:”邮箱”
		sex:”性别，MALE/FEMAIE”
		age:10
		signature:”签名”
		password:”密码”
	
* 返回示例：
> 
	{
  	 "data": 
		{
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

### <h2 id='2'>登录</h2>
* 请求URL : {API_Url}/socket/user/login
* 请求类型 ： POST
* 入参 ： 
> 
	account:”admin”
	passowrd:”123456”

* 返回示例：
> 	
	{
  	"data": 
		{
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

### <h2 id='3'>验证码发送</h2>
* 请求URL : {API_Url}/socket/user/{mobile}/register/code
* 请求类型 ： GET
* 入参 ： 
* 返回示例：
> 
	{
  	"errMsg": "验证码已发送，请在10分钟内完成验证！",
 	"success": true
	}




### <h2 id='4'>更新密码</h2>
* 请求URL : {API_Url}/socket/account/password/update
* 请求类型 ： POST
* 入参 ： 
> 	
	account:”666666”,
	oldPwd:”旧密码”
	newPwd:”新密码”
	repartPwd:”重复密码”
	
* 返回示例：
> 
	{
  	"errMsg": "密码更新成功,请重新登录！",
  	"success": true
	}



### <h2 id='5'>个人信息更新</h2>
* 请求URL : {API_Url}/socket/account/updates
* 请求类型 ： POST
* 入参 ： 
> 
	account:”666666”
	name: “姓名”
	nickname:”昵称”
	email:”邮箱”
	sex:”性别，MALE/FEMAIE”
	age:10
	signature:”签名”
	password:”密码”

* 返回示例：
>
	{
  	"data": {
    "account": "666666",
    "age": 10,
    "id": 8,
    "lastLoginTime": 1494401704000,
    "name": "wo222ddd22312",
    "nickname": "123",
    "sex": "MALE",
    "signature": "签名牛逼的很",
    "status": "ONLINE",
    "statusType": "在线",
    "validFlag": "ENABLE"
 	 },
 	 "errMsg": "更新成功！",
  	"success": true
	}
	

### <h2 id='6'>用户在线状态更新</h2>
* 请求URL : {API_Url}/socket/account/{account}/{status}/change
* 请求类型 ： GET
* 描述
> 
	Status:对应
	ONLINE(1,"在线"),
	OFFLINE(2,"离线"),
	GOAWAY(3,"离开"),
	BUSY(4,"忙碌"),
	STEALTH(5,"隐身");

* 返回示例：

>
	{
  		"errMsg": "更新成功！",
 		 "success": true
	}




### <h2 id='7'>添加好友</h2>
* 请求URL : {API_Url}/socket/user/account/friend/add
* 请求类型 ： POST
* 入参 ： 
> 
	account:”当前账号”
	applyAccount:”要添加好友的额账号”
	remark:”好友备注”
	groupId:1 //好友分组

* 返回示例：

> 
	{
	  "data": {
	    "account": "66666",
	    "createTime": 1494402571565,
	    "friendAccount": "admin",
	    "friendRemark": "shab",
	    "id": 2,
	    "validFlag": "ENABLE"
	  },
	  "errMsg": "成功！",
	  "success": true
	}



### <h2 id='8'>删除好友</h2>
* 请求URL : {API_Url}/socket/user/account/friend/del
* 请求类型 ： POST
* 入参 ： 
> 
	account:”当前账号”
	applyAccount:”被删除者账号”

* 返回示例：

> 
	{
  	"errMsg": "成功！",
  	"success": true
	}


### <h2 id='8'>删除好友</h2>
* 请求URL : {API_Url}/socket/user/account/friend/del
* 请求类型 ： POST
* 入参 ： 
> 
	account:”当前账号”
	applyAccount:”被删除者账号”

* 返回示例：

> 
	{
  	"errMsg": "成功！",
  	"success": true
	}


### <h2 id='9'>好友列表</h2>
* 请求URL : {API_Url}/socket/user/account/friend/fd
* 请求类型 ： POST
* 入参 ： 
> 
	account:”当前账号”
	

* 返回示例：

> 
	{
  	"data": [
    {
      "details": [
        {
          "account": "king",
          "age": 0,
          "createTime": 1493969399000,
          "id": 3,
          "lastLoginTime": 1493970792000,
          "name": "king",
          "status": "OFFLINE",
          "statusType": "离线",
          "updateTime": 1493969401000,
          "validFlag": "ENABLE"
        }
      ],
      "groupName": "同学"
    },
    {
      "details": [
        {
          "account": "666",
          "age": 10,
          "id": 5,
          "name": "wo",
          "nickname": "123",
          "sex": "MALE",
          "signature": "签名牛逼的很",
          "status": "OFFLINE",
          "statusType": "离线",
          "validFlag": "ENABLE"
        }
      ],
      "groupName": "朋友"
    },
    {
      "details": [
        {
          "account": "admin",
          "age": 0,
          "avatarPic": "yoxi.jpg",
          "createTime": 1493953325000,
          "id": 1,
          "lastLoginTime": 1494399777000,
          "name": "admin",
          "status": "ONLINE",
          "statusType": "在线",
          "updateTime": 1493953327000,
          "validFlag": "ENABLE"
        },
        {
          "account": "root",
          "age": 0,
          "createTime": 1493965682000,
          "id": 2,
          "lastLoginTime": 1493970834000,
          "name": "root",
          "status": "OFFLINE",
          "statusType": "离线",
          "updateTime": 1493965684000,
          "validFlag": "ENABLE"
        },
        {
          "account": "666666",
          "age": 10,
          "id": 8,
          "lastLoginTime": 1494402029000,
          "name": "wo222ddd22312",
          "nickname": "123",
          "sex": "MALE",
          "signature": "签名牛逼的很",
          "status": "OFFLINE",
          "statusType": "离线",
          "validFlag": "ENABLE"
        },
        {
          "account": "731638471",
          "age": 10,
          "id": 4,
          "name": "wo",
          "nickname": "123",
          "sex": "MALE",
          "signature": "签名牛逼的很",
          "status": "OFFLINE",
          "statusType": "离线",
          "validFlag": "ENABLE"
        }
      ],
      "groupName": "好友列表"
    }
 	 ],
 	 "errMsg": "成功！",
 	 "success": true
	}

### <h2 id='10'>添加分组</h2>
* 请求URL : {API_Url}/socket/user/account/friend/del
* 请求类型 ： POST
* 入参 ： 
> 
	account:”当前账号”
	name:”分组名字”

* 返回示例：

> 
	{
 	 "data": {
    "account": "66666",
    "createTime": 1494402913296,
    "groupName": "同学",
    "id": 1,
    "order": 0
 	 },
  	"errMsg": "成功！",
 	 "success": true
	}



### <h2 id='11'>建群</h2>
* 请求URL : {API_Url}/socket/user/member/group/create
* 请求类型 ： POST
* 入参 ： 
> 
	fgroupName:”群名”
	account:”当前账号”
	introduction:”群介绍”

* 返回示例：

> 
	{
  	"data": {
    "account": "66666",
    "createTime": 1494403669655,
    "fgroupId": "15714",//群号
    "fgroupName": "qunqun",
    "id": 1,
    "introduction": "计算机就是就是"
  	},
  	"errMsg": "成功！",
  	"success": true
	}




### <h2 id='12'>加群</h2>
* 请求URL : {API_Url}/socket/user/member/group/join
* 请求类型 ： POST
* 入参 ： 
> 
	groupId:”群号”
	account:”当前账号”
	remark:”备注	”

* 返回示例：

> 
	 {
 	 "data": {
    "account": "66666",
    "createTime": 1494403765650,
    "fgroupId": "15714",
    "id": 1,
    "remark": "445"
 	 },
 	 "errMsg": "成功！",
 	 "success": true
	}

### <h2 id='13'>群成员列表</h2>
* 请求URL : {API_Url}/socket/user/member/{groupId}/group/fd
* 请求类型 ： POST
* 入参 ： 
> 
	groupId:”群号”
	
* 返回示例：

> 
	 {
  	"data": [
    {
      "account": "66666",
      "createTime": 1494403765000,
      "fgroupId": "15714",
      "id": 1,
      "remark": "445"
    },
    {
      "account": "admin",
      "createTime": 1494405046000,
      "fgroupId": "15714",
      "id": 2,
      "remark": "4451"
    },
    {
      "account": "root",
      "createTime": 1494405051000,
      "fgroupId": "15714",
      "id": 3,
      "remark": "4451"
    }
  	],
  	"errMsg": "成功！",
 	 "success": true
	}



### <h2 id='14'>创建普通聊天室</h2>
* 请求URL : {API_Url}/socket/room/pt/estab
* 请求类型 ： POST
* 入参 ： 
> 
	from:”发起者”
	to:””
	
* 返回示例：

> 
	 {
 	 "data": {
    "createTime": 1494403267866,
    "from": "66666",
    "id": 1,
    "to": "root"
 	 },
 	 "errMsg": "成功！",
 	 "success": true
	}



### <h2 id='15'>创建群聊天室</h2>
* 请求URL : {API_Url}/socket/room/{id}/group/estab
* 请求类型 ： POST
* 描述

> 
	Id为群号

* 入参 ： 

* 返回示例：

> 
	{
  	"data": {
    "createTime": 1494467321000,
    "groupId": "15714",
    "id": 1,
    "type": "GROUP"
 	 },
 	 "errMsg": "成功！",
 	 "success": true
	}




### <h2 id='16'>SOCKET传输message 格式</h2>
* 格式
> 
	发消息格式：
 	ws.send(JSON.stringify({
            message : {
            	room:1,
            	group:15714,
            	chatType:'SIGNLE', 
            	msgType:'TEXT',
                content : message,
                from : '${login_userid}',
                to : to,     
                time : getDateFull()
            },
            type : "message"
	}));



* 字段说明

> 
	chatType ：
		SIGNLE,//普通聊天
		GROUP,//群聊
		DISCUSS;//讨论组
	msgType ： 
		TEXT,//文字信息
		AUDIO,//图片信息
		IMAGE;//语音信息




### <h2 id='17'>服务器地址</h2>
* 地址
> 
	API_URL:
		http://218.244.150.173:8081
> 
	SOCKET_URL:
		ws://218.244.150.173:8081/socket/socketServer?userid=${login_userid}
> 
    Web页面例子：http://218.244.150.173:8081/socket/user01/login
