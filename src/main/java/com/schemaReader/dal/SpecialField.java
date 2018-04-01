package com.schemaReader.dal;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;

import com.i2group.utils.exception.NewEnumValueAddedException;

public enum SpecialField
{
	
	
	
	 TEXT(0), UNIQUE_ID(-1),CREATE_USER(-2),CREATE_DATE(-3),LAST_UPD_USER(-4), 
	 LAST_UPD_DATE(-5), SCC(-6), RECORD_STATUS(-7), 
	 STATUS_BINDING(-8), ALT_ENTITY(-9), ICON_SHADING_COLOUR(-10);
	 
	 private static final Map<Integer, SpecialField> ID_MAP;
	 private final int mId;
	 
	 static
	  {
	    ID_MAP = new HashMap();
	    for (SpecialField e : values()) {
	      ID_MAP.put(e.getId(), e);
	    }
	  }


  
  public static SpecialField lookupByFixedFieldId(int id)
  {
	  return (SpecialField)ID_MAP.get(id);
  }
  
  private SpecialField(int id)
  {
	  this.mId = ((byte)id);
  }
  
  public final int getId()
  {
    return this.mId;
  }
  
  public final int getFieldId()
  {
    return this.mId;
  }
  
  public final FieldType getLogicalType()
  {
    switch (ordinal())
    {
    case 0:
    	return FieldType.TEXT;
    case 1: 
      return FieldType.RECORD_ID;
    case 2: 
      return FieldType.CREATE_USER;
    case 3: 
      return FieldType.CREATE_DATE;
    case 4: 
      return FieldType.COUNTING_NUMBER;
    case 5: 
      return FieldType.LAST_UPDATE_DATE;
    case 6: 
      return FieldType.LAST_UPDATE_USER;
    case 7: 
      return FieldType.COUNTING_NUMBER;
    case 8: 
      return FieldType.SCC;
    case 9: 
      return FieldType.TEXT;
    case 10: 
      return FieldType.RECORD_ID;
    }
    throw new NewEnumValueAddedException(this);
  }
}
