package com.gusso.fashionblog_api.entities;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Visitor extends BaseEntity{

    @Column(length = 25, nullable = false)
    private String email;

    @Column(length = 25, nullable = false)
    private String password;

    @Column(length = 25, nullable = false)
    private String username;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

}
