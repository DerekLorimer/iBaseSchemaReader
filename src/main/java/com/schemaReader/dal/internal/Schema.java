package com.schemaReader.dal.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;


public class Schema {
	
	private Map<Integer,CodeGroup> codeGroupsById;;
	
		
	public Schema(EntityManager entityManager)  {
		
		SchemaReader schemaReader = new SchemaReader();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		codeGroupsById = new HashMap<Integer,CodeGroup>();
		
		schemaReader.readTable(CodeGroup.class,entityManager,criteriaBuilder)
	     .forEach(codeGroup -> codeGroupsById.put(codeGroup.getId(), codeGroup));
		
		
		 Map<Integer, ItemType> mapTables = new HashMap<Integer,ItemType>();
	     
	     List<ItemType> itemTypes = schemaReader.readTable(ItemType.class,entityManager,criteriaBuilder);
	     
	     itemTypes
	     .stream()
	     .sorted()
	     .forEach(itemType -> {
	        	System.out.println(itemType.getLogicalName());
	        	mapTables.put(itemType.getId(), itemType);
	        });
	     
	     schemaReader.readTable(Field.class, entityManager, criteriaBuilder)
	     .forEach(field -> {
	    	 System.out.println(field.getLogicalName());
	    	
	    	 System.out.println(field.getCodeGroupId());
	    	 
	    	  
		 	//    if ( codeGroupsById.containsKey(field.g)) {
		 	    
			 	 //   CodeGroup codeGroup = codeGroupsById.get(field.getCodeGroupId());
			 	       
			 	  //  field.setCodeGroup(codeGroup);
			 	  // 
			 	  //  log.debug("CODEGROUPID=" + field.getCodeGroup().getId());
		 	    
		 	  //  }
	     });

	     
	    
		}
	
}