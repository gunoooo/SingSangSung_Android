package com.gunwoo.karaoke.singsangsung.view.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentSearchBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.SearchViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.SearchViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    override val viewModel: SearchViewModel
        get() = getViewModel(searchViewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            searchList.observe(this@SearchFragment, Observer {
                val args = YoutubeDataList()
                args.addAll(it)
                val action = SearchFragmentDirections.actionSearchFragmentToListFragment(args)
                this@SearchFragment.findNavController().navigate(action)
            })

            onEmptyEvent.observe(this@SearchFragment, Observer {
                shortToast(R.string.error_empty)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSearchEvent()
    }

    private fun onSearchEvent() {
        input_search.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mViewModel.search()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

}