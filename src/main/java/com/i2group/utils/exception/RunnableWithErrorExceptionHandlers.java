/*  1:   */ package com.i2group.utils.exception;
/*  2:   */ 
/*  3:   */ public abstract class RunnableWithErrorExceptionHandlers
/*  4:   */   implements Runnable
/*  5:   */ {
/*  6:   */   public final void run()
/*  7:   */   {
/*  8:41 */     preExecuteSetup();
/*  9:   */     try
/* 10:   */     {
/* 11:45 */       execute();
/* 12:   */     }
/* 13:   */     catch (Exception ex)
/* 14:   */     {
/* 15:   */       try
/* 16:   */       {
/* 17:51 */         handleExecuteException(ex);
/* 18:   */       }
/* 19:   */       catch (Throwable ex2)
/* 20:   */       {
/* 21:55 */         handleThrowableDuringHandleException(ex, ex2);
/* 22:   */       }
/* 23:   */     }
/* 24:   */     catch (Error ex)
/* 25:   */     {
/* 26:   */       try
/* 27:   */       {
/* 28:62 */         handleExecuteError(ex);
/* 29:   */       }
/* 30:   */       catch (Throwable ex2)
/* 31:   */       {
/* 32:66 */         handleThrowableDuringHandleError(ex, ex2);
/* 33:   */       }
/* 34:   */     }
/* 35:   */     finally
/* 36:   */     {
/* 37:71 */       postExecuteTeardown();
/* 38:   */     }
/* 39:   */   }
/* 40:   */   
/* 41:   */   protected void preExecuteSetup() {}
/* 42:   */   
/* 43:   */   protected abstract void execute();
/* 44:   */   
/* 45:   */   protected void postExecuteTeardown() {}
/* 46:   */   
/* 47:   */   protected abstract void handleExecuteException(Exception paramException);
/* 48:   */   
/* 49:   */   protected abstract void handleExecuteError(Error paramError);
/* 50:   */   
/* 51:   */   protected void handleThrowableDuringHandleException(Exception originalException, Throwable ex) {}
/* 52:   */   
/* 53:   */   protected void handleThrowableDuringHandleError(Error originalError, Throwable ex) {}
/* 54:   */ }


/* Location:           C:\Users\Administrator\Desktop\Intellishare Deployment\intellishare-8.9.1\intellishare\WEB-INF\lib\i2-java-utils-1.1.5.jar
 * Qualified Name:     com.i2group.utils.exception.RunnableWithErrorExceptionHandlers
 * JD-Core Version:    0.7.0.1
 */