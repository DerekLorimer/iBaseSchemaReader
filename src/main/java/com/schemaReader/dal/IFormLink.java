package com.schemaReader.dal;


import java.util.SortedSet;

public abstract interface IFormLink
{
  public abstract String getName();
  
  public abstract IEntityType getEntityType();
  
  public abstract ILinkType getLinkType();
  
  public abstract LinkDirection getDirection();
  
  public abstract boolean ignoreDirectionForShow();
  
  public abstract SortedSet<IFormPage> getFormPages();
}