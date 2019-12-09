package com.fsck.k9m.ui.endtoend

import androidx.lifecycle.ViewModel

internal class AutocryptKeyTransferViewModel(
        val autocryptSetupMessageLiveEvent: AutocryptSetupMessageLiveEvent,
        val autocryptSetupTransferLiveEvent: AutocryptSetupTransferLiveEvent) : ViewModel()
