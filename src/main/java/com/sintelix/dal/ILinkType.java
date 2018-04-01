package com.sintelix.dal;

import java.util.List;

public abstract interface ILinkType
  extends IItemType
{
  public abstract String getColor();
  
  public abstract List<IEntityType> getLeftEndTypes();
  
  public abstract List<IEntityType> getRightEndTypes();
}
