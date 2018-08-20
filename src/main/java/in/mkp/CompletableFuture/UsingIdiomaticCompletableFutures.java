package in.mkp.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static in.mkp.CompletableFuture.Currency.USD;

public class UsingIdiomaticCompletableFutures {
    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args)  {
        new UsingIdiomaticCompletableFutures().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName)  {
        long time = System.currentTimeMillis();

        lookupProduct(productName)
                .thenCompose(this::lookupPrice)
                .thenCombineAsync(lookupExchangeRate(localCurrency), this::exchange)
                .thenAcceptAsync(result -> {
                    System.out.printf("A %s will cost us %f %s\n", productName, result, localCurrency);
                    System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
                }, executorService).join();

        this.executorService.shutdown();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

    private CompletableFuture<Product> lookupProduct(final String productName) {
        return CompletableFuture.supplyAsync(() -> catalogue.productByName(productName), this.executorService);
    }

    private CompletableFuture<Price> lookupPrice(final Product product) {
        return CompletableFuture.supplyAsync(() -> priceFinder.findBestPrice(product), this.executorService);
    }

    private CompletableFuture<Double> lookupExchangeRate(final Currency localCurrency) {
        return CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(USD, localCurrency), this.executorService);
    }

}
