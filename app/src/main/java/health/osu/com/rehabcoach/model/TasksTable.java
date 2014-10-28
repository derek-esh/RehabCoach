package health.osu.com.rehabcoach.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by brainfreak on 10/27/14.
 */
public class TasksTable {
    public static final String QUERY_SELECT_FROM = "select * from ";
    public static String TABLE_TASKS = "tasks_table";
    public static String KEY_ID = "_id";
    public static String KEY_NAME = "name";
    public static String KEY_DESCRIPTION = "description";
    public static String KEY_DATE = "date";
    public static String KEY_INTERVAL = "interval";
    public static String KEY_FREQUENCY = "frequency";
    public static String KEY_TIME = "time";
    public static String KEY_END_DATE = "end_date";
    public static String KEY_COUNT = "count";
    public static String KEY_ALIVE = "alive";
    public static String KEY_EXTRAS = "extras";
    public static String KEY_PRIORITY = "priority";

    public static String[] COLUMN_KEYS = { KEY_ID, KEY_NAME, KEY_EXTRAS, KEY_FREQUENCY,
                                            KEY_ALIVE, KEY_COUNT, KEY_DATE, KEY_DESCRIPTION,
                                                KEY_END_DATE, KEY_INTERVAL, KEY_TIME, KEY_PRIORITY };
    Context mContext;
    MySQLiteHelper mMySQLiteHelper;
    SQLiteDatabase mSQLiteDatabase;

    public TasksTable(Context context){
        mContext = context;
        mMySQLiteHelper = new MySQLiteHelper(context);
        open();
        createTable();
    }

    private void open() throws SQLException {
        mSQLiteDatabase = mMySQLiteHelper.getWritableDatabase();
    }

    private void close() {
        mSQLiteDatabase.close();
    }

    private void createTable(){
        String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, " + KEY_NAME + " TEXT , "
                + KEY_DESCRIPTION + " TEXT , " + KEY_DATE + " TEXT , " + KEY_INTERVAL + " INTEGER , " +
                KEY_FREQUENCY + " TEXT , " + KEY_TIME + " TEXT , " + KEY_END_DATE + " TEXT , " +
                KEY_COUNT + " INTEGER , " + KEY_ALIVE + " BOOLEAN , " + KEY_EXTRAS + " TEXT , " + KEY_PRIORITY + " BOOLEAN " + ");";

        mSQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    public boolean addNewTask(UserTask task) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_INTERVAL, task.getInterval());
        contentValues.put(KEY_NAME, task.getName());
        contentValues.put(KEY_ALIVE, task.isAlive());
        contentValues.put(KEY_COUNT, task.getCount());
        contentValues.put(KEY_DATE, task.getDate());
        contentValues.put(KEY_END_DATE, task.getEndDate());
        contentValues.put(KEY_DESCRIPTION, task.getDescription());
        contentValues.put(KEY_FREQUENCY, task.getFrequency());
        contentValues.put(KEY_PRIORITY, task.isHighPriority());
        contentValues.put(KEY_EXTRAS, task.getExtras());
        contentValues.put(KEY_TIME, task.getTime());

        try {
            if (mSQLiteDatabase.insertOrThrow(TABLE_TASKS, null, contentValues) < 0) {

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Cursor getAllTasksCursor() {
        return mSQLiteDatabase.rawQuery(QUERY_SELECT_FROM + TABLE_TASKS, null);
    }
}
