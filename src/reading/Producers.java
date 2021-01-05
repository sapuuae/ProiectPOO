package reading;

import base.WorkingDistributors;
import entities.EnergyType;
import observer.Observer;

import java.util.ArrayList;

public class Producers extends Observer{
    private Integer id;
    private EnergyType energyType;
    private Integer maxDistributors;
    private Double priceKW;
    private Integer energyPerDistributor;
    private Integer actualDistributors = 0;
    private final ArrayList<DistributorStats> monthlyStats = new ArrayList<>();

    public ArrayList<DistributorStats> getMonthlyStats() {
        return monthlyStats;
    }

    public Integer getActualDistributors() {
        return actualDistributors;
    }

    public void setActualDistributors(Integer actualDistributors) {
        this.actualDistributors = actualDistributors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public Integer getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(Integer maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public Double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(Double priceKW) {
        this.priceKW = priceKW;
    }

    public Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    @Override
    public void update(Integer energy, ArrayList<WorkingDistributors> distributorsArrayList) {
        this.energyPerDistributor = energy;
        for (DistributorStats s : monthlyStats) {
            ArrayList<Integer> idsArray = s.getDistributorsIds();
            for (Integer i : idsArray) {
                distributorsArrayList.get(i).setNeedToUpdate(true);
            }
        }
        actualDistributors = 0;
    }
}
