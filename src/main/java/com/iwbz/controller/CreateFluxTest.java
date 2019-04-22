package com.iwbz.controller;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

public class CreateFluxTest {
    public static void main(String[] args) {
/*
        //使用Flux静态方法创建Flux
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.just("hello","world").subscribe(System.out::print);*/
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS))
                .subscribe(System.out::println);

        //使用Flux generate()方法创建Flux
        Flux.generate(sink ->{
                    sink.next("hello!");
                    sink.complete();
                }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new,(list,sink)->{
            int value = random.nextInt(10);
            list.add(value);
            sink.next(value);
            if (list.size() == 10){
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
        System.out.println("generate分割----------");
        //使用Flux create()创建Flux
        Flux.create(sink ->{
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }
}
