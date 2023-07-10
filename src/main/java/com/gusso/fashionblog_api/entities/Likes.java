package com.gusso.fashionblog_api.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Likes extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "comments_id")
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

}
