import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * @author luismoramedina
 */
public class Service1 {
    public String operation(String text) {
        System.out.println("Start: Executing slow task in Service " + text);
        delay(3000);
        System.out.println("End: Executing slow task in Service " + text);
        return "operation" + text;
    }


    public Observable<String> operationRx(String text) {
        System.out.println("Start: Executing slow task in Service " + text);
        delay(1000);
        System.out.println("End: Executing slow task in Service " + text);
        return Observable.just("operation" + text);
    }

    public Observable<String> operationRxAsync(String text) {
        return Observable.<String>create(s -> {
            System.out.println("Start: Executing slow task in Service " + text);
            delay(1000);
            s.onNext("operation " + text);
            System.out.println("End: Executing slow task in Service " + text);
            s.onCompleted();
        }).subscribeOn(Schedulers.computation());
    }


    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ignored) {}

    }

    public static void main(String[] args) {
        Observable<String> operation1 = new Service1().operationRxAsync("1");
        Observable<String> operation2 = new Service1().operationRxAsync("2");
        Observable<String> operation3 = new Service1().operationRxAsync("3");
        Observable<String> merge = Observable.merge(operation1, operation2, operation3);
        Observable<List<String>> lst = merge.toList();
        System.out.println("strings = " + lst);
    }
}
