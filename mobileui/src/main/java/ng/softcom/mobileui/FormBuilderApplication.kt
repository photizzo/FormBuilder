package ng.softcom.mobileui

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ng.softcom.mobileui.injection.DaggerApplicationComponent

class FormBuilderApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent
            .builder()
            .create(this)
            .build()
    }
}
