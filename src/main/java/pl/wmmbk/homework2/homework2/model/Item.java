package pl.wmmbk.homework2.homework2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    @Override
    public String toString() {


        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(currency.format(price));
        sb.append('}');
        return sb.toString();

    }
}
