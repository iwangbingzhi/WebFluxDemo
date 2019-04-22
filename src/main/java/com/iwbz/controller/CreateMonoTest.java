package com.iwbz.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.Optional;

public class CreateMonoTest {
    public static void main(String[] args) {
        //mono静态方法的创建
        Mono.create(monoSink -> monoSink.success("hello Mono")).subscribe(System.out::println);
        Mono.fromSupplier(()->"hello Supplier").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("HELLO")).subscribe(System.out::println);

        //操作符号
        Flux.range(1,100).buffer(20).subscribe(System.out::println);
        System.out.println("-----------------------");

        Flux.intervalMillis(100).bufferMillis(1001).take(1).toStream().forEach(System.out::println);
        System.out.println("-----------------------");

        Flux.range(1,10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        System.out.println("-----------------------");

        Flux.range(1,10).bufferWhile(i -> i % 2 ==0).subscribe(System.out::println);
        System.out.println("-----------------------");

        //filter操作符
        Flux.range(1,10).filter(i -> i % 2 ==0).subscribe(System.out::println);
        System.out.println("-----------------------");

        //windows操作符
        Flux.range(1,100).window(20).subscribe(System.out::println);
        System.out.println("-----------------------");

        Flux.intervalMillis(100).windowMillis(1001).take(2).toStream().forEach(System.out::println);
        System.out.println("-----------------------");

        //zipWith()
        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(System.out::println);

        Flux.just("a","b").zipWith(Flux.just("c","d"),(s1,s2)->String.format("%s-%s",s1,s2))
                .subscribe(System.out::println);
        System.out.println("-----------------------");

        //take()
        System.out.println("take-----------------------");
        Flux.range(1,100).take(10).subscribe(System.out::println);

        System.out.println("takeLast-----------------------");
        Flux.range(1,100).takeLast(1).subscribe(System.out::println);

        System.out.println("takeWhile-----------------------");
        Flux.range(1,100).takeWhile(i -> i <= 10).subscribe(System.out::println);

        System.out.println("takeUntil-----------------------");
        Flux.range(1,100).takeUntil(i -> i == 10).subscribe(System.out::println);


        //reduce() reduceWith()
        System.out.println("reduce-----------------------");
        Flux.range(1,100).reduce((x,y) -> x + y).subscribe(System.out::println);

        System.out.println("reduceWith-----------------------");
        Flux.range(1,100).reduceWith(() -> 100,(x,y) -> x + y).subscribe(System.out::println);

        //merge()
        System.out.println("merge-----------------------");
        Flux.merge(Flux.intervalMillis(0,100).take(5),Flux.intervalMillis(50,100).take(5))
                .toStream().forEach(System.out::println);

        System.out.println("mergeSequential-----------------------");
        Flux.mergeSequential(Flux.intervalMillis(0,100).take(5),Flux.intervalMillis(50,100).take(5))
                .toStream().forEach(System.out::println);

        System.out.println("flatMap-----------------------");
        Flux.just(5,10).flatMap(x -> Flux.intervalMillis(x * 10,100).take(x))
                .toStream().forEach(System.out::println);

        System.out.println("flatMapSequential-----------------------");
        Flux.just(5,10).flatMapSequential(x -> Flux.intervalMillis(x * 10,100).take(x))
                .toStream().forEach(System.out::println);

        System.out.println("concatMap-----------------------");
        Flux.just(5,10).concatMap(x -> Flux.intervalMillis(x * 10,100).take(x))
                .toStream().forEach(System.out::println);

        System.out.println("combineLatest-----------------------");
        Flux.combineLatest(
                Arrays::toString,
                Flux.intervalMillis(100).take(5),
                Flux.intervalMillis(50,100).take(5)).toStream()
                        .forEach(System.out::println);

    }
}
