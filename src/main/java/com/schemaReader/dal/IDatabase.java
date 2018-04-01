package com.schemaReader.dal;

public interface IDatabase {
	  public String getLogicalName();
	  public String getPhysicalName();
	  public String getI2Schema();
}
