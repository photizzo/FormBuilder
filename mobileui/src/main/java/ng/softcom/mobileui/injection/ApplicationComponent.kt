package ng.softcom.mobileui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ng.softcom.mobileui.FormBuilderApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class])
interface ApplicationComponent: AndroidInjector<FormBuilderApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder

        fun build(): ApplicationComponent
    }

}