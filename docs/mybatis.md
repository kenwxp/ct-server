# MYBATIS

## 参考

* https://blog.mybatis.org/p/products.html

## 数据库迁移 mybatis migration

### 下载地址

* https://github.com/mybatis/migrations/releases

### 安装使用

* https://mybatis.org/migrations/migrate.html

## 数据库驱动

* https://mariadb.org/connector-java/all-releases/
* https://mavenlibs.com/maven/dependency/org.mariadb.jdbc/mariadb-java-client#3.1.0

## 常用命令

```sh
# 初始化系统库迁移
sh ./bin/migrate_sys.sh init

# 初始化应用库迁移
sh ./bin/migrate_app.sh init

# 创建数据库变更
sh ./bin/migrate_app.sh new "add index ct_xxx"

# 查看数据库变更状态
sh ./bin/migrate_app.sh status

# 执行数据库变更
sh ./bin/migrate_app.sh up

# 回滚数据库变更
sh ./bin/migrate_app.sh down
```