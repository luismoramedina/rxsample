package org.luismoramedina.rxjava.sample;

import org.luismoramedina.rxjava.sample.blogpost.BlogPost;
import org.luismoramedina.rxjava.sample.blogpost.BlogPostService;
import org.luismoramedina.rxjava.sample.likes.Likes;
import org.luismoramedina.rxjava.sample.likes.LikesService;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Date;

/**
 * @author luismoramedina
 */
public class RxSampleAsync {
    public static void main(String[] args) {

        try {
            long start = new Date().getTime();

            final PostAndLikes[] mPostAndLikes = new PostAndLikes[1];
            Observable<Object> postStream = callAsync(new BlogPostService.BlogPostCommand());
            Observable<Object> likesStream = callAsync(new LikesService.LikesErrorCommand());

            Observable.zip(
                    postStream,
                    likesStream,
                    (blogPost, likes) -> new PostAndLikes(((BlogPost) blogPost).id, ((Likes) likes).size))

                    //Blocks until every observable is completed
                    .toBlocking()

                    .subscribe(postAndLikes -> {
                        mPostAndLikes[0] = postAndLikes;
                        System.out.println("All together: " + postAndLikes);
                    });

            System.out.println("mPostAndLikes = " + mPostAndLikes[0]);

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
                } finally {
                    subscriber.onCompleted();
                }
            };

            r.run();

        }).subscribeOn(Schedulers.io());
    }


}
