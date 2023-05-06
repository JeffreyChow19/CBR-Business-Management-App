package com.cbr;

import com.cbr.plugin.Plugin;
import lombok.Getter;
import lombok.Setter;

public class LineBarChartPlugin implements Plugin{
    @Setter
    @Getter
    private Boolean status;

    public LineBarChartPlugin() {

    }
    public String getName(){
        return "com.cbr.LineBarChartPlugin";
    }
    public void load() {

    }
}
