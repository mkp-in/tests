package in.mkp.CompletableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static in.mkp.CompletableFuture.Currency.USD;

public class UsingFutures {

    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new UsingFutures().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName) throws InterruptedException, ExecutionException {
        long time = System.currentTimeMillis();

        Future<Product> productFuture = this.executorService.submit(() -> catalogue.productByName(productName));
        Future<Price> priceFuture = this.executorService.submit(() -> priceFinder.findBestPrice(productFuture.get()));

        Future<Double> exchangEfuture = this.executorService.submit(() -> exchangeService.lookupExchangeRate(USD, localCurrency));


        double localPrice = exchange(priceFuture.get(), exchangEfuture.get());

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);

        this.executorService.shutdown();
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

}
