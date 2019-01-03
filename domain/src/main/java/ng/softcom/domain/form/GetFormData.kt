package ng.softcom.domain.form

import io.reactivex.Observable
import ng.softcom.domain.executors.PostExecutionThread
import ng.softcom.domain.interactors.ObservableUseCase
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.Form
import javax.inject.Inject

class GetFormData @Inject constructor(
    private val formRepository: FormRepository,
    postExecutionThread: PostExecutionThread): ObservableUseCase<Form, GetFormData.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: GetFormData.Params?): Observable<Form> {
        if(params == null) throw IllegalArgumentException("params cannot be null")
        return formRepository.parseFormData(params.string)
    }

    data class Params constructor(val string: String) {
        companion object {
            fun forProject(string: String): Params {
                return Params(string)
            }


        }
    }

}