/*  1:   */ package com.i2group.utils.exception;
/*  2:   */ 
/*  3:   */ public class NewEnumValueAddedException
/*  4:   */   extends RuntimeException
/*  5:   */ {
/*  6:   */   private static final long serialVersionUID = 1L;
/*  7:   */   
/*  8:   */   public NewEnumValueAddedException(Enum<?> anEnumObject)
/*  9:   */   {
/* 10:59 */     super("New enum value (" + (anEnumObject == null ? "null" : anEnumObject.toString()) + ") is not supported.");
/* 11:   */   }
/* 12:   */ }


/* Location:           C:\Users\Administrator\Desktop\Intellishare Deployment\intellishare-8.9.1\intellishare\WEB-INF\lib\i2-java-utils-1.1.5.jar
 * Qualified Name:     com.i2group.utils.exception.NewEnumValueAddedException
 * JD-Core Version:    0.7.0.1
 */