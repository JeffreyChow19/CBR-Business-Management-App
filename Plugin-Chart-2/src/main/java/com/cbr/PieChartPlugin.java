package com.cbr;

import com.cbr.plugin.Plugin;
import lombok.Getter;
import lombok.Setter;

public class PieChartPlugin implements Plugin{
    @Setter
    @Getter
    private Boolean status;

    public PieChartPlugin() {

    }
    public String getName(){
        return "com.cbr.PieChartPlugin";
    }
    public void load() {

    }
}
