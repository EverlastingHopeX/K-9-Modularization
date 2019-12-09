package com.fsck.k9m.job

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator

class K9JobCreator(
        private val mailSyncJobManager: MailSyncJobManager,
        private val pusherRefreshJobManager: PusherRefreshJobManager
) : JobCreator {

    override fun create(tag: String): Job? {
        return when (tag) {
            MailSyncJob.TAG -> mailSyncJobManager.getJob()
            PusherRefreshJob.TAG -> pusherRefreshJobManager.getJob()
            else -> null
        }
    }

}