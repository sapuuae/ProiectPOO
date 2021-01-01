package writing;

import reading.DistributorStats;

import java.util.ArrayList;

public final class ProducersToWrite {
    private final Integer id;
    private final Integer maxDistributors;
    private final Double priceKW;
    private final String energyType;
    private final Integer energyPerDistributor;
    private final ArrayList<DistributorStats> monthlyStats;

    public ProducersToWrite(Integer id, Integer maxDistributors, Double priceKW,
                            String energyType, Integer energyPerDistributor,
                            final ArrayList<DistributorStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMaxDistributors() {
        return maxDistributors;
    }

    public Double getPriceKW() {
        return priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public ArrayList<DistributorStats> getMonthlyStats() {
        return monthlyStats;
    }
}
