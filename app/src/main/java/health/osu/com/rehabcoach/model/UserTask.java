package health.osu.com.rehabcoach.model;

/**
 * Created by brainfreak on 10/23/14.
 */
public class UserTask {
    String mName, mDescription, mEndDate, mDate, mTime, mExtras, mFrequency;
    Integer mId, mInterval, mCount;
    boolean mPriority, mAlive;

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setExtras(String extras) {
        mExtras = extras;
    }

    public void setFrequency(String frequency) {
        mFrequency = frequency;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public void setInterval(Integer interval) {
        mInterval = interval;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public void setPriority(boolean priority) {
        mPriority = priority;
    }

    public void setAlive(boolean alive) {
        mAlive = alive;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public String getExtras() {
        return mExtras;
    }

    public String getFrequency() {
        return mFrequency;
    }

    public Integer getId() {
        return mId;
    }

    public Integer getInterval() {
        return mInterval;
    }

    public Integer getCount() {
        return mCount;
    }

    public boolean isAlive() {
        return mAlive;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
    }

    public boolean isHighPriority() {
        return mPriority;
    }
}
