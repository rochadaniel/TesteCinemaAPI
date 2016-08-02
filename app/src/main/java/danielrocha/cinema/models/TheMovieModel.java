package danielrocha.cinema.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielrocha on 01/08/16.
 */
public class TheMovieModel {
    private Integer page;
    private List<MovieModel> results = new ArrayList<MovieModel>();
    private Integer total_pages;
    private Integer total_results;

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public TheMovieModel withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     *
     * @return
     * The movieModels
     */
    public List<MovieModel> getMovieModels() {
        return results;
    }

    /**
     *
     * @param movieModels
     * The movieModels
     */
    public void setMovieModels(List<MovieModel> movieModels) {
        this.results = movieModels;
    }

    public TheMovieModel withMovieModels(List<MovieModel> movieModels) {
        this.results = movieModels;
        return this;
    }

    /**
     *
     * @return
     * The totalResults
     */
    public Integer getTotalResults() {
        return total_results;
    }

    /**
     *
     * @param totalResults
     * The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.total_results = totalResults;
    }

    public TheMovieModel withTotalResults(Integer totalResults) {
        this.total_results = totalResults;
        return this;
    }

    /**
     *
     * @return
     * The totalPages
     */
    public Integer getTotalPages() {
        return total_pages;
    }

    /**
     *
     * @param totalPages
     * The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.total_pages = totalPages;
    }

    public TheMovieModel withTotalPages(Integer totalPages) {
        this.total_pages = totalPages;
        return this;
    }
}
