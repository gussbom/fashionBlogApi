package com.gusso.fashionblog_api.entities;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="commentTable")
public class Comment extends BaseEntity implements Serializable {

    @Column(length = 254)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_title", referencedColumnName = "title")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
