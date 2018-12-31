package ng.softcom.data.implementation

import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.Observable
import ng.softcom.domain.repository.FormRepository
import ng.softcom.models.*
import java.io.FileReader
import javax.inject.Inject

class FormDataRepository @Inject constructor() : FormRepository {

    override fun getForm(): Observable<Form> {
        val jsonString = "{\n" +
                "  \"id\": \"form_1\",\n" +
                "  \"name\": \"Pet Adoption Application Form\",\n" +
                "  \"pages\": [\n" +
                "    {\n" +
                "      \"label\": \"Page 1\",\n" +
                "      \"sections\": [\n" +
                "        {\n" +
                "          \"label\": \"Welcome to Pets Rescue\",\n" +
                "          \"elements\": [\n" +
                "            {\n" +
                "              \"type\": \"embeddedphoto\",\n" +
                "              \"file\": \"https://images.pexels.com/photos/8700/wall-animal-dog-pet.jpg?cs=srgb&dl=animal-collar-dog-8700.jpg&fm=jpg\",\n" +
                "              \"unique_id\": \"embeddedphoto_1\",\n" +
                "              \"rules\": []\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"label\": \"Basic Info\",\n" +
                "          \"elements\": [\n" +
                "            {\n" +
                "              \"type\": \"text\",\n" +
                "              \"label\": \"Your fullname\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"text_1\",\n" +
                "              \"rules\": []\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"text\",\n" +
                "              \"label\": \"Email address\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"text_2\",\n" +
                "              \"rules\": []\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"formattednumeric\",\n" +
                "              \"label\": \"Phone Number\",\n" +
                "              \"keyboard\": \"numeric\",\n" +
                "              \"formattedNumeric\": \"####-###-####\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"formattednumeric_1\",\n" +
                "              \"rules\": []\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"datetime\",\n" +
                "              \"mode\": \"date\",\n" +
                "              \"label\": \"Date of Birth\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"datetime_1\",\n" +
                "              \"rules\": []\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"label\": \"Page 2\",\n" +
                "      \"sections\": [\n" +
                "        {\n" +
                "          \"label\": \"About your home\",\n" +
                "          \"elements\": [\n" +
                "            {\n" +
                "              \"type\": \"yesno\",\n" +
                "              \"label\": \"Do you have a yard?\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"yesno_1\",\n" +
                "              \"rules\": [\n" +
                "                {\n" +
                "                  \"condition\": \"equals\",\n" +
                "                  \"value\": \"Yes\",\n" +
                "                  \"action\": \"show\",\n" +
                "                  \"otherwise\": \"hide\",\n" +
                "                  \"targets\": [\n" +
                "                    \"text_3\"\n" +
                "                  ]\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"text\",\n" +
                "              \"label\": \"Is it fenced? Also indicate the type\",\n" +
                "              \"isMandatory\": false,\n" +
                "              \"unique_id\": \"text_3\",\n" +
                "              \"rules\": []\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"label\": \"Page 3\",\n" +
                "      \"sections\": [\n" +
                "        {\n" +
                "          \"label\": \"Additional Information\",\n" +
                "          \"elements\": [\n" +
                "            {\n" +
                "              \"type\": \"text\",\n" +
                "              \"label\": \"Please provide your veterinarian's name\",\n" +
                "              \"isMandatory\": false,\n" +
                "              \"unique_id\": \"text_4\",\n" +
                "              \"rules\": []\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"text\",\n" +
                "              \"label\": \"Please provide the name of a personal reference\",\n" +
                "              \"isMandatory\": true,\n" +
                "              \"unique_id\": \"text_5\",\n" +
                "              \"rules\": []\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        val jsonObject = JsonParser().parse(jsonString).asJsonObject
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
                            //todo: this can break the app- find how to make when case exhaustive
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

        return Observable.just(form)
    }

    private fun readJsonDataFromFile(fileName: String):String {
        var c:Int? = null
        try {
            val fin = FileReader(fileName)
            do {
                c = fin.read()
                print(c.toChar())

            }while (c!=-1)
        } catch (ex:Exception){
            print("error file reading ${ex.localizedMessage}")
        }
        return c.toString()
    }
}