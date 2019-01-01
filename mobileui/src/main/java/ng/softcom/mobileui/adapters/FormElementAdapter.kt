package ng.softcom.mobileui.adapters

import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import ng.softcom.mobileui.R
import ng.softcom.mobileui.injection.GlideApp
import ng.softcom.mobileui.utils.inflate
import ng.softcom.models.*
import java.util.*


class FormElementAdapter(
    var items: List<FormElement>,
    private val listener: (List<FormElement>) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            YES_OR_NO -> {
                YesOrNoViewHolder(parent.inflate(R.layout.layout_form_element_yes_or_no))
            }
            EMBEDDED_PHOTO -> {
                PhotoViewHolder(parent.inflate(R.layout.layout_form_element_photo))
            }
            FORMATTED_NUMERIC -> {
                NumberViewHolder(parent.inflate(R.layout.layout_form_element_number))
            }
            DATE_TIME -> {
                DateAndTimeViewHolder(parent.inflate(R.layout.layout_form_element_date))
            }
            else -> {
                TextViewHolder(parent.inflate(R.layout.layout_form_element_text))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].formType){
            FormElementType.YES_OR_NO -> {
                YES_OR_NO
            }
            FormElementType.EMBEDDED_PHOTO -> {
                EMBEDDED_PHOTO
            }
            FormElementType.FORMATTED_NUMERIC -> {
                FORMATTED_NUMERIC
            }
            FormElementType.DATE_TIME -> {
                DATE_TIME
            }
            else -> {
                TEXT
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(items[position].formType){
            FormElementType.YES_OR_NO -> {
                (holder as YesOrNoViewHolder).bind(position)
            }
            FormElementType.EMBEDDED_PHOTO -> {
                (holder as PhotoViewHolder).bind(position)
            }
            FormElementType.FORMATTED_NUMERIC -> {
                (holder as NumberViewHolder).bind(position)
            }
            FormElementType.DATE_TIME -> {
                (holder as DateAndTimeViewHolder).bind(position)
            }
            else -> {
                (holder as TextViewHolder).bind(position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputText: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(position: Int) = with(itemView) {
            val formElementText =  items[position]
            if(!formElementText.isVisible) this.visibility = View.VISIBLE
            else this.visibility = View.GONE
            val mandatoryAsterisks = if(formElementText.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementText.label}  $mandatoryAsterisks"

            var userResponse = formElementText.userResponse
            inputText.setText(userResponse?.stringResponse ?: "")
            inputText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    formElementText.userResponse = FormResponse(stringResponse = s.toString())
                    listener(items)
                }
            })
        }
    }

    inner class YesOrNoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textview_label)
        private val radibGroup:RadioGroup = itemView.findViewById(R.id.radiogroup_yes_or_no)
        fun bind(position: Int) = with(itemView) {
            val formElementYesOrNo =  items[position]
            if(!formElementYesOrNo.isVisible) this.visibility = View.VISIBLE
            else this.visibility = View.GONE
            val mandatoryAsterisks = if(formElementYesOrNo.isMandatory!!) "*" else "" //let user know that field is mandatory
            sectionNameTv.text = "${formElementYesOrNo.label}  $mandatoryAsterisks"


            radibGroup.setOnCheckedChangeListener { group, checkedId ->
                formElementYesOrNo.userResponse = FormResponse(booleanResponse = checkedId == R.id.radioButton_yes)

                if (formElementYesOrNo.rules.isNotEmpty()){
                    if(checkedId == R.id.radioButton_yes)
                        applyRulesToFormElement(formElementYesOrNo.rules, false)
                    else applyRulesToFormElement(formElementYesOrNo.rules, true)
                }

                listener(items)
            }
        }
    }

    inner class DateAndTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputText: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        private val selectDate: TextView = itemView.findViewById(R.id.textView_select_date)
        fun bind(position: Int) = with(itemView) {
            val formElementDateAndTime =  items[position]
            if(!formElementDateAndTime.isVisible) this.visibility = View.VISIBLE
            else this.visibility = View.GONE
            val mandatoryAsterisks = if(formElementDateAndTime.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementDateAndTime.label}  $mandatoryAsterisks"

            setOnClickListener {
                val calender = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener {
                            view, year, month, dayOfMonth -> inputText.setText("$year-$month-$dayOfMonth")
                    },
                    calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }
            var userResponse = formElementDateAndTime.userResponse
            inputText.setText(userResponse?.stringResponse ?: "")
            inputText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    formElementDateAndTime.userResponse = FormResponse(stringResponse = s.toString())
                    listener(items)
                }
            })
        }
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageview: CircleImageView = itemView.findViewById(R.id.imageview_photo)
        fun bind(position: Int) = with(itemView) {
            GlideApp.with(context)
                .load((items[position] as FormElementEmbeddedPhoto).file)
                .centerCrop()
                .placeholder(R.drawable.form_icon)
                .into(imageview)
        }
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputText: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(position: Int) = with(itemView) {
            val formElementNumeric =  items[position]
            if(!formElementNumeric.isVisible) this.visibility = View.VISIBLE
            else this.visibility = View.GONE
            val mandatoryAsterisks = if(formElementNumeric.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementNumeric.label}  $mandatoryAsterisks"

            var userResponse = formElementNumeric.userResponse
            inputText.setText(userResponse?.stringResponse ?: "")
            inputText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    formElementNumeric.userResponse = FormResponse(stringResponse = s.toString())
                    listener(items)
                }
            })
        }
    }

    fun applyRulesToFormElement(rules: List<Rules?>, hideAction:Boolean){
        for(i in rules){
            val targetsList = i!!.targets
            Log.e("tag", "targets $targetsList")
            items.map {
                if(targetsList.contains(it.uniqueId)){
                    it.isVisible = hideAction
                }
            }
        }
        notifyDataSetChanged()
    }

    companion object {
        private const val YES_OR_NO = 1
        private const val TEXT = 2
        private const val EMBEDDED_PHOTO = 3
        private const val FORMATTED_NUMERIC = 4
        private const val DATE_TIME = 5
    }

}