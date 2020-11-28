package com.portfolio.twitter.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio")
public class Portfolio extends BaseEntity {
    @Id
    @Column(name = "idportfolio")
    private Integer id;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "twitter_user_name")
    private String twitterUserName;

    private String title;

    private String tittle;

    @Column(name = "id_portafolio")
    private Long idPortafolio;

}
