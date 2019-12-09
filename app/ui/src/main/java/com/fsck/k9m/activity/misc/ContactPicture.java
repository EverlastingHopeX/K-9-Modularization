package com.fsck.k9m.activity.misc;


import com.fsck.k9m.DI;
import com.fsck.k9m.contacts.ContactPictureLoader;


public class ContactPicture {

    public static ContactPictureLoader getContactPictureLoader() {
        return DI.get(ContactPictureLoader.class);
    }
}
