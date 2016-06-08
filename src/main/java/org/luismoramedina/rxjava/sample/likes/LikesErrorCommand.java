package org.luismoramedina.rxjava.sample.likes;

import org.luismoramedina.rxjava.sample.Command;

/**
 * @author luismoramedina
 */
public class LikesErrorCommand implements Command {
    @Override
    public Object execute() {
        String s = null;
        s.indexOf(0);
        return null;
    }
}
