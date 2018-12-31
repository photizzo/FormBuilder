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
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import ng.softcom.mobileui.adapters.FormSectionAdapter
import ng.softcom.mobileui.databinding.FragmentFormPageBinding
import ng.softcom.mobileui.utils.showSnackbar
import ng.softcom.models.*
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
            } else activity?.showSnackbar("All mandatory field marked * must be filled", Snackbar.LENGTH_LONG)
        }
        binding.includedMiddlePageControl.buttonBack.setOnClickListener {
            getFormViewModel.decrementCurrentPage()
        }
        binding.includedMiddlePageControl.buttonNext.setOnClickListener {
            if(isAllMandatoryQuestionsAreAnsweredForCurrentPage()){
                updateCurrentPageFormSectionsWithUserInput()
                getFormViewModel.incrementCurrentPage()
            } else activity?.showSnackbar("All mandatory field marked * must be filled", Snackbar.LENGTH_LONG)
        }
        binding.includedLastPageControl.buttonBack.setOnClickListener {
            getFormViewModel.decrementCurrentPage()
        }
        binding.includedLastPageControl.buttonSubmit.setOnClickListener {
            if(isAllMandatoryQuestionsAreAnsweredForCurrentPage()){
                Log.e("tag", "pages ${getFormViewModel.getFormLiveData().value?.data!!.pages}")

            } else activity?.showSnackbar("All mandatory field marked * must be filled", Snackbar.LENGTH_LONG)

        }
    }

    private fun isAllMandatoryQuestionsAreAnsweredForCurrentPage():Boolean{
        var allMandatoryQuestionsAnswered = false
        for(i in sectionAdapter.formSectionList){
            for(j in i.elements){
                when(j.formType){
                    FormElementType.YES_OR_NO -> {
                        val formElement = j as FormElementYesOrNo
                        allMandatoryQuestionsAnswered = if(formElement.isMandatory){
                            formElement.userResponseIsYes != null
                        } else {
                            true
                        }
                        Log.e("tag", "yes/no $allMandatoryQuestionsAnswered")
                    }
                    FormElementType.EMBEDDED_PHOTO -> {
                        //do nothing
                    }
                    FormElementType.FORMATTED_NUMERIC -> {
                        val formElement = j as FormElementFormattedNumeric
                        allMandatoryQuestionsAnswered = if(formElement.isMandatory){
                            formElement.userResponse != null && formElement.userResponse!!.isNotEmpty()
                        } else {
                            true
                        }
                        Log.e("tag", "numeric $allMandatoryQuestionsAnswered")
                    }
                    FormElementType.DATE_TIME -> {
                        val formElement = j as FormElementDateAndTime
                        allMandatoryQuestionsAnswered = if(formElement.isMandatory){
                            formElement.userResponse != null && formElement.userResponse!!.isNotEmpty()
                        } else {
                            true
                        }
                        Log.e("tag", "datetime $allMandatoryQuestionsAnswered")
                    }
                    else -> {
                        val formElement = j as FormElementText
                        allMandatoryQuestionsAnswered = if(formElement.isMandatory){
                            formElement.userResponse != null && formElement.userResponse!!.isNotEmpty()
                        } else {
                            true
                        }
                        Log.e("tag", "text $allMandatoryQuestionsAnswered")
                    }
                }
            }
        }
        return allMandatoryQuestionsAnswered
    }

    private fun updateCurrentPageFormSectionsWithUserInput(){
        val currentPagePosition = arguments?.getInt(getString(R.string.page_position_key))
        getFormViewModel.getFormLiveData().value?.data!!.pages[currentPagePosition!!].section = sectionAdapter.formSectionList
    }

}