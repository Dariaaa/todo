package k_de.com.app.mvp

interface MvpPresenter<V: MvpView>{

    fun attachView(mvpView: V)

    fun detachView()

    fun destroy()
}