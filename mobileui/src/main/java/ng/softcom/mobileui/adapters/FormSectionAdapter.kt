package ng.softcom.mobileui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ng.softcom.mobileui.R
import ng.softcom.models.Form
import ng.softcom.mobileui.utils.inflate

class FormSectionAdapter (private val items: List<Form>,
                          private val listener: (Form) -> Unit) : RecyclerView.Adapter<FormSectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.layout_form_section_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textView_section_title)
        fun bind(item: Form, listener: (Form) -> Unit) = with(itemView) {
//            sectionNameTv.text = item.sourceAddress
            setOnClickListener { listener(item) }
        }
    }

}