package ng.softcom.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ng.softcom.models.Form

interface FormRepository {
    fun parseFormData(string:String):Observable<Form>

    fun submitForm(form: Form):Completable
}