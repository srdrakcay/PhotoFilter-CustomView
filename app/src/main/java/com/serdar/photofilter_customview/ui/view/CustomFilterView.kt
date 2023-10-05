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

class CustomFilterView : View {
    private var lineXLeft=0f
    private var lineXRight=0f
    private var lineYPos=0f
    private var lineHeight=0f
    private var lineWith=0f
    private var mBitmap: Bitmap? = null

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
        val lineMarginHorizontal=dpToPx(LINE_MARGIN_HORIZONTAL_DP)
        lineXLeft=lineMarginHorizontal
        lineXRight=w/4f
        lineYPos=h* LINE_Y_POS_FRACTION
        lineHeight=dpToPx(LINE_HEIGHT_DP)
        lineWith=w.toFloat()


    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val colorMatrix=ColorMatrix().apply {
            setSaturation(0f)
            set(floatArrayOf(
                0f,1f,0f,0f,0f,//red
                0f,1f,0f,0f,0f,//green
                0f,0f,0f,3f,0f,//blue
                0f,0f,0f,3f,2f//alpha
            ))
        }
        val filteredColorPaint=Paint().apply {
            colorFilter= ColorMatrixColorFilter(colorMatrix)

        }
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.un_filtered)

        canvas?.drawBitmap(mBitmap!!,null,RectF(0f,0f,measuredWidth.toFloat(),measuredHeight.toFloat()),filteredColorPaint)

    }

    companion object {
        const val LINE_Y_POS_FRACTION = 0.1f
        const val LINE_MARGIN_HORIZONTAL_DP = 20f
        const val LINE_HEIGHT_DP = 5f
    }
    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics,
        )
    }
}