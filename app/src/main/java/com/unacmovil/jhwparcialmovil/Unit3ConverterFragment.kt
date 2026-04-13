package com.unacmovil.jhwparcialmovil

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.DecimalFormat

class Unit3ConverterFragment : Fragment(R.layout.fragment_unit3_converter) {

	private val currencyInCop = mapOf(
		"COP" to 1.0,
		"USD" to 4000.0,
		"EUR" to 4350.0,
		"MXN" to 235.0
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val input = view.findViewById<EditText>(R.id.editTextCurrencyValue)
		val fromSpinner = view.findViewById<Spinner>(R.id.spinnerCurrencyFrom)
		val toSpinner = view.findViewById<Spinner>(R.id.spinnerCurrencyTo)
		val convertButton = view.findViewById<Button>(R.id.buttonCurrencyConvert)
		val resultText = view.findViewById<TextView>(R.id.textCurrencyResult)

		val adapter = ArrayAdapter.createFromResource(
			requireContext(),
			R.array.currency_units,
			android.R.layout.simple_spinner_item
		)
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		fromSpinner.adapter = adapter
		toSpinner.adapter = adapter
		toSpinner.setSelection(1)

		convertButton.setOnClickListener {
			val value = input.text.toString().trim().replace(',', '.').toDoubleOrNull()
			if (value == null) {
				Toast.makeText(requireContext(), getString(R.string.error_invalid_value), Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			val fromUnit = fromSpinner.selectedItem.toString()
			val toUnit = toSpinner.selectedItem.toString()
			val converted = convertCurrency(value, fromUnit, toUnit)

			resultText.text = getString(R.string.result_format, formatNumber(converted), toUnit)
		}
	}

	private fun convertCurrency(value: Double, fromUnit: String, toUnit: String): Double {
		val fromRate = currencyInCop[fromUnit] ?: return value
		val toRate = currencyInCop[toUnit] ?: return value
		return (value * fromRate) / toRate
	}

	private fun formatNumber(value: Double): String = DecimalFormat("#,##0.######").format(value)
}