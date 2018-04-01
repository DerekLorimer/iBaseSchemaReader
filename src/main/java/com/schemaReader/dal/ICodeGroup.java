package com.schemaReader.dal;

import java.util.Set;
import java.util.SortedSet;

public abstract interface ICodeGroup
{
  public abstract int getId();
  
  public abstract String getDescription();
  
  public abstract SortedSet<ICode> getSortedCodes();
   
  public abstract CodeGroupType getType();
  
  public abstract ICodeGroup getParentGroup();

  public abstract Set<ICodeGroup> getChildGroups();
}

