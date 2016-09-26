package org.luismoramedina.rxjava.sample.merge;

import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author luismoramedina
 */
public class RxMergeSample {
    public static void main(String[] args) {

        rx.Observable<Integer> firstList = getListObservable(1, 2);
        rx.Observable<Integer> secondList = getListObservable(2, 3);

        List<Integer> data = new ArrayList<>();
        Observable.merge(secondList, firstList).subscribe(data::add);
        System.out.println(data);

    }

    private static Observable<Integer> getListObservable(Integer... i) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                List<Integer> list = asList(i);
                for (Integer integer : list) {
                    subscriber.onNext(integer);
                }
                subscriber.onCompleted();
            }
        });
    }

}
