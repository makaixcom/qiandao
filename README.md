# 某个私活的后台
## 项目简介
这是业余时间优化私活项目的后台代码，项目基于JDK17 和 SpringBoot3。  

由于数据库连接是敏感信息，于是把数据连接那块儿改为 JVM 的参数指定或者环境变量指定，请大家手动改一下。  

## 更新说明
- [x] 用户管理(增、删、改、查、授权角色)
- [x] 角色管理（增、删、改、查、管理角色拥有的资源）
- [x] 资源管理（增、删、改、查）
- [ ] 支付渠道管理（增、删、改、查、渠道模版）
- [ ] 商户管理（增、删、改、查、模板属性）
- [ ] 消费 （查询、统计）
- [ ] 退款 （查询、统计）

## 设计架构
系统
- 用户
- 角色
- 资源

商户
- 商户
- 支付渠道

订单
- 支付
- 退款
- 对帐

## 使用说明
- 数据库的安装与配置
- Linux的环境配置
- 启动 商户-管理端
- 启动 商户-管理端-UI
- 启动 开放平台
- nginx的安装与配置

## 技术栈
- JDK17
- SpringBoot3（快照版）
- Swagger3(由于没有适配SpringBoot3，目前先用注解写好)
- MySQL
- lombok
- 工具类 spring tools > apache commons > google guava > hutools 