/*
 * @(#)BadStringOperationException.java	4.17 05/11/17
 * 
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.management;

/**
 * Thrown when an invalid string operation is passed
 * to a method for constructing a query.
 *
 * @since 1.5
 */
public class BadStringOperationException extends Exception   { 


    /* Serial version */
    private static final long serialVersionUID = 7802201238441662100L;

    /**
     * @serial The description of the operation that originated this exception
     */
    private String op;

    /**
     * Constructs a <CODE>BadStringOperationException</CODE> with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public BadStringOperationException(String message) { 
	this.op = message;
    } 
 
   
    /**
     * Returns the string representing the object.
     */
    public String toString()  { 
	return "BadStringOperationException: " + op;
    } 
      
 }
