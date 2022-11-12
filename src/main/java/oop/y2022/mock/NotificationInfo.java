package oop.y2022.mock;

import java.util.List;
import oop.y2022.mock.contact.ContactInfo;
import oop.y2022.mock.user.UserInfo;

public class NotificationInfo {
    private final UserInfo userInfo;
    private final List<ContactInfo> contactInfoList;

    public NotificationInfo(
            UserInfo userInfo,
            List<ContactInfo> contactInfoList
    ) {
        this.userInfo = userInfo;
        this.contactInfoList = contactInfoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<ContactInfo> getContactInfoList() {
        return contactInfoList;
    }
}
