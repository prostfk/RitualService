package by.prostrmk.ritualServices.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TypeOfProduct {
    Памятник,
    Ограда,
    ГраверныеРаботы;



    TypeOfProduct() {
    }

    public static List<TypeOfProduct> getAllTypes(){
        return new ArrayList<>(Arrays.asList(TypeOfProduct.ГраверныеРаботы, TypeOfProduct.Ограда,Памятник));
    }


    public static TypeOfProduct stringToEnum(String value){
        switch (value) {
            case "Памятник":
                return TypeOfProduct.Памятник;
            case "Ограда":
                return TypeOfProduct.Ограда;
            case "ГраверныеРаботы":
                return TypeOfProduct.ГраверныеРаботы;
            default:
                return null;
        }
    }


}
