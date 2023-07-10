package com.gusso.fashionblog_api.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Administrator extends BaseEntity {

    @Column(length = 25, nullable = false)
    private String email;

    @Column(length = 25, nullable = false)
    private String password;

    @Column(length = 25, nullable = false)
    private String username;

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();
}
