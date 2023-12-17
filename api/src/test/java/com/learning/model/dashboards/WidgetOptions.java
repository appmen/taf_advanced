package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WidgetOptions {
    public String timeline;
    public boolean zoom;
    public String viewMode;
    public boolean latest;
    public boolean includeMethods;
    public String launchNameFilter;

    public WidgetOptions(){}
}
