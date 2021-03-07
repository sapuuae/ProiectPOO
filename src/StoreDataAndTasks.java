import base.ProducersTasks;
import base.Tasks;
import base.ElectricConsumers;
import base.WorkingDistributors;
import com.fasterxml.jackson.databind.ObjectMapper;
import factory.CreateElectricConsumers;
import reading.Consumers;
import reading.DistributorStats;
import reading.Input;
import reading.MonthlyUpdates;
import reading.Producers;
import writing.WriteInJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public final class StoreDataAndTasks {

    /**
     * Read the data from the file, write it in an Input object.
     * Then, run a initial turn for the consumers/distributors, and
     * numberOfTurns times after that.
     * @param in  file to read data from
     * @param out used to write the final data to
     * @throws IOException throws an error if in / out aren't correct
     */
    public void checkFile(final File in, final File out) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input =  (objectMapper.readValue(in, Input.class));

        ArrayList<Producers> producersArrayList = input.getInitialData().getProducers();
        ArrayList<Consumers> consumersArrayList = input.getInitialData().getConsumers();
        ArrayList<MonthlyUpdates> monthlyUpdates = input.getMonthlyUpdates();
        ArrayList<ElectricConsumers> consumersList = new ArrayList<>();
        CreateElectricConsumers create = new
                CreateElectricConsumers(consumersArrayList, monthlyUpdates);
        create.createElectricConsumers(consumersList);


        for (int i = 0; i <= input.getNumberOfTurns(); i++) {

            for (Producers p : producersArrayList) {
                p.getMonthlyStats().add(new DistributorStats(i));
            }
        }
        /*
        First step of the game.
         */

        ArrayList<WorkingDistributors> initialDistributors =
                input.getInitialData().getDistributors();
        ProducersTasks tasks = new ProducersTasks();
        int count = 0;
        tasks.chooseProducers(initialDistributors, producersArrayList, count++);
        initialDistributors.sort(Comparator.comparing(WorkingDistributors::getContractCost));
        Tasks executeTasks = Tasks.getInstance();
        executeTasks.updateConsumer(consumersList, initialDistributors);
        executeTasks.updateDistributor(initialDistributors);

        /*
        Iterate through numberOfTurns (size of monthlyUpdates)
         */
        for (MonthlyUpdates m : monthlyUpdates) {
            executeTasks.monthlyUpdate(initialDistributors, consumersList,
                    m.getConsumersToWork(), m.getDistributorChanges());
            executeTasks.updateConsumer(consumersList, initialDistributors);
            executeTasks.updateDistributor(initialDistributors);
            tasks.updateProducers(producersArrayList, m.getProducerChanges(),
                    initialDistributors, count);
            tasks.chooseProducers(initialDistributors, producersArrayList, count++);
        }

        /*
        Write in file.
         */
        WriteInJson write = new WriteInJson(consumersList, initialDistributors, producersArrayList);
        write.writeInFile(out);
    }
}
