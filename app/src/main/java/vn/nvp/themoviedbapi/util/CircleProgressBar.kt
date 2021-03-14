package vn.nvp.themoviedbapi.util

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import vn.nvp.themoviedbapi.R
import kotlin.math.min

/**
 * Create by Nguyen Van Phuc on 3/14/21
 */
class CircleProgressBar : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    /**
     * ProgressBar's line thickness
     */
    private var strokeWidth = 4f
    private var progress = 0f
    private var max = 100

    /**
     * Start the progress at 12 o'clock
     */
    private val startAngle = -90F
    private var color: Int = Color.DKGRAY
    private var rectF: RectF? = null
    private var backgroundPaint: Paint? = null
    private var foregroundPaint: Paint? = null
    private var textPaint: Paint? = null
    private var shader: Shader? = null


    private fun init(context: Context, attrs: AttributeSet) {
        rectF = RectF()
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleProgressBar,
            0, 0
        ).also {
            strokeWidth = it.getDimension(
                R.styleable.CircleProgressBar_progressBarThickness,
                strokeWidth
            )
            progress = it.getFloat(R.styleable.CircleProgressBar_progress, progress)
            color = it.getInt(R.styleable.CircleProgressBar_progressbarColor, color)
        }.recycle()

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = this@CircleProgressBar.color
            style = Paint.Style.STROKE
            strokeWidth = this@CircleProgressBar.strokeWidth
        }
        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = this@CircleProgressBar.strokeWidth
        }
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GREEN
            textSize = 28F
            textAlign = Paint.Align.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    internal fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = min(width, height)
        setMeasuredDimension(min, min)
        rectF?.let {
            it[0 + strokeWidth / 2, 0 + strokeWidth / 2, min - strokeWidth / 2] =
                min - strokeWidth / 2
            if (shader == null) {
                shader = SweepGradient(
                    it.centerX(),
                    it.centerY(),
                    intArrayOf(
                        ResourcesCompat.getColor(resources, R.color.saffron, null),
                        ResourcesCompat.getColor(resources, R.color.atlantis, null),
                        Color.GREEN,
                    ),
                    floatArrayOf(0F, 0.5F, 1F)
                ).also { sShader ->
                    // Set start angle for Gradient
                    sShader.setLocalMatrix(Matrix().apply {
                        preRotate(startAngle, it.centerX(), it.centerY())
                    })
                }
                foregroundPaint?.shader = shader
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rectF?.let {
            canvas?.drawOval(it, backgroundPaint!!)
            val angle = 360 * progress / max
            canvas?.drawArc(it, startAngle, angle, false, foregroundPaint!!)
            textPaint?.let { tp ->
                canvas?.drawText(
                    "${progress.toInt()} %",
                    it.centerX(),
                    it.centerY() - (tp.descent() + tp.ascent()) / 2,
                    tp
                )
            }
        }
    }
}
