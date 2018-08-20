package in.mkp.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static in.mkp.CompletableFuture.Currency.USD;

public class UsingRudimentaryCompletableFutures {
    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args)  {
        new UsingRudimentaryCompletableFutures().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName)  {
        long time = System.currentTimeMillis();

        CompletableFuture<Product> productCompletableFuture = CompletableFuture.supplyAsync(() -> catalogue.productByName(productName), executorService);
        CompletableFuture<Price> priceCompletableFuture = CompletableFuture.supplyAsync(() -> priceFinder.findBestPrice(productCompletableFuture.join()), executorService);
        CompletableFuture<Double> exchangeCompletableFuture = CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(USD, localCurrency), executorService);

        double localPrice = exchange(priceCompletableFuture.join(), exchangeCompletableFuture.join());

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
        this.executorService.shutdown();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

}
