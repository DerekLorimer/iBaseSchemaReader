package com.sintelix.dal.internal;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.sintelix.dal.FieldType;
import com.sintelix.dal.IField;
import com.sintelix.dal.IItemType;
import com.sintelix.dal.ILabelPartInfo;
import com.sintelix.dal.SpecialField;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="Type", columnDefinition = "tinyint", discriminatorType=DiscriminatorType.INTEGER)
@Table(name="_DataTable")
@Proxy(lazy=false) 


public abstract class ItemType
  implements IItemType, Comparable<ItemType>
{
  private static final int BASE_ID_FOR_AUDIT_FIELDS = -11;
  private int mId;
  private String mI2AnalyzeId;
  private String mLogicalName;
  private String mPhysicalName;
  private String mDescription;
  private String mTableCode;
  private String mSemanticType;
  
 
 
  
  @Id
  @Column(name="Table_ID", updatable=false)
  public  int getId()
  {
    return this.mId;
  }
  
   void setId(int value)
  {
    this.mId = value;
  }
  
  @Column(name="LogicalName", columnDefinition = "nvarchar", length=255, nullable=false, updatable=false)
  public  String getLogicalName()
  {
    return this.mLogicalName;
  }
  
   public void setLogicalName(String value)
  {
    this.mLogicalName = value;
  }
  
  @Column(name="PhysicalName", columnDefinition = "nvarchar",length=255, nullable=false, updatable=false)
   public  String getPhysicalName()
  {
    return this.mPhysicalName;
  }
  
   public void setPhysicalName(String value)
  {
    this.mPhysicalName = value;
  }
  
  @Column(name="Description",columnDefinition = "nvarchar", length=255, nullable=true, updatable=false)
  public  String getDescription()
  {
    return SchemaUtils.nullProtect(this.mDescription);
  }
  
   void setDescription(String value)
  {
    this.mDescription = value;
  }
  
  @Column(name="TableCode", columnDefinition = "nvarchar",length=3, nullable=false, updatable=false)
  public  String getTableCode()
  {
    return this.mTableCode;
  }
  
   void setTableCode(String value)
  {
    this.mTableCode = value;
  }
  
  private  SortedSet<IField> mModifiablePublicFields = new TreeSet(Field.getSortIndexComparatorInstance());
  private  SortedSet<IField> mUnmodifiablePublicFields = Collections.unmodifiableSortedSet(this.mModifiablePublicFields);
  private  SortedSet<IField> mModifiableAllFields = new TreeSet(Field.getSortIndexComparatorInstance());
  private  SortedSet<IField> mUnmodifiableAllFields = Collections.unmodifiableSortedSet(this.mModifiableAllFields);
  private  SortedSet<IField> mMandatoryFields = new TreeSet(Field.getSortIndexComparatorInstance());
  
  private  Set<IField> mDiscriminators = new HashSet<IField>();
  
  private boolean mReadOnly;
  
  @Transient
  public abstract TableType getType();
  
  @Transient
  public  SortedSet<IField> getFields()
  {
    return this.mUnmodifiablePublicFields;
  }
  
  
  @Transient
  public  SortedSet<IField> getAllFields()
  {
    return this.mUnmodifiableAllFields;
  }
  
  @Transient public  List<com.sintelix.dal.sql.Column> getAllColumns() {
	  // Fix for SQLBuilder
	  List<com.sintelix.dal.sql.Column> columns = new ArrayList<com.sintelix.dal.sql.Column>();
	  this.mUnmodifiableAllFields.forEach( field -> {
		  columns.add(new com.sintelix.dal.sql.Column(field.getPhysicalName(),"",field.getLogicalType()));
	  });
	  
	  return columns;
  }
  
   void addPublicField(Field field)
  {
    this.mModifiablePublicFields.add(field);
  }
  
   public void addAllField(Field field)
  {
    this.mModifiableAllFields.add(field);
  }
   
   public void addMandatoryField(Field field) {
	   this.mMandatoryFields.add(field);
   }
   
   @Transient
   public  SortedSet<IField> getMandatoryFields()  {
     return this.mMandatoryFields;
   }
   
   public void addDiscriminator(Field field) {
	   this.mDiscriminators.add(field);
   }
   
   @Transient
   public Set<IField> getDiscriminators() {
	   return this.mDiscriminators;
   }
  
  @Transient
  public  boolean isReadOnly()
  {
    return this.mReadOnly;
  }
  
  @Transient
  public  void setReadonly(boolean readOnly)
  {
    this.mReadOnly = readOnly;
  }
  
  private  Map<SpecialField, IField> mSpecialFields = new HashMap();
  
  public void considerSpecialField(IField coreFixedField)
  {
	  int fieldId = coreFixedField.getId();
    
    
    assert ((fieldId < 0) && (fieldId > -11));
    
    SpecialField specialFieldKey = SpecialField.lookupByFixedFieldId(fieldId);
  
    IField specialFieldValue = coreFixedField;
    
    FieldType logicalTypeForSystemField = coreFixedField.getLogicalType();
    

    assert (logicalTypeForSystemField != null);
    



    SpecialField specialFieldForLogicalType = logicalTypeForSystemField.getSpecialFieldType();
    if (specialFieldForLogicalType != null)
    {
      assert (specialFieldForLogicalType == specialFieldKey);
      for (IField field : this.mModifiablePublicFields) {
        if (field.getLogicalType() == logicalTypeForSystemField)
        {
          specialFieldValue = field;
          break;
        }
      }
    }
    
    this.mSpecialFields.put(specialFieldKey, specialFieldValue);
  }
  
  @Transient
  public  IField getSpecialField(SpecialField specialField)
  {

    return (IField)this.mSpecialFields.get(specialField);
  }
  
  private List<ILabelPartInfo> mLabelParts = new ArrayList();
  
  @Transient
  public  List<ILabelPartInfo> getLabelParts()
  {
    return this.mLabelParts;
  }
  
  @Transient
  public  void setLabelParts(List<ILabelPartInfo> labelParts)
  {
    this.mLabelParts = labelParts;
  }
  
  @Transient
  public String getSemanticType() {
		return mSemanticType;
	}

	public void setSemanticType(String mSemanticType) {
		this.mSemanticType = mSemanticType;
	}
	
	@Transient
	public String getI2AnalyzeId() {
			return mI2AnalyzeId;
	}

	public void setI2AnalyzeId(String mI2AnalyzeId) {
		this.mI2AnalyzeId = mI2AnalyzeId;
	}
	
	@Override
	public int compareTo(ItemType itemType) {
		// TODO Auto-generated method stub
		return mLogicalName.compareTo((itemType.getLogicalName()));
	}
}
