package in.mkp.CompletableFuture;

import static in.mkp.CompletableFuture.Currency.USD;

public class UsingThreads {

    private final Catalogue catalogue = new Catalogue();
    private final PriceFinder priceFinder = new PriceFinder();
    private final ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args) throws InterruptedException {
        new UsingThreads().findLocalDiscountedPrice(Currency.CHF, "Nexus7");
    }

    private void findLocalDiscountedPrice(final Currency localCurrency, final String productName) throws InterruptedException {
        long time = System.currentTimeMillis();

        ProductRunnable productRunnable = new ProductRunnable(productName);

        ExchangeRunnable exchangeRunnable = new ExchangeRunnable(localCurrency);

        Thread productThread = new Thread(productRunnable);
        productThread.start();;

        Thread exchangeThread = new Thread(exchangeRunnable);
        exchangeThread.start();

        productThread.join();
        exchangeThread.join();

        double localPrice = exchange(productRunnable.getPrice(), exchangeRunnable.getResult());

        System.out.printf("A %s will cost us %f %s\n", productName, localPrice, localCurrency);
        System.out.printf("It took us %d ms to calculate this\n", System.currentTimeMillis() - time);
    }

    private double exchange(Price price, double exchangeRate) {
        return Utils.round(price.getAmount() * exchangeRate);
    }

    private class ProductRunnable implements Runnable {

        private String productName;
        private Price price;

        public ProductRunnable(final String productName) {
            this.productName = productName;
        }

        @Override
        public void run() {
            final Product product = catalogue.productByName(productName);
            final Price price = priceFinder.findBestPrice(product);
            this.price = price;
        }

        public Price getPrice() {
            return this.price;
        }
    }

    private class ExchangeRunnable implements Runnable {

        private Currency localCurrency;
        private double result;

        public ExchangeRunnable(final Currency localCurrency) {
            this.localCurrency = localCurrency;
        }

        public void run() {
            double result = exchangeService.lookupExchangeRate(USD, localCurrency);
            this.result = result;
        }

        public double getResult() {
            return this.result;
        }
    }

}
