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

class Unit1ConverterFragment : Fragment(R.layout.fragment_unit1_converter) {

	private val lengthInMeters = mapOf(
		"Kilometro (km)" to 1000.0,
		"Metro (m)" to 1.0,
		"Centimetro (cm)" to 0.01,
		"Milimetro (mm)" to 0.001,
		"Pulgada (in)" to 0.0254,
		"Pie (ft)" to 0.3048
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val input = view.findViewById<EditText>(R.id.editTextLengthValue)
		val fromSpinner = view.findViewById<Spinner>(R.id.spinnerLengthFrom)
		val toSpinner = view.findViewById<Spinner>(R.id.spinnerLengthTo)
		val convertButton = view.findViewById<Button>(R.id.buttonLengthConvert)
		val resultText = view.findViewById<TextView>(R.id.textLengthResult)

		val adapter = ArrayAdapter.createFromResource(
			requireContext(),
			R.array.length_units,
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
			val converted = convertLength(value, fromUnit, toUnit)

			resultText.text = getString(R.string.result_format, formatNumber(converted), toUnit)
		}
	}

	private fun convertLength(value: Double, fromUnit: String, toUnit: String): Double {
		val fromFactor = lengthInMeters[fromUnit] ?: return value
		val toFactor = lengthInMeters[toUnit] ?: return value
		return (value * fromFactor) / toFactor
	}

	private fun formatNumber(value: Double): String = DecimalFormat("#,##0.######").format(value)
}