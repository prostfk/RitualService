package by.prostrmk.ritualServices.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String description;
    private String material;
    private String country;
    private String pathToPic;
    private int weight;

    public Product() {
    }

    public Product(String name, String description, String material, String country, String pathToPic, int weight) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.country = country;
        this.pathToPic = pathToPic;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPathToPic() {
        return pathToPic;
    }

    public void setPathToPic(String pathToPic) {
        this.pathToPic = pathToPic;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", material='" + material + '\'' +
                ", country='" + country + '\'' +
                ", weight=" + weight +
                '}';
    }
}
