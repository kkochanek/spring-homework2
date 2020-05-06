package pl.wmmbk.homework2.homework2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.wmmbk.homework2.homework2.model.Item;
import pl.wmmbk.homework2.homework2.service.ShopService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Homework2Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Homework2Application.class);
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final double lower = 50.0;
    private static final double upper = 300.0;

    @Autowired
    private ShopService service;

    public static void main(String[] args) {
        SpringApplication.run(Homework2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All items in SHOP: " + service.countItems() + "; prices: " + service.sumUpPrices());

        Item item_1 = new Item();
        item_1.setName("First");
        item_1.setPrice(randomPrice());

        Item item_2 = new Item();
        item_2.setName("Second");
        item_2.setPrice(randomPrice());

        Item item_3 = new Item();
        item_3.setName("Third");
        item_3.setPrice(randomPrice());

        Item item_4 = new Item();
        item_4.setName("Fourth");
        item_4.setPrice(randomPrice());

        Item item_5 = new Item();
        item_5.setName("Fifth");
        item_5.setPrice(randomPrice());

        service.addItem(item_1);
        service.addItem(item_2);
        service.addItem(item_3);
        service.addItem(item_4);
        service.addItem(item_5);

        service.findAll();
        logger.info("All items in SHOP: " + service.countItems() + "; prices: " + currency.format(service.sumUpPrices()));

    }

    private BigDecimal randomPrice() {
        double randomDouble = ThreadLocalRandom.current().nextDouble(lower, upper);

        return new BigDecimal(randomDouble).setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
