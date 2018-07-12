package by.prostrmk.ritualServices.model.util;

import by.prostrmk.ritualServices.model.entity.Product;

public class ProductUtil {

    public static boolean validate(Product product){
        if (product!=null){
            System.out.println("product ne null");
            String country = product.getCountry();
            String material = product.getMaterial();
            String name = product.getName();
            int weight = product.getWeight();
            return country != null && material != null && name != null && weight != 0;
        }
        return false;
    }

}
