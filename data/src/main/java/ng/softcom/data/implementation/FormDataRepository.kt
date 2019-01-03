package ng.softcom.data.implementation

import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.Completable
import io.reactivex.Observable
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.*
import javax.inject.Inject

class FormDataRepository @Inject constructor() : FormRepository {

    override fun parseFormData(string: String): Observable<Form> {
        val jsonObject = JsonParser().parse(string).asJsonObject
        val label = jsonObject.get("name").asString
        val pages = jsonObject.get("pages").asJsonArray

        val formPageList = mutableListOf<FormPage>()
        for (section in pages) {
            val label = section.asJsonObject.get("label").asString
            val sections = section.asJsonObject.get("sections").asJsonArray

            val formSectionList = mutableListOf<FormSection>()
            for (element in sections) {
                val label = element.asJsonObject.get("label").asString
                val elements = element.asJsonObject.get("elements").asJsonArray

                val formElementList = mutableListOf<FormElement>()
                for (formElement in elements) {
                    val formElementType = formElement.asJsonObject.get("type").asString
                    val formElement = when (formElementType) {
                        "text" -> {
                            val text = Gson().fromJson(formElement, FormElementText::class.java)
                            text.formType = FormElementType.TEXT
                            text
                        }
                        "datetime" -> {
                            val dateAndTime = Gson().fromJson(formElement, FormElementDateAndTime::class.java)
                            dateAndTime.formType = FormElementType.DATE_TIME
                            dateAndTime
                        }
                        "embeddedphoto" -> {
                            val photo = Gson().fromJson(formElement, FormElementEmbeddedPhoto::class.java)
                            photo.formType = FormElementType.EMBEDDED_PHOTO
                            photo.uniqueId = ""
                            photo
                        }
                        "formattednumeric" -> {
                            val formatterNumeric = Gson().fromJson(formElement, FormElementFormattedNumeric::class.java)
                            formatterNumeric.formType = FormElementType.FORMATTED_NUMERIC
                            formatterNumeric
                        }
                        "yesno" -> {
                            val yesNo = Gson().fromJson(formElement, FormElementYesOrNo::class.java)
                            yesNo.formType = FormElementType.YES_OR_NO
                            yesNo
                        }
                        else -> {
                            //todo: this can break the app- is there a way to make when exhausive
                            val text = Gson().fromJson(formElement, FormElementText::class.java)
                            text.formType = FormElementType.TEXT
                            text
                        }
                    }
                    formElementList.add(formElement)
                }
                formSectionList.add(FormSection(label, formElementList))
            }
            formPageList.add(FormPage(label, formSectionList))
        }
        val form = Form(label, formPageList)
        applyRulesToForm(form)

        return Observable.just(form)
    }

    private fun applyRulesToForm(form: Form){
        for (i in form.pages) {
            for (j in i.section) {
                for (k in j.elements) {
                    if(k.rules.isNotEmpty()){
                        applyRulesToFormElement(k.rules, j.elements, "")
                    }
                }
            }
        }
    }
    private fun applyRulesToFormElement(rules: List<Rules?>, items: List<FormElement>, value:String) {
        /*for (i in rules) {
            val targetsList = i!!.targets
            items.map {
                it.isVisible = targetsList.contains(it.uniqueId)
            }
        }*/
        for (i in rules) {
            val targetsList = i!!.targets
            items.map {
                if (targetsList.contains(it.uniqueId)) {
                    val isConditionMet = i.value == value
                    val isShow = i.action == "show"
                    if (isConditionMet)
                        it.isVisible = !isShow
                    else it.isVisible = isShow
                }
            }
        }
    }

    override fun submitForm(form: Form): Completable {
        return Completable.complete()
    }

}