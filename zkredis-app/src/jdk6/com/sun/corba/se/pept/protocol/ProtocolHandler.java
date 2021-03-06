/*
 * @(#)ProtocolHandler.java	1.13 05/11/17
 * 
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sun.corba.se.pept.protocol;

import com.sun.corba.se.pept.protocol.MessageMediator;

/**
 * <code>ProtocolHandler</code> is used to determine the
 * type of an incoming message.
 *
 * @author Harold Carr
 */
public interface ProtocolHandler
{
    // REVISIT - return type
    /**
     * This method determines the type of an incoming message and
     * dispatches it appropriately.
     *
     * For example, on the server side, it may find a 
     * {@link com.sun.corba.se.pept.protocol.ServerRequestDispatcher
     * ServerRequestDispatcher} to handle the request.  On the client-side
     * it may signal a waiting thread to handle a reply.
     *
     * @return deprecated
     */
    public boolean handleRequest(MessageMediator messageMediator);
}

// End of file.
