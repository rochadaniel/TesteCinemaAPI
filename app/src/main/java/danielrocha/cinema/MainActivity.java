package danielrocha.cinema;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import danielrocha.cinema.adapters.ViewPagerAdapter;
import danielrocha.cinema.api.PopularAPI;
import danielrocha.cinema.api.ServiceGenerator;
import danielrocha.cinema.enums.MovieType;
import danielrocha.cinema.fragments.GridFragment;
import danielrocha.cinema.models.MovieModel;
import danielrocha.cinema.models.TheMovieModel;
import danielrocha.cinema.utils.EndlessRecyclerOnScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initViewPager();
        initTabLayout();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        GridFragment popularFragment = GridFragment.newInstance(MovieType.POPULAR);
        GridFragment topRatedFragment = GridFragment.newInstance(MovieType.TOPRATED);
        adapter.addFragment(popularFragment, "POPULAR");
        adapter.addFragment(topRatedFragment, "TOP RATED");
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
    }

   private void initTabLayout() {
       tabLayout.setupWithViewPager(viewPager);
   }

}
