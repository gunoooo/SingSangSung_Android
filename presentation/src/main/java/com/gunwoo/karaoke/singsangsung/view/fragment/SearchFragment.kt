package com.gunwoo.karaoke.singsangsung.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.UserRecoverableAuthException
import com.gunwoo.karaoke.domain.model.YoutubeDataList
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.base.BaseFragment
import com.gunwoo.karaoke.singsangsung.databinding.FragmentSearchBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.SearchViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.SearchViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.shortToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

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
                if (it is UserRecoverableAuthException) {
                    startActivityForResult(
                        it.intent,
                        REQUEST_AUTHORIZATION
                    )
                }
                else {
                    shortToast(it.message)
                }
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

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        val isOK = resultCode == DaggerAppCompatActivity.RESULT_OK
        when (requestCode) {
            REQUEST_AUTHORIZATION ->
                if (isOK) mViewModel.search()
        }
    }

    companion object {
        private const val REQUEST_AUTHORIZATION = 0
    }
}