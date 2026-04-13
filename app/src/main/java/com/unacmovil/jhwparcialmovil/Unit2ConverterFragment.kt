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

class Unit2ConverterFragment : Fragment(R.layout.fragment_unit2_converter) {

	private val massInKilograms = mapOf(
		"Tonelada (t)" to 1000.0,
		"Kilogramo (kg)" to 1.0,
		"Gramo (g)" to 0.001,
		"Libra (lb)" to 0.45359237,
		"Onza (oz)" to 0.028349523125
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val input = view.findViewById<EditText>(R.id.editTextMassValue)
		val fromSpinner = view.findViewById<Spinner>(R.id.spinnerMassFrom)
		val toSpinner = view.findViewById<Spinner>(R.id.spinnerMassTo)
		val convertButton = view.findViewById<Button>(R.id.buttonMassConvert)
		val resultText = view.findViewById<TextView>(R.id.textMassResult)

		val adapter = ArrayAdapter.createFromResource(
			requireContext(),
			R.array.mass_units,
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
			val converted = convertMass(value, fromUnit, toUnit)

			resultText.text = getString(R.string.result_format, formatNumber(converted), toUnit)
		}
	}

	private fun convertMass(value: Double, fromUnit: String, toUnit: String): Double {
		val fromFactor = massInKilograms[fromUnit] ?: return value
		val toFactor = massInKilograms[toUnit] ?: return value
		return (value * fromFactor) / toFactor
	}

	private fun formatNumber(value: Double): String = DecimalFormat("#,##0.######").format(value)
}