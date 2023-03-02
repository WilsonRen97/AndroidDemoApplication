package com.wilsontryingapp2023.demoapplication

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference

class SettingsActivity : AppCompatActivity() {

    companion object {
        private val OPT_SOUND = "soundfx"
        private val OPT_IMAGE = "musicfx"

        fun getSoundFX(context: Context?): Boolean {
            // 第一個參數是key，代表我們從shared preference要取得的數據是哪個。
            // 第二個參數是default value，代表如果我們沒有儲存過這筆數據的話，
            // getSoundFX要return什麼值
            return PreferenceManager.getDefaultSharedPreferences(context!!).getBoolean(OPT_SOUND, true)
        }

        fun getImagePref(context: Context?): String {
            return PreferenceManager.getDefaultSharedPreferences(context!!).getString(OPT_IMAGE, "0")!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            val context = preferenceManager.context
            // same as java code: Context context = getPreferenceManager().getContext();
            val screen = preferenceManager.createPreferenceScreen(context)
            // same as java code: PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            val speed = SwitchPreference(context)
            speed.title = "音樂效果"
            speed.summaryOn = "音樂將會播放"
            speed.summaryOff = "音樂將不會播放"
            speed.isChecked = true
            speed.key = OPT_SOUND
            screen.addPreference(speed)

            preferenceScreen = screen
            // same as java code
            // screen.addPreference(speed);

            val imagePref = ListPreference(context)
            imagePref.title = "你要的圖片是？"
            // 在List Preference當中，entries與values都只能接受string array
            val entries = arrayOf("正向", "左右相反", "上下相反")
            val values = arrayOf("0", "-1x", "-1y")
            imagePref.entries = entries
            imagePref.entryValues = values
            imagePref.value = "0" // 代表我們預設選這一項
            imagePref.key = OPT_IMAGE
            screen.addPreference(imagePref)
        }
    }

}