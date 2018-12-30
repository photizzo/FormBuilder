package ng.softcom.data.implementation

import io.reactivex.Observable
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.Form

class FormDataRepository: FormRepository {

    override fun getForm(): Observable<Form> {
        return Observable.just(Form())
    }
}