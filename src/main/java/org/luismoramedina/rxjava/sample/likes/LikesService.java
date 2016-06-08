package org.luismoramedina.rxjava.sample.likes;

import org.luismoramedina.rxjava.sample.Command;

/**
 * @author luismoramedina
 */
public class LikesService {
    public Likes getLikes() {
        System.out.println("RxSample.getLikes before");
        try {
            Thread.sleep(5000);
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
            String s = null;
            s.indexOf(0);
            return null;
        }
    }
}
