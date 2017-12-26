# google-kubernetes-engine

## week 1
### 学习目标
* 理解Container、docker、and Google Container Registry
* 容器化应用
* 理解Kubernetes 概念和基本原则
* 以为服务方式发布一个APP到 Kubernetes 集群
* 创建&管理Kubernetes 发布
* 使用CLI 发布应用到Kubernetes
* 了解持续交付管道
* 使用Jenkins 创建持续交付管道

### 笔记
#### 什么是容器？
容器是一种将程序和进程彼此隔离的文件虚拟技术

#### Docker
* 代码随处运行，跨域开发、测试、生产环境，跨域物理机、虚拟机、和云平台
* 快速创建发布，便于进行持续集成、持续交付，单文件复制
* 提供轻便节约、隔离、弹性扩容的微服务路径

#### 什么是Kubernetes？
Kubernetes 是一个开源的容器编排工具；它提供定义多少机器运行，多少容器需要发布，如何扩容，如何持久化到磁盘，以及如何发布一组容器为一个发布单元.
__基础概念__
* cluster `集群`: 一组工作的计算机，并作为管理这些节点的一个实例
* master
* node
* pod
* service
* replication controller


## week 2
