package com.sintelix.dal.internal;

import java.util.Collection;
import java.util.SortedSet;

public class SchemaUtils
{
 
  public static String nullProtect(String value)
  {
    if (value == null) {
      return "";
    }
    return value;
  }
  
  static <TInterface, TImplementation extends TInterface> SortedSet<TInterface> downcastSortedSet(SortedSet<TImplementation> implSet)
  {
    return (SortedSet<TInterface>) implSet;
  }
  
  static <TInterface, TImplementation extends TInterface> Collection<TInterface> downcastCollection(Collection<TImplementation> implSet)
  {
    return (Collection<TInterface>) implSet;
  }
  
 }
