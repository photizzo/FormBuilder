package ng.softcom.domain.repository

import io.reactivex.Observable
import ng.softcom.models.Form

interface FormRepository {
    fun getForm():Observable<Form>
}