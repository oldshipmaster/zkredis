/*
 * @(#)AnnotationTypeMismatchException.java	1.5 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.lang.annotation;
import java.lang.reflect.Method;

/**
 * Thrown to indicate that a program has attempted to access an element of
 * an annotation whose type has changed after the annotation was compiled
 * (or serialized).
 *
 * @author  Josh Bloch
 * @since 1.5
 */
public class AnnotationTypeMismatchException extends RuntimeException {
    /**
     * The <tt>Method</tt> object for the annotation element.
     */
    private final Method element;

    /**
     * The (erroneous) type of data found in the annotation.  This string
     * may, but is not required to, contain the value as well.  The exact
     * format of the string is unspecified.
     */
    private final String foundType;

    /**
     * Constructs an AnnotationTypeMismatchException for the specified
     * annotation type element and found data type.
     *
     * @param element the <tt>Method</tt> object for the annotation element
     * @param foundType the (erroneous) type of data found in the annotation.
     *        This string may, but is not required to, contain the value
     *        as well.  The exact format of the string is unspecified.
     */
    public AnnotationTypeMismatchException(Method element, String foundType) {
        super("Incorrectly typed data found for annotation element " + element
              + " (Found data of type " + foundType + ")");
        this.element = element;
        this.foundType = foundType;
    }

    /**
     * Returns the <tt>Method</tt> object for the incorrectly typed element.
     *
     * @return the <tt>Method</tt> object for the incorrectly typed element
     */
    public Method element() {
        return this.element;
    }

    /**
     * Returns the type of data found in the incorrectly typed element.
     * The returned string may, but is not required to, contain the value
     * as well.  The exact format of the string is unspecified.
     *
     * @return the type of data found in the incorrectly typed element
     */
    public String foundType() {
        return this.foundType;
    }
}
