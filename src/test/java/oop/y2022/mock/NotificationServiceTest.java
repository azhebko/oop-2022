package oop.y2022.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import oop.y2022.mock.contact.ContactInfo;
import oop.y2022.mock.contact.ContactType;
import oop.y2022.mock.contact.IContactInfoService;
import oop.y2022.mock.user.IUserInfoService;
import oop.y2022.mock.user.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Для тестирования логики NotificationService воспользуемся объектами-имитациями (Mock)
 * вместо настоящих UserInfoService, ContactInfoService. Всю сложную логику этих компонентов
 * заменим на требуемое поведение - в начале каждого тест метода сконфигурируем его.
 */
public class NotificationServiceTest {

    private static final UUID userID = UUID.fromString("792d9ff4-aa1d-4387-8f58-78afe30817d1");

    private final IUserInfoService userInfoService = mock(IUserInfoService.class);
    private final IContactInfoService contactInfoService = mock(IContactInfoService.class);
    private final NotificationService notificationService = new NotificationService(userInfoService, contactInfoService);

    @Test
    public void testWhenUserExistsAndContactsExist() {
        when(userInfoService.findUserInfo(userID)).thenReturn(Optional.of(new UserInfo("Петр Петров")));
        when(contactInfoService.findContactInfo(userID, ContactType.PRIMARY)).thenReturn(Optional.of(new ContactInfo("petrov@mail.ru", ContactType.PRIMARY)));
        when(contactInfoService.findContactInfo(userID, ContactType.ADDITIONAL)).thenReturn(Optional.of(new ContactInfo("petrov1@mail.ru", ContactType.ADDITIONAL)));

        NotificationInfo notification = notificationService.getNotification(userID);

        assertEquals("Петр Петров", notification.getUserInfo().getName());
        List<ContactInfo> actualContactInfoList = notification.getContactInfoList();

        assertEquals(2, actualContactInfoList.size());
    }

    @Test
    public void testWhenUserAbsentAndContactsExist() {
        when(userInfoService.findUserInfo(userID)).thenReturn(Optional.empty());
        when(contactInfoService.findContactInfo(userID, ContactType.PRIMARY)).thenReturn(Optional.of(new ContactInfo("petrov@mail.ru", ContactType.PRIMARY)));
        when(contactInfoService.findContactInfo(userID, ContactType.ADDITIONAL)).thenReturn(Optional.of(new ContactInfo("petrov1@mail.ru", ContactType.ADDITIONAL)));

        Assertions.assertThrows(Exception.class, () -> notificationService.getNotification(userID));
    }
}
