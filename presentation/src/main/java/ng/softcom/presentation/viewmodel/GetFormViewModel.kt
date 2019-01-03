package ng.softcom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import ng.softcom.domain.form.GetFormData
import ng.softcom.domain.form.SubmitForm
import ng.softcom.models.Form
import ng.softcom.presentation.state.Resource
import ng.softcom.presentation.state.ResourceState
import javax.inject.Inject

/**
 * this handles all data operations between the UI and the data layer
 * it also helps preserve state when orientation changes occur
 * @getFormData - GetFormData use case class
 * @submitForm - SubmitForm use case class
 */
class GetFormViewModel @Inject internal constructor(
    private val getFormData: GetFormData,
    private val submitForm: SubmitForm) : ViewModel() {

    private val formLiveData:MutableLiveData<Resource<Form>> = MutableLiveData()
    private val submitFormLiveData:MutableLiveData<Resource<Form>> = MutableLiveData()
    private val currentPageLiveData:MutableLiveData<Int> = MutableLiveData()

    override fun onCleared() {
        println("view model cleared")
        super.onCleared()
        getFormData.dispose()
    }

    fun getFormLiveData():MutableLiveData<Resource<Form>> {
        return formLiveData
    }
    fun getSubmitFormLiveData():MutableLiveData<Resource<Form>> {
        return submitFormLiveData
    }
    fun getCurrentPageLiveData():MutableLiveData<Int> {
        return currentPageLiveData
    }

    fun incrementCurrentPage(){
        currentPageLiveData.value = currentPageLiveData.value?.plus(1)
    }
    fun decrementCurrentPage(){
        currentPageLiveData.value = currentPageLiveData.value?.minus(1)
    }

    fun getFormData(string:String){
        formLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getFormData.execute(FormSubscriber(), GetFormData.Params(string))
    }

    fun submitForm(form:Form){
        submitFormLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        submitForm.execute(SubmitFormSubscriber(), SubmitForm.Params(form))
    }

    inner class FormSubscriber : DisposableObserver<Form>() {
        override fun onComplete() {
        }

        override fun onNext(t: Form) {
            formLiveData.postValue(Resource(ResourceState.SUCCESS, t, null))
        }

        override fun onError(e: Throwable) {
            formLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
        }
    }

    inner class SubmitFormSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            submitFormLiveData.postValue(Resource(ResourceState.SUCCESS, null, null))
        }

        override fun onError(e: Throwable) {
            submitFormLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}