package com.schemaReader.dal;

public abstract interface ILabelPartInfo {

  public abstract LabelPartType getLabelPartType();
  
  public abstract String getUserText();
  
  public abstract Integer getFieldId();
}
