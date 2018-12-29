package ng.softcom.mobileui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ng.softcom.mobileui.adapters.FormSectionAdapter
import ng.softcom.mobileui.databinding.FragmentFormPageBinding
import ng.softcom.models.Form


class FormFragment : Fragment() {

    /**
     * @numberOfPages The number of pages to show in this form.
     * @currentPage the currentPage user is on the form.
     * todo:replace this with live data
     */
    private var numberOfPages = 1
    private var currentPage = 1


    private lateinit var binding:FragmentFormPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form_page,
            container, false)

        initRecyclerView()
        initFormControlButtons()
        return binding.root
    }

    private fun initRecyclerView(){
        val llm = LinearLayoutManager(activity)
        binding.recyclerViewSection.layoutManager = llm
        val sectionAdapter = FormSectionAdapter(listOf(Form(1), Form(2), Form(3))) {

        }
        binding.recyclerViewSection.adapter = sectionAdapter
    }

    private fun initFormControlButtons(){
        //check if form is single page
        when(currentPage){
            numberOfPages -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                //check if form is a single page form only show submit button
                if(currentPage == 1 && numberOfPages == 1)
                    binding.includedLastPageControl.buttonBack.visibility = View.GONE

            }
            1 -> {
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
            else -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
        }

        binding.includedFirstPageControl.buttonNext.setOnClickListener {
            currentPage++
        }
        binding.includedMiddlePageControl.buttonBack.setOnClickListener {
            currentPage--
        }
        binding.includedMiddlePageControl.buttonNext.setOnClickListener {
            currentPage++
        }
        binding.includedLastPageControl.buttonBack.setOnClickListener {
            currentPage--
        }
        binding.includedLastPageControl.buttonNext.setOnClickListener {
            currentPage++
        }

    }
}