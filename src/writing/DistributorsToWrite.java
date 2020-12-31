package writing;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
@JsonPropertyOrder({ "id", "budget", "isBankrupt", "contracts" })
public final class DistributorsToWrite {
    private final Integer id;
    private final Integer budget;
    private final Boolean isBankrupt;
    private final ArrayList<ConsumersToWriteInDistributors> contracts;

    public DistributorsToWrite(final Integer id, final Integer budget, final Boolean isBankrupt,
                               final ArrayList<ConsumersToWriteInDistributors> contracts) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBudget() {
        return budget;
    }

    public Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public ArrayList<ConsumersToWriteInDistributors> getContracts() {
        return contracts;
    }
}
