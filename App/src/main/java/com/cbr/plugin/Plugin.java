package com.cbr.plugin;


public interface Plugin {
    void load();
    String getName();

    Boolean getStatus();
    void setStatus(Boolean status);
}
