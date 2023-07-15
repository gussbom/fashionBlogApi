package com.gusso.fashionblog_api.entities;


import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comments extends BaseEntity{


    @Column(length = 254, nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrator administrator;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;
}
