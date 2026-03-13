package automation.util;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class KudoData {

    private final String from;
    private final String to;
    private final String category;
    private final String message;

    public KudoData(String from, String to, String category, String message) {
        this.from = from;
        this.to = to;
        this.category = category;
        this.message = message;
    }

    public static KudoData from(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0);
        return new KudoData(
            row.get("from"),
            row.get("to"),
            row.get("category"),
            row.get("message")
        );
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public String category() {
        return category;
    }

    public String message() {
        return message;
    }
}
