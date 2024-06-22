package com.example.testtasksearchfortickets.presenter.mainScreen

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.databinding.BottomSheetBinding
import com.example.testtasksearchfortickets.databinding.FragmentMainScreenBinding
import com.example.testtasksearchfortickets.di.appComponent
import com.example.testtasksearchfortickets.di.viewModel.ViewModelFactory
import com.example.testtasksearchfortickets.presenter.MainFragmentDirections
import com.example.testtasksearchfortickets.presenter.selectCountry.SelectCountryFragment
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding: FragmentMainScreenBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory }

    private val adapter = OffersAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.offers.observe(viewLifecycleOwner) {
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
        viewModel.loadOffers()
        initializeUi()
    }

    private fun initializeUi() {
        initializeRecycler()
        initializeEditText()
    }

    private fun initializeEditText() {
        binding.toWhereEditText.setOnClickListener {
            if (binding.fromWhereEditText.text!!.isNotEmpty()) {
                createDialog()
            }

        }
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

    private fun goSelectCountry(from: String, to: String) {
        val action = MainFragmentDirections.actionMainFragmentToSelectCountryFragment(
            from,
            to
        )
        Navigation.findNavController(requireView()).navigate(action)
//        val navHostId = R.id.nav_host
//        childFragmentManager.beginTransaction().replace(
//            navHostId,
//            SelectCountryFragment.newInstance()
//        )
    }

    private fun createDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet)

        dialog.show()
        dialog.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val popularDestinationsRecyclerView = dialog.findViewById<RecyclerView>(R.id.popular_destinations_recyclerView)
        val fromWhereEditText = dialog.findViewById<TextInputEditText>(R.id.from_where_dialog_editText)
        val toWhereEditText = dialog.findViewById<TextInputEditText>(R.id.to_where_dialog_editText)
        val difficultRouteButton = dialog.findViewById<ConstraintLayout>(R.id.difficult_route_button)
        val anyRouteButton = dialog.findViewById<ConstraintLayout>(R.id.any_route_button)
        val weekendButton = dialog.findViewById<ConstraintLayout>(R.id.weekend_button)
        val hotTicketsButton = dialog.findViewById<ConstraintLayout>(R.id.hot_tickets_button)

        val goSelectCountry = {
            goSelectCountry(
                fromWhereEditText.text.toString(),
                toWhereEditText.text.toString()
            )
            dialog.dismiss()
        }

        toWhereEditText.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                goSelectCountry()
                return@OnEditorActionListener true
            }
            false
        })

        val dialogAdapter = ListDelegationAdapter(
            MainScreenDelegates.popularDestinationsDelegate {
                toWhereEditText.setText(it.name)
                goSelectCountry()
            }
        )

        val stabNavigate = {
            dialog.dismiss()
            Navigation.findNavController(requireView()).navigate(R.id.stab2Fragment)
        }

        difficultRouteButton.setOnClickListener {
            stabNavigate()
        }

        anyRouteButton.setOnClickListener {
            toWhereEditText.setText(dialogAdapter.items?.random()?.name ?: "")
            goSelectCountry()
        }

        weekendButton.setOnClickListener {
            stabNavigate()
        }

        hotTicketsButton.setOnClickListener {
            stabNavigate()
        }

        fromWhereEditText.text = binding.fromWhereEditText.text

        viewModel.popularDestination.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Success -> {
                    dialogAdapter.apply {
                        items = it.value
                        notifyDataSetChanged()
                    }
                }

                is UiState.Loading -> {

                }

                is UiState.Failure -> {

                }
            }
        }

        viewModel.loadPopularDestination()


        popularDestinationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = dialogAdapter
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
