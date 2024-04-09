package com.market.marketJpa.vo.user;

import com.market.marketJpa.vo.AuditingEntity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.market.marketJpa.vo.image.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.market.marketJpa.vo.constant.ConstantValue.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfileId;

    private String name;

    private String city;

    private String dong;

    private String gu;

    private LocalDate birth;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String nickname;

    @OneToOne
    @JoinColumn(name = USER_ID)
    private Users user;

    @OneToOne
    @JoinColumn(name = IMAGE_ID)
    private Image Image;

    @OneToMany(mappedBy = USER_PRODUCT_MAPPER)
    private List<UserProductMapper> productMappers;

    @Builder
    public UserProfile(String name, String city, String dong, String gu, LocalDate birth, String phoneNumber, String nickname, Image image, Users users) {
        this.name = name;
        this.city = city;
        this.dong = dong;
        this.gu = gu;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.user = users;
        this.productMappers = new LinkedList<>();
        Image = image;
    }
}
