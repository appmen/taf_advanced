package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Widget {
    public String widgetName;
    public int widgetId;
    public String widgetType;
    public WidgetSize widgetSize;
    public WidgetPosition widgetPosition;
    public WidgetOptions widgetOptions;

    public Widget(){}
}
