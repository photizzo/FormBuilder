package ng.softcom.mobileui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import ng.softcom.mobileui.R
import ng.softcom.mobileui.utils.inflate
import ng.softcom.models.*

class FormElementAdapter(
    private var items: List<FormElement>,
    private val listener: (FormElement) -> Unit
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
                (holder as YesOrNoViewHolder).bind(items[position], listener)
            }
            FormElementType.EMBEDDED_PHOTO -> {
                (holder as PhotoViewHolder).bind(items[position], listener)
            }
            FormElementType.FORMATTED_NUMERIC -> {
                (holder as NumberViewHolder).bind(items[position], listener)
            }
            FormElementType.DATE_TIME -> {
                (holder as DateAndTimeViewHolder).bind(items[position], listener)
            }
            else -> {
                (holder as TextViewHolder).bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputTextView: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(item: FormElement, listener: (FormElement) -> Unit) = with(itemView) {
            inputLayout.hint = (item as FormElementText).label
        }
    }

    class YesOrNoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textview_label)
        fun bind(item: FormElement, listener: (FormElement) -> Unit) = with(itemView) {
            sectionNameTv.text = (item as FormElementYesOrNo).label

        }
    }

    class DateAndTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputTextView: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        private val selectDate: TextView = itemView.findViewById(R.id.textView_select_date)
        fun bind(item: FormElement, listener: (FormElement) -> Unit) = with(itemView) {
            inputLayout.hint = (item as FormElementDateAndTime).label
            setOnClickListener {

            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: CircleImageView = itemView.findViewById(R.id.imageview_photo)
        fun bind(item: FormElement, listener: (FormElement) -> Unit) = with(itemView) {

        }
    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputTextView: TextInputEditText = itemView.findViewById(R.id.input_text)
        private val inputLayout: TextInputLayout = itemView.findViewById(R.id.input_layout)
        fun bind(item: FormElement, listener: (FormElement) -> Unit) = with(itemView) {
            inputLayout.hint = (item as FormElementFormattedNumeric).label
        }
    }

    companion object {
        private const val YES_OR_NO = 1
        private const val TEXT = 2
        private const val EMBEDDED_PHOTO = 3
        private const val FORMATTED_NUMERIC = 4
        private const val DATE_TIME = 5
    }

}