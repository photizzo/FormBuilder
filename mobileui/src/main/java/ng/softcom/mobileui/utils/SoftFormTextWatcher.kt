package ng.softcom.mobileui.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

//this class was created to delay checking for rules while user is typing input
//if ignored checking for rules happens so often that the app will crash
open class SoftFormTextWatcher: TextWatcher {
    private val autoCompletePublishSubject = PublishRelay.create<String>()
    val disposable  = autoCompletePublishSubject
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                checkAndApplyFormRules(result)
            }, { t: Throwable? -> Log.e("tag", " $t Failed to get apply rules") })


    override fun afterTextChanged(s: Editable?) {
        autoCompletePublishSubject.accept(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    open fun checkAndApplyFormRules(s:CharSequence){
    }
}