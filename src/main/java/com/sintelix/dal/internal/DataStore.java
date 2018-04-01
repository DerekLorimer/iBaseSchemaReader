package com.sintelix.dal.internal;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Proxy;

import com.sintelix.dal.DataStoreRecordType;
import com.sintelix.dal.IDataStore;

@Entity
@Table(name="_DataStore")
@Proxy(lazy=false)
public class DataStore
  implements IDataStore
{
  private String mId;
  private String mName;
  private String mCategory;
  private String mDescription;
  private String mData;
  private DataStoreRecordType mRecordType;
  private Date mCreateDate;
  private String mCreateUser;
  private Date mUpdateDate;
  private String mUpdateUser;
  private String mGids;
  static final String ID_PROPERTY = "id";
  static final String NAME_PROPERTY = "name";
  static final String CATEGORY_PROPERTY = "category";
  static final String RECORDTYPE_PROPERTY = "recordTypeNumber";
  
 
  
  @Id
  @Column(name="Record_ID", columnDefinition = "nvarchar", length=50, nullable=false)
  public String getId()
  {
    return this.mId;
  }
  
  public  void setId(String id)
  {
    assert (id != null);
    this.mId = id;
  }
  
  @Column(name="Name", columnDefinition = "nvarchar", length=255, nullable=false)
  public  String getName()
  {
    return this.mName;
  }
  
  public  void setName(String value)
  {
    assert (value != null);
    assert (!value.isEmpty());
    this.mName = value;
  }
  
  @Column(name="Category", columnDefinition = "nvarchar", length=255, nullable=false)
  public  String getCategory()
  {
    return this.mCategory;
  }
  
  public void setCategory(String value)
  {
    assert (value != null);
    this.mCategory = value;
  }
  
  @Column(name="Description", columnDefinition = "nvarchar", length=255, nullable=true)
  public  String getDescription()
  {
    return this.mDescription;
  }
  
  public  void setDescription(String value)
  {
    this.mDescription = value;
  }
  
  @Lob
  @Column(name="Data", nullable=true, columnDefinition="NTEXT NULL")
  public  String getData()
  {
    return this.mData;
  }
  
  public  void setData(String value)
  {
    this.mData = value;
  }
  
  @Transient
  public  DataStoreRecordType getRecordType()
  {
    return this.mRecordType;
  }
  
    
  public  void setRecordType(DataStoreRecordType value)
  {
    assert (value != null);
    this.mRecordType = value;
  }
  
  @Column(name="RecordType", nullable=false)
   int getRecordTypeNumber()
  {
    assert (this.mRecordType != null);
    return this.mRecordType.getId();
  }
  
  void setRecordTypeNumber(int value)
  {
    setRecordType(DataStoreRecordType.lookupByIdOrNull(value));
  }
  
  @Column(name="CreateDate", nullable=false)
  public  Date getCreateDate()
  {
    return this.mCreateDate;
  }
  
  public  void setCreateDate(Date value)
  {
    this.mCreateDate = value;
  }
  
  @Column(name="CreateUser", columnDefinition = "nvarchar",length=10, nullable=false)
  public  String getCreateUser()
  {
    return this.mCreateUser;
  }
  
  public  void setCreateUser(String value)
  {
    this.mCreateUser = value;
  }
  
  @Column(name="UpdateDate", nullable=true)
  public  Date getUpdateDate()
  {
    return this.mUpdateDate;
  }
  
  public  void setUpdateDate(Date value)
  {
    this.mUpdateDate = value;
  }
  
  @Column(name="UpdateUser", columnDefinition = "nvarchar", length=10, nullable=true)
  public  String getUpdateUser()
  {
    return this.mUpdateUser;
  }
  
  public  void setUpdateUser(String value)
  {
    this.mUpdateUser = value;
  }
  
  @Column(name="GIDs", columnDefinition = "nvarchar", nullable=true)
  public  String getGids()
  {
    return this.mGids;
  }
  
  public  void setGids(String gids)
  {
    this.mGids = gids;
  }


}