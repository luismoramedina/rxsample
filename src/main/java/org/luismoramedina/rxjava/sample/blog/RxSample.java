package org.luismoramedina.rxjava.sample.blog;

import org.luismoramedina.rxjava.sample.blog.blogpost.BlogPost;
import org.luismoramedina.rxjava.sample.blog.blogpost.BlogPostService;
import org.luismoramedina.rxjava.sample.blog.likes.Likes;
import org.luismoramedina.rxjava.sample.blog.likes.LikesService;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static org.luismoramedina.rxjava.util.RxUtils.createAsyncObservable;

/**
 * Blocking! no async!
 *
 * @author luismoramedina
 */
public class RxSample {
    public static void main(String[] args) {
        Observable<PostAndLikes> zip = Observable.zip(
                createAsyncObservable(new BlogPostService.BlogPostCommand()),
                createAsyncObservable(new LikesService.LikesErrorCommand())
//                createAsyncObservable(new LikesService.LikesCommand())
                        .retryWhen(errors -> errors
                                .zipWith(Observable.range(1, 3), (n, i) -> i)
                                .flatMap(retryCount -> Observable.timer((long) Math.pow(2, retryCount), TimeUnit.SECONDS))),
                (blogPost, likes) -> new PostAndLikes(((BlogPost) blogPost).id, ((Likes)likes).size));

        System.out.println(zip.toBlocking().single());


    }


}
