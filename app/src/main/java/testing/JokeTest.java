package testing;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.OnJokeReceivedListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gmgn on 2/6/2017.
 */
public class JokeTest extends ApplicationTestCase<Application> implements OnJokeReceivedListener {

    CountDownLatch signal;
    String joke;

    public JokeTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Override
    public void onReceived(String joke) {

    }

}
