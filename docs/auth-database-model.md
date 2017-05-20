# Metagraph Oauth2 Database Model

> 参考自[Github API V3](https://developer.github.com/v3/)

## 1. Overview

> Metagraph Oauth2 Database Model 定义数据库表结构

## 2. User Model(用户模块)

### 2.1 users(用户表)

> 用于存储用户信息

| 列名       | 含义                   |
| -------- | -------------------- |
| id       | 用户id，主键              |
| username | 用户名                  |
| password | 密码                   |
| state    | 用户状态，1表示启用状态，0表示禁用状态 |



## 3. Auth Model(权限模块)

### 3.1 authorities(权限表)

> 用于存储用户权限信息

| 列名        | 含义                                       |
| --------- | ---------------------------------------- |
| id        | 编号，主键                                    |
| username  | 用户名                                      |
| authority | 指定客户端所拥有的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 对于是否要设置该字段的值,要根据不同的grant_type来判断, 若客户端在Oauth流程中需要用户的用户名(username)与密码(password)的(`authorization_code`,`password`), 则该字段可以不需要设置值,因为服务端将根据用户在服务端所拥有的权限来判断是否有权限访问对应的API. 但如果客户端在Oauth流程中不需要用户信息的(`implicit`,`client_credentials`), 则该字段必须要设置对应的权限值, 因为服务端将根据该字段值的权限来判断是否有权限访问对应的API. |

### 3.2 oauth_access_token(访问令牌表)

| 列名                | 含义                                       |
| ----------------- | ---------------------------------------- |
| create_time       | 数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段) |
| token_id          | 该字段的值是将`access_token`的值通过MD5加密后存储的.      |
| token             | 存储将`OAuth2AccessToken.java`对象序列化后的二进制数据, 是真实的AccessToken的数据值. |
| authentication_id | 该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 具体实现请参考`DefaultAuthenticationKeyGenerator.java`类. |
| user_name         | 登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id |
| client_id         | 客户端id                                    |
| authentication    | 存储将`OAuth2Authentication.java`对象序列化后的二进制数据. |
| refresh_token     | 该字段的值是将`refresh_token`的值通过MD5加密后存储的.     |



### 3.3 oauth_approvals(用户授权表)

| 列名             | 含义                                       |
| -------------- | ---------------------------------------- |
| user_id        | 用户id                                     |
| client_id      | 客户端id                                    |
| scope          | 指定客户端申请的权限范围,可选值包括*read*,*write*,*trust*;若有多个权限范围用逗号(,)分隔,如: "read,write". scope的值与`security.xml`中配置的`‹intercept-url`的`access`属性有关系. 如`‹intercept-url`的配置为`‹intercept-url pattern="/m/**" access="ROLE_MOBILE,SCOPE_READ"/>`则说明访问该URL时的客户端必须有*read*权限范围. *write*的配置值为*SCOPE_WRITE*, *trust*的配置值为*SCOPE_TRUST*. 在实际应该中, 该值一般由服务端指定, 常用的值为*read,write*. |
| status         | 状态，APPROVED表示同意，DENIED表示拒绝               |
| expiresAt      | 授权期限时间                                   |
| lastModifiedAt | 上次修改时间                                   |



### 3.4 oauth_client_details(客户端详情表)

| 列名                      | 含义                                       |
| ----------------------- | ---------------------------------------- |
| client_id               | 主键,必须唯一,不能为空. 用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念. |
| resource_ids            | 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". 该字段的值必须来源于与`security.xml`中标签`‹oauth2:resource-server`的属性`resource-id`值一致. 在`security.xml`配置有几个`‹oauth2:resource-server`标签, 则该字段可以使用几个该值. 在实际应用中, 我们一般将资源进行分类,并分别配置对应的`‹oauth2:resource-server`,如订单资源配置一个`‹oauth2:resource-server`, 用户资源又配置一个`‹oauth2:resource-server`. 当注册客户端时,根据实际需要可选择资源id,也可根据不同的注册流程,赋予对应的资源id. |
| client_secret           | 用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成). 对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念. |
| scope                   | 指定客户端申请的权限范围,可选值包括*read*,*write*,*trust*;若有多个权限范围用逗号(,)分隔,如: "read,write". scope的值与`security.xml`中配置的`‹intercept-url`的`access`属性有关系. 如`‹intercept-url`的配置为`‹intercept-url pattern="/m/**" access="ROLE_MOBILE,SCOPE_READ"/>`则说明访问该URL时的客户端必须有*read*权限范围. *write*的配置值为*SCOPE_WRITE*, *trust*的配置值为*SCOPE_TRUST*. 在实际应该中, 该值一般由服务端指定, 常用的值为*read,write*. |
| authorized_grant_types  | 指定客户端支持的grant_type,可选值包括*authorization_code*,*password*,*refresh_token*,*implicit*,*client_credentials*, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有: "authorization_code,refresh_token"(针对通过浏览器访问的客户端); "password,refresh_token"(针对移动设备的客户端). *implicit*与*client_credentials*在实际中很少使用. |
| web_server_redirect_uri | 客户端的重定向URI,可为空, 当grant_type为`authorization_code`或`implicit`时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致. 下面分别说明:当grant_type=`authorization_code`时, 第一步 `从 spring-oauth-server获取 'code'`时客户端发起请求时必须有`redirect_uri`参数, 该参数的值必须与`web_server_redirect_uri`的值一致. 第二步 `用 'code' 换取 'access_token'` 时客户也必须传递相同的`redirect_uri`. 在实际应用中, *web_server_redirect_uri*在注册时是必须填写的, 一般用来处理服务器返回的`code`, 验证`state`是否合法与通过`code`去换取`access_token`值. |
| authorities             | 指定客户端所拥有的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". 对于是否要设置该字段的值,要根据不同的grant_type来判断, 若客户端在Oauth流程中需要用户的用户名(username)与密码(password)的(`authorization_code`,`password`), 则该字段可以不需要设置值,因为服务端将根据用户在服务端所拥有的权限来判断是否有权限访问对应的API. 但如果客户端在Oauth流程中不需要用户信息的(`implicit`,`client_credentials`), 则该字段必须要设置对应的权限值, 因为服务端将根据该字段值的权限来判断是否有权限访问对应的API. |
| access_token_validity   | 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时). 在服务端获取的access_token JSON数据中的`expires_in`字段的值即为当前access_token的有效时间值. 在项目中, 可具体参考`DefaultTokenServices.java`中属性`accessTokenValiditySeconds`. 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义. |
| refresh_token_validity  | 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). 若客户端的grant_type不包括`refresh_token`,则不用关心该字段 在项目中, 可具体参考`DefaultTokenServices.java`中属性`refreshTokenValiditySeconds`. 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义. |
| additional_information  | 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:`{"country":"CN","country_code":"086"}`按照`spring-security-oauth`项目中对该字段的描述 *Additional information for this client, not need by the vanilla OAuth protocol but might be useful, for example,for storing descriptive information.* (详见`ClientDetails.java`的`getAdditionalInformation()`方法的注释)在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等. |
| create_time             | 数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段) |
| autoapprove             | 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权. 该字段与 trusted 有类似的功能, 是 spring-security-oauth2 的 2.0 版本后添加的新属性. |



### 3.5 oauth_client_token(客户端令牌表)

| 列名                | 含义                                       |
| ----------------- | ---------------------------------------- |
| create_time       | 数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段) |
| token_id          | 从服务器端获取到的`access_token`的值.               |
| token             | 这是一个二进制的字段, 存储的数据是`OAuth2AccessToken.java`对象序列化后的二进制数据. |
| authentication_id | 该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 具体实现请参考`DefaultClientKeyGenerator.java`类. |
| user_name         | 登录时的用户名                                  |
| client_id         | 客户端id                                    |



### 3.6 oauth_code(授权码表)

| 列名             | 含义                                       |
| -------------- | ---------------------------------------- |
| create_time    | 数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段) |
| code           | 存储服务端系统生成的`code`的值(未加密).                 |
| authentication | 存储将`AuthorizationRequestHolder.java`对象序列化后的二进制数据. |



### 3.7 oauth_refresh_token(刷新令牌表)

| 列名             | 含义                                       |
| -------------- | ---------------------------------------- |
| create_time    | 数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段) |
| token_id       | 该字段的值是将`refresh_token`的值通过MD5加密后存储的.     |
| token          | 存储将`OAuth2RefreshToken.java`对象序列化后的二进制数据. |
| authentication | 存储将`OAuth2Authentication.java`对象序列化后的二进制数据. |