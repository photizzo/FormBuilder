package ng.softcom.mobileui.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar


fun ViewGroup.inflate (layout:Int): View {
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)

}

fun Activity.showSnackbar(message: String, length: Int = Snackbar.LENGTH_SHORT, actionString: String = "", `listener`: View.OnClickListener? = null) {
    val snackbar = Snackbar.make(findViewById(android.R.id.content), message, length)

    if (actionString.isNotEmpty()) {
        snackbar.setAction(actionString, `listener`)
    }
    snackbar.show()
}

