package ng.softcom.mobileui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ng.softcom.mobileui.R
import ng.softcom.mobileui.utils.inflate
import ng.softcom.models.FormElement
import ng.softcom.models.FormSection
class FormSectionAdapter(
    private var items: List<FormSection>,
    private val listener: (FormSection) -> Unit
) : RecyclerView.Adapter<FormSectionAdapter.ViewHolder>() {

     var viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val holder = ViewHolder(parent.inflate(R.layout.layout_form_section_item))
        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textView_section_title)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView_element)

        fun bind(item: FormSection, listener: (FormSection) -> Unit) = with(itemView) {
            sectionNameTv.text = item.label
            initElementRecyclerView(context, item.elements)
            setOnClickListener { listener(item) }
        }

        private fun initElementRecyclerView(context:Context, formElements:List<FormElement>) {
            val llm = LinearLayoutManager(context)
            recyclerView.layoutManager = llm
            val elementAdapter = FormElementAdapter(formElements) {
                //doing nothing here
            }
            recyclerView.adapter = elementAdapter
        }
    }

}

