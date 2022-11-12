package oop.y2022.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import oop.y2022.mock.contact.ContactInfo;
import oop.y2022.mock.contact.ContactType;
import oop.y2022.mock.contact.IContactInfoService;
import oop.y2022.mock.user.IUserInfoService;
import oop.y2022.mock.user.UserInfo;

/**
 * Сервис уведомлений.
 * Основная задача запросить данные требуемого пользователя и список его контактов. */
public class NotificationService {

    private final IUserInfoService userInfoService;
    private final IContactInfoService contactInfoService;

    public NotificationService(
            IUserInfoService userInfoService,
            IContactInfoService contactInfoService
    ) {
        this.userInfoService = userInfoService;
        this.contactInfoService = contactInfoService;
    }

    /** При тестировании будет сложно просчитать все комбинации данных UserInfoService и ContactInfoService */
    public NotificationInfo getNotification(UUID userId) {
        Optional<UserInfo> userInfo = userInfoService.findUserInfo(userId);
        if (!userInfo.isPresent()) {
            throw new IllegalArgumentException("User %s not found");
        }

        List<ContactInfo> contactInfoList = new ArrayList<>();
        try {
            contactInfoService.findContactInfo(userId, ContactType.PRIMARY)
                    .ifPresent(contactInfoList::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            contactInfoService.findContactInfo(userId, ContactType.ADDITIONAL)
                    .ifPresent(contactInfoList::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new NotificationInfo(userInfo.get(), contactInfoList);
    }
}
