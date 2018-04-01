package com.sintelix.dal;


import java.util.SortedSet;


public abstract interface IForm
{
  public abstract int getId();
  
  public abstract String getName();
  
  public abstract int getMainEntityId();
  
 //  
 public abstract FormStyle getStyle();
//  
  public abstract SortedSet<IFormPage> getFormPages();
//  
public abstract SortedSet<IFormLink> getFormLinks();

public abstract void addFormPage(IFormPage formPage);

public abstract  void addFormLink(IFormLink formLink);
}


