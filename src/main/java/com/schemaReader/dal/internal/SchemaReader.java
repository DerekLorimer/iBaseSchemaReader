package com.schemaReader.dal.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SchemaReader {
	
	// Using Generics to read a table
	
	 public <T> List<T> readTable(Class<T> table,
             EntityManager entityManager,
             CriteriaBuilder criteriaBuilder) {

		CriteriaQuery<T> critieraQuery = criteriaBuilder.createQuery(table);
		
		Root<T> root = critieraQuery.from(table);
		
		critieraQuery.select(root);
		
		return entityManager.createQuery(critieraQuery).getResultList();
	}
	
}