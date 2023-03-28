package com.tebreca.simplemodelhost;

import com.tebreca.simplemodelhost.pojo.Table;

import java.util.ArrayList;
import java.util.List;

public class TableManager {

    private static int idSuggestion = 1;
    private static List<Table> tables = new ArrayList<>();

    public static int getId(int suggested) {
        if (suggested < 1 || tables.stream().anyMatch(table -> table.getId() == suggested)) {
            return getId(idSuggestion++);
        }
        return suggested;
    }

    public static void add(Table table) {
        if (!tables.contains(table))
            tables.add(table);
    }

    public static Table[] getAll() {
        return tables.toArray(Table[]::new);
    }
}
