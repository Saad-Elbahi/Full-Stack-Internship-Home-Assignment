package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private int id;
    private String Name;
    private String jobTitle;
    private double salary;
}