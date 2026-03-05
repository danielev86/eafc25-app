package com.redcatdev86.backend.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class IssueRow {

    private final IssueType table;
    private final int id;
    private final Map<String, Object> columns;

    public IssueRow(IssueType table, int id, Map<String, Object> columns) {
        this.table = table;
        this.id = id;
        this.columns = Collections.unmodifiableMap(new LinkedHashMap<>(columns));
    }

    public IssueType getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public Map<String, Object> getColumns() {
        return columns;
    }
}