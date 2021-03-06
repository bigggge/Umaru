package cc.haoduoyu.umaru.utils.ui;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cc.haoduoyu.umaru.R;
import cc.haoduoyu.umaru.model.Weather;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by XP on 2016/3/21.
 */
public class ChartUtils {


    public static void showChart(Context context, LineChartView chartView, Weather.HeWeather heWeather, boolean refresh) {
        List<AxisValue> axisXValues = new ArrayList<>();//X轴线
        List<AxisValue> axisYValues = new ArrayList<>();//Y轴线
        List<PointValue> maxValues = new ArrayList<>();//最高温度
        List<PointValue> minValues = new ArrayList<>();//最低温度
        float maxMax = Float.parseFloat(heWeather.getDailyForecast().get(0).getTmp().getMax());//最大值中的最大值
        float minMin = Float.parseFloat(heWeather.getDailyForecast().get(0).getTmp().getMin());//最小值中的最小值
        ;

        for (int i = 0; i < 7; ++i) {
            maxValues.add(new PointValue(i, Float.parseFloat(heWeather.getDailyForecast().get(i).getTmp().getMax())));
            minValues.add(new PointValue(i, Float.parseFloat(heWeather.getDailyForecast().get(i).getTmp().getMin())));
            maxMax = Math.max(Float.parseFloat(heWeather.getDailyForecast().get(i).getTmp().getMax()), maxMax);
            minMin = Math.min(Float.parseFloat(heWeather.getDailyForecast().get(i).getTmp().getMin()), minMin);
        }

        axisXValues.add(new AxisValue(0).setLabel(context.getString(R.string.today)));
        axisXValues.add(new AxisValue(1).setLabel(context.getString(R.string.tomorrow)));
        axisXValues.add(new AxisValue(2).setLabel(context.getString(R.string.after_tomorrow)));
        axisYValues.add(new AxisValue(0).setLabel("25"));
        axisYValues.add(new AxisValue(1).setLabel("10"));
        axisYValues.add(new AxisValue(2).setLabel("0"));
        for (int i = 3; i < 6; ++i) {
            axisXValues.add(new AxisValue(i).setLabel(heWeather.getDailyForecast().get(i).getDate().substring(5)));
        }

        Line maxLine = new Line(maxValues);
        maxLine.setColor(lecho.lib.hellocharts.util.ChartUtils.COLOR_RED).setCubic(true).setHasPoints(true);
        maxLine.setHasLabelsOnlyForSelected(true);

        Line minLine = new Line(minValues);
        minLine.setColor(lecho.lib.hellocharts.util.ChartUtils.COLOR_BLUE).setCubic(true).setHasPoints(true);
        minLine.setHasLabelsOnlyForSelected(true);


        List<Line> lines = new ArrayList<>();
        lines.add(maxLine);
        lines.add(minLine);

        LineChartData lineData;
        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisXValues));//设置x轴
        lineData.setAxisYLeft(new Axis());
//                .setFormatter(new SimpleAxisValueFormatter().setAppendedText(getString(R.string.tmp_formatter).toCharArray())));//设置y轴

        chartView.setLineChartData(lineData);
        chartView.setValueSelectionEnabled(true);//点击显示数值并且不消失
        chartView.setZoomEnabled(false);//双击放大

        if (refresh) {
            //防止线条溢出
            final Viewport v = new Viewport(chartView.getMaximumViewport());
            LogUtils.d(v);
            v.top = maxMax + 1;
            v.bottom = minMin - 1;
            chartView.setMaximumViewport(v);
            chartView.setCurrentViewport(v);
            chartView.setViewportCalculationEnabled(false);
        }

        for (Line line : lineData.getLines()) {
            for (PointValue value : line.getValues()) {
                value.setTarget(value.getX(), value.getY());
            }
        }
        chartView.startDataAnimation(300);
    }
}
