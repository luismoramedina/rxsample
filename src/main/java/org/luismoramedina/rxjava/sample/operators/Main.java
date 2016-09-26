package org.luismoramedina.rxjava.sample.operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author luismoramedina
 */
public class Main {
    public static void main(String[] args) {

   /*     Observable<String> just = Observable.just("Este es mi unico evento");
        just.subscribe(System.out::println);

        System.out.println("as list");
        Observable<String> from = Observable.from(asList("hola", "adios"));
        from.subscribe(System.out::println);

        System.out.println("create");
        Observable.create(subscriber -> {
            subscriber.onNext("hola");
            subscriber.onNext("adios");
            subscriber.onCompleted();
        }).subscribe(System.out::println);*/

        //Observable.just(doSomething()).subscribe(System.out::println);

        System.out.println("as list");
        Observable<String> from = Observable.from(asList("hola", "adios"));

    }

    private static String doSomething() {
        return "hola from method";
    }
}
