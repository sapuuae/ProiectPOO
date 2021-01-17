package strategies;

import reading.Producers;

import java.util.ArrayList;

public final class ChooseProducerStrategyFactory {
    private ChooseProducerStrategyFactory() { }

    /**
     * Used to select the distributor strategy.
     * @param strategyType strategy searched by the distributor
     * @param producersArrayList the array of producers
     * @return a specific strategy (with producers' arraylist sorted)
     */
    public static ChooseProducerStrategy createStrategy(final String strategyType,
                                               final ArrayList<Producers> producersArrayList) {
        return switch (strategyType) {
            case "GREEN" -> new GreenStrategy(producersArrayList);
            case "PRICE" -> new PriceStrategy(producersArrayList);
            case "QUANTITY" -> new QuantityStrategy(producersArrayList);
            default -> throw new IllegalStateException("Unexpected value: " + strategyType);
        };

    }

}
