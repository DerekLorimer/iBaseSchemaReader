package com.sintelix.dal;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum CodeGroupType
{
  CODE_LIST(3),  ICON_LIST(6),  CASE(7);
  
  private static final Map<Byte, CodeGroupType> ID_MAP;
  private final byte mId;
  
  // Constructor
  static
  {
    ID_MAP = new HashMap<Byte, CodeGroupType>();
   
    
    for (CodeGroupType e : values()) {
      ID_MAP.put(Byte.valueOf(e.getId()), e);
     }
  }
  
  public static CodeGroupType lookupById(byte id)
  {
    return (CodeGroupType)ID_MAP.get(Byte.valueOf(id));
  }
  
  private CodeGroupType(int id)
  {
    this.mId = ((byte)id);
  }
  
  public final byte getId()
  {
    return this.mId;
  }
  
  public static Stream<CodeGroupType> stream() {
      return Stream.of(CodeGroupType.values()); 
  }
}
