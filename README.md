###收藏夹
#### 1.Java
##### 简单LogTrace工具 (已完成)
* 总结:单体应用使用追踪日志效果好
#####  Java 强弱软虚引用 (已完成)
* 总结：软引用与网上描述有差异，只有OOM 才会回收softReference
#####  Java 中应用骨架实现 (已完成)
* 总结：解决多个实现类实现同一个接口方法存在重复CODE问题(委托内部类调用)
#####  Orika vs Dozer vs spring beanCopy(未开始)
#####  Guava Cache / Expiring Map (已完成)
* 总结： Expiring在guavaCache 基础上增加了对KV的过期时间设置，功能更加强大以及灵活。
#####  对象比较工具 (已完成)  
* 总结：使用cglib获得对象属性速度 > JDK反射 > toJson->toMap 
#####  Spring aop vs AspectJ (未开始)
  
#####  工具包(已完成)
> > 完成Sales工具包

#####  spring 重复依赖场景
> > 1 set 注入.scope 为 singleton(未开始)
* 总结： 
> > 2 set 注入.scope 为 property (未开始)
* 总结： 
> > 3 construction 注入 (未开始)
* 总结： 
##### 提升效率开发模板(已完成)
* 总结： 场景：1.facade包负责控制层使用 
             2.service包负责服务层使用
##### RxJava (已完成)
* 总结：观察者模式

##### Hystrix (已完成)
* 总结：
>> 1.使用线程池隔离
>> 2.使用信号量隔离
>> 3.超时熔断降级
              
##### 关于多个子线程运行，其中某个线程异常情况测试(已完成)
> > 场景： 某线程 OOM 其他是否运行
* 总结： 是 例子：com.mxy.module.thread.JvmMemOOM
> > 场景： 某线程 stackOverFlow 其他是否运行 
* 总结： 是 例子：com.mxy.module.thread.JvmStackOverflow

#####  Synchronize 方法 对象 静态方法区别 (已完成)
* 总结：
>> 1.synchronize 加实例方法与 synchronize(this) 效果一样 都是锁当前实例对象

>> 2.synchronize 加静态方法与 synchronize(类.class) 效果一样 都是锁当前类

>> 3.synchronize(obj) 锁obj对象，是指锁住这个实例对象。

#####  BIO 与 NIO (已完成)
* 总结：
>> BIO 同步并阻塞，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善
>
>> NIO:同步非阻塞，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。

#### 2.数据库(mysql)  
#####  模拟隔离级别RR场景 (间隙锁原理) 
> > 场景：1 t1->读 t2->写 t1->读 (未开始)
* 总结： 
> > 场景：2 t1->读 t2->删 t1->读 (未开始)
* 总结：  
> > 场景：3 t1->读 t2->更 t1->读 (未开始)
* 总结： 
> > 场景：4 t1->读 t2->更 t1->使用读锁 (未开始)
* 总结： 
> > 场景：5 t1->读 t2->写 t1->更 t1->读 (未开始)
* 总结： 

##### 模拟for update 场景问题 (未开始)

#### 3.缓存
##### 3.1 redis 各种数据结构(已完成)

##### 3.2 redis 分布式锁实现(未开始) 
> > 场景：1.2.1 单机中设置锁后，断开连接再去连接，是否会获得锁。
* 结果：
> > 场景：1.2.2 并发条件下删除锁，这时候锁到期，是否删去同一个key其他线程的锁。
* 结果： 
> > 场景：1.2.3 集群中断开连接,是否还能获得锁.
* 结果： 
> > 场景：1.2.4 获得锁时候启动GC，锁引用是否会回收。
* 结果： 





