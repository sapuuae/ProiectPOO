package reading;

import java.util.ArrayList;

public final class DistributorStats {
    private final int month;
    private final ArrayList<Integer> distributorsIds = new ArrayList<>();

    public DistributorStats(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }
}
