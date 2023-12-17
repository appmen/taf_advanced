package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateDashboardBody {
    private String description;
    private String name;

    public CreateDashboardBody(){}
}
