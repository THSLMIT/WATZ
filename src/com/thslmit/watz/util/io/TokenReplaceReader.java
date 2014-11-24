package com.thslmit.watz.util.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.nio.CharBuffer;

public class TokenReplaceReader {
	
	private PushbackReader pbReader;
	private TokenResolver tr;
	private StringBuilder tnBuffer = new StringBuilder();
	private String tokenValue = null;
	private int tvalIndex = 0;
	
	public TokenReplaceReader(Reader source, TokenResolver resolver) {
		pbReader = new PushbackReader(source, 2);
		tr = resolver;
	}

	public int read(CharBuffer target) {
		throw new UnsupportedOperationException();
	}
	
	public int read() throws IOException {
		if (tokenValue != null) {
			if (tvalIndex < tokenValue.length())
				return tokenValue.charAt(tvalIndex++);
			if (tvalIndex == tokenValue.length()) {
				tokenValue = null;
				tvalIndex = 0;
			}
			
			int data = pbReader.read();
			if (data != '$') return data;
			
			data = pbReader.read();
			if (data != '{') {
				pbReader.unread(data);
				return '$';
			}
			tnBuffer.delete(0, tnBuffer.length());
			
			data = pbReader.read();
			while (data != '}') {
				tnBuffer.append((char) data);
				data = pbReader.read();
			}
			
			tokenValue = tr.resolveToken(tnBuffer.toString());
			if (tokenValue == null)
				tokenValue = "${" + tnBuffer.toString() + "}";
			
			return tokenValue.charAt(tvalIndex++);
		}
		return tokenValue.charAt(tvalIndex++);
	}
	
	public int read(char[] char_buff) throws IOException {
		return read(char_buff, 0, char_buff.length);
	}
	
	public int read(char[] char_buff, int off, int len) throws IOException {
		int char_read = -1;
		for (int i = 0; i < len; i++) {
			int nxt_char = read();
			if (nxt_char == -1) break;
			char_read++;
			char_buff[off + i] = (char) nxt_char;
		}
		
		return char_read;
	}
	
	public void close() throws IOException {
		pbReader.close();
	}
	
	public long skip(long n) {
		throw new UnsupportedOperationException();
	}
	
	public boolean ready() throws IOException {
		return pbReader.ready();
	}
	
	public boolean markSupported() {
		return false;
	}
	
	public void mark(int ra_limit) {
		throw new UnsupportedOperationException();
	}
	
	public void reset() {
		throw new UnsupportedOperationException();
	}
}
