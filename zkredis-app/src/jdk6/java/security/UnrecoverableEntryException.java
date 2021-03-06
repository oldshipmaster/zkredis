/*
 * @(#)UnrecoverableEntryException.java	1.3 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.security;

/**
 * This exception is thrown if an entry in the keystore cannot be recovered.
 *
 * @version 1.3, 11/17/05
 *
 * @since 1.5
 */

public class UnrecoverableEntryException extends GeneralSecurityException {

    private static final long serialVersionUID = -4527142945246286535L;

    /**
     * Constructs an UnrecoverableEntryException with no detail message.
     */
    public UnrecoverableEntryException() {
	super();
    }

    /**
     * Constructs an UnrecoverableEntryException with the specified detail
     * message, which provides more information about why this exception
     * has been thrown.
     *
     * @param msg the detail message.
     */
   public UnrecoverableEntryException(String msg) {
       super(msg);
    }
}
