package com.fsck.k9m

import com.fsck.k9m.backends.backendsModule
import com.fsck.k9m.controller.ControllerExtension
import com.fsck.k9m.crypto.EncryptionExtractor
import com.fsck.k9m.crypto.openpgp.OpenPgpEncryptionExtractor
import com.fsck.k9m.external.BroadcastSenderListener
import com.fsck.k9m.external.externalModule
import com.fsck.k9m.notification.notificationModule
import com.fsck.k9m.preferences.K9StoragePersister
import com.fsck.k9m.preferences.StoragePersister
import com.fsck.k9m.resources.resourcesModule
import com.fsck.k9m.storage.storageModule
import com.fsck.k9m.widget.list.MessageListWidgetUpdateListener
import com.fsck.k9m.widget.list.messageListWidgetModule
import com.fsck.k9m.widget.unread.UnreadWidgetUpdateListener
import com.fsck.k9m.widget.unread.unreadWidgetModule
import org.koin.dsl.module.applicationContext

private val mainAppModule = applicationContext {
    bean { App.appConfig }
    bean { MessagingListenerProvider(
            listOf(
                    get<UnreadWidgetUpdateListener>(),
                    get<MessageListWidgetUpdateListener>(),
                    get<BroadcastSenderListener>()
            ))
    }
    bean("controllerExtensions") { emptyList<ControllerExtension>() }
    bean { OpenPgpEncryptionExtractor.newInstance() as EncryptionExtractor }
    bean { K9StoragePersister(get()) as StoragePersister }
}

val appModules = listOf(
        mainAppModule,
        externalModule,
        messageListWidgetModule,
        unreadWidgetModule,
        notificationModule,
        resourcesModule,
        backendsModule,
        storageModule
)
