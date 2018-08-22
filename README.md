# JDroid
JDroid 是一个基于MVP下的快速开发框架，方便开发者快速进行App开发，减少复制Code的工作，可以下载后直接使用。
目前JDroid主要实现功能点如下：
1.BaseActivity 
  抽离基础的实现，Activity中涉及的基本操作在BaseActivity中实现
2.CrashHandler 
  实现自定义的日志管理，进行日志备份，可选择SD卡或者后台存储
3.自定义Application
  Crashhandler初始化在App刚启动的时候执行
4.ActivityManager
  实现Activity 栈管理，方便Activity的创建，删除。
5.mvp模式 
  基础实现模式，目前已经用Login模块实现
6.网络请求框架 
  rxjava2 + retrofit2 + okhttp3实现网络请求
7.Glide 图片框架
  添加图片架构，借用Glide的三级缓存机制
8.BaseObserver
  抽离基础的网络请求步骤，不用管HttpResponse的其他，只需要关注返回的Result
9.网络请求进度框 
  在BaseObserver中实现，网络请求的进度条提示
