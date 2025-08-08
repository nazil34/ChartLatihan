package com.example.chartlatihan

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.DecimalFormat

@SuppressLint("ViewConstructor")
class MarkerBarChart(context: Context, layoutResource: Int): MarkerView(context, layoutResource) {

    private val tvContent: TextView = findViewById(R.id.tvContent)
    private val format = DecimalFormat("###,###,###")

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e != null) {
            tvContent.text = format.format(e.y.toInt())
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        // Offset ini mungkin perlu sedikit disesuaikan agar panah menunjuk tepat
        // ke tengah atas bar. Nilai default ini biasanya sudah cukup baik.
        return MPPointF(-(width / 2f), -height.toFloat())
    }
}