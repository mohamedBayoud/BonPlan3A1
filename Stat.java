/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author Omar
 */
import com.mycompany.Entite.Promotion;
import com.mycompagny.Service.ServiceStat;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;
import java.util.Random;

/**
 *
 * @author
 */
public class Stat extends SideMenuBaseForm {

	Form f;
	SpanLabel lb;
	Resources theme;

	public Stat(Resources res) {
		f = new Form(BoxLayout.y());
		lb = new SpanLabel("");
		f.add(lb);

		theme = UIManager.initFirstTheme("/theme_1");
		getToolbar().setTitleComponent(
				FlowLayout.encloseCenterMiddle(
						new Label("Statistique", "Title")
				)
		);
		add(createPieChartForm());
		 f.getToolbar().addCommandToRightBar("back", null, (ev)->{PromotionAcceuil h=new PromotionAcceuil();
          h.getF().show();
          });
	}
	public int stock;
	public int nb = 20;
	ServiceStat ser = new ServiceStat();
	List<Promotion> list = ser.getList2();
	public int j = list.size();

	public int[] tab = new int[j];

	private DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[]{20, 30, 15, 0});
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}

		return renderer;
	}

	/**
	 * Builds a category series using the provided values.
	 *
	 * @param titles the series titles
	 * @param values the values
	 * @return the category series
	 */
	protected CategorySeries buildCategoryDataset(String title, int[] values) {
		CategorySeries series = new CategorySeries(title);
		int k = 0;
		for (int value : values) {
			series.add(" " + ++k, value);
		}

		return series;
	}

	public Form createPieChartForm() {
		// Generate the values

		int[] values = new int[]{12, 14, 11, 10, 19};

		for (Promotion p : list) {
			// System.out.println(p.getId());
			tab[stock] = p.getId();
			stock++;

		}

		for (int t : tab) {
			//System.out.println(p.getNbr());
			System.err.println(t);

		}

		// Set up the renderer
		int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.BLUE};
		DefaultRenderer renderer = buildCategoryRenderer(colors);
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		renderer.setChartTitleTextSize(20);
		renderer.setDisplayValues(true);
		renderer.setShowLabels(true);
		SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
		r.setGradientEnabled(true);
		r.setGradientStart(0, ColorUtil.BLUE);
		r.setGradientStop(0, ColorUtil.YELLOW);
		r.setHighlighted(true);

		// Create the chart ... pass the values and renderer to the chart object.
		PieChart chart = new PieChart(buildCategoryDataset("yosra", tab), renderer);

		// Wrap the chart in a Component so we can add it to a form
		ChartComponent c = new ChartComponent(chart);

		// Create a form and show it.
		f = new Form("N°:Promotions", new BorderLayout());

		f.add(BorderLayout.CENTER, c);
		f.show();

		return f;
	}

	protected void showOtherForm(Resources res) {
	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}

}
