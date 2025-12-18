package supermarket;

public class ActivityQueue {

    private Activity[] activities;
    private int size;

    public ActivityQueue() {
        activities = new Activity[4];
        size = 0;
    }

    public void enqueue(Activity activity) {
        if (size < 4) {
            activities[size] = activity;
            size++;
        } else {
            for (int i = 1; i < 4; i++) {
                activities[i - 1] = activities[i];
            }
            activities[3] = activity;
        }
    }

    public Activity[] getAll() {
        Activity[] result = new Activity[size];
        for (int i = 0; i < size; i++) {
            result[i] = activities[i];
        }
        return result;
    }

    public int getSize() {
        return size;
    }
}

