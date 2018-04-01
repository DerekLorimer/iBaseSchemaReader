package com.schemaReader.dal.sql;

import java.sql.Timestamp;

import com.schemaReader.dal.FieldType;

public class Column {
	
	  public String name;
	  public String value;
	  public FieldType fieldType;
	  public int maximumChars;
	  public Timestamp timestamp;
	  public boolean key = false;
	 
	  
	  
		  
	  public Column(String name, String value) {
		  this.name = name;
		  this.value = value;
	  }
	  
	  public Column(String name, String value, FieldType fieldType) {
		  this.name = name;
		  this.value = value;
		  this.fieldType = fieldType;
	  }
	  
	  public Column(String name, String value, FieldType fieldType, boolean key) {
		  this.name = name;
		  this.value = value;
		  this.fieldType = fieldType;
		  this.key = key;
	  }
	 
	  
	  public Column(String name, Timestamp timestamp ) {
		  this.name = name;
		  this.timestamp = timestamp;
		  this.fieldType = FieldType.CREATE_DATE;
	  }
	  
	  
	
	  
	  public boolean isKey()  {
		  return key;
	  }
	  
	  
	  
	 

}
