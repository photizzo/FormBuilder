package ng.softcom.mobileui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import ng.softcom.mobileui.adapters.FormSectionAdapter
import ng.softcom.mobileui.databinding.FragmentFormPageBinding
import ng.softcom.presentation.viewmodel.GetFormViewModel

class FormFragment : Fragment() {

    private lateinit var getFormViewModel: GetFormViewModel

    private lateinit var binding: FragmentFormPageBinding
    private lateinit var sectionAdapter:FormSectionAdapter
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_form_page,
            container, false
        )

        initViewModel()
        initFormControlButtons()

        return binding.root
    }

    private fun initViewModel() {
        getFormViewModel = activity?.run {
            ViewModelProviders.of(this).get(GetFormViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        initFormSectionRecyclerView()
    }

    private fun initFormSectionRecyclerView() {
        val llm = LinearLayoutManager(activity)
        binding.recyclerViewSection.layoutManager = llm
        val currentPagePosition = arguments?.getInt(getString(R.string.page_position_key))
        val currentFormPage = getFormViewModel.getFormLiveData().value?.data!!.pages
        val formSections = currentFormPage[currentPagePosition!!].section
        sectionAdapter = FormSectionAdapter(formSections) {
            //doing nothing here
        }
        binding.recyclerViewSection.adapter = sectionAdapter
    }

    private fun initFormControlButtons() {
        val currentPage = arguments!!.getInt(getString(R.string.page_position_key))
        val numberOfPages = getFormViewModel.getFormLiveData().value?.data?.pages?.size!! - 1

        when (currentPage) {
            //last page
            numberOfPages -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                //check if form is a single page form only show submit button
                if (getFormViewModel.getCurrentPageLiveData().value == 1
                    && getFormViewModel.getFormLiveData().value?.data?.pages?.size == 1)
                        binding.includedLastPageControl.buttonBack.visibility = View.GONE
            }
            //first page
            0 -> {
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
            //middle pages
            else -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
        }

        binding.includedFirstPageControl.buttonNext.setOnClickListener {
            if(isAllMandatoryQuestionsAreAnsweredForCurrentPage()) {
                updateCurrentPageFormSectionsWithUserInput()
                getFormViewModel.incrementCurrentPage()
            }
        }
        binding.includedMiddlePageControl.buttonBack.setOnClickListener {
            getFormViewModel.decrementCurrentPage()
        }
        binding.includedMiddlePageControl.buttonNext.setOnClickListener {
            if(isAllMandatoryQuestionsAreAnsweredForCurrentPage()){
                updateCurrentPageFormSectionsWithUserInput()
                getFormViewModel.incrementCurrentPage()
            }
        }
        binding.includedLastPageControl.buttonBack.setOnClickListener {
            getFormViewModel.decrementCurrentPage()
        }
        binding.includedLastPageControl.buttonSubmit.setOnClickListener {
            Log.e("tag", "pages ${getFormViewModel.getFormLiveData().value?.data!!.pages}")
        }
    }

    private fun isAllMandatoryQuestionsAreAnsweredForCurrentPage():Boolean{

        return true
    }

    private fun updateCurrentPageFormSectionsWithUserInput(){
        val currentPagePosition = arguments?.getInt(getString(R.string.page_position_key))
        getFormViewModel.getFormLiveData().value?.data!!.pages[currentPagePosition!!].section = sectionAdapter.formSectionList
    }

}