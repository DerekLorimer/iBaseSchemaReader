package com.sintelix.dal;

import java.util.HashMap;
import java.util.Map;

public enum FormStyle
{
  STANDARD(0),  TABBED(1),  HYPERLINK(2);
  
  private static final Map<Byte, FormStyle> ID_MAP;
  private final byte mId;
  
  static
  {
    ID_MAP = new HashMap();
    for (FormStyle e : values()) {
      ID_MAP.put(Byte.valueOf(e.getId()), e);
    }
  }
  
  private FormStyle(int id)
  {
    this.mId = ((byte)id);
  }
  
  public static FormStyle lookupById(byte id)
  {
    return (FormStyle)ID_MAP.get(Byte.valueOf(id));
  }
  
  public final byte getId()
  {
    return this.mId;
  }
}
