package supermarket;

import java.time.LocalDateTime;

public class Activity {

    private int activityId;
    private String activityName;
    private int activityQuantity;
    private LocalDateTime activityDate;

    public Activity(int activityId, String activityName, int activityQuantity, LocalDateTime activityDate) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityQuantity = activityQuantity;
        this.activityDate = activityDate;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getActivityQuantity() {
        return activityQuantity;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + activityId +
                ", name='" + activityName + '\'' +
                ", quantity=" + activityQuantity +
                ", date=" + activityDate +
                '}';
    }
}

