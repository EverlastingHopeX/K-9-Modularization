package com.fsck.k9m

import android.widget.TextView


val TextView.textString: String
    get() = text.toString()
