package ng.softcom.domain.form

import io.reactivex.Observable
import ng.softcom.domain.executors.PostExecutionThread
import ng.softcom.domain.interactors.ObservableUseCase
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.Form
import javax.inject.Inject

class GetFormData @Inject constructor(
    private val formRepository: FormRepository,
    postExecutionThread: PostExecutionThread): ObservableUseCase<Form, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<Form> {
        return formRepository.getForm()
    }

}