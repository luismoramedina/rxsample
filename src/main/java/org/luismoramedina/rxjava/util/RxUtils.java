package org.luismoramedina.rxjava.util;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 *
 * @author luismoramedina
 */
public class RxUtils {

    public static <T> Observable<T> createAsyncObservable(Command command) {
        return (Observable<T>) Observable.create(subscriber -> {
            try {
                subscriber.onNext(command.execute());
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }
}
