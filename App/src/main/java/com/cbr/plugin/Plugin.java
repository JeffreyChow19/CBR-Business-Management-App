package com.cbr.plugin;


import com.cbr.App;

public interface Plugin {
    void load();
    String getName();

    Boolean getStatus();
}
