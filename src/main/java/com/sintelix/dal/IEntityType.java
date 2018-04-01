package com.sintelix.dal;

public abstract interface IEntityType
extends IItemType
{
public abstract String getIcon();

public abstract boolean isOverridenByForm();

public abstract IForm getOverridingForm();
}
