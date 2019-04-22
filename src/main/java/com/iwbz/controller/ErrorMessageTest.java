package com.iwbz.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorMessageTest {
    //错误消息处理
    public static void main(String[] args) {
/*        System.out.println("concatWith---------------");
        Flux.just(1,2).concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println);*/
        //onErrorReturn()参数为返回的错误信息
        System.out.println("onErrorReturn---------------");
        Flux.just(1,2).concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);

        System.out.println("switchOnError---------------");
        Flux.just(1,2).concatWith(Mono.error(new IllegalStateException()))
                .switchOnError(Mono.just(0))
                .subscribe(System.out::println);


        System.out.println("onErrorResumeWith---------------");
        Flux.just(1,3).concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResumeWith(e ->{
                    if (e instanceof IllegalStateException){
                        return Mono.just(0);
                    }else if (e instanceof IllegalArgumentException){
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                }).subscribe(System.out::println);

        //retry()参数为重试次数
        System.out.println("retry----------------");
        Flux.just(1,2).concatWith(Mono.error(new IllegalStateException()))
                .retry(1).subscribe(System.out::println);

    }
}
