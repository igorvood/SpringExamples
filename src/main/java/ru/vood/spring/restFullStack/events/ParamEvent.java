package ru.vood.spring.restFullStack.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParamEvent {
    private Date dateEvent;
    private Object source;
}
