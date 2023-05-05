package com.cbr.utils;

import com.cbr.App;
import com.cbr.exception.PluginException;
import com.cbr.models.DataList;
import com.cbr.models.FixedInvoice;
import com.cbr.plugin.PluginManager;
import com.cbr.view.MainView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
public class AppSettings {
    private String dataStorePath;
    private String dataStoreMode;
    private List<String> plugins;
    private Map<String, String> additionalSettings;

    private static volatile AppSettings instance;

    private AppSettings(){
    }

    public void toDefault(){
        this.dataStorePath = "/assets/data/json";
        this.dataStoreMode = "JSON";
        this.plugins = new ArrayList<>();
        this.additionalSettings = new HashMap<>();
    }

    public void updateSettings(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        prettyPrinter.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        mapper.setDefaultPrettyPrinter(prettyPrinter);
        ObjectWriter writer = mapper.writer(prettyPrinter);

        try{
            String jsonDataString = writer.writeValueAsString(getInstance());
            Files.write(Paths.get("", "settings.json"), jsonDataString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AppSettings getInstance() {
        if (instance == null) {
            synchronized (AppSettings.class) {
                if (instance == null) {
                    Path invoicesPath = Paths.get("", "settings.json");
                    if (Files.exists(invoicesPath)) {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            String invoicesJson = new String(Files.readAllBytes(invoicesPath));
                            instance = mapper.readValue(invoicesJson, new TypeReference<AppSettings>() {});
                            PluginManager.getInstance().init();
                            return instance;
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("failed to read file");
                        }

                    }
                    instance = new AppSettings();
                    instance.toDefault();
                }
            }
        }
        return instance;
    }

    public void addPlugin(String pluginPath){
        this.getPlugins().add(pluginPath);
    }

}
