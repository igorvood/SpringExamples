package ru.vood.spring.restFullStack.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static ru.vood.spring.restFullStack.consts.Const.GENERATOR_ID_PACKAGE;
import static ru.vood.spring.restFullStack.consts.Const.SCHEMA;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USR", schema = SCHEMA)
public class UsrEntity implements Comparable<UsrEntity> {

    @Id
    @GenericGenerator(name = "UsrSequence", strategy = GENERATOR_ID_PACKAGE + "UsrSequence")
    @GeneratedValue(generator = "UsrSequence")
    @Column(name = "usr_key", nullable = false)
    private Long id;

    @Basic
    @Column(name = "usr_last_name", length = 150, nullable = false)
    private String lastName;

    @Basic
    @Column(name = "usr_first_name", length = 150)
    private String firstName;

    @Basic
    @Column(name = "usr_middle_name", length = 80)
    private String middleName;

    @Basic
    @Column(name = "usr_password", length = 200, nullable = false)
    private String usrPassword;

    @Basic
    @Column(name = "usr_login", length = 256)
    private String usrLogin;

    @Override
    public int compareTo(UsrEntity o) {
        return id.compareTo(o.getId());
    }

}
