package ru.vood.spring.restFullStack.controller.restParameters;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateRequestParam {
    private List<User> userList;
    private List<AccessAndDate> accessAndDateList;
    private Boolean skipApprovals;
    private String justification;
    private Long creatorUser;

    @Data
    public class User {
        private Long userId;
        private Long employmentId;
        private Long accountId;
    }

    @Data
    public class AccessAndDate {
        private Long access;
        private Date dateAccess;
    }
}
