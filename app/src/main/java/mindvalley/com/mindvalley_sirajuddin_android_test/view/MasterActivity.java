package mindvalley.com.mindvalley_sirajuddin_android_test.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mindvalley.com.mindvalley_sirajuddin_android_test.R;


/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */


public class MasterActivity extends AppCompatActivity {
    @LayoutRes
    int ContentView;
    public MasterActivity(){}
    public MasterActivity(@LayoutRes int ContentView)
    {
        this.ContentView= ContentView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.ContentView);
        InitializeComponents();
    }

    private void InitializeComponents() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.pins, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

                return super.onOptionsItemSelected(item);
    }


}
