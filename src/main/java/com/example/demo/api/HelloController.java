package com.example.demo.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {
//    @GetMapping(value="/data", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<String> produceData(){
//        Mono<String> stringData = Mono.just("tao");
//        return stringData;
//    }

//    @GetMapping(value="/data", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<String> produceFlux2(){
//        Mono<String> stringFlux = Mono.just("tao");
//        return stringFlux;
//    }

//    @GetMapping(value="/data", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Flux<String> produceFlux2(){
//        Flux<String> stringFlux = Flux.just("tao","zhao","sheng");
//        return stringFlux;
//    }

    @GetMapping(value="/mono")
    public Mono<String> stringMono() {
        Mono<String> from = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" in Supplier thread: " + Thread.currentThread().getName());
            return "Hello, Spring Reactive data time:" + LocalDateTime.now();
        });
        System.out.println("thread: " + Thread.currentThread().getName() + ", time:" + LocalDateTime.now());
        return from;
    }

    @GetMapping(value="/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> produceFlux(){
        Flux<String> stringFlux = Flux.fromStream(IntStream.range(1,6).mapToObj(i->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" in Supplier thread: " + Thread.currentThread().getName());
            return "java north flux："+i+", date time: "+LocalDateTime.now();
        }));
        System.out.println("thread: " + Thread.currentThread().getName() + ", time:" + LocalDateTime.now());
        return stringFlux;
    }

//    @GetMapping(value="/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> produceFlux2(){
//        Flux<String> stringFlux = Flux.just("tao","zhao","sheng");
//        return stringFlux;
//    }
}
