package ng.softcom.models

data class Form(
    val label: String,
    val pages: List<FormPage>
)

data class FormPage(
    val label: String,
    val section: List<FormSection>
)

data class FormSection(
    val label: String,
    val elements: List<FormElement>
)


enum class FormElementType {
    YES_OR_NO, TEXT, EMBEDDED_PHOTO, FORMATTED_NUMERIC, DATE_TIME
}

open class FormElement(val formType: FormElementType)

data class FormElementText(
    val label: String,
    val isMandatory: Boolean,
    val uniqueId: String,
    val rules: List<Rules?>,
    var userResponse: String?
) : FormElement(FormElementType.TEXT)

data class FormElementYesOrNo(
    val label: String,
    val isMandatory: Boolean,
    val uniqueId: String,
    val rules: List<Rules?>,
    var userResponseIsYes: Boolean?
) : FormElement(FormElementType.YES_OR_NO)

data class FormElementEmbeddedPhoto(
    val file: String,
    val uniqueId: String,
    val rules: List<Rules?>
) : FormElement(FormElementType.EMBEDDED_PHOTO)

data class FormElementFormattedNumeric(
    val label: String,
    val keyboard: String,
    val formatPattern: String,
    val isMandatory: Boolean,
    val uniqueId: String,
    val rules: List<Rules?>,
    var userResponse: String?
) : FormElement(FormElementType.FORMATTED_NUMERIC)

data class FormElementDateAndTime(
    val label: String,
    val mode: String,
    val isMandatory: Boolean,
    val uniqueId: String,
    val rules: List<Rules?>,
    var userResponse: String?
) : FormElement(FormElementType.DATE_TIME)

data class Rules(
    val condition: String,
    val value: String,
    val action: String,
    val otherwise: String,
    val targets: List<String>
)


