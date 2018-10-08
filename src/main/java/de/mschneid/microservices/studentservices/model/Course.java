package de.mschneid.microservices.studentservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    private String id;
    private String name;
    private String description;
    private List<String> steps;
}
