package org.luismoramedina.rxjava.sample.operators;

import rx.Observable;
import rx.functions.*;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * @author luismoramedina
 */
public class RxOperatorsSamples {
    public static void main(String[] args) {
/*
*/

        //From some list to single list with collect
        Observable<List<String>> firstList = Observable.just(asList("1", "2"));
        Observable<List<String>> secondList = Observable.just(asList("3", "5", "1"));
        Observable<List<String>> mergeStream = Observable.merge(firstList, secondList);

        firstList.subscribe(System.out::println);
        secondList.subscribe(System.out::println);

        mergeStream.subscribe(System.out::println);

        mergeStream.collect(
                (Func0<ArrayList<String>>) ArrayList::new, ArrayList::addAll)
                .subscribe(System.out::println);

        System.out.println("Range (0 - 4)");
        Observable<Integer> zeroToFour = Observable.range(0, 5);
        zeroToFour.subscribe(System.out::println);

        System.out.println("Map * 10");
        zeroToFour
                .map(i -> i * 10)
                .subscribe(System.out::println);


        System.out.println("Just 1, 2");
        Observable.just(1, 2).subscribe(System.out::println);

        System.out.println("From list");
        Observable<String> fromList = Observable.from(new String[]{"pilar", "manuel"});
        fromList.subscribe(System.out::println);

        System.out.println("Flatmap/mapMany");
        Observable<Object> flatMap = fromList.flatMap(
                s -> Observable.from(new String[]{s, s + " apellido"}));
        flatMap.subscribe(System.out::println);

        //NOT WORKING
        System.out.println("Range");
        Observable<Integer> range = Observable.range(2, 5);
        range.subscribe(System.out::println);
        System.out.println("Scan");
        range.scan((integer, integer2) -> integer + integer2).subscribe(System.out::println);
        System.exit(0);



    }

}
