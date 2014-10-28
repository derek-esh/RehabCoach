package health.osu.com.rehabcoach;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import health.osu.com.rehabcoach.model.TasksTable;
import health.osu.com.rehabcoach.model.UserTask;
import health.osu.com.rehabcoach.ui.MyCursorAdapter;
import health.osu.com.rehabcoach.util.CustomFonts;


public class ScheduleActivity extends FragmentActivity implements View.OnClickListener, AddTaskDialogFragment.OnDismissListener {

    private static final int MY_REQUEST_CODE = 110;
    ListView mTasksListView;
    View mNextTaskView;
    SimpleAdapter mTasksAdapter;
    ArrayList<HashMap<String, ?>> mAllTasksList;
    String[] mTasksListFrom;
    int[] mTasksListTo;
    SwingRightInAnimationAdapter mTasksAnimationAdapter;
    FloatingActionButton mFloatingActionButton;
    FragmentManager mFragmentManager;
    AdapterView.OnItemClickListener mOnTaskItemClickListener;
    MyCursorAdapter mTasksCursorAdapter;
    TasksTable mTasksTable;
    AddTaskDialogFragment mAddTaskDialogFragment;
    CustomFonts mCustomFonts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initProperties();
        initData();
        initListeners();
    }

    void initProperties() {
        mTasksListView = (ListView) findViewById(R.id.allTasksListView);
        mNextTaskView = findViewById(R.id.nextTaskView);
        layoutNextTask();
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.button_floating_action);
        mFragmentManager = getSupportFragmentManager();
        mCustomFonts = new CustomFonts();
        mCustomFonts.init(getBaseContext(), getWindow().getDecorView());
    }

    void initData() {
        mTasksTable = new TasksTable(getBaseContext());
        mAllTasksList = new ArrayList<HashMap<String, ?>>();
        mTasksListFrom = new String[] { "task_name", "task_time" };
        mTasksListTo = new int[] { R.id.taskNameTextView, R.id.taskTimeTextView };
        //mTasksAdapter = new SimpleAdapter(this, mAllTasksList, R.layout.listitem_task, mTasksListFrom, mTasksListTo);
        mTasksCursorAdapter = new MyCursorAdapter(getBaseContext(), R.layout.listitem_task, mTasksTable.getAllTasksCursor(), 0);
        mTasksAnimationAdapter = new SwingRightInAnimationAdapter(mTasksCursorAdapter);

        displayNextTask();
    }

    void layoutNextTask() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup) mNextTaskView.getParent();
        int index = parent.indexOfChild(mNextTaskView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                                                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        parent.removeView(mNextTaskView);
        mNextTaskView = layoutInflater.inflate(R.layout.listitem_task, parent, false);
        mNextTaskView.setLayoutParams(layoutParams);
        parent.addView(mNextTaskView, index);
    }

    void displayNextTask() {
        ((TextView) mNextTaskView.findViewById(mTasksListTo[0])).setText("Blow the candles");
        ((TextView) mNextTaskView.findViewById(mTasksListTo[1])).setText("2:22 PM");

    }

    void initListeners() {

        mFloatingActionButton.attachToListView(mTasksListView);
        mTasksAnimationAdapter.setAbsListView(mTasksListView);
        mTasksListView.setAdapter(mTasksAnimationAdapter);
        mFloatingActionButton.setOnClickListener(this);

        mTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("on", "item click");
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);

                Intent intent = new Intent(ScheduleActivity.this, TaskInfoActivity.class);
                intent.putExtra("task_name", cursor.getString(cursor.getColumnIndex(TasksTable.KEY_NAME)));
                intent.putExtra("task_description", cursor.getString(cursor.getColumnIndex(TasksTable.KEY_DESCRIPTION)));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_floating_action:
                mAddTaskDialogFragment = new AddTaskDialogFragment();
                mAddTaskDialogFragment.show(mFragmentManager, "");
                break;
        }
    }

    @Override
    public void onDismiss() {
        mTasksCursorAdapter.changeCursor(mTasksTable.getAllTasksCursor());
        mTasksAnimationAdapter.notifyDataSetChanged();
        Log.i("on", "dismiss");
    }
}
