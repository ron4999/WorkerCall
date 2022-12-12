package com.va.workercall.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.SystemClock
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.blurImage(bitmap: Bitmap, radius: Float, ratio: Int): Bitmap? {
    return try {
        val mInBitmap =
            Bitmap.createScaledBitmap(bitmap, bitmap.width / ratio, bitmap.height / ratio, true)
        val mOutBitmap = Bitmap.createBitmap(
            mInBitmap!!.width, mInBitmap.height, mInBitmap.config
        )
        val mRenderScript = RenderScript.create(this)
        val input = Allocation.createFromBitmap(mRenderScript, mInBitmap)
        val output = Allocation.createTyped(mRenderScript, input.type)
        val script = ScriptIntrinsicBlur.create(
            mRenderScript,
            Element.U8_4(mRenderScript)
        )
        script.setRadius(radius)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(mOutBitmap)
        if (!mInBitmap.isRecycled) {
            mInBitmap.recycle()
        }
        mOutBitmap
    } catch (e: OutOfMemoryError) {
        null
    } catch (e: Exception) {
        null
    }
}

fun Context.getActionBarHeight(): Int {
    val tv = TypedValue()
    if (this.theme?.resolveAttribute(
            android.R.attr.actionBarSize,
            tv,
            true
        ) == true
    ) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return 0
}

fun View.changeBackgroundColor(newColor: Int) {
    setBackgroundColor(
        ContextCompat.getColor(
            context,
            newColor
        )
    )
}

fun ImageView.setTintColor(@ColorRes color: Int) {
    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
}

fun TextView.changeTextColor(newColor: Int) {
    setTextColor(
        ContextCompat.getColor(
            context,
            newColor
        )
    )
}

fun View.animAlphaHide() {
    animate()
        .alpha(0f)
        .setDuration(300)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = View.GONE
            }
        })
}

fun View.animAlphaShow() {
    animate()
        .alpha(1f)
        .setDuration(300)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
}

fun View.isShow() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.inv() {
    visibility = View.INVISIBLE
}


fun View.setPreventDoubleClick(debounceTime: Long = 500, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

private var lastClickTime: Long = 0
fun View.setPreventDoubleClickItem(debounceTime: Long = 500, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.setPreventDoubleClickScaleView(debounceTime: Long = 500, action: () -> Unit) {
    setOnTouchListener(object : View.OnTouchListener {
        private var lastClickTime: Long = 0
        private var rect: Rect? = null

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            fun setScale(scale: Float) {
                v.scaleX = scale
                v.scaleY = scale
            }

            if (event.action == MotionEvent.ACTION_DOWN) {
                //action down: scale view down
                rect = Rect(v.left, v.top, v.right, v.bottom)
                setScale(0.9f)
            } else if (rect != null && !rect!!.contains(
                    v.left + event.x.toInt(),
                    v.top + event.y.toInt()
                )
            ) {
                //action moved out
                setScale(1f)
                return false
            } else if (event.action == MotionEvent.ACTION_UP) {
                //action up
                setScale(1f)
                //handle click too fast
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                } else {
                    lastClickTime = SystemClock.elapsedRealtime()
                    action()
                }
            } else {
                //other
            }

            return true
        }
    })
}