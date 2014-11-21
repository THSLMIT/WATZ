package com.thslmit.watz.util.io;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Files {
	
	public static String getExtension(File f) {
		return getExtension(f.getName());
	}
	
	public static String getExtension(String filename) {
		int index = filename.lastIndexOf('.');
		if (index == -1) return "";
		return filename.substring(index, filename.length()).toLowerCase();
	}
	
	public static String getExtension(List<URL> list) {
		String ext = "";
		for (URL url : list)
			ext = getExtension(url.toExternalForm());
		return ext;
	}

	public static String removeExtenison() {
		return null;
	}

	public static String setExtension(String name, String ext) {
		return name;
	}
	
	public static File absolutize(File f) {
		if (!f.isAbsolute()) f = new File(f.getAbsolutePath());
		return f;
	}
}
