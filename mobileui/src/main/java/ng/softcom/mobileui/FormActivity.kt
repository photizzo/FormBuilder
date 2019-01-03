package ng.softcom.mobileui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import ng.softcom.mobileui.databinding.ActivityFormBinding
import ng.softcom.mobileui.injection.ViewModelFactory
import ng.softcom.mobileui.utils.readJsonFromFilePath
import ng.softcom.models.Form
import ng.softcom.presentation.state.Resource
import ng.softcom.presentation.state.ResourceState
import ng.softcom.presentation.viewmodel.GetFormViewModel
import javax.inject.Inject


class FormActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var getFormViewModel: GetFormViewModel

    lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)

        initViewModel()
    }

    /**
     * initialize view model
     */
    private fun initViewModel() {
        getFormViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GetFormViewModel::class.java)
        getFormViewModel.getFormLiveData().observe(this, Observer<Resource<Form>> {
            it?.let { handleFormDataState(it) }
        })
        getFormViewModel.getSubmitFormLiveData().observe(this, Observer<Resource<Form>> {
            it?.let { handleSubmitFormData(it) }
        })

        getFormViewModel.getCurrentPageLiveData().observe(this, Observer<Int> {
            it?.let { handleCurrentPageDataState(it) }
        })

        val jsonString = readJsonFromFilePath("jsondata.json")
        //check if form data is null before fetching data to help preserve data when config changes
        if(getFormViewModel.getFormLiveData().value?.data?.pages == null) getFormViewModel.getFormData(jsonString)
    }

    /**
     * handles how backpressed behaves for the view pager
     */
    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            getFormViewModel.decrementCurrentPage()
        }
    }

    /**
     * A simple pager adapter helps manage the form pages in sequence
     */
    private inner class FormPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = getFormViewModel.getFormLiveData().value?.data?.pages?.size!!

        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(getString(R.string.page_position_key), position)
            val fragment  = FormFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    /**
     * initializes view pager
     */
    private fun initFormViewPager() {
        binding.pager.setMyScroller() //enable smooth scroll on non-swipeableViewPager
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = FormPagerAdapter(supportFragmentManager)
        binding.pager.adapter = pagerAdapter
    }

    /**
     * handles data when received from the view model
     * all UI interactions when form changes are handled here
     */
    private fun handleFormDataState(resource: Resource<Form>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                binding.textViewToolbar.text = resource.data?.label
                if(getFormViewModel.getCurrentPageLiveData().value == null)
                    getFormViewModel.getCurrentPageLiveData().value = 0//initialize current page to page 1
                else getFormViewModel.getCurrentPageLiveData().value = getFormViewModel.getCurrentPageLiveData().value
                initFormViewPager()
            }
            ResourceState.LOADING -> {
                //show progress bar
            }
            ResourceState.ERROR -> {
                //show error toast
                binding.pager.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            }
        }
    }

    /**
     * handles data when received from the view model
     * all UI interactions when form has been submitted happens here
     */
    private fun handleSubmitFormData(resource: Resource<Form>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                SuccessActivity.startActivity(this)
                finish()
            }
            ResourceState.LOADING -> {
                //show progress bar
            }
            ResourceState.ERROR -> {
                //show error toast
            }
        }
    }

    /**
     * handles data when received from the view model
     * all UI interactions when current page data changes are handled here
     */
    private fun handleCurrentPageDataState(pageNumber: Int) {
        val numOfPages = getFormViewModel.getFormLiveData().value?.data?.pages?.size
        binding.textViewPageNumber.text = "${pageNumber + 1}/$numOfPages"

        //move to the next page
        binding.pager.currentItem = pageNumber
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, FormActivity::class.java))
        }
    }
}
