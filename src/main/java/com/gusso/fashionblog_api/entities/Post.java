package com.gusso.fashionblog_api.entities;


import com.gusso.fashionblog_api.enums.DesignCategories;
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
public class Posts extends BaseEntity {

    @Column(length = 25, nullable = false)
    private String imageUrl;

    private DesignCategories designCategory;

    @ManyToOne
    @JoinColumn(name = "Admin_id")
    private Administrator administrator;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();
}
