package com.router.civilization.library.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by tian
 * on 2019/8/8.
 */

@SuppressLint("AppCompatCustomView")
class CircleImageView : ImageView {
    private var mPaint: Paint? = null //画笔

    private var mRadius: Int = 0 //圆形图片的半径

    private var mScale: Float = 0.toFloat() //图片的缩放比例

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //因为是圆形图片，所以应该让宽高保持一致
        val size = Math.min(measuredWidth, measuredHeight)
        mRadius = size / 2
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {

        mPaint = Paint()
        val bitmap = drawableToBitmap(drawable)

        //初始化BitmapShader，传入bitmap对象
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        //计算缩放比例
        mScale = mRadius * 2.0f / Math.min(bitmap.height, bitmap.width)

        val matrix = Matrix()
        matrix.setScale(mScale, mScale)
        bitmapShader.setLocalMatrix(matrix)


        mPaint!!.shader = bitmapShader

        //画圆形，指定好中心点坐标、半径、画笔
        canvas.drawCircle(mRadius.toFloat(), mRadius.toFloat(), mRadius.toFloat(), mPaint!!)
    }

    //写一个drawble转BitMap的方法
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val w = drawable.intrinsicWidth
        val h = drawable.intrinsicHeight
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, w, h)
        drawable.draw(canvas)
        return bitmap
    }
}
