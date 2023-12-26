package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Content {
    public String owner;
    public int id;
    public String name;
    public List<Widget> widgets;
    public String description;

    public Content(){}
}
