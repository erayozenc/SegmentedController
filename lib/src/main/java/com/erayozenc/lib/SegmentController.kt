package com.erayozenc.lib
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.ViewCompat

class SegmentController(
    context: Context,
    attrs: AttributeSet
): RadioGroup(context, attrs) {

    private var tabStrokeWidth = 2f.toPx()
    private var tabCornerRadius = 4f.toPx().toFloat()

    private var tabSelectedColor = Color.WHITE
    private var tabUnselectedColor = Color.LTGRAY

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SegmentController, 0, 0)
        tabStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.SegmentController_tab_strokeWidth, tabStrokeWidth)
        tabCornerRadius = typedArray.getDimension(R.styleable.SegmentController_tab_cornerRadius, tabCornerRadius)
        tabSelectedColor = typedArray.getColor(R.styleable.SegmentController_tab_selectedColor, tabSelectedColor)
        tabUnselectedColor = typedArray.getColor(R.styleable.SegmentController_tab_unselectedColor, tabUnselectedColor)

        this.orientation = HORIZONTAL
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val bgDrawable = GradientDrawable().apply {
          cornerRadius = tabCornerRadius
          setColor(tabUnselectedColor)
        }

        background = bgDrawable

        if (childCount < 1) throw IllegalArgumentException("SegmentController must have at least one SegmentTab!")

        for (i in 0 until childCount) {
            val child = this.getChildAt(i) as? RadioButton ?: return

            child.setButtonDrawable(android.R.color.transparent)
            child.gravity = Gravity.CENTER

            val layoutParams = child.layoutParams
            if (layoutParams is LayoutParams)
                layoutParams.weight = 1f

            val stateListDrawable = StateListDrawable()
            val selectedTabDrawable = GradientDrawable()
            val unselectedTabDrawable = GradientDrawable()
            var insetDrawable: InsetDrawable?

            when (i) {
                0 -> {
                    child.isChecked = true

                    selectedTabDrawable.cornerRadius = tabCornerRadius
                    selectedTabDrawable.setColor(tabSelectedColor)
                    insetDrawable = InsetDrawable(
                            selectedTabDrawable,
                            tabStrokeWidth,
                            tabStrokeWidth,
                            0,
                            tabStrokeWidth
                    )

                    unselectedTabDrawable.cornerRadii = setRadius(tabCornerRadius, 0f)
                    unselectedTabDrawable.setColor(tabUnselectedColor)
                }
                childCount - 1 -> {
                    selectedTabDrawable.cornerRadius = tabCornerRadius
                    selectedTabDrawable.setColor(tabSelectedColor)
                    insetDrawable = InsetDrawable(
                            selectedTabDrawable,
                            0,
                            tabStrokeWidth,
                            tabStrokeWidth,
                            tabStrokeWidth
                    )

                    unselectedTabDrawable.cornerRadii = setRadius(0f, tabCornerRadius)
                    unselectedTabDrawable.setColor(tabUnselectedColor)
                }
                else -> {

                    selectedTabDrawable.cornerRadius = tabCornerRadius
                    selectedTabDrawable.setColor(tabSelectedColor)
                    insetDrawable = InsetDrawable(
                            selectedTabDrawable,
                            0,
                            tabStrokeWidth,
                            0,
                            tabStrokeWidth
                    )

                    unselectedTabDrawable.setColor(tabUnselectedColor)
                }
            }

            val checkedState = IntArray(1) {android.R.attr.state_checked}
            val uncheckedState = IntArray(0)

            stateListDrawable.addState(checkedState, insetDrawable)
            stateListDrawable.addState(uncheckedState, unselectedTabDrawable)

            ViewCompat.setBackground(child,stateListDrawable)
        }
    }

    fun setCornerRadius(dp: Float) {
        tabCornerRadius = dp.toPx().toFloat()
        onFinishInflate()
    }

    fun setStrokeWidth(dp: Float) {
        tabStrokeWidth = dp.toPx()
        onFinishInflate()
    }

    fun setSelectedTabColor(color: Int) {
        tabSelectedColor = color
        onFinishInflate()
    }

    fun setUnselectedTabColor(color: Int) {
        tabUnselectedColor = color
        onFinishInflate()
    }

    private fun setRadius(leftRadius: Float, rightRadius: Float): FloatArray {
        val radius = FloatArray(8)
        radius[0] = leftRadius
        radius[1] = leftRadius
        radius[2] = rightRadius
        radius[3] = rightRadius
        radius[4] = rightRadius
        radius[5] = rightRadius
        radius[6] = leftRadius
        radius[7] = leftRadius
        return radius
    }

}

fun Float.toPx() : Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}