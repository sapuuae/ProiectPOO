package writing;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
@JsonPropertyOrder({ "id", "energyNeededKW", "contractCost",
        "budget", "producerStrategy", "isBankrupt", "contracts" })
public final class DistributorsToWrite {
    private final Integer id;
    private final Integer energyNeededKW;
    private final Integer contractCost;
    private final Integer budget;
    private final String producerStrategy;
    private final Boolean isBankrupt;
    private final ArrayList<ConsumersToWriteInDistributors> contracts;

    public DistributorsToWrite(Integer id, Integer energyNeededKW, Integer contractCost, Integer budget, String producerStrategy, Boolean isBankrupt, ArrayList<ConsumersToWriteInDistributors> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public Integer getContractCost() {
        return contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
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
