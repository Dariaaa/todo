package k_de.com.app.mvp

import android.content.SharedPreferences

interface MvpPresenter<V: MvpView>{

    fun attachView(mvpView: V)

    fun detachView()

    fun destroy()

    fun setPrefs(pref:SharedPreferences)
}