package writing;

import base.ElectricConsumers;
import base.WorkingDistributors;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import reading.DistributorStats;
import reading.Producers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public final class WriteInJson {
    private final ArrayList<ElectricConsumers> electricConsumers;
    private final ArrayList<WorkingDistributors> workingDistributors;
    private final ArrayList<Producers> producersArrayList;

    public WriteInJson(final ArrayList<ElectricConsumers> electricConsumers,
                       final ArrayList<WorkingDistributors> workingDistributors,
                       final ArrayList<Producers> producersArrayList) {
        this.electricConsumers = electricConsumers;
        this.workingDistributors = workingDistributors;
        this.producersArrayList = producersArrayList;
    }

    /**
     * Create objects to write in the final file:
     * DistributorsToWrite and ConsumersToWrite.
     * @param out used to write data to
     * @throws IOException throws an error if out isn't correct
     */
    public void writeInFile(final File out) throws IOException {
        workingDistributors.sort(Comparator.comparing(WorkingDistributors::getId));
        ArrayList<DistributorsToWrite> distributors = new ArrayList<>();
        for (WorkingDistributors d : workingDistributors) {
            Integer finalBudget = Math.toIntExact(Math.round(d.getInitialBudget()));
            Integer finalContractCost = Math.toIntExact(Math.round(d.getContractCost()));
            ArrayList<ElectricConsumers> distributorsConsumers = d.getDistributorConsumers();
            ArrayList<ConsumersToWriteInDistributors> toWrite = new ArrayList<>();
            for (ElectricConsumers e : distributorsConsumers) {
                Integer priceInt = Math.toIntExact(Math.round(e.getPrice()));
                toWrite.add(new ConsumersToWriteInDistributors(e.getId(),
                        priceInt, e.getRemainedContractMonths()));
            }
            DistributorsToWrite toAdd = new DistributorsToWrite(d.getId(), d.getEnergyNeededKW(),
                    finalContractCost, finalBudget, d.getProducerStrategy().label, d.getBankrupt(),
                    toWrite);
            distributors.add(toAdd);
        }
        ArrayList<ConsumersToWrite> consumersToWrite = new ArrayList<>();
        for (ElectricConsumers c : electricConsumers) {
            Integer budget = Math.toIntExact(Math.round(c.getInitialBudget()));
            consumersToWrite.add(new ConsumersToWrite(c.getId(), c.getBankrupt(), budget));
        }

        ArrayList<ProducersToWrite> producersToWrite = new ArrayList<>();
        for (Producers p : producersArrayList) {
            ArrayList<DistributorStats> statsToAdd = new ArrayList<>();
            for (int i = 1; i < p.getMonthlyStats().size(); i++) {
                statsToAdd.add(p.getMonthlyStats().get(i));
            }
            producersToWrite.add(new ProducersToWrite(p.getId(), p.getMaxDistributors(),
                    p.getPriceKW(), p.getEnergyType().getLabel(), p.getEnergyPerDistributor(),
                    statsToAdd));
        }

        WrapperJson wrapper = new WrapperJson(consumersToWrite, distributors, producersToWrite);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(out, wrapper);
    }

}
