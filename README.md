# 使用Reactor的反应式编程
|-- CreateFluxTest  ```//创建Flux方法类``` </br>
|-- CreateMonoTest  ```//创建Mono方法类``` </br>

## Flux和Mono
Flux 和 Mono 是 Reactor 中的两个基本概念。Flux 表示的是包含 0 到 N 个元素的异步序列。在该序列中可以包含三种不同类型的消息通知：正常的包含元素的消息、序列结束的消息和序列出错的消息。当消息通知产生时，订阅者中对应的方法 onNext(), onComplete()和 onError()会被调用。Mono 表示的是包含 0 或者 1 个元素的异步序列。该序列中同样可以包含与 Flux 相同的三种类型的消息通知。Flux 和 Mono 之间可以进行转换。对一个 Flux 序列进行计数操作，得到的结果是一个 Mono<Long>对象。把两个 Mono 序列合并在一起，得到的是一个 Flux 对象。

### Flux类常用的静态方法
```interval(Duration period)和 interval(Duration delay, Duration period)```创建一个包含了从 0 开始递增的 Long 对象的序列。其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。</br>
```intervalMillis(long period)和 intervalMillis(long delay, long period)```与 ```interval()```方法的作用相同，只不过该方法通过毫秒数来指定时间间隔和延迟时间。</br>
待更新ing-----------------------</br>
</br>部分内容参考自 https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html?lnk=hmhm


