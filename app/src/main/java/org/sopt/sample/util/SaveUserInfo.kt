package org.sopt.sample.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.sample.data.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveUserInfo @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)

    fun setAutoLogin(isAutoLogin: Boolean) {
        prefs.edit().putBoolean(AUTO_LOGIN, isAutoLogin).apply()
    }

    fun getAutoLogin(): Boolean {
        return prefs.getBoolean(AUTO_LOGIN, false)
    }

    fun setUserInfo(user: User) {
        prefs.edit().putString(USER_INFO, Gson().toJson(user)).apply()
    }

    fun getUserInfo(): User {
        val userInfo = prefs.getString(USER_INFO, "")
        return Gson().fromJson(userInfo, User::class.java)
    }

    companion object {
        const val AUTO_LOGIN = "AutoLogin"
        const val STORAGE_KEY = "StorageKey"
        const val USER_INFO = "UserInfo"
    }
}
