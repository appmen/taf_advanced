package com.learning.model.dashboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Page {
    public int number;
    public int size;
    public int totalElements;
    public int totalPages;

    public Page(){}
}
