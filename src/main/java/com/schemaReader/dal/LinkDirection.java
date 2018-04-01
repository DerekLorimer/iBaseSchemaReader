package com.schemaReader.dal;

import com.i2group.utils.exception.NewEnumValueAddedException;
import java.util.HashMap;
import java.util.Map;

public enum LinkDirection
{
  NONE(0),  LEFT_TO_RIGHT(1),  RIGHT_TO_LEFT(2),  BOTH(3);
  
  private static final Map<Byte, LinkDirection> ID_MAP;
  private final byte mId;
  
  static
  {
    ID_MAP = new HashMap();
    for (LinkDirection e : values()) {
      ID_MAP.put(Byte.valueOf(e.getId()), e);
    }
  }
  
  private LinkDirection(int id)
  {
    this.mId = ((byte)id);
  }
  
  public final byte getId()
  {
    return this.mId;
  }
  
  public final LinkDirection reverseDirection()
  {
    switch (ordinal())
    {
    case 1: 
      return NONE;
    case 2: 
      return RIGHT_TO_LEFT;
    case 3: 
      return LEFT_TO_RIGHT;
    case 4: 
      return BOTH;
    }
    throw new NewEnumValueAddedException(this);
  }
  
  public static LinkDirection lookupById(byte id)
  {
    return (LinkDirection)ID_MAP.get(Byte.valueOf(id));
  }
}
