package ng.softcom.mobileui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ng.softcom.domain.executors.PostExecutionThread
import javax.inject.Inject


class UiThread @Inject constructor(): PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}