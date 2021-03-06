package org.luismoramedina.rxjava.sample.blog.blogpost;

import org.luismoramedina.rxjava.util.Command;

/**
 * @author luismoramedina
 */
public class BlogPostService {
    public BlogPost getPost() {
        System.out.println("RxSample.getPost before");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        BlogPost blogPost = new BlogPost(5);
        System.out.println("RxSample.getPost after");
        return blogPost;
    }

    public static class BlogPostCommand implements Command {
        @Override
        public Object execute() {
            return new BlogPostService().getPost();
        }
    }
}
