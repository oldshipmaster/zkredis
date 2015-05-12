/*
 * @(#)file      JDMSecurityDefs.java
 * @(#)author    Sun Microsystems, Inc.
 * @(#)version   4.8
 * @(#)date      09/10/11
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */


/* Generated By:JJTree: Do not edit this line. JDMSecurityDefs.java */

package com.sun.jmx.snmp.IPAcl;

/** 
 * @version     4.8     11/17/05 
 * @author      Sun Microsystems, Inc. 
 */ 
class JDMSecurityDefs extends SimpleNode {
  JDMSecurityDefs(int id) {
    super(id);
  }

  JDMSecurityDefs(Parser p, int id) {
    super(p, id);
  }

  public static Node jjtCreate(int id) {
      return new JDMSecurityDefs(id);
  }

  public static Node jjtCreate(Parser p, int id) {
      return new JDMSecurityDefs(p, id);
  }
}