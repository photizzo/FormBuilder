package ng.softcom.mobileui.adapters

import android.app.DatePickerDialog
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.InputFilter
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import ng.softcom.mobileui.R
import ng.softcom.mobileui.injection.GlideApp
import ng.softcom.mobileui.utils.SoftFormTextWatcher
import ng.softcom.mobileui.utils.inflate
import ng.softcom.models.*
import java.util.*


class FormElementAdapter(
    var items: List<FormElement>,
    private val listenerItemsChanged: (List<FormElement>) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val formTextWatcherListeners = mutableListOf<SoftFormTextWatcher?>()
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
        return when (items[position].formType) {
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
        when (items[position].formType) {
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
            val formElementText = items[position]
            formTextWatcherListeners.map { inputText.removeTextChangedListener(it) }

            addTextWatcher(inputText, formElementText)

            changeFormElementVisibility(this, !formElementText.isVisible)
            //update form element label
            val mandatoryAsterisks =
                if (formElementText.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementText.label}  $mandatoryAsterisks"
        }
    }

    inner class YesOrNoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textview_label)
        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radiogroup_yes_or_no)
        fun bind(position: Int) = with(itemView) {
            val formElementYesOrNo = items[position]
            changeFormElementVisibility(this, !formElementYesOrNo.isVisible)
            //update form element label
            val mandatoryAsterisks =
                if (formElementYesOrNo.isMandatory!!) "*" else "" //let user know that field is mandatory
            sectionNameTv.text = "${formElementYesOrNo.label}  $mandatoryAsterisks"


            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                formElementYesOrNo.userResponse = FormResponse(booleanResponse = checkedId == R.id.radioButton_yes)

                if (formElementYesOrNo.rules.isNotEmpty()) {
                    if (checkedId == R.id.radioButton_yes)
                        applyRulesToFormElement(formElementYesOrNo.rules, "Yes")
                    else applyRulesToFormElement(formElementYesOrNo.rules, "No")
                }
                listenerItemsChanged(items)
            }
        }
    }

    inner class DateAndTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputText: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(position: Int) = with(itemView) {
            val formElementDateAndTime = items[position]
            formTextWatcherListeners.map { inputText.removeTextChangedListener(it) }

            addTextWatcher(inputText, formElementDateAndTime)
            changeFormElementVisibility(this, !formElementDateAndTime.isVisible)
            //update form element label
            val mandatoryAsterisks =
                if (formElementDateAndTime.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementDateAndTime.label}  $mandatoryAsterisks"

            setOnClickListener {
                val calender = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        inputText.setText("$year-${month + 1}-$dayOfMonth")
                    },
                    calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
            }

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

            applyRulesToFormElement((items[position] as FormElementEmbeddedPhoto).rules, "")
        }
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputText: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(position: Int) = with(itemView) {
            val formElementNumeric = items[position]
            val textLimit = (formElementNumeric as FormElementFormattedNumeric).formatPattern.length
            inputText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(textLimit))
            inputText.addTextChangedListener(PhoneNumberFormattingTextWatcher())

            formTextWatcherListeners.map { inputText.removeTextChangedListener(it) }

            addTextWatcher(inputText, formElementNumeric)
            changeFormElementVisibility(this, !formElementNumeric.isVisible)
            //update form element label
            val mandatoryAsterisks =
                if (formElementNumeric.isMandatory!!) "*" else "" //let user know that field is mandatory
            inputLayout.hint = "${formElementNumeric.label}  $mandatoryAsterisks"

        }
    }

    fun applyRulesToFormElement(rules: List<Rules?>, value: String) {
        val oldItemsList = mutableListOf<FormElement>()
        items.forEach {
            val formElement = FormElement()
            formElement.uniqueId = it.uniqueId
            formElement.isVisible = it.isVisible
            formElement.userResponse = it.userResponse
            oldItemsList.add(formElement)
        }

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

        val diffCallback = FormDiffCallback(oldItemsList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

    }

    fun changeFormElementVisibility(view: View, status: Boolean) {
        if (status) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }


    fun addTextWatcher(inputText: TextInputEditText, formElement: FormElement) {
        val textWatcher = object : SoftFormTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                formElement.userResponse = FormResponse(stringResponse = s.toString())
                if(formElement.formType == FormElementType.FORMATTED_NUMERIC && s.toString().isNotEmpty()){
                    super.applyNumberFormatting((formElement as FormElementFormattedNumeric).formatPattern)
                    super.afterTextChanged(s)
                }
                listenerItemsChanged(items)
                applyRulesToFormElement(formElement.rules, s.toString())
            }
        }

        formTextWatcherListeners.map { inputText.removeTextChangedListener(it) }
        inputText.setText(formElement.userResponse?.stringResponse ?: "")
        inputText.setSelection(inputText.text.toString().length)
        inputText.addTextChangedListener(textWatcher)
        formTextWatcherListeners.add(textWatcher as SoftFormTextWatcher)

    }

    companion object {
        private const val YES_OR_NO = 1
        private const val TEXT = 2
        private const val EMBEDDED_PHOTO = 3
        private const val FORMATTED_NUMERIC = 4
        private const val DATE_TIME = 5
    }

}