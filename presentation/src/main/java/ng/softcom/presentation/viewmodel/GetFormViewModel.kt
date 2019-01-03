package ng.softcom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ng.softcom.domain.form.GetFormData
import ng.softcom.models.Form
import ng.softcom.presentation.state.Resource
import ng.softcom.presentation.state.ResourceState
import javax.inject.Inject

class GetFormViewModel @Inject internal constructor(
    private val getFormData: GetFormData) : ViewModel() {

    private val formLiveData:MutableLiveData<Resource<Form>> = MutableLiveData()
    private val currentPageLiveData:MutableLiveData<Int> = MutableLiveData()

    override fun onCleared() {
        println("view model cleared")
        super.onCleared()
        getFormData.dispose()
    }

    fun getFormLiveData():MutableLiveData<Resource<Form>> {
        return formLiveData
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

    fun getFormData(){
        formLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getFormData.execute(FormSubscriber())
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
}