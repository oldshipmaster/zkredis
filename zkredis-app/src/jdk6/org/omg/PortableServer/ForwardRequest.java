package org.omg.PortableServer;


/**
* org/omg/PortableServer/ForwardRequest.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/org/omg/PortableServer/poa.idl
* Sunday, October 11, 2009 1:14:29 AM GMT-08:00
*/

public final class ForwardRequest extends org.omg.CORBA.UserException
{
  public org.omg.CORBA.Object forward_reference = null;

  public ForwardRequest ()
  {
    super(ForwardRequestHelper.id());
  } // ctor

  public ForwardRequest (org.omg.CORBA.Object _forward_reference)
  {
    super(ForwardRequestHelper.id());
    forward_reference = _forward_reference;
  } // ctor


  public ForwardRequest (String $reason, org.omg.CORBA.Object _forward_reference)
  {
    super(ForwardRequestHelper.id() + "  " + $reason);
    forward_reference = _forward_reference;
  } // ctor

} // class ForwardRequest
