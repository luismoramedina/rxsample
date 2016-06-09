package org.luismoramedina.rxjava.sample;

import org.luismoramedina.rxjava.sample.blogpost.BlogPost;
import org.luismoramedina.rxjava.sample.blogpost.BlogPostService;
import org.luismoramedina.rxjava.sample.likes.Likes;
import org.luismoramedina.rxjava.sample.likes.LikesService;
import rx.Observable;

import java.util.Date;

import static org.luismoramedina.rxjava.util.RxUtils.createAsyncObservable;

/**
 * Emulates a composite service that calls different services to get a composite response.
 *
 * @author luismoramedina
 */
public class RxSampleAsync {
    public static void main(String[] args) {

        try {
            long start = new Date().getTime();

            Observable<BlogPost> postStream = createAsyncObservable(new BlogPostService.BlogPostCommand());
            Observable<Likes> likesStream = createAsyncObservable(new LikesService.LikesCommand());

            //Working with errors
            //Observable<Object> likesStream = createAsyncObservable(new LikesService.LikesErrorCommand());

            PostAndLikes postAndLikes = Observable.zip(
                    postStream,
                    likesStream,
                    (blogPost, likes) -> new PostAndLikes(blogPost.id, likes.size))

                    //Blocks until every observable is completed
                    .toBlocking()

                    //Get the single item result
                    .single();

            System.out.println("postAndLikes = " + postAndLikes);

            System.out.println("-------------" + ((new Date().getTime()) - start));

        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            cause.printStackTrace();
            System.out.println("cause.getMessage() = " + cause.getMessage());
        }

    }


}
