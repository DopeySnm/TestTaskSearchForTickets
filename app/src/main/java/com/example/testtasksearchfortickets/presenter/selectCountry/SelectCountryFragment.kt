package com.example.testtasksearchfortickets.presenter.selectCountry

import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.FragmentSelectCountryBinding
import com.example.testtasksearchfortickets.utils.DateUtils
import java.util.Date


class SelectCountryFragment : Fragment(R.layout.fragment_select_country) {
    private val binding: FragmentSelectCountryBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUi()
    }

    private fun initializeUi() {
        initializeBackDate()
        initializeStartDate()
        initializeSwapButton()
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

}
