package com.gusso.fashionblog_api.entities;


import com.gusso.fashionblog_api.enums.DesignCategory;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="postTable")
public class Post extends BaseEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private String title;

    @Column
    private String description;

//    @Column
//    private int likes;
//
//    @Column
//    private int unlikes;

    @Column
    @Enumerated(EnumType.STRING)
    private DesignCategory designCategory;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<UserEngagement> userEngagement = new ArrayList<>();
}
