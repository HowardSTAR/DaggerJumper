package com.littlebiglion;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdView;
import com.littlebiglion.DaggerJumper;

public class AndroidLauncher extends AndroidApplication {

	private RelativeLayout mainView; // Разметка на экране
	private AdView bannerView; // Праметр для отображения рекламы
	private ViewGroup bannerContainer;
	private RelativeLayout.LayoutParams bannerParams; // Параметры разметки

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Для отобьражения на весь экран
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN); // Для того, чтобы не было проблем развертки игры на весь экран на некоторых устройствах

		mainView = new RelativeLayout(this);
		setContentView(mainView);

		View gameView = initializeForView(new DaggerJumper());
		mainView.addView(gameView);

		bannerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bannerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		bannerContainer = new LinearLayout(this);
		mainView.addView(bannerContainer, bannerParams);
		bannerContainer.setVisibility(View.GONE);
	}
}
