package com.fsck.k9m_m

import com.fsck.k9m_m.account.accountModule
import com.fsck.k9m_m.activity.activityModule
import com.fsck.k9m_m.autodiscovery.autodiscoveryModule
import com.fsck.k9m_m.contacts.contactsModule
import com.fsck.k9m_m.fragment.fragmentModule
import com.fsck.k9m_m.ui.endtoend.endToEndUiModule
import com.fsck.k9m_m.ui.settings.settingsUiModule
import com.fsck.k9m_m.ui.uiModule
import com.fsck.k9m_m.view.viewModule

val uiModules = listOf(
        activityModule,
        uiModule,
        settingsUiModule,
        endToEndUiModule,
        fragmentModule,
        contactsModule,
        accountModule,
        autodiscoveryModule,
        viewModule
)
