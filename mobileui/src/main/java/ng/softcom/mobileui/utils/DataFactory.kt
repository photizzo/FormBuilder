package ng.softcom.mobileui.utils

import ng.softcom.models.*

object DataFactory {
    val formElements1 =  FormElementText("Text", true, "unique", listOf(), null)
    val formElements2 =  FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null)
    val formElements3 =  FormElementEmbeddedPhoto("Text",  "unique", listOf())
    val formElements4 =  FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null)
    val formElements5 =  FormElementYesOrNo("Text", true, "unique", listOf(), null)

    val section = FormSection("FormSection " , listOf(formElements1, formElements2, formElements3, formElements4, formElements5))
    val formPage = FormPage("Form Pages", listOf(section, section))

    val form = Form("Form", listOf(formPage, formPage, formPage))


}