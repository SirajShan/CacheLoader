package mindvalley.com.mindvalley_sirajuddin_android_test.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import mindvalley.com.mindvalley_sirajuddin_android_test.R;

/**
 * @author Sirajuddin
 * @purpose To Show the Splash screen until the timer is over
Then Starts app main activity.
 * @since 03-09-2016
 */

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(WelcomeScreenActivity.this, PinsBoardActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
