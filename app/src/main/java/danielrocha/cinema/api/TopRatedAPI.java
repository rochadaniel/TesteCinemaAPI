package danielrocha.cinema.api;

import java.util.ArrayList;

import danielrocha.cinema.models.MovieModel;
import danielrocha.cinema.models.TheMovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by danielrocha on 01/08/16.
 */
public interface TopRatedAPI {
    @GET("top_rated")
    Call<TheMovieModel> getTopRated(@Query("page") int page, @Query("api_key") String apiKey);
}
