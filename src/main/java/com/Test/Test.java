package com.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.schemaReader.dal.CodeGroupType;
import com.schemaReader.dal.internal.Code;
import com.schemaReader.dal.internal.CodeGroup;
import com.schemaReader.dal.internal.DataStore;
import com.schemaReader.dal.internal.Field;
import com.schemaReader.dal.internal.ItemType;
import com.schemaReader.dal.internal.Schema;
import com.schemaReader.dal.internal.SchemaReader;




public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("Finished" + CodeGroupType.CODE_LIST.getId());
		
		System.out.println("Done" + CodeGroupType.CASE.getId());
		
		EnumSet.allOf(CodeGroupType.class)
		  .forEach(codeGroup -> System.out.println(codeGroup));
		
		Stream.of(CodeGroupType.values()).forEach(codeGroup -> System.out.println(codeGroup));
		
		
		// Auto closable
		try (PrintWriter writer = new PrintWriter(new File("c:/development/test.txt"))) {
			CodeGroupType.stream().forEach(codeGroup -> writer.println(codeGroup));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("iBase");
		
		EntityManager entityManager = emf.createEntityManager();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		

	 //   CriteriaQuery<Field> dsCriteriaQuery = criteriaBuilder.createQuery(Field.class);
	//    Root<Field> dsRoot = dsCriteriaQuery.from(Field.class);
	    
	//    dsCriteriaQuery.select(dsRoot);
	    
	     
	     
	     SchemaReader schemaReader = new SchemaReader();
	     
	     // Using Comparable to do sorting
	     
	     schemaReader.readTable(CodeGroup.class,entityManager,criteriaBuilder)
	     .stream()
	     .sorted()
	     .forEach(group -> System.out.println(group.getDescription()));
	     
	  //   schemaReader.readTable(ItemType.class,entityManager,criteriaBuilder);
	     
	     schemaReader.readTable(ItemType.class,entityManager,criteriaBuilder)
	     .stream()
	     .sorted()
	   .forEach(type -> System.out.println(type.getLogicalName()));
	     
	 //    schemaReader.readTable(CodeGroup.class,entityManager,criteriaBuilder).forEach(group -> System.out.println(group.getDescription()));
	     
	  //   schemaReader.readTable(CodeGroup.class,entityManager,criteriaBuilder).sort(Comparator.comparing(o -> o.));
	  //   
	   //  CriteriaBuilder cb  = new CriteriaBuilder.equal(dsRoot.get("name"), "i2[CATALOG]");
	     
	   //  dsCriteriaQuery.select(dsRoot).where(criteriaBuilder.equal(dsRoot.get("name"), "i2[CATALOG]"));
	     
	    List<String> dsList = entityManager
	    		.createQuery("SELECT data FROM DataStore WHERE name = 'i2[CATALOG]'")
	    		.getResultList();
	    
	    dsList.forEach( item -> System.out.println(item));
	    
	       
	     //  CriteriaQuery<Field> dsCriteriaQuery = criteriaBuilder.createQuery(Field.class);
	 	//    Root<Field> dsRoot = dsCriteriaQuery.from(Field.class);
	 	    
	 	//    dsCriteriaQuery.select(dsRoot);
	 	    
	     
	  //   Query q1 = em.createQuery
	    	//	 createQuery(qlString)
	    	//	 EntityManager's method
	    	//	 Create an instance of Query for executing a Java Persistence query language statement.
	    		// See JavaDoc Reference Page...
	    		// ("SELECT c FROM Country c");
	     
	  //   schemaReader.readTable(DataStore.class,entityManager,criteriaBuilder).forEach(dataStore ->  System.out.println(dataStore.getData()));
	     
	    // schemaReader.readTable(Field.class,entityManager,criteriaBuilder).forEach(field -> System.out.println(field.getId()));
	     
	   //  schemaReader.readTable(Code.class,entityManager,criteriaBuilder).forEach(code ->  System.out.println(code.getExpansion()));
	    
	     Schema schema = new Schema(entityManager);
	     
	     System.out.println();
	     
	     schema.getEntityTypes()
	     .stream()
	     .sorted()
	     .forEach(entityType -> System.out.println(entityType.getLogicalName()));
	     
	     List<Object[]> list =entityManager.createNativeQuery("SELECT  CAST(First_Name AS VARCHAR) a, CAST(Last_Name AS VARCHAR) b FROM Person_")
	    		.getResultList();
	     
	 //    list.forEach( item -> System.out.println(item[1]));
	     
	     //.forEach(result[] -> System.out.println(result[0]));
	    //  System.out.println(entityManager.createNativeQuery("Select First_Name from Person_").getSingleResult()); 
	      
	   
   
	     
	     entityManager.close();
	     emf.close();
	     
	}
}




		
		
	//	Configuration configuration = new Configuration().configure(Test.class.getResource("/hibernate.cfg.xml"));
		
		// SessionFactory sessionFactory = configuration.buildSessionFactory();
			
		    // Auto closable
		 /*
			try (Session session = sessionFactory.openSession()) 
			{
			
				 criteriaBuilder = session.getCriteriaBuilder();
		
		   
			    CriteriaQuery<Field> dsCriteriaQuery = criteriaBuilder.createQuery(Field.class);
			    Root<Field> dsRoot = dsCriteriaQuery.from(Field.class);
			    
			    dsCriteriaQuery.select(dsRoot);
			    
			    session.createQuery(dsCriteriaQuery).getResultList().forEach(field -> System.out.println(field.getId()));
			}
	    
           */
	     
	    


