package danielrocha.cinema.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by danielrocha on 01/08/16.
 */
public class MovieModel {
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Long> genre_ids = new ArrayList<Long>();
    private long id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private long vote_count;
    private boolean video;
    private double vote_average;

    /**
     *
     * @return
     * The poster_path
     */
    public String getPoster_path() {
        return poster_path;
    }

    /**
     *
     * @param poster_path
     * The poster_path
     */
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public MovieModel withPoster_path(String poster_path) {
        this.poster_path = poster_path;
        return this;
    }

    /**
     *
     * @return
     * The adult
     */
    public boolean isAdult() {
        return adult;
    }

    /**
     *
     * @param adult
     * The adult
     */
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public MovieModel withAdult(boolean adult) {
        this.adult = adult;
        return this;
    }

    /**
     *
     * @return
     * The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     *
     * @param overview
     * The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public MovieModel withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    /**
     *
     * @return
     * The release_date
     */
    public String getRelease_date() {
        return release_date;
    }

    /**
     *
     * @param release_date
     * The release_date
     */
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public MovieModel withRelease_date(String release_date) {
        this.release_date = release_date;
        return this;
    }

    /**
     *
     * @return
     * The genre_ids
     */
    public List<Long> getGenre_ids() {
        return genre_ids;
    }

    /**
     *
     * @param genre_ids
     * The genre_ids
     */
    public void setGenre_ids(List<Long> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public MovieModel withGenre_ids(List<Long> genre_ids) {
        this.genre_ids = genre_ids;
        return this;
    }

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public MovieModel withId(long id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The original_title
     */
    public String getOriginal_title() {
        return original_title;
    }

    /**
     *
     * @param original_title
     * The original_title
     */
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public MovieModel withOriginal_title(String original_title) {
        this.original_title = original_title;
        return this;
    }

    /**
     *
     * @return
     * The original_language
     */
    public String getOriginal_language() {
        return original_language;
    }

    /**
     *
     * @param original_language
     * The original_language
     */
    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public MovieModel withOriginal_language(String original_language) {
        this.original_language = original_language;
        return this;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public MovieModel withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     *
     * @return
     * The backdrop_path
     */
    public String getBackdrop_path() {
        return backdrop_path;
    }

    /**
     *
     * @param backdrop_path
     * The backdrop_path
     */
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public MovieModel withBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
        return this;
    }

    /**
     *
     * @return
     * The popularity
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public MovieModel withPopularity(double popularity) {
        this.popularity = popularity;
        return this;
    }

    /**
     *
     * @return
     * The vote_count
     */
    public long getVote_count() {
        return vote_count;
    }

    /**
     *
     * @param vote_count
     * The vote_count
     */
    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }

    public MovieModel withVote_count(long vote_count) {
        this.vote_count = vote_count;
        return this;
    }

    /**
     *
     * @return
     * The video
     */
    public boolean isVideo() {
        return video;
    }

    /**
     *
     * @param video
     * The video
     */
    public void setVideo(boolean video) {
        this.video = video;
    }

    public MovieModel withVideo(boolean video) {
        this.video = video;
        return this;
    }

    /**
     *
     * @return
     * The vote_average
     */
    public double getVote_average() {
        return vote_average;
    }

    /**
     *
     * @param vote_average
     * The vote_average
     */
    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public MovieModel withVote_average(double vote_average) {
        this.vote_average = vote_average;
        return this;
    }

    public String getUrl() {
        return "http://image.tmdb.org/t/p/w342" + this.poster_path;
    }

    public Date getDateFromString(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public String getReleaseYear() {
        return this.release_date.split("-")[0];
    }
}
