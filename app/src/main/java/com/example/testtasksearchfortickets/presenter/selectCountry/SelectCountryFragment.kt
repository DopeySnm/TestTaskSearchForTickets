package com.example.testtasksearchfortickets.presenter.selectCountry

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.data.state.UiState
import com.example.testtasksearchfortickets.databinding.FragmentSelectCountryBinding
import com.example.testtasksearchfortickets.di.appComponent
import com.example.testtasksearchfortickets.di.viewModel.ViewModelFactory
import com.example.testtasksearchfortickets.utils.DateUtils
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import java.util.Date
import javax.inject.Inject

class SelectCountryFragment : Fragment(R.layout.fragment_select_country) {
    private val binding: FragmentSelectCountryBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SelectCountryViewModel by viewModels { viewModelFactory }

    private val adapter = ListDelegationAdapter(
        SelectCountryDelegates.ticketsOffersHorizontalDelegate
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ticketsOffers.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    adapter.apply {
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

        viewModel.loadOffers()
        initializeUi()
    }

    private fun initializeUi() {
        initializeBackDate()
        initializeStartDate()
        initializeSwapButton()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        binding.ticketsOffersRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                context
            )
            adapter = this@SelectCountryFragment.adapter
        }
    }

    private fun initializeSwapButton() {
        binding.swapImageView.setOnClickListener {
            val temp = binding.toWhereEditText.text
            binding.toWhereEditText.text = binding.fromWhereEditText.text
            binding.fromWhereEditText.text = temp
        }
    }

    private fun textColoring(start: Int, end: Int, text: String, color: Int): Spannable {
        val spannable: Spannable = SpannableString(text)

        spannable.setSpan(
            ForegroundColorSpan(color),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    private fun getSpannableDateText(date: Date): Spannable {
        val dayAndMonth = "${DateUtils.getDay(date)} ${DateUtils.getMonth(date)}"
        val dateText = "$dayAndMonth, ${getString(DateUtils.getStringIdDayOfWeek(date))}"

        return textColoring(
            dayAndMonth.length,
            dateText.length,
            dateText,
            Color.GRAY
        )
    }

    private fun initializeBackDate() {
        binding.backDateButton.text = getString(R.string.back)
        binding.backDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dpd = DatePickerDialog(
                requireContext(), { _, year, monthOfYear, dayOfMonth ->
                    val spannable = getSpannableDateText(DateUtils.getData(year, monthOfYear, dayOfMonth))
                    binding.backDateButton.setText(spannable, TextView.BufferType.SPANNABLE)
                    binding.backDateButton.setCompoundDrawables(null, null, null, null)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
    }

    private fun initializeStartDate() {
        binding.startDateButton.text = getSpannableDateText(DateUtils.getCurrentDate())
        binding.startDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dpd = DatePickerDialog(
                requireContext(), { _, year, monthOfYear, dayOfMonth ->
                    val spannable = getSpannableDateText(DateUtils.getData(year, monthOfYear, dayOfMonth))
                    binding.startDateButton.setText(spannable, TextView.BufferType.SPANNABLE)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

}
