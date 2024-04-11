package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.UserProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static com.market.marketJpa.vo.image.QImage.image;
import static com.market.marketJpa.vo.user.QUserProductMapper.*;
import static com.market.marketJpa.vo.user.QUserProfile.userProfile;
import static com.market.marketJpa.vo.user.QUsers.*;

@RequiredArgsConstructor
public class CustomUserProfileRepositoryImpl implements CustomUserProfileRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserProfile findByUserId(UUID userId) {
        return jpaQueryFactory.selectFrom(userProfile)
                .leftJoin(userProfile.user, users).fetchJoin()
                .leftJoin(userProfile.image, image).fetchJoin()
                .leftJoin(userProfile.productMappers, userProductMapper).fetchJoin()
                .where(userProfile.user.userId.eq(userId))
                .fetchOne();
    }
}
