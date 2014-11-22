package com.thslmit.watz.util.io;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamBlock extends InputStream {
	private InputStream delegate;
	
	public InputStreamBlock(InputStream delegate) {
		this.delegate = delegate;
	}
	
	public int available() throws IOException {
		return delegate.available();
	}
	
	public synchronized void mark(int readlimit) {
		delegate.mark(readlimit);
	}
	
	public synchronized void reset() throws IOException {
		delegate.reset();
	}
	
	public boolean markSupported() {
		return delegate.markSupported();
	}
	
	public int read() throws IOException {
		return delegate.read();
	}
	
	public void closeInputStream() throws IOException {
		delegate.close();
	}
}
