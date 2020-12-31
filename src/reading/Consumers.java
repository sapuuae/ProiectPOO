package reading;

public class Consumers {
    private Integer id;
    private Double initialBudget;
    private Integer monthlyIncome;

    public final Integer getId() {
        return id;
    }

    public final Double getInitialBudget() {
        return initialBudget;
    }

    public final Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setInitialBudget(final Double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final void setMonthlyIncome(final Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
