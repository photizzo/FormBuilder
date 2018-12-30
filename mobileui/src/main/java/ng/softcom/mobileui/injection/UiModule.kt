package ng.softcom.mobileui.injection

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ng.softcom.domain.executors.PostExecutionThread
import ng.softcom.mobileui.FormActivity
import ng.softcom.mobileui.FormFragment
import ng.softcom.mobileui.UiThread


@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesFormActivity(): FormActivity

    @ContributesAndroidInjector
    abstract fun contributeFormFragment(): FormFragment

}