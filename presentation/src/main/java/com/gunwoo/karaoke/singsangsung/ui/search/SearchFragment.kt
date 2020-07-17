package com.gunwoo.karaoke.singsangsung.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.databinding.FragmentSearchBinding
import com.gunwoo.karaoke.singsangsung.ui.list.ListFragment
import com.gunwoo.karaoke.singsangsung.ui.main.MainChildFragment
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : MainChildFragment<FragmentSearchBinding, SearchViewModel>() {

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    override val viewModel: SearchViewModel
        get() = getViewModel(searchViewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            onOpenListEvent.observe(this@SearchFragment, Observer {
                val list = YoutubeDataList()
                list.addAll(searchList)
                val title = search.value!!
                val type = ListFragment.SEARCH_TYPE
                val action = SearchFragmentDirections.actionSearchFragmentToListFragment(list, title, type)
                this@SearchFragment.findNavController().navigate(action)
            })

            onOpenSettingEvent.observe(this@SearchFragment, Observer {
                this@SearchFragment.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchSettingFragment())
            })

            onEmptyEvent.observe(this@SearchFragment, Observer {
                shortToast(R.string.error_empty)
            })

            onHideKeyEvent.observe(this@SearchFragment, Observer {
                val imm: InputMethodManager =
                    this@SearchFragment.activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                imm.hideSoftInputFromWindow(input_search.windowToken, 0)
                input_search.clearFocus()
            })

            onErrorEvent.observe(this@SearchFragment, Observer {
                shortToast(it.message)
            })

            with(searchHistoryListAdapter) {
                onClickItemEvent.observe(this@SearchFragment, Observer {
                    search(it)
                })

                onClickRemoveEvent.observe(this@SearchFragment, Observer {
                    deleteSearchHistory(it)
                })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSearchEvent()
    }

    override fun onStart() {
        super.onStart()
        mViewModel.search.value = null
        mViewModel.setSearchHistoryList()
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