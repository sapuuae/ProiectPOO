package strategies;

import base.WorkingDistributors;
import reading.Producers;

import java.util.ArrayList;

public class ChooseProducerStrategyFactory {
    public static ChooseProducerStrategy createStrategy
            (final String strategyType, final ArrayList<Producers> producersArrayList,
             final ArrayList<WorkingDistributors> distributorsArrayList) {
        return switch (strategyType) {
            case "GREEN" -> new GreenStrategy(producersArrayList, distributorsArrayList);
            case "PRICE" -> new PriceStrategy(producersArrayList, distributorsArrayList);
            case "QUANTITY" -> new QuantityStrategy(producersArrayList, distributorsArrayList);
            default -> throw new IllegalStateException("Unexpected value: " + strategyType);
        };

    }

}
