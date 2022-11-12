package oop.y2022.mock.user;

import java.util.Optional;
import java.util.UUID;

public interface IUserInfoService {

    Optional<UserInfo> findUserInfo(UUID userId);
}
