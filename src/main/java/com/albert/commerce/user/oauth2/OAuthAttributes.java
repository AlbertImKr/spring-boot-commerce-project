package com.albert.commerce.user.oauth2;

import com.albert.commerce.user.EncryptionAlgorithm;
import com.albert.commerce.user.Role;
import com.albert.commerce.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder, EncryptionAlgorithm algorithm, Role role) {
        return User.builder()
                .nickname(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode("Aa1!"+ UUID.randomUUID()))
                .algorithm(algorithm)
                .role(role)
                .build();
    }
}
