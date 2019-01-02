package ng.softcom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Form(
    val label: String,
    var pages: List<FormPage>
)

data class FormPage(
    val label: String,
    var section: List<FormSection>
)

data class FormSection(
    val label: String,
    var elements: List<FormElement>
)


enum class FormElementType {
    YES_OR_NO, TEXT, EMBEDDED_PHOTO, FORMATTED_NUMERIC, DATE_TIME
}

open  class FormElement{
    //using transient prevent gson from deserializing the variable
    open val label: String get() {return label}
    open val formType: FormElementType get() { return formType }
    open var uniqueId: String = ""
    open val isMandatory: Boolean? get() {return isMandatory}
    open val rules: List<Rules?> get() {return rules}
    open var userResponse:FormResponse? = null
    open var isVisible: Boolean = true

}

data class FormResponse(var stringResponse:String? = null,
                        var booleanResponse:Boolean? = false)


data class FormElementText(
    @Expose override var formType: FormElementType = FormElementType.TEXT,
    override val label: String,
    override val isMandatory: Boolean,
    @SerializedName("unique_id") override var uniqueId: String,
    override val rules: List<Rules?>
) : FormElement() //FormElementType.TEXT, uniqueId, isMandatory, rules, isVisible)

data class FormElementYesOrNo(
    @Expose override var formType: FormElementType = FormElementType.YES_OR_NO,
    override val label: String,
    override val isMandatory: Boolean,
    @SerializedName("unique_id") override var uniqueId: String,
    override val rules: List<Rules?>
) : FormElement() //FormElementType.YES_OR_NO, uniqueId, isMandatory, rules, isVisible)

data class FormElementEmbeddedPhoto(
    @Expose override var formType: FormElementType = FormElementType.EMBEDDED_PHOTO,
    val file: String,
    override val rules: List<Rules?>
) : FormElement() // FormElementType.EMBEDDED_PHOTO)

data class FormElementFormattedNumeric(
    @Expose override var formType: FormElementType = FormElementType.FORMATTED_NUMERIC,
    override val label: String,
    val keyboard: String,
    @SerializedName("formattedNumeric") val formatPattern: String,
    override val isMandatory: Boolean,
    @SerializedName("unique_id") override var uniqueId: String,
    override val rules: List<Rules?>
) : FormElement() //FormElementType.FORMATTED_NUMERIC, uniqueId, isMandatory, rules, isVisible)

data class FormElementDateAndTime(
    @Expose override var formType: FormElementType = FormElementType.DATE_TIME,
    override val label: String,
    val mode: String,
    override val isMandatory: Boolean,
    @SerializedName("unique_id") override var uniqueId: String,
    override val rules: List<Rules?>
) : FormElement() //FormElementType.DATE_TIME, uniqueId, isMandatory, rules, isVisible)

data class Rules(
    val condition: String,
    val value: String,
    val action: String,
    val otherwise: String,
    val targets: List<String>
)



