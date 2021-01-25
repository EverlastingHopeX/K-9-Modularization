package com.fsck.k9m_m.activity.misc;


import com.fsck.k9m_m.DI;
import com.fsck.k9m_m.contacts.ContactPictureLoader;


public class ContactPicture {

    public static ContactPictureLoader getContactPictureLoader() {
        return DI.get(ContactPictureLoader.class);
    }
}
