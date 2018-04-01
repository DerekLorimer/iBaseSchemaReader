package com.schemaReader.dal;

import java.util.List;


public interface ICatalog {
	public boolean read();
	public List<IDatabase> getDatabases();
}