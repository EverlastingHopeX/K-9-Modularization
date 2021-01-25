package com.fsck.k9m_m.ui.messageview;


import com.fsck.k9m_m.mailstore.AttachmentViewInfo;


interface AttachmentViewCallback {
    void onViewAttachment(AttachmentViewInfo attachment);
    void onSaveAttachment(AttachmentViewInfo attachment);
}
