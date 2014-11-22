package com.thslmit.watz.util.sys;

public class Platform {
	public static boolean isLinux() {
        return getOperationSystem().contains("linux");
    }

    public static boolean isMac() {
        return getOperationSystem().contains("mac");
    }

    public static boolean isWindows() {
        return getOperationSystem().contains("windows");
    }

    public static String getPlatform() {
        return System.getProperty("os.name") + " " +
                System.getProperty("os.version") + " " +
                System.getProperty("os.arch");
    }

    public static boolean isJavaFX() {
        return System.getProperty("java.vendor").contains("Oracle") &&
                System.getProperty("java.version").compareTo("1.7.0_40") >= 0;
    }

    public static String getJava() {
        return System.getProperty("java.vendor") + " Java " + System.getProperty("java.version") + " (" + getBits() + "-bit)";
    }

    public static String getBits() {
        return System.getProperty("sun.arch.data.model");
    }

    public static boolean isCurrentAtLeastMinimumVersion(String currentVersion, String minimumVersion) {
        return currentVersion.compareTo(minimumVersion) >= 0;
    }

    private static String canonical(String value) {
        return value.toLowerCase().replaceAll("[\\\\/ ]", "_");
    }

    public static String getOperationSystem() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows"))
            return "windows";
        return canonical(osName);
    }

    public static String getArchitecture() {
        String osArch = System.getProperty("os.arch");
        if (osArch.endsWith("86"))
            return "x86";
        return canonical(osArch);
    }

    public static long getMaximumMemory() {
        return Runtime.getRuntime().maxMemory() / (1024 * 1000 /* to get 2^n numbers */);
    }
}
