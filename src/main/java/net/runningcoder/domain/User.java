package net.runningcoder.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangmaocheng on 2017/10/31.
 */
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer status;
    private String creator;
    private Date createTime;
    private String lastModifier;
    private Date lastModifyTime;

    @ManyToOne
    private Role role;

    public Status getStatus() {
        return Status.valueOf(status);
    }
}
