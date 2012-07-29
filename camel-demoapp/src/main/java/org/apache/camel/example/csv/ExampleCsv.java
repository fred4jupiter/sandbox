package org.apache.camel.example.csv;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@CsvRecord(separator = ";", crlf = "UNIX", skipFirstLine = true, generateHeaderColumns = true)
public class ExampleCsv {

    @DataField(pos = 1)
    private int id;
    
    @DataField(pos = 2)
    private String content;
    
    @DataField(pos = 3)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("id", id);
        builder.append("content", content);
        builder.append("description", description);
        return builder.toString();
    }
    
}
