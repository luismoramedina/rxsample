package org.luismoramedina.rxjava.sample;

import org.luismoramedina.rxjava.sample.blogpost.BlogPost;
import org.luismoramedina.rxjava.sample.blogpost.BlogPostService;
import org.luismoramedina.rxjava.sample.likes.Likes;
import org.luismoramedina.rxjava.sample.likes.LikesService;
import rx.Observable;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

import java.util.Date;

/**
 * @author luismoramedina
 */
public class RxSampleAsync {
    public static void main(String[] args) {

        try {
            long start = new Date().getTime();

            Observable<Object> postStream = callAsync(new BlogPostService.BlogPostCommand());
            Observable<Object> likesStream = callAsync(new LikesService.LikesCommand());

            BlockingObservable<PostAndLikes> zip = Observable.zip(postStream, likesStream,
                    (blogPost, likes) -> new PostAndLikes(((BlogPost) blogPost).id, ((Likes) likes).size)).toBlocking();

            zip.subscribe(postAndLikes -> {
                System.out.println("All together: " + postAndLikes);
            });

            System.out.println("-------------" + ((new Date().getTime()) - start));
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            cause.printStackTrace();
            System.out.println("cause.getMessage() = " + cause.getMessage());
        }

    }


    private static Observable<Object> callAsync(Command command) {
        return Observable.create(subscriber -> {

            Runnable r = () -> {
                try {
                    subscriber.onNext(command.execute());
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            };

            r.run();

        }).subscribeOn(Schedulers.io());
    }


}
