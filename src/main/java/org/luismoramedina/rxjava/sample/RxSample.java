package org.luismoramedina.rxjava.sample;

import org.luismoramedina.rxjava.sample.blogpost.BlogPostService;
import org.luismoramedina.rxjava.sample.likes.LikesService;
import rx.Observable;

/**
 * @author luismoramedina
 */
public class RxSample {
    public static void main(String[] args) {
        Observable<PostAndLikes> zip = Observable.zip(
                Observable.just(new BlogPostService().getPost()),
                Observable.just(new LikesService().getLikes()),
                (blogPost, likes) -> new PostAndLikes(blogPost.id, likes.size));

        zip.subscribe(postAndLikes -> {
            System.out.println("postAndLikes = " + postAndLikes);
        });

    }


}
