package org.generation.italy.collectionarchive.models.repositories.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.springframework.data.jpa.domain.Specification;


public class CollectionSpecification {

    public static Specification<Collection>hasNameLike(String collectionName){
        return ( root, query, builder) ->{
            if(collectionName == null){
                return builder.conjunction();
            }
            return builder.like(root.get("collectionName").as(String.class),
                    "%" + collectionName.toLowerCase() + "%");
        };
    }

    public static Specification<Collection> hasCategoryId (Integer categoryId){
        return (root, query, builder) ->{
            if(categoryId == null){
                return builder.conjunction();
            }
            Join<Collection, Category> categoryJoin = root.join("category");
            return builder.equal((categoryJoin.get("categoryId")), categoryId);
        };
    }
    public static Specification<Collection> hasUserId(Integer userId){
        return(root, query, builder)-> {
            if(userId == null){
                return builder.conjunction();
            }
            Join<Collection, User> userJoin = root.join("user");
            return builder.equal((userJoin.get("userId")), userId);
        };
    }
    public static Specification<Collection> hasSalePrice(Double salePrice, String priceComparation){
        return (root, query, builder) -> {
            if(salePrice == null || priceComparation == null){
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
}