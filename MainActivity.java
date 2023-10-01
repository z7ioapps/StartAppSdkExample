package com.studio.appjavaanuncios;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

public class MainActivity extends AppCompatActivity {

    Button interstitial_btn;
    StartAppAd startAppAd;

    Button rewarded_btn, btn;
    Button offerwall_btn;
    Button fullpage_btn;
    Button automatic_btn;
    StartAppAd startAppAd_rewarded;
    StartAppAd startAppAd_offerwall;
    StartAppAd startAppAd_fullpage;
    StartAppAd startAppAd_automatic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //disabilita o splash Sdk StartApp
        StartAppAd.disableSplash();
        //disabilita interticial Atomatico
        StartAppAd.disableAutoInterstitial();




        StartAppSDK.init(this,getString(R.string.startapp_id),false);
        StartAppSDK.setTestAdsEnabled(true); //remova esta linha ao publicar na Play Store.
        Banner banner = findViewById(R.id.startAppBanner);
        banner.loadAd();


        //Interticial StartApp

        interstitial_btn = findViewById(R.id.interstitial_btn);
        startAppAd = new StartAppAd(this);
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(@NonNull Ad ad) {
                Log.e("TAG", "onReceiveAd: " );
                // startAppAd.showAd();
            }
            @Override
            public void onFailedToReceiveAd(@Nullable Ad ad) {
                Log.e("TAG", "onFailedToReceiveAd: "+ad.errorMessage );
            }
        });
        interstitial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startAppAd.isReady())
                {
                    //você também precisa informar ao usuário que o anúncio será exibido aos usuários
                    ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("Anuncio");
                    dialog.setMessage("Carregando anúncio...");
                    dialog.setCancelable(false);
                    dialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            startAppAd.showAd();
                            //ir para a próxima atividade
                        }
                    },1500);
                }
                else
                {
                    //goto next activity
                }
            }
        });




        //Anuncios de Saida de Actibity
      /*  StartAppAd.enableAutoInterstitial();
        // seconds of time when new autoad will be shown
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );
        // activities of which ads are shown
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setActivitiesBetweenAds(3)
        );
    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this); // whenever this backpress calls
        // it shows an ad and then perform action
        super.onBackPressed(); */



        //Rewards StartApp
        rewarded_btn = findViewById(R.id.start_app_rewarded);
        offerwall_btn = findViewById(R.id.start_app_offerwall);
        fullpage_btn = findViewById(R.id.start_app_fullpage);
        automatic_btn = findViewById(R.id.start_app_Automatic);
        StartAppSDK.init(this,"Your App Id",false);
        StartAppSDK.setTestAdsEnabled(true);
        StartAppAd.showSplash(this, savedInstanceState,
                new SplashConfig()
                        .setTheme(SplashConfig.Theme.OCEAN)
                        .setAppName("Your Application Name")
                        .setLogo(R.drawable.ic_splash)  // resource ID
                        .setOrientation(SplashConfig.Orientation.LANDSCAPE)
        );
        startAppAd_rewarded = new StartAppAd(this);
        startAppAd_offerwall = new StartAppAd(this);
        startAppAd_fullpage = new StartAppAd(this);
        startAppAd_automatic = new StartAppAd(this);
        startAppAd_rewarded.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);
        startAppAd_offerwall.loadAd(StartAppAd.AdMode.OFFERWALL);
        startAppAd_fullpage.loadAd(StartAppAd.AdMode.FULLPAGE);
        startAppAd_automatic.loadAd(StartAppAd.AdMode.AUTOMATIC); // automatically loads the best ad for you.
        startAppAd_rewarded.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                Toast.makeText(MainActivity.this, "10 moedas concedidas para visualização de anúncios", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onVideoCompleted: " );
            }
        });
        rewarded_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startAppAd_rewarded.isReady())
                {
                    startAppAd_rewarded.showAd();
                }
            }
        });
        offerwall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startAppAd_offerwall.isReady())
                {
                    startAppAd_offerwall.showAd();
                }
            }
        });
        fullpage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startAppAd_fullpage.isReady())
                {
                    startAppAd_fullpage.showAd();
                }
            }
        });
        automatic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startAppAd_automatic.isReady())
                {
                    startAppAd_automatic.showAd();
                }
            }
        });


    


    }
}