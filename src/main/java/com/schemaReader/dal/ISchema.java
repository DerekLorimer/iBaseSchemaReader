package com.schemaReader.dal;

import java.util.Collection;

public abstract interface ISchema {

	public abstract Collection<IEntityType> getEntityTypes();

	public abstract Collection<ILinkType> getLinkTypes();

}
