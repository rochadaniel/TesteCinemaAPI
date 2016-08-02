package danielrocha.cinema;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.Bind;
import butterknife.ButterKnife;
import danielrocha.cinema.models.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "extraImage";
    private static final String EXTRA_TITLE = "extraTitle";
    private static final String EXTRA_DESCRIPTION = "extraDescription";
    private static final String EXTRA_VOTE = "extraVote";
    private static final String EXTRA_YEAR = "extraYear";

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.title) TextView title;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.year) TextView year;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.ratingBar) RatingBar ratingBar;

    public static void navigate(FragmentActivity activity, View transitionImage, MovieModel viewModel) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_IMAGE, viewModel.getUrl());
        intent.putExtra(EXTRA_TITLE, viewModel.getTitle());
        intent.putExtra(EXTRA_DESCRIPTION, viewModel.getOverview());
        intent.putExtra(EXTRA_VOTE, viewModel.getVote_average());
        intent.putExtra(EXTRA_YEAR, viewModel.getReleaseYear());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ratingBar.setRating(0.0f);

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);

        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Glide.with(MovieDetailsActivity.this)
                .load(getIntent().getStringExtra(EXTRA_IMAGE))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        image.setImageBitmap(resource);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette palette) {
                                applyPalette(palette);
                            }
                        });
                    }
                });

        title.setText(itemTitle);
        description.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
        year.setText(getIntent().getStringExtra(EXTRA_YEAR));
        double rating = (getIntent().getDoubleExtra(EXTRA_VOTE, 0)/2);
        float newRating = (float) (rating/2);

        ratingBar.setRating(Float.parseFloat(rating + "f"));

    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void applyPalette(Palette palette) {
        //int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
        //int primary = getResources().getColor(R.color.colorPrimary);
        //collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        //collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));

        supportStartPostponedEnterTransition();
    }

}
