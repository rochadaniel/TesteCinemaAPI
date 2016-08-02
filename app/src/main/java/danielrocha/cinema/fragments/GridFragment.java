package danielrocha.cinema.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import danielrocha.cinema.MovieDetailsActivity;
import danielrocha.cinema.R;
import danielrocha.cinema.adapters.GalleryAdapter;
import danielrocha.cinema.api.PopularAPI;
import danielrocha.cinema.api.ServiceGenerator;
import danielrocha.cinema.api.TopRatedAPI;
import danielrocha.cinema.enums.MovieType;
import danielrocha.cinema.models.MovieModel;
import danielrocha.cinema.models.TheMovieModel;
import danielrocha.cinema.utils.EndlessRecyclerOnScrollListener;
import danielrocha.cinema.utils.OnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by danielrocha on 02/08/16.
 */
public class GridFragment  extends Fragment implements OnItemClickListener {

    private GalleryAdapter mAdapter;
    private List<MovieModel> movieModels;
    private LinearLayoutManager mLayoutManager;
    private int totalPages;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private int movieType;
    private static final String ARGS_NAME = "movieType";

    public GridFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieType = getArguments().getInt(ARGS_NAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grid, container, false);

        ButterKnife.bind(this, v);

        movieModels = new ArrayList<>();
        mAdapter = new GalleryAdapter(getActivity().getApplicationContext(), movieModels);
        mAdapter.setOnItemClickListener(this);
        initRecyclerView();
        getMovies(1);
        return v;
    }

    public static GridFragment newInstance(MovieType movieType) {
        GridFragment myFragment = new GridFragment();

        Bundle args = new Bundle();
        args.putInt(ARGS_NAME, movieType.getIntValue());
        myFragment.setArguments(args);

        return myFragment;
    }

    private void initRecyclerView() {
        mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {

            @Override
            public void onLoadMore(int current_page) {
                if(current_page <= totalPages) {
                    Toast.makeText(getActivity(), "Pag.: " + current_page, Toast.LENGTH_SHORT).show();
                    getMovies(current_page);
                } else {
                    Toast.makeText(getActivity(), "Total de pag.: " + totalPages, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override public void onItemClick(View view, MovieModel viewModel) {
        MovieDetailsActivity.navigate(getActivity(), view.findViewById(R.id.image), viewModel);
    }

    private void getMovies(int page) {
        String myToken = getResources().getString(R.string.TheMovieAPIKey);
        Call<TheMovieModel> call;
        if(movieType == MovieType.POPULAR.getIntValue()) {
            PopularAPI popularAPI = ServiceGenerator.createService(PopularAPI.class);
            call = popularAPI.getPopular(page, myToken);
        } else {
            TopRatedAPI topRatedAPI = ServiceGenerator.createService(TopRatedAPI.class);
            call = topRatedAPI.getTopRated(page, myToken);
        }
        call.enqueue(new Callback<TheMovieModel>() {
            @Override
            public void onResponse(Call<TheMovieModel> call, Response<TheMovieModel> response) {
                if(response.isSuccessful()) {
                    totalPages = response.body().getTotalPages();
                    movieModels.addAll(response.body().getMovieModels());
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TheMovieModel> call, Throwable t) {
                Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
