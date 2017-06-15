# Metagraph Oauth2 Manage API 设计规范

> 参考自[Github API V3](https://developer.github.com/v3/)

## 1. Overview

> Metagraph Oauth2 Manage API 定义用户信息、客户端信息 管理API

### 1.1 Root Endpoint

- http://domain:8080
- https://domain:8080

其中`domain`是具体的域名或者`ip:port`的形式,可以在`root endpoint`中加上`api version`标识调用的api版本

### 1.2 MediaType

form-data+json

### 1.3 Charset

UTF-8

### 1.4 Errors

错误码格式范例(JSON)

```json
{
  "code": "10003",
  "message": "delete user error",
  "description": "删除用户出错"
}
```

错误码含义表

| code  | message           | description |
| ----- | ----------------- | ----------- |
| 10001 | add user error    | 新增用户出错      |
| 10002 | update user error | 更新用户出错      |
| 10003 | delete user error | 删除用户出错      |
| 10004 | get user error    | 查询用户出错      |

## 2. User API

> User API：用户管理API

### 2.1 add

> 新增用户

**Endpoint:** 

POST: /user/add

**Request：**

```json
http://localhost:8080/user/add
```

**Parameter:**

```json
{
    "username": "test",
    "password": "test",
    "enabled": "true"
}
```

**Response:**

```json
{
  "code": "200",
  "message": "success",
  "description": "请求成功"
}
```

### 2.2 update

> 更新用户

**Endpoint:**

POST: /user/update

**Request：**

```json
http://localhost:8080/user/update
```

**Parameter:**

```json
{
  	"id":3,
    "username": "test",
    "password": "test1"
}
```



**Response:**

```json
{
  "code": "200",
  "message": "success",
  "description": "请求成功"
}
```

### 2.3 delete

> 删除用户

**Endpoint:**

POST: /user/delete

**Request：**

```json
http://localhost:8080/user/delete
```

**Parameter:**

```json
{
  	"id":3
}
```

**Response:**

```json
{
  "code": "200",
  "message": "success",
  "description": "请求成功"
}
```
### 2.4 getUserById

> 根据ID查询用户信息

**Endpoint:**

POST: /user/getUserById

**Request：**

```json
http://localhost:8080/user/getUserById
```

**Parameter:**

```json
{
  	"id":3
}
```

**Response:**

```json
{
  "code": "200",
  "message": "success",
  "description": "请求成功",
  "result": {
    "enabled": true,
    "id": 1,
    "username": "admin"
  }
}
```