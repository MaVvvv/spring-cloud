package com.hxb.webflux.FluxMono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-05-31 15:36
 */
public class Test1 {

    public static void main(String[] args) {
        Flux<String> seql = Flux.just("foo","bar","foobar");
        String first = seql.blockFirst();
        String last = seql.blockLast();
        System.out.println(first + ";" + last);
        List<String> iterable = Arrays.asList("foo","bar","foobar");

        Flux<String> seq2 = Flux.fromIterable(iterable);

        Mono<String> data = Mono.just("foo");
        String e = data.block();
        System.out.println(e);
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5,3);

        Flux<Integer> fluxInt = Flux.create(integerFluxSink -> {
            for (int i = 0; i<5; i ++) {
                integerFluxSink.next(i);
            }
            integerFluxSink.complete();
        });

        System.out.println(fluxInt.blockFirst());
        System.out.println(fluxInt.blockLast());


        List<Integer> elements = new ArrayList<>();
        fluxInt.subscribe(elements::add);
    }
}
