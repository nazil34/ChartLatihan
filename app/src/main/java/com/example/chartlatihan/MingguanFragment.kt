package com.example.chartlatihan

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class MingguanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mingguan, container, false)

        val weeklyChart: BarChart = view.findViewById(R.id.weekly_chart)
        setupBarChart(weeklyChart)

        return view
    }

    private fun setupBarChart(chart: BarChart) {
        // 1. DATA: Hanya ada data untuk Sabtu (posisi 5) dan Minggu (posisi 6)
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 0f)) // sen
        entries.add(BarEntry(1f, 0f)) // Sel
        entries.add(BarEntry(2f, 0f)) // rab
        entries.add(BarEntry(3f, 0f)) // kam
        entries.add(BarEntry(4f, 0f)) // jum
        entries.add(BarEntry(5f, 1600f)) // Sab
        entries.add(BarEntry(6f, 280f))  // Min

        // 2. DATASET: Mengatur data dan warna yang berbeda untuk setiap bar
        val dataSet = BarDataSet(entries, "Weekly Data")
        // Atur warna sesuai gambar: biru muda transparan untuk Sabtu, biru solid untuk Minggu
        dataSet.colors = listOf(
            "#2196F3".toColorInt(), // Warna biru muda untuk Sabtu
            "#DBEEFD".toColorInt()  // Warna biru solid untuk Minggu
        )

        // nonaktifkan nilai di ats bar
        dataSet.setDrawValues(false)

        // menonaktifkan highlight ketika bar diklik
        dataSet.isHighlightEnabled = true

        dataSet.highLightAlpha = 0

        dataSet.highLightColor = Color.TRANSPARENT

        // nonaktifkna legenda/ keterangan "weekly data" beserta warnanya
        chart.legend.isEnabled = false

        // 3. BAR DATA: Gabungkan dataset ke dalam BarData
        val barData = BarData(dataSet)
        barData.barWidth = 0.6f // Atur lebar bar agar tidak terlalu tebal

        chart.data = barData

        // 4. KONFIGURASI SUMBU (AXIS)
        // Label untuk sumbu-X
        val days = arrayOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
        val xAxisFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: com.github.mikephil.charting.components.AxisBase?): String {
                return days.getOrNull(value.toInt()) ?: ""
            }
        }

        // Sumbu X (Horizontal)
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = xAxisFormatter
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false) // Menghilangkan garis grid vertikal
        xAxis.labelCount = days.size

        // Sumbu Y Kiri (Vertikal)
        val yAxisLeft = chart.axisLeft
        yAxisLeft.axisMinimum = 0f // Mulai dari 0
        yAxisLeft.axisMaximum = 2000f // Hingga 2000
        yAxisLeft.setDrawGridLines(true) // Menampilkan garis grid horizontal
        val labelCount = 6
        yAxisLeft.setLabelCount(labelCount, true)
        yAxisLeft.setDrawAxisLine(false)

        // Sumbu Y Kanan (dinonaktifkan agar bersih)
        chart.axisRight.isEnabled = false

        // memasang marker untuk bar yang diklik
        val marker = MarkerBarChart(requireContext(), R.layout.component_marker_bar_chart)
        chart.marker = marker

        chart.isHighlightPerTapEnabled = true

        // nonaktifkan teks deskripsi
        chart.description.isEnabled = false

        // 5. TAMPILKAN GRAFIK
        chart.invalidate() // Wajib dipanggil untuk me-refresh grafik
    }

}