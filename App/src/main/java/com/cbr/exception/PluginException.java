package com.cbr.exception;

public class PluginException extends Exception {
    public PluginException(){
        super("Failed to load plugin");
    }
}
