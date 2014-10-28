package health.osu.com.rehabcoach;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import health.osu.com.rehabcoach.util.CustomFonts;


public class TaskInfoActivity extends Activity {

    CustomFonts mCustomFonts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        initProperties();
    }

    private void initProperties() {
        mCustomFonts = new CustomFonts();
        mCustomFonts.init(getBaseContext(), getWindow().getDecorView());
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
