package com.example.anmol_5732.mycanvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup


/**
 * Created by anmol-5732 on 05/01/18.
 */
class CanvasView(context: Context) : View(context) {
    private var paint: Paint
    private var path: Path = Path()
    private var initX: Float = 0.0f
    private var initY: Float = 0.0f

    init {
        this.setDrawingCacheEnabled(true);
        paint = Paint()
        paint.color = Color.argb(255, 100, 200, 0)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
        paint.isDither = true
        path.addRect(0f, 0f, 200f, 200f, Path.Direction.CW)
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
        setBackgroundColor(Color.YELLOW)
        layoutParams = ViewGroup.LayoutParams(200, 200)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                paint.color = Color.WHITE
            }
            MotionEvent.ACTION_UP -> {
                paint.color = Color.GREEN
            }
            MotionEvent.ACTION_MOVE -> {
                translationX = translationX + (event.rawX - initX) / DemoView.scale
                translationY = translationY + (event.rawY - initY) / DemoView.scale
            }
        }
        initX = event.rawX
        initY = event.rawY
        invalidate()
        return true
    }

}