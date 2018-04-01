package com.sintelix.dal;

import java.util.List;
import java.util.SortedSet;

import com.sintelix.dal.internal.TableType;
import com.sintelix.dal.sql.Column;

public abstract interface IItemType
{
  public abstract int getId();
  
  public abstract TableType getType();
  
  public abstract String getDescription();
  
  public abstract String getLogicalName();
  
  public abstract String getPhysicalName();
  
  public abstract String getTableCode();
  
  public abstract SortedSet<IField> getFields();
  
  public abstract SortedSet<IField> getAllFields();
  
  public abstract List<Column> getAllColumns();
  
  public abstract SortedSet<IField> getMandatoryFields();
  
  public abstract IField getSpecialField(SpecialField paramRenoirSpecialField);
  
  public abstract boolean isReadOnly();
  
  public abstract String getI2AnalyzeId();
  
  public abstract String getSemanticTypeId();
  
  public abstract void setSemanticTypeId(String mSemanticTypeId);
 
}