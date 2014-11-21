package com.thslmit.watz.util.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/* 
 * TODO: Implement the Apache commons library for
 * pipeline processing of files.
 */

public class IO {
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	public static void copy(InputStream in, OutputStream out) {
		try {
			copyLarge(in, out, new byte[DEFAULT_BUFFER_SIZE]);
		} finally {
		}
	}
	
	public static void copy(Reader r, Writer w) {
	}
	
	public static byte[] readBytes(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}
}
