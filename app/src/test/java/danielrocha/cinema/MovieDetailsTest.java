package danielrocha.cinema;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by danielrocha on 03/08/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class MovieDetailsTest {

    private ActivityController<MovieDetailsActivity> controller;
    private MovieDetailsActivity activity;

    @Before
    public void setUp() throws Exception {
        controller = Robolectric.buildActivity(MovieDetailsActivity.class);
        activity = controller
                .create()
                .visible()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull( activity );
    }

    private void createWithIntent(String url, String title, String description, double rating, String year) {
        Intent intent = new Intent(RuntimeEnvironment.application, MovieDetailsActivity.class);
        intent.putExtra("extraImage", url);
        intent.putExtra("extraTitle", title);
        intent.putExtra("extraDescription", description);
        intent.putExtra("extraVote", rating);
        intent.putExtra("extraYear", year);



        activity = Robolectric.buildActivity(MovieDetailsActivity.class)
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void createsActivtyWithParams() {
        createWithIntent("http://www.infoglobo.com.br/Anuncie/comum/img/topo/logo_infoglobo_new.gif",
                "infoglobo", "descrição", 5, "2016");

        assertNotNull(activity.getItemDescription());
        assertNotNull(activity.getItemTitle());
        assertNotNull(activity.getItemRating());
        assertNotNull(activity.getItemUrl());
        assertNotNull(activity.getItemYear());
    }

    @After
    public void tearDown() {
        controller
                .pause()
                .stop()
                .destroy();
    }
}
