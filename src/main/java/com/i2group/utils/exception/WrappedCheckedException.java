/*  1:   */ package com.i2group.utils.exception;
/*  2:   */ 
/*  3:   */ public final class WrappedCheckedException
/*  4:   */   extends RuntimeException
/*  5:   */ {
/*  6:   */   private static final long serialVersionUID = -7010154284187757820L;
/*  7:   */   
/*  8:   */   public WrappedCheckedException(Exception cause)
/*  9:   */   {
/* 10:25 */     super(cause);
/* 11:   */     
/* 12:27 */     throwIllegalArgumentExceptionIfRuntimeException(cause);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public WrappedCheckedException(String message, Exception cause)
/* 16:   */   {
/* 17:49 */     super(message, cause);
/* 18:   */     
/* 19:51 */     throwIllegalArgumentExceptionIfRuntimeException(cause);
/* 20:   */   }
/* 21:   */   
/* 22:   */   private static void throwIllegalArgumentExceptionIfRuntimeException(Exception cause)
/* 23:   */   {
/* 24:56 */     if ((cause instanceof RuntimeException))
/* 25:   */     {
/* 26:58 */       String message = "Can't construct wrapped checked exception with runtime exception";
/* 27:59 */       throw new IllegalArgumentException("Can't construct wrapped checked exception with runtime exception", cause);
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Administrator\Desktop\Intellishare Deployment\intellishare-8.9.1\intellishare\WEB-INF\lib\i2-java-utils-1.1.5.jar
 * Qualified Name:     com.i2group.utils.exception.WrappedCheckedException
 * JD-Core Version:    0.7.0.1
 */