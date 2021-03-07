1.redis已经配置1主2从
2.主机 localhost:6379
3.从机 localhost:6380 与 6381
5.请使用redis第三方工具登录使用
6.注册主从模板：redis-server --service-install redis.windows.conf --loglevel verbose  --service-name redisSlave6381
7.哨兵命令模板：redis-server --service-install sentinel.conf --sentinel  --loglevel verbose  --service-name redissentinel26381
8.如果报错
9.错误：Invalid argument during startup: unknown conf file parameter :
检查空格清理掉没用的空格
10.参考
https://www.cnblogs.com/justdoyou/p/10253668.html