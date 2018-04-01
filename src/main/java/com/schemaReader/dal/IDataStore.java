package com.schemaReader.dal;


import java.util.Date;

public abstract interface IDataStore
{
  public abstract String getId();
  
  public abstract void setId(String paramString);
  
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract String getCategory();
  
  public abstract void setCategory(String paramString);
  
  public abstract String getDescription();
  
  public abstract void setDescription(String paramString);
  
  public abstract String getData();
  
  public abstract void setData(String paramString);
  
  public abstract DataStoreRecordType getRecordType();
  
  public abstract void setRecordType(DataStoreRecordType DataStoreRecordType);
  
  public abstract Date getCreateDate();
  
  public abstract void setCreateDate(Date paramDate);
  
  public abstract String getCreateUser();
  
  public abstract void setCreateUser(String paramString);
  
  public abstract Date getUpdateDate();
  
  public abstract void setUpdateDate(Date paramDate);
  
  public abstract String getUpdateUser();
  
  public abstract void setUpdateUser(String paramString);
  
  public abstract String getGids();
  
  public abstract void setGids(String paramString);
}
