package com.schemaReader.dal.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import com.schemaReader.dal.IEntityType;
import com.schemaReader.dal.ILinkType;
import com.schemaReader.dal.ISchema;






public class Schema implements ISchema {
	
	private Map<Integer,CodeGroup> codeGroupsById;;
	private  List<IEntityType> entityTypes;
	private  List<ILinkType> linkTypes;
	
		
	public Schema(EntityManager entityManager)  {
		
		codeGroupsById = new HashMap<Integer,CodeGroup>();
		entityTypes = new ArrayList<IEntityType>();
		linkTypes   = new ArrayList<ILinkType>();
		
		SchemaReader schemaReader = new SchemaReader();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		
		
		schemaReader.readTable(CodeGroup.class,entityManager,criteriaBuilder)
	     .forEach(codeGroup -> codeGroupsById.put(codeGroup.getId(), codeGroup));
		
		
		 Map<Integer, ItemType> mapTables = new HashMap<Integer,ItemType>();
	     
	     List<ItemType> itemTypes = schemaReader.readTable(ItemType.class,entityManager,criteriaBuilder);
	     
	     itemTypes
	     .stream()
	     .sorted()
	     .forEach(itemType -> {
	        	mapTables.put(itemType.getId(), itemType);
	        });
	     
	     schemaReader.readTable(Field.class, entityManager, criteriaBuilder)
	     .forEach(field -> {
	    	
	    	 
	    	 Integer codeGroupId = field.getCodeGroupId(); 
	    	  
		 	 if ( codeGroupsById.containsKey(codeGroupId)) {
		 		 
		 		
		 		field.setCodeGroup(codeGroupsById.get(codeGroupId));
		 	 }
		 	 
		 	ItemType itemType = mapTables.get(field.getTableId());
		 	
		 	if ( itemType != null ) {
		 		itemType.addAllField(field);
		 	}
		 	    
			 	 //   CodeGroup codeGroup = codeGroupsById.get(field.getCodeGroupId());
			 	       
			 	  //  field.setCodeGroup(codeGroup);
			 	  // 
			 	  //  log.debug("CODEGROUPID=" + field.getCodeGroup().getId());
		 	    
		 	  //  }
	     });
	     
	     itemTypes.forEach(item -> {
		    	
	    	 switch (item.getType())
		     {
		     
		      case ENTITY: 		    	  
		    	   	entityTypes.add((IEntityType) item);		    	  	
		    	    break;
		      case LINK:
		    	    linkTypes.add((ILinkType) item);
			      break;
		    	 
		     }
	     
	    });

	     
	    
		}
	
	public List<IEntityType> getEntityTypes() {
		return  entityTypes;
	}

	@Override
	public List<ILinkType> getLinkTypes() {
		// TODO Auto-generated method stub
		return linkTypes;
	}
	
}