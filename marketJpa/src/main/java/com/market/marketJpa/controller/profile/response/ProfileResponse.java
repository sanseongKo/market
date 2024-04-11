package com.market.marketJpa.controller.profile.response;

import com.market.marketJpa.vo.user.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProfileResponse {
    private String nickname;
    private String name;
    private LocalDate birth;
    private String phoneNumber;
    private Long point;
    private int registeredGoods;
    private String profileImg;
    private boolean registeredEmailStatus;
    private boolean tradeChatStatus;
    private boolean noticeStatus;

    @Builder
    public ProfileResponse(
            String nickname, String name, LocalDate birth,
            String phoneNumber, Long point, int registeredGoods,
            String profileImg, boolean registeredEmailStatus, boolean tradeChatStatus,
            boolean noticeStatus
    ) {
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.registeredGoods = registeredGoods;
        this.profileImg = profileImg;
        this.registeredEmailStatus = registeredEmailStatus;
        this.tradeChatStatus = tradeChatStatus;
        this.noticeStatus = noticeStatus;
    }

    public static ProfileResponse of(UserProfile userProfile) {
        return ProfileResponse.builder()
                .nickname(userProfile.getNickname())
                .name(userProfile.getName())
                .birth(userProfile.getBirth())
                .phoneNumber(userProfile.getPhoneNumber())
                .point(userProfile.getPoint())
                .registeredGoods(userProfile.getProductMappers().size())
                .tradeChatStatus(userProfile.isTradeChatStatus())
                .noticeStatus(userProfile.isNoticeStatus())
                .build();
    }
}
