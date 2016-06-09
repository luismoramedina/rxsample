package org.luismoramedina.rxjava.sample.operators;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author luismoramedina
 */
public class RxOperatorsSamples {
    public static void main(String[] args) {
/*
*/
        System.out.println("Range (0 - 4)");
        Observable<Integer> zeroToFour = Observable.range(0, 5);
        zeroToFour.subscribe(integer -> System.out.println(integer*2));

        System.out.println("Map * 10");
        Observable<Object> map = zeroToFour.map(integer -> integer * 10);
        map.subscribe(System.out::println);

        System.out.println("Just 1, 2 * 10");
        Observable.just(1, 2).subscribe(System.out::println);

        Observable<String> fromList = Observable.from(new String[]{"pilar", "manuel"});
        fromList.subscribe(System.out::println);

        Observable<Object> flatMap = fromList.flatMap(Observable::just);
        flatMap.subscribe(System.out::println);

        //NOT WORKING
        Observable.interval(3, TimeUnit.SECONDS).subscribe(System.out::println);
        Observable<Integer> range = Observable.range(2, 5);
        range.subscribe(System.out::println);
        range.scan((integer, integer2) -> integer + integer2).subscribe(System.out::println);



    }

}
