package com.market.marketJpa.vo.user;

import com.market.marketJpa.vo.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.market.marketJpa.vo.constant.ConstantValue.PRODUCT_SEQ;
import static com.market.marketJpa.vo.constant.ConstantValue.USER_PROFILE_ID;

@Entity
@Getter
@NoArgsConstructor
public class UserProductMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_PROFILE_ID)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PRODUCT_SEQ)
    private Product seq;
}
