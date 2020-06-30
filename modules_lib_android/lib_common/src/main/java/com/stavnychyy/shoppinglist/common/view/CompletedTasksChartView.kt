package com.stavnychyy.shoppinglist.common.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.stavnychyy.shoppinglist.common.R
import com.stavnychyy.shoppinglist.common.extensions.dpToPx
import com.stavnychyy.shoppinglist.common.extensions.spToPx
import com.stavnychyy.shoppinglist.domain.ShoppingListCompletedTasks

private const val CHART_START_ANGLE = -90F

class CompletedTasksChartView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val averageRatingColor = ContextCompat.getColor(context, R.color.msla_common_grey_1)
    private val averageRatingTextSize = 12.spToPx()
    private val averageRateBounds = Rect()

    private val chartBackgroundColorId = ContextCompat.getColor(context, R.color.msla_colorAccent)
    private val chartColorId = ContextCompat.getColor(context, R.color.msla_colorPrimary)
    private val chartThickness = 5.dpToPx()
    private val chartArea = RectF()

    private var chartCenter: PointF = PointF(0F, 0F)
    private var chartRadius = 0F
    private var chartBackgroundRadius = 0F
    private var ratingCountYPosition = 0F

    private var chartEndAngle = 0F
    private var ratingCount = "100"

    private val chartPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = chartThickness
    }
    private val textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        flags = TextPaint.ANTI_ALIAS_FLAG
    }

    private var currentChartAngle = 0F
    private var currentRatingAverageMoveValue = 0f
    private var currentRatingValue = "0/0"

    fun applyViewEntity(completedTasks: ShoppingListCompletedTasks) {
        val averageRatingPercentage: Float =
            completedTasks.completedTasks.toFloat() / completedTasks.total
        chartEndAngle = (averageRatingPercentage * 360)
        currentChartAngle = chartEndAngle
        currentRatingValue = "${completedTasks.completedTasks}/${completedTasks.total}"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        chartRadius = width / 2F - chartThickness
        chartBackgroundRadius = chartRadius + chartThickness / 2
        chartCenter.set(width / 2F, height / 2F)
        ratingCountYPosition = chartCenter.y + (averageRatingTextSize / 2)
        val halfChartThickness = chartThickness / 2
        chartArea.set(
            halfChartThickness,
            halfChartThickness,
            width.toFloat() - halfChartThickness,
            height.toFloat() - halfChartThickness
        )
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawChartBackground(canvas)
        drawChart(canvas)
        drawAverageRatingValue(canvas)
    }

    private fun drawAverageRatingValue(canvas: Canvas) {
        textPaint.apply {
            color = averageRatingColor
            alpha = 255
            textSize = averageRatingTextSize
        }

        textPaint.getTextBounds(ratingCount, 0, ratingCount.length, averageRateBounds)
        canvas.drawText(
            currentRatingValue,
            chartCenter.x,
            chartCenter.y - averageRateBounds.exactCenterY() + currentRatingAverageMoveValue,
            textPaint
        )
    }

    private fun drawChartBackground(canvas: Canvas) {
        chartPaint.color = chartBackgroundColorId
        canvas.drawCircle(chartCenter.x, chartCenter.y, chartBackgroundRadius, chartPaint)
    }

    private fun drawChart(canvas: Canvas) {
        chartPaint.color = chartColorId
        canvas.drawArc(chartArea, CHART_START_ANGLE, currentChartAngle, false, chartPaint)
    }
}
