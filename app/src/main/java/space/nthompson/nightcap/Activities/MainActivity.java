package space.nthompson.nightcap.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

import space.nthompson.nightcap.Adapters.ViewPagerAdapter;
import space.nthompson.nightcap.Fragments.CabFrag;
import space.nthompson.nightcap.Fragments.CockFrag;
import space.nthompson.nightcap.R;

public class MainActivity extends AppCompatActivity {

    TwitterSession session = Twitter.getSessionManager().getActiveSession();
    TwitterAuthToken authToken = session.getAuthToken();

    Toolbar actionToolbar;

    Context context = this;
    String token = authToken.token;
    String secret = authToken.secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting toolbar as acting actionbar and configuring
        actionToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_local_bar_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_add_circle_black_24dp));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_logout){
            AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                    .setTitle("Logout?")
                    .setMessage("Are you sure you want to logout?")
                    .setCancelable(true)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Twitter.getSessionManager().clearActiveSession();
                            Twitter.logOut();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            dialog.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
