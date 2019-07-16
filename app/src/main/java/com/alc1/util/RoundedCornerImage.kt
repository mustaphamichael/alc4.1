package com.alc1.util

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.alc1.R


/**
 * @author Okeke Paul
 * Created by paulex on 7/3/16.
 */
class RoundedCornerImage : android.support.v7.widget.AppCompatImageView {

    private var vBorderWidth: Int = 0

    constructor(context: Context) : super(context) {
        initDrawingTools()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initDrawingTools()
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RoundedCornerImage, 0, 0
        )
        try {
            vBorderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundedCornerImage_borderWidth, 0)
        } finally {
            typedArray.recycle()
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initDrawingTools()
    }

    private fun initDrawingTools() {

    }

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return

        if (width == 0 || height == 0) return

        val srcBitmap = (drawable as BitmapDrawable).bitmap

        val roundBitmap = this.getRoundedCornerBitmap(srcBitmap, width, height, vBorderWidth)

        canvas.drawBitmap(roundBitmap!!, 0f, 0f, null)
    }

    private fun getRoundedCornerBitmap(bitmap: Bitmap?, width: Int, height: Int, borderWidth: Int): Bitmap? {
        var bitmap = bitmap
        if (bitmap == null || bitmap.isRecycled) {
            return null
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)

        val w = bitmap!!.width
        val h = bitmap.height

        val canvasLayer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val vPaint = Paint(Paint.DITHER_FLAG)
        vPaint.isAntiAlias = true
        vPaint.shader = shader

        val canvas = Canvas(canvasLayer)
        val radius = if (w > h) h.toFloat() / 2f else w.toFloat() / 2f

        canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), radius, vPaint)

        vPaint.shader = null
        vPaint.style = Paint.Style.STROKE
        vPaint.color = Color.parseColor("#c02428")
        vPaint.strokeWidth = borderWidth.toFloat()
        //
        canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), radius - borderWidth / 2, vPaint)
        return canvasLayer
    }
}
