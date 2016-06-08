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
        Likes just = new Likes(10);
        System.out.println("RxSample.getLikes after");
        return just;
    }

    public static class LikesCommand implements Command {
        @Override
        public Object execute() {
            return new LikesService().getLikes();
        }
    }
}
