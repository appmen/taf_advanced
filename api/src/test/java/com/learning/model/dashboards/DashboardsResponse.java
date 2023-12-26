package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DashboardsResponse {
    public List<Content> content;
    public Page page;

    public DashboardsResponse(){}
}






