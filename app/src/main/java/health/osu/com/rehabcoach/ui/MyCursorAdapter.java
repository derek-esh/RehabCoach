package health.osu.com.rehabcoach.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import health.osu.com.rehabcoach.R;
import health.osu.com.rehabcoach.model.TasksTable;
import health.osu.com.rehabcoach.util.CustomFonts;

/**
 * Created by brainfreak on 10/27/14.
 */
public class MyCursorAdapter extends CursorAdapter {

    Cursor mCursor;
    Context mContext;
    int mResource, mFlags;
    LayoutInflater mLayoutInflater;
    CustomFonts customFonts;

    public MyCursorAdapter(Context context, int resource, Cursor cursor, int flags) {
        super(context, cursor, flags);
        mCursor = cursor;
        mContext = context;
        mResource = resource;
        mFlags = flags;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customFonts = new CustomFonts();

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = mLayoutInflater.inflate(mResource, null);

        TaskViewHolder holder = new TaskViewHolder();
        holder.mTaskNameTextView = (TextView) view.findViewById(R.id.taskNameTextView);
        holder.mTaskTimeTextView = (TextView) view.findViewById(R.id.taskTimeTextView);

        view.setTag(holder);
        customFonts.init(mContext, view);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TaskViewHolder holder = (TaskViewHolder) view.getTag();
        holder.mTaskNameTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(TasksTable.KEY_NAME)));
        holder.mTaskTimeTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow(TasksTable.KEY_TIME)));
    }

}
