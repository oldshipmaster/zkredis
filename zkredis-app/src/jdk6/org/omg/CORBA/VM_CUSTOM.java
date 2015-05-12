/*
 * @(#)VM_CUSTOM.java	1.10 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.omg.CORBA;

/** Defines the code used to represent a custom marshalled value type in
* a typecode.
* This is one of the possible results of the <code>type_modifier</code>
* method on the <code>TypeCode</code> interface.
* @see org.omg.CORBA.TypeCode
* @version 1.10 11/17/05
*/
public interface VM_CUSTOM {
    /** The value representing a custom marshalled value type in
    * a typecode.
    */
    final short value = (short) (1L);
}
