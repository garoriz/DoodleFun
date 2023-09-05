package com.example.doodlefun.feature.main.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.doodlefun.Storage
import com.example.doodlefun.TypeOfInstrument


class MySurfaceView(context: Context?) : View(context)   {
    constructor(context: Context?, attrs: AttributeSet) : this(context)
    constructor(context: Context?, attrs: AttributeSet, defStyle: Int):this(context)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path: Path?

    init {
        path = Path()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        if (Storage.typeOfInstrument == TypeOfInstrument.PENCIL)
            paint.color = Color.RED
        else
            paint.color = Color.WHITE
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path?.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                path?.lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                path?.lineTo(event.x, event.y)
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        if (path != null) canvas.drawPath(path, paint)
    }
}