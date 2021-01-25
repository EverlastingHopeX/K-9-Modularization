package com.fsck.k9m_m

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fsck.k9m_m.ui.endtoend.AutocryptKeyTransferActivity
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.Koin
import org.koin.log.PrintLogger
import org.koin.test.AutoCloseKoinTest
import org.koin.test.dryRun
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class)
class DependencyInjectionTest : AutoCloseKoinTest() {
    val lifecycleOwner = mock<LifecycleOwner> {
        on { lifecycle } doReturn mock<Lifecycle>()
    }

    @Test
    fun testDependencyTree() {
        Koin.logger = PrintLogger()

        dryRun {
            mapOf(
                    "lifecycleOwner" to lifecycleOwner,
                    "autocryptTransferView" to mock<AutocryptKeyTransferActivity>()
            )
        }
    }
}
