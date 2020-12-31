package writing;

import base.ElectricConsumers;
import base.WorkingDistributors;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public final class WriteInJson {
    private final ArrayList<ElectricConsumers> electricConsumers;
    private final ArrayList<WorkingDistributors> workingDistributors;

    public WriteInJson(final ArrayList<ElectricConsumers> electricConsumers,
                       final ArrayList<WorkingDistributors> workingDistributors) {
        this.electricConsumers = electricConsumers;
        this.workingDistributors = workingDistributors;
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
            Integer number = Math.toIntExact(Math.round(d.getInitialBudget()));
            ArrayList<ElectricConsumers> distributorsConsumers = d.getDistributorConsumers();
            ArrayList<ConsumersToWriteInDistributors> toWrite = new ArrayList<>();
            for (ElectricConsumers e : distributorsConsumers) {
                Integer priceInt = Math.toIntExact(Math.round(e.getPrice()));
                toWrite.add(new ConsumersToWriteInDistributors(e.getId(),
                        priceInt, e.getRemainedContractMonths()));
            }
            DistributorsToWrite toAdd = new DistributorsToWrite(d.getId(),
                    number, d.getBankrupt(),
                    toWrite);
            distributors.add(toAdd);
        }
        ArrayList<ConsumersToWrite> consumersToWrite = new ArrayList<>();
        for (ElectricConsumers c : electricConsumers) {
            Integer budget = Math.toIntExact(Math.round(c.getInitialBudget()));
            consumersToWrite.add(new ConsumersToWrite(c.getId(), c.getBankrupt(), budget));
        }

        WrapperJson wrapper = new WrapperJson(consumersToWrite, distributors);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(out, wrapper);
    }

}
