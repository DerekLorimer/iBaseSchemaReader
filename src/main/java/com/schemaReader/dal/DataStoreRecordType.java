package com.schemaReader.dal;


import java.util.HashMap;
import java.util.Map;

public enum DataStoreRecordType
{
  SET(12),  QUERY(13),  STRUCTURED_SEARCH_SET(300);
  
  private static final Map<Integer, DataStoreRecordType> ID_MAP;
  private final int mId;
  
  static
  {
    ID_MAP = new HashMap();
    for (DataStoreRecordType e : values()) {
      ID_MAP.put(Integer.valueOf(e.getId()), e);
    }
  }
  
  private DataStoreRecordType(int id)
  {
    this.mId = id;
  }
  
  public static DataStoreRecordType lookupByIdOrNull(int id)
  {
    return (DataStoreRecordType)ID_MAP.get(Integer.valueOf(id));
  }
  
  public final int getId()
  {
    return this.mId;
  }
}

