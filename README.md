# zkredis
项目基本架构为：zookeeper+redis
项目工程说明：
    1、zkredis-app为会用到redis的实际应用
    2、zkredis-detector为监控服务器
项目架构
    1、监控服务器主要采用心跳来获取到最新可用的redis地址，端口。然后将地址同步到zookeeper
    2、所有的项目应用均通过zk客户端去zookeeper拿去可用的redis地址，端口。项目本身会缓存这些数据，只有zookeeper节点存的redis主从列表发生变化的时候，通过watcher通知到各个应用，各个应用更新本地缓存
    3、本zkredis组件不关心，各个应用如何使用redis，只提供可用的redis集群中的每一台可用的地址
实现原理：
  1、detecor监控服务器，通过redis的ping命令实时查看redis的master，和slave的状态
  2、master故障更新zookeeper用slave替换slave
  3、slave故障，更新zookeeper的redis列表，移除
  4、每个应用启动时，内部的zk客户端都watch zookeeper的redis列表节点。同步更新本地缓。
  
