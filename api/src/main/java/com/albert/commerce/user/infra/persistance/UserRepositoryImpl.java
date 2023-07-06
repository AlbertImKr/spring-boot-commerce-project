package com.albert.commerce.user.infra.persistance;

import com.albert.commerce.user.command.application.dto.UserProfileRequest;
import com.albert.commerce.user.command.domain.QUser;
import com.albert.commerce.user.command.domain.User;
import com.albert.commerce.user.command.domain.UserRepository;
import com.albert.commerce.user.infra.persistance.imports.UserJpaRepository;
import com.albert.commerce.user.query.domain.UserDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository, UserDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> updateUserInfo(String email, UserProfileRequest userProfileRequest) {
        QUser user = QUser.user;
        jpaQueryFactory.update(user)
                .set(user.nickname, userProfileRequest.nickname())
                .set(user.address, userProfileRequest.address())
                .set(user.phoneNumber, userProfileRequest.phoneNumber())
                .set(user.dateOfBirth, userProfileRequest.dateOfBirth())
                .where(user.email.eq(email))
                .execute();
        return Optional.ofNullable(jpaQueryFactory.selectFrom(user)
                .where(user.email.eq(email))
                .fetchFirst());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
