package ng.softcom.mobileui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ng.softcom.mobileui.databinding.ActivityFormBinding


class FormActivity : FragmentActivity() {


    lateinit var binding:ActivityFormBinding

    /**
     * @numberOfPages The number of pages to show in this form.
     * @currentPage the currentPage user is on the form.
     * todo:replace this with live data
     */
    private var numberOfPages = 3 //default is
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)

        //pager variable is the id of the viewpager in xml since using kotlin extensions no need for findviewbyId
        binding.pager.setMyScroller() //enable smooth scroll on non-swipeableViewPager
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.pager.adapter = pagerAdapter

        initFormControlButtons()
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
    }

    private fun initFormControlButtons(){
        //check if form is single page
        when(currentPage){
            1 -> {
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
            numberOfPages -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedMiddlePageControl.parent.visibility = View.GONE
                //check if form is a single page form only show submit button
                if(currentPage == 1 && numberOfPages == 1)
                    binding.includedLastPageControl.buttonBack.visibility = View.GONE

            }
            else -> {
                binding.includedFirstPageControl.buttonNext.visibility = View.GONE
                binding.includedLastPageControl.parent.visibility = View.GONE
            }
        }

        binding.includedFirstPageControl.buttonNext.setOnClickListener {
            currentPage++
            binding.pager.currentItem = currentPage
        }
        binding.includedMiddlePageControl.buttonBack.setOnClickListener {
            currentPage--
            binding.pager.currentItem = currentPage
        }
        binding.includedMiddlePageControl.buttonNext.setOnClickListener {
            currentPage++
            binding.pager.currentItem = currentPage
        }
        binding.includedLastPageControl.buttonBack.setOnClickListener {
            currentPage--
            binding.pager.currentItem = currentPage
        }
        binding.includedLastPageControl.buttonNext.setOnClickListener {
            currentPage++
            binding.pager.currentItem = currentPage
        }

        binding.textViewPageNumber.text = "$currentPage/$numberOfPages"

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = numberOfPages

        override fun getItem(position: Int): Fragment = FormFragment()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState ?: return

        with(savedInstanceState) {
            // Save whether the address has been requested.
            putInt(NUM_PAGES_KEY, numberOfPages)
            putInt(CURRENT_PAGE_KEY, currentPage)
        }
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?: return

        with(savedInstanceState){
            numberOfPages = getInt(NUM_PAGES_KEY)
            currentPage = getInt(CURRENT_PAGE_KEY)
        }
        super.onRestoreInstanceState(savedInstanceState)


    }


    companion object {
        const val NUM_PAGES_KEY = "num_pages"
        const val CURRENT_PAGE_KEY = "current_page"
    }
}
