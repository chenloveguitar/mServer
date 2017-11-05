/*  1:   */ package com.magicmoble.luzhouapp.json.core;
import com.magicmoble.luzhouapp.json.status.StatusObject;
/*  4:   */ 
/*  5:   */ public class AbstractJsonObject
/*  6:   */ {
/*  7:   */   private String code;
/*  8:   */   private String msg;
/*  9:   */   
/* 10:   */   public String getCode()
/* 11:   */   {
/* 12:18 */     return this.code;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setCode(String code)
/* 16:   */   {
/* 17:22 */     this.code = code;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getMsg()
/* 21:   */   {
/* 22:27 */     return this.msg;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setMsg(String msg)
/* 26:   */   {
/* 27:31 */     this.msg = msg;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setContent(String code, String msg)
/* 31:   */   {
/* 32:35 */     this.code = code;
/* 33:36 */     this.msg = msg;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setStatusObject(StatusObject statusObject)
/* 37:   */   {
/* 38:40 */     this.code = statusObject.getCode();
/* 39:41 */     this.msg = statusObject.getMsg();
/* 40:   */   }
/* 41:   */ }


/* Location:           F:\新建文件夹 (5)\classes\
 * Qualified Name:     cn.xinxing.json.core.AbstractJsonObject
 * JD-Core Version:    0.7.0.1
 */