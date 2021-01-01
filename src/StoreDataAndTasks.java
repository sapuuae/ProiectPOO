import base.Tasks;
import base.ElectricConsumers;
import base.WorkingDistributors;
import com.fasterxml.jackson.databind.ObjectMapper;
import factory.CreateElectricConsumers;
import reading.Consumers;
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
        System.out.println(input.getInitialData().getDistributors());
        ArrayList<Producers> producersArrayList = input.getInitialData().getProducers();
//        ArrayList<Consumers> consumersArrayList = input.getInitialData().getConsumers();
//        ArrayList<MonthlyUpdates> monthlyUpdates = input.getMonthlyUpdates();
//        ArrayList<ElectricConsumers> consumersList = new ArrayList<>();
//        CreateElectricConsumers create = new
//                CreateElectricConsumers(consumersArrayList, monthlyUpdates);
//        create.createElectricConsumers(consumersList);
//
//        /*
//        First step of the game.
//         */
//
//        ArrayList<WorkingDistributors> initialDistributors =
//                input.getInitialData().getDistributors();
//
//        initialDistributors.sort(Comparator.comparing(WorkingDistributors::getContractCost));
//        Tasks executeTasks = Tasks.getInstance();
//        executeTasks.updateConsumer(consumersList, initialDistributors);
//        executeTasks.updateDistributor(initialDistributors);
//
//        /*
//        Iterate through numberOfTurns (size of monthlyUpdates)
//         */
//        for (MonthlyUpdates m : monthlyUpdates) {
//            executeTasks.monthlyUpdate(initialDistributors, consumersList,
//                    m.getConsumersToWork(), m.getCostsChanges(), producersArrayList,
//                    m.getProducerChanges());
//            executeTasks.updateConsumer(consumersList, initialDistributors);
//            executeTasks.updateDistributor(initialDistributors);
//        }
//
//        /*
//        Write in file.
//         */
//        WriteInJson write = new WriteInJson(consumersList, initialDistributors);
//        write.writeInFile(out);
    }
}
