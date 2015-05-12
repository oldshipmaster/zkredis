/* 
 * @(#)ProxyInputStream.java	1.4 05/11/17
 * 
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */ 

package com.sun.jmx.remote.internal;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import org.omg.CORBA.Any;
import org.omg.CORBA.Context;
import org.omg.CORBA.NO_IMPLEMENT;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Principal;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.BoxedValueHelper;
import org.omg.CORBA_2_3.portable.InputStream;

public class ProxyInputStream extends org.omg.CORBA_2_3.portable.InputStream {
    public ProxyInputStream(org.omg.CORBA.portable.InputStream in) {
	this.in = in;
    }

    public boolean read_boolean() {
	return in.read_boolean();
    }

    public char read_char() {
	return in.read_char();
    }

    public char read_wchar() {
	return in.read_wchar();
    }

    public byte read_octet() {
	return in.read_octet();
    }

    public short read_short() {
	return in.read_short();
    }

    public short read_ushort() {
	return in.read_ushort();
    }

    public int read_long() {
	return in.read_long();
    }

    public int read_ulong() {
	return in.read_ulong();
    }

    public long read_longlong() {
	return in.read_longlong();
    }

    public long read_ulonglong() {
	return in.read_ulonglong();
    }

    public float read_float() {
	return in.read_float();
    }

    public double read_double() {
	return in.read_double();
    }

    public String read_string() {
	return in.read_string();
    }

    public String read_wstring() {
	return in.read_wstring();
    }

    public void read_boolean_array(boolean[] value, int offset, int length) {
	in.read_boolean_array(value, offset, length);
    }

    public void read_char_array(char[] value, int offset, int length) {
	in.read_char_array(value, offset, length);
    }

    public void read_wchar_array(char[] value, int offset, int length) {
	in.read_wchar_array(value, offset, length);
    }

    public void read_octet_array(byte[] value, int offset, int length) {
	in.read_octet_array(value, offset, length);
    }

    public void read_short_array(short[] value, int offset, int length) {
	in.read_short_array(value, offset, length);
    }

    public void read_ushort_array(short[] value, int offset, int length) {
	in.read_ushort_array(value, offset, length);
    }

    public void read_long_array(int[] value, int offset, int length) {
	in.read_long_array(value, offset, length);
    }

    public void read_ulong_array(int[] value, int offset, int length) {
	in.read_ulong_array(value, offset, length);
    }

    public void read_longlong_array(long[] value, int offset, int length) {
	in.read_longlong_array(value, offset, length);
    }

    public void read_ulonglong_array(long[] value, int offset, int length) {
	in.read_ulonglong_array(value, offset, length);
    }

    public void read_float_array(float[] value, int offset, int length) {
	in.read_float_array(value, offset, length);
    }

    public void read_double_array(double[] value, int offset, int length) {
	in.read_double_array(value, offset, length);
    }

    public org.omg.CORBA.Object read_Object() {
	return in.read_Object();
    }

    public TypeCode read_TypeCode() {
	return in.read_TypeCode();
    }

    public Any read_any() {
	return in.read_any();
    }

    public Principal read_Principal() {
	return in.read_Principal();
    }

    public int read() throws IOException {
	return in.read();
    }

    public BigDecimal read_fixed() {
	return in.read_fixed();
    }

    public Context read_Context() {
	return in.read_Context();
    }

    public org.omg.CORBA.Object read_Object(java.lang.Class clz) {
	return in.read_Object(clz);
    }

    public ORB orb() {
	return in.orb();
    }

    public Serializable read_value() { 
	return narrow().read_value();
    }

    public Serializable read_value(Class clz) { 
	return narrow().read_value(clz);
    }
 
    public Serializable read_value(BoxedValueHelper factory) {
	return narrow().read_value(factory);
    }

    public Serializable read_value(String rep_id) {
	return narrow().read_value(rep_id);
    }

    public Serializable read_value(Serializable value) {
	return narrow().read_value(value);
    }

    public Object read_abstract_interface() {
	return narrow().read_abstract_interface();
    }
 
    public Object read_abstract_interface(Class clz) {
	return narrow().read_abstract_interface(clz);
    }

    protected org.omg.CORBA_2_3.portable.InputStream narrow() {
	if (in instanceof org.omg.CORBA_2_3.portable.InputStream)
	    return (org.omg.CORBA_2_3.portable.InputStream) in;
	throw new NO_IMPLEMENT();
    }

    public org.omg.CORBA.portable.InputStream getProxiedInputStream() {
	return in;
    }

    protected final org.omg.CORBA.portable.InputStream in;
}
