package danielrocha.cinema.api;

import java.util.ArrayList;

import danielrocha.cinema.models.MovieModel;
import danielrocha.cinema.models.TheMovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by danielrocha on 01/08/16.
 */
public interface TopRatedAPI {
    @GET("top_rated/{api_key}")
    Call<TheMovieModel> getTopRated(@Path("api_key") String apiKey);
}
