package danielrocha.cinema;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import danielrocha.cinema.adapters.GalleryAdapter;
import danielrocha.cinema.api.PopularAPI;
import danielrocha.cinema.api.ServiceGenerator;
import danielrocha.cinema.models.MovieModel;
import danielrocha.cinema.models.TheMovieModel;
import danielrocha.cinema.utils.EndlessRecyclerOnScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private List<MovieModel> movieModels;
    private LinearLayoutManager mLayoutManager;
    private int totalPages;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        pDialog = new ProgressDialog(this);
        movieModels = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), movieModels);
        initToolbar();
        initRecyclerView();
        getMovies(1);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(MainActivity.this, movieModels.get(position).getOriginal_title(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {

            @Override
            public void onLoadMore(int current_page) {
                /*if(perfilListAdapter.getListSize() < perfilListAdapter.getTotalOrders()) {
                    //Toast.makeText(getActivity(), "LOAD MORE", Toast.LENGTH_SHORT).show();
                    getOrders();
                }*/
                if(current_page <= totalPages) {
                    Toast.makeText(MainActivity.this, "Pag.: " + current_page, Toast.LENGTH_SHORT).show();
                    getMovies(current_page);
                } else {
                    Toast.makeText(MainActivity.this, "Total de pag.: " + totalPages, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getMovies(int page) {
        PopularAPI popularAPI = ServiceGenerator.createService(PopularAPI.class);
        String myToken = getResources().getString(R.string.TheMovieAPIKey);
        Call<TheMovieModel> call = popularAPI.getPopular(page, myToken);
        call.enqueue(new Callback<TheMovieModel>() {
            @Override
            public void onResponse(Call<TheMovieModel> call, Response<TheMovieModel> response) {
                if(response.isSuccessful()) {
                    totalPages = response.body().getTotalPages();
                    movieModels.addAll(response.body().getMovieModels());
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TheMovieModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
