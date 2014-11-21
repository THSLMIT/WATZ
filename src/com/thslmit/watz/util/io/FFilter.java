package com.thslmit.watz.util.io;

import java.io.File;
import java.io.FileFilter;

public class FFilter implements FileFilter {
	
	public boolean accept(File f) {
		return f.isFile();
	}
	
}
