package com.schemaReader.dal.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Proxy;

import com.schemaReader.dal.ICode;

@Entity
@Table(name="_Codes")
@Proxy(lazy=false)
public  class Code
  implements ICode
{
	
	static Logger log = org.apache.log4j.Logger.getLogger( 
			com.schemaReader.dal.internal.Code.class.getName());
	
  private String mId;
  private String mDescription;
  private String mExpansion;
  private Integer mSortIndex;
  private String mParentId;
  private Code mParent;
  private  SortedSet<ICode> mSortedChildCodes;
  private  SortedSet<ICode> mReadonlySortedChildCodes;
  private CodeGroup mCodeGroup;
  
  @Id
  @Column(name="Unique_ID",columnDefinition = "nvarchar", length=50, nullable=false, updatable=false)
  public String getId()
  {
    return this.mId;
  }
  
  void setId(String value)
  {
    this.mId = value;
  }
  
  @Column(name="Description", columnDefinition = "nvarchar", length=255, nullable=false, updatable=false)
  public String getDescription()
  {
    return SchemaUtils.nullProtect(this.mDescription);
  }
  
  void setDescription(String value)
  {
    this.mDescription = value;
  }
  
  @Column(name="Expansion",columnDefinition = "nvarchar", length=255, nullable=true, updatable=false)
  public String getExpansion()
  {
    return SchemaUtils.nullProtect(this.mExpansion);
  }
  
  void setExpansion(String value)
  {
    this.mExpansion = value;
  }
  
  @Column(name="SortIndex", nullable=true, updatable=false)
  Integer getSortIndex()
  {
    return this.mSortIndex;
  }
  
  void setSortIndex(Integer value)
  {
    this.mSortIndex = value;
  }
  
  @Column(name="Parent_ID", columnDefinition = "nvarchar",length=50, nullable=true, updatable=false)
  String getParentId()
  {
    return this.mParentId;
  }
  
  void setParentId(String value)
  {
    this.mParentId = value;
  }
  
  @Transient
  public Code getParentCode()
  {
    return this.mParent;
  }
  
  void setParent(Code value)
  {
    this.mParent = value;
  }
  
  public Code()
  {
    this.mSortedChildCodes = new TreeSet(getSortIndexComparatorInstance());
    

    this.mReadonlySortedChildCodes = Collections.unmodifiableSortedSet(this.mSortedChildCodes);
  }
  
  @Transient
  public SortedSet<ICode> getSortedChildCodes()
  {
    return this.mReadonlySortedChildCodes;
  }
  
  void addChildCode(Code code)
  {
    this.mSortedChildCodes.add(code);
  }
  
 @ManyToOne
 @JoinColumn(name="CodeGroup_ID", nullable=false, updatable=false)
  public CodeGroup getCodeGroup() {
    return this.mCodeGroup;
  }
  
  void setCodeGroup(CodeGroup value) {
    this.mCodeGroup = value;
  }
  
  private static final Comparator<Code> SORT_INDEX_COMPARATOR = new SortIndexComparator();
  
  static Comparator<Code> getSortIndexComparatorInstance()
  {
    return SORT_INDEX_COMPARATOR;
  }
  
  private static final class SortIndexComparator
    implements Comparator<Code>
  {
    public int compare(Code c1, Code c2)
    {
      
      Integer i1 = c1.getSortIndex();
      Integer i2 = c2.getSortIndex();
     // log.debug("Code.compare"+ i1 + ":" + i2);
      if ((i1 != null) && (i2 != null)) {
     //   return i1.compareTo(i2);
      }
      String exp1 = c1.getExpansion();
      String exp2 = c2.getExpansion();
      
      return exp1.compareTo(exp2);
    }
  }
}
