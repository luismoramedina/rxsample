package org.luismoramedina.rxjava.sample.blog.likes;

import org.luismoramedina.rxjava.util.Command;

/**
 * @author luismoramedina
 */
public class LikesService {
    public Likes getLikes() {
        System.out.println("RxSample.getLikes before");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
        Likes likes = new Likes(10);
        System.out.println("RxSample.getLikes after");
        return likes;
    }

    public static class LikesCommand implements Command {
        @Override
        public Object execute() {
            return new LikesService().getLikes();
        }
    }

    public static class LikesErrorCommand implements Command {
        @Override
        public Object execute() {
            System.out.println("Error on Likes command");
            String s = null;
            s.indexOf(0);
            return null;
        }
    }
}
