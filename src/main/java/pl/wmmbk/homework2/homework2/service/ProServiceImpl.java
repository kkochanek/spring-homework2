package pl.wmmbk.homework2.homework2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wmmbk.homework2.homework2.model.Item;
import pl.wmmbk.homework2.homework2.repository.ItemRepository;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

@Service
@Profile("pro")
public class ProServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ProServiceImpl.class);

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    @Autowired
    private ItemRepository repository;

    @Value("${homework2.discount.value:}")
    private Double discountValue;

    @Value("${homework2.tax.value:}")
    private Double taxValue;

    @Override
    public void findAll() {
        List<Item> items = repository.findAll();

        for (Item item : items) {
            logger.info(item.toString());
        }
    }

    @Override
    public long countItems() {
        return repository.count();
    }

    @Override
    public void addItem(Item item) {
        repository.save(item);

    }

    @Override
    public BigDecimal sumUpPrices() {

        BigDecimal total = BigDecimal.ZERO;

        List<Item> items = repository.findAll();

        for (Item item : items) {
            total = total.add(item.getPrice());
        }

        BigDecimal taxPercent = new BigDecimal(taxValue);
        taxPercent = taxPercent.divide(BigDecimal.valueOf(100));

        BigDecimal taxTotal = total.multiply(taxPercent);

        BigDecimal totalAfterTax = total.add(taxTotal);

        BigDecimal discountPercent = new BigDecimal(discountValue);
        discountPercent = discountPercent.divide(BigDecimal.valueOf(100));

        BigDecimal discountTotal = totalAfterTax.multiply(discountPercent);
        BigDecimal result = totalAfterTax.subtract(discountTotal);

        logger.info("After discount: " + currency.format(result) + " = " + currency.format(total) + " (prices) + " + currency.format(taxTotal) + " (taxes) - " + currency.format(discountTotal) + " (discount)");

        return result;

    }
}
