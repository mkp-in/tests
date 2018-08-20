package in.mkp.CompletableFuture;

import static in.mkp.CompletableFuture.Currency.USD;

public class Sequential {

    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args) throws InterruptedException {
        new Sequential().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName) throws InterruptedException {
        long time = System.currentTimeMillis();

        Product product = catalogue.productByName(productName);
        Price price = priceFinder.findBestPrice(product);

        double result = exchangeService.lookupExchangeRate(USD, localCurrency);

        double localPrice = exchange(price, result);

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
    }

    private double exchange(Price price, double exchangeRate)
    {
        return Utils.round(price.getAmount() * exchangeRate);
    }

}
