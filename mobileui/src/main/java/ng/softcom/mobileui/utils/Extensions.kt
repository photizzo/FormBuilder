package ng.softcom.mobileui.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar


fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)

}

fun Activity.showSnackbar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    actionString: String = "",
    `listener`: View.OnClickListener? = null
) {
    val snackbar = Snackbar.make(findViewById(android.R.id.content), message, length)

    if (actionString.isNotEmpty()) {
        snackbar.setAction(actionString, `listener`)
    }
    snackbar.show()
}

fun String.getFormattedNumber(pattern: String): String {
    val formattedString = this.removeDashes()
    val patternsLength = this.getPattern(pattern)
    var subStringList = mutableListOf<String>()

    var beginIndex = 0
    var endIndex = patternsLength[0]
    for (i in 0 until patternsLength.size) {
        if (endIndex > formattedString.length) {
            subStringList.add(formattedString.substring(beginIndex, formattedString.length))
            break
        }
        if (formattedString.length >= endIndex) {
            subStringList.add(formattedString.substring(beginIndex, endIndex))
        }

        beginIndex = endIndex
        if(i+1 == patternsLength.size) break
        endIndex += patternsLength[i+1]
    }

    var subString = ""
    subStringList.forEachIndexed { index, s ->
        subString = if (index == subStringList.size-1) "$subString$s"
                    else "$subString$s-"
    }

    var nsubString = ""
    var nendIndex = patternsLength[0]
    for (i in 0 until subStringList.size) {
        nsubString = if (formattedString.length > nendIndex) {
            "$nsubString${subStringList[i]}-"
        }else {
            "$nsubString${subStringList[i]}"
        }

        if(i+1 == subStringList.size) break
        nendIndex += patternsLength[i+1]
    }
    return nsubString
}

fun String.getPattern(pattern: String): List<Int> {
    val count = pattern.length - pattern.replace("-", "").length
    val patternsLength = mutableListOf<Int>()

    var index = pattern.indexOf('-')
    var length = pattern.substring(0, index).length
    for (i in 0..count) {
        patternsLength.add(length)
        length = index + 1
        try {
            index = pattern.indexOf('-', index + 1)
            length = pattern.substring(length, index).length
        } catch (e: StringIndexOutOfBoundsException) {
            length = pattern.substring(length, pattern.length).length
        }
    }
    return patternsLength
}

fun String.removeDashes():String{
    return this.replace("-", "")
}
fun String.removeCommas():String{
    return this.replace(",", "-")
}


