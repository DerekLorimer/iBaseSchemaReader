package com.schemaReader.dal.internal;


//import com.i2group.tracing.TracingManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Proxy;

import com.schemaReader.dal.IEntityType;
import com.schemaReader.dal.ILinkType;

@Entity
@Table(name="_LinkType")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name="LinkType_ID")
@Proxy(lazy=false)
public class LinkType
  extends ItemType
  implements ILinkType
{
  private static final String NTEXT_NULL = "NTEXT NULL";
  private String mCommaSeparatedEnd1TypeIds;
  private String mCommaSeparatedEnd2TypeIds;
  private  List<IEntityType> mLeftEndTypes = new ArrayList();
  private  List<IEntityType> mRightEndTypes = new ArrayList();
  private String mColor;
  private String mSemanticTypeId;
  
  @Transient
  public  TableType getType()
  {
    return TableType.LINK;
  }
  
  @Column(name="Colour", columnDefinition = "nvarchar",length=50, nullable=false, updatable=false)
  public  String getColor()
  {
    return this.mColor;
  }
  
   void setColor(String value)
  {
    this.mColor = value;
  }
  
  @Lob
  @Column(name="End1Types", nullable=true, updatable=false, columnDefinition="NTEXT NULL")
  public  String getCommaSeparatedEnd1TypeIds()
  {
    return this.mCommaSeparatedEnd1TypeIds;
  }
  
   void setCommaSeparatedEnd1TypeIds(String end1TypeIds)
  {
    this.mCommaSeparatedEnd1TypeIds = end1TypeIds;
  }
  
  @Lob
  @Column(name="End2Types", nullable=true, updatable=false, columnDefinition="NTEXT NULL")
  public  String getCommaSeparatedEnd2TypeIds()
  {
    return this.mCommaSeparatedEnd2TypeIds;
  }
  
   void setCommaSeparatedEnd2TypeIds(String commaSeparatedEnd2TypeIds)
  {
    this.mCommaSeparatedEnd2TypeIds = commaSeparatedEnd2TypeIds;
  }
  
  @Transient
  public  List<IEntityType> getLeftEndTypes()
  {
    return Collections.unmodifiableList(this.mLeftEndTypes);
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
  
  public void addLEntityTypesMatchingLeftEndIds(Map<Integer, EntityType> entityTypes)
  {
    List<Integer> leftEndIds = extractIntegerIdsFromString(this.mCommaSeparatedEnd1TypeIds);
    clearAndAddEntityTypesIfMatchEndIds(leftEndIds, this.mLeftEndTypes, entityTypes);
  }
  
  public void addLEntityTypesMatchingRightEndIds(Map<Integer, EntityType> entityTypes)
  {
    List<Integer> rightEndIds = extractIntegerIdsFromString(this.mCommaSeparatedEnd2TypeIds);
    clearAndAddEntityTypesIfMatchEndIds(rightEndIds, this.mRightEndTypes, entityTypes);
  }
  
  private void clearAndAddEntityTypesIfMatchEndIds(List<Integer> endIdsForLink, List<IEntityType> endTypesForLink, Map<Integer, EntityType> entityTypes)
  {
    endTypesForLink.clear();
    if (endIdsForLink.isEmpty()) {
      endTypesForLink.addAll(entityTypes.values());
    } else {
      for (Integer typeId : endIdsForLink)
      {
        EntityType entityType = (EntityType)entityTypes.get(typeId);
        if (entityType != null) {
          endTypesForLink.add(entityType);
        }
      }
    }
  }
  
  @Transient
  public  List<IEntityType> getRightEndTypes()
  {
    return Collections.unmodifiableList(this.mRightEndTypes);
  }
  
  @Transient
   List<Integer> getLeftEndTypeIds()
  {
    return extractIntegerIdsFromString(this.mCommaSeparatedEnd1TypeIds);
  }
  
  @Transient
   List<Integer> getRightEndTypeIds()
  {
    return extractIntegerIdsFromString(this.mCommaSeparatedEnd2TypeIds);
  }
  
//  private  ITracer mTracer = TracingManager.getTracer(LinkType.class);
  
  private List<Integer> extractIntegerIdsFromString(String commasSeparatedIds)
  {
    String[] possibleIdsFromString = SchemaUtils.nullProtect(commasSeparatedIds).split(",");
    List<Integer> ids = new ArrayList();
    for (String string : possibleIdsFromString) {
      if (!string.isEmpty()) {
        try
        {
          Integer temp = Integer.valueOf(string);
          ids.add(temp);
        }
        catch (NumberFormatException ex)
        {
          String message = "An error occurred trying to parse '" + string + "' as a link end type id.";
//          this.mTracer.error(message);
        }
      }
    }
    return ids;
  }
}
