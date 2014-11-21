package com.thslmit.watz.util.io;

import static java.io.File.separator;

import java.io.File;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class Directories {
	private static final Logger log = Logger.getLogger(Files.class.getName());
    private static final Preferences pref = Preferences.userNodeForPackage(Files.class);
	private static final String APP_DIR_PREF = "APP_DIR";
    private static final String TMP_DIR_PREF = "TMP_DIR";
    
    // TODO: Consider file type formats for the system and appen
    private static final String app_dir = pref.get(APP_DIR_PREF, System.getProperty("user.home")+separator+".rc");
    private static final String tmp_dir = pref.get(TMP_DIR_PREF, System.getProperty("java.io.tmpdir")+separator + "routeconverter" + (!isWindows() ? "-" + System.getProperty("user.name") : ""));
    
    public static File ensureDirectory(File directory) {
    	if (!directory.exists() && !directory.mkdirs()) {
    		// throw exception prompt here...
    	}
		return directory;
    }
    
    public static File ensureDirectory(String directory) {
    	return ensureDirectory(new File(directory));
    }
    
    public synchronized static File getTemporaryDirectory() {
        return ensureDirectory(tmp_dir);
    }

    public synchronized static File getApplicationDirectory() {
        return ensureDirectory(app_dir);
    }

    public synchronized static File getApplicationDirectory(String subDirectory) {
        return ensureDirectory(getApplicationDirectory() + separator + subDirectory);
    }
    
}