package strategies;

import reading.Producers;

import java.util.ArrayList;

public class ChooseProducerStrategyFactory {
    public static ChooseProducerStrategy createStrategy
            (final String strategyType, final ArrayList<Producers> producersArrayList) {
        return switch (strategyType) {
            case "GREEN" -> new GreenStrategy(producersArrayList);
            case "PRICE" -> new PriceStrategy(producersArrayList);
            case "QUANTITY" -> new QuantityStrategy(producersArrayList);
            default -> throw new IllegalStateException("Unexpected value: " + strategyType);
        };

    }

}
