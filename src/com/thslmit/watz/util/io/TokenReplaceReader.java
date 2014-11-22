package com.thslmit.watz.util.io;

import java.io.PushbackReader;
import java.io.Reader;

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
}
