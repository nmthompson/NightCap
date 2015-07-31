package space.nthompson.nightcap.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import space.nthompson.nightcap.R;

import android.content.Intent;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "f26uIyrKNBHCOVNfCkoTiHCCj";
    private static final String TWITTER_SECRET = "0MUOv4BGXzFtMACTKLmEwp5xS1pNqCk57Pntu0UIzDHv4jMkgB ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));


        final Intent intent = new Intent(this, MainActivity.class);

        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if(twitterSession != null){
            startActivity(intent);
            finish();
        }

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                startActivity(intent);
                finish();

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }



}
