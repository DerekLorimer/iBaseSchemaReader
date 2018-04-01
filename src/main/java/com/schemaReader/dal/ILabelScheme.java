package com.schemaReader.dal;

import java.util.List;

public abstract interface ILabelScheme
{
  public abstract String getName();
  
  public abstract List<IField> getLabelFields(IItemType paramIItemType);
  
  public abstract List<ILabelPartInfo> getLabelPartsInfo(IItemType paramIItemType);
}