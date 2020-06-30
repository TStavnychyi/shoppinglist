package com.stavnychyy.shoppinglist.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.stavnychyy.shoppinglist.common.R
import com.stavnychyy.shoppinglist.common.extensions.dpToPx
import com.stavnychyy.shoppinglist.common.extensions.spToPx
import com.stavnychyy.shoppinglist.domain.ShoppingListCompletedTasks

private const val CHART_START_ANGLE = -90F

class CompletedTasksChartView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

  private val completedTasksColor = ContextCompat.getColor(context, R.color.msla_common_grey_1)
  private val completedTasksTextSize = 12.spToPx()
  private val completedTasksBounds = Rect()

  private val chartBackgroundColorId = ContextCompat.getColor(context, R.color.msla_colorAccent)
  private val chartColorId = ContextCompat.getColor(context, R.color.msla_colorPrimary)
  private val chartThickness = 5.dpToPx()
  private val chartArea = RectF()

  private var chartCenter: PointF = PointF(0F, 0F)
  private var chartRadius = 0F
  private var chartBackgroundRadius = 0F
  private var chartEndAngle = 0F
  private var tasksCount = "100"

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
  private var currentTasksMoveValue = 0f
  private var currentTasksValue = "0/0"

  fun applyViewEntity(completedTasks: ShoppingListCompletedTasks) {
    val averageCompletedTasksPercentage: Float = completedTasks.completedTasks.toFloat() / completedTasks.total
    chartEndAngle = (averageCompletedTasksPercentage * 360)
    currentChartAngle = chartEndAngle
    currentTasksValue = "${completedTasks.completedTasks}/${completedTasks.total}"
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val height = MeasureSpec.getSize(heightMeasureSpec)
    val width = MeasureSpec.getSize(widthMeasureSpec)
    chartRadius = width / 2F - chartThickness
    chartBackgroundRadius = chartRadius + chartThickness / 2
    chartCenter.set(width / 2F, height / 2F)
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
    drawCompletedTasksValue(canvas)
  }

  private fun drawCompletedTasksValue(canvas: Canvas) {
    textPaint.apply {
      color = completedTasksColor
      alpha = 255
      textSize = completedTasksTextSize
    }

    textPaint.getTextBounds(tasksCount, 0, tasksCount.length, completedTasksBounds)
    canvas.drawText(
      currentTasksValue,
      chartCenter.x,
      chartCenter.y - completedTasksBounds.exactCenterY() + currentTasksMoveValue,
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
