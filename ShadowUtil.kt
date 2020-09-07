package com.seoultech.livingtogether_android.util

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.Gravity
import android.view.View
import android.view.View.LAYER_TYPE_SOFTWARE
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


object ShadowUtil {

    fun generateBackgroundWithShadow(view: View, @ColorRes backgroundColor: Int, cornerRadius: Float,
                                     @ColorRes shadowColor: Int, elevation: Int, shadowGravity: Int): Drawable {

        val shadowColorValue = ContextCompat.getColor(view.context, shadowColor)
        val backgroundColorValue = ContextCompat.getColor(view.context, backgroundColor)

        val outerRadius = floatArrayOf(
            cornerRadius, cornerRadius, cornerRadius,
            cornerRadius, cornerRadius, cornerRadius, cornerRadius,
            cornerRadius
        )

        val backgroundPaint = Paint().apply {
            style = Paint.Style.FILL
            setShadowLayer(cornerRadius, 0f, 0f, 0)
        }

        val shapeDrawablePadding = Rect().apply {
            left = elevation
            right = elevation
        }

        val dx = when (shadowGravity) {
            Gravity.CENTER -> {
                shapeDrawablePadding.top = elevation
                shapeDrawablePadding.bottom = elevation
                0
            }
            Gravity.TOP -> {
                shapeDrawablePadding.top = elevation * 2
                shapeDrawablePadding.bottom = elevation
                -1 * elevation / 3
            }
            Gravity.BOTTOM -> {
                shapeDrawablePadding.top = elevation
                shapeDrawablePadding.bottom = elevation * 2
                elevation / 3
            }
            else -> {
                shapeDrawablePadding.top = elevation
                shapeDrawablePadding.bottom = elevation * 2
                elevation / 3
            }
        }

        val shapeDrawable = ShapeDrawable().apply {
            setPadding(shapeDrawablePadding)
            paint.color = backgroundColorValue
            paint.setShadowLayer(cornerRadius / 3, 0f, dx.toFloat(), shadowColorValue)
            view.setLayerType(LAYER_TYPE_SOFTWARE, this.paint)
            shape = RoundRectShape(outerRadius, null, null)
        }

        return LayerDrawable(arrayOf<Drawable>(shapeDrawable)).apply {
            setLayerInset(0, elevation, elevation * 2, elevation, elevation * 2)
        }
    }
}