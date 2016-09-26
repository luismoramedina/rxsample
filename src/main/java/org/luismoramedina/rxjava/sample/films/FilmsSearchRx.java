package org.luismoramedina.rxjava.sample.films;

import rx.Observable;
import rx.functions.Func2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.luismoramedina.rxjava.util.RxUtils.createAsyncObservable;

/**
 * This clones imdb search feature:
 * <ul>
 *     <li>Search a list of films</li>
 *     <li>For each film gets (data and reviews)</li>
 *     <li>System.out...</li>
 * </ul>
 *
 * We use RxJava implementation to handle different streams, you can run in sync or async mode,
 * check {@link FilmsSearchRx#sync}. And you can change the {@link FilmsSearchRx#DELAY} to check the service
 * behaviour when the delay grows.
 *
 * @author luismoramedina
 */
public class FilmsSearchRx {

    private static final int DELAY = 1000;
    private static boolean sync = false;

    public static void main(String[] args) throws Throwable{

        long start = new Date().getTime();
        String search = "history";

        Observable<Object> booksStream = Observable.from(searchByTitle(search));
        Observable<Object> filmDataAndReviewsObservable = booksStream.flatMap(o -> {

            Observable<String> filmDataStream;
            Observable<String[]> filmReviewsStream;

            if (sync) {
                filmDataStream = Observable.just(searchFilmData((String) o));
                filmReviewsStream = Observable.just(searchFilmReviews((String) o));
            } else {
                filmDataStream = createAsyncObservable(() -> searchFilmData((String) o));
                filmReviewsStream = createAsyncObservable(() -> searchFilmReviews((String) o));
            }

            return Observable.zip(filmDataStream, filmReviewsStream, (Func2<Object, Object, Object>)
                    (data, reviews) -> new FilmDataAndReviews(((String) data), ((String[]) reviews)));
        });


        List<Object> films = filmDataAndReviewsObservable.toList().toBlocking().single();

        for (Object film : films) {
            System.out.println("film = " + Arrays.toString(((FilmDataAndReviews) film).getReviews()));
        }

        System.out.println(new Date().getTime() - start + "... syn: " + sync + " and delay:" + DELAY);
    }

    private static String[] searchFilmReviews(String film) {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException ignored) {
        }
        if (film.startsWith("America")) {
            return new String[]{"Aggresive", "Bad"};
        } else {
            return new String[]{"Good!"};
        }
    }

    private static String searchFilmData(String query) {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException ignored) {
        }
        if (query.startsWith("America")) {
            return "AHX";
        } else {
            return "AHOV";
        }
    }

    private static String[] searchByTitle(String title) throws InterruptedException {
        System.out.println("searching... " + title);
        Thread.sleep(DELAY);
        return new String[]{"American history X", "A history of violence"};
    }

    private static class FilmDataAndReviews {
        String filmData;
        String[] reviews;

        public FilmDataAndReviews(String filmData, String[] reviews) {
            this.filmData = filmData;
            this.reviews = reviews;
        }

        @Override
        public String toString() {
            return "FilmDataAndReviews{" +
                    "filmData='" + filmData + '\'' +
                    ", reviews=" + Arrays.toString(reviews) +
                    '}';
        }

        public String getFilmData() {
            return filmData;
        }

        public String[] getReviews() {
            return reviews;
        }
    }
}
