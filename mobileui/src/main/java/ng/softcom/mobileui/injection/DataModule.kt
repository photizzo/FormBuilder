package ng.softcom.mobileui.injection

import dagger.Binds
import dagger.Module
import ng.softcom.data.implementation.FormDataRepository
import ng.softcom.domain.repository.FormRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindFormDataRepository(dataRepository: FormDataRepository): FormRepository
}