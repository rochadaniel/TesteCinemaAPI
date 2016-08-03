package danielrocha.cinema;

import android.os.Build;

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
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by danielrocha on 03/08/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class MainActivityTest {

    private ActivityController<MainActivity> controller;
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        controller = Robolectric.buildActivity(MainActivity.class);

        mainActivity = controller
                .create()
                .visible()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull( mainActivity );
    }

    @Test
    public void shouldHaveTheMovieApiKey() throws Exception {
        String apiKey = mainActivity.getResources().getString(
                R.string.TheMovieAPIKey);
        assertNotNull(apiKey);
    }

    @After
    public void tearDown() {
        controller
                .pause()
                .stop()
                .destroy();
    }

}
