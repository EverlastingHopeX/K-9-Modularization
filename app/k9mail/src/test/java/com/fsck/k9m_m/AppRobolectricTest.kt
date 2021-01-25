package com.fsck.k9m_m

import android.app.Application
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * A Robolectric test that creates an instance of our [Application] class [App].
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = App::class)
abstract class AppRobolectricTest : AutoCloseKoinTest()
