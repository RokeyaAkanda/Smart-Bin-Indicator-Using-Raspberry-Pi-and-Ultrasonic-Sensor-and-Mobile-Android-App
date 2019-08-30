package edu.northsouth.smartbindhaka.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import edu.northsouth.smartbindhaka.R;
import edu.northsouth.smartbindhaka.busmodels.DistanceModel;


public class HomeFragment extends Fragment {

    LineChart chart;
    YAxis yAxisRight;
    YAxis yAxisRight2;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<Entry> entries2 = new ArrayList<>();
    XAxis xAxis;
    XAxis xAxis2;
    YAxis yAxisLeft;
    YAxis yAxisLeft2;
    LineData data;
    LineData data2;
    private TextView tfLdr;
    private TextView tfGas;
    private LineChart chart2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chart = view.findViewById(R.id.chart);
    }


    @Subscribe
    public void updateUI(final DistanceModel distanceModel) {
        Log.d("DistanceModel", distanceModel.toString());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {


                entries.add(new Entry(entries.size(), (distanceModel.getDistance()))); //LDR

                //For LDR CHART
                LineDataSet dataSet;
                dataSet = new LineDataSet(entries, "Bin Status");
                dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                dataSet.setLineWidth(3f);


                xAxis = chart.getXAxis();

                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                final String[] months = new String[]{"Time"};

                IAxisValueFormatter formatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return months[0];
                    }
                };
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1

                xAxis.setValueFormatter(formatter);

                yAxisRight = chart.getAxisRight();

                yAxisRight.setEnabled(false);


                yAxisLeft = chart.getAxisLeft();
                yAxisLeft.setGranularity(1f);

                data = new LineData(dataSet);
                dataSet.setDrawFilled(true);

                chart.setData(data);
                chart.animateX(0);
                chart.invalidate();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
