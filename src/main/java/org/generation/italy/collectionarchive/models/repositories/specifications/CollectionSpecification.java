package org.generation.italy.collectionarchive.models.repositories.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.springframework.data.jpa.domain.Specification;


public class CollectionSpecification {

    public static Specification<Collection>hasNameLike(String collectionName){
        return ( root, query, builder) ->{
            if(collectionName.isEmpty() || collectionName == null){
                return builder.conjunction();
            }
            return builder.like(root.get("collectionName").as(String.class),
            "%" + collectionName.toLowerCase() + "%");
        };
    }

    public static Specification<Collection> hasCategoryName (Integer categoryId){
        return (root, query, builder) ->{
            if(categoryId == null){
                return builder.conjunction();
            }
            Join<Collection, Category> categoryJoin = root.join("category");
            return builder.equal((categoryJoin.get("categoryId")), categoryId);
        };
    }
}
