package health.osu.com.rehabcoach;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.CheckBox;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import java.util.Calendar;
import java.util.Date;

import be.billington.calendar.recurrencepicker.EventRecurrence;
import be.billington.calendar.recurrencepicker.EventRecurrenceFormatter;
import be.billington.calendar.recurrencepicker.RecurrencePickerDialog;
import health.osu.com.rehabcoach.model.TasksTable;
import health.osu.com.rehabcoach.model.UserTask;
import health.osu.com.rehabcoach.util.RecurrenceEvent;

/**
 * Created by brainfreak on 10/23/14.
 */
public class AddTaskDialogFragment extends DialogFragment implements View.OnClickListener, View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {
    private static final String TIMEPICKER_TAG = "timepicker";
    public static final String PLEASE_CHOOSE_DATE = "Please choose date";
    public static final String PLEASE_CHOOSE_TIME = "Please choose time";
    public static final String TASK_NAME_CHARACTERS = "Task name must have atleast 3 characters";
    CheckBox mRepeatTaskCheckBox;
    RecurrencePickerDialog mDayPickerDialog;
    String mRepeatTaskRule, mRecurranceDate;
    View mView;
    FloatLabeledEditText mTaskNameEditText, mTaskDescriptionEditText;
    EditText mTimeEditText;
    ButtonFlat mAddTaskButton, mCancelButton;
    LinearLayout mDateLinearLayout;
    TextView mDateTextView;
    TasksTable mTasksTable;
    OnDismissListener mOnDismissListener;

    private void initListeners() {
        mRepeatTaskCheckBox.setOnClickListener(this);
        mTimeEditText.setOnClickListener(this);
        mTimeEditText.setOnFocusChangeListener(this);
        mAddTaskButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
    }

    private void initProperties() {
        mRepeatTaskCheckBox = (CheckBox) mView.findViewById(R.id.repeatingTaskCheckBox);
        mTimeEditText = (EditText) mView.findViewById(R.id.timeEditText);
        mTaskNameEditText = (FloatLabeledEditText) mView.findViewById(R.id.taskNameEditText);
        mTaskDescriptionEditText = (FloatLabeledEditText) mView.findViewById(R.id.taskDescriptionEditText);
        mAddTaskButton = (ButtonFlat) mView.findViewById(R.id.taskAddButton);
        mCancelButton = (ButtonFlat) mView.findViewById(R.id.taskCancelButton);
        mDateLinearLayout = (LinearLayout) mView.findViewById(R.id.repeatDataLinearLayout);
        mDateTextView = (TextView) mView.findViewById(R.id.dateTextView);
        mTasksTable = new TasksTable(getActivity().getBaseContext());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnDismissListener = (OnDismissListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.activity_add_task, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        initProperties();
        initListeners();

        return mView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.repeatingTaskCheckBox:
                CheckBox checkBox = (CheckBox) v;
                Log.i("on", "click");

                if (checkBox.isChecked()) {
                    mDayPickerDialog = new RecurrencePickerDialog();

                    if (mRepeatTaskRule != null && mRepeatTaskRule.length() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(RecurrencePickerDialog.BUNDLE_RRULE, mRepeatTaskRule);
                        mDayPickerDialog.setArguments(bundle);
                    }

                    mDayPickerDialog.setOnRecurrenceSetListener(new RecurrencePickerDialog.OnRecurrenceSetListener() {
                        @Override
                        public void onRecurrenceSet(String rrule) {

                            if (rrule != null && rrule.length() > 0) {
                                EventRecurrence recurrenceEvent = new EventRecurrence();
                                recurrenceEvent.setStartDate(new Time("" + new Date().getTime()));
                                recurrenceEvent.parse(rrule);
                                mRecurranceDate = rrule;

                                populateDateView();
                                String srt = EventRecurrenceFormatter.getRepeatString(getActivity().getBaseContext(), getResources(), recurrenceEvent, true);
                                mDateLinearLayout.setVisibility(View.VISIBLE);
                                mDateTextView.setText(srt);
                            } else {
                                Toast.makeText(getActivity().getBaseContext(), "Nothing", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    mDayPickerDialog.show(getActivity().getSupportFragmentManager(), "recurrencePicker");
                }
                break;

            case R.id.timeEditText:
                Log.i("on", "click");
                showDialog();
                break;

            case R.id.taskAddButton:
                if(validateTaskInfo()) {
                    addNewTask();
                    mOnDismissListener.onDismiss();
                    dismiss();
                }
                break;

            case R.id.taskCancelButton:
                mOnDismissListener.onDismiss();
                dismiss();
        }
    }

    private void addNewTask() {
        UserTask userTask = new UserTask();
        userTask.setAlive(true);
        userTask.setName(mTaskNameEditText.getText().toString());
        userTask.setDescription(mTaskDescriptionEditText.getText().toString());
        userTask.setTime(mTimeEditText.getText().toString());
        parseDate(userTask);
        userTask.setDate(RecurrenceEvent.getStartDate());

        if(!mTasksTable.addNewTask(userTask))
            Toast.makeText(getActivity().getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    private void parseDate(UserTask userTask) {
        String[] dateAttributes = mRecurranceDate.split(";");
        String attrValue;

        for(int i = 0; i < dateAttributes.length; i++) {
            attrValue = dateAttributes[i].substring(dateAttributes[i].indexOf("=") + 1);
            if(dateAttributes[i].contains("FREQ"))
                userTask.setFrequency(attrValue);
            else if(dateAttributes[i].contains("COUNT"))
                userTask.setCount(Integer.parseInt(attrValue));
            else if(dateAttributes[i].contains("INTERVAL"))
                userTask.setInterval(Integer.parseInt(attrValue));
            else if(dateAttributes[i].contains("BYDAY") || dateAttributes[i].contains("UNTIL"))
                userTask.setEndDate(attrValue);
        }
    }
    private void populateDateView() {

    }

    private boolean validateTaskInfo() {
        String toastText;

        if(mTaskNameEditText.getText().length() > 2)
            if(mTimeEditText.length() > 0)
                if(mDateTextView.length() > 0)
                    return true;
                else
                    toastText = PLEASE_CHOOSE_DATE;
            else
                toastText = PLEASE_CHOOSE_TIME;
        else
            toastText = TASK_NAME_CHARACTERS;

        Toast.makeText(getActivity().getBaseContext(), toastText, Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.i("on", "focus");
        showDialog();
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
        String hourStr, minuteStr;

        minuteStr = minute < 10 ? "0" + Integer.toString(minute) : Integer.toString(minute);
        hourStr = hour < 10 ? "0" + Integer.toString(hour) : Integer.toString(hour);

        mTimeEditText.setText(hourStr + " : " + minuteStr);
    }

    public void showDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);
        timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMEPICKER_TAG);
    }

    public interface OnDismissListener {
        public void onDismiss();
    }
}
