package ru.vood.spring.restFullStack.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsrDTO implements Comparable<UsrDTO> {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    @Override
    public int compareTo(UsrDTO o) {
        return id.compareTo(o.getId());
    }
}
