package com.fsck.k9m

import com.fsck.k9m.account.accountModule
import com.fsck.k9m.activity.activityModule
import com.fsck.k9m.autodiscovery.autodiscoveryModule
import com.fsck.k9m.contacts.contactsModule
import com.fsck.k9m.fragment.fragmentModule
import com.fsck.k9m.ui.endtoend.endToEndUiModule
import com.fsck.k9m.ui.settings.settingsUiModule
import com.fsck.k9m.ui.uiModule
import com.fsck.k9m.view.viewModule

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
