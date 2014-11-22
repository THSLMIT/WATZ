package com.thslmit.watz.util.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.logging.Logger;

public class Externalization {
	private static Logger log = Logger.getLogger(Externalization.class.getName());
	
	private static File getTempFile(String filename) {
		int index = filename.lastIndexOf('/');
		if (index == -1) filename = filename.substring(index);
		return new File(Directories.getTemporaryDirectory(), filename);
	}
	
	private static long getLastModified(String filename) throws IOException {
		long l_mod = Long.MAX_VALUE;
		URL url = Externalization.class.getClassLoader().getResource(filename);
		if (url != null) l_mod = url.openConnection().getLastModified();
		return l_mod;
	}
	
	private static File extractFile(String filename) throws IOException {
		File target = getTempFile(filename);
		long lmod_cp = getLastModified(filename);
		long lmod_t = target.lastModified();
		if (target.exists() && (lmod_cp/1000) == (lmod_t/1000))
			return target;
		
		InputStream in = Externalization.class.getClassLoader().getResourceAsStream(filename);
		if (in == null) return null;
		
		log.info("Extracting " + filename + " to " + target);
		FileOutputStream fos = new FileOutputStream(target);
		IO.copy(in, fos);
		if (target.setLastModified(lmod_cp))
			log.warning("Cannot set last modified for " + filename);
		
		return target;	
	}
	
	public static File extractFile(String filename, String tfix, TokenResolver tr) throws IOException {
		InputStream in = Externalization.class.getClassLoader().getResourceAsStream(filename);
		if (in == null) return null;
		
		File target = getTempFile(filename.replace(".", "_" + tfix + "."));
		log.info("Extracting " + filename + " to " + target);

		Reader r = new TokenReplaceReader(new InputStreamReader(in), tr); // Implement TokenRepalceReader files
		FileWriter w = new FileWriter(target);
		IO.copy(r, w);
		return target;
	}
}
