package com.example.testtasksearchfortickets.presenter.allTickets

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.databinding.FragmentAllTicketsBinding
import com.example.testtasksearchfortickets.di.appComponent
import com.example.testtasksearchfortickets.di.viewModel.ViewModelFactory
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

class AllTicketsFragment : Fragment(R.layout.fragment_all_tickets) {
    private val binding: FragmentAllTicketsBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AllTicketsViewModel by viewModels { viewModelFactory }

    private val adapter = AllTicketsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tickets.observe(viewLifecycleOwner) {
            when (it) {
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
        viewModel.loadTickets()
        initializeUi()
    }

    private fun initializeUi() {
        initializeRecyclerView()
        initializeBackButton()
        setArguments()
    }

    private fun initializeBackButton() {
        binding.backImageView.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
    }

    private fun setArguments() {
        arguments?.let {
            var route = ""
            it.getString("from").let {
                route += it ?: ""

            }
            it.getString("to").let {
                binding.routeTextView.text = "$route-$it"
            }
            it.getString("startDate").let {
                binding.descriptionTextView.text = it
            }
        }
    }

    private fun initializeRecyclerView() {
        binding.ticketsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                context
            )
            addItemDecoration(VerticalItemDecoration(50))
            adapter = this@AllTicketsFragment.adapter
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

}
