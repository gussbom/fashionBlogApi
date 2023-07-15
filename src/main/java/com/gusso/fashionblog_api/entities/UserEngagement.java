package com.gusso.fashionblog_api.entities;

import com.gusso.fashionblog_api.enums.Reaction;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="userEngagementTable")
public class UserEngagement extends BaseEntity implements Serializable {

    @Column
    @Enumerated(EnumType.STRING)
    private Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "post_title", referencedColumnName = "title")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
