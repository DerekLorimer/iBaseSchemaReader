package com.sintelix.dal;

import com.i2group.utils.exception.NewEnumValueAddedException;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

public enum FieldType
{
  TEXT(0, false, StandardBasicTypes.STRING),  REAL_NUMBER(1, false, StandardBasicTypes.DOUBLE),  DATE(2, false, StandardBasicTypes.DATE),  TIME(3, false, StandardBasicTypes.TIMESTAMP),  COUNTING_NUMBER(4, false, StandardBasicTypes.INTEGER),  CURRENCY(5, false, StandardBasicTypes.BIG_DECIMAL),  YES_NO(6, false, StandardBasicTypes.SHORT),  SELECTED_FROM_CODE_LIST(7, false, StandardBasicTypes.STRING),  SUGGESTED_FROM_CODE_LIST(8, false, StandardBasicTypes.STRING),  OLE(9, true),  MEMO(10, false, StandardBasicTypes.TEXT),  CREATE_DATE(12, false, StandardBasicTypes.TIMESTAMP),  CREATE_USER(13, false, StandardBasicTypes.STRING),  LAST_UPDATE_USER(14, false, StandardBasicTypes.STRING),  LAST_UPDATE_DATE(15, false, StandardBasicTypes.TIMESTAMP),  RECORD_ID(16, false, StandardBasicTypes.STRING),  SCC(21, false, StandardBasicTypes.STRING),  ICON(22, false, StandardBasicTypes.STRING),  STRENGTH(23, false, StandardBasicTypes.SHORT),  HYPERLINK(24, false, StandardBasicTypes.STRING),  PICTURE(25, true, StandardBasicTypes.STRING),  DOCUMENT(26, true, StandardBasicTypes.STRING),  MEMO_APPEND_ONLY(27, false, StandardBasicTypes.TEXT),  TIME_ZONE(28, false),  COORDINATE(29, false),  CALCULATED_DATE_PART(30, false),  CALCULATED_NUMBER(31, false),  CALCULATED_DATE(32, false);
  
  private static final Map<Byte, FieldType> ID_MAP;
  private final byte mId;
  private final boolean mIsLOBType;
  private final Type mHibernateType;
  
  static
  {
    ID_MAP = new HashMap();
    for (FieldType e : values()) {
      ID_MAP.put(Byte.valueOf(e.getId()), e);
    }
  }
  
  public static FieldType lookupById(byte id)
  {
    return (FieldType)ID_MAP.get(Byte.valueOf(id));
  }
  
  private FieldType(int id, boolean isLOBType)
  {
    this(id, isLOBType, null);
  }
  
  private FieldType(int id, boolean isLOBType, Type hibernateType)
  {
    this.mId = ((byte)id);
    this.mIsLOBType = isLOBType;
    this.mHibernateType = hibernateType;
  }
  
  public final byte getId()
  {
    return this.mId;
  }
  
  public final boolean isLOBType()
  {
    return this.mIsLOBType;
  }
  
  public final boolean isSupportedFieldType()
  {
    switch (ordinal())
    {
    case 0:
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
      return true;
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
      return false;
    }
    throw new NewEnumValueAddedException(this);
  }
  
  public final Type getHibernateType()
  {
    if (this.mHibernateType == null) {
      throw new UnsupportedOperationException("Fields of type " + this + " are not supported for data access.");
    }
    return this.mHibernateType;
  }
  
  public final SpecialField getSpecialFieldType()
  {
    switch (ordinal())
    {
    case 0:  
      return SpecialField.TEXT;
    case 1: 
      return SpecialField.CREATE_DATE;
    case 2: 
    case 3: 
      return SpecialField.LAST_UPD_USER;
    case 4: 
      return SpecialField.LAST_UPD_DATE;
    case 5: 
      return SpecialField.UNIQUE_ID;
    case 6: 
      return SpecialField.SCC;
    case 7: 
      return SpecialField.ALT_ENTITY;
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    	return SpecialField.CREATE_USER;
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
      return null;
    }
    throw new NewEnumValueAddedException(this);
  }
  
  public final boolean isServerAssignedFieldType()
  {
    switch (ordinal())
    {
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      return true;
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
      return false;
    }
    throw new NewEnumValueAddedException(this);
  }
}
