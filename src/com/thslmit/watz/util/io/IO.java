package com.thslmit.watz.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copyLarge;

/* 
 * TODO: Implement the Apache commons library for
 * pipeline processing of files.
 */

public class IO {
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	public static void copy(InputStream in, OutputStream out) throws IOException {
		try {
			copyLarge(in, out, new byte[DEFAULT_BUFFER_SIZE]);
		} finally {
			try {
				closeQuietly(in);
			} finally {
				closeQuietly(out);
			}
		}
	}
	
	public static void copy(Reader r, Writer w) throws IOException {
		try {
			copyLarge(r, w, new char[DEFAULT_BUFFER_SIZE]);
		} finally {
			try {
				closeQuietly(r);
			} finally {
				closeQuietly(w);
			}
		}
	}
	
	public static byte[] readBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}
}
