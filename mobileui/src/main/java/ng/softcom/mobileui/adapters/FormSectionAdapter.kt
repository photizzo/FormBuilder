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
    val formSectionList: List<FormSection>,
    private val listener: (FormSection) -> Unit
) : RecyclerView.Adapter<FormSectionAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(parent.inflate(R.layout.layout_form_section_item))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position, formSectionList[position].elements)

    override fun getItemCount(): Int = formSectionList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionNameTv: TextView = itemView.findViewById(R.id.textView_section_title)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView_element)

        fun bind(position: Int, formElementList:List<FormElement>) = with(itemView) {
            sectionNameTv.text = formSectionList[position].label
            initElementRecyclerView(context, position, formElementList)
        }

        private fun initElementRecyclerView(context:Context, position: Int, formElementList:List<FormElement>) {
            val llm = LinearLayoutManager(context)
            recyclerView.layoutManager = llm

            val elementAdapter = FormElementAdapter(formElementList){
                //update the section form elements
                formSectionList.toMutableList().apply {
                    this[position].elements = it
                }
            }
            recyclerView.adapter = elementAdapter
        }
    }


}

