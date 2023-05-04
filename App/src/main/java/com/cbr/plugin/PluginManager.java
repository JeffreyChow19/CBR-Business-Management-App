package com.cbr.plugin;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class PluginManager {
    @Getter
    private List<Plugin> plugins = new ArrayList<>();
    private Map<String, Plugin> loadedPlugins = new HashMap<>();

    public void loadPlugin(String jarFile, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, MalformedURLException, NoSuchMethodException, InvocationTargetException {
        URLClassLoader classLoader = new URLClassLoader(new URL[] { new URL("file:///D:/lessons/SEM 4/OOP/TUBES/backup/CBR-Business-Management-App/Plugin-System-2/target/Plugin-System-2-1.0.jar")});
        Class<?> pluginClass = classLoader.loadClass("com.cbr.BillingPlugin").asSubclass(Plugin.class);
        Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
        if (pluginClass != null) {
            System.out.println("Class found!");
            plugin.load();
            System.out.println(pluginClass.getName());
        } else {
            System.out.println("Class not found!");
        }
//        plugins.add(plugin);
//        loadedPlugins.put(pluginClass.getName(), plugin);
    }

    public void unloadPlugin(String className) {
        Plugin plugin = loadedPlugins.get(className);
        if (plugin != null) {
            plugins.remove(plugin);
            loadedPlugins.remove(className);
        }
    }

    public Plugin getPlugin(String className, int instanceId) {
        Plugin plugin = loadedPlugins.get(className);
        if (plugin != null) {
            return plugin;
        }
        return null;
    }
}
