package com.serdar.photofilter_customview.ui.view


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.serdar.photofilter_customview.R
import com.serdar.photofilter_customview.data.FilteredImages

class CustomFilterView : View {
    private var mBitmap: Bitmap? = null
    private var red:Float=0f
    private var green:Float=0f
    private var blue:Float=0f
    private var imageView:Int=R.drawable.un_filtered

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val colorMatrix=ColorMatrix().apply {
            setSaturation(0f)
            set(floatArrayOf(
                0f,red,0f,0f,0f,//red
                0f,green,0f,0f,0f,//green
                blue,0f,0f,0f,0f,//blue
                0f,0f,0f,1f,0f//alpha
            ))
        }
        val filteredColorPaint=Paint().apply {
            colorFilter= ColorMatrixColorFilter(colorMatrix)

        }
        mBitmap = BitmapFactory.decodeResource(resources, imageView)

        mBitmap?.let {
            canvas?.drawBitmap(it,null,RectF((measuredWidth.toFloat()-measuredWidth/2f)/2f,
                (measuredHeight.toFloat()-measuredHeight/2f)/2f,
                measuredWidth.toFloat()-(measuredWidth.toFloat()-measuredWidth/2f)/2f,
                measuredHeight.toFloat()-(measuredHeight.toFloat()-measuredHeight/2f)/2f),
                filteredColorPaint)
        }


    }
    fun setFilter(value: FilteredImages){
            green=value.filtered1Value
            red=value.filtered2Value
            blue=value.filtered3Value
            imageView=value.imageView
        invalidate()
    }

    private fun reSizeBitmap(){
        val width=measuredWidth.toFloat()/ mBitmap!!.width
        val height=measuredHeight.toFloat()/mBitmap!!.height
        val scale= width.coerceAtMost(height)

        val resizeWidth=mBitmap!!.width.toFloat()*scale
        val resizeHeight=mBitmap!!.height.toFloat()*scale
    }
}