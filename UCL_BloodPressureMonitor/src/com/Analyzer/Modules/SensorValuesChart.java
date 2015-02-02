/**
  * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.Analyzer.Modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.MathHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * Temperature sensor demo chart.
 */
public class SensorValuesChart extends AbstractDemoChart {
  private static final long HOUR = 3600 * 1000;

  private static final long DAY = HOUR * 24;

  private static final int HOURS = 24;


  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public GraphicalView execute(Context context,List<Date[]> timestamp,
		                       List<double[]> BPvalues, String[] Linetitles, 
		                       String title, String xLabel,String yLabel, 
		                       double yMin, double yMax) {
	  
    int[] colors = new int[Linetitles.length];
    for(int i = 0; i < colors.length; i++){
    	colors[i] = Color.rgb(100+10*i, 100, 100);
    }
    
    
    PointStyle[] styles = new PointStyle[colors.length];
    for(int i = 0; i<styles.length; i++){
    	switch (i%4){
    	    case 0: styles[i] = PointStyle.POINT;
    	            break;
    	    case 1: styles[i] = PointStyle.DIAMOND;
    	    case 2: styles[i] = PointStyle.TRIANGLE;
    	    case 3: styles[i] = PointStyle.SQUARE;
    	}
    }
    
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    setChartSettings(renderer, title, xLabel, yLabel,
        timestamp.get(0)[0].getTime(), timestamp.get(0)[timestamp.get(0).length - 1].getTime(), 
        -yMin, yMax, Color.LTGRAY, Color.LTGRAY);
    renderer.setXLabels(24);
    renderer.setYLabels(10);
    renderer.setShowGrid(true);
    renderer.setXLabelsAlign(Align.CENTER);
    renderer.setYLabelsAlign(Align.RIGHT);
    GraphicalView graph = ChartFactory.getTimeChartView(context, buildDateDataset(Linetitles, timestamp, BPvalues),
        renderer, null);
    return graph;
  }

}
