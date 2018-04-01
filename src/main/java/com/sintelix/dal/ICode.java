package com.sintelix.dal;


import java.util.SortedSet;

public abstract interface ICode
{
  public abstract String getId();
  
  public abstract String getExpansion();
  
  public abstract String getDescription();
  
  public abstract ICodeGroup getCodeGroup();
  
  public abstract ICode getParentCode();
  
  public abstract SortedSet<ICode> getSortedChildCodes();
  

}

