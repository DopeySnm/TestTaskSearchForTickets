package com.example.testtasksearchfortickets.presenter.mainScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.databinding.FragmentMainScreenBinding
import com.example.testtasksearchfortickets.di.appComponent
import com.example.testtasksearchfortickets.di.viewModel.ViewModelFactory
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding: FragmentMainScreenBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory }

    private val adapter = MainScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.offers.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Success -> {
                    adapter.apply {
                        items = it.value
                    }
                }
                is UiState.Loading -> {

                }
                is UiState.Failure -> {

                }
            }
        }
        viewModel.loadOffers()
        initializeRecycler()
    }

    private fun initializeRecycler() {
        binding.offersRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(HorizontalItemDecoration(250))
            adapter = this@MainScreenFragment.adapter
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            MainScreenFragment()
    }

}
