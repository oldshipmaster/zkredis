/*
 * @(#)file      SnmpMibEntry.java
 * @(#)author    Sun Microsystems, Inc.
 * @(#)version   1.19
 * @(#)date      09/10/11
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.jmx.snmp.agent;

// java imports
// 
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Enumeration;

// jmx imports
//
import com.sun.jmx.snmp.SnmpValue;
import com.sun.jmx.snmp.SnmpVarBind;
import com.sun.jmx.snmp.SnmpStatusException;
import com.sun.jmx.snmp.agent.SnmpMibOid;
import com.sun.jmx.snmp.agent.SnmpMibNode;

/**
 * Represents a node in an SNMP MIB which corresponds to a table entry
 * meta node.
 * <P>
 * This class is used by the class generated by <CODE>mibgen</CODE>.
 * You should not need to use this class directly.
 *
 * <p><b>This API is a Sun Microsystems internal API  and is subject 
 * to change without notice.</b></p>
 * @version     4.3     10/29/99
 * @author      Sun Microsystems, Inc
 */

public abstract class SnmpMibEntry extends SnmpMibNode 
    implements Serializable {

    /**
     * Tells whether the given arc identifies a variable (scalar object) in 
     * this entry.
     *
     * @param arc An OID arc.
     *
     * @return <CODE>true</CODE> if `arc' leads to a variable.
     */
    public abstract boolean isVariable(long arc);

    /**
     * Tells whether the given arc identifies a readable scalar object in 
     * this entry.
     *
     * @param arc An OID arc.
     *
     * @return <CODE>true</CODE> if `arc' leads to a readable variable.
     */
    public abstract boolean isReadable(long arc);

    /**
     * Get the next OID arc corresponding to a readable scalar variable.
     *
     */
    public long getNextVarId(long id, Object userData)  
	throws SnmpStatusException {
	long nextvar = super.getNextVarId(id,userData);
	while (!isReadable(nextvar)) 
	    nextvar = super.getNextVarId(nextvar,userData);
	return nextvar;
    }

    /**
     * Checks whether the given OID arc identifies a variable (columnar 
     * object).
     *
     * @param userData A contextual object containing user-data.
     *        This object is allocated through the <code>
     *        {@link com.sun.jmx.snmp.agent.SnmpUserDataFactory}</code>
     *        for each incoming SNMP request.
     *
     * @exception If the given `arc' does not identify any variable in this
     *    group, throws an SnmpStatusException.
     */
    public void validateVarId(long arc, Object userData) 
	throws SnmpStatusException {
	if (isVariable(arc) == false) throw noSuchNameException;
    }

    /**
     * Generic handling of the <CODE>get</CODE> operation.
     * <p>The actual implementation of this method will be generated
     * by mibgen. Usually, this implementation only delegates the
     * job to some other provided runtime class, which knows how to
     * access the MBean. The current toolkit thus provides two 
     * implementations:
     * <ul><li>The standard implementation will directly access the
     *         MBean through a java reference,</li>
     *     <li>The generic implementation will access the MBean through
     *         the MBean server.</li>
     * </ul>
     * <p>Both implementations rely upon specific - and distinct, set of
     * mibgen generated methods.
     * <p> You can override this method if you need to implement some
     * specific policies for minimizing the accesses made to some remote
     * underlying resources.
     * <p>
     *
     * @param req   The sub-request that must be handled by this node.
     *
     * @param depth The depth reached in the OID tree.
     *
     * @exception SnmpStatusException An error occurred while accessing 
     *  the MIB node.
     */
    abstract public void get(SnmpMibSubRequest req, int depth) 
	throws SnmpStatusException;

    /**
     * Generic handling of the <CODE>set</CODE> operation.
     * <p>The actual implementation of this method will be generated
     * by mibgen. Usually, this implementation only delegates the
     * job to some other provided runtime class, which knows how to
     * access the MBean. The current toolkit thus provides two 
     * implementations:
     * <ul><li>The standard implementation will directly access the
     *         MBean through a java reference,</li>
     *     <li>The generic implementation will access the MBean through
     *         the MBean server.</li>
     * </ul>
     * <p>Both implementations rely upon specific - and distinct, set of
     * mibgen generated methods.
     * <p> You can override this method if you need to implement some
     * specific policies for minimizing the accesses made to some remote
     * underlying resources.
     * <p>
     *
     * @param req   The sub-request that must be handled by this node.
     *
     * @param depth The depth reached in the OID tree.
     *
     * @exception SnmpStatusException An error occurred while accessing 
     *  the MIB node.
     */
    abstract public void set(SnmpMibSubRequest req, int depth) 
	throws SnmpStatusException;

    /**
     * Generic handling of the <CODE>check</CODE> operation.
     *
     * <p>The actual implementation of this method will be generated
     * by mibgen. Usually, this implementation only delegates the
     * job to some other provided runtime class, which knows how to
     * access the MBean. The current toolkit thus provides two 
     * implementations:
     * <ul><li>The standard implementation will directly access the
     *         MBean through a java reference,</li>
     *     <li>The generic implementation will access the MBean through
     *         the MBean server.</li>
     * </ul>
     * <p>Both implementations rely upon specific - and distinct, set of
     * mibgen generated methods.
     * <p> You can override this method if you need to implement some
     * specific policies for minimizing the accesses made to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different values provided in the varbind list.
     * <p>
     *
     * @param req   The sub-request that must be handled by this node.
     *
     * @param depth The depth reached in the OID tree.
     *
     * @exception SnmpStatusException An error occurred while accessing 
     *  the MIB node.
     */
    abstract public void check(SnmpMibSubRequest req, int depth) 
	throws SnmpStatusException;

}
