package reading;

import java.util.ArrayList;

public final class Input {
    private Integer numberOfTurns;
    private InitialData initialData;
    private ArrayList<MonthlyUpdates> monthlyUpdates;

    public Integer getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final Integer numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<MonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
