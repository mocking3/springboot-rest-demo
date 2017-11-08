package net.runningcoder.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/10/31.
 */
@Data
@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String code;
    @ElementCollection
    @CollectionTable(name = "role_authority")
    @Column(name = "code")
    private List<String> authorityCodes;
    private Integer status;
    private String creator;
    private Date createTime;
    private String lastModifier;
    private Date lastModifyTime;

    public Status getStatus() {
        return Status.valueOf(status);
    }
}
