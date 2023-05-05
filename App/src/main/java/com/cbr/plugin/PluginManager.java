package com.cbr.plugin;

import com.cbr.exception.PluginException;
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

    private PluginManager() {
        this.plugins = new ArrayList<>();
    }

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }

    public void loadNewPlugin() throws MalformedURLException, PluginException {
        for (String jarFile : AppSettings.getInstance().getPlugins()) {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(jarFile).toURI().toURL()}, ClassLoader.getSystemClassLoader());
            pluginServiceLoader = ServiceLoader.load(Plugin.class, classLoader);
            for (Plugin p : pluginServiceLoader) {
                boolean exists = plugins.stream()
                        .map(Plugin::getClass)
                        .anyMatch(c -> c.getName().equals(p.getClass().getName()));
                // If an object of the same class already exists, don't add the new object
                if (!exists) {
                    System.out.println(p.getClass().getName());
                    plugins.add(p);
                    p.load();
                }
                else{
                    throw new PluginException();
                }
            }

        }
    }

    public void loadPlugin(){
        for (Plugin p : plugins){
            p.load();
            System.out.println("ajskkskjsdkj");
        }
    }
}


