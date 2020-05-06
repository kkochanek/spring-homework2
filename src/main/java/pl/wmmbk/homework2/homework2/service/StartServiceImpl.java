package pl.wmmbk.homework2.homework2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wmmbk.homework2.homework2.model.Item;
import pl.wmmbk.homework2.homework2.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("default")
public class StartServiceImpl implements ShopService {

    private static Logger logger = LoggerFactory.getLogger(StartServiceImpl.class);

    @Autowired
    private ItemRepository repository;

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

        BigDecimal prices = BigDecimal.ZERO;

        List<Item> items = repository.findAll();

        for (Item item : items) {
            prices = prices.add(item.getPrice());
        }

        return prices;
    }
}
