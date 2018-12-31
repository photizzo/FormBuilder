package ng.softcom.data.utils

import ng.softcom.models.*

object DataFactory {
    val formElements1 =  FormElementText("Text", true, "unique", listOf(), null)
    val formElements2 =  FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null)
    val formElements3 =  FormElementEmbeddedPhoto("Text",  "unique", listOf())
    val formElements4 =  FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null)
    val formElements5 =  FormElementYesOrNo("Text", true, "unique", listOf(), null)

    val section = FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
        FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
        FormElementEmbeddedPhoto("Text",  "unique", listOf()),
        FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
        FormElementYesOrNo("Text", true, "unique", listOf(), null)))


    val formPage = FormPage("Form Pages", listOf(FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
        FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
        FormElementEmbeddedPhoto("Text",  "unique", listOf()),
        FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
        FormElementYesOrNo("Text", true, "unique", listOf(), null))),

        FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
            FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
            FormElementEmbeddedPhoto("Text",  "unique", listOf()),
            FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
            FormElementYesOrNo("Text", true, "unique", listOf(), null)))))


    val form = Form("Form", listOf(FormPage("Form Pages", listOf(FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
        FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
        FormElementEmbeddedPhoto("Text",  "unique", listOf()),
        FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
        FormElementYesOrNo("Text", true, "unique", listOf(), null))),

        FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
            FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
            FormElementEmbeddedPhoto("Text",  "unique", listOf()),
            FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
            FormElementYesOrNo("Text", true, "unique", listOf(), null))))),

        FormPage("Form Pages", listOf(FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
            FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
            FormElementEmbeddedPhoto("Text",  "unique", listOf()),
            FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
            FormElementYesOrNo("Text", true, "unique", listOf(), null))),

            FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
                FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
                FormElementEmbeddedPhoto("Text",  "unique", listOf()),
                FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
                FormElementYesOrNo("Text", true, "unique", listOf(), null))))),

        FormPage("Form Pages", listOf(FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
            FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
            FormElementEmbeddedPhoto("Text",  "unique", listOf()),
            FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
            FormElementYesOrNo("Text", true, "unique", listOf(), null))),

            FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
                FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
                FormElementEmbeddedPhoto("Text",  "unique", listOf()),
                FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
                FormElementYesOrNo("Text", true, "unique", listOf(), null))))),

        FormPage("Form Pages", listOf(FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
            FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
            FormElementEmbeddedPhoto("Text",  "unique", listOf()),
            FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
            FormElementYesOrNo("Text", true, "unique", listOf(), null))),

            FormSection("FormSection " , listOf(FormElementText("Text", true, "unique", listOf(), null),
                FormElementFormattedNumeric("Text", "numberic", "###",true, "unique", listOf(), null),
                FormElementEmbeddedPhoto("Text",  "unique", listOf()),
                FormElementDateAndTime("Text", "numberic",true, "unique", listOf(), null),
                FormElementYesOrNo("Text", true, "unique", listOf(), null)))))))


}

