package org.dew.pojo;
import lombok.Data;

@Data
public class Subject {
    private long id;
    private String number;
    private String name;
    private String teacher;
    private String classroom;
}
