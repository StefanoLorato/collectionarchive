package org.generation.italy.collectionarchive.models.repositories.specifications;

import jakarta.persistence.criteria.Join;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    public static Specification<Item> hasNameLike(String itemName){
        return (root, query, builder)->{
            if(itemName.isEmpty() || itemName == null){
                return builder.conjunction();
            }
            return builder.like(root.get("itemName").as(String.class),
                    "%" + itemName.toLowerCase() + "%");
        };
    }
    public static Specification<Item> hasUserId (Integer userId){
        return(root, query, builder) ->{
            if(userId == null){
                return builder.conjunction();
            }
            Join<Item, User> userJoin = root.join("user");
            return builder.equal(userJoin.get("userId"), userId);
        };
    }

    public static Specification<Item> hasSalePrice(Double salePrice, String priceComparation){
        return(root, query, builder) ->{
            if (salePrice == null || priceComparation == null){
                return builder.conjunction();
            }
            return switch (priceComparation.toLowerCase()) {
                case "equal" -> builder.equal(root.get("salePrice"), salePrice);
                case "greaterthan" -> builder.greaterThan(root.get("salePrice"), salePrice);
                case "lessthan" -> builder.lessThan(root.get("salePrice"), salePrice);
                default -> builder.conjunction();
            };
        };
    }

    public static Specification<Item> hasForSale(Boolean forSale) {
        return (root, query, builder) -> {
            if (forSale == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("forSale"), forSale);
        };
    }


}