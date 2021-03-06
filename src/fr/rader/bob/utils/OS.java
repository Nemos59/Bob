package fr.rader.bob.utils;

import fr.rader.bob.BobSettings;
import org.lwjgl.LWJGLUtil;

public class OS {

    private static final int platformID;

    public static String getBobFolder() {
        if(BobSettings.getWorkingDirectory() == null) {
            if(getOS() != null) return System.getProperty("user.home") + "/.bob/";
            else return null;
        } else {
            return BobSettings.getWorkingDirectory();
        }
    }

    public static String getOS() {
        switch(platformID) {
            case LWJGLUtil.PLATFORM_LINUX: return "linux";
            case LWJGLUtil.PLATFORM_MACOSX: return "macos";
            case LWJGLUtil.PLATFORM_WINDOWS: return "windows";
            default: return null;
        }
    }

    public static String getMinecraftFolder() {
        switch(getOS()) {
            case "windows": return System.getenv("appdata") + "/.minecraft/";
            case "linux": return System.getProperty("user.home") + "/.minecraft/";
            case "macos": return System.getProperty("user.home") + "/Library/Application Support/minecraft/";
            default: return null;
        }
    }

    static {
        platformID = LWJGLUtil.getPlatform();
    }
}
