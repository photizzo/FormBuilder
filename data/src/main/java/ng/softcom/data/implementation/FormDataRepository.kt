package ng.softcom.data.implementation

import io.reactivex.Observable
import ng.softcom.data.utils.DataFactory
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.Form
import javax.inject.Inject

class FormDataRepository @Inject constructor(): FormRepository {

    override fun getForm(): Observable<Form> {
        return Observable.just(DataFactory.form)
    }
}