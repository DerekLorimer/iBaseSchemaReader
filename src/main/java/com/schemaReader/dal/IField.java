package com.schemaReader.dal;


public abstract interface IField
{
 	
  public abstract int getId();
  
  public abstract IItemType getItemType();
  
  public abstract boolean Fixed();
  
  public abstract String getLogicalName();
  
  public abstract Short getFieldIndex();
  
  public abstract String getPhysicalName();
  
  public abstract String getDescription();
  
  public abstract boolean Mandatory();
  
  public abstract boolean Characteristic();
  
  public abstract boolean Discriminator();
  
  public abstract boolean Indexed();
  
  public abstract FieldType getLogicalType();
  
  public abstract Integer getMaximumFieldSize();
  
  public abstract boolean Readonly();
  
  public abstract ICodeGroup getCodeGroup();
  
  public abstract Object getDefaultLiteralValue();
  
  public abstract String getSemanticType();
  
  public abstract void setSemanticType(String mSemanticType);
  
  public abstract String getSemanticTypeId();
  
  public abstract void setSemanticTypeId(String mSemanticTypeId);
  
  public abstract String getI2AnalyzeId();
  
  
 
}

