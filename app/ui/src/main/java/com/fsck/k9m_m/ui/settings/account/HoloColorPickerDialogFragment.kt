package com.fsck.k9m_m.ui.settings.account

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceDialogFragmentCompat
import android.view.LayoutInflater
import com.fsck.k9m_m.ui.R
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.larswerkman.colorpicker.ColorPicker

class HoloColorPickerDialogFragment : PreferenceDialogFragmentCompat() {
    private val holoColorPickerPreference: HoloColorPickerPreference
        get() = preference as HoloColorPickerPreference

    private lateinit var colorPicker: ColorPicker

    private var manager: SplitInstallManager? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        manager = SplitInstallManagerFactory.create(context)
        if (!manager?.getInstalledModules()?.contains(MODULE2_NAME)!!){
            return AlertDialog.Builder(requireContext())
                    .setTitle("Color Picker cannot install")
                    .setView(view)
                    .setPositiveButton("ok", null)
                    .create()
        }

        @SuppressLint("InflateParams")
        val view = LayoutInflater.from(context).inflate(R.layout.holo_color_picker_dialog, null)
        colorPicker = view.findViewById(R.id.color_picker)
        colorPicker.color = holoColorPickerPreference.color

        return AlertDialog.Builder(requireContext())
                .setTitle(R.string.color_picker_default_title)
                .setView(view)
                .setPositiveButton(R.string.okay_action, { _, _ -> updateColor(colorPicker.color) })
                .setNeutralButton(R.string.account_settings_color_none, { _, _ -> updateColor(COLOR_TRANSPARENT) })
                .setNegativeButton(R.string.cancel_action, null)
                .create()
    }

    override fun onDialogClosed(positiveResult: Boolean) = Unit

    private fun updateColor(pickedColor: Int) {
        val preference = holoColorPickerPreference
        if (preference.callChangeListener(pickedColor)) {
            preference.color = pickedColor
        }
    }


    companion object {
        private const val COLOR_TRANSPARENT = 0x00000000
        private const val MODULE2_NAME = "dynamicfeature2"
    }
}
