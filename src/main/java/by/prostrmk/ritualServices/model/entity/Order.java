package by.prostrmk.ritualServices.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private String id;
    private User user;
    private Product product;

    public Order() {
    }

    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(user, order.user) &&
                Objects.equals(product, order.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
