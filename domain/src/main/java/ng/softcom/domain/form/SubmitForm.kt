package ng.softcom.domain.form

import io.reactivex.Completable
import ng.softcom.domain.executors.PostExecutionThread
import ng.softcom.domain.interactors.CompletableUseCase
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.Form
import javax.inject.Inject

class SubmitForm @Inject constructor(
    private val formRepository: FormRepository,
    postExecutionThread: PostExecutionThread): CompletableUseCase<SubmitForm.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: SubmitForm.Params?): Completable {
        if(params == null) throw IllegalArgumentException("params cannot be null")
        return formRepository.submitForm(params.form)
    }

    data class Params constructor(val form: Form) {
        companion object {
            fun forProject(string: Form): Params {
                return Params(string)
            }


        }
    }

}