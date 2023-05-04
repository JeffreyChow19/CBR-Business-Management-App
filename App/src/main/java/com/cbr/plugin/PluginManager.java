package com.cbr.plugin;

import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import lombok.Getter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class PluginManager {
    @Getter
    private List<Plugin> plugins;
    private ServiceLoader<Plugin> pluginServiceLoader;

    private static volatile PluginManager instance;

    private PluginManager(){
        this.plugins = new ArrayList<>();
    }

    public static PluginManager getInstance(){
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }
    public void loadPlugin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, NoSuchMethodException, InvocationTargetException {
        for (String jarFile : AppSettings.getInstance().getPlugins()){
            URLClassLoader classLoader = new URLClassLoader(new URL[] { new File(jarFile).toURI().toURL()}, ClassLoader.getSystemClassLoader());
            pluginServiceLoader = ServiceLoader.load(Plugin.class, classLoader);
            for (Plugin p : pluginServiceLoader){
                System.out.println("hehehe");
                p.load();
            }
//            Class<?> pluginClass = classLoader.loadClass("com.cbr.BillingPlugin").asSubclass(Plugin.class);
//            Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
//            if (pluginClass != null) {
//                System.out.println("Class found!");
//                plugin.load();
//                System.out.println(pluginClass.getName());
//            } else {
//                System.out.println("Class not found!");
//            }
        }
    }


//    public Plugin getPlugin(String className, int instanceId) {
//        Plugin plugin = loadedPlugins.get(className);
//        if (plugin != null) {
//            return plugin;
//        }
//        return null;
//    }
}
