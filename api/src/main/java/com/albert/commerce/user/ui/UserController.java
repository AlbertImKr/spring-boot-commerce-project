package com.albert.commerce.user.ui;

import static com.albert.commerce.common.units.BusinessLinks.USER_INFO_RESPONSE_LINKS;

import com.albert.commerce.user.command.application.UserProfileRequest;
import com.albert.commerce.user.command.application.UserService;
import com.albert.commerce.user.query.application.UserInfoResponse;
import com.albert.commerce.user.query.domain.UserQueryDao;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserQueryDao userQueryDao;
    private final UserService userService;


    @GetMapping("/")
    public OAuth2User mainPage(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return oAuth2User;
    }


    @GetMapping("/users/profile")
    public UserInfoResponse getUserInfo(Principal principal) {
        String email = principal.getName();
        UserInfoResponse userInfoResponse = userQueryDao.findUserProfileByEmail(email);
        userInfoResponse.add(USER_INFO_RESPONSE_LINKS);
        return userInfoResponse;
    }

    @PutMapping("/users/profile")
    public UserInfoResponse updateUserInfo(Principal principal,
            @RequestBody UserProfileRequest userProfileRequest) {
        String email = principal.getName();
        UserInfoResponse userInfoResponse = userService.updateUserInfo(email,
                userProfileRequest);
        userInfoResponse.add(USER_INFO_RESPONSE_LINKS);
        return userInfoResponse;
    }
}


