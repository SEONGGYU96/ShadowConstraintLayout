package com.seoultech.livingtogether_android.library

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.seoultech.livingtogether_android.R
import com.seoultech.livingtogether_android.util.ShadowUtil
import com.seoultech.livingtogether_android.util.toPixel


class ShadowConstraintLayout : ConstraintLayout {

    companion object {
        private const val ROUND_RADIUS = 5
        private const val ELEVATION = 5
    }

    constructor(context: Context?) : super(context) {
        initBackground()
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        initBackground()
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        initBackground()
    }

    private fun initBackground() {
        background = ShadowUtil.generateBackgroundWithShadow(this, R.color.colorWhite,
            ROUND_RADIUS.toPixel(), R.color.ColorTempShadow, ELEVATION.toPixel().toInt(), Gravity.BOTTOM)
    }
}