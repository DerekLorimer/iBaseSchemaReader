package com.sintelix.dal.internal;

import com.sintelix.dal.CodeGroupType;
import com.sintelix.dal.ICode;
import com.sintelix.dal.ICodeGroup;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="_CodeGroup")
@Proxy(lazy=false)
public  class CodeGroup
  implements ICodeGroup, Comparable<CodeGroup>
{
	
	/* Get actual class name to be printed on */ 
static Logger log = org.apache.log4j.Logger.getLogger( 
		com.sintelix.dal.internal.CodeGroup.class.getName());
			 
  private int mId;
  private String mDescription;
  private CodeGroupType mCodeGroupType;
  private Integer mParentId;
  private CodeGroup mParentCodeGroup;
  
  @Id
  @Column(name="CodeGroup_ID", updatable=false)
  public int getId()
  {
    return this.mId;
  }
  
  void setId(int value)
  {
    this.mId = value;
  }
  
  @Column(name="Description",  columnDefinition = "nvarchar", length=255, nullable=false, updatable=false)
  public String getDescription()
  {
    assert (this.mDescription != null);
    return this.mDescription;
  }
  
  void setDescription(String value)
  {
    this.mDescription = value;
  }
  
  @Column(name="Type", nullable=true, updatable=false)
  Byte getTypeNumber()
  {
    assert (this.mCodeGroupType != null);
    return Byte.valueOf(this.mCodeGroupType.getId());
  }
  
  void setTypeNumber(Byte value)
  {
    assert (value != null);
    this.mCodeGroupType = CodeGroupType.lookupById(value.byteValue());
  }
  
  @Transient
  public CodeGroupType getType()
  {
    return this.mCodeGroupType;
  }
  
  @Column(name="Parent_ID", nullable=true, updatable=false)
  Integer getParentId()
  {
    return this.mParentId;
  }
  
  void setParentId(Integer value)
  {
    this.mParentId = value;
  }
  
  @Transient
  public CodeGroup getParentGroup()  {
    return this.mParentCodeGroup;
  }
  
  void setParent(CodeGroup value)  {
    this.mParentCodeGroup = value;
  }
  
  private  Set<ICodeGroup> mReadwriteChildGroups = new HashSet();
  private  Set<ICodeGroup> mReadonlyChildGroups = Collections.unmodifiableSet(this.mReadwriteChildGroups);
  
  @Transient
  public Set<ICodeGroup> getChildGroups()  {
    return this.mReadonlyChildGroups;
  }

  void addChildGroup(CodeGroup value)  {
    this.mReadwriteChildGroups.add(value);
  }
  
  private  SortedSet<ICode> mSortedCodes = new TreeSet(Code.getSortIndexComparatorInstance());
  private  SortedSet<ICode> mReadonlySortedCodes = Collections.unmodifiableSortedSet(this.mSortedCodes);
  
  @Transient
  public SortedSet<ICode> getSortedCodes()
  {
    return this.mReadonlySortedCodes;
  }
  
   
  void addCode(Code value)
  {
	  // BUGBUG Code to change later
	log.debug(value.getId() + value.getExpansion() + value.getSortIndex() );
	try {
    if ( mSortedCodes.add(value) ) {
    	log.debug("SUCCESS");
    } else
    	log.debug("FAILURE");
	} catch ( Exception e) {
		log.debug(e.getMessage());
	}
    log.debug("CodeGroup.addCode Id=" + mId + "size=" + mSortedCodes.size());
  }

@Override
public int compareTo(CodeGroup codeGroup) {
	// TODO Auto-generated method stub
	return mDescription.compareTo((codeGroup.getDescription()));
}
}
