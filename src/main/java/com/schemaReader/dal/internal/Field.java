package com.schemaReader.dal.internal;

import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.annotations.Proxy;

import com.schemaReader.dal.DefaultValueType;
import com.schemaReader.dal.FieldType;
import com.schemaReader.dal.ICodeGroup;
import com.schemaReader.dal.IField;
import com.schemaReader.dal.IItemType;
import com.schemaReader.dal.SpecialField;

@Entity
@Table(name="_Field")
@Proxy(lazy=false)
public  class Field
  implements IField
{
  private int mId;
  private String mI2AnalyzeId;
  private String mLogicalName;
  private String mDefaultValueRaw;
  private Object mDefaultLiteralValue;
  private DefaultValueType mDefaultValueType;
  private String mPhysicalName;
  private short mIsFixed;
  private String mDescription;
  private int mTableId;
  private Integer mFieldSize;
  private Short mFieldIndex;
  private FieldType mLogicalType;
  private boolean mDiscriminator;
  private boolean mIndexed;
  private boolean mCharacteristic;
  private String mSemanticType;
  private String mSemanticTypeId;
  private boolean mMandatory;
  private Integer mCodeGroupId;
  private ICodeGroup mCodeGroup;
  private IItemType mTable;
  private boolean mReadOnly;
  
  static Logger log = LogManager.getLogger( 
		  com.schemaReader.dal.internal.Field.class.getName()); 
  
  
@Id
  @Column(name="Field_ID", updatable=false)
  public int getId()
  {
    return this.mId;
  }
  
  void setId(int id)
  {
    this.mId = id;
  }
  
  @Column(name="LogicalName", columnDefinition = "nvarchar",length=255, nullable=false, updatable=false)
  public String getLogicalName()
  {
    return SchemaUtils.nullProtect(this.mLogicalName);
  }
  
  void setLogicalName(String value)
  {
    this.mLogicalName = value;
  }
  
  @Column(name="DefaultValue", columnDefinition = "nvarchar",length=255, nullable=false, updatable=false)
  String getRawDefaultValue()
  {
    return this.mDefaultValueRaw;
  }
  
  void setRawDefaultValue(String value)
  {
    this.mDefaultValueRaw = value;
  }
  
  @Transient
  public Object getDefaultLiteralValue()
  {
    return this.mDefaultLiteralValue;
  }
  
  void setDefaultLiteralValue(Object defaultValueLiteral)
  {
    this.mDefaultLiteralValue = defaultValueLiteral;
  }
  
  @Transient
  public DefaultValueType getDefaultValueType()
  {
    return this.mDefaultValueType;
  }
  
  void setDefaultValueType(DefaultValueType defaultValueType)
  {
    this.mDefaultValueType = defaultValueType;
  }
  
  @Column(name="PhysicalName",columnDefinition = "nvarchar", length=255, nullable=false, updatable=false)
  public String getPhysicalName()
  {
    return SchemaUtils.nullProtect(this.mPhysicalName);
  }
  
  void setPhysicalName(String value)
  {
    this.mPhysicalName = value;
  }
  
  @Column(name="Fixed", nullable=false, updatable=false)
  short getFixed()
  {
    return this.mIsFixed;
  }
  
  void setFixed(short value)
  {
    this.mIsFixed = value;
  }
  
  @Transient
  public boolean Fixed()
  {
    return fromVBBoolean(getFixed());
  }
  
  @Column(name="Description", columnDefinition = "nvarchar",length=255, nullable=true, updatable=false)
  public String getDescription()
  {
    return SchemaUtils.nullProtect(this.mDescription);
  }
  
  void setDescription(String value)
  {
    this.mDescription = value;
  }
  
  @Column(name="Table_ID", nullable=false, updatable=false)
  public int getTableId()
  {
    return this.mTableId;
  }
  
  public void setTableId(int value)
  {
    this.mTableId = value;
  }
  
  @Column(name="FieldSize", nullable=true, updatable=false, columnDefinition="TINYINT NULL")
  public Integer getMaximumFieldSize()
  {
    return this.mFieldSize;
  }
  
  void setMaximumFieldSize(Integer value)
  {
    this.mFieldSize = value;
    if ((value != null) && (value.intValue() == 0)) {
      this.mFieldSize = null;
    }
  }
  
  @Column(name="FieldIndex", nullable=true, updatable=false)
  public Short getFieldIndex()
  {
    return this.mFieldIndex;
  }
  
  void setFieldIndex(Short value)
  {
    this.mFieldIndex = value;
  }
  
  @Column(name="LogicalType", nullable=false, updatable=false)
  byte getLogicalTypeNumber()
  {
    return this.mLogicalType.getId();
  }
  
  void setLogicalTypeNumber(byte value)
  {
    this.mLogicalType = FieldType.lookupById(value);
  }
  
  @Transient
  public boolean isSupportedFieldType()
  {
    return getLogicalType().isSupportedFieldType();
  }
  
  @Transient
  public FieldType getLogicalType()
  {
    FieldType result;
    
   // log.debug(mLogicalName + "LOGICAL TYPE" + mLogicalType);
 
    if ((Fixed()) && (getId() < 0))
    {
    //  log.debug("SPECIAL FIELD");
      SpecialField specialField = SpecialField.lookupByFixedFieldId(getId());
      
      // Fix for bug SCC  -20
      
     if( specialField == null ) {
    //	 log.error("NULL specicalField" + getId());
    	 return null;
     }
    	
      
      result = specialField.getLogicalType();
    }
    else
    {
    //	log.debug(mLogicalType);
    	
      result = this.mLogicalType;
      
    //  log.debug("AFTER" + result.name());
    }
    assert (result != null);
    return result;
  }
  
  @Column(name="Discriminator", nullable=false, updatable=false)
  short getDiscriminator()
  {
    return toVBBoolean(this.mDiscriminator);
  }
  
  void setDiscriminator(short value)
  {
    this.mDiscriminator = fromVBBoolean(value);
  }
  
  @Transient
  public boolean Discriminator()
  {
    return this.mDiscriminator;
  }
  
  @Column(name="Search", nullable=false, updatable=false)
  short getIndexed()
  {
    return toVBBoolean(this.mIndexed);
  }
  
  void setIndexed(short value)
  {
    this.mIndexed = fromVBBoolean(value);
  }
  
  @Transient
  public boolean Indexed()
  {
    return this.mIndexed;
  }
  
  @Column(name="Characteristic", nullable=false, updatable=false)
  short getCharacteristic()
  {
    return toVBBoolean(this.mCharacteristic);
  }
  
  void setCharacteristic(short value)
  {
    this.mCharacteristic = fromVBBoolean(value);
  }
  
  
  @Transient
  public boolean Characteristic()
  {
    return this.mCharacteristic;
  }

  
  @Transient
  public String getSemanticType() {
		return mSemanticType;
	}

	public void setSemanticType(String i2Mapping) {
		this.mSemanticType = mSemanticType;
	}
	
	@Transient
	@Override
	public String getSemanticTypeId() {
		// TODO Auto-generated method stub
		return mSemanticTypeId;
	}

	@Override
	public void setSemanticTypeId(String mSemanticTypeId) {
		this.mSemanticTypeId = mSemanticTypeId;
	}
	
	@Transient
	public String getI2AnalyzeId() {
			return mI2AnalyzeId;
	}

	public void setI2AnalyzeId(String mI2AnalyzeId) {
		this.mI2AnalyzeId = mI2AnalyzeId;
	}
  
  @Column(name="Mandatory", nullable=false, updatable=false)
  short getMandatory()
  {
	  log.debug(this.mId + this.mLogicalName + this.mMandatory);
    return toVBBoolean(this.mMandatory);
  }
  
  void setMandatory(short value)
  {
	 log.debug(this.mId + "value=" +  value);
	 this.mMandatory = fromVBBoolean(value);
  }
  
  @Transient
  public boolean Mandatory()
  {
	// log.debug(this.mId + this.mLogicalName + this.mMandatory);
    return (this.mMandatory) || (this.mLogicalType == FieldType.YES_NO);
  }
  
  @Column(name="CodeGroup_ID", nullable=true, updatable=false)
  Integer getCodeGroupId()
  {
    return this.mCodeGroupId;
  }
  
  void setCodeGroupId(Integer value)
  {
    this.mCodeGroupId = value;
  }
  
  @Transient
  public ICodeGroup getCodeGroup()
  {
    return this.mCodeGroup;
  }
  
  void setCodeGroup(ICodeGroup value)
  {
    this.mCodeGroup = value;
  }
  
  @Transient
  public IItemType getItemType()
  {
    return this.mTable;
  }
  
  void setItemType(IItemType value)
  {
    this.mTable = value;
  }
  
  @Transient
  public boolean Readonly()
  {
    return this.mReadOnly;
  }
  
  @Transient
  public void setReadonly(boolean readOnly)
  {
    this.mReadOnly = readOnly;
  }
  
  private static final Comparator<Field> SORT_INDEX_COMPARATOR = new SortIndexComparator();
  
  public static Comparator<Field> getSortIndexComparatorInstance()
  {
    return SORT_INDEX_COMPARATOR;
  }
  
  private static boolean fromVBBoolean(short visualBasicValue)
  {
    return visualBasicValue != 0;
  }
  
  private static short toVBBoolean(boolean value)
  {
	  int result;
	  
	  result = value ? -1 : 0;
	  
	  short shortresult = (short) result;
	  
    return shortresult;
  }
  
  private static final class SortIndexComparator
    implements Comparator<Field>
  {
    private static int compare(int i1, int i2)
    {
      if (i1 < i2) {
        return -1;
      }
      if (i1 == i2) {
        return 0;
      }
      return 1;
    }
    
    public int compare(Field f1, Field f2)
    {
      int tableIdDiff = compare(f1.getTableId(), f2.getTableId());
      if (tableIdDiff != 0) {
        return tableIdDiff;
      }
      Short i1 = f1.getFieldIndex();
      Short i2 = f2.getFieldIndex();
      if ((i1 != null) && (i2 != null))
      {
        int fieldIndexDiff = i1.compareTo(i2);
        if (fieldIndexDiff != 0) {
          return fieldIndexDiff;
        }
      }
      return compare(f1.getId(), f2.getId());
    }
  }


}