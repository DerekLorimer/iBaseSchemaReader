package com.sintelix.dal.internal;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

import com.sintelix.dal.IEntityType;
import com.sintelix.dal.IForm;

@XmlRootElement
@Entity
@Table(name="_Entity")
@DiscriminatorValue("0")
@Proxy(lazy=false)
public class EntityType
  extends ItemType
  implements IEntityType
{
  private String mChartEntity;
  private boolean mIsOverridenByForm;
  private IForm mOverridingForm;
  private String mSemanticTypeId;
  
  @OrderBy("LogicalName")
  
  @Column(name="ChartEntity",columnDefinition = "nvarchar", length=255, nullable=true, updatable=false)
  public  String getIcon()
  {
    return this.mChartEntity;
  }
  
   void setIcon(String value)
  {
    this.mChartEntity = value;
  }
  
  @Transient
  public  TableType getType()
  {
    return TableType.ENTITY;
  }
  
  @Transient
  public  boolean isOverridenByForm()
  {
    return this.mIsOverridenByForm;
  }
  
   void setOverridenByForm(boolean value)
  {
    this.mIsOverridenByForm = value;
  }
  
  @Transient
  public  IForm getOverridingForm()
  {
    return this.mOverridingForm;
  }
  
   void setOverridingForm(IForm value)
  {
    this.mOverridingForm = value;
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
}

