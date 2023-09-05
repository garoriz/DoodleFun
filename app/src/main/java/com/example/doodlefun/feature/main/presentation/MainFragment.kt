package com.example.doodlefun.feature.main.presentation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.doodlefun.R
import com.kyanogen.signatureview.SignatureView
import yuku.ambilwarna.AmbilWarnaDialog


class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var signatureView: SignatureView

    var defaultcolor = 0 // стандартный цвет который мы используем на данный момент


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signatureView =
            view.findViewById(R.id.signature_view);//метод findViewById делает возможным поиск по айди элемента в XML
        val imgEraser: ImageButton = view.findViewById(R.id.btnEraser);
        val imgColor: ImageButton = view.findViewById(R.id.btnColor);
        val seekBar: SeekBar = view.findViewById(R.id.penSize);

        defaultcolor = ContextCompat.getColor(requireContext(), R.color.black);//просто черный цвет

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                signatureView.penSize = i.toFloat();//смена размера кисти через функции библиотеки
                seekBar.max = 50;//максимальное значение seekbara
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        imgEraser.setOnClickListener { signatureView.clearCanvas() }

        imgColor.setOnClickListener {
            openColorPicker()
        }
    }

    fun openColorPicker() {
        val ambilWarnaDialog = AmbilWarnaDialog(
            requireContext(),
            defaultcolor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    defaultcolor = color
                    signatureView.penColor = color
                }

            })
        ambilWarnaDialog.show()
    }
}