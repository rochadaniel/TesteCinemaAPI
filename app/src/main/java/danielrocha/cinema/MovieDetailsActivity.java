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

    private String itemTitle, itemDescription, itemUrl, itemYear;
    private double itemRating;

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.title) TextView title;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.year) TextView year;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.ratingBar) RatingBar ratingBar;
    @Bind(R.id.toolbar) Toolbar toolbar;

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

        initToolbar();

        ratingBar.setRating(0.0f);

        if(savedInstanceState == null) {
            itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
            itemDescription = getIntent().getStringExtra(EXTRA_DESCRIPTION);
            itemYear = getIntent().getStringExtra(EXTRA_YEAR);
            itemUrl = getIntent().getStringExtra(EXTRA_IMAGE);
            itemRating = (getIntent().getDoubleExtra(EXTRA_VOTE, 0)/2);
        } else {
            itemTitle = savedInstanceState.getString(EXTRA_TITLE);
            itemDescription = savedInstanceState.getString(EXTRA_DESCRIPTION);
            itemYear = savedInstanceState.getString(EXTRA_YEAR);
            itemUrl = savedInstanceState.getString(EXTRA_IMAGE);
            itemRating = savedInstanceState.getDouble(EXTRA_VOTE);
        }


        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        try {
            Glide.with(MovieDetailsActivity.this)
                    .load(itemUrl)
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
        } catch (Exception e) {
            Toast.makeText(MovieDetailsActivity.this, "teste", Toast.LENGTH_SHORT).show();
        }

        title.setText(itemTitle);
        description.setText(itemDescription);
        year.setText(itemYear);
        ratingBar.setRating(Float.parseFloat(itemRating + "f"));

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EXTRA_IMAGE, itemUrl);
        savedInstanceState.putString(EXTRA_TITLE, itemTitle);
        savedInstanceState.putString(EXTRA_DESCRIPTION, itemDescription);
        savedInstanceState.putDouble(EXTRA_VOTE, itemRating);
        savedInstanceState.putString(EXTRA_YEAR, itemYear);
    }
}
