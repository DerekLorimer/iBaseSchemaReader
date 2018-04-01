package com.schemaReader.dal;

import java.util.List;

public abstract interface IFormPage
{
  public abstract String getName();
  
  public abstract List<IField> getFields();
}