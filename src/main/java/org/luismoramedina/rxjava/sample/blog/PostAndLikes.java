package org.luismoramedina.rxjava.sample.blog;

/**
 * @author luismoramedina
 */
public class PostAndLikes {
    Integer id;
    Integer size;

    public PostAndLikes(Integer id, Integer size) {
        this.size = size;
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostAndLikes{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
