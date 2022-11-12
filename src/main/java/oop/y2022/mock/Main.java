package oop.y2022.mock;

import java.util.UUID;
import oop.y2022.mock.contact.ContactInfo;
import oop.y2022.mock.contact.ContactInfoService;
import oop.y2022.mock.contact.IContactInfoService;
import oop.y2022.mock.user.IUserInfoService;
import oop.y2022.mock.user.UserInfoService;

public class Main {

    /** Тест-клиент */
    public static void main(String[] args) {
        IUserInfoService userInfoService = new UserInfoService();
        IContactInfoService contactInfoService = new ContactInfoService();
        NotificationService notificationService = new NotificationService(
                userInfoService, contactInfoService
        );

        NotificationInfo notification = notificationService
                .getNotification(UUID.fromString("236e246d-7929-427f-b388-366864d1d0a1"));

        System.out.println(notification.getUserInfo().getName());
        for (ContactInfo contactInfo : notification.getContactInfoList()) {
            System.out.println(contactInfo.getEmail());
        }
    }
}
