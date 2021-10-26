package com.example.creme;
// 산 tab 1 과 관련
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity2 extends AppCompatActivity
        implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    LinearLayout container;

    Fragment1 fragment1;

    public static final int REQUEST_CODE_MENU1 = 101;
    TextView textView;
    private GoogleMap mMap;
    private Marker currentMarker = null;
    private static final String TAG = "googlemap example";
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초 간격으로 위치 업데이트 수신
    private static final int FASTEST_UPDATE_INTERVAL_MS = 9000000; // 30초(30000) 간격으로 화면 갱신

    private static final int GPS_PROVIDER = 1;
    private static final int GPS_REQUEST_CODE = 1000; // 숫자에 의미는 없음
    private static final int PERMISSIONS_REQUEST_CODE = 2000; //100
    boolean needRequest = false;

    private static final int IMAGEBTN1_CODE = 2400;
    private static final int IMAGEBTN2_CODE = 2401;
    private static final int IMAGEBTN3_CODE = 2402;

    public static final int REQUEST_CODE_MENU2 = 102;
    public static final int REQUEST_CODE_MENU3 = 103;

    // 앱을 실행하기 위해 필요한 퍼미션을 정의.
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};  // 외부 저장소

    Location mCurrentLocation2;
    LatLng currentPoint;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    private View mLayout;

//latlng

    private final LatLng SEOULTECH = new LatLng(37.63224, 127.07769);
    private Marker markerSEOULTECH;
    private final LatLng ATOM = new LatLng(37.62877, 127.08268);
    private Marker markerATOM;


    private final LatLng A1 = new LatLng(37.650402, 126.966599);
    private final LatLng A2 = new LatLng(37.655057, 127.010382);
    private final LatLng A3 = new LatLng(37.641684, 127.001698);
    private final LatLng A4 = new LatLng(37.665589, 126.998533);
    private final LatLng A5 = new LatLng(37.677539, 127.032314);
    private final LatLng A6 = new LatLng(37.665589, 126.998533);
    private final LatLng A7 = new LatLng(37.687965, 127.030262);
    private final LatLng A8 = new LatLng(37.640704, 127.000794);
    private final LatLng A9 = new LatLng(37.637031, 126.997239);
    private final LatLng A10 = new LatLng(37.646852, 126.962603);
    private final LatLng A11 = new LatLng(37.671377, 126.959393);
    private final LatLng A12 = new LatLng(37.689313, 126.963054);
    private final LatLng A13 = new LatLng(37.687965, 127.030262);
    private final LatLng A14 = new LatLng(37.640877, 126.954233);
    private final LatLng A15 = new LatLng(37.704919, 127.02106);
    private final LatLng A16 = new LatLng(37.684764, 127.033184);
    private final LatLng A17 = new LatLng(37.644727, 126.940423);
    private final LatLng A18 = new LatLng(37.712386, 126.984577);
    private final LatLng A19 = new LatLng(37.711278, 126.987757);
    private final LatLng A20 = new LatLng(37.678085, 127.002628);
    private final LatLng A21 = new LatLng(37.655675, 126.948563);
    private final LatLng A22 = new LatLng(37.710812, 126.99543);
    private final LatLng A23 = new LatLng(37.707924, 126.980192);
    private final LatLng A24 = new LatLng(37.658134, 127.002419);
    private final LatLng A25 = new LatLng(37.61783, 126.943296);
    private final LatLng A26 = new LatLng(37.658134, 127.002419);
    private final LatLng A27 = new LatLng(37.706051, 127.00671);
    private final LatLng A28 = new LatLng(37.693758, 127.022465);
    private final LatLng A29 = new LatLng(37.721565, 127.003259);
    private final LatLng A30 = new LatLng(37.68739, 127.016222);
    private final LatLng A31 = new LatLng(37.720567, 127.003522);
    private final LatLng A32 = new LatLng(37.720567, 127.003522);
    private final LatLng A33 = new LatLng(37.676457, 127.02785);
    private final LatLng A34 = new LatLng(37.68739, 127.016222);
    private final LatLng A35 = new LatLng(37.636264, 126.984378);
    private final LatLng A36 = new LatLng(37.635827, 126.976363);
    private final LatLng A37 = new LatLng(37.671936, 126.957753);
    private final LatLng A38 = new LatLng(37.623912, 126.990596);
    private final LatLng A39 = new LatLng(37.65587, 126.948355);
    private final LatLng A40 = new LatLng(37.65587, 126.948355);
    private final LatLng A41 = new LatLng(37.675413, 126.959326);
    private final LatLng A42 = new LatLng(37.707924, 126.980192);
    private final LatLng A43 = new LatLng(37.675882, 127.026849);
    private final LatLng A44 = new LatLng(37.720891, 127.030525);
    private final LatLng A45 = new LatLng(37.674718, 127.019042);
    private final LatLng A46 = new LatLng(37.720891, 127.030525);
    private final LatLng A47 = new LatLng(37.720891, 127.030525);
    private final LatLng A48 = new LatLng(37.720891, 127.030525);
    private final LatLng A49 = new LatLng(37.720891, 127.030525);
    private final LatLng A50 = new LatLng(37.720891, 127.030525);
    private final LatLng A51 = new LatLng(37.720891, 127.030525);
    private final LatLng A52 = new LatLng(37.720891, 127.030525);
    private final LatLng A53 = new LatLng(37.685217, 127.030287);
    private final LatLng A54 = new LatLng(37.62678, 126.960842);
    private final LatLng A55 = new LatLng(37.698219, 126.979021);
    private final LatLng A56 = new LatLng(37.698219, 126.979021);
    private final LatLng A57 = new LatLng(37.637735, 126.943445);
    private final LatLng A58 = new LatLng(37.676457, 127.02785);
    private final LatLng A59 = new LatLng(37.675474, 127.02334);
    private final LatLng A60 = new LatLng(37.64997, 126.966729);
    private final LatLng A61 = new LatLng(37.649553, 126.972639);
    private final LatLng A62 = new LatLng(37.635494, 126.97647);
    private final LatLng A63 = new LatLng(37.645166, 126.974807);
    private final LatLng A64 = new LatLng(37.645676, 126.970211);
    private final LatLng A65 = new LatLng(37.645676, 126.970211);
    private final LatLng A66 = new LatLng(37.650402, 126.966599);
    private final LatLng A67 = new LatLng(37.64553, 126.978304);
    private final LatLng A68 = new LatLng(37.665484, 126.971683);
    private final LatLng A69 = new LatLng(37.645676, 126.970211);
    private final LatLng A70 = new LatLng(37.645374, 127.00004);
    private final LatLng A71 = new LatLng(37.636245, 127.001986);
    private final LatLng A72 = new LatLng(37.670344, 127.003593);
    private final LatLng A73 = new LatLng(37.676348, 126.970309);
    private final LatLng A74 = new LatLng(37.67556, 126.977133);
    private final LatLng A75 = new LatLng(37.645103, 126.940145);
    private final LatLng A76 = new LatLng(37.662458, 126.992761);
    private final LatLng A77 = new LatLng(37.662458, 126.992761);
    private final LatLng A78 = new LatLng(37.667922, 127.006935);
    private final LatLng A79 = new LatLng(37.662458, 126.992761);
    private final LatLng A80 = new LatLng(37.694209, 127.019341);
    private final LatLng A81 = new LatLng(37.720891, 127.030525);
    private final LatLng A82 = new LatLng(37.656514, 126.969064);
    private final LatLng A83 = new LatLng(37.623912, 126.990596);
    private final LatLng A84 = new LatLng(37.677539, 127.032314);
    private final LatLng A85 = new LatLng(37.688619, 127.027589);
    private final LatLng A86 = new LatLng(37.694264, 127.020452);
    private final LatLng A87 = new LatLng(37.674718, 127.019042);
    private final LatLng A88 = new LatLng(37.674718, 127.019042);
    private final LatLng A89 = new LatLng(37.67964, 127.012901);
    private final LatLng A90 = new LatLng(37.682359, 127.016912);
    private final LatLng A91 = new LatLng(37.682359, 127.016912);
    private final LatLng A92 = new LatLng(37.682359, 127.016912);
    private final LatLng A93 = new LatLng(37.682578, 127.010844);
    private final LatLng A94 = new LatLng(37.682973, 127.010669);
    private final LatLng A95 = new LatLng(37.694091, 127.019252);
    private final LatLng A96 = new LatLng(37.647352, 126.941802);
    private final LatLng A97 = new LatLng(37.62678, 126.960842);
    private final LatLng A98 = new LatLng(37.629065, 127.001085);
    private final LatLng A99 = new LatLng(37.64074, 127.000793);
    private final LatLng A100 = new LatLng(37.664987, 127.02885);
    private final LatLng A101 = new LatLng(37.623032, 126.974217);
    private final LatLng A102 = new LatLng(37.635946, 127.002852);
    private final LatLng A103 = new LatLng(37.656514, 126.969064);
    private final LatLng A104 = new LatLng(37.677009, 127.003254);
    private final LatLng A105 = new LatLng(37.662458, 126.992761);
    private final LatLng A106 = new LatLng(37.624608, 127.010027);
    private final LatLng A107 = new LatLng(37.629065, 127.001085);
    private final LatLng A108 = new LatLng(37.656629, 127.000263);
    private final LatLng A109 = new LatLng(37.625773, 126.962125);
    private final LatLng A110 = new LatLng(37.675413, 126.959326);
    private final LatLng A111 = new LatLng(37.676348, 126.970309);
    private final LatLng A112 = new LatLng(37.704601, 127.024069);
    private final LatLng A113 = new LatLng(37.726864, 127.030436);
    private final LatLng A114 = new LatLng(37.720891, 127.030525);
    private final LatLng A115 = new LatLng(37.720891, 127.030525);
    private final LatLng A116 = new LatLng(37.659018, 126.96079);
    private final LatLng A117 = new LatLng(37.645154, 126.975533);
    private final LatLng A118 = new LatLng(37.635745, 126.976319);
    private final LatLng A119 = new LatLng(37.711278, 126.987757);
    private final LatLng A120 = new LatLng(37.710812, 126.99543);
    private final LatLng A121 = new LatLng(37.61783, 126.943296);
    private final LatLng A122 = new LatLng(37.638142, 126.946578);
    private final LatLng A123 = new LatLng(37.645175, 126.940099);
    private final LatLng A124 = new LatLng(37.651327, 127.006114);
    private final LatLng A125 = new LatLng(37.643569, 126.981172);
    private final LatLng A126 = new LatLng(37.637961, 126.974328);
    private final LatLng A127 = new LatLng(37.640704, 127.000794);
    private final LatLng A128 = new LatLng(37.653131, 126.960386);
    private final LatLng A129 = new LatLng(37.654787, 127.008266);
    private final LatLng A130 = new LatLng(37.677539, 127.032314);
    private final LatLng A131 = new LatLng(37.684764, 127.033184);
    private final LatLng A132 = new LatLng(37.687965, 127.030262);
    private final LatLng A133 = new LatLng(37.677072, 127.028058);
    private final LatLng A134 = new LatLng(37.675346, 127.022027);
    private final LatLng A135 = new LatLng(37.655675, 126.948563);
    private final LatLng A136 = new LatLng(37.698219, 126.979021);
    private final LatLng A137 = new LatLng(37.698219, 126.979021);
    private final LatLng A138 = new LatLng(37.691211, 126.98973);
    private final LatLng A139 = new LatLng(37.691211, 126.98973);
    private final LatLng A140 = new LatLng(37.691211, 126.98973);
    private final LatLng A141 = new LatLng(37.691211, 126.98973);
    private final LatLng A142 = new LatLng(37.691211, 126.98973);
    private final LatLng A143 = new LatLng(37.662458, 126.992761);
    private final LatLng A144 = new LatLng(37.662458, 126.992761);
    private final LatLng A145 = new LatLng(37.612872, 126.991606);
    private final LatLng A146 = new LatLng(37.626522, 126.960234);
    private final LatLng A147 = new LatLng(37.675811, 127.024144);
    private final LatLng A148 = new LatLng(37.687965, 127.030262);
    private final LatLng A149 = new LatLng(37.614655, 126.960139);
    private final LatLng A150 = new LatLng(37.675882, 127.026849);
    private final LatLng A151 = new LatLng(37.636245, 127.001986);
    private final LatLng A152 = new LatLng(37.640704, 127.000794);
    private final LatLng A153 = new LatLng(37.62678, 126.960842);
    private final LatLng A154 = new LatLng(37.704714, 127.021403);
    private final LatLng A155 = new LatLng(37.6375, 126.976678);
    private final LatLng A156 = new LatLng(37.633903, 126.975569);
    private final LatLng A157 = new LatLng(37.68739, 127.016222);
    private final LatLng A158 = new LatLng(37.637117, 126.997533);
    private final LatLng A159 = new LatLng(37.704928, 127.021071);
    private final LatLng A160 = new LatLng(37.622514, 126.989739);
    private final LatLng A161 = new LatLng(37.639178, 126.979733);
    private final LatLng A162 = new LatLng(37.625773, 126.962125);
    private final LatLng A163 = new LatLng(37.676189, 127.042856);
    private final LatLng A164 = new LatLng(37.639378, 126.969625);
    private final LatLng A165 = new LatLng(37.645374, 127.00004);
    private final LatLng A166 = new LatLng(37.609325, 126.950692);
    private final LatLng A167 = new LatLng(37.609325, 126.950692);
    private final LatLng A168 = new LatLng(37.649175, 126.981964);
    private final LatLng A169 = new LatLng(37.662458, 126.992761);
    private final LatLng A170 = new LatLng(37.634104, 126.950532);
    private final LatLng A171 = new LatLng(37.64149, 126.942202);
    private final LatLng A172 = new LatLng(37.64832, 126.972758);
    private final LatLng A173 = new LatLng(37.650402, 126.966599);
    private final LatLng A174 = new LatLng(37.645676, 126.970211);
    private final LatLng A175 = new LatLng(37.653326, 126.960019);
    private final LatLng A176 = new LatLng(37.654171, 126.957786);
    private final LatLng A177 = new LatLng(37.618438, 126.953902);
    private final LatLng A178 = new LatLng(37.678085, 127.002628);
    private final LatLng A179 = new LatLng(37.623912, 126.990596);
    private final LatLng A180 = new LatLng(37.655863, 126.948334);
    private final LatLng A181 = new LatLng(37.662458, 126.992761);
    private final LatLng A182 = new LatLng(37.658134, 127.002419);
    private final LatLng A183 = new LatLng(37.678085, 127.002628);
    private final LatLng A184 = new LatLng(37.655675, 126.948563);
    private final LatLng A185 = new LatLng(37.710812, 126.99543);
    private final LatLng A186 = new LatLng(37.712386, 126.984577);
    private final LatLng A187 = new LatLng(37.711482, 126.986382);
    private final LatLng A188 = new LatLng(37.638142, 126.946578);
    private final LatLng A189 = new LatLng(37.636245, 127.001986);
    private final LatLng A190 = new LatLng(37.623912, 126.990596);
    private final LatLng A191 = new LatLng(37.645175, 126.940099);
    private final LatLng A192 = new LatLng(37.642724, 126.946128);
    private final LatLng A193 = new LatLng(37.642491, 126.951366);
    private final LatLng A194 = new LatLng(37.64149, 126.942202);
    private final LatLng A195 = new LatLng(37.640704, 127.000794);
    private final LatLng A196 = new LatLng(37.687965, 127.030262);
    private final LatLng A197 = new LatLng(37.684764, 127.033184);
    private final LatLng A198 = new LatLng(37.689313, 126.963054);
    private final LatLng A199 = new LatLng(37.635494, 126.97647);
    private final LatLng A200 = new LatLng(37.676348, 126.970309);
    private final LatLng A201 = new LatLng(37.637075, 126.969258);
    private final LatLng A202 = new LatLng(37.623912, 126.990596);
    private final LatLng A203 = new LatLng(37.677539, 127.032314);
    private final LatLng A204 = new LatLng(37.626931, 126.960534);
    private final LatLng A205 = new LatLng(37.637117, 126.997533);
    private final LatLng A206 = new LatLng(37.662458, 126.992761);
    private final LatLng A207 = new LatLng(37.662458, 126.992761);
    private final LatLng A208 = new LatLng(37.675882, 127.026849);
    private final LatLng A209 = new LatLng(37.720891, 127.030525);
    private final LatLng A210 = new LatLng(37.61783, 126.943296);
    private final LatLng A211 = new LatLng(37.634104, 126.950532);
    private final LatLng A212 = new LatLng(37.650402, 126.966599);
    private final LatLng A213 = new LatLng(37.653326, 126.960019);
    private final LatLng A214 = new LatLng(37.64553, 126.978304);
    private final LatLng A215 = new LatLng(37.649553, 126.972639);
    private final LatLng A216 = new LatLng(37.645676, 126.970211);
    private final LatLng A217 = new LatLng(37.646852, 126.962603);
    private final LatLng A218 = new LatLng(37.623912, 126.990596);
    private final LatLng A219 = new LatLng(37.710812, 126.99543);
    private final LatLng A220 = new LatLng(37.704601, 127.024069);
    private final LatLng A221 = new LatLng(37.671966, 126.957753);
    private final LatLng A222 = new LatLng(37.623912, 126.990596);
    private final LatLng A223 = new LatLng(37.694264, 127.020452);
    private final LatLng A224 = new LatLng(37.625773, 126.962125);
    private final LatLng A225 = new LatLng(37.68739, 127.016222);
    private final LatLng A226 = new LatLng(37.625773, 126.962125);
    private final LatLng A227 = new LatLng(37.625773, 126.962125);
    private final LatLng A228 = new LatLng(37.712386, 126.984577);
    private final LatLng A229 = new LatLng(37.720567, 127.003522);
    private final LatLng A230 = new LatLng(37.699781, 126.983639);
    private final LatLng A231 = new LatLng(37.623912, 126.990596);
    private final LatLng A232 = new LatLng(37.691749, 126.976922);
    private final LatLng A233 = new LatLng(37.691169, 126.995298);
    private final LatLng A234 = new LatLng(37.689535, 127.025038);
    private final LatLng A235 = new LatLng(37.691258, 127.023054);
    private final LatLng A236 = new LatLng(37.691258, 127.023054);
    private final LatLng A237 = new LatLng(37.694264, 127.020452);
    private final LatLng A238 = new LatLng(37.694264, 127.020452);
    private final LatLng A239 = new LatLng(37.691169, 126.995298);
    private final LatLng A240 = new LatLng(37.689535, 127.025038);
    private final LatLng A241 = new LatLng(37.691258, 127.023054);
    private final LatLng A242 = new LatLng(37.691258, 127.023054);
    private final LatLng A243 = new LatLng(37.694264, 127.020452);
    private final LatLng A244 = new LatLng(37.694264, 127.020452);
    private final LatLng A245 = new LatLng(37.691169, 126.995298);
    private final LatLng A246 = new LatLng(37.691169, 126.995298);
    private final LatLng A247 = new LatLng(37.625773, 126.962125);
    private final LatLng A248 = new LatLng(37.642509, 127.002254);
    private final LatLng A249 = new LatLng(37.721565, 127.003259);
    private final LatLng A250 = new LatLng(37.707993, 126.994561);
    private final LatLng A251 = new LatLng(37.678931, 127.002328);
    private final LatLng A252 = new LatLng(37.721565, 127.003259);
    private final LatLng A253 = new LatLng(37.707993, 126.994561);
    private final LatLng A254 = new LatLng(37.678085, 127.002628);
    private final LatLng A255 = new LatLng(37.6565, 126.969552);
    private final LatLng A256 = new LatLng(37.685074, 127.030368);
    private final LatLng A257 = new LatLng(37.652181, 127.008051);
    private final LatLng A258 = new LatLng(37.651327, 127.006114);
    private final LatLng A259 = new LatLng(37.654787, 127.008266);
    private final LatLng A260 = new LatLng(37.623912, 126.990596);
    private final LatLng A261 = new LatLng(37.69013, 127.017513);
    private final LatLng A262 = new LatLng(37.728369, 127.020694);
    private final LatLng A263 = new LatLng(37.64252, 127.001359);
    private final LatLng A264 = new LatLng(37.642247, 127.002155);
    private final LatLng A265 = new LatLng(37.675274, 127.022028);
    private final LatLng A266 = new LatLng(37.696987, 127.018533);
    private final LatLng A267 = new LatLng(37.674718, 127.019042);
    private final LatLng A268 = new LatLng(37.623912, 126.990596);
    private final LatLng A269 = new LatLng(37.685656, 127.0204);
    private final LatLng A270 = new LatLng(37.685378, 127.020122);
    private final LatLng A271 = new LatLng(37.682578, 127.010844);
    private final LatLng A272 = new LatLng(37.682946, 127.010646);
    private final LatLng A273 = new LatLng(37.720891, 127.030525);
    private final LatLng A274 = new LatLng(37.728396, 127.020683);
    private final LatLng A275 = new LatLng(37.696354, 126.97594);
    private final LatLng A276 = new LatLng(37.720891, 127.030525);
    private final LatLng A277 = new LatLng(37.720891, 127.030525);
    private final LatLng A278 = new LatLng(37.720891, 127.030525);
    private final LatLng A279 = new LatLng(37.691749, 126.976922);
    private final LatLng A280 = new LatLng(37.694244, 126.986106);
    private final LatLng A281 = new LatLng(37.694244, 126.986106);
    private final LatLng A282 = new LatLng(37.694244, 126.986106);
    private final LatLng A283 = new LatLng(37.693432, 126.995483);
    private final LatLng A284 = new LatLng(37.690624, 126.99382);
    private final LatLng A285 = new LatLng(37.682359, 127.016912);
    private final LatLng A286 = new LatLng(37.67964, 127.012901);
    private final LatLng A287 = new LatLng(37.640278, 126.943333);
    private final LatLng A288 = new LatLng(37.676697, 127.021525);
    private final LatLng A289 = new LatLng(37.67674, 127.021725);
    private final LatLng A290 = new LatLng(37.707924, 126.980192);
    private final LatLng A291 = new LatLng(37.728369, 127.020694);
    private final LatLng A292 = new LatLng(37.696356, 127.018728);
    private final LatLng A293 = new LatLng(37.696353, 127.019222);
    private final LatLng A294 = new LatLng(37.645676, 126.970211);
    private final LatLng A295 = new LatLng(37.665589, 126.998533);
    private final LatLng A296 = new LatLng(37.624608, 127.010027);
    private final LatLng A297 = new LatLng(37.658134, 127.002419);
    private final LatLng A298 = new LatLng(37.618438, 126.953902);
    private final LatLng A299 = new LatLng(37.630879, 127.003531);
    private final LatLng A300 = new LatLng(37.662458, 126.992761);
    private final LatLng A301 = new LatLng(37.677072, 127.028058);
    private final LatLng A302 = new LatLng(37.623912, 126.990596);
    private final LatLng A303 = new LatLng(37.639133, 126.978906);
    private final LatLng A304 = new LatLng(37.687965, 127.030262);
    private final LatLng A305 = new LatLng(37.684764, 127.033184);
    private final LatLng A306 = new LatLng(37.678085, 127.002628);
    private final LatLng A307 = new LatLng(37.618308, 126.939044);
    private final LatLng A308 = new LatLng(37.618308, 126.939044);
    private final LatLng A309 = new LatLng(37.618308, 126.939044);
    private final LatLng A310 = new LatLng(37.618308, 126.939044);
    private final LatLng A311 = new LatLng(37.618308, 126.939044);
    private final LatLng A312 = new LatLng(37.618308, 126.939044);
    private final LatLng A313 = new LatLng(37.618308, 126.939044);
    private final LatLng A314 = new LatLng(37.665589, 126.998533);
    private final LatLng A315 = new LatLng(37.682973, 127.010669);
    private final LatLng A316 = new LatLng(37.665484, 126.971683);
    private final LatLng A317 = new LatLng(37.665484, 126.971683);
    private final LatLng A318 = new LatLng(37.662458, 126.992761);
    private final LatLng A319 = new LatLng(37.665589, 126.998533);
    private final LatLng A320 = new LatLng(37.618438, 126.953902);
    private final LatLng A321 = new LatLng(37.620381, 126.953583);
    private final LatLng A322 = new LatLng(37.649716, 127.00533);
    private final LatLng A323 = new LatLng(37.659018, 126.96079);
    private final LatLng A324 = new LatLng(37.645676, 126.970211);
    private final LatLng A325 = new LatLng(37.649716, 127.00533);
    private final LatLng A326 = new LatLng(37.695129, 127.020474);
    private final LatLng A327 = new LatLng(37.682359, 127.016912);
    private final LatLng A328 = new LatLng(37.618438, 126.953902);
    private final LatLng A329 = new LatLng(37.668503, 127.00685);
    private final LatLng A330 = new LatLng(37.662458, 126.992761);
    private final LatLng A331 = new LatLng(37.720567, 127.003522);
    private final LatLng A332 = new LatLng(37.630879, 127.003531);
    private final LatLng A333 = new LatLng(37.675639, 126.959345);
    private final LatLng A334 = new LatLng(37.617194, 126.947333);
    private final LatLng A335 = new LatLng(37.637844, 126.946491);
    private final LatLng A336 = new LatLng(37.642285, 126.952468);
    private final LatLng A337 = new LatLng(37.645374, 127.00004);
    private final LatLng A338 = new LatLng(37.636245, 127.001986);
    private final LatLng A339 = new LatLng(37.638158, 126.993892);
    private final LatLng A340 = new LatLng(37.6721, 126.957524);
    private final LatLng A341 = new LatLng(37.638158, 126.993892);
    private final LatLng A342 = new LatLng(37.653326, 126.960019);
    private final LatLng A343 = new LatLng(37.656472, 126.969056);
    private final LatLng A344 = new LatLng(37.655675, 126.948563);
    private final LatLng A345 = new LatLng(37.647028, 126.962611);
    private final LatLng A346 = new LatLng(37.648361, 126.97275);
    private final LatLng A347 = new LatLng(37.638142, 126.946578);
    private final LatLng A348 = new LatLng(37.710812, 126.99543);
    private final LatLng A349 = new LatLng(37.712186, 126.984597);
    private final LatLng A350 = new LatLng(37.711322, 126.987789);
    private final LatLng A351 = new LatLng(37.635827, 126.976363);
    private final LatLng A352 = new LatLng(37.645154, 126.975533);
    private final LatLng A353 = new LatLng(37.645175, 126.940099);
    private final LatLng A354 = new LatLng(37.629176, 126.958747);
    private final LatLng A355 = new LatLng(37.650417, 126.966611);
    private final LatLng A356 = new LatLng(37.643861, 126.961806);
    private final LatLng A357 = new LatLng(37.709897, 127.039152);
    private final LatLng A358 = new LatLng(37.649716, 127.00533);
    private final LatLng A359 = new LatLng(37.640478, 127.001019);
    private final LatLng A360 = new LatLng(37.676157, 126.970119);
    private final LatLng A361 = new LatLng(37.707047, 126.978844);
    private final LatLng A362 = new LatLng(37.660642, 126.993697);
    private final LatLng A363 = new LatLng(37.660642, 126.993697);
    private final LatLng A364 = new LatLng(37.707047, 126.978844);
    private final LatLng A365 = new LatLng(37.639163, 126.945918);
    private final LatLng A366 = new LatLng(37.623032, 126.974217);
    private final LatLng A367 = new LatLng(37.620378, 126.953583);
    private final LatLng A368 = new LatLng(37.628389, 126.974214);
    private final LatLng A369 = new LatLng(37.629176, 126.958747);
    private final LatLng A370 = new LatLng(37.626931, 126.960534);
    private final LatLng A371 = new LatLng(37.631767, 126.973417);
    private final LatLng A372 = new LatLng(37.637117, 126.997533);
    private final LatLng A373 = new LatLng(37.640704, 127.000794);
    private final LatLng A374 = new LatLng(37.629065, 127.001085);
    private final LatLng A375 = new LatLng(37.638142, 126.946578);
    private final LatLng A376 = new LatLng(37.645175, 126.940099);
    private final LatLng A377 = new LatLng(37.642481, 126.950892);
    private final LatLng A378 = new LatLng(37.655981, 126.948203);
    private final LatLng A379 = new LatLng(37.637117, 126.997533);
    private final LatLng A380 = new LatLng(37.721461, 127.030547);
    private final LatLng A381 = new LatLng(37.721461, 127.030547);
    private final LatLng A382 = new LatLng(37.658044, 127.0023);
    private final LatLng A383 = new LatLng(37.730541, 127.020779);
    private final LatLng A384 = new LatLng(37.728396, 127.020683);
    private final LatLng A385 = new LatLng(37.645676, 126.970211);
    private final LatLng A386 = new LatLng(37.659018, 126.96079);
    private final LatLng A387 = new LatLng(37.641139, 126.978035);
    private final LatLng A388 = new LatLng(37.659018, 126.96079);
    private final LatLng A389 = new LatLng(37.659018, 126.96079);
    private final LatLng A390 = new LatLng(37.659018, 126.96079);
    private final LatLng A391 = new LatLng(37.665484, 126.971683);
    private final LatLng A392 = new LatLng(37.659018, 126.96079);
    private final LatLng A393 = new LatLng(37.641139, 126.978035);
    private final LatLng A394 = new LatLng(37.659108, 126.960687);
    private final LatLng A395 = new LatLng(37.659108, 126.960687);
    private final LatLng A396 = new LatLng(37.659018, 126.96079);
    private final LatLng A397 = new LatLng(37.665484, 126.971683);
    private final LatLng A398 = new LatLng(37.659018, 126.96079);
    private final LatLng A399 = new LatLng(37.641139, 126.978035);
    private final LatLng A400 = new LatLng(37.659018, 126.96079);
    private final LatLng A401 = new LatLng(37.659018, 126.96079);
    private final LatLng A402 = new LatLng(37.659018, 126.96079);
    private final LatLng A403 = new LatLng(37.665484, 126.971683);
    private final LatLng A404 = new LatLng(37.730541, 127.020779);
    private final LatLng A405 = new LatLng(37.728396, 127.020683);
    private final LatLng A406 = new LatLng(37.662458, 126.992761);
    private final LatLng A407 = new LatLng(37.639163, 126.945918);
    private final LatLng A408 = new LatLng(37.684764, 127.033184);
    private final LatLng A409 = new LatLng(37.655675, 126.948563);
    private final LatLng A410 = new LatLng(37.625773, 126.962125);
    private final LatLng A411 = new LatLng(37.685956, 127.034919);
    private final LatLng A412 = new LatLng(37.694264, 127.020452);
    private final LatLng A413 = new LatLng(37.691258, 127.023054);
    private final LatLng A414 = new LatLng(37.695129, 127.020474);
    private final LatLng A415 = new LatLng(37.678085, 127.002628);
    private final LatLng A416 = new LatLng(37.689215, 127.025552);
    private final LatLng A417 = new LatLng(37.662458, 126.992761);
    private final LatLng A418 = new LatLng(37.677539, 127.032314);
    private final LatLng A419 = new LatLng(37.688372, 127.030348);
    private final LatLng A420 = new LatLng(37.671517, 126.999983);
    private final LatLng A421 = new LatLng(37.656508, 126.991597);
    private final LatLng A422 = new LatLng(37.623912, 126.990596);
    private final LatLng A423 = new LatLng(37.636245, 127.001986);
    private final LatLng A424 = new LatLng(37.682973, 127.010669);
    private final LatLng A425 = new LatLng(37.694061, 127.020469);
    private final LatLng A426 = new LatLng(37.644119, 126.961764);
    private final LatLng A427 = new LatLng(37.653326, 126.960019);
    private final LatLng A428 = new LatLng(37.68739, 127.016222);
    private final LatLng A429 = new LatLng(37.640704, 127.000794);
    private final LatLng A430 = new LatLng(37.623912, 126.990596);
    private final LatLng A431 = new LatLng(37.662458, 126.992761);
    private final LatLng A432 = new LatLng(37.649558, 126.97265);
    private final LatLng A433 = new LatLng(37.661278, 126.985133);
    private final LatLng A434 = new LatLng(37.661278, 126.985133);
    private final LatLng A435 = new LatLng(37.659108, 126.960687);
    private final LatLng A436 = new LatLng(37.646976, 126.962352);
    private final LatLng A437 = new LatLng(37.623912, 126.990596);
    private final LatLng A438 = new LatLng(37.616473, 126.977444);
    private final LatLng A439 = new LatLng(37.616473, 126.977444);
    private final LatLng A440 = new LatLng(37.616473, 126.977444);
    private final LatLng A441 = new LatLng(37.616473, 126.977444);
    private final LatLng A442 = new LatLng(37.645103, 126.940145);
    private final LatLng A443 = new LatLng(37.643858, 126.962058);
    private final LatLng A444 = new LatLng(37.665589, 126.998533);
    private final LatLng A445 = new LatLng(37.659108, 126.960687);
    private final LatLng A446 = new LatLng(37.645676, 126.970211);
    private final LatLng A447 = new LatLng(37.645676, 126.970211);
    private final LatLng A448 = new LatLng(37.659108, 126.960687);
    private final LatLng A449 = new LatLng(37.659108, 126.960687);
    private final LatLng A450 = new LatLng(37.659108, 126.960687);
    private final LatLng A451 = new LatLng(37.659108, 126.960687);
    private final LatLng A452 = new LatLng(37.645676, 126.970211);
    private final LatLng A453 = new LatLng(37.659108, 126.960687);
    private final LatLng A454 = new LatLng(37.645676, 126.970211);
    private final LatLng A455 = new LatLng(37.626931, 126.960534);
    private final LatLng A456 = new LatLng(37.626931, 126.960534);
    private final LatLng A457 = new LatLng(37.691749, 126.976922);
    private final LatLng A458 = new LatLng(37.691169, 126.995298);
    private final LatLng A459 = new LatLng(37.64553, 126.978304);
    private final LatLng A460 = new LatLng(37.650402, 126.966599);
    private final LatLng A461 = new LatLng(37.656514, 126.969064);
    private final LatLng A462 = new LatLng(37.646852, 126.962603);
    private final LatLng A463 = new LatLng(37.671966, 126.957753);
    private final LatLng A464 = new LatLng(37.675453, 126.959611);
    private final LatLng A465 = new LatLng(37.676348, 126.970309);
    private final LatLng A466 = new LatLng(37.704601, 127.024069);
    private final LatLng A467 = new LatLng(37.721036, 127.030092);
    private final LatLng A468 = new LatLng(37.62678, 126.960842);
    private final LatLng A469 = new LatLng(37.709897, 127.039152);
    private final LatLng A470 = new LatLng(37.653326, 126.960019);
    private final LatLng A471 = new LatLng(37.635736, 126.976283);
    private final LatLng A472 = new LatLng(37.646083, 126.976585);
    private final LatLng A473 = new LatLng(37.646976, 126.962352);
    private final LatLng A474 = new LatLng(37.653326, 126.960019);
    private final LatLng A475 = new LatLng(37.649553, 126.972639);
    private final LatLng A476 = new LatLng(37.645555, 126.978066);
    private final LatLng A477 = new LatLng(37.687965, 127.030262);
    private final LatLng A478 = new LatLng(37.65385, 126.951025);
    private final LatLng A479 = new LatLng(37.645676, 126.970211);
    private final LatLng A480 = new LatLng(37.655471, 126.954946);
    private final LatLng A481 = new LatLng(37.65385, 126.951025);
    private final LatLng A482 = new LatLng(37.645676, 126.970211);
    private final LatLng A483 = new LatLng(37.655471, 126.954946);
    private final LatLng A484 = new LatLng(37.645676, 126.970211);
    private final LatLng A485 = new LatLng(37.645676, 126.970211);
    private final LatLng A486 = new LatLng(37.611798, 126.988087);
    private final LatLng A487 = new LatLng(37.648438, 126.972825);
    private final LatLng A488 = new LatLng(37.732442, 127.023569);
    private final LatLng A489 = new LatLng(37.691211, 126.98973);
    private final LatLng A490 = new LatLng(37.637117, 126.997533);
    private final LatLng A491 = new LatLng(37.623912, 126.990596);
    private final LatLng A492 = new LatLng(37.662458, 126.992761);
    private final LatLng A493 = new LatLng(37.710311, 127.003747);
    private final LatLng A494 = new LatLng(37.707147, 127.004564);
    private final LatLng A495 = new LatLng(37.695129, 127.020474);
    private final LatLng A496 = new LatLng(37.65489, 126.950466);
    private final LatLng A497 = new LatLng(37.648084, 126.971515);
    private final LatLng A498 = new LatLng(37.637117, 126.997533);
    private final LatLng A499 = new LatLng(37.623912, 126.990596);
    private final LatLng A500 = new LatLng(37.662458, 126.992761);
    private final LatLng A501 = new LatLng(37.710311, 127.003747);
    private final LatLng A502 = new LatLng(37.707147, 127.004564);
    private final LatLng A503 = new LatLng(37.695129, 127.020474);
    private final LatLng A504 = new LatLng(37.65489, 126.950466);
    private final LatLng A505 = new LatLng(37.648084, 126.971515);
    private final LatLng A506 = new LatLng(37.643597, 126.996698);
    private final LatLng A507 = new LatLng(37.611798, 126.988087);
    private final LatLng A508 = new LatLng(37.665589, 126.998533);
    private final LatLng A509 = new LatLng(37.710311, 127.003747);
    private final LatLng A510 = new LatLng(37.707147, 127.004564);
    private final LatLng A511 = new LatLng(37.695129, 127.020474);
    private final LatLng A512 = new LatLng(37.65489, 126.950466);
    private final LatLng A513 = new LatLng(37.648084, 126.971515);
    private final LatLng A514 = new LatLng(37.695129, 127.020474);
    private final LatLng A515 = new LatLng(37.669503, 127.022781);
    private final LatLng A516 = new LatLng(37.686444, 127.023694);
    private final LatLng A517 = new LatLng(37.695129, 127.020474);
    private final LatLng A518 = new LatLng(37.694264, 127.020452);
    private final LatLng A519 = new LatLng(37.695129, 127.020474);
    private final LatLng A520 = new LatLng(37.695129, 127.020474);
    private final LatLng A521 = new LatLng(37.669503, 127.022781);
    private final LatLng A522 = new LatLng(37.686444, 127.023694);
    private final LatLng A523 = new LatLng(37.695129, 127.020474);
    private final LatLng A524 = new LatLng(37.694264, 127.020452);
    private final LatLng A525 = new LatLng(37.695129, 127.020474);
    private final LatLng A526 = new LatLng(37.695129, 127.020474);
    private final LatLng A527 = new LatLng(37.669503, 127.022781);
    private final LatLng A528 = new LatLng(37.686444, 127.023694);
    private final LatLng A529 = new LatLng(37.694264, 127.020452);
    private final LatLng A530 = new LatLng(37.695129, 127.020474);
    private final LatLng A531 = new LatLng(37.691169, 126.995298);
    private final LatLng A532 = new LatLng(37.691169, 126.995298);
    private final LatLng A533 = new LatLng(37.662458, 126.992761);
    private final LatLng A534 = new LatLng(37.662458, 126.992761);
    private final LatLng A535 = new LatLng(37.662458, 126.992761);
    private final LatLng A536 = new LatLng(37.643003, 126.945104);
    private final LatLng A537 = new LatLng(37.62678, 126.960842);
    private final LatLng A538 = new LatLng(37.625773, 126.962125);
    private final LatLng A539 = new LatLng(37.625773, 126.962125);
    private final LatLng A540 = new LatLng(37.71085, 126.992458);
    private final LatLng A541 = new LatLng(37.641432, 127.00484);
    private final LatLng A542 = new LatLng(37.641432, 127.00484);
    private final LatLng A543 = new LatLng(37.641993, 127.005184);
    private final LatLng A544 = new LatLng(37.689215, 127.025552);
    private final LatLng A545 = new LatLng(37.665469, 126.971647);
    private final LatLng A546 = new LatLng(37.665469, 126.971647);
    private final LatLng A547 = new LatLng(37.653131, 126.960386);
    private final LatLng A548 = new LatLng(37.653131, 126.960386);
    private final LatLng A549 = new LatLng(37.653326, 126.960019);
    private final LatLng A550 = new LatLng(37.637117, 126.997533);
    private final LatLng A551 = new LatLng(37.637117, 126.997533);
    private final LatLng A552 = new LatLng(37.665589, 126.998533);
    private final LatLng A553 = new LatLng(37.643627, 127.003361);
    private final LatLng A554 = new LatLng(37.643167, 127.002353);
    private final LatLng A555 = new LatLng(37.643195, 127.002347);
    private final LatLng A556 = new LatLng(37.709694, 126.999956);
    private final LatLng A557 = new LatLng(37.710367, 127.038989);
    private final LatLng A558 = new LatLng(37.665484, 126.971683);
    private final LatLng A559 = new LatLng(37.631294, 126.968733);
    private final LatLng A560 = new LatLng(37.659108, 126.960687);
    private final LatLng A561 = new LatLng(37.642392, 126.979333);
    private final LatLng A562 = new LatLng(37.659108, 126.960687);
    private final LatLng A563 = new LatLng(37.645676, 126.970211);
    private final LatLng A564 = new LatLng(37.627914, 126.977197);
    private final LatLng A565 = new LatLng(37.623032, 126.974217);
    private final LatLng A566 = new LatLng(37.628, 126.977086);
    private final LatLng A567 = new LatLng(37.651327, 127.006114);
    private final LatLng A568 = new LatLng(37.693419, 126.992111);
    private final LatLng A569 = new LatLng(37.691878, 126.991842);
    private final LatLng A570 = new LatLng(37.717375, 127.018133);
    private final LatLng A571 = new LatLng(37.693419, 126.992111);
    private final LatLng A572 = new LatLng(37.652628, 126.954839);
    private final LatLng A573 = new LatLng(37.652628, 126.954839);
    private final LatLng A574 = new LatLng(37.681839, 127.013547);
    private final LatLng A575 = new LatLng(37.609094, 126.952531);
    private final LatLng A576 = new LatLng(37.659018, 126.96079);
    private final LatLng A577 = new LatLng(37.659018, 126.96079);
    private final LatLng A578 = new LatLng(37.659018, 126.96079);
    private final LatLng A579 = new LatLng(37.659018, 126.96079);
    private final LatLng A580 = new LatLng(37.665484, 126.971683);
    private final LatLng A581 = new LatLng(37.658842, 126.981281);
    private final LatLng A582 = new LatLng(37.652628, 126.954839);
    private final LatLng A583 = new LatLng(37.682359, 127.016912);
    private final LatLng A584 = new LatLng(37.682359, 127.016912);
    private final LatLng A585 = new LatLng(37.623912, 126.990596);
    private final LatLng A586 = new LatLng(37.706051, 127.00671);
    private final LatLng A587 = new LatLng(37.706051, 127.00671);
    private final LatLng A588 = new LatLng(37.706325, 127.005067);
    private final LatLng A589 = new LatLng(37.706325, 127.005067);
    private final LatLng A590 = new LatLng(37.728369, 127.020694);
    private final LatLng A591 = new LatLng(37.728369, 127.020694);
    private final LatLng A592 = new LatLng(37.728369, 127.020694);
    private final LatLng A593 = new LatLng(37.618438, 126.953902);
    private final LatLng A594 = new LatLng(37.728369, 127.020694);
    private final LatLng A595 = new LatLng(37.728369, 127.020694);
    private final LatLng A596 = new LatLng(37.62678, 126.960842);
    private final LatLng A597 = new LatLng(37.631617, 126.972922);
    private final LatLng A598 = new LatLng(37.631617, 126.972922);
    private final LatLng A599 = new LatLng(37.689613, 126.963368);
    private final LatLng A600 = new LatLng(37.689613, 126.963368);
    private final LatLng A601 = new LatLng(37.689613, 126.963368);
    private final LatLng A602 = new LatLng(37.6893, 126.963081);
    private final LatLng A603 = new LatLng(37.676731, 127.021725);
    private final LatLng A604 = new LatLng(37.675522, 127.005556);
    private final LatLng A605 = new LatLng(37.677819, 127.032342);
    private final LatLng A606 = new LatLng(37.687958, 127.031525);
    private final LatLng A607 = new LatLng(37.648438, 126.972825);
    private final LatLng A608 = new LatLng(37.689613, 126.963368);
    private final LatLng A609 = new LatLng(37.656514, 126.969064);
    private final LatLng A610 = new LatLng(37.671966, 126.957753);
    private final LatLng A611 = new LatLng(37.675413, 126.959326);
    private final LatLng A612 = new LatLng(37.676348, 126.970309);
    private final LatLng A613 = new LatLng(37.646852, 126.962603);
    private final LatLng A614 = new LatLng(37.649739, 127.005119);
    private final LatLng A615 = new LatLng(37.694931, 127.015083);
    private final LatLng A616 = new LatLng(37.642344, 126.946153);
    private final LatLng A617 = new LatLng(37.633656, 126.950194);
    private final LatLng A618 = new LatLng(37.630879, 127.003531);
    private final LatLng A619 = new LatLng(37.645555, 126.978066);
    private final LatLng A620 = new LatLng(37.649853, 126.967147);
    private final LatLng A621 = new LatLng(37.720891, 127.030525);
    private final LatLng A622 = new LatLng(37.656235, 127.000495);
    private final LatLng A623 = new LatLng(37.712386, 126.984577);
    private final LatLng A624 = new LatLng(37.711482, 126.986382);
    private final LatLng A625 = new LatLng(37.710812, 126.99543);
    private final LatLng A626 = new LatLng(37.6436, 126.996606);
    private final LatLng A627 = new LatLng(37.720925, 127.030006);
    private final LatLng A628 = new LatLng(37.704601, 127.024069);
    private final LatLng A629 = new LatLng(37.690089, 127.017425);
    private final LatLng A630 = new LatLng(37.660642, 126.993697);
    private final LatLng A631 = new LatLng(37.695129, 127.020474);
    private final LatLng A632 = new LatLng(37.629197, 126.958769);
    private final LatLng A633 = new LatLng(37.655675, 126.948563);
    private final LatLng A634 = new LatLng(37.720567, 127.003522);
    private final LatLng A635 = new LatLng(37.709897, 127.039152);
    private final LatLng A636 = new LatLng(37.66135, 126.985275);
    private final LatLng A637 = new LatLng(37.652181, 127.008051);
    private final LatLng A638 = new LatLng(37.686947, 127.02472);
    private final LatLng A639 = new LatLng(37.681839, 127.013547);
    private final LatLng A640 = new LatLng(37.679636, 127.014928);
    private final LatLng A641 = new LatLng(37.678406, 127.015825);
    private final LatLng A642 = new LatLng(37.696275, 127.018672);
    private final LatLng A643 = new LatLng(37.645175, 126.940099);
    private final LatLng A644 = new LatLng(37.660642, 126.993697);
    private final LatLng A645 = new LatLng(37.635392, 127.003028);
    private final LatLng A646 = new LatLng(37.688553, 127.027581);
    private final LatLng A647 = new LatLng(37.621625, 126.994156);
    private final LatLng A648 = new LatLng(37.61783, 126.943296);
    private final LatLng A649 = new LatLng(37.706075, 127.006656);
    private final LatLng A650 = new LatLng(37.675882, 127.026849);
    private final LatLng A651 = new LatLng(37.675882, 127.026849);
    private final LatLng A652 = new LatLng(37.61783, 126.943296);
    private final LatLng A653 = new LatLng(37.68711, 127.024888);
    private final LatLng A654 = new LatLng(37.676457, 127.02785);
    private final LatLng A655 = new LatLng(37.678014, 127.002503);
    private final LatLng A656 = new LatLng(37.611798, 126.988087);
    private final LatLng A657 = new LatLng(37.693758, 127.022465);
    private final LatLng A658 = new LatLng(37.706051, 127.00671);
    private final LatLng A659 = new LatLng(37.706051, 127.00671);
    private final LatLng A660 = new LatLng(37.720386, 127.003608);
    private final LatLng A661 = new LatLng(37.677583, 126.962152);
    private final LatLng A662 = new LatLng(37.677583, 126.962152);
    private final LatLng A663 = new LatLng(37.677583, 126.962152);
    private final LatLng A664 = new LatLng(37.645528, 126.978664);
    private final LatLng A665 = new LatLng(37.646103, 126.980919);
    private final LatLng A666 = new LatLng(37.68739, 127.016222);
    private final LatLng A667 = new LatLng(37.68739, 127.016222);
    private final LatLng A668 = new LatLng(37.68739, 127.016222);
    private final LatLng A669 = new LatLng(37.653326, 126.960019);
    private final LatLng A670 = new LatLng(37.645343, 126.976527);
    private final LatLng A671 = new LatLng(37.657821, 127.002638);
    private final LatLng A672 = new LatLng(37.631456, 126.992119);
    private final LatLng A673 = new LatLng(37.691853, 126.991869);
    private final LatLng A674 = new LatLng(37.64251, 127.001302);
    private final LatLng A675 = new LatLng(37.624608, 127.010027);
    private final LatLng A676 = new LatLng(37.696354, 126.97594);
    private final LatLng A677 = new LatLng(37.691169, 126.995298);
    private final LatLng A678 = new LatLng(37.691211, 126.98973);
    private final LatLng A679 = new LatLng(37.64251, 127.001302);
    private final LatLng A680 = new LatLng(37.675789, 127.026908);
    private final LatLng A681 = new LatLng(37.626822, 126.960686);
    private final LatLng A682 = new LatLng(37.681225, 127.016931);
    private final LatLng A683 = new LatLng(37.684764, 127.033184);
    private final LatLng A684 = new LatLng(37.621625, 126.994156);
    private final LatLng A685 = new LatLng(37.613095, 126.998558);
    private final LatLng A686 = new LatLng(37.639163, 126.945918);
    private final LatLng A687 = new LatLng(37.61783, 126.943296);
    private final LatLng A688 = new LatLng(37.648084, 126.971515);
    private final LatLng A689 = new LatLng(37.645125, 127.000869);
    private final LatLng A690 = new LatLng(37.670953, 127.004772);
    private final LatLng A691 = new LatLng(37.650649, 127.010123);
    private final LatLng A692 = new LatLng(37.649662, 127.003167);
    private final LatLng A693 = new LatLng(37.672728, 127.000544);
    private final LatLng A694 = new LatLng(37.662458, 126.992761);
    private final LatLng A695 = new LatLng(37.676928, 127.0031);
    private final LatLng A696 = new LatLng(37.688759, 127.032599);
    private final LatLng A697 = new LatLng(37.676508, 127.020628);
    private final LatLng A698 = new LatLng(37.6755, 127.022728);
    private final LatLng A699 = new LatLng(37.675697, 127.026436);
    private final LatLng A700 = new LatLng(37.676561, 127.028044);
    private final LatLng A701 = new LatLng(37.645125, 127.000869);
    private final LatLng A702 = new LatLng(37.670953, 127.004772);
    private final LatLng A703 = new LatLng(37.650649, 127.010123);
    private final LatLng A704 = new LatLng(37.649662, 127.003167);
    private final LatLng A705 = new LatLng(37.662458, 126.992761);
    private final LatLng A706 = new LatLng(37.662458, 126.992761);
    private final LatLng A707 = new LatLng(37.676928, 127.0031);
    private final LatLng A708 = new LatLng(37.688759, 127.032599);
    private final LatLng A709 = new LatLng(37.676508, 127.020628);
    private final LatLng A710 = new LatLng(37.6755, 127.022728);
    private final LatLng A711 = new LatLng(37.675697, 127.026436);
    private final LatLng A712 = new LatLng(37.676561, 127.028044);
    private final LatLng A713 = new LatLng(37.665589, 126.998533);
    private final LatLng A714 = new LatLng(37.635478, 126.976464);
    private final LatLng A715 = new LatLng(37.618347, 126.943672);
    private final LatLng A716 = new LatLng(37.647352, 126.941802);
    private final LatLng A717 = new LatLng(37.720806, 127.020278);
    private final LatLng A718 = new LatLng(37.61783, 126.943296);
    private final LatLng A719 = new LatLng(37.611798, 126.988087);
    private final LatLng A720 = new LatLng(37.624236, 126.990128);
    private final LatLng A721 = new LatLng(37.659108, 126.960687);
    private final LatLng A722 = new LatLng(37.655825, 126.968314);
    private final LatLng A723 = new LatLng(37.634031, 126.969264);
    private final LatLng A724 = new LatLng(37.655825, 126.968314);
    private final LatLng A725 = new LatLng(37.642481, 126.950892);
    private final LatLng A726 = new LatLng(37.628831, 126.959006);
    private final LatLng A727 = new LatLng(37.642481, 126.950892);
    private final LatLng A728 = new LatLng(37.628831, 126.959006);
    private final LatLng A729 = new LatLng(37.688069, 127.023831);
    private final LatLng A730 = new LatLng(37.688069, 127.023831);
    private final LatLng A731 = new LatLng(37.688069, 127.023831);
    private final LatLng A732 = new LatLng(37.690042, 127.021);
    private final LatLng A733 = new LatLng(37.635736, 126.976283);
    private final LatLng A734 = new LatLng(37.651364, 126.981753);
    private final LatLng A735 = new LatLng(37.651364, 126.981753);
    private final LatLng A736 = new LatLng(37.659108, 126.960687);
    private final LatLng A737 = new LatLng(37.658042, 126.947214);
    private final LatLng A738 = new LatLng(37.654908, 126.950444);
    private final LatLng A739 = new LatLng(37.658051, 126.947169);
    private final LatLng A740 = new LatLng(37.706051, 127.00671);
    private final LatLng A741 = new LatLng(37.706051, 127.00671);
    private final LatLng A742 = new LatLng(37.706051, 127.00671);
    private final LatLng A743 = new LatLng(37.673693, 126.984277);
    private final LatLng A744 = new LatLng(37.673693, 126.984277);
    private final LatLng A745 = new LatLng(37.673693, 126.984277);
    private final LatLng A746 = new LatLng(37.659108, 126.960687);
    private final LatLng A747 = new LatLng(37.654661, 126.960508);
    private final LatLng A748 = new LatLng(37.654661, 126.960508);
    private final LatLng A749 = new LatLng(37.654908, 126.950444);
    private final LatLng A750 = new LatLng(37.655358, 126.953889);
    private final LatLng A751 = new LatLng(37.655358, 126.953889);
    private final LatLng A752 = new LatLng(37.631456, 126.992119);
    private final LatLng A753 = new LatLng(37.655358, 126.953889);
    private final LatLng A754 = new LatLng(37.631928, 126.987075);
    private final LatLng A755 = new LatLng(37.726931, 127.0201);
    private final LatLng A756 = new LatLng(37.626386, 126.994422);
    private final LatLng A757 = new LatLng(37.648084, 126.971515);
    private final LatLng A758 = new LatLng(37.730524, 127.020791);
    private final LatLng A759 = new LatLng(37.726642, 127.026731);
    private final LatLng A760 = new LatLng(37.685656, 127.0204);
    private final LatLng A761 = new LatLng(37.685778, 127.030484);
    private final LatLng A762 = new LatLng(37.685656, 127.0204);
    private final LatLng A763 = new LatLng(37.683728, 127.031817);
    private final LatLng A764 = new LatLng(37.706051, 127.00671);
    private final LatLng A765 = new LatLng(37.685656, 127.0204);
    private final LatLng A766 = new LatLng(37.685074, 127.030368);
    private final LatLng A767 = new LatLng(37.645676, 126.970211);
    private final LatLng A768 = new LatLng(37.706051, 127.00671);
    private final LatLng A769 = new LatLng(37.728369, 127.020694);
    private final LatLng A770 = new LatLng(37.706051, 127.00671);
    private final LatLng A771 = new LatLng(37.618308, 126.939142);
    private final LatLng A772 = new LatLng(37.618308, 126.939142);
    private final LatLng A773 = new LatLng(37.618308, 126.939142);
    private final LatLng A774 = new LatLng(37.656472, 126.969078);
    private final LatLng A775 = new LatLng(37.645697, 126.977882);
    private final LatLng A776 = new LatLng(37.645697, 126.977882);
    private final LatLng A777 = new LatLng(37.645697, 126.977882);
    private final LatLng A778 = new LatLng(37.719993, 127.027633);
    private final LatLng A779 = new LatLng(37.719298, 127.026349);
    private final LatLng A780 = new LatLng(37.719298, 127.026349);
    private final LatLng A781 = new LatLng(37.688389, 127.027048);
    private final LatLng A782 = new LatLng(37.688589, 127.027592);
    private final LatLng A783 = new LatLng(37.688589, 127.027592);
    private final LatLng A784 = new LatLng(37.700731, 127.015636);
    private final LatLng A785 = new LatLng(37.645676, 126.970211);
    private final LatLng A786 = new LatLng(37.652181, 127.008051);
    private final LatLng A787 = new LatLng(37.651073, 127.010265);
    private final LatLng A788 = new LatLng(37.645533, 126.978272);
    private final LatLng A789 = new LatLng(37.656472, 126.969078);
    private final LatLng A790 = new LatLng(37.721565, 127.003259);
    private final LatLng A791 = new LatLng(37.651073, 127.010265);
    private final LatLng A792 = new LatLng(37.7234, 127.003761);
    private final LatLng A793 = new LatLng(37.645374, 127.00004);
    private final LatLng A794 = new LatLng(37.629176, 126.958747);
    private final LatLng A795 = new LatLng(37.706051, 127.00671);
    private final LatLng A796 = new LatLng(37.706051, 127.00671);
    private final LatLng A797 = new LatLng(37.688553, 127.027581);
    private final LatLng A798 = new LatLng(37.665589, 126.998533);
    private final LatLng A799 = new LatLng(37.6679, 126.998853);
    private final LatLng A800 = new LatLng(37.6679, 126.998853);
    private final LatLng A801 = new LatLng(37.732442, 127.023569);
    private final LatLng A802 = new LatLng(37.732442, 127.023569);
    private final LatLng A803 = new LatLng(37.710983, 127.023226);
    private final LatLng A804 = new LatLng(37.665589, 126.998533);
    private final LatLng A805 = new LatLng(37.665589, 126.998533);
    private final LatLng A806 = new LatLng(37.665589, 126.998533);
    private final LatLng A807 = new LatLng(37.658051, 126.947169);
    private final LatLng A808 = new LatLng(37.65806, 126.947158);
    private final LatLng A809 = new LatLng(37.645374, 127.00004);
    private final LatLng A810 = new LatLng(37.706051, 127.00671);
    private final LatLng A811 = new LatLng(37.645676, 126.970211);
    private final LatLng A812 = new LatLng(37.657789, 127.002181);
    private final LatLng A813 = new LatLng(37.717342, 126.984122);
    private final LatLng A814 = new LatLng(37.623802, 127.007364);
    private final LatLng A815 = new LatLng(37.647213, 126.941294);
    private final LatLng A816 = new LatLng(37.677139, 127.036014);
    private final LatLng A817 = new LatLng(37.649025, 126.98215);
    private final LatLng A818 = new LatLng(37.649025, 126.98215);
    private final LatLng A819 = new LatLng(37.693758, 127.022465);
    private final LatLng A820 = new LatLng(37.662458, 126.992761);
    private final LatLng A821 = new LatLng(37.722553, 127.010392);
    private final LatLng A822 = new LatLng(37.655778, 126.992839);
    private final LatLng A823 = new LatLng(37.665589, 126.998533);
    private final LatLng A824 = new LatLng(37.711482, 126.986382);
    private final LatLng A825 = new LatLng(37.678085, 127.002628);
    private final LatLng A826 = new LatLng(37.622375, 126.974342);
    private final LatLng A827 = new LatLng(37.664011, 126.972011);
    private final LatLng A828 = new LatLng(37.663961, 126.971997);
    private final LatLng A829 = new LatLng(37.676159, 126.964213);
    private final LatLng A830 = new LatLng(37.725731, 127.020714);
    private final LatLng A831 = new LatLng(37.677539, 127.032314);
    private final LatLng A832 = new LatLng(37.677506, 127.032319);
    private final LatLng A833 = new LatLng(37.677506, 127.032319);
    private final LatLng A834 = new LatLng(37.652614, 126.955586);
    private final LatLng A835 = new LatLng(37.642508, 126.979333);
    private final LatLng A836 = new LatLng(37.6218, 126.994081);
    private final LatLng A837 = new LatLng(37.652614, 126.955586);
    private final LatLng A838 = new LatLng(37.684811, 127.029789);
    private final LatLng A839 = new LatLng(37.665247, 126.975808);
    private final LatLng A840 = new LatLng(37.662458, 126.992761);
    private final LatLng A841 = new LatLng(37.662458, 126.992761);
    private final LatLng A842 = new LatLng(37.662458, 126.992761);
    private final LatLng A843 = new LatLng(37.649258, 126.9817);
    private final LatLng A844 = new LatLng(37.641684, 127.001698);
    private final LatLng A845 = new LatLng(37.6218, 126.994081);
    private final LatLng A846 = new LatLng(37.727361, 127.026275);
    private final LatLng A847 = new LatLng(37.663958, 126.968947);
    private final LatLng A848 = new LatLng(37.727361, 127.026275);
    private final LatLng A849 = new LatLng(37.727143, 127.026054);
    private final LatLng A850 = new LatLng(37.624444, 126.981111);
    private final LatLng A851 = new LatLng(37.633205, 127.007987);
    private final LatLng A852 = new LatLng(37.703944, 127.028058);
    private final LatLng A853 = new LatLng(37.637735, 126.943445);
    private final LatLng A854 = new LatLng(37.637735, 126.943445);
    private final LatLng A855 = new LatLng(37.703944, 127.028058);
    private final LatLng A856 = new LatLng(37.636353, 126.978058);
    private final LatLng A857 = new LatLng(37.632997, 127.007953);
    private final LatLng A858 = new LatLng(37.720508, 127.003539);
    private final LatLng A859 = new LatLng(37.659108, 126.960687);
    private final LatLng A860 = new LatLng(37.668272, 127.025153);
    private final LatLng A861 = new LatLng(37.684731, 127.033189);
    private final LatLng A862 = new LatLng(37.712319, 126.989);
    private final LatLng A863 = new LatLng(37.722564, 126.994208);
    private final LatLng A864 = new LatLng(37.687883, 127.030308);
    private final LatLng A865 = new LatLng(37.645103, 126.940145);
    private final LatLng A866 = new LatLng(37.722564, 126.994208);
    private final LatLng A867 = new LatLng(37.713012, 126.99143);
    private final LatLng A868 = new LatLng(37.722596, 126.994216);
    private final LatLng A869 = new LatLng(37.687931, 127.030264);
    private final LatLng A870 = new LatLng(37.645103, 126.940145);
    private final LatLng A871 = new LatLng(37.687883, 127.030308);
    private final LatLng A872 = new LatLng(37.705103, 127.028253);
    private final LatLng A873 = new LatLng(37.705103, 127.028253);
    private final LatLng A874 = new LatLng(37.631767, 126.973417);
    private final LatLng A875 = new LatLng(37.626522, 126.960234);
    private final LatLng A876 = new LatLng(37.612686, 126.981881);
    private final LatLng A877 = new LatLng(37.705103, 127.028253);
    private final LatLng A878 = new LatLng(37.661194, 127.015939);
    private final LatLng A879 = new LatLng(37.618836, 126.953875);
    private final LatLng A880 = new LatLng(37.662458, 126.992761);
    private final LatLng A881 = new LatLng(37.665589, 126.998533);
    private final LatLng A882 = new LatLng(37.706325, 127.005067);
    private final LatLng A883 = new LatLng(37.707047, 126.978844);
    private final LatLng A884 = new LatLng(37.707039, 126.979003);
    private final LatLng A885 = new LatLng(37.687933, 127.030508);
    private final LatLng A886 = new LatLng(37.627683, 126.976794);
    private final LatLng A887 = new LatLng(37.655981, 126.948203);
    private final LatLng A888 = new LatLng(37.614914, 126.958378);
    private final LatLng A889 = new LatLng(37.618233, 126.95935);
    private final LatLng A890 = new LatLng(37.694408, 127.019861);
    private final LatLng A891 = new LatLng(37.694408, 127.019861);
    private final LatLng A892 = new LatLng(37.694408, 127.019861);
    private final LatLng A893 = new LatLng(37.652683, 126.955529);
    private final LatLng A894 = new LatLng(37.671966, 126.957753);
    private final LatLng A895 = new LatLng(37.61783, 126.943296);
    private final LatLng A896 = new LatLng(37.645175, 126.940099);
    private final LatLng A897 = new LatLng(37.629065, 127.001085);
    private final LatLng A898 = new LatLng(37.629065, 127.001085);
    private final LatLng A899 = new LatLng(37.630879, 127.003531);
    private final LatLng A900 = new LatLng(37.730427, 127.007133);
    private final LatLng A901 = new LatLng(37.623912, 126.990596);
    private final LatLng A902 = new LatLng(37.691878, 126.991842);
    private final LatLng A903 = new LatLng(37.691878, 126.991842);
    private final LatLng A904 = new LatLng(37.711622, 126.977811);
    private final LatLng A905 = new LatLng(37.711622, 126.977811);
    private final LatLng A906 = new LatLng(37.694209, 127.019341);
    private final LatLng A907 = new LatLng(37.720533, 127.003522);
    private final LatLng A908 = new LatLng(37.711482, 126.986382);
    private final LatLng A909 = new LatLng(37.694209, 127.019341);
    private final LatLng A910 = new LatLng(37.685417, 127.013464);
    private final LatLng A911 = new LatLng(37.703197, 127.029267);
    private final LatLng A912 = new LatLng(37.704601, 127.024069);
    private final LatLng A913 = new LatLng(37.736692, 127.028789);
    private final LatLng A914 = new LatLng(37.676375, 127.016908);
    private final LatLng A915 = new LatLng(37.659108, 126.960687);
    private final LatLng A916 = new LatLng(37.70323, 127.029258);
    private final LatLng A917 = new LatLng(37.720178, 127.025331);
    private final LatLng A918 = new LatLng(37.647775, 127.008547);
    private final LatLng A919 = new LatLng(37.694203, 127.019311);
    private final LatLng A920 = new LatLng(37.687431, 126.964272);
    private final LatLng A921 = new LatLng(37.668396, 126.961577);
    private final LatLng A922 = new LatLng(37.696825, 127.017625);
    private final LatLng A923 = new LatLng(37.696825, 127.017625);
    private final LatLng A924 = new LatLng(37.629065, 127.001085);
    private final LatLng A925 = new LatLng(37.682214, 127.042331);
    private final LatLng A926 = new LatLng(37.737117, 127.028536);
    private final LatLng A927 = new LatLng(37.650402, 126.966599);
    private final LatLng A928 = new LatLng(37.687965, 127.030262);
    private final LatLng A929 = new LatLng(37.645242, 126.974217);
    private final LatLng A930 = new LatLng(37.637844, 126.946491);
    private final LatLng A931 = new LatLng(37.6859, 127.021328);
    private final LatLng A932 = new LatLng(37.6859, 127.021328);
    private final LatLng A933 = new LatLng(37.727143, 127.026054);
    private final LatLng A934 = new LatLng(37.645486, 127.000083);
    private final LatLng A935 = new LatLng(37.619228, 126.990711);
    private final LatLng A936 = new LatLng(37.68315, 127.017083);
    private final LatLng A937 = new LatLng(37.721883, 127.031989);
    private final LatLng A938 = new LatLng(37.722408, 127.010392);
    private final LatLng A939 = new LatLng(37.69665, 127.001414);
    private final LatLng A940 = new LatLng(37.722408, 127.010392);
    private final LatLng A941 = new LatLng(37.69665, 127.001414);
    private final LatLng A942 = new LatLng(37.721565, 127.003259);
    private final LatLng A943 = new LatLng(37.706051, 127.00671);
    private final LatLng A944 = new LatLng(37.722408, 127.010392);
    private final LatLng A945 = new LatLng(37.69665, 127.001414);
    private final LatLng A946 = new LatLng(37.722408, 127.010392);
    private final LatLng A947 = new LatLng(37.69665, 127.001414);
    private final LatLng A948 = new LatLng(37.68275, 127.017975);
    private final LatLng A949 = new LatLng(37.659108, 126.960687);
    private final LatLng A950 = new LatLng(37.646653, 126.978747);
    private final LatLng A951 = new LatLng(37.665484, 126.971683);
    private final LatLng A952 = new LatLng(37.642394, 126.971431);
    private final LatLng A953 = new LatLng(37.659108, 126.960687);
    private final LatLng A954 = new LatLng(37.646653, 126.978747);
    private final LatLng A955 = new LatLng(37.665469, 126.971647);
    private final LatLng A956 = new LatLng(37.642394, 126.971431);
    private final LatLng A957 = new LatLng(37.659108, 126.960687);
    private final LatLng A958 = new LatLng(37.646653, 126.978747);
    private final LatLng A959 = new LatLng(37.665469, 126.971647);
    private final LatLng A960 = new LatLng(37.642394, 126.971431);
    private final LatLng A961 = new LatLng(37.665589, 126.998533);
    private final LatLng A962 = new LatLng(37.646478, 126.971306);
    private final LatLng A963 = new LatLng(37.646478, 126.971306);
    private final LatLng A964 = new LatLng(37.665589, 126.998533);
    private final LatLng A965 = new LatLng(37.665589, 126.998533);
    private final LatLng A966 = new LatLng(37.665589, 126.998533);
    private final LatLng A967 = new LatLng(37.655863, 126.948334);
    private final LatLng A968 = new LatLng(37.684764, 127.033184);
    private final LatLng A969 = new LatLng(37.672825, 127.000322);
    private final LatLng A970 = new LatLng(37.672825, 127.000322);
    private final LatLng A971 = new LatLng(37.672825, 127.000322);
    private final LatLng A972 = new LatLng(37.618854, 126.997597);
    private final LatLng A973 = new LatLng(37.618854, 126.997597);
    private final LatLng A974 = new LatLng(37.618854, 126.997597);
    private final LatLng A975 = new LatLng(37.618854, 126.997597);
    private final LatLng A976 = new LatLng(37.654422, 126.960031);
    private final LatLng A977 = new LatLng(37.643244, 126.99675);
    private final LatLng A978 = new LatLng(37.687958, 127.030267);
    private final LatLng A979 = new LatLng(37.648403, 127.001931);
    private final LatLng A980 = new LatLng(37.614661, 126.960153);
    private final LatLng A981 = new LatLng(37.618406, 126.953842);
    private final LatLng A982 = new LatLng(37.635956, 127.002875);
    private final LatLng A983 = new LatLng(37.658069, 126.947175);
    private final LatLng A984 = new LatLng(37.678456, 126.959056);
    private final LatLng A985 = new LatLng(37.63775, 126.943467);
    private final LatLng A986 = new LatLng(37.655863, 126.948334);
    private final LatLng A987 = new LatLng(37.65489, 126.950466);
    private final LatLng A988 = new LatLng(37.619197, 126.959125);
    private final LatLng A989 = new LatLng(37.65806, 126.947158);
    private final LatLng A990 = new LatLng(37.645175, 126.940099);
    private final LatLng A991 = new LatLng(37.684764, 127.033184);
    private final LatLng A992 = new LatLng(37.629065, 127.001085);
    private final LatLng A993 = new LatLng(37.642509, 127.002254);
    private final LatLng A994 = new LatLng(37.654958, 126.950514);
    private final LatLng A995 = new LatLng(37.658106, 126.947103);
    private final LatLng A996 = new LatLng(37.665589, 126.998533);
    private final LatLng A997 = new LatLng(37.655992, 126.999014);
    private final LatLng A998 = new LatLng(37.677539, 127.032314);
    private final LatLng A999 = new LatLng(37.619228, 126.990711);
    private final LatLng A1000 = new LatLng(37.708949, 126.976821);


    private Marker markerA1;
    private Marker markerA2;
    private Marker markerA3;
    private Marker markerA4;
    private Marker markerA5;
    private Marker markerA6;
    private Marker markerA7;
    private Marker markerA8;
    private Marker markerA9;
    private Marker markerA10;
    private Marker markerA11;
    private Marker markerA12;
    private Marker markerA13;
    private Marker markerA14;
    private Marker markerA15;
    private Marker markerA16;
    private Marker markerA17;
    private Marker markerA18;
    private Marker markerA19;
    private Marker markerA20;
    private Marker markerA21;
    private Marker markerA22;
    private Marker markerA23;
    private Marker markerA24;
    private Marker markerA25;
    private Marker markerA26;
    private Marker markerA27;
    private Marker markerA28;
    private Marker markerA29;
    private Marker markerA30;
    private Marker markerA31;
    private Marker markerA32;
    private Marker markerA33;
    private Marker markerA34;
    private Marker markerA35;
    private Marker markerA36;
    private Marker markerA37;
    private Marker markerA38;
    private Marker markerA39;
    private Marker markerA40;
    private Marker markerA41;
    private Marker markerA42;
    private Marker markerA43;
    private Marker markerA44;
    private Marker markerA45;
    private Marker markerA46;
    private Marker markerA47;
    private Marker markerA48;
    private Marker markerA49;
    private Marker markerA50;
    private Marker markerA51;
    private Marker markerA52;
    private Marker markerA53;
    private Marker markerA54;
    private Marker markerA55;
    private Marker markerA56;
    private Marker markerA57;
    private Marker markerA58;
    private Marker markerA59;
    private Marker markerA60;
    private Marker markerA61;
    private Marker markerA62;
    private Marker markerA63;
    private Marker markerA64;
    private Marker markerA65;
    private Marker markerA66;
    private Marker markerA67;
    private Marker markerA68;
    private Marker markerA69;
    private Marker markerA70;
    private Marker markerA71;
    private Marker markerA72;
    private Marker markerA73;
    private Marker markerA74;
    private Marker markerA75;
    private Marker markerA76;
    private Marker markerA77;
    private Marker markerA78;
    private Marker markerA79;
    private Marker markerA80;
    private Marker markerA81;
    private Marker markerA82;
    private Marker markerA83;
    private Marker markerA84;
    private Marker markerA85;
    private Marker markerA86;
    private Marker markerA87;
    private Marker markerA88;
    private Marker markerA89;
    private Marker markerA90;
    private Marker markerA91;
    private Marker markerA92;
    private Marker markerA93;
    private Marker markerA94;
    private Marker markerA95;
    private Marker markerA96;
    private Marker markerA97;
    private Marker markerA98;
    private Marker markerA99;
    private Marker markerA100;
    private Marker markerA101;
    private Marker markerA102;
    private Marker markerA103;
    private Marker markerA104;
    private Marker markerA105;
    private Marker markerA106;
    private Marker markerA107;
    private Marker markerA108;
    private Marker markerA109;
    private Marker markerA110;
    private Marker markerA111;
    private Marker markerA112;
    private Marker markerA113;
    private Marker markerA114;
    private Marker markerA115;
    private Marker markerA116;
    private Marker markerA117;
    private Marker markerA118;
    private Marker markerA119;
    private Marker markerA120;
    private Marker markerA121;
    private Marker markerA122;
    private Marker markerA123;
    private Marker markerA124;
    private Marker markerA125;
    private Marker markerA126;
    private Marker markerA127;
    private Marker markerA128;
    private Marker markerA129;
    private Marker markerA130;
    private Marker markerA131;
    private Marker markerA132;
    private Marker markerA133;
    private Marker markerA134;
    private Marker markerA135;
    private Marker markerA136;
    private Marker markerA137;
    private Marker markerA138;
    private Marker markerA139;
    private Marker markerA140;
    private Marker markerA141;
    private Marker markerA142;
    private Marker markerA143;
    private Marker markerA144;
    private Marker markerA145;
    private Marker markerA146;
    private Marker markerA147;
    private Marker markerA148;
    private Marker markerA149;
    private Marker markerA150;
    private Marker markerA151;
    private Marker markerA152;
    private Marker markerA153;
    private Marker markerA154;
    private Marker markerA155;
    private Marker markerA156;
    private Marker markerA157;
    private Marker markerA158;
    private Marker markerA159;
    private Marker markerA160;
    private Marker markerA161;
    private Marker markerA162;
    private Marker markerA163;
    private Marker markerA164;
    private Marker markerA165;
    private Marker markerA166;
    private Marker markerA167;
    private Marker markerA168;
    private Marker markerA169;
    private Marker markerA170;
    private Marker markerA171;
    private Marker markerA172;
    private Marker markerA173;
    private Marker markerA174;
    private Marker markerA175;
    private Marker markerA176;
    private Marker markerA177;
    private Marker markerA178;
    private Marker markerA179;
    private Marker markerA180;
    private Marker markerA181;
    private Marker markerA182;
    private Marker markerA183;
    private Marker markerA184;
    private Marker markerA185;
    private Marker markerA186;
    private Marker markerA187;
    private Marker markerA188;
    private Marker markerA189;
    private Marker markerA190;
    private Marker markerA191;
    private Marker markerA192;
    private Marker markerA193;
    private Marker markerA194;
    private Marker markerA195;
    private Marker markerA196;
    private Marker markerA197;
    private Marker markerA198;
    private Marker markerA199;
    private Marker markerA200;
    private Marker markerA201;
    private Marker markerA202;
    private Marker markerA203;
    private Marker markerA204;
    private Marker markerA205;
    private Marker markerA206;
    private Marker markerA207;
    private Marker markerA208;
    private Marker markerA209;
    private Marker markerA210;
    private Marker markerA211;
    private Marker markerA212;
    private Marker markerA213;
    private Marker markerA214;
    private Marker markerA215;
    private Marker markerA216;
    private Marker markerA217;
    private Marker markerA218;
    private Marker markerA219;
    private Marker markerA220;
    private Marker markerA221;
    private Marker markerA222;
    private Marker markerA223;
    private Marker markerA224;
    private Marker markerA225;
    private Marker markerA226;
    private Marker markerA227;
    private Marker markerA228;
    private Marker markerA229;
    private Marker markerA230;
    private Marker markerA231;
    private Marker markerA232;
    private Marker markerA233;
    private Marker markerA234;
    private Marker markerA235;
    private Marker markerA236;
    private Marker markerA237;
    private Marker markerA238;
    private Marker markerA239;
    private Marker markerA240;
    private Marker markerA241;
    private Marker markerA242;
    private Marker markerA243;
    private Marker markerA244;
    private Marker markerA245;
    private Marker markerA246;
    private Marker markerA247;
    private Marker markerA248;
    private Marker markerA249;
    private Marker markerA250;
    private Marker markerA251;
    private Marker markerA252;
    private Marker markerA253;
    private Marker markerA254;
    private Marker markerA255;
    private Marker markerA256;
    private Marker markerA257;
    private Marker markerA258;
    private Marker markerA259;
    private Marker markerA260;
    private Marker markerA261;
    private Marker markerA262;
    private Marker markerA263;
    private Marker markerA264;
    private Marker markerA265;
    private Marker markerA266;
    private Marker markerA267;
    private Marker markerA268;
    private Marker markerA269;
    private Marker markerA270;
    private Marker markerA271;
    private Marker markerA272;
    private Marker markerA273;
    private Marker markerA274;
    private Marker markerA275;
    private Marker markerA276;
    private Marker markerA277;
    private Marker markerA278;
    private Marker markerA279;
    private Marker markerA280;
    private Marker markerA281;
    private Marker markerA282;
    private Marker markerA283;
    private Marker markerA284;
    private Marker markerA285;
    private Marker markerA286;
    private Marker markerA287;
    private Marker markerA288;
    private Marker markerA289;
    private Marker markerA290;
    private Marker markerA291;
    private Marker markerA292;
    private Marker markerA293;
    private Marker markerA294;
    private Marker markerA295;
    private Marker markerA296;
    private Marker markerA297;
    private Marker markerA298;
    private Marker markerA299;
    private Marker markerA300;
    private Marker markerA301;
    private Marker markerA302;
    private Marker markerA303;
    private Marker markerA304;
    private Marker markerA305;
    private Marker markerA306;
    private Marker markerA307;
    private Marker markerA308;
    private Marker markerA309;
    private Marker markerA310;
    private Marker markerA311;
    private Marker markerA312;
    private Marker markerA313;
    private Marker markerA314;
    private Marker markerA315;
    private Marker markerA316;
    private Marker markerA317;
    private Marker markerA318;
    private Marker markerA319;
    private Marker markerA320;
    private Marker markerA321;
    private Marker markerA322;
    private Marker markerA323;
    private Marker markerA324;
    private Marker markerA325;
    private Marker markerA326;
    private Marker markerA327;
    private Marker markerA328;
    private Marker markerA329;
    private Marker markerA330;
    private Marker markerA331;
    private Marker markerA332;
    private Marker markerA333;
    private Marker markerA334;
    private Marker markerA335;
    private Marker markerA336;
    private Marker markerA337;
    private Marker markerA338;
    private Marker markerA339;
    private Marker markerA340;
    private Marker markerA341;
    private Marker markerA342;
    private Marker markerA343;
    private Marker markerA344;
    private Marker markerA345;
    private Marker markerA346;
    private Marker markerA347;
    private Marker markerA348;
    private Marker markerA349;
    private Marker markerA350;
    private Marker markerA351;
    private Marker markerA352;
    private Marker markerA353;
    private Marker markerA354;
    private Marker markerA355;
    private Marker markerA356;
    private Marker markerA357;
    private Marker markerA358;
    private Marker markerA359;
    private Marker markerA360;
    private Marker markerA361;
    private Marker markerA362;
    private Marker markerA363;
    private Marker markerA364;
    private Marker markerA365;
    private Marker markerA366;
    private Marker markerA367;
    private Marker markerA368;
    private Marker markerA369;
    private Marker markerA370;
    private Marker markerA371;
    private Marker markerA372;
    private Marker markerA373;
    private Marker markerA374;
    private Marker markerA375;
    private Marker markerA376;
    private Marker markerA377;
    private Marker markerA378;
    private Marker markerA379;
    private Marker markerA380;
    private Marker markerA381;
    private Marker markerA382;
    private Marker markerA383;
    private Marker markerA384;
    private Marker markerA385;
    private Marker markerA386;
    private Marker markerA387;
    private Marker markerA388;
    private Marker markerA389;
    private Marker markerA390;
    private Marker markerA391;
    private Marker markerA392;
    private Marker markerA393;
    private Marker markerA394;
    private Marker markerA395;
    private Marker markerA396;
    private Marker markerA397;
    private Marker markerA398;
    private Marker markerA399;
    private Marker markerA400;
    private Marker markerA401;
    private Marker markerA402;
    private Marker markerA403;
    private Marker markerA404;
    private Marker markerA405;
    private Marker markerA406;
    private Marker markerA407;
    private Marker markerA408;
    private Marker markerA409;
    private Marker markerA410;
    private Marker markerA411;
    private Marker markerA412;
    private Marker markerA413;
    private Marker markerA414;
    private Marker markerA415;
    private Marker markerA416;
    private Marker markerA417;
    private Marker markerA418;
    private Marker markerA419;
    private Marker markerA420;
    private Marker markerA421;
    private Marker markerA422;
    private Marker markerA423;
    private Marker markerA424;
    private Marker markerA425;
    private Marker markerA426;
    private Marker markerA427;
    private Marker markerA428;
    private Marker markerA429;
    private Marker markerA430;
    private Marker markerA431;
    private Marker markerA432;
    private Marker markerA433;
    private Marker markerA434;
    private Marker markerA435;
    private Marker markerA436;
    private Marker markerA437;
    private Marker markerA438;
    private Marker markerA439;
    private Marker markerA440;
    private Marker markerA441;
    private Marker markerA442;
    private Marker markerA443;
    private Marker markerA444;
    private Marker markerA445;
    private Marker markerA446;
    private Marker markerA447;
    private Marker markerA448;
    private Marker markerA449;
    private Marker markerA450;
    private Marker markerA451;
    private Marker markerA452;
    private Marker markerA453;
    private Marker markerA454;
    private Marker markerA455;
    private Marker markerA456;
    private Marker markerA457;
    private Marker markerA458;
    private Marker markerA459;
    private Marker markerA460;
    private Marker markerA461;
    private Marker markerA462;
    private Marker markerA463;
    private Marker markerA464;
    private Marker markerA465;
    private Marker markerA466;
    private Marker markerA467;
    private Marker markerA468;
    private Marker markerA469;
    private Marker markerA470;
    private Marker markerA471;
    private Marker markerA472;
    private Marker markerA473;
    private Marker markerA474;
    private Marker markerA475;
    private Marker markerA476;
    private Marker markerA477;
    private Marker markerA478;
    private Marker markerA479;
    private Marker markerA480;
    private Marker markerA481;
    private Marker markerA482;
    private Marker markerA483;
    private Marker markerA484;
    private Marker markerA485;
    private Marker markerA486;
    private Marker markerA487;
    private Marker markerA488;
    private Marker markerA489;
    private Marker markerA490;
    private Marker markerA491;
    private Marker markerA492;
    private Marker markerA493;
    private Marker markerA494;
    private Marker markerA495;
    private Marker markerA496;
    private Marker markerA497;
    private Marker markerA498;
    private Marker markerA499;
    private Marker markerA500;
    private Marker markerA501;
    private Marker markerA502;
    private Marker markerA503;
    private Marker markerA504;
    private Marker markerA505;
    private Marker markerA506;
    private Marker markerA507;
    private Marker markerA508;
    private Marker markerA509;
    private Marker markerA510;
    private Marker markerA511;
    private Marker markerA512;
    private Marker markerA513;
    private Marker markerA514;
    private Marker markerA515;
    private Marker markerA516;
    private Marker markerA517;
    private Marker markerA518;
    private Marker markerA519;
    private Marker markerA520;
    private Marker markerA521;
    private Marker markerA522;
    private Marker markerA523;
    private Marker markerA524;
    private Marker markerA525;
    private Marker markerA526;
    private Marker markerA527;
    private Marker markerA528;
    private Marker markerA529;
    private Marker markerA530;
    private Marker markerA531;
    private Marker markerA532;
    private Marker markerA533;
    private Marker markerA534;
    private Marker markerA535;
    private Marker markerA536;
    private Marker markerA537;
    private Marker markerA538;
    private Marker markerA539;
    private Marker markerA540;
    private Marker markerA541;
    private Marker markerA542;
    private Marker markerA543;
    private Marker markerA544;
    private Marker markerA545;
    private Marker markerA546;
    private Marker markerA547;
    private Marker markerA548;
    private Marker markerA549;
    private Marker markerA550;
    private Marker markerA551;
    private Marker markerA552;
    private Marker markerA553;
    private Marker markerA554;
    private Marker markerA555;
    private Marker markerA556;
    private Marker markerA557;
    private Marker markerA558;
    private Marker markerA559;
    private Marker markerA560;
    private Marker markerA561;
    private Marker markerA562;
    private Marker markerA563;
    private Marker markerA564;
    private Marker markerA565;
    private Marker markerA566;
    private Marker markerA567;
    private Marker markerA568;
    private Marker markerA569;
    private Marker markerA570;
    private Marker markerA571;
    private Marker markerA572;
    private Marker markerA573;
    private Marker markerA574;
    private Marker markerA575;
    private Marker markerA576;
    private Marker markerA577;
    private Marker markerA578;
    private Marker markerA579;
    private Marker markerA580;
    private Marker markerA581;
    private Marker markerA582;
    private Marker markerA583;
    private Marker markerA584;
    private Marker markerA585;
    private Marker markerA586;
    private Marker markerA587;
    private Marker markerA588;
    private Marker markerA589;
    private Marker markerA590;
    private Marker markerA591;
    private Marker markerA592;
    private Marker markerA593;
    private Marker markerA594;
    private Marker markerA595;
    private Marker markerA596;
    private Marker markerA597;
    private Marker markerA598;
    private Marker markerA599;
    private Marker markerA600;
    private Marker markerA601;
    private Marker markerA602;
    private Marker markerA603;
    private Marker markerA604;
    private Marker markerA605;
    private Marker markerA606;
    private Marker markerA607;
    private Marker markerA608;
    private Marker markerA609;
    private Marker markerA610;
    private Marker markerA611;
    private Marker markerA612;
    private Marker markerA613;
    private Marker markerA614;
    private Marker markerA615;
    private Marker markerA616;
    private Marker markerA617;
    private Marker markerA618;
    private Marker markerA619;
    private Marker markerA620;
    private Marker markerA621;
    private Marker markerA622;
    private Marker markerA623;
    private Marker markerA624;
    private Marker markerA625;
    private Marker markerA626;
    private Marker markerA627;
    private Marker markerA628;
    private Marker markerA629;
    private Marker markerA630;
    private Marker markerA631;
    private Marker markerA632;
    private Marker markerA633;
    private Marker markerA634;
    private Marker markerA635;
    private Marker markerA636;
    private Marker markerA637;
    private Marker markerA638;
    private Marker markerA639;
    private Marker markerA640;
    private Marker markerA641;
    private Marker markerA642;
    private Marker markerA643;
    private Marker markerA644;
    private Marker markerA645;
    private Marker markerA646;
    private Marker markerA647;
    private Marker markerA648;
    private Marker markerA649;
    private Marker markerA650;
    private Marker markerA651;
    private Marker markerA652;
    private Marker markerA653;
    private Marker markerA654;
    private Marker markerA655;
    private Marker markerA656;
    private Marker markerA657;
    private Marker markerA658;
    private Marker markerA659;
    private Marker markerA660;
    private Marker markerA661;
    private Marker markerA662;
    private Marker markerA663;
    private Marker markerA664;
    private Marker markerA665;
    private Marker markerA666;
    private Marker markerA667;
    private Marker markerA668;
    private Marker markerA669;
    private Marker markerA670;
    private Marker markerA671;
    private Marker markerA672;
    private Marker markerA673;
    private Marker markerA674;
    private Marker markerA675;
    private Marker markerA676;
    private Marker markerA677;
    private Marker markerA678;
    private Marker markerA679;
    private Marker markerA680;
    private Marker markerA681;
    private Marker markerA682;
    private Marker markerA683;
    private Marker markerA684;
    private Marker markerA685;
    private Marker markerA686;
    private Marker markerA687;
    private Marker markerA688;
    private Marker markerA689;
    private Marker markerA690;
    private Marker markerA691;
    private Marker markerA692;
    private Marker markerA693;
    private Marker markerA694;
    private Marker markerA695;
    private Marker markerA696;
    private Marker markerA697;
    private Marker markerA698;
    private Marker markerA699;
    private Marker markerA700;
    private Marker markerA701;
    private Marker markerA702;
    private Marker markerA703;
    private Marker markerA704;
    private Marker markerA705;
    private Marker markerA706;
    private Marker markerA707;
    private Marker markerA708;
    private Marker markerA709;
    private Marker markerA710;
    private Marker markerA711;
    private Marker markerA712;
    private Marker markerA713;
    private Marker markerA714;
    private Marker markerA715;
    private Marker markerA716;
    private Marker markerA717;
    private Marker markerA718;
    private Marker markerA719;
    private Marker markerA720;
    private Marker markerA721;
    private Marker markerA722;
    private Marker markerA723;
    private Marker markerA724;
    private Marker markerA725;
    private Marker markerA726;
    private Marker markerA727;
    private Marker markerA728;
    private Marker markerA729;
    private Marker markerA730;
    private Marker markerA731;
    private Marker markerA732;
    private Marker markerA733;
    private Marker markerA734;
    private Marker markerA735;
    private Marker markerA736;
    private Marker markerA737;
    private Marker markerA738;
    private Marker markerA739;
    private Marker markerA740;
    private Marker markerA741;
    private Marker markerA742;
    private Marker markerA743;
    private Marker markerA744;
    private Marker markerA745;
    private Marker markerA746;
    private Marker markerA747;
    private Marker markerA748;
    private Marker markerA749;
    private Marker markerA750;
    private Marker markerA751;
    private Marker markerA752;
    private Marker markerA753;
    private Marker markerA754;
    private Marker markerA755;
    private Marker markerA756;
    private Marker markerA757;
    private Marker markerA758;
    private Marker markerA759;
    private Marker markerA760;
    private Marker markerA761;
    private Marker markerA762;
    private Marker markerA763;
    private Marker markerA764;
    private Marker markerA765;
    private Marker markerA766;
    private Marker markerA767;
    private Marker markerA768;
    private Marker markerA769;
    private Marker markerA770;
    private Marker markerA771;
    private Marker markerA772;
    private Marker markerA773;
    private Marker markerA774;
    private Marker markerA775;
    private Marker markerA776;
    private Marker markerA777;
    private Marker markerA778;
    private Marker markerA779;
    private Marker markerA780;
    private Marker markerA781;
    private Marker markerA782;
    private Marker markerA783;
    private Marker markerA784;
    private Marker markerA785;
    private Marker markerA786;
    private Marker markerA787;
    private Marker markerA788;
    private Marker markerA789;
    private Marker markerA790;
    private Marker markerA791;
    private Marker markerA792;
    private Marker markerA793;
    private Marker markerA794;
    private Marker markerA795;
    private Marker markerA796;
    private Marker markerA797;
    private Marker markerA798;
    private Marker markerA799;
    private Marker markerA800;
    private Marker markerA801;
    private Marker markerA802;
    private Marker markerA803;
    private Marker markerA804;
    private Marker markerA805;
    private Marker markerA806;
    private Marker markerA807;
    private Marker markerA808;
    private Marker markerA809;
    private Marker markerA810;
    private Marker markerA811;
    private Marker markerA812;
    private Marker markerA813;
    private Marker markerA814;
    private Marker markerA815;
    private Marker markerA816;
    private Marker markerA817;
    private Marker markerA818;
    private Marker markerA819;
    private Marker markerA820;
    private Marker markerA821;
    private Marker markerA822;
    private Marker markerA823;
    private Marker markerA824;
    private Marker markerA825;
    private Marker markerA826;
    private Marker markerA827;
    private Marker markerA828;
    private Marker markerA829;
    private Marker markerA830;
    private Marker markerA831;
    private Marker markerA832;
    private Marker markerA833;
    private Marker markerA834;
    private Marker markerA835;
    private Marker markerA836;
    private Marker markerA837;
    private Marker markerA838;
    private Marker markerA839;
    private Marker markerA840;
    private Marker markerA841;
    private Marker markerA842;
    private Marker markerA843;
    private Marker markerA844;
    private Marker markerA845;
    private Marker markerA846;
    private Marker markerA847;
    private Marker markerA848;
    private Marker markerA849;
    private Marker markerA850;
    private Marker markerA851;
    private Marker markerA852;
    private Marker markerA853;
    private Marker markerA854;
    private Marker markerA855;
    private Marker markerA856;
    private Marker markerA857;
    private Marker markerA858;
    private Marker markerA859;
    private Marker markerA860;
    private Marker markerA861;
    private Marker markerA862;
    private Marker markerA863;
    private Marker markerA864;
    private Marker markerA865;
    private Marker markerA866;
    private Marker markerA867;
    private Marker markerA868;
    private Marker markerA869;
    private Marker markerA870;
    private Marker markerA871;
    private Marker markerA872;
    private Marker markerA873;
    private Marker markerA874;
    private Marker markerA875;
    private Marker markerA876;
    private Marker markerA877;
    private Marker markerA878;
    private Marker markerA879;
    private Marker markerA880;
    private Marker markerA881;
    private Marker markerA882;
    private Marker markerA883;
    private Marker markerA884;
    private Marker markerA885;
    private Marker markerA886;
    private Marker markerA887;
    private Marker markerA888;
    private Marker markerA889;
    private Marker markerA890;
    private Marker markerA891;
    private Marker markerA892;
    private Marker markerA893;
    private Marker markerA894;
    private Marker markerA895;
    private Marker markerA896;
    private Marker markerA897;
    private Marker markerA898;
    private Marker markerA899;
    private Marker markerA900;
    private Marker markerA901;
    private Marker markerA902;
    private Marker markerA903;
    private Marker markerA904;
    private Marker markerA905;
    private Marker markerA906;
    private Marker markerA907;
    private Marker markerA908;
    private Marker markerA909;
    private Marker markerA910;
    private Marker markerA911;
    private Marker markerA912;
    private Marker markerA913;
    private Marker markerA914;
    private Marker markerA915;
    private Marker markerA916;
    private Marker markerA917;
    private Marker markerA918;
    private Marker markerA919;
    private Marker markerA920;
    private Marker markerA921;
    private Marker markerA922;
    private Marker markerA923;
    private Marker markerA924;
    private Marker markerA925;
    private Marker markerA926;
    private Marker markerA927;
    private Marker markerA928;
    private Marker markerA929;
    private Marker markerA930;
    private Marker markerA931;
    private Marker markerA932;
    private Marker markerA933;
    private Marker markerA934;
    private Marker markerA935;
    private Marker markerA936;
    private Marker markerA937;
    private Marker markerA938;
    private Marker markerA939;
    private Marker markerA940;
    private Marker markerA941;
    private Marker markerA942;
    private Marker markerA943;
    private Marker markerA944;
    private Marker markerA945;
    private Marker markerA946;
    private Marker markerA947;
    private Marker markerA948;
    private Marker markerA949;
    private Marker markerA950;
    private Marker markerA951;
    private Marker markerA952;
    private Marker markerA953;
    private Marker markerA954;
    private Marker markerA955;
    private Marker markerA956;
    private Marker markerA957;
    private Marker markerA958;
    private Marker markerA959;
    private Marker markerA960;
    private Marker markerA961;
    private Marker markerA962;
    private Marker markerA963;
    private Marker markerA964;
    private Marker markerA965;
    private Marker markerA966;
    private Marker markerA967;
    private Marker markerA968;
    private Marker markerA969;
    private Marker markerA970;
    private Marker markerA971;
    private Marker markerA972;
    private Marker markerA973;
    private Marker markerA974;
    private Marker markerA975;
    private Marker markerA976;
    private Marker markerA977;
    private Marker markerA978;
    private Marker markerA979;
    private Marker markerA980;
    private Marker markerA981;
    private Marker markerA982;
    private Marker markerA983;
    private Marker markerA984;
    private Marker markerA985;
    private Marker markerA986;
    private Marker markerA987;
    private Marker markerA988;
    private Marker markerA989;
    private Marker markerA990;
    private Marker markerA991;
    private Marker markerA992;
    private Marker markerA993;
    private Marker markerA994;
    private Marker markerA995;
    private Marker markerA996;
    private Marker markerA997;
    private Marker markerA998;
    private Marker markerA999;
    private Marker markerA1000;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map2);

        fragment1 = new Fragment1();

       // getSupportFragmentManager().beginTransaction().replace(R.id.nav_view, fragment1).commit();
        BottomNavigationView bottomNavigation = findViewById(R.id.nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.antt:
                                Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_view, fragment1).commit();

                                Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                                startActivityForResult(intent, REQUEST_CODE_MENU1);
                                finish();
                                return  true;

                            case R.id.aedd:
                                Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_view, fragment1).commit();
                                Intent intent2 = new Intent(getApplicationContext(), AedActivity.class);
                                startActivityForResult(intent2, REQUEST_CODE_MENU1);
                                finish();
                                return  true;

                            case R.id.toii:
                                Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_view, fragment1).commit();

                                Intent intent3 = new Intent(getApplicationContext(), MapsActivity4.class);
                                startActivityForResult(intent3, REQUEST_CODE_MENU1);
                                finish();
                                return  true;

                            case R.id.hoss:
                                Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_view, fragment1).commit();

                                Intent intent4 = new Intent(getApplicationContext(), HosActivity.class);
                                startActivityForResult(intent4, REQUEST_CODE_MENU1);
                                finish();
                                return  true;
                        }

                        return false;
                    }
                }
        );
        mLayout = findViewById(R.id.layout_main2);

        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                //setPriority 메서드를 통해 요청의 우선순위를 - 정확한 위치로 설정
                //balanced_power_accuracy, high_accuracy, low_power, no_power 가 있음
                .setInterval(UPDATE_INTERVAL_MS)
                //interval_ms : 10초간격
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);
        //fastest : 0.5초 간격

        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);

        // Construct FusedLocationProviderClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // map build
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");
        mMap = googleMap;


        setDefaultLocation();  //디폴트위치로이동

        // 1. 위치 퍼미션을 가지고 있는지 체크
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // 3. 위치 업데이트 시작
            startLocationUpdates();

        } else {
            // 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 퍼미션 요청
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // 3-3. 사용자에 퍼미션 요청
                        // 요청 결과는 onRequestPermissionResult에서 수신
                        ActivityCompat.requestPermissions(MapsActivity2.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청
                // 요청 결과는 onRequestPermissionResult에서 수신
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }


        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "onMapClick :");
            }
        });


        markerATOM = googleMap.addMarker(new MarkerOptions()
                .position(ATOM)
                .title("원자력병원")
                .snippet("병원병원"));


        markerA1 = googleMap.addMarker(new MarkerOptions()
                .position(A1)
                .title("A1")
                .snippet("37.650402, 126.966599"));
        markerA2 = googleMap.addMarker(new MarkerOptions()
                .position(A2)
                .title("A2")
                .snippet("37.655057, 127.010382"));
        markerA3 = googleMap.addMarker(new MarkerOptions()
                .position(A3)
                .title("A3")
                .snippet("37.641684, 127.001698"));
        markerA4 = googleMap.addMarker(new MarkerOptions()
                .position(A4)
                .title("A4")
                .snippet("37.665589, 126.998533"));
        markerA5 = googleMap.addMarker(new MarkerOptions()
                .position(A5)
                .title("A5")
                .snippet("37.677539, 127.032314"));
        markerA6 = googleMap.addMarker(new MarkerOptions()
                .position(A6)
                .title("A6")
                .snippet("37.665589, 126.998533"));
        markerA7 = googleMap.addMarker(new MarkerOptions()
                .position(A7)
                .title("A7")
                .snippet("37.687965, 127.030262"));
        markerA8 = googleMap.addMarker(new MarkerOptions()
                .position(A8)
                .title("A8")
                .snippet("37.640704, 127.000794"));
        markerA9 = googleMap.addMarker(new MarkerOptions()
                .position(A9)
                .title("A9")
                .snippet("37.637031, 126.997239"));
        markerA10 = googleMap.addMarker(new MarkerOptions()
                .position(A10)
                .title("A10")
                .snippet("37.646852, 126.962603"));
        markerA11 = googleMap.addMarker(new MarkerOptions()
                .position(A11)
                .title("A11")
                .snippet("37.671377, 126.959393"));
        markerA12 = googleMap.addMarker(new MarkerOptions()
                .position(A12)
                .title("A12")
                .snippet("37.689313, 126.963054"));
        markerA13 = googleMap.addMarker(new MarkerOptions()
                .position(A13)
                .title("A13")
                .snippet("37.687965, 127.030262"));
        markerA14 = googleMap.addMarker(new MarkerOptions()
                .position(A14)
                .title("A14")
                .snippet("37.640877, 126.954233"));
        markerA15 = googleMap.addMarker(new MarkerOptions()
                .position(A15)
                .title("A15")
                .snippet("37.704919, 127.02106"));
        markerA16 = googleMap.addMarker(new MarkerOptions()
                .position(A16)
                .title("A16")
                .snippet("37.684764, 127.033184"));
        markerA17 = googleMap.addMarker(new MarkerOptions()
                .position(A17)
                .title("A17")
                .snippet("37.644727, 126.940423"));
        markerA18 = googleMap.addMarker(new MarkerOptions()
                .position(A18)
                .title("A18")
                .snippet("37.712386, 126.984577"));
        markerA19 = googleMap.addMarker(new MarkerOptions()
                .position(A19)
                .title("A19")
                .snippet("37.711278, 126.987757"));
        markerA20 = googleMap.addMarker(new MarkerOptions()
                .position(A20)
                .title("A20")
                .snippet("37.678085, 127.002628"));
        markerA21 = googleMap.addMarker(new MarkerOptions()
                .position(A21)
                .title("A21")
                .snippet("37.655675, 126.948563"));
        markerA22 = googleMap.addMarker(new MarkerOptions()
                .position(A22)
                .title("A22")
                .snippet("37.710812, 126.99543"));
        markerA23 = googleMap.addMarker(new MarkerOptions()
                .position(A23)
                .title("A23")
                .snippet("37.707924, 126.980192"));
        markerA24 = googleMap.addMarker(new MarkerOptions()
                .position(A24)
                .title("A24")
                .snippet("37.658134, 127.002419"));
        markerA25 = googleMap.addMarker(new MarkerOptions()
                .position(A25)
                .title("A25")
                .snippet("37.61783, 126.943296"));
        markerA26 = googleMap.addMarker(new MarkerOptions()
                .position(A26)
                .title("A26")
                .snippet("37.658134, 127.002419"));
        markerA27 = googleMap.addMarker(new MarkerOptions()
                .position(A27)
                .title("A27")
                .snippet("37.706051, 127.00671"));
        markerA28 = googleMap.addMarker(new MarkerOptions()
                .position(A28)
                .title("A28")
                .snippet("37.693758, 127.022465"));
        markerA29 = googleMap.addMarker(new MarkerOptions()
                .position(A29)
                .title("A29")
                .snippet("37.721565, 127.003259"));
        markerA30 = googleMap.addMarker(new MarkerOptions()
                .position(A30)
                .title("A30")
                .snippet("37.68739, 127.016222"));
        markerA31 = googleMap.addMarker(new MarkerOptions()
                .position(A31)
                .title("A31")
                .snippet("37.720567, 127.003522"));
        markerA32 = googleMap.addMarker(new MarkerOptions()
                .position(A32)
                .title("A32")
                .snippet("37.720567, 127.003522"));
        markerA33 = googleMap.addMarker(new MarkerOptions()
                .position(A33)
                .title("A33")
                .snippet("37.676457, 127.02785"));
        markerA34 = googleMap.addMarker(new MarkerOptions()
                .position(A34)
                .title("A34")
                .snippet("37.68739, 127.016222"));
        markerA35 = googleMap.addMarker(new MarkerOptions()
                .position(A35)
                .title("A35")
                .snippet("37.636264, 126.984378"));
        markerA36 = googleMap.addMarker(new MarkerOptions()
                .position(A36)
                .title("A36")
                .snippet("37.635827, 126.976363"));
        markerA37 = googleMap.addMarker(new MarkerOptions()
                .position(A37)
                .title("A37")
                .snippet("37.671936, 126.957753"));
        markerA38 = googleMap.addMarker(new MarkerOptions()
                .position(A38)
                .title("A38")
                .snippet("37.623912, 126.990596"));
        markerA39 = googleMap.addMarker(new MarkerOptions()
                .position(A39)
                .title("A39")
                .snippet("37.65587, 126.948355"));
        markerA40 = googleMap.addMarker(new MarkerOptions()
                .position(A40)
                .title("A40")
                .snippet("37.65587, 126.948355"));
        markerA41 = googleMap.addMarker(new MarkerOptions()
                .position(A41)
                .title("A41")
                .snippet("37.675413, 126.959326"));
        markerA42 = googleMap.addMarker(new MarkerOptions()
                .position(A42)
                .title("A42")
                .snippet("37.707924, 126.980192"));
        markerA43 = googleMap.addMarker(new MarkerOptions()
                .position(A43)
                .title("A43")
                .snippet("37.675882, 127.026849"));
        markerA44 = googleMap.addMarker(new MarkerOptions()
                .position(A44)
                .title("A44")
                .snippet("37.720891, 127.030525"));
        markerA45 = googleMap.addMarker(new MarkerOptions()
                .position(A45)
                .title("A45")
                .snippet("37.674718, 127.019042"));
        markerA46 = googleMap.addMarker(new MarkerOptions()
                .position(A46)
                .title("A46")
                .snippet("37.720891, 127.030525"));
        markerA47 = googleMap.addMarker(new MarkerOptions()
                .position(A47)
                .title("A47")
                .snippet("37.720891, 127.030525"));
        markerA48 = googleMap.addMarker(new MarkerOptions()
                .position(A48)
                .title("A48")
                .snippet("37.720891, 127.030525"));
        markerA49 = googleMap.addMarker(new MarkerOptions()
                .position(A49)
                .title("A49")
                .snippet("37.720891, 127.030525"));
        markerA50 = googleMap.addMarker(new MarkerOptions()
                .position(A50)
                .title("A50")
                .snippet("37.720891, 127.030525"));
        markerA51 = googleMap.addMarker(new MarkerOptions()
                .position(A51)
                .title("A51")
                .snippet("37.720891, 127.030525"));
        markerA52 = googleMap.addMarker(new MarkerOptions()
                .position(A52)
                .title("A52")
                .snippet("37.720891, 127.030525"));
        markerA53 = googleMap.addMarker(new MarkerOptions()
                .position(A53)
                .title("A53")
                .snippet("37.685217, 127.030287"));
        markerA54 = googleMap.addMarker(new MarkerOptions()
                .position(A54)
                .title("A54")
                .snippet("37.62678, 126.960842"));
        markerA55 = googleMap.addMarker(new MarkerOptions()
                .position(A55)
                .title("A55")
                .snippet("37.698219, 126.979021"));
        markerA56 = googleMap.addMarker(new MarkerOptions()
                .position(A56)
                .title("A56")
                .snippet("37.698219, 126.979021"));
        markerA57 = googleMap.addMarker(new MarkerOptions()
                .position(A57)
                .title("A57")
                .snippet("37.637735, 126.943445"));
        markerA58 = googleMap.addMarker(new MarkerOptions()
                .position(A58)
                .title("A58")
                .snippet("37.676457, 127.02785"));
        markerA59 = googleMap.addMarker(new MarkerOptions()
                .position(A59)
                .title("A59")
                .snippet("37.675474, 127.02334"));
        markerA60 = googleMap.addMarker(new MarkerOptions()
                .position(A60)
                .title("A60")
                .snippet("37.64997, 126.966729"));
        markerA61 = googleMap.addMarker(new MarkerOptions()
                .position(A61)
                .title("A61")
                .snippet("37.649553, 126.972639"));
        markerA62 = googleMap.addMarker(new MarkerOptions()
                .position(A62)
                .title("A62")
                .snippet("37.635494, 126.97647"));
        markerA63 = googleMap.addMarker(new MarkerOptions()
                .position(A63)
                .title("A63")
                .snippet("37.645166, 126.974807"));
        markerA64 = googleMap.addMarker(new MarkerOptions()
                .position(A64)
                .title("A64")
                .snippet("37.645676, 126.970211"));
        markerA65 = googleMap.addMarker(new MarkerOptions()
                .position(A65)
                .title("A65")
                .snippet("37.645676, 126.970211"));
        markerA66 = googleMap.addMarker(new MarkerOptions()
                .position(A66)
                .title("A66")
                .snippet("37.650402, 126.966599"));
        markerA67 = googleMap.addMarker(new MarkerOptions()
                .position(A67)
                .title("A67")
                .snippet("37.64553, 126.978304"));
        markerA68 = googleMap.addMarker(new MarkerOptions()
                .position(A68)
                .title("A68")
                .snippet("37.665484, 126.971683"));
        markerA69 = googleMap.addMarker(new MarkerOptions()
                .position(A69)
                .title("A69")
                .snippet("37.645676, 126.970211"));
        markerA70 = googleMap.addMarker(new MarkerOptions()
                .position(A70)
                .title("A70")
                .snippet("37.645374, 127.00004"));
        markerA71 = googleMap.addMarker(new MarkerOptions()
                .position(A71)
                .title("A71")
                .snippet("37.636245, 127.001986"));
        markerA72 = googleMap.addMarker(new MarkerOptions()
                .position(A72)
                .title("A72")
                .snippet("37.670344, 127.003593"));
        markerA73 = googleMap.addMarker(new MarkerOptions()
                .position(A73)
                .title("A73")
                .snippet("37.676348, 126.970309"));
        markerA74 = googleMap.addMarker(new MarkerOptions()
                .position(A74)
                .title("A74")
                .snippet("37.67556, 126.977133"));
        markerA75 = googleMap.addMarker(new MarkerOptions()
                .position(A75)
                .title("A75")
                .snippet("37.645103, 126.940145"));
        markerA76 = googleMap.addMarker(new MarkerOptions()
                .position(A76)
                .title("A76")
                .snippet("37.662458, 126.992761"));
        markerA77 = googleMap.addMarker(new MarkerOptions()
                .position(A77)
                .title("A77")
                .snippet("37.662458, 126.992761"));
        markerA78 = googleMap.addMarker(new MarkerOptions()
                .position(A78)
                .title("A78")
                .snippet("37.667922, 127.006935"));
        markerA79 = googleMap.addMarker(new MarkerOptions()
                .position(A79)
                .title("A79")
                .snippet("37.662458, 126.992761"));
        markerA80 = googleMap.addMarker(new MarkerOptions()
                .position(A80)
                .title("A80")
                .snippet("37.694209, 127.019341"));
        markerA81 = googleMap.addMarker(new MarkerOptions()
                .position(A81)
                .title("A81")
                .snippet("37.720891, 127.030525"));
        markerA82 = googleMap.addMarker(new MarkerOptions()
                .position(A82)
                .title("A82")
                .snippet("37.656514, 126.969064"));
        markerA83 = googleMap.addMarker(new MarkerOptions()
                .position(A83)
                .title("A83")
                .snippet("37.623912, 126.990596"));
        markerA84 = googleMap.addMarker(new MarkerOptions()
                .position(A84)
                .title("A84")
                .snippet("37.677539, 127.032314"));
        markerA85 = googleMap.addMarker(new MarkerOptions()
                .position(A85)
                .title("A85")
                .snippet("37.688619, 127.027589"));
        markerA86 = googleMap.addMarker(new MarkerOptions()
                .position(A86)
                .title("A86")
                .snippet("37.694264, 127.020452"));
        markerA87 = googleMap.addMarker(new MarkerOptions()
                .position(A87)
                .title("A87")
                .snippet("37.674718, 127.019042"));
        markerA88 = googleMap.addMarker(new MarkerOptions()
                .position(A88)
                .title("A88")
                .snippet("37.674718, 127.019042"));
        markerA89 = googleMap.addMarker(new MarkerOptions()
                .position(A89)
                .title("A89")
                .snippet("37.67964, 127.012901"));
        markerA90 = googleMap.addMarker(new MarkerOptions()
                .position(A90)
                .title("A90")
                .snippet("37.682359, 127.016912"));
        markerA91 = googleMap.addMarker(new MarkerOptions()
                .position(A91)
                .title("A91")
                .snippet("37.682359, 127.016912"));
        markerA92 = googleMap.addMarker(new MarkerOptions()
                .position(A92)
                .title("A92")
                .snippet("37.682359, 127.016912"));
        markerA93 = googleMap.addMarker(new MarkerOptions()
                .position(A93)
                .title("A93")
                .snippet("37.682578, 127.010844"));
        markerA94 = googleMap.addMarker(new MarkerOptions()
                .position(A94)
                .title("A94")
                .snippet("37.682973, 127.010669"));
        markerA95 = googleMap.addMarker(new MarkerOptions()
                .position(A95)
                .title("A95")
                .snippet("37.694091, 127.019252"));
        markerA96 = googleMap.addMarker(new MarkerOptions()
                .position(A96)
                .title("A96")
                .snippet("37.647352, 126.941802"));
        markerA97 = googleMap.addMarker(new MarkerOptions()
                .position(A97)
                .title("A97")
                .snippet("37.62678, 126.960842"));
        markerA98 = googleMap.addMarker(new MarkerOptions()
                .position(A98)
                .title("A98")
                .snippet("37.629065, 127.001085"));
        markerA99 = googleMap.addMarker(new MarkerOptions()
                .position(A99)
                .title("A99")
                .snippet("37.64074, 127.000793"));
        markerA100 = googleMap.addMarker(new MarkerOptions()
                .position(A100)
                .title("A100")
                .snippet("37.664987, 127.02885"));
        markerA101 = googleMap.addMarker(new MarkerOptions()
                .position(A101)
                .title("A101")
                .snippet("37.623032, 126.974217"));
        markerA102 = googleMap.addMarker(new MarkerOptions()
                .position(A102)
                .title("A102")
                .snippet("37.635946, 127.002852"));
        markerA103 = googleMap.addMarker(new MarkerOptions()
                .position(A103)
                .title("A103")
                .snippet("37.656514, 126.969064"));
        markerA104 = googleMap.addMarker(new MarkerOptions()
                .position(A104)
                .title("A104")
                .snippet("37.677009, 127.003254"));
        markerA105 = googleMap.addMarker(new MarkerOptions()
                .position(A105)
                .title("A105")
                .snippet("37.662458, 126.992761"));
        markerA106 = googleMap.addMarker(new MarkerOptions()
                .position(A106)
                .title("A106")
                .snippet("37.624608, 127.010027"));
        markerA107 = googleMap.addMarker(new MarkerOptions()
                .position(A107)
                .title("A107")
                .snippet("37.629065, 127.001085"));
        markerA108 = googleMap.addMarker(new MarkerOptions()
                .position(A108)
                .title("A108")
                .snippet("37.656629, 127.000263"));
        markerA109 = googleMap.addMarker(new MarkerOptions()
                .position(A109)
                .title("A109")
                .snippet("37.625773, 126.962125"));
        markerA110 = googleMap.addMarker(new MarkerOptions()
                .position(A110)
                .title("A110")
                .snippet("37.675413, 126.959326"));
        markerA111 = googleMap.addMarker(new MarkerOptions()
                .position(A111)
                .title("A111")
                .snippet("37.676348, 126.970309"));
        markerA112 = googleMap.addMarker(new MarkerOptions()
                .position(A112)
                .title("A112")
                .snippet("37.704601, 127.024069"));
        markerA113 = googleMap.addMarker(new MarkerOptions()
                .position(A113)
                .title("A113")
                .snippet("37.726864, 127.030436"));
        markerA114 = googleMap.addMarker(new MarkerOptions()
                .position(A114)
                .title("A114")
                .snippet("37.720891, 127.030525"));
        markerA115 = googleMap.addMarker(new MarkerOptions()
                .position(A115)
                .title("A115")
                .snippet("37.720891, 127.030525"));
        markerA116 = googleMap.addMarker(new MarkerOptions()
                .position(A116)
                .title("A116")
                .snippet("37.659018, 126.96079"));
        markerA117 = googleMap.addMarker(new MarkerOptions()
                .position(A117)
                .title("A117")
                .snippet("37.645154, 126.975533"));
        markerA118 = googleMap.addMarker(new MarkerOptions()
                .position(A118)
                .title("A118")
                .snippet("37.635745, 126.976319"));
        markerA119 = googleMap.addMarker(new MarkerOptions()
                .position(A119)
                .title("A119")
                .snippet("37.711278, 126.987757"));
        markerA120 = googleMap.addMarker(new MarkerOptions()
                .position(A120)
                .title("A120")
                .snippet("37.710812, 126.99543"));
        markerA121 = googleMap.addMarker(new MarkerOptions()
                .position(A121)
                .title("A121")
                .snippet("37.61783, 126.943296"));
        markerA122 = googleMap.addMarker(new MarkerOptions()
                .position(A122)
                .title("A122")
                .snippet("37.638142, 126.946578"));
        markerA123 = googleMap.addMarker(new MarkerOptions()
                .position(A123)
                .title("A123")
                .snippet("37.645175, 126.940099"));
        markerA124 = googleMap.addMarker(new MarkerOptions()
                .position(A124)
                .title("A124")
                .snippet("37.651327, 127.006114"));
        markerA125 = googleMap.addMarker(new MarkerOptions()
                .position(A125)
                .title("A125")
                .snippet("37.643569, 126.981172"));
        markerA126 = googleMap.addMarker(new MarkerOptions()
                .position(A126)
                .title("A126")
                .snippet("37.637961, 126.974328"));
        markerA127 = googleMap.addMarker(new MarkerOptions()
                .position(A127)
                .title("A127")
                .snippet("37.640704, 127.000794"));
        markerA128 = googleMap.addMarker(new MarkerOptions()
                .position(A128)
                .title("A128")
                .snippet("37.653131, 126.960386"));
        markerA129 = googleMap.addMarker(new MarkerOptions()
                .position(A129)
                .title("A129")
                .snippet("37.654787, 127.008266"));
        markerA130 = googleMap.addMarker(new MarkerOptions()
                .position(A130)
                .title("A130")
                .snippet("37.677539, 127.032314"));
        markerA131 = googleMap.addMarker(new MarkerOptions()
                .position(A131)
                .title("A131")
                .snippet("37.684764, 127.033184"));
        markerA132 = googleMap.addMarker(new MarkerOptions()
                .position(A132)
                .title("A132")
                .snippet("37.687965, 127.030262"));
        markerA133 = googleMap.addMarker(new MarkerOptions()
                .position(A133)
                .title("A133")
                .snippet("37.677072, 127.028058"));
        markerA134 = googleMap.addMarker(new MarkerOptions()
                .position(A134)
                .title("A134")
                .snippet("37.675346, 127.022027"));
        markerA135 = googleMap.addMarker(new MarkerOptions()
                .position(A135)
                .title("A135")
                .snippet("37.655675, 126.948563"));
        markerA136 = googleMap.addMarker(new MarkerOptions()
                .position(A136)
                .title("A136")
                .snippet("37.698219, 126.979021"));
        markerA137 = googleMap.addMarker(new MarkerOptions()
                .position(A137)
                .title("A137")
                .snippet("37.698219, 126.979021"));
        markerA138 = googleMap.addMarker(new MarkerOptions()
                .position(A138)
                .title("A138")
                .snippet("37.691211, 126.98973"));
        markerA139 = googleMap.addMarker(new MarkerOptions()
                .position(A139)
                .title("A139")
                .snippet("37.691211, 126.98973"));
        markerA140 = googleMap.addMarker(new MarkerOptions()
                .position(A140)
                .title("A140")
                .snippet("37.691211, 126.98973"));
        markerA141 = googleMap.addMarker(new MarkerOptions()
                .position(A141)
                .title("A141")
                .snippet("37.691211, 126.98973"));
        markerA142 = googleMap.addMarker(new MarkerOptions()
                .position(A142)
                .title("A142")
                .snippet("37.691211, 126.98973"));
        markerA143 = googleMap.addMarker(new MarkerOptions()
                .position(A143)
                .title("A143")
                .snippet("37.662458, 126.992761"));
        markerA144 = googleMap.addMarker(new MarkerOptions()
                .position(A144)
                .title("A144")
                .snippet("37.662458, 126.992761"));
        markerA145 = googleMap.addMarker(new MarkerOptions()
                .position(A145)
                .title("A145")
                .snippet("37.612872, 126.991606"));
        markerA146 = googleMap.addMarker(new MarkerOptions()
                .position(A146)
                .title("A146")
                .snippet("37.626522, 126.960234"));
        markerA147 = googleMap.addMarker(new MarkerOptions()
                .position(A147)
                .title("A147")
                .snippet("37.675811, 127.024144"));
        markerA148 = googleMap.addMarker(new MarkerOptions()
                .position(A148)
                .title("A148")
                .snippet("37.687965, 127.030262"));
        markerA149 = googleMap.addMarker(new MarkerOptions()
                .position(A149)
                .title("A149")
                .snippet("37.614655, 126.960139"));
        markerA150 = googleMap.addMarker(new MarkerOptions()
                .position(A150)
                .title("A150")
                .snippet("37.675882, 127.026849"));
        markerA151 = googleMap.addMarker(new MarkerOptions()
                .position(A151)
                .title("A151")
                .snippet("37.636245, 127.001986"));
        markerA152 = googleMap.addMarker(new MarkerOptions()
                .position(A152)
                .title("A152")
                .snippet("37.640704, 127.000794"));
        markerA153 = googleMap.addMarker(new MarkerOptions()
                .position(A153)
                .title("A153")
                .snippet("37.62678, 126.960842"));
        markerA154 = googleMap.addMarker(new MarkerOptions()
                .position(A154)
                .title("A154")
                .snippet("37.704714, 127.021403"));
        markerA155 = googleMap.addMarker(new MarkerOptions()
                .position(A155)
                .title("A155")
                .snippet("37.6375, 126.976678"));
        markerA156 = googleMap.addMarker(new MarkerOptions()
                .position(A156)
                .title("A156")
                .snippet("37.633903, 126.975569"));
        markerA157 = googleMap.addMarker(new MarkerOptions()
                .position(A157)
                .title("A157")
                .snippet("37.68739, 127.016222"));
        markerA158 = googleMap.addMarker(new MarkerOptions()
                .position(A158)
                .title("A158")
                .snippet("37.637117, 126.997533"));
        markerA159 = googleMap.addMarker(new MarkerOptions()
                .position(A159)
                .title("A159")
                .snippet("37.704928, 127.021071"));
        markerA160 = googleMap.addMarker(new MarkerOptions()
                .position(A160)
                .title("A160")
                .snippet("37.622514, 126.989739"));
        markerA161 = googleMap.addMarker(new MarkerOptions()
                .position(A161)
                .title("A161")
                .snippet("37.639178, 126.979733"));
        markerA162 = googleMap.addMarker(new MarkerOptions()
                .position(A162)
                .title("A162")
                .snippet("37.625773, 126.962125"));
        markerA163 = googleMap.addMarker(new MarkerOptions()
                .position(A163)
                .title("A163")
                .snippet("37.676189, 127.042856"));
        markerA164 = googleMap.addMarker(new MarkerOptions()
                .position(A164)
                .title("A164")
                .snippet("37.639378, 126.969625"));
        markerA165 = googleMap.addMarker(new MarkerOptions()
                .position(A165)
                .title("A165")
                .snippet("37.645374, 127.00004"));
        markerA166 = googleMap.addMarker(new MarkerOptions()
                .position(A166)
                .title("A166")
                .snippet("37.609325, 126.950692"));
        markerA167 = googleMap.addMarker(new MarkerOptions()
                .position(A167)
                .title("A167")
                .snippet("37.609325, 126.950692"));
        markerA168 = googleMap.addMarker(new MarkerOptions()
                .position(A168)
                .title("A168")
                .snippet("37.649175, 126.981964"));
        markerA169 = googleMap.addMarker(new MarkerOptions()
                .position(A169)
                .title("A169")
                .snippet("37.662458, 126.992761"));
        markerA170 = googleMap.addMarker(new MarkerOptions()
                .position(A170)
                .title("A170")
                .snippet("37.634104, 126.950532"));
        markerA171 = googleMap.addMarker(new MarkerOptions()
                .position(A171)
                .title("A171")
                .snippet("37.64149, 126.942202"));
        markerA172 = googleMap.addMarker(new MarkerOptions()
                .position(A172)
                .title("A172")
                .snippet("37.64832, 126.972758"));
        markerA173 = googleMap.addMarker(new MarkerOptions()
                .position(A173)
                .title("A173")
                .snippet("37.650402, 126.966599"));
        markerA174 = googleMap.addMarker(new MarkerOptions()
                .position(A174)
                .title("A174")
                .snippet("37.645676, 126.970211"));
        markerA175 = googleMap.addMarker(new MarkerOptions()
                .position(A175)
                .title("A175")
                .snippet("37.653326, 126.960019"));
        markerA176 = googleMap.addMarker(new MarkerOptions()
                .position(A176)
                .title("A176")
                .snippet("37.654171, 126.957786"));
        markerA177 = googleMap.addMarker(new MarkerOptions()
                .position(A177)
                .title("A177")
                .snippet("37.618438, 126.953902"));
        markerA178 = googleMap.addMarker(new MarkerOptions()
                .position(A178)
                .title("A178")
                .snippet("37.678085, 127.002628"));
        markerA179 = googleMap.addMarker(new MarkerOptions()
                .position(A179)
                .title("A179")
                .snippet("37.623912, 126.990596"));
        markerA180 = googleMap.addMarker(new MarkerOptions()
                .position(A180)
                .title("A180")
                .snippet("37.655863, 126.948334"));
        markerA181 = googleMap.addMarker(new MarkerOptions()
                .position(A181)
                .title("A181")
                .snippet("37.662458, 126.992761"));
        markerA182 = googleMap.addMarker(new MarkerOptions()
                .position(A182)
                .title("A182")
                .snippet("37.658134, 127.002419"));
        markerA183 = googleMap.addMarker(new MarkerOptions()
                .position(A183)
                .title("A183")
                .snippet("37.678085, 127.002628"));
        markerA184 = googleMap.addMarker(new MarkerOptions()
                .position(A184)
                .title("A184")
                .snippet("37.655675, 126.948563"));
        markerA185 = googleMap.addMarker(new MarkerOptions()
                .position(A185)
                .title("A185")
                .snippet("37.710812, 126.99543"));
        markerA186 = googleMap.addMarker(new MarkerOptions()
                .position(A186)
                .title("A186")
                .snippet("37.712386, 126.984577"));
        markerA187 = googleMap.addMarker(new MarkerOptions()
                .position(A187)
                .title("A187")
                .snippet("37.711482, 126.986382"));
        markerA188 = googleMap.addMarker(new MarkerOptions()
                .position(A188)
                .title("A188")
                .snippet("37.638142, 126.946578"));
        markerA189 = googleMap.addMarker(new MarkerOptions()
                .position(A189)
                .title("A189")
                .snippet("37.636245, 127.001986"));
        markerA190 = googleMap.addMarker(new MarkerOptions()
                .position(A190)
                .title("A190")
                .snippet("37.623912, 126.990596"));
        markerA191 = googleMap.addMarker(new MarkerOptions()
                .position(A191)
                .title("A191")
                .snippet("37.645175, 126.940099"));
        markerA192 = googleMap.addMarker(new MarkerOptions()
                .position(A192)
                .title("A192")
                .snippet("37.642724, 126.946128"));
        markerA193 = googleMap.addMarker(new MarkerOptions()
                .position(A193)
                .title("A193")
                .snippet("37.642491, 126.951366"));
        markerA194 = googleMap.addMarker(new MarkerOptions()
                .position(A194)
                .title("A194")
                .snippet("37.64149, 126.942202"));
        markerA195 = googleMap.addMarker(new MarkerOptions()
                .position(A195)
                .title("A195")
                .snippet("37.640704, 127.000794"));
        markerA196 = googleMap.addMarker(new MarkerOptions()
                .position(A196)
                .title("A196")
                .snippet("37.687965, 127.030262"));
        markerA197 = googleMap.addMarker(new MarkerOptions()
                .position(A197)
                .title("A197")
                .snippet("37.684764, 127.033184"));
        markerA198 = googleMap.addMarker(new MarkerOptions()
                .position(A198)
                .title("A198")
                .snippet("37.689313, 126.963054"));
        markerA199 = googleMap.addMarker(new MarkerOptions()
                .position(A199)
                .title("A199")
                .snippet("37.635494, 126.97647"));
        markerA200 = googleMap.addMarker(new MarkerOptions()
                .position(A200)
                .title("A200")
                .snippet("37.676348, 126.970309"));
        markerA201 = googleMap.addMarker(new MarkerOptions()
                .position(A201)
                .title("A201")
                .snippet("37.637075, 126.969258"));
        markerA202 = googleMap.addMarker(new MarkerOptions()
                .position(A202)
                .title("A202")
                .snippet("37.623912, 126.990596"));
        markerA203 = googleMap.addMarker(new MarkerOptions()
                .position(A203)
                .title("A203")
                .snippet("37.677539, 127.032314"));
        markerA204 = googleMap.addMarker(new MarkerOptions()
                .position(A204)
                .title("A204")
                .snippet("37.626931, 126.960534"));
        markerA205 = googleMap.addMarker(new MarkerOptions()
                .position(A205)
                .title("A205")
                .snippet("37.637117, 126.997533"));
        markerA206 = googleMap.addMarker(new MarkerOptions()
                .position(A206)
                .title("A206")
                .snippet("37.662458, 126.992761"));
        markerA207 = googleMap.addMarker(new MarkerOptions()
                .position(A207)
                .title("A207")
                .snippet("37.662458, 126.992761"));
        markerA208 = googleMap.addMarker(new MarkerOptions()
                .position(A208)
                .title("A208")
                .snippet("37.675882, 127.026849"));
        markerA209 = googleMap.addMarker(new MarkerOptions()
                .position(A209)
                .title("A209")
                .snippet("37.720891, 127.030525"));
        markerA210 = googleMap.addMarker(new MarkerOptions()
                .position(A210)
                .title("A210")
                .snippet("37.61783, 126.943296"));
        markerA211 = googleMap.addMarker(new MarkerOptions()
                .position(A211)
                .title("A211")
                .snippet("37.634104, 126.950532"));
        markerA212 = googleMap.addMarker(new MarkerOptions()
                .position(A212)
                .title("A212")
                .snippet("37.650402, 126.966599"));
        markerA213 = googleMap.addMarker(new MarkerOptions()
                .position(A213)
                .title("A213")
                .snippet("37.653326, 126.960019"));
        markerA214 = googleMap.addMarker(new MarkerOptions()
                .position(A214)
                .title("A214")
                .snippet("37.64553, 126.978304"));
        markerA215 = googleMap.addMarker(new MarkerOptions()
                .position(A215)
                .title("A215")
                .snippet("37.649553, 126.972639"));
        markerA216 = googleMap.addMarker(new MarkerOptions()
                .position(A216)
                .title("A216")
                .snippet("37.645676, 126.970211"));
        markerA217 = googleMap.addMarker(new MarkerOptions()
                .position(A217)
                .title("A217")
                .snippet("37.646852, 126.962603"));
        markerA218 = googleMap.addMarker(new MarkerOptions()
                .position(A218)
                .title("A218")
                .snippet("37.623912, 126.990596"));
        markerA219 = googleMap.addMarker(new MarkerOptions()
                .position(A219)
                .title("A219")
                .snippet("37.710812, 126.99543"));
        markerA220 = googleMap.addMarker(new MarkerOptions()
                .position(A220)
                .title("A220")
                .snippet("37.704601, 127.024069"));
        markerA221 = googleMap.addMarker(new MarkerOptions()
                .position(A221)
                .title("A221")
                .snippet("37.671966, 126.957753"));
        markerA222 = googleMap.addMarker(new MarkerOptions()
                .position(A222)
                .title("A222")
                .snippet("37.623912, 126.990596"));
        markerA223 = googleMap.addMarker(new MarkerOptions()
                .position(A223)
                .title("A223")
                .snippet("37.694264, 127.020452"));
        markerA224 = googleMap.addMarker(new MarkerOptions()
                .position(A224)
                .title("A224")
                .snippet("37.625773, 126.962125"));
        markerA225 = googleMap.addMarker(new MarkerOptions()
                .position(A225)
                .title("A225")
                .snippet("37.68739, 127.016222"));
        markerA226 = googleMap.addMarker(new MarkerOptions()
                .position(A226)
                .title("A226")
                .snippet("37.625773, 126.962125"));
        markerA227 = googleMap.addMarker(new MarkerOptions()
                .position(A227)
                .title("A227")
                .snippet("37.625773, 126.962125"));
        markerA228 = googleMap.addMarker(new MarkerOptions()
                .position(A228)
                .title("A228")
                .snippet("37.712386, 126.984577"));
        markerA229 = googleMap.addMarker(new MarkerOptions()
                .position(A229)
                .title("A229")
                .snippet("37.720567, 127.003522"));
        markerA230 = googleMap.addMarker(new MarkerOptions()
                .position(A230)
                .title("A230")
                .snippet("37.699781, 126.983639"));
        markerA231 = googleMap.addMarker(new MarkerOptions()
                .position(A231)
                .title("A231")
                .snippet("37.623912, 126.990596"));
        markerA232 = googleMap.addMarker(new MarkerOptions()
                .position(A232)
                .title("A232")
                .snippet("37.691749, 126.976922"));
        markerA233 = googleMap.addMarker(new MarkerOptions()
                .position(A233)
                .title("A233")
                .snippet("37.691169, 126.995298"));
        markerA234 = googleMap.addMarker(new MarkerOptions()
                .position(A234)
                .title("A234")
                .snippet("37.689535, 127.025038"));
        markerA235 = googleMap.addMarker(new MarkerOptions()
                .position(A235)
                .title("A235")
                .snippet("37.691258, 127.023054"));
        markerA236 = googleMap.addMarker(new MarkerOptions()
                .position(A236)
                .title("A236")
                .snippet("37.691258, 127.023054"));
        markerA237 = googleMap.addMarker(new MarkerOptions()
                .position(A237)
                .title("A237")
                .snippet("37.694264, 127.020452"));
        markerA238 = googleMap.addMarker(new MarkerOptions()
                .position(A238)
                .title("A238")
                .snippet("37.694264, 127.020452"));
        markerA239 = googleMap.addMarker(new MarkerOptions()
                .position(A239)
                .title("A239")
                .snippet("37.691169, 126.995298"));
        markerA240 = googleMap.addMarker(new MarkerOptions()
                .position(A240)
                .title("A240")
                .snippet("37.689535, 127.025038"));
        markerA241 = googleMap.addMarker(new MarkerOptions()
                .position(A241)
                .title("A241")
                .snippet("37.691258, 127.023054"));
        markerA242 = googleMap.addMarker(new MarkerOptions()
                .position(A242)
                .title("A242")
                .snippet("37.691258, 127.023054"));
        markerA243 = googleMap.addMarker(new MarkerOptions()
                .position(A243)
                .title("A243")
                .snippet("37.694264, 127.020452"));
        markerA244 = googleMap.addMarker(new MarkerOptions()
                .position(A244)
                .title("A244")
                .snippet("37.694264, 127.020452"));
        markerA245 = googleMap.addMarker(new MarkerOptions()
                .position(A245)
                .title("A245")
                .snippet("37.691169, 126.995298"));
        markerA246 = googleMap.addMarker(new MarkerOptions()
                .position(A246)
                .title("A246")
                .snippet("37.691169, 126.995298"));
        markerA247 = googleMap.addMarker(new MarkerOptions()
                .position(A247)
                .title("A247")
                .snippet("37.625773, 126.962125"));
        markerA248 = googleMap.addMarker(new MarkerOptions()
                .position(A248)
                .title("A248")
                .snippet("37.642509, 127.002254"));
        markerA249 = googleMap.addMarker(new MarkerOptions()
                .position(A249)
                .title("A249")
                .snippet("37.721565, 127.003259"));
        markerA250 = googleMap.addMarker(new MarkerOptions()
                .position(A250)
                .title("A250")
                .snippet("37.707993, 126.994561"));
        markerA251 = googleMap.addMarker(new MarkerOptions()
                .position(A251)
                .title("A251")
                .snippet("37.678931, 127.002328"));
        markerA252 = googleMap.addMarker(new MarkerOptions()
                .position(A252)
                .title("A252")
                .snippet("37.721565, 127.003259"));
        markerA253 = googleMap.addMarker(new MarkerOptions()
                .position(A253)
                .title("A253")
                .snippet("37.707993, 126.994561"));
        markerA254 = googleMap.addMarker(new MarkerOptions()
                .position(A254)
                .title("A254")
                .snippet("37.678085, 127.002628"));
        markerA255 = googleMap.addMarker(new MarkerOptions()
                .position(A255)
                .title("A255")
                .snippet("37.6565, 126.969552"));
        markerA256 = googleMap.addMarker(new MarkerOptions()
                .position(A256)
                .title("A256")
                .snippet("37.685074, 127.030368"));
        markerA257 = googleMap.addMarker(new MarkerOptions()
                .position(A257)
                .title("A257")
                .snippet("37.652181, 127.008051"));
        markerA258 = googleMap.addMarker(new MarkerOptions()
                .position(A258)
                .title("A258")
                .snippet("37.651327, 127.006114"));
        markerA259 = googleMap.addMarker(new MarkerOptions()
                .position(A259)
                .title("A259")
                .snippet("37.654787, 127.008266"));
        markerA260 = googleMap.addMarker(new MarkerOptions()
                .position(A260)
                .title("A260")
                .snippet("37.623912, 126.990596"));
        markerA261 = googleMap.addMarker(new MarkerOptions()
                .position(A261)
                .title("A261")
                .snippet("37.69013, 127.017513"));
        markerA262 = googleMap.addMarker(new MarkerOptions()
                .position(A262)
                .title("A262")
                .snippet("37.728369, 127.020694"));
        markerA263 = googleMap.addMarker(new MarkerOptions()
                .position(A263)
                .title("A263")
                .snippet("37.64252, 127.001359"));
        markerA264 = googleMap.addMarker(new MarkerOptions()
                .position(A264)
                .title("A264")
                .snippet("37.642247, 127.002155"));
        markerA265 = googleMap.addMarker(new MarkerOptions()
                .position(A265)
                .title("A265")
                .snippet("37.675274, 127.022028"));
        markerA266 = googleMap.addMarker(new MarkerOptions()
                .position(A266)
                .title("A266")
                .snippet("37.696987, 127.018533"));
        markerA267 = googleMap.addMarker(new MarkerOptions()
                .position(A267)
                .title("A267")
                .snippet("37.674718, 127.019042"));
        markerA268 = googleMap.addMarker(new MarkerOptions()
                .position(A268)
                .title("A268")
                .snippet("37.623912, 126.990596"));
        markerA269 = googleMap.addMarker(new MarkerOptions()
                .position(A269)
                .title("A269")
                .snippet("37.685656, 127.0204"));
        markerA270 = googleMap.addMarker(new MarkerOptions()
                .position(A270)
                .title("A270")
                .snippet("37.685378, 127.020122"));
        markerA271 = googleMap.addMarker(new MarkerOptions()
                .position(A271)
                .title("A271")
                .snippet("37.682578, 127.010844"));
        markerA272 = googleMap.addMarker(new MarkerOptions()
                .position(A272)
                .title("A272")
                .snippet("37.682946, 127.010646"));
        markerA273 = googleMap.addMarker(new MarkerOptions()
                .position(A273)
                .title("A273")
                .snippet("37.720891, 127.030525"));
        markerA274 = googleMap.addMarker(new MarkerOptions()
                .position(A274)
                .title("A274")
                .snippet("37.728396, 127.020683"));
        markerA275 = googleMap.addMarker(new MarkerOptions()
                .position(A275)
                .title("A275")
                .snippet("37.696354, 126.97594"));
        markerA276 = googleMap.addMarker(new MarkerOptions()
                .position(A276)
                .title("A276")
                .snippet("37.720891, 127.030525"));
        markerA277 = googleMap.addMarker(new MarkerOptions()
                .position(A277)
                .title("A277")
                .snippet("37.720891, 127.030525"));
        markerA278 = googleMap.addMarker(new MarkerOptions()
                .position(A278)
                .title("A278")
                .snippet("37.720891, 127.030525"));
        markerA279 = googleMap.addMarker(new MarkerOptions()
                .position(A279)
                .title("A279")
                .snippet("37.691749, 126.976922"));
        markerA280 = googleMap.addMarker(new MarkerOptions()
                .position(A280)
                .title("A280")
                .snippet("37.694244, 126.986106"));
        markerA281 = googleMap.addMarker(new MarkerOptions()
                .position(A281)
                .title("A281")
                .snippet("37.694244, 126.986106"));
        markerA282 = googleMap.addMarker(new MarkerOptions()
                .position(A282)
                .title("A282")
                .snippet("37.694244, 126.986106"));
        markerA283 = googleMap.addMarker(new MarkerOptions()
                .position(A283)
                .title("A283")
                .snippet("37.693432, 126.995483"));
        markerA284 = googleMap.addMarker(new MarkerOptions()
                .position(A284)
                .title("A284")
                .snippet("37.690624, 126.99382"));
        markerA285 = googleMap.addMarker(new MarkerOptions()
                .position(A285)
                .title("A285")
                .snippet("37.682359, 127.016912"));
        markerA286 = googleMap.addMarker(new MarkerOptions()
                .position(A286)
                .title("A286")
                .snippet("37.67964, 127.012901"));
        markerA287 = googleMap.addMarker(new MarkerOptions()
                .position(A287)
                .title("A287")
                .snippet("37.640278, 126.943333"));
        markerA288 = googleMap.addMarker(new MarkerOptions()
                .position(A288)
                .title("A288")
                .snippet("37.676697, 127.021525"));
        markerA289 = googleMap.addMarker(new MarkerOptions()
                .position(A289)
                .title("A289")
                .snippet("37.67674, 127.021725"));
        markerA290 = googleMap.addMarker(new MarkerOptions()
                .position(A290)
                .title("A290")
                .snippet("37.707924, 126.980192"));
        markerA291 = googleMap.addMarker(new MarkerOptions()
                .position(A291)
                .title("A291")
                .snippet("37.728369, 127.020694"));
        markerA292 = googleMap.addMarker(new MarkerOptions()
                .position(A292)
                .title("A292")
                .snippet("37.696356, 127.018728"));
        markerA293 = googleMap.addMarker(new MarkerOptions()
                .position(A293)
                .title("A293")
                .snippet("37.696353, 127.019222"));
        markerA294 = googleMap.addMarker(new MarkerOptions()
                .position(A294)
                .title("A294")
                .snippet("37.645676, 126.970211"));
        markerA295 = googleMap.addMarker(new MarkerOptions()
                .position(A295)
                .title("A295")
                .snippet("37.665589, 126.998533"));
        markerA296 = googleMap.addMarker(new MarkerOptions()
                .position(A296)
                .title("A296")
                .snippet("37.624608, 127.010027"));
        markerA297 = googleMap.addMarker(new MarkerOptions()
                .position(A297)
                .title("A297")
                .snippet("37.658134, 127.002419"));
        markerA298 = googleMap.addMarker(new MarkerOptions()
                .position(A298)
                .title("A298")
                .snippet("37.618438, 126.953902"));
        markerA299 = googleMap.addMarker(new MarkerOptions()
                .position(A299)
                .title("A299")
                .snippet("37.630879, 127.003531"));
        markerA300 = googleMap.addMarker(new MarkerOptions()
                .position(A300)
                .title("A300")
                .snippet("37.662458, 126.992761"));
        markerA301 = googleMap.addMarker(new MarkerOptions()
                .position(A301)
                .title("A301")
                .snippet("37.677072, 127.028058"));
        markerA302 = googleMap.addMarker(new MarkerOptions()
                .position(A302)
                .title("A302")
                .snippet("37.623912, 126.990596"));
        markerA303 = googleMap.addMarker(new MarkerOptions()
                .position(A303)
                .title("A303")
                .snippet("37.639133, 126.978906"));
        markerA304 = googleMap.addMarker(new MarkerOptions()
                .position(A304)
                .title("A304")
                .snippet("37.687965, 127.030262"));
        markerA305 = googleMap.addMarker(new MarkerOptions()
                .position(A305)
                .title("A305")
                .snippet("37.684764, 127.033184"));
        markerA306 = googleMap.addMarker(new MarkerOptions()
                .position(A306)
                .title("A306")
                .snippet("37.678085, 127.002628"));
        markerA307 = googleMap.addMarker(new MarkerOptions()
                .position(A307)
                .title("A307")
                .snippet("37.618308, 126.939044"));
        markerA308 = googleMap.addMarker(new MarkerOptions()
                .position(A308)
                .title("A308")
                .snippet("37.618308, 126.939044"));
        markerA309 = googleMap.addMarker(new MarkerOptions()
                .position(A309)
                .title("A309")
                .snippet("37.618308, 126.939044"));
        markerA310 = googleMap.addMarker(new MarkerOptions()
                .position(A310)
                .title("A310")
                .snippet("37.618308, 126.939044"));
        markerA311 = googleMap.addMarker(new MarkerOptions()
                .position(A311)
                .title("A311")
                .snippet("37.618308, 126.939044"));
        markerA312 = googleMap.addMarker(new MarkerOptions()
                .position(A312)
                .title("A312")
                .snippet("37.618308, 126.939044"));
        markerA313 = googleMap.addMarker(new MarkerOptions()
                .position(A313)
                .title("A313")
                .snippet("37.618308, 126.939044"));
        markerA314 = googleMap.addMarker(new MarkerOptions()
                .position(A314)
                .title("A314")
                .snippet("37.665589, 126.998533"));
        markerA315 = googleMap.addMarker(new MarkerOptions()
                .position(A315)
                .title("A315")
                .snippet("37.682973, 127.010669"));
        markerA316 = googleMap.addMarker(new MarkerOptions()
                .position(A316)
                .title("A316")
                .snippet("37.665484, 126.971683"));
        markerA317 = googleMap.addMarker(new MarkerOptions()
                .position(A317)
                .title("A317")
                .snippet("37.665484, 126.971683"));
        markerA318 = googleMap.addMarker(new MarkerOptions()
                .position(A318)
                .title("A318")
                .snippet("37.662458, 126.992761"));
        markerA319 = googleMap.addMarker(new MarkerOptions()
                .position(A319)
                .title("A319")
                .snippet("37.665589, 126.998533"));
        markerA320 = googleMap.addMarker(new MarkerOptions()
                .position(A320)
                .title("A320")
                .snippet("37.618438, 126.953902"));
        markerA321 = googleMap.addMarker(new MarkerOptions()
                .position(A321)
                .title("A321")
                .snippet("37.620381, 126.953583"));
        markerA322 = googleMap.addMarker(new MarkerOptions()
                .position(A322)
                .title("A322")
                .snippet("37.649716, 127.00533"));
        markerA323 = googleMap.addMarker(new MarkerOptions()
                .position(A323)
                .title("A323")
                .snippet("37.659018, 126.96079"));
        markerA324 = googleMap.addMarker(new MarkerOptions()
                .position(A324)
                .title("A324")
                .snippet("37.645676, 126.970211"));
        markerA325 = googleMap.addMarker(new MarkerOptions()
                .position(A325)
                .title("A325")
                .snippet("37.649716, 127.00533"));
        markerA326 = googleMap.addMarker(new MarkerOptions()
                .position(A326)
                .title("A326")
                .snippet("37.695129, 127.020474"));
        markerA327 = googleMap.addMarker(new MarkerOptions()
                .position(A327)
                .title("A327")
                .snippet("37.682359, 127.016912"));
        markerA328 = googleMap.addMarker(new MarkerOptions()
                .position(A328)
                .title("A328")
                .snippet("37.618438, 126.953902"));
        markerA329 = googleMap.addMarker(new MarkerOptions()
                .position(A329)
                .title("A329")
                .snippet("37.668503, 127.00685"));
        markerA330 = googleMap.addMarker(new MarkerOptions()
                .position(A330)
                .title("A330")
                .snippet("37.662458, 126.992761"));
        markerA331 = googleMap.addMarker(new MarkerOptions()
                .position(A331)
                .title("A331")
                .snippet("37.720567, 127.003522"));
        markerA332 = googleMap.addMarker(new MarkerOptions()
                .position(A332)
                .title("A332")
                .snippet("37.630879, 127.003531"));
        markerA333 = googleMap.addMarker(new MarkerOptions()
                .position(A333)
                .title("A333")
                .snippet("37.675639, 126.959345"));
        markerA334 = googleMap.addMarker(new MarkerOptions()
                .position(A334)
                .title("A334")
                .snippet("37.617194, 126.947333"));
        markerA335 = googleMap.addMarker(new MarkerOptions()
                .position(A335)
                .title("A335")
                .snippet("37.637844, 126.946491"));
        markerA336 = googleMap.addMarker(new MarkerOptions()
                .position(A336)
                .title("A336")
                .snippet("37.642285, 126.952468"));
        markerA337 = googleMap.addMarker(new MarkerOptions()
                .position(A337)
                .title("A337")
                .snippet("37.645374, 127.00004"));
        markerA338 = googleMap.addMarker(new MarkerOptions()
                .position(A338)
                .title("A338")
                .snippet("37.636245, 127.001986"));
        markerA339 = googleMap.addMarker(new MarkerOptions()
                .position(A339)
                .title("A339")
                .snippet("37.638158, 126.993892"));
        markerA340 = googleMap.addMarker(new MarkerOptions()
                .position(A340)
                .title("A340")
                .snippet("37.6721, 126.957524"));
        markerA341 = googleMap.addMarker(new MarkerOptions()
                .position(A341)
                .title("A341")
                .snippet("37.638158, 126.993892"));
        markerA342 = googleMap.addMarker(new MarkerOptions()
                .position(A342)
                .title("A342")
                .snippet("37.653326, 126.960019"));
        markerA343 = googleMap.addMarker(new MarkerOptions()
                .position(A343)
                .title("A343")
                .snippet("37.656472, 126.969056"));
        markerA344 = googleMap.addMarker(new MarkerOptions()
                .position(A344)
                .title("A344")
                .snippet("37.655675, 126.948563"));
        markerA345 = googleMap.addMarker(new MarkerOptions()
                .position(A345)
                .title("A345")
                .snippet("37.647028, 126.962611"));
        markerA346 = googleMap.addMarker(new MarkerOptions()
                .position(A346)
                .title("A346")
                .snippet("37.648361, 126.97275"));
        markerA347 = googleMap.addMarker(new MarkerOptions()
                .position(A347)
                .title("A347")
                .snippet("37.638142, 126.946578"));
        markerA348 = googleMap.addMarker(new MarkerOptions()
                .position(A348)
                .title("A348")
                .snippet("37.710812, 126.99543"));
        markerA349 = googleMap.addMarker(new MarkerOptions()
                .position(A349)
                .title("A349")
                .snippet("37.712186, 126.984597"));
        markerA350 = googleMap.addMarker(new MarkerOptions()
                .position(A350)
                .title("A350")
                .snippet("37.711322, 126.987789"));
        markerA351 = googleMap.addMarker(new MarkerOptions()
                .position(A351)
                .title("A351")
                .snippet("37.635827, 126.976363"));
        markerA352 = googleMap.addMarker(new MarkerOptions()
                .position(A352)
                .title("A352")
                .snippet("37.645154, 126.975533"));
        markerA353 = googleMap.addMarker(new MarkerOptions()
                .position(A353)
                .title("A353")
                .snippet("37.645175, 126.940099"));
        markerA354 = googleMap.addMarker(new MarkerOptions()
                .position(A354)
                .title("A354")
                .snippet("37.629176, 126.958747"));
        markerA355 = googleMap.addMarker(new MarkerOptions()
                .position(A355)
                .title("A355")
                .snippet("37.650417, 126.966611"));
        markerA356 = googleMap.addMarker(new MarkerOptions()
                .position(A356)
                .title("A356")
                .snippet("37.643861, 126.961806"));
        markerA357 = googleMap.addMarker(new MarkerOptions()
                .position(A357)
                .title("A357")
                .snippet("37.709897, 127.039152"));
        markerA358 = googleMap.addMarker(new MarkerOptions()
                .position(A358)
                .title("A358")
                .snippet("37.649716, 127.00533"));
        markerA359 = googleMap.addMarker(new MarkerOptions()
                .position(A359)
                .title("A359")
                .snippet("37.640478, 127.001019"));
        markerA360 = googleMap.addMarker(new MarkerOptions()
                .position(A360)
                .title("A360")
                .snippet("37.676157, 126.970119"));
        markerA361 = googleMap.addMarker(new MarkerOptions()
                .position(A361)
                .title("A361")
                .snippet("37.707047, 126.978844"));
        markerA362 = googleMap.addMarker(new MarkerOptions()
                .position(A362)
                .title("A362")
                .snippet("37.660642, 126.993697"));
        markerA363 = googleMap.addMarker(new MarkerOptions()
                .position(A363)
                .title("A363")
                .snippet("37.660642, 126.993697"));
        markerA364 = googleMap.addMarker(new MarkerOptions()
                .position(A364)
                .title("A364")
                .snippet("37.707047, 126.978844"));
        markerA365 = googleMap.addMarker(new MarkerOptions()
                .position(A365)
                .title("A365")
                .snippet("37.639163, 126.945918"));
        markerA366 = googleMap.addMarker(new MarkerOptions()
                .position(A366)
                .title("A366")
                .snippet("37.623032, 126.974217"));
        markerA367 = googleMap.addMarker(new MarkerOptions()
                .position(A367)
                .title("A367")
                .snippet("37.620378, 126.953583"));
        markerA368 = googleMap.addMarker(new MarkerOptions()
                .position(A368)
                .title("A368")
                .snippet("37.628389, 126.974214"));
        markerA369 = googleMap.addMarker(new MarkerOptions()
                .position(A369)
                .title("A369")
                .snippet("37.629176, 126.958747"));
        markerA370 = googleMap.addMarker(new MarkerOptions()
                .position(A370)
                .title("A370")
                .snippet("37.626931, 126.960534"));
        markerA371 = googleMap.addMarker(new MarkerOptions()
                .position(A371)
                .title("A371")
                .snippet("37.631767, 126.973417"));
        markerA372 = googleMap.addMarker(new MarkerOptions()
                .position(A372)
                .title("A372")
                .snippet("37.637117, 126.997533"));
        markerA373 = googleMap.addMarker(new MarkerOptions()
                .position(A373)
                .title("A373")
                .snippet("37.640704, 127.000794"));
        markerA374 = googleMap.addMarker(new MarkerOptions()
                .position(A374)
                .title("A374")
                .snippet("37.629065, 127.001085"));
        markerA375 = googleMap.addMarker(new MarkerOptions()
                .position(A375)
                .title("A375")
                .snippet("37.638142, 126.946578"));
        markerA376 = googleMap.addMarker(new MarkerOptions()
                .position(A376)
                .title("A376")
                .snippet("37.645175, 126.940099"));
        markerA377 = googleMap.addMarker(new MarkerOptions()
                .position(A377)
                .title("A377")
                .snippet("37.642481, 126.950892"));
        markerA378 = googleMap.addMarker(new MarkerOptions()
                .position(A378)
                .title("A378")
                .snippet("37.655981, 126.948203"));
        markerA379 = googleMap.addMarker(new MarkerOptions()
                .position(A379)
                .title("A379")
                .snippet("37.637117, 126.997533"));
        markerA380 = googleMap.addMarker(new MarkerOptions()
                .position(A380)
                .title("A380")
                .snippet("37.721461, 127.030547"));
        markerA381 = googleMap.addMarker(new MarkerOptions()
                .position(A381)
                .title("A381")
                .snippet("37.721461, 127.030547"));
        markerA382 = googleMap.addMarker(new MarkerOptions()
                .position(A382)
                .title("A382")
                .snippet("37.658044, 127.0023"));
        markerA383 = googleMap.addMarker(new MarkerOptions()
                .position(A383)
                .title("A383")
                .snippet("37.730541, 127.020779"));
        markerA384 = googleMap.addMarker(new MarkerOptions()
                .position(A384)
                .title("A384")
                .snippet("37.728396, 127.020683"));
        markerA385 = googleMap.addMarker(new MarkerOptions()
                .position(A385)
                .title("A385")
                .snippet("37.645676, 126.970211"));
        markerA386 = googleMap.addMarker(new MarkerOptions()
                .position(A386)
                .title("A386")
                .snippet("37.659018, 126.96079"));
        markerA387 = googleMap.addMarker(new MarkerOptions()
                .position(A387)
                .title("A387")
                .snippet("37.641139, 126.978035"));
        markerA388 = googleMap.addMarker(new MarkerOptions()
                .position(A388)
                .title("A388")
                .snippet("37.659018, 126.96079"));
        markerA389 = googleMap.addMarker(new MarkerOptions()
                .position(A389)
                .title("A389")
                .snippet("37.659018, 126.96079"));
        markerA390 = googleMap.addMarker(new MarkerOptions()
                .position(A390)
                .title("A390")
                .snippet("37.659018, 126.96079"));
        markerA391 = googleMap.addMarker(new MarkerOptions()
                .position(A391)
                .title("A391")
                .snippet("37.665484, 126.971683"));
        markerA392 = googleMap.addMarker(new MarkerOptions()
                .position(A392)
                .title("A392")
                .snippet("37.659018, 126.96079"));
        markerA393 = googleMap.addMarker(new MarkerOptions()
                .position(A393)
                .title("A393")
                .snippet("37.641139, 126.978035"));
        markerA394 = googleMap.addMarker(new MarkerOptions()
                .position(A394)
                .title("A394")
                .snippet("37.659108, 126.960687"));
        markerA395 = googleMap.addMarker(new MarkerOptions()
                .position(A395)
                .title("A395")
                .snippet("37.659108, 126.960687"));
        markerA396 = googleMap.addMarker(new MarkerOptions()
                .position(A396)
                .title("A396")
                .snippet("37.659018, 126.96079"));
        markerA397 = googleMap.addMarker(new MarkerOptions()
                .position(A397)
                .title("A397")
                .snippet("37.665484, 126.971683"));
        markerA398 = googleMap.addMarker(new MarkerOptions()
                .position(A398)
                .title("A398")
                .snippet("37.659018, 126.96079"));
        markerA399 = googleMap.addMarker(new MarkerOptions()
                .position(A399)
                .title("A399")
                .snippet("37.641139, 126.978035"));
        markerA400 = googleMap.addMarker(new MarkerOptions()
                .position(A400)
                .title("A400")
                .snippet("37.659018, 126.96079"));
        markerA401 = googleMap.addMarker(new MarkerOptions()
                .position(A401)
                .title("A401")
                .snippet("37.659018, 126.96079"));
        markerA402 = googleMap.addMarker(new MarkerOptions()
                .position(A402)
                .title("A402")
                .snippet("37.659018, 126.96079"));
        markerA403 = googleMap.addMarker(new MarkerOptions()
                .position(A403)
                .title("A403")
                .snippet("37.665484, 126.971683"));
        markerA404 = googleMap.addMarker(new MarkerOptions()
                .position(A404)
                .title("A404")
                .snippet("37.730541, 127.020779"));
        markerA405 = googleMap.addMarker(new MarkerOptions()
                .position(A405)
                .title("A405")
                .snippet("37.728396, 127.020683"));
        markerA406 = googleMap.addMarker(new MarkerOptions()
                .position(A406)
                .title("A406")
                .snippet("37.662458, 126.992761"));
        markerA407 = googleMap.addMarker(new MarkerOptions()
                .position(A407)
                .title("A407")
                .snippet("37.639163, 126.945918"));
        markerA408 = googleMap.addMarker(new MarkerOptions()
                .position(A408)
                .title("A408")
                .snippet("37.684764, 127.033184"));
        markerA409 = googleMap.addMarker(new MarkerOptions()
                .position(A409)
                .title("A409")
                .snippet("37.655675, 126.948563"));
        markerA410 = googleMap.addMarker(new MarkerOptions()
                .position(A410)
                .title("A410")
                .snippet("37.625773, 126.962125"));
        markerA411 = googleMap.addMarker(new MarkerOptions()
                .position(A411)
                .title("A411")
                .snippet("37.685956, 127.034919"));
        markerA412 = googleMap.addMarker(new MarkerOptions()
                .position(A412)
                .title("A412")
                .snippet("37.694264, 127.020452"));
        markerA413 = googleMap.addMarker(new MarkerOptions()
                .position(A413)
                .title("A413")
                .snippet("37.691258, 127.023054"));
        markerA414 = googleMap.addMarker(new MarkerOptions()
                .position(A414)
                .title("A414")
                .snippet("37.695129, 127.020474"));
        markerA415 = googleMap.addMarker(new MarkerOptions()
                .position(A415)
                .title("A415")
                .snippet("37.678085, 127.002628"));
        markerA416 = googleMap.addMarker(new MarkerOptions()
                .position(A416)
                .title("A416")
                .snippet("37.689215, 127.025552"));
        markerA417 = googleMap.addMarker(new MarkerOptions()
                .position(A417)
                .title("A417")
                .snippet("37.662458, 126.992761"));
        markerA418 = googleMap.addMarker(new MarkerOptions()
                .position(A418)
                .title("A418")
                .snippet("37.677539, 127.032314"));
        markerA419 = googleMap.addMarker(new MarkerOptions()
                .position(A419)
                .title("A419")
                .snippet("37.688372, 127.030348"));
        markerA420 = googleMap.addMarker(new MarkerOptions()
                .position(A420)
                .title("A420")
                .snippet("37.671517, 126.999983"));
        markerA421 = googleMap.addMarker(new MarkerOptions()
                .position(A421)
                .title("A421")
                .snippet("37.656508, 126.991597"));
        markerA422 = googleMap.addMarker(new MarkerOptions()
                .position(A422)
                .title("A422")
                .snippet("37.623912, 126.990596"));
        markerA423 = googleMap.addMarker(new MarkerOptions()
                .position(A423)
                .title("A423")
                .snippet("37.636245, 127.001986"));
        markerA424 = googleMap.addMarker(new MarkerOptions()
                .position(A424)
                .title("A424")
                .snippet("37.682973, 127.010669"));
        markerA425 = googleMap.addMarker(new MarkerOptions()
                .position(A425)
                .title("A425")
                .snippet("37.694061, 127.020469"));
        markerA426 = googleMap.addMarker(new MarkerOptions()
                .position(A426)
                .title("A426")
                .snippet("37.644119, 126.961764"));
        markerA427 = googleMap.addMarker(new MarkerOptions()
                .position(A427)
                .title("A427")
                .snippet("37.653326, 126.960019"));
        markerA428 = googleMap.addMarker(new MarkerOptions()
                .position(A428)
                .title("A428")
                .snippet("37.68739, 127.016222"));
        markerA429 = googleMap.addMarker(new MarkerOptions()
                .position(A429)
                .title("A429")
                .snippet("37.640704, 127.000794"));
        markerA430 = googleMap.addMarker(new MarkerOptions()
                .position(A430)
                .title("A430")
                .snippet("37.623912, 126.990596"));
        markerA431 = googleMap.addMarker(new MarkerOptions()
                .position(A431)
                .title("A431")
                .snippet("37.662458, 126.992761"));
        markerA432 = googleMap.addMarker(new MarkerOptions()
                .position(A432)
                .title("A432")
                .snippet("37.649558, 126.97265"));
        markerA433 = googleMap.addMarker(new MarkerOptions()
                .position(A433)
                .title("A433")
                .snippet("37.661278, 126.985133"));
        markerA434 = googleMap.addMarker(new MarkerOptions()
                .position(A434)
                .title("A434")
                .snippet("37.661278, 126.985133"));
        markerA435 = googleMap.addMarker(new MarkerOptions()
                .position(A435)
                .title("A435")
                .snippet("37.659108, 126.960687"));
        markerA436 = googleMap.addMarker(new MarkerOptions()
                .position(A436)
                .title("A436")
                .snippet("37.646976, 126.962352"));
        markerA437 = googleMap.addMarker(new MarkerOptions()
                .position(A437)
                .title("A437")
                .snippet("37.623912, 126.990596"));
        markerA438 = googleMap.addMarker(new MarkerOptions()
                .position(A438)
                .title("A438")
                .snippet("37.616473, 126.977444"));
        markerA439 = googleMap.addMarker(new MarkerOptions()
                .position(A439)
                .title("A439")
                .snippet("37.616473, 126.977444"));
        markerA440 = googleMap.addMarker(new MarkerOptions()
                .position(A440)
                .title("A440")
                .snippet("37.616473, 126.977444"));
        markerA441 = googleMap.addMarker(new MarkerOptions()
                .position(A441)
                .title("A441")
                .snippet("37.616473, 126.977444"));
        markerA442 = googleMap.addMarker(new MarkerOptions()
                .position(A442)
                .title("A442")
                .snippet("37.645103, 126.940145"));
        markerA443 = googleMap.addMarker(new MarkerOptions()
                .position(A443)
                .title("A443")
                .snippet("37.643858, 126.962058"));
        markerA444 = googleMap.addMarker(new MarkerOptions()
                .position(A444)
                .title("A444")
                .snippet("37.665589, 126.998533"));
        markerA445 = googleMap.addMarker(new MarkerOptions()
                .position(A445)
                .title("A445")
                .snippet("37.659108, 126.960687"));
        markerA446 = googleMap.addMarker(new MarkerOptions()
                .position(A446)
                .title("A446")
                .snippet("37.645676, 126.970211"));
        markerA447 = googleMap.addMarker(new MarkerOptions()
                .position(A447)
                .title("A447")
                .snippet("37.645676, 126.970211"));
        markerA448 = googleMap.addMarker(new MarkerOptions()
                .position(A448)
                .title("A448")
                .snippet("37.659108, 126.960687"));
        markerA449 = googleMap.addMarker(new MarkerOptions()
                .position(A449)
                .title("A449")
                .snippet("37.659108, 126.960687"));
        markerA450 = googleMap.addMarker(new MarkerOptions()
                .position(A450)
                .title("A450")
                .snippet("37.659108, 126.960687"));
        markerA451 = googleMap.addMarker(new MarkerOptions()
                .position(A451)
                .title("A451")
                .snippet("37.659108, 126.960687"));
        markerA452 = googleMap.addMarker(new MarkerOptions()
                .position(A452)
                .title("A452")
                .snippet("37.645676, 126.970211"));
        markerA453 = googleMap.addMarker(new MarkerOptions()
                .position(A453)
                .title("A453")
                .snippet("37.659108, 126.960687"));
        markerA454 = googleMap.addMarker(new MarkerOptions()
                .position(A454)
                .title("A454")
                .snippet("37.645676, 126.970211"));
        markerA455 = googleMap.addMarker(new MarkerOptions()
                .position(A455)
                .title("A455")
                .snippet("37.626931, 126.960534"));
        markerA456 = googleMap.addMarker(new MarkerOptions()
                .position(A456)
                .title("A456")
                .snippet("37.626931, 126.960534"));
        markerA457 = googleMap.addMarker(new MarkerOptions()
                .position(A457)
                .title("A457")
                .snippet("37.691749, 126.976922"));
        markerA458 = googleMap.addMarker(new MarkerOptions()
                .position(A458)
                .title("A458")
                .snippet("37.691169, 126.995298"));
        markerA459 = googleMap.addMarker(new MarkerOptions()
                .position(A459)
                .title("A459")
                .snippet("37.64553, 126.978304"));
        markerA460 = googleMap.addMarker(new MarkerOptions()
                .position(A460)
                .title("A460")
                .snippet("37.650402, 126.966599"));
        markerA461 = googleMap.addMarker(new MarkerOptions()
                .position(A461)
                .title("A461")
                .snippet("37.656514, 126.969064"));
        markerA462 = googleMap.addMarker(new MarkerOptions()
                .position(A462)
                .title("A462")
                .snippet("37.646852, 126.962603"));
        markerA463 = googleMap.addMarker(new MarkerOptions()
                .position(A463)
                .title("A463")
                .snippet("37.671966, 126.957753"));
        markerA464 = googleMap.addMarker(new MarkerOptions()
                .position(A464)
                .title("A464")
                .snippet("37.675453, 126.959611"));
        markerA465 = googleMap.addMarker(new MarkerOptions()
                .position(A465)
                .title("A465")
                .snippet("37.676348, 126.970309"));
        markerA466 = googleMap.addMarker(new MarkerOptions()
                .position(A466)
                .title("A466")
                .snippet("37.704601, 127.024069"));
        markerA467 = googleMap.addMarker(new MarkerOptions()
                .position(A467)
                .title("A467")
                .snippet("37.721036, 127.030092"));
        markerA468 = googleMap.addMarker(new MarkerOptions()
                .position(A468)
                .title("A468")
                .snippet("37.62678, 126.960842"));
        markerA469 = googleMap.addMarker(new MarkerOptions()
                .position(A469)
                .title("A469")
                .snippet("37.709897, 127.039152"));
        markerA470 = googleMap.addMarker(new MarkerOptions()
                .position(A470)
                .title("A470")
                .snippet("37.653326, 126.960019"));
        markerA471 = googleMap.addMarker(new MarkerOptions()
                .position(A471)
                .title("A471")
                .snippet("37.635736, 126.976283"));
        markerA472 = googleMap.addMarker(new MarkerOptions()
                .position(A472)
                .title("A472")
                .snippet("37.646083, 126.976585"));
        markerA473 = googleMap.addMarker(new MarkerOptions()
                .position(A473)
                .title("A473")
                .snippet("37.646976, 126.962352"));
        markerA474 = googleMap.addMarker(new MarkerOptions()
                .position(A474)
                .title("A474")
                .snippet("37.653326, 126.960019"));
        markerA475 = googleMap.addMarker(new MarkerOptions()
                .position(A475)
                .title("A475")
                .snippet("37.649553, 126.972639"));
        markerA476 = googleMap.addMarker(new MarkerOptions()
                .position(A476)
                .title("A476")
                .snippet("37.645555, 126.978066"));
        markerA477 = googleMap.addMarker(new MarkerOptions()
                .position(A477)
                .title("A477")
                .snippet("37.687965, 127.030262"));
        markerA478 = googleMap.addMarker(new MarkerOptions()
                .position(A478)
                .title("A478")
                .snippet("37.65385, 126.951025"));
        markerA479 = googleMap.addMarker(new MarkerOptions()
                .position(A479)
                .title("A479")
                .snippet("37.645676, 126.970211"));
        markerA480 = googleMap.addMarker(new MarkerOptions()
                .position(A480)
                .title("A480")
                .snippet("37.655471, 126.954946"));
        markerA481 = googleMap.addMarker(new MarkerOptions()
                .position(A481)
                .title("A481")
                .snippet("37.65385, 126.951025"));
        markerA482 = googleMap.addMarker(new MarkerOptions()
                .position(A482)
                .title("A482")
                .snippet("37.645676, 126.970211"));
        markerA483 = googleMap.addMarker(new MarkerOptions()
                .position(A483)
                .title("A483")
                .snippet("37.655471, 126.954946"));
        markerA484 = googleMap.addMarker(new MarkerOptions()
                .position(A484)
                .title("A484")
                .snippet("37.645676, 126.970211"));
        markerA485 = googleMap.addMarker(new MarkerOptions()
                .position(A485)
                .title("A485")
                .snippet("37.645676, 126.970211"));
        markerA486 = googleMap.addMarker(new MarkerOptions()
                .position(A486)
                .title("A486")
                .snippet("37.611798, 126.988087"));
        markerA487 = googleMap.addMarker(new MarkerOptions()
                .position(A487)
                .title("A487")
                .snippet("37.648438, 126.972825"));
        markerA488 = googleMap.addMarker(new MarkerOptions()
                .position(A488)
                .title("A488")
                .snippet("37.732442, 127.023569"));
        markerA489 = googleMap.addMarker(new MarkerOptions()
                .position(A489)
                .title("A489")
                .snippet("37.691211, 126.98973"));
        markerA490 = googleMap.addMarker(new MarkerOptions()
                .position(A490)
                .title("A490")
                .snippet("37.637117, 126.997533"));
        markerA491 = googleMap.addMarker(new MarkerOptions()
                .position(A491)
                .title("A491")
                .snippet("37.623912, 126.990596"));
        markerA492 = googleMap.addMarker(new MarkerOptions()
                .position(A492)
                .title("A492")
                .snippet("37.662458, 126.992761"));
        markerA493 = googleMap.addMarker(new MarkerOptions()
                .position(A493)
                .title("A493")
                .snippet("37.710311, 127.003747"));
        markerA494 = googleMap.addMarker(new MarkerOptions()
                .position(A494)
                .title("A494")
                .snippet("37.707147, 127.004564"));
        markerA495 = googleMap.addMarker(new MarkerOptions()
                .position(A495)
                .title("A495")
                .snippet("37.695129, 127.020474"));
        markerA496 = googleMap.addMarker(new MarkerOptions()
                .position(A496)
                .title("A496")
                .snippet("37.65489, 126.950466"));
        markerA497 = googleMap.addMarker(new MarkerOptions()
                .position(A497)
                .title("A497")
                .snippet("37.648084, 126.971515"));
        markerA498 = googleMap.addMarker(new MarkerOptions()
                .position(A498)
                .title("A498")
                .snippet("37.637117, 126.997533"));
        markerA499 = googleMap.addMarker(new MarkerOptions()
                .position(A499)
                .title("A499")
                .snippet("37.623912, 126.990596"));
        markerA500 = googleMap.addMarker(new MarkerOptions()
                .position(A500)
                .title("A500")
                .snippet("37.662458, 126.992761"));
        markerA501 = googleMap.addMarker(new MarkerOptions()
                .position(A501)
                .title("A501")
                .snippet("37.710311, 127.003747"));
        markerA502 = googleMap.addMarker(new MarkerOptions()
                .position(A502)
                .title("A502")
                .snippet("37.707147, 127.004564"));
        markerA503 = googleMap.addMarker(new MarkerOptions()
                .position(A503)
                .title("A503")
                .snippet("37.695129, 127.020474"));
        markerA504 = googleMap.addMarker(new MarkerOptions()
                .position(A504)
                .title("A504")
                .snippet("37.65489, 126.950466"));
        markerA505 = googleMap.addMarker(new MarkerOptions()
                .position(A505)
                .title("A505")
                .snippet("37.648084, 126.971515"));
        markerA506 = googleMap.addMarker(new MarkerOptions()
                .position(A506)
                .title("A506")
                .snippet("37.643597, 126.996698"));
        markerA507 = googleMap.addMarker(new MarkerOptions()
                .position(A507)
                .title("A507")
                .snippet("37.611798, 126.988087"));
        markerA508 = googleMap.addMarker(new MarkerOptions()
                .position(A508)
                .title("A508")
                .snippet("37.665589, 126.998533"));
        markerA509 = googleMap.addMarker(new MarkerOptions()
                .position(A509)
                .title("A509")
                .snippet("37.710311, 127.003747"));
        markerA510 = googleMap.addMarker(new MarkerOptions()
                .position(A510)
                .title("A510")
                .snippet("37.707147, 127.004564"));
        markerA511 = googleMap.addMarker(new MarkerOptions()
                .position(A511)
                .title("A511")
                .snippet("37.695129, 127.020474"));
        markerA512 = googleMap.addMarker(new MarkerOptions()
                .position(A512)
                .title("A512")
                .snippet("37.65489, 126.950466"));
        markerA513 = googleMap.addMarker(new MarkerOptions()
                .position(A513)
                .title("A513")
                .snippet("37.648084, 126.971515"));
        markerA514 = googleMap.addMarker(new MarkerOptions()
                .position(A514)
                .title("A514")
                .snippet("37.695129, 127.020474"));
        markerA515 = googleMap.addMarker(new MarkerOptions()
                .position(A515)
                .title("A515")
                .snippet("37.669503, 127.022781"));
        markerA516 = googleMap.addMarker(new MarkerOptions()
                .position(A516)
                .title("A516")
                .snippet("37.686444, 127.023694"));
        markerA517 = googleMap.addMarker(new MarkerOptions()
                .position(A517)
                .title("A517")
                .snippet("37.695129, 127.020474"));
        markerA518 = googleMap.addMarker(new MarkerOptions()
                .position(A518)
                .title("A518")
                .snippet("37.694264, 127.020452"));
        markerA519 = googleMap.addMarker(new MarkerOptions()
                .position(A519)
                .title("A519")
                .snippet("37.695129, 127.020474"));
        markerA520 = googleMap.addMarker(new MarkerOptions()
                .position(A520)
                .title("A520")
                .snippet("37.695129, 127.020474"));
        markerA521 = googleMap.addMarker(new MarkerOptions()
                .position(A521)
                .title("A521")
                .snippet("37.669503, 127.022781"));
        markerA522 = googleMap.addMarker(new MarkerOptions()
                .position(A522)
                .title("A522")
                .snippet("37.686444, 127.023694"));
        markerA523 = googleMap.addMarker(new MarkerOptions()
                .position(A523)
                .title("A523")
                .snippet("37.695129, 127.020474"));
        markerA524 = googleMap.addMarker(new MarkerOptions()
                .position(A524)
                .title("A524")
                .snippet("37.694264, 127.020452"));
        markerA525 = googleMap.addMarker(new MarkerOptions()
                .position(A525)
                .title("A525")
                .snippet("37.695129, 127.020474"));
        markerA526 = googleMap.addMarker(new MarkerOptions()
                .position(A526)
                .title("A526")
                .snippet("37.695129, 127.020474"));
        markerA527 = googleMap.addMarker(new MarkerOptions()
                .position(A527)
                .title("A527")
                .snippet("37.669503, 127.022781"));
        markerA528 = googleMap.addMarker(new MarkerOptions()
                .position(A528)
                .title("A528")
                .snippet("37.686444, 127.023694"));
        markerA529 = googleMap.addMarker(new MarkerOptions()
                .position(A529)
                .title("A529")
                .snippet("37.694264, 127.020452"));
        markerA530 = googleMap.addMarker(new MarkerOptions()
                .position(A530)
                .title("A530")
                .snippet("37.695129, 127.020474"));
        markerA531 = googleMap.addMarker(new MarkerOptions()
                .position(A531)
                .title("A531")
                .snippet("37.691169, 126.995298"));
        markerA532 = googleMap.addMarker(new MarkerOptions()
                .position(A532)
                .title("A532")
                .snippet("37.691169, 126.995298"));
        markerA533 = googleMap.addMarker(new MarkerOptions()
                .position(A533)
                .title("A533")
                .snippet("37.662458, 126.992761"));
        markerA534 = googleMap.addMarker(new MarkerOptions()
                .position(A534)
                .title("A534")
                .snippet("37.662458, 126.992761"));
        markerA535 = googleMap.addMarker(new MarkerOptions()
                .position(A535)
                .title("A535")
                .snippet("37.662458, 126.992761"));
        markerA536 = googleMap.addMarker(new MarkerOptions()
                .position(A536)
                .title("A536")
                .snippet("37.643003, 126.945104"));
        markerA537 = googleMap.addMarker(new MarkerOptions()
                .position(A537)
                .title("A537")
                .snippet("37.62678, 126.960842"));
        markerA538 = googleMap.addMarker(new MarkerOptions()
                .position(A538)
                .title("A538")
                .snippet("37.625773, 126.962125"));
        markerA539 = googleMap.addMarker(new MarkerOptions()
                .position(A539)
                .title("A539")
                .snippet("37.625773, 126.962125"));
        markerA540 = googleMap.addMarker(new MarkerOptions()
                .position(A540)
                .title("A540")
                .snippet("37.71085, 126.992458"));
        markerA541 = googleMap.addMarker(new MarkerOptions()
                .position(A541)
                .title("A541")
                .snippet("37.641432, 127.00484"));
        markerA542 = googleMap.addMarker(new MarkerOptions()
                .position(A542)
                .title("A542")
                .snippet("37.641432, 127.00484"));
        markerA543 = googleMap.addMarker(new MarkerOptions()
                .position(A543)
                .title("A543")
                .snippet("37.641993, 127.005184"));
        markerA544 = googleMap.addMarker(new MarkerOptions()
                .position(A544)
                .title("A544")
                .snippet("37.689215, 127.025552"));
        markerA545 = googleMap.addMarker(new MarkerOptions()
                .position(A545)
                .title("A545")
                .snippet("37.665469, 126.971647"));
        markerA546 = googleMap.addMarker(new MarkerOptions()
                .position(A546)
                .title("A546")
                .snippet("37.665469, 126.971647"));
        markerA547 = googleMap.addMarker(new MarkerOptions()
                .position(A547)
                .title("A547")
                .snippet("37.653131, 126.960386"));
        markerA548 = googleMap.addMarker(new MarkerOptions()
                .position(A548)
                .title("A548")
                .snippet("37.653131, 126.960386"));
        markerA549 = googleMap.addMarker(new MarkerOptions()
                .position(A549)
                .title("A549")
                .snippet("37.653326, 126.960019"));
        markerA550 = googleMap.addMarker(new MarkerOptions()
                .position(A550)
                .title("A550")
                .snippet("37.637117, 126.997533"));
        markerA551 = googleMap.addMarker(new MarkerOptions()
                .position(A551)
                .title("A551")
                .snippet("37.637117, 126.997533"));
        markerA552 = googleMap.addMarker(new MarkerOptions()
                .position(A552)
                .title("A552")
                .snippet("37.665589, 126.998533"));
        markerA553 = googleMap.addMarker(new MarkerOptions()
                .position(A553)
                .title("A553")
                .snippet("37.643627, 127.003361"));
        markerA554 = googleMap.addMarker(new MarkerOptions()
                .position(A554)
                .title("A554")
                .snippet("37.643167, 127.002353"));
        markerA555 = googleMap.addMarker(new MarkerOptions()
                .position(A555)
                .title("A555")
                .snippet("37.643195, 127.002347"));
        markerA556 = googleMap.addMarker(new MarkerOptions()
                .position(A556)
                .title("A556")
                .snippet("37.709694, 126.999956"));
        markerA557 = googleMap.addMarker(new MarkerOptions()
                .position(A557)
                .title("A557")
                .snippet("37.710367, 127.038989"));
        markerA558 = googleMap.addMarker(new MarkerOptions()
                .position(A558)
                .title("A558")
                .snippet("37.665484, 126.971683"));
        markerA559 = googleMap.addMarker(new MarkerOptions()
                .position(A559)
                .title("A559")
                .snippet("37.631294, 126.968733"));
        markerA560 = googleMap.addMarker(new MarkerOptions()
                .position(A560)
                .title("A560")
                .snippet("37.659108, 126.960687"));
        markerA561 = googleMap.addMarker(new MarkerOptions()
                .position(A561)
                .title("A561")
                .snippet("37.642392, 126.979333"));
        markerA562 = googleMap.addMarker(new MarkerOptions()
                .position(A562)
                .title("A562")
                .snippet("37.659108, 126.960687"));
        markerA563 = googleMap.addMarker(new MarkerOptions()
                .position(A563)
                .title("A563")
                .snippet("37.645676, 126.970211"));
        markerA564 = googleMap.addMarker(new MarkerOptions()
                .position(A564)
                .title("A564")
                .snippet("37.627914, 126.977197"));
        markerA565 = googleMap.addMarker(new MarkerOptions()
                .position(A565)
                .title("A565")
                .snippet("37.623032, 126.974217"));
        markerA566 = googleMap.addMarker(new MarkerOptions()
                .position(A566)
                .title("A566")
                .snippet("37.628, 126.977086"));
        markerA567 = googleMap.addMarker(new MarkerOptions()
                .position(A567)
                .title("A567")
                .snippet("37.651327, 127.006114"));
        markerA568 = googleMap.addMarker(new MarkerOptions()
                .position(A568)
                .title("A568")
                .snippet("37.693419, 126.992111"));
        markerA569 = googleMap.addMarker(new MarkerOptions()
                .position(A569)
                .title("A569")
                .snippet("37.691878, 126.991842"));
        markerA570 = googleMap.addMarker(new MarkerOptions()
                .position(A570)
                .title("A570")
                .snippet("37.717375, 127.018133"));
        markerA571 = googleMap.addMarker(new MarkerOptions()
                .position(A571)
                .title("A571")
                .snippet("37.693419, 126.992111"));
        markerA572 = googleMap.addMarker(new MarkerOptions()
                .position(A572)
                .title("A572")
                .snippet("37.652628, 126.954839"));
        markerA573 = googleMap.addMarker(new MarkerOptions()
                .position(A573)
                .title("A573")
                .snippet("37.652628, 126.954839"));
        markerA574 = googleMap.addMarker(new MarkerOptions()
                .position(A574)
                .title("A574")
                .snippet("37.681839, 127.013547"));
        markerA575 = googleMap.addMarker(new MarkerOptions()
                .position(A575)
                .title("A575")
                .snippet("37.609094, 126.952531"));
        markerA576 = googleMap.addMarker(new MarkerOptions()
                .position(A576)
                .title("A576")
                .snippet("37.659018, 126.96079"));
        markerA577 = googleMap.addMarker(new MarkerOptions()
                .position(A577)
                .title("A577")
                .snippet("37.659018, 126.96079"));
        markerA578 = googleMap.addMarker(new MarkerOptions()
                .position(A578)
                .title("A578")
                .snippet("37.659018, 126.96079"));
        markerA579 = googleMap.addMarker(new MarkerOptions()
                .position(A579)
                .title("A579")
                .snippet("37.659018, 126.96079"));
        markerA580 = googleMap.addMarker(new MarkerOptions()
                .position(A580)
                .title("A580")
                .snippet("37.665484, 126.971683"));
        markerA581 = googleMap.addMarker(new MarkerOptions()
                .position(A581)
                .title("A581")
                .snippet("37.658842, 126.981281"));
        markerA582 = googleMap.addMarker(new MarkerOptions()
                .position(A582)
                .title("A582")
                .snippet("37.652628, 126.954839"));
        markerA583 = googleMap.addMarker(new MarkerOptions()
                .position(A583)
                .title("A583")
                .snippet("37.682359, 127.016912"));
        markerA584 = googleMap.addMarker(new MarkerOptions()
                .position(A584)
                .title("A584")
                .snippet("37.682359, 127.016912"));
        markerA585 = googleMap.addMarker(new MarkerOptions()
                .position(A585)
                .title("A585")
                .snippet("37.623912, 126.990596"));
        markerA586 = googleMap.addMarker(new MarkerOptions()
                .position(A586)
                .title("A586")
                .snippet("37.706051, 127.00671"));
        markerA587 = googleMap.addMarker(new MarkerOptions()
                .position(A587)
                .title("A587")
                .snippet("37.706051, 127.00671"));
        markerA588 = googleMap.addMarker(new MarkerOptions()
                .position(A588)
                .title("A588")
                .snippet("37.706325, 127.005067"));
        markerA589 = googleMap.addMarker(new MarkerOptions()
                .position(A589)
                .title("A589")
                .snippet("37.706325, 127.005067"));
        markerA590 = googleMap.addMarker(new MarkerOptions()
                .position(A590)
                .title("A590")
                .snippet("37.728369, 127.020694"));
        markerA591 = googleMap.addMarker(new MarkerOptions()
                .position(A591)
                .title("A591")
                .snippet("37.728369, 127.020694"));
        markerA592 = googleMap.addMarker(new MarkerOptions()
                .position(A592)
                .title("A592")
                .snippet("37.728369, 127.020694"));
        markerA593 = googleMap.addMarker(new MarkerOptions()
                .position(A593)
                .title("A593")
                .snippet("37.618438, 126.953902"));
        markerA594 = googleMap.addMarker(new MarkerOptions()
                .position(A594)
                .title("A594")
                .snippet("37.728369, 127.020694"));
        markerA595 = googleMap.addMarker(new MarkerOptions()
                .position(A595)
                .title("A595")
                .snippet("37.728369, 127.020694"));
        markerA596 = googleMap.addMarker(new MarkerOptions()
                .position(A596)
                .title("A596")
                .snippet("37.62678, 126.960842"));
        markerA597 = googleMap.addMarker(new MarkerOptions()
                .position(A597)
                .title("A597")
                .snippet("37.631617, 126.972922"));
        markerA598 = googleMap.addMarker(new MarkerOptions()
                .position(A598)
                .title("A598")
                .snippet("37.631617, 126.972922"));
        markerA599 = googleMap.addMarker(new MarkerOptions()
                .position(A599)
                .title("A599")
                .snippet("37.689613, 126.963368"));
        markerA600 = googleMap.addMarker(new MarkerOptions()
                .position(A600)
                .title("A600")
                .snippet("37.689613, 126.963368"));
        markerA601 = googleMap.addMarker(new MarkerOptions()
                .position(A601)
                .title("A601")
                .snippet("37.689613, 126.963368"));
        markerA602 = googleMap.addMarker(new MarkerOptions()
                .position(A602)
                .title("A602")
                .snippet("37.6893, 126.963081"));
        markerA603 = googleMap.addMarker(new MarkerOptions()
                .position(A603)
                .title("A603")
                .snippet("37.676731, 127.021725"));
        markerA604 = googleMap.addMarker(new MarkerOptions()
                .position(A604)
                .title("A604")
                .snippet("37.675522, 127.005556"));
        markerA605 = googleMap.addMarker(new MarkerOptions()
                .position(A605)
                .title("A605")
                .snippet("37.677819, 127.032342"));
        markerA606 = googleMap.addMarker(new MarkerOptions()
                .position(A606)
                .title("A606")
                .snippet("37.687958, 127.031525"));
        markerA607 = googleMap.addMarker(new MarkerOptions()
                .position(A607)
                .title("A607")
                .snippet("37.648438, 126.972825"));
        markerA608 = googleMap.addMarker(new MarkerOptions()
                .position(A608)
                .title("A608")
                .snippet("37.689613, 126.963368"));
        markerA609 = googleMap.addMarker(new MarkerOptions()
                .position(A609)
                .title("A609")
                .snippet("37.656514, 126.969064"));
        markerA610 = googleMap.addMarker(new MarkerOptions()
                .position(A610)
                .title("A610")
                .snippet("37.671966, 126.957753"));
        markerA611 = googleMap.addMarker(new MarkerOptions()
                .position(A611)
                .title("A611")
                .snippet("37.675413, 126.959326"));
        markerA612 = googleMap.addMarker(new MarkerOptions()
                .position(A612)
                .title("A612")
                .snippet("37.676348, 126.970309"));
        markerA613 = googleMap.addMarker(new MarkerOptions()
                .position(A613)
                .title("A613")
                .snippet("37.646852, 126.962603"));
        markerA614 = googleMap.addMarker(new MarkerOptions()
                .position(A614)
                .title("A614")
                .snippet("37.649739, 127.005119"));
        markerA615 = googleMap.addMarker(new MarkerOptions()
                .position(A615)
                .title("A615")
                .snippet("37.694931, 127.015083"));
        markerA616 = googleMap.addMarker(new MarkerOptions()
                .position(A616)
                .title("A616")
                .snippet("37.642344, 126.946153"));
        markerA617 = googleMap.addMarker(new MarkerOptions()
                .position(A617)
                .title("A617")
                .snippet("37.633656, 126.950194"));
        markerA618 = googleMap.addMarker(new MarkerOptions()
                .position(A618)
                .title("A618")
                .snippet("37.630879, 127.003531"));
        markerA619 = googleMap.addMarker(new MarkerOptions()
                .position(A619)
                .title("A619")
                .snippet("37.645555, 126.978066"));
        markerA620 = googleMap.addMarker(new MarkerOptions()
                .position(A620)
                .title("A620")
                .snippet("37.649853, 126.967147"));
        markerA621 = googleMap.addMarker(new MarkerOptions()
                .position(A621)
                .title("A621")
                .snippet("37.720891, 127.030525"));
        markerA622 = googleMap.addMarker(new MarkerOptions()
                .position(A622)
                .title("A622")
                .snippet("37.656235, 127.000495"));
        markerA623 = googleMap.addMarker(new MarkerOptions()
                .position(A623)
                .title("A623")
                .snippet("37.712386, 126.984577"));
        markerA624 = googleMap.addMarker(new MarkerOptions()
                .position(A624)
                .title("A624")
                .snippet("37.711482, 126.986382"));
        markerA625 = googleMap.addMarker(new MarkerOptions()
                .position(A625)
                .title("A625")
                .snippet("37.710812, 126.99543"));
        markerA626 = googleMap.addMarker(new MarkerOptions()
                .position(A626)
                .title("A626")
                .snippet("37.6436, 126.996606"));
        markerA627 = googleMap.addMarker(new MarkerOptions()
                .position(A627)
                .title("A627")
                .snippet("37.720925, 127.030006"));
        markerA628 = googleMap.addMarker(new MarkerOptions()
                .position(A628)
                .title("A628")
                .snippet("37.704601, 127.024069"));
        markerA629 = googleMap.addMarker(new MarkerOptions()
                .position(A629)
                .title("A629")
                .snippet("37.690089, 127.017425"));
        markerA630 = googleMap.addMarker(new MarkerOptions()
                .position(A630)
                .title("A630")
                .snippet("37.660642, 126.993697"));
        markerA631 = googleMap.addMarker(new MarkerOptions()
                .position(A631)
                .title("A631")
                .snippet("37.695129, 127.020474"));
        markerA632 = googleMap.addMarker(new MarkerOptions()
                .position(A632)
                .title("A632")
                .snippet("37.629197, 126.958769"));
        markerA633 = googleMap.addMarker(new MarkerOptions()
                .position(A633)
                .title("A633")
                .snippet("37.655675, 126.948563"));
        markerA634 = googleMap.addMarker(new MarkerOptions()
                .position(A634)
                .title("A634")
                .snippet("37.720567, 127.003522"));
        markerA635 = googleMap.addMarker(new MarkerOptions()
                .position(A635)
                .title("A635")
                .snippet("37.709897, 127.039152"));
        markerA636 = googleMap.addMarker(new MarkerOptions()
                .position(A636)
                .title("A636")
                .snippet("37.66135, 126.985275"));
        markerA637 = googleMap.addMarker(new MarkerOptions()
                .position(A637)
                .title("A637")
                .snippet("37.652181, 127.008051"));
        markerA638 = googleMap.addMarker(new MarkerOptions()
                .position(A638)
                .title("A638")
                .snippet("37.686947, 127.02472"));
        markerA639 = googleMap.addMarker(new MarkerOptions()
                .position(A639)
                .title("A639")
                .snippet("37.681839, 127.013547"));
        markerA640 = googleMap.addMarker(new MarkerOptions()
                .position(A640)
                .title("A640")
                .snippet("37.679636, 127.014928"));
        markerA641 = googleMap.addMarker(new MarkerOptions()
                .position(A641)
                .title("A641")
                .snippet("37.678406, 127.015825"));
        markerA642 = googleMap.addMarker(new MarkerOptions()
                .position(A642)
                .title("A642")
                .snippet("37.696275, 127.018672"));
        markerA643 = googleMap.addMarker(new MarkerOptions()
                .position(A643)
                .title("A643")
                .snippet("37.645175, 126.940099"));
        markerA644 = googleMap.addMarker(new MarkerOptions()
                .position(A644)
                .title("A644")
                .snippet("37.660642, 126.993697"));
        markerA645 = googleMap.addMarker(new MarkerOptions()
                .position(A645)
                .title("A645")
                .snippet("37.635392, 127.003028"));
        markerA646 = googleMap.addMarker(new MarkerOptions()
                .position(A646)
                .title("A646")
                .snippet("37.688553, 127.027581"));
        markerA647 = googleMap.addMarker(new MarkerOptions()
                .position(A647)
                .title("A647")
                .snippet("37.621625, 126.994156"));
        markerA648 = googleMap.addMarker(new MarkerOptions()
                .position(A648)
                .title("A648")
                .snippet("37.61783, 126.943296"));
        markerA649 = googleMap.addMarker(new MarkerOptions()
                .position(A649)
                .title("A649")
                .snippet("37.706075, 127.006656"));
        markerA650 = googleMap.addMarker(new MarkerOptions()
                .position(A650)
                .title("A650")
                .snippet("37.675882, 127.026849"));
        markerA651 = googleMap.addMarker(new MarkerOptions()
                .position(A651)
                .title("A651")
                .snippet("37.675882, 127.026849"));
        markerA652 = googleMap.addMarker(new MarkerOptions()
                .position(A652)
                .title("A652")
                .snippet("37.61783, 126.943296"));
        markerA653 = googleMap.addMarker(new MarkerOptions()
                .position(A653)
                .title("A653")
                .snippet("37.68711, 127.024888"));
        markerA654 = googleMap.addMarker(new MarkerOptions()
                .position(A654)
                .title("A654")
                .snippet("37.676457, 127.02785"));
        markerA655 = googleMap.addMarker(new MarkerOptions()
                .position(A655)
                .title("A655")
                .snippet("37.678014, 127.002503"));
        markerA656 = googleMap.addMarker(new MarkerOptions()
                .position(A656)
                .title("A656")
                .snippet("37.611798, 126.988087"));
        markerA657 = googleMap.addMarker(new MarkerOptions()
                .position(A657)
                .title("A657")
                .snippet("37.693758, 127.022465"));
        markerA658 = googleMap.addMarker(new MarkerOptions()
                .position(A658)
                .title("A658")
                .snippet("37.706051, 127.00671"));
        markerA659 = googleMap.addMarker(new MarkerOptions()
                .position(A659)
                .title("A659")
                .snippet("37.706051, 127.00671"));
        markerA660 = googleMap.addMarker(new MarkerOptions()
                .position(A660)
                .title("A660")
                .snippet("37.720386, 127.003608"));
        markerA661 = googleMap.addMarker(new MarkerOptions()
                .position(A661)
                .title("A661")
                .snippet("37.677583, 126.962152"));
        markerA662 = googleMap.addMarker(new MarkerOptions()
                .position(A662)
                .title("A662")
                .snippet("37.677583, 126.962152"));
        markerA663 = googleMap.addMarker(new MarkerOptions()
                .position(A663)
                .title("A663")
                .snippet("37.677583, 126.962152"));
        markerA664 = googleMap.addMarker(new MarkerOptions()
                .position(A664)
                .title("A664")
                .snippet("37.645528, 126.978664"));
        markerA665 = googleMap.addMarker(new MarkerOptions()
                .position(A665)
                .title("A665")
                .snippet("37.646103, 126.980919"));
        markerA666 = googleMap.addMarker(new MarkerOptions()
                .position(A666)
                .title("A666")
                .snippet("37.68739, 127.016222"));
        markerA667 = googleMap.addMarker(new MarkerOptions()
                .position(A667)
                .title("A667")
                .snippet("37.68739, 127.016222"));
        markerA668 = googleMap.addMarker(new MarkerOptions()
                .position(A668)
                .title("A668")
                .snippet("37.68739, 127.016222"));
        markerA669 = googleMap.addMarker(new MarkerOptions()
                .position(A669)
                .title("A669")
                .snippet("37.653326, 126.960019"));
        markerA670 = googleMap.addMarker(new MarkerOptions()
                .position(A670)
                .title("A670")
                .snippet("37.645343, 126.976527"));
        markerA671 = googleMap.addMarker(new MarkerOptions()
                .position(A671)
                .title("A671")
                .snippet("37.657821, 127.002638"));
        markerA672 = googleMap.addMarker(new MarkerOptions()
                .position(A672)
                .title("A672")
                .snippet("37.631456, 126.992119"));
        markerA673 = googleMap.addMarker(new MarkerOptions()
                .position(A673)
                .title("A673")
                .snippet("37.691853, 126.991869"));
        markerA674 = googleMap.addMarker(new MarkerOptions()
                .position(A674)
                .title("A674")
                .snippet("37.64251, 127.001302"));
        markerA675 = googleMap.addMarker(new MarkerOptions()
                .position(A675)
                .title("A675")
                .snippet("37.624608, 127.010027"));
        markerA676 = googleMap.addMarker(new MarkerOptions()
                .position(A676)
                .title("A676")
                .snippet("37.696354, 126.97594"));
        markerA677 = googleMap.addMarker(new MarkerOptions()
                .position(A677)
                .title("A677")
                .snippet("37.691169, 126.995298"));
        markerA678 = googleMap.addMarker(new MarkerOptions()
                .position(A678)
                .title("A678")
                .snippet("37.691211, 126.98973"));
        markerA679 = googleMap.addMarker(new MarkerOptions()
                .position(A679)
                .title("A679")
                .snippet("37.64251, 127.001302"));
        markerA680 = googleMap.addMarker(new MarkerOptions()
                .position(A680)
                .title("A680")
                .snippet("37.675789, 127.026908"));
        markerA681 = googleMap.addMarker(new MarkerOptions()
                .position(A681)
                .title("A681")
                .snippet("37.626822, 126.960686"));
        markerA682 = googleMap.addMarker(new MarkerOptions()
                .position(A682)
                .title("A682")
                .snippet("37.681225, 127.016931"));
        markerA683 = googleMap.addMarker(new MarkerOptions()
                .position(A683)
                .title("A683")
                .snippet("37.684764, 127.033184"));
        markerA684 = googleMap.addMarker(new MarkerOptions()
                .position(A684)
                .title("A684")
                .snippet("37.621625, 126.994156"));
        markerA685 = googleMap.addMarker(new MarkerOptions()
                .position(A685)
                .title("A685")
                .snippet("37.613095, 126.998558"));
        markerA686 = googleMap.addMarker(new MarkerOptions()
                .position(A686)
                .title("A686")
                .snippet("37.639163, 126.945918"));
        markerA687 = googleMap.addMarker(new MarkerOptions()
                .position(A687)
                .title("A687")
                .snippet("37.61783, 126.943296"));
        markerA688 = googleMap.addMarker(new MarkerOptions()
                .position(A688)
                .title("A688")
                .snippet("37.648084, 126.971515"));
        markerA689 = googleMap.addMarker(new MarkerOptions()
                .position(A689)
                .title("A689")
                .snippet("37.645125, 127.000869"));
        markerA690 = googleMap.addMarker(new MarkerOptions()
                .position(A690)
                .title("A690")
                .snippet("37.670953, 127.004772"));
        markerA691 = googleMap.addMarker(new MarkerOptions()
                .position(A691)
                .title("A691")
                .snippet("37.650649, 127.010123"));
        markerA692 = googleMap.addMarker(new MarkerOptions()
                .position(A692)
                .title("A692")
                .snippet("37.649662, 127.003167"));
        markerA693 = googleMap.addMarker(new MarkerOptions()
                .position(A693)
                .title("A693")
                .snippet("37.672728, 127.000544"));
        markerA694 = googleMap.addMarker(new MarkerOptions()
                .position(A694)
                .title("A694")
                .snippet("37.662458, 126.992761"));
        markerA695 = googleMap.addMarker(new MarkerOptions()
                .position(A695)
                .title("A695")
                .snippet("37.676928, 127.0031"));
        markerA696 = googleMap.addMarker(new MarkerOptions()
                .position(A696)
                .title("A696")
                .snippet("37.688759, 127.032599"));
        markerA697 = googleMap.addMarker(new MarkerOptions()
                .position(A697)
                .title("A697")
                .snippet("37.676508, 127.020628"));
        markerA698 = googleMap.addMarker(new MarkerOptions()
                .position(A698)
                .title("A698")
                .snippet("37.6755, 127.022728"));
        markerA699 = googleMap.addMarker(new MarkerOptions()
                .position(A699)
                .title("A699")
                .snippet("37.675697, 127.026436"));
        markerA700 = googleMap.addMarker(new MarkerOptions()
                .position(A700)
                .title("A700")
                .snippet("37.676561, 127.028044"));
        markerA701 = googleMap.addMarker(new MarkerOptions()
                .position(A701)
                .title("A701")
                .snippet("37.645125, 127.000869"));
        markerA702 = googleMap.addMarker(new MarkerOptions()
                .position(A702)
                .title("A702")
                .snippet("37.670953, 127.004772"));
        markerA703 = googleMap.addMarker(new MarkerOptions()
                .position(A703)
                .title("A703")
                .snippet("37.650649, 127.010123"));
        markerA704 = googleMap.addMarker(new MarkerOptions()
                .position(A704)
                .title("A704")
                .snippet("37.649662, 127.003167"));
        markerA705 = googleMap.addMarker(new MarkerOptions()
                .position(A705)
                .title("A705")
                .snippet("37.662458, 126.992761"));
        markerA706 = googleMap.addMarker(new MarkerOptions()
                .position(A706)
                .title("A706")
                .snippet("37.662458, 126.992761"));
        markerA707 = googleMap.addMarker(new MarkerOptions()
                .position(A707)
                .title("A707")
                .snippet("37.676928, 127.0031"));
        markerA708 = googleMap.addMarker(new MarkerOptions()
                .position(A708)
                .title("A708")
                .snippet("37.688759, 127.032599"));
        markerA709 = googleMap.addMarker(new MarkerOptions()
                .position(A709)
                .title("A709")
                .snippet("37.676508, 127.020628"));
        markerA710 = googleMap.addMarker(new MarkerOptions()
                .position(A710)
                .title("A710")
                .snippet("37.6755, 127.022728"));
        markerA711 = googleMap.addMarker(new MarkerOptions()
                .position(A711)
                .title("A711")
                .snippet("37.675697, 127.026436"));
        markerA712 = googleMap.addMarker(new MarkerOptions()
                .position(A712)
                .title("A712")
                .snippet("37.676561, 127.028044"));
        markerA713 = googleMap.addMarker(new MarkerOptions()
                .position(A713)
                .title("A713")
                .snippet("37.665589, 126.998533"));
        markerA714 = googleMap.addMarker(new MarkerOptions()
                .position(A714)
                .title("A714")
                .snippet("37.635478, 126.976464"));
        markerA715 = googleMap.addMarker(new MarkerOptions()
                .position(A715)
                .title("A715")
                .snippet("37.618347, 126.943672"));
        markerA716 = googleMap.addMarker(new MarkerOptions()
                .position(A716)
                .title("A716")
                .snippet("37.647352, 126.941802"));
        markerA717 = googleMap.addMarker(new MarkerOptions()
                .position(A717)
                .title("A717")
                .snippet("37.720806, 127.020278"));
        markerA718 = googleMap.addMarker(new MarkerOptions()
                .position(A718)
                .title("A718")
                .snippet("37.61783, 126.943296"));
        markerA719 = googleMap.addMarker(new MarkerOptions()
                .position(A719)
                .title("A719")
                .snippet("37.611798, 126.988087"));
        markerA720 = googleMap.addMarker(new MarkerOptions()
                .position(A720)
                .title("A720")
                .snippet("37.624236, 126.990128"));
        markerA721 = googleMap.addMarker(new MarkerOptions()
                .position(A721)
                .title("A721")
                .snippet("37.659108, 126.960687"));
        markerA722 = googleMap.addMarker(new MarkerOptions()
                .position(A722)
                .title("A722")
                .snippet("37.655825, 126.968314"));
        markerA723 = googleMap.addMarker(new MarkerOptions()
                .position(A723)
                .title("A723")
                .snippet("37.634031, 126.969264"));
        markerA724 = googleMap.addMarker(new MarkerOptions()
                .position(A724)
                .title("A724")
                .snippet("37.655825, 126.968314"));
        markerA725 = googleMap.addMarker(new MarkerOptions()
                .position(A725)
                .title("A725")
                .snippet("37.642481, 126.950892"));
        markerA726 = googleMap.addMarker(new MarkerOptions()
                .position(A726)
                .title("A726")
                .snippet("37.628831, 126.959006"));
        markerA727 = googleMap.addMarker(new MarkerOptions()
                .position(A727)
                .title("A727")
                .snippet("37.642481, 126.950892"));
        markerA728 = googleMap.addMarker(new MarkerOptions()
                .position(A728)
                .title("A728")
                .snippet("37.628831, 126.959006"));
        markerA729 = googleMap.addMarker(new MarkerOptions()
                .position(A729)
                .title("A729")
                .snippet("37.688069, 127.023831"));
        markerA730 = googleMap.addMarker(new MarkerOptions()
                .position(A730)
                .title("A730")
                .snippet("37.688069, 127.023831"));
        markerA731 = googleMap.addMarker(new MarkerOptions()
                .position(A731)
                .title("A731")
                .snippet("37.688069, 127.023831"));
        markerA732 = googleMap.addMarker(new MarkerOptions()
                .position(A732)
                .title("A732")
                .snippet("37.690042, 127.021"));
        markerA733 = googleMap.addMarker(new MarkerOptions()
                .position(A733)
                .title("A733")
                .snippet("37.635736, 126.976283"));
        markerA734 = googleMap.addMarker(new MarkerOptions()
                .position(A734)
                .title("A734")
                .snippet("37.651364, 126.981753"));
        markerA735 = googleMap.addMarker(new MarkerOptions()
                .position(A735)
                .title("A735")
                .snippet("37.651364, 126.981753"));
        markerA736 = googleMap.addMarker(new MarkerOptions()
                .position(A736)
                .title("A736")
                .snippet("37.659108, 126.960687"));
        markerA737 = googleMap.addMarker(new MarkerOptions()
                .position(A737)
                .title("A737")
                .snippet("37.658042, 126.947214"));
        markerA738 = googleMap.addMarker(new MarkerOptions()
                .position(A738)
                .title("A738")
                .snippet("37.654908, 126.950444"));
        markerA739 = googleMap.addMarker(new MarkerOptions()
                .position(A739)
                .title("A739")
                .snippet("37.658051, 126.947169"));
        markerA740 = googleMap.addMarker(new MarkerOptions()
                .position(A740)
                .title("A740")
                .snippet("37.706051, 127.00671"));
        markerA741 = googleMap.addMarker(new MarkerOptions()
                .position(A741)
                .title("A741")
                .snippet("37.706051, 127.00671"));
        markerA742 = googleMap.addMarker(new MarkerOptions()
                .position(A742)
                .title("A742")
                .snippet("37.706051, 127.00671"));
        markerA743 = googleMap.addMarker(new MarkerOptions()
                .position(A743)
                .title("A743")
                .snippet("37.673693, 126.984277"));
        markerA744 = googleMap.addMarker(new MarkerOptions()
                .position(A744)
                .title("A744")
                .snippet("37.673693, 126.984277"));
        markerA745 = googleMap.addMarker(new MarkerOptions()
                .position(A745)
                .title("A745")
                .snippet("37.673693, 126.984277"));
        markerA746 = googleMap.addMarker(new MarkerOptions()
                .position(A746)
                .title("A746")
                .snippet("37.659108, 126.960687"));
        markerA747 = googleMap.addMarker(new MarkerOptions()
                .position(A747)
                .title("A747")
                .snippet("37.654661, 126.960508"));
        markerA748 = googleMap.addMarker(new MarkerOptions()
                .position(A748)
                .title("A748")
                .snippet("37.654661, 126.960508"));
        markerA749 = googleMap.addMarker(new MarkerOptions()
                .position(A749)
                .title("A749")
                .snippet("37.654908, 126.950444"));
        markerA750 = googleMap.addMarker(new MarkerOptions()
                .position(A750)
                .title("A750")
                .snippet("37.655358, 126.953889"));
        markerA751 = googleMap.addMarker(new MarkerOptions()
                .position(A751)
                .title("A751")
                .snippet("37.655358, 126.953889"));
        markerA752 = googleMap.addMarker(new MarkerOptions()
                .position(A752)
                .title("A752")
                .snippet("37.631456, 126.992119"));
        markerA753 = googleMap.addMarker(new MarkerOptions()
                .position(A753)
                .title("A753")
                .snippet("37.655358, 126.953889"));
        markerA754 = googleMap.addMarker(new MarkerOptions()
                .position(A754)
                .title("A754")
                .snippet("37.631928, 126.987075"));
        markerA755 = googleMap.addMarker(new MarkerOptions()
                .position(A755)
                .title("A755")
                .snippet("37.726931, 127.0201"));
        markerA756 = googleMap.addMarker(new MarkerOptions()
                .position(A756)
                .title("A756")
                .snippet("37.626386, 126.994422"));
        markerA757 = googleMap.addMarker(new MarkerOptions()
                .position(A757)
                .title("A757")
                .snippet("37.648084, 126.971515"));
        markerA758 = googleMap.addMarker(new MarkerOptions()
                .position(A758)
                .title("A758")
                .snippet("37.730524, 127.020791"));
        markerA759 = googleMap.addMarker(new MarkerOptions()
                .position(A759)
                .title("A759")
                .snippet("37.726642, 127.026731"));
        markerA760 = googleMap.addMarker(new MarkerOptions()
                .position(A760)
                .title("A760")
                .snippet("37.685656, 127.0204"));
        markerA761 = googleMap.addMarker(new MarkerOptions()
                .position(A761)
                .title("A761")
                .snippet("37.685778, 127.030484"));
        markerA762 = googleMap.addMarker(new MarkerOptions()
                .position(A762)
                .title("A762")
                .snippet("37.685656, 127.0204"));
        markerA763 = googleMap.addMarker(new MarkerOptions()
                .position(A763)
                .title("A763")
                .snippet("37.683728, 127.031817"));
        markerA764 = googleMap.addMarker(new MarkerOptions()
                .position(A764)
                .title("A764")
                .snippet("37.706051, 127.00671"));
        markerA765 = googleMap.addMarker(new MarkerOptions()
                .position(A765)
                .title("A765")
                .snippet("37.685656, 127.0204"));
        markerA766 = googleMap.addMarker(new MarkerOptions()
                .position(A766)
                .title("A766")
                .snippet("37.685074, 127.030368"));
        markerA767 = googleMap.addMarker(new MarkerOptions()
                .position(A767)
                .title("A767")
                .snippet("37.645676, 126.970211"));
        markerA768 = googleMap.addMarker(new MarkerOptions()
                .position(A768)
                .title("A768")
                .snippet("37.706051, 127.00671"));
        markerA769 = googleMap.addMarker(new MarkerOptions()
                .position(A769)
                .title("A769")
                .snippet("37.728369, 127.020694"));
        markerA770 = googleMap.addMarker(new MarkerOptions()
                .position(A770)
                .title("A770")
                .snippet("37.706051, 127.00671"));
        markerA771 = googleMap.addMarker(new MarkerOptions()
                .position(A771)
                .title("A771")
                .snippet("37.618308, 126.939142"));
        markerA772 = googleMap.addMarker(new MarkerOptions()
                .position(A772)
                .title("A772")
                .snippet("37.618308, 126.939142"));
        markerA773 = googleMap.addMarker(new MarkerOptions()
                .position(A773)
                .title("A773")
                .snippet("37.618308, 126.939142"));
        markerA774 = googleMap.addMarker(new MarkerOptions()
                .position(A774)
                .title("A774")
                .snippet("37.656472, 126.969078"));
        markerA775 = googleMap.addMarker(new MarkerOptions()
                .position(A775)
                .title("A775")
                .snippet("37.645697, 126.977882"));
        markerA776 = googleMap.addMarker(new MarkerOptions()
                .position(A776)
                .title("A776")
                .snippet("37.645697, 126.977882"));
        markerA777 = googleMap.addMarker(new MarkerOptions()
                .position(A777)
                .title("A777")
                .snippet("37.645697, 126.977882"));
        markerA778 = googleMap.addMarker(new MarkerOptions()
                .position(A778)
                .title("A778")
                .snippet("37.719993, 127.027633"));
        markerA779 = googleMap.addMarker(new MarkerOptions()
                .position(A779)
                .title("A779")
                .snippet("37.719298, 127.026349"));
        markerA780 = googleMap.addMarker(new MarkerOptions()
                .position(A780)
                .title("A780")
                .snippet("37.719298, 127.026349"));
        markerA781 = googleMap.addMarker(new MarkerOptions()
                .position(A781)
                .title("A781")
                .snippet("37.688389, 127.027048"));
        markerA782 = googleMap.addMarker(new MarkerOptions()
                .position(A782)
                .title("A782")
                .snippet("37.688589, 127.027592"));
        markerA783 = googleMap.addMarker(new MarkerOptions()
                .position(A783)
                .title("A783")
                .snippet("37.688589, 127.027592"));
        markerA784 = googleMap.addMarker(new MarkerOptions()
                .position(A784)
                .title("A784")
                .snippet("37.700731, 127.015636"));
        markerA785 = googleMap.addMarker(new MarkerOptions()
                .position(A785)
                .title("A785")
                .snippet("37.645676, 126.970211"));
        markerA786 = googleMap.addMarker(new MarkerOptions()
                .position(A786)
                .title("A786")
                .snippet("37.652181, 127.008051"));
        markerA787 = googleMap.addMarker(new MarkerOptions()
                .position(A787)
                .title("A787")
                .snippet("37.651073, 127.010265"));
        markerA788 = googleMap.addMarker(new MarkerOptions()
                .position(A788)
                .title("A788")
                .snippet("37.645533, 126.978272"));
        markerA789 = googleMap.addMarker(new MarkerOptions()
                .position(A789)
                .title("A789")
                .snippet("37.656472, 126.969078"));
        markerA790 = googleMap.addMarker(new MarkerOptions()
                .position(A790)
                .title("A790")
                .snippet("37.721565, 127.003259"));
        markerA791 = googleMap.addMarker(new MarkerOptions()
                .position(A791)
                .title("A791")
                .snippet("37.651073, 127.010265"));
        markerA792 = googleMap.addMarker(new MarkerOptions()
                .position(A792)
                .title("A792")
                .snippet("37.7234, 127.003761"));
        markerA793 = googleMap.addMarker(new MarkerOptions()
                .position(A793)
                .title("A793")
                .snippet("37.645374, 127.00004"));
        markerA794 = googleMap.addMarker(new MarkerOptions()
                .position(A794)
                .title("A794")
                .snippet("37.629176, 126.958747"));
        markerA795 = googleMap.addMarker(new MarkerOptions()
                .position(A795)
                .title("A795")
                .snippet("37.706051, 127.00671"));
        markerA796 = googleMap.addMarker(new MarkerOptions()
                .position(A796)
                .title("A796")
                .snippet("37.706051, 127.00671"));
        markerA797 = googleMap.addMarker(new MarkerOptions()
                .position(A797)
                .title("A797")
                .snippet("37.688553, 127.027581"));
        markerA798 = googleMap.addMarker(new MarkerOptions()
                .position(A798)
                .title("A798")
                .snippet("37.665589, 126.998533"));
        markerA799 = googleMap.addMarker(new MarkerOptions()
                .position(A799)
                .title("A799")
                .snippet("37.6679, 126.998853"));
        markerA800 = googleMap.addMarker(new MarkerOptions()
                .position(A800)
                .title("A800")
                .snippet("37.6679, 126.998853"));
        markerA801 = googleMap.addMarker(new MarkerOptions()
                .position(A801)
                .title("A801")
                .snippet("37.732442, 127.023569"));
        markerA802 = googleMap.addMarker(new MarkerOptions()
                .position(A802)
                .title("A802")
                .snippet("37.732442, 127.023569"));
        markerA803 = googleMap.addMarker(new MarkerOptions()
                .position(A803)
                .title("A803")
                .snippet("37.710983, 127.023226"));
        markerA804 = googleMap.addMarker(new MarkerOptions()
                .position(A804)
                .title("A804")
                .snippet("37.665589, 126.998533"));
        markerA805 = googleMap.addMarker(new MarkerOptions()
                .position(A805)
                .title("A805")
                .snippet("37.665589, 126.998533"));
        markerA806 = googleMap.addMarker(new MarkerOptions()
                .position(A806)
                .title("A806")
                .snippet("37.665589, 126.998533"));
        markerA807 = googleMap.addMarker(new MarkerOptions()
                .position(A807)
                .title("A807")
                .snippet("37.658051, 126.947169"));
        markerA808 = googleMap.addMarker(new MarkerOptions()
                .position(A808)
                .title("A808")
                .snippet("37.65806, 126.947158"));
        markerA809 = googleMap.addMarker(new MarkerOptions()
                .position(A809)
                .title("A809")
                .snippet("37.645374, 127.00004"));
        markerA810 = googleMap.addMarker(new MarkerOptions()
                .position(A810)
                .title("A810")
                .snippet("37.706051, 127.00671"));
        markerA811 = googleMap.addMarker(new MarkerOptions()
                .position(A811)
                .title("A811")
                .snippet("37.645676, 126.970211"));
        markerA812 = googleMap.addMarker(new MarkerOptions()
                .position(A812)
                .title("A812")
                .snippet("37.657789, 127.002181"));
        markerA813 = googleMap.addMarker(new MarkerOptions()
                .position(A813)
                .title("A813")
                .snippet("37.717342, 126.984122"));
        markerA814 = googleMap.addMarker(new MarkerOptions()
                .position(A814)
                .title("A814")
                .snippet("37.623802, 127.007364"));
        markerA815 = googleMap.addMarker(new MarkerOptions()
                .position(A815)
                .title("A815")
                .snippet("37.647213, 126.941294"));
        markerA816 = googleMap.addMarker(new MarkerOptions()
                .position(A816)
                .title("A816")
                .snippet("37.677139, 127.036014"));
        markerA817 = googleMap.addMarker(new MarkerOptions()
                .position(A817)
                .title("A817")
                .snippet("37.649025, 126.98215"));
        markerA818 = googleMap.addMarker(new MarkerOptions()
                .position(A818)
                .title("A818")
                .snippet("37.649025, 126.98215"));
        markerA819 = googleMap.addMarker(new MarkerOptions()
                .position(A819)
                .title("A819")
                .snippet("37.693758, 127.022465"));
        markerA820 = googleMap.addMarker(new MarkerOptions()
                .position(A820)
                .title("A820")
                .snippet("37.662458, 126.992761"));
        markerA821 = googleMap.addMarker(new MarkerOptions()
                .position(A821)
                .title("A821")
                .snippet("37.722553, 127.010392"));
        markerA822 = googleMap.addMarker(new MarkerOptions()
                .position(A822)
                .title("A822")
                .snippet("37.655778, 126.992839"));
        markerA823 = googleMap.addMarker(new MarkerOptions()
                .position(A823)
                .title("A823")
                .snippet("37.665589, 126.998533"));
        markerA824 = googleMap.addMarker(new MarkerOptions()
                .position(A824)
                .title("A824")
                .snippet("37.711482, 126.986382"));
        markerA825 = googleMap.addMarker(new MarkerOptions()
                .position(A825)
                .title("A825")
                .snippet("37.678085, 127.002628"));
        markerA826 = googleMap.addMarker(new MarkerOptions()
                .position(A826)
                .title("A826")
                .snippet("37.622375, 126.974342"));
        markerA827 = googleMap.addMarker(new MarkerOptions()
                .position(A827)
                .title("A827")
                .snippet("37.664011, 126.972011"));
        markerA828 = googleMap.addMarker(new MarkerOptions()
                .position(A828)
                .title("A828")
                .snippet("37.663961, 126.971997"));
        markerA829 = googleMap.addMarker(new MarkerOptions()
                .position(A829)
                .title("A829")
                .snippet("37.676159, 126.964213"));
        markerA830 = googleMap.addMarker(new MarkerOptions()
                .position(A830)
                .title("A830")
                .snippet("37.725731, 127.020714"));
        markerA831 = googleMap.addMarker(new MarkerOptions()
                .position(A831)
                .title("A831")
                .snippet("37.677539, 127.032314"));
        markerA832 = googleMap.addMarker(new MarkerOptions()
                .position(A832)
                .title("A832")
                .snippet("37.677506, 127.032319"));
        markerA833 = googleMap.addMarker(new MarkerOptions()
                .position(A833)
                .title("A833")
                .snippet("37.677506, 127.032319"));
        markerA834 = googleMap.addMarker(new MarkerOptions()
                .position(A834)
                .title("A834")
                .snippet("37.652614, 126.955586"));
        markerA835 = googleMap.addMarker(new MarkerOptions()
                .position(A835)
                .title("A835")
                .snippet("37.642508, 126.979333"));
        markerA836 = googleMap.addMarker(new MarkerOptions()
                .position(A836)
                .title("A836")
                .snippet("37.6218, 126.994081"));
        markerA837 = googleMap.addMarker(new MarkerOptions()
                .position(A837)
                .title("A837")
                .snippet("37.652614, 126.955586"));
        markerA838 = googleMap.addMarker(new MarkerOptions()
                .position(A838)
                .title("A838")
                .snippet("37.684811, 127.029789"));
        markerA839 = googleMap.addMarker(new MarkerOptions()
                .position(A839)
                .title("A839")
                .snippet("37.665247, 126.975808"));
        markerA840 = googleMap.addMarker(new MarkerOptions()
                .position(A840)
                .title("A840")
                .snippet("37.662458, 126.992761"));
        markerA841 = googleMap.addMarker(new MarkerOptions()
                .position(A841)
                .title("A841")
                .snippet("37.662458, 126.992761"));
        markerA842 = googleMap.addMarker(new MarkerOptions()
                .position(A842)
                .title("A842")
                .snippet("37.662458, 126.992761"));
        markerA843 = googleMap.addMarker(new MarkerOptions()
                .position(A843)
                .title("A843")
                .snippet("37.649258, 126.9817"));
        markerA844 = googleMap.addMarker(new MarkerOptions()
                .position(A844)
                .title("A844")
                .snippet("37.641684, 127.001698"));
        markerA845 = googleMap.addMarker(new MarkerOptions()
                .position(A845)
                .title("A845")
                .snippet("37.6218, 126.994081"));
        markerA846 = googleMap.addMarker(new MarkerOptions()
                .position(A846)
                .title("A846")
                .snippet("37.727361, 127.026275"));
        markerA847 = googleMap.addMarker(new MarkerOptions()
                .position(A847)
                .title("A847")
                .snippet("37.663958, 126.968947"));
        markerA848 = googleMap.addMarker(new MarkerOptions()
                .position(A848)
                .title("A848")
                .snippet("37.727361, 127.026275"));
        markerA849 = googleMap.addMarker(new MarkerOptions()
                .position(A849)
                .title("A849")
                .snippet("37.727143, 127.026054"));
        markerA850 = googleMap.addMarker(new MarkerOptions()
                .position(A850)
                .title("A850")
                .snippet("37.624444, 126.981111"));
        markerA851 = googleMap.addMarker(new MarkerOptions()
                .position(A851)
                .title("A851")
                .snippet("37.633205, 127.007987"));
        markerA852 = googleMap.addMarker(new MarkerOptions()
                .position(A852)
                .title("A852")
                .snippet("37.703944, 127.028058"));
        markerA853 = googleMap.addMarker(new MarkerOptions()
                .position(A853)
                .title("A853")
                .snippet("37.637735, 126.943445"));
        markerA854 = googleMap.addMarker(new MarkerOptions()
                .position(A854)
                .title("A854")
                .snippet("37.637735, 126.943445"));
        markerA855 = googleMap.addMarker(new MarkerOptions()
                .position(A855)
                .title("A855")
                .snippet("37.703944, 127.028058"));
        markerA856 = googleMap.addMarker(new MarkerOptions()
                .position(A856)
                .title("A856")
                .snippet("37.636353, 126.978058"));
        markerA857 = googleMap.addMarker(new MarkerOptions()
                .position(A857)
                .title("A857")
                .snippet("37.632997, 127.007953"));
        markerA858 = googleMap.addMarker(new MarkerOptions()
                .position(A858)
                .title("A858")
                .snippet("37.720508, 127.003539"));
        markerA859 = googleMap.addMarker(new MarkerOptions()
                .position(A859)
                .title("A859")
                .snippet("37.659108, 126.960687"));
        markerA860 = googleMap.addMarker(new MarkerOptions()
                .position(A860)
                .title("A860")
                .snippet("37.668272, 127.025153"));
        markerA861 = googleMap.addMarker(new MarkerOptions()
                .position(A861)
                .title("A861")
                .snippet("37.684731, 127.033189"));
        markerA862 = googleMap.addMarker(new MarkerOptions()
                .position(A862)
                .title("A862")
                .snippet("37.712319, 126.989"));
        markerA863 = googleMap.addMarker(new MarkerOptions()
                .position(A863)
                .title("A863")
                .snippet("37.722564, 126.994208"));
        markerA864 = googleMap.addMarker(new MarkerOptions()
                .position(A864)
                .title("A864")
                .snippet("37.687883, 127.030308"));
        markerA865 = googleMap.addMarker(new MarkerOptions()
                .position(A865)
                .title("A865")
                .snippet("37.645103, 126.940145"));
        markerA866 = googleMap.addMarker(new MarkerOptions()
                .position(A866)
                .title("A866")
                .snippet("37.722564, 126.994208"));
        markerA867 = googleMap.addMarker(new MarkerOptions()
                .position(A867)
                .title("A867")
                .snippet("37.713012, 126.99143"));
        markerA868 = googleMap.addMarker(new MarkerOptions()
                .position(A868)
                .title("A868")
                .snippet("37.722596, 126.994216"));
        markerA869 = googleMap.addMarker(new MarkerOptions()
                .position(A869)
                .title("A869")
                .snippet("37.687931, 127.030264"));
        markerA870 = googleMap.addMarker(new MarkerOptions()
                .position(A870)
                .title("A870")
                .snippet("37.645103, 126.940145"));
        markerA871 = googleMap.addMarker(new MarkerOptions()
                .position(A871)
                .title("A871")
                .snippet("37.687883, 127.030308"));
        markerA872 = googleMap.addMarker(new MarkerOptions()
                .position(A872)
                .title("A872")
                .snippet("37.705103, 127.028253"));
        markerA873 = googleMap.addMarker(new MarkerOptions()
                .position(A873)
                .title("A873")
                .snippet("37.705103, 127.028253"));
        markerA874 = googleMap.addMarker(new MarkerOptions()
                .position(A874)
                .title("A874")
                .snippet("37.631767, 126.973417"));
        markerA875 = googleMap.addMarker(new MarkerOptions()
                .position(A875)
                .title("A875")
                .snippet("37.626522, 126.960234"));
        markerA876 = googleMap.addMarker(new MarkerOptions()
                .position(A876)
                .title("A876")
                .snippet("37.612686, 126.981881"));
        markerA877 = googleMap.addMarker(new MarkerOptions()
                .position(A877)
                .title("A877")
                .snippet("37.705103, 127.028253"));
        markerA878 = googleMap.addMarker(new MarkerOptions()
                .position(A878)
                .title("A878")
                .snippet("37.661194, 127.015939"));
        markerA879 = googleMap.addMarker(new MarkerOptions()
                .position(A879)
                .title("A879")
                .snippet("37.618836, 126.953875"));
        markerA880 = googleMap.addMarker(new MarkerOptions()
                .position(A880)
                .title("A880")
                .snippet("37.662458, 126.992761"));
        markerA881 = googleMap.addMarker(new MarkerOptions()
                .position(A881)
                .title("A881")
                .snippet("37.665589, 126.998533"));
        markerA882 = googleMap.addMarker(new MarkerOptions()
                .position(A882)
                .title("A882")
                .snippet("37.706325, 127.005067"));
        markerA883 = googleMap.addMarker(new MarkerOptions()
                .position(A883)
                .title("A883")
                .snippet("37.707047, 126.978844"));
        markerA884 = googleMap.addMarker(new MarkerOptions()
                .position(A884)
                .title("A884")
                .snippet("37.707039, 126.979003"));
        markerA885 = googleMap.addMarker(new MarkerOptions()
                .position(A885)
                .title("A885")
                .snippet("37.687933, 127.030508"));
        markerA886 = googleMap.addMarker(new MarkerOptions()
                .position(A886)
                .title("A886")
                .snippet("37.627683, 126.976794"));
        markerA887 = googleMap.addMarker(new MarkerOptions()
                .position(A887)
                .title("A887")
                .snippet("37.655981, 126.948203"));
        markerA888 = googleMap.addMarker(new MarkerOptions()
                .position(A888)
                .title("A888")
                .snippet("37.614914, 126.958378"));
        markerA889 = googleMap.addMarker(new MarkerOptions()
                .position(A889)
                .title("A889")
                .snippet("37.618233, 126.95935"));
        markerA890 = googleMap.addMarker(new MarkerOptions()
                .position(A890)
                .title("A890")
                .snippet("37.694408, 127.019861"));
        markerA891 = googleMap.addMarker(new MarkerOptions()
                .position(A891)
                .title("A891")
                .snippet("37.694408, 127.019861"));
        markerA892 = googleMap.addMarker(new MarkerOptions()
                .position(A892)
                .title("A892")
                .snippet("37.694408, 127.019861"));
        markerA893 = googleMap.addMarker(new MarkerOptions()
                .position(A893)
                .title("A893")
                .snippet("37.652683, 126.955529"));
        markerA894 = googleMap.addMarker(new MarkerOptions()
                .position(A894)
                .title("A894")
                .snippet("37.671966, 126.957753"));
        markerA895 = googleMap.addMarker(new MarkerOptions()
                .position(A895)
                .title("A895")
                .snippet("37.61783, 126.943296"));
        markerA896 = googleMap.addMarker(new MarkerOptions()
                .position(A896)
                .title("A896")
                .snippet("37.645175, 126.940099"));
        markerA897 = googleMap.addMarker(new MarkerOptions()
                .position(A897)
                .title("A897")
                .snippet("37.629065, 127.001085"));
        markerA898 = googleMap.addMarker(new MarkerOptions()
                .position(A898)
                .title("A898")
                .snippet("37.629065, 127.001085"));
        markerA899 = googleMap.addMarker(new MarkerOptions()
                .position(A899)
                .title("A899")
                .snippet("37.630879, 127.003531"));
        markerA900 = googleMap.addMarker(new MarkerOptions()
                .position(A900)
                .title("A900")
                .snippet("37.730427, 127.007133"));
        markerA901 = googleMap.addMarker(new MarkerOptions()
                .position(A901)
                .title("A901")
                .snippet("37.623912, 126.990596"));
        markerA902 = googleMap.addMarker(new MarkerOptions()
                .position(A902)
                .title("A902")
                .snippet("37.691878, 126.991842"));
        markerA903 = googleMap.addMarker(new MarkerOptions()
                .position(A903)
                .title("A903")
                .snippet("37.691878, 126.991842"));
        markerA904 = googleMap.addMarker(new MarkerOptions()
                .position(A904)
                .title("A904")
                .snippet("37.711622, 126.977811"));
        markerA905 = googleMap.addMarker(new MarkerOptions()
                .position(A905)
                .title("A905")
                .snippet("37.711622, 126.977811"));
        markerA906 = googleMap.addMarker(new MarkerOptions()
                .position(A906)
                .title("A906")
                .snippet("37.694209, 127.019341"));
        markerA907 = googleMap.addMarker(new MarkerOptions()
                .position(A907)
                .title("A907")
                .snippet("37.720533, 127.003522"));
        markerA908 = googleMap.addMarker(new MarkerOptions()
                .position(A908)
                .title("A908")
                .snippet("37.711482, 126.986382"));
        markerA909 = googleMap.addMarker(new MarkerOptions()
                .position(A909)
                .title("A909")
                .snippet("37.694209, 127.019341"));
        markerA910 = googleMap.addMarker(new MarkerOptions()
                .position(A910)
                .title("A910")
                .snippet("37.685417, 127.013464"));
        markerA911 = googleMap.addMarker(new MarkerOptions()
                .position(A911)
                .title("A911")
                .snippet("37.703197, 127.029267"));
        markerA912 = googleMap.addMarker(new MarkerOptions()
                .position(A912)
                .title("A912")
                .snippet("37.704601, 127.024069"));
        markerA913 = googleMap.addMarker(new MarkerOptions()
                .position(A913)
                .title("A913")
                .snippet("37.736692, 127.028789"));
        markerA914 = googleMap.addMarker(new MarkerOptions()
                .position(A914)
                .title("A914")
                .snippet("37.676375, 127.016908"));
        markerA915 = googleMap.addMarker(new MarkerOptions()
                .position(A915)
                .title("A915")
                .snippet("37.659108, 126.960687"));
        markerA916 = googleMap.addMarker(new MarkerOptions()
                .position(A916)
                .title("A916")
                .snippet("37.70323, 127.029258"));
        markerA917 = googleMap.addMarker(new MarkerOptions()
                .position(A917)
                .title("A917")
                .snippet("37.720178, 127.025331"));
        markerA918 = googleMap.addMarker(new MarkerOptions()
                .position(A918)
                .title("A918")
                .snippet("37.647775, 127.008547"));
        markerA919 = googleMap.addMarker(new MarkerOptions()
                .position(A919)
                .title("A919")
                .snippet("37.694203, 127.019311"));
        markerA920 = googleMap.addMarker(new MarkerOptions()
                .position(A920)
                .title("A920")
                .snippet("37.687431, 126.964272"));
        markerA921 = googleMap.addMarker(new MarkerOptions()
                .position(A921)
                .title("A921")
                .snippet("37.668396, 126.961577"));
        markerA922 = googleMap.addMarker(new MarkerOptions()
                .position(A922)
                .title("A922")
                .snippet("37.696825, 127.017625"));
        markerA923 = googleMap.addMarker(new MarkerOptions()
                .position(A923)
                .title("A923")
                .snippet("37.696825, 127.017625"));
        markerA924 = googleMap.addMarker(new MarkerOptions()
                .position(A924)
                .title("A924")
                .snippet("37.629065, 127.001085"));
        markerA925 = googleMap.addMarker(new MarkerOptions()
                .position(A925)
                .title("A925")
                .snippet("37.682214, 127.042331"));
        markerA926 = googleMap.addMarker(new MarkerOptions()
                .position(A926)
                .title("A926")
                .snippet("37.737117, 127.028536"));
        markerA927 = googleMap.addMarker(new MarkerOptions()
                .position(A927)
                .title("A927")
                .snippet("37.650402, 126.966599"));
        markerA928 = googleMap.addMarker(new MarkerOptions()
                .position(A928)
                .title("A928")
                .snippet("37.687965, 127.030262"));
        markerA929 = googleMap.addMarker(new MarkerOptions()
                .position(A929)
                .title("A929")
                .snippet("37.645242, 126.974217"));
        markerA930 = googleMap.addMarker(new MarkerOptions()
                .position(A930)
                .title("A930")
                .snippet("37.637844, 126.946491"));
        markerA931 = googleMap.addMarker(new MarkerOptions()
                .position(A931)
                .title("A931")
                .snippet("37.6859, 127.021328"));
        markerA932 = googleMap.addMarker(new MarkerOptions()
                .position(A932)
                .title("A932")
                .snippet("37.6859, 127.021328"));
        markerA933 = googleMap.addMarker(new MarkerOptions()
                .position(A933)
                .title("A933")
                .snippet("37.727143, 127.026054"));
        markerA934 = googleMap.addMarker(new MarkerOptions()
                .position(A934)
                .title("A934")
                .snippet("37.645486, 127.000083"));
        markerA935 = googleMap.addMarker(new MarkerOptions()
                .position(A935)
                .title("A935")
                .snippet("37.619228, 126.990711"));
        markerA936 = googleMap.addMarker(new MarkerOptions()
                .position(A936)
                .title("A936")
                .snippet("37.68315, 127.017083"));
        markerA937 = googleMap.addMarker(new MarkerOptions()
                .position(A937)
                .title("A937")
                .snippet("37.721883, 127.031989"));
        markerA938 = googleMap.addMarker(new MarkerOptions()
                .position(A938)
                .title("A938")
                .snippet("37.722408, 127.010392"));
        markerA939 = googleMap.addMarker(new MarkerOptions()
                .position(A939)
                .title("A939")
                .snippet("37.69665, 127.001414"));
        markerA940 = googleMap.addMarker(new MarkerOptions()
                .position(A940)
                .title("A940")
                .snippet("37.722408, 127.010392"));
        markerA941 = googleMap.addMarker(new MarkerOptions()
                .position(A941)
                .title("A941")
                .snippet("37.69665, 127.001414"));
        markerA942 = googleMap.addMarker(new MarkerOptions()
                .position(A942)
                .title("A942")
                .snippet("37.721565, 127.003259"));
        markerA943 = googleMap.addMarker(new MarkerOptions()
                .position(A943)
                .title("A943")
                .snippet("37.706051, 127.00671"));
        markerA944 = googleMap.addMarker(new MarkerOptions()
                .position(A944)
                .title("A944")
                .snippet("37.722408, 127.010392"));
        markerA945 = googleMap.addMarker(new MarkerOptions()
                .position(A945)
                .title("A945")
                .snippet("37.69665, 127.001414"));
        markerA946 = googleMap.addMarker(new MarkerOptions()
                .position(A946)
                .title("A946")
                .snippet("37.722408, 127.010392"));
        markerA947 = googleMap.addMarker(new MarkerOptions()
                .position(A947)
                .title("A947")
                .snippet("37.69665, 127.001414"));
        markerA948 = googleMap.addMarker(new MarkerOptions()
                .position(A948)
                .title("A948")
                .snippet("37.68275, 127.017975"));
        markerA949 = googleMap.addMarker(new MarkerOptions()
                .position(A949)
                .title("A949")
                .snippet("37.659108, 126.960687"));
        markerA950 = googleMap.addMarker(new MarkerOptions()
                .position(A950)
                .title("A950")
                .snippet("37.646653, 126.978747"));
        markerA951 = googleMap.addMarker(new MarkerOptions()
                .position(A951)
                .title("A951")
                .snippet("37.665484, 126.971683"));
        markerA952 = googleMap.addMarker(new MarkerOptions()
                .position(A952)
                .title("A952")
                .snippet("37.642394, 126.971431"));
        markerA953 = googleMap.addMarker(new MarkerOptions()
                .position(A953)
                .title("A953")
                .snippet("37.659108, 126.960687"));
        markerA954 = googleMap.addMarker(new MarkerOptions()
                .position(A954)
                .title("A954")
                .snippet("37.646653, 126.978747"));
        markerA955 = googleMap.addMarker(new MarkerOptions()
                .position(A955)
                .title("A955")
                .snippet("37.665469, 126.971647"));
        markerA956 = googleMap.addMarker(new MarkerOptions()
                .position(A956)
                .title("A956")
                .snippet("37.642394, 126.971431"));
        markerA957 = googleMap.addMarker(new MarkerOptions()
                .position(A957)
                .title("A957")
                .snippet("37.659108, 126.960687"));
        markerA958 = googleMap.addMarker(new MarkerOptions()
                .position(A958)
                .title("A958")
                .snippet("37.646653, 126.978747"));
        markerA959 = googleMap.addMarker(new MarkerOptions()
                .position(A959)
                .title("A959")
                .snippet("37.665469, 126.971647"));
        markerA960 = googleMap.addMarker(new MarkerOptions()
                .position(A960)
                .title("A960")
                .snippet("37.642394, 126.971431"));
        markerA961 = googleMap.addMarker(new MarkerOptions()
                .position(A961)
                .title("A961")
                .snippet("37.665589, 126.998533"));
        markerA962 = googleMap.addMarker(new MarkerOptions()
                .position(A962)
                .title("A962")
                .snippet("37.646478, 126.971306"));
        markerA963 = googleMap.addMarker(new MarkerOptions()
                .position(A963)
                .title("A963")
                .snippet("37.646478, 126.971306"));
        markerA964 = googleMap.addMarker(new MarkerOptions()
                .position(A964)
                .title("A964")
                .snippet("37.665589, 126.998533"));
        markerA965 = googleMap.addMarker(new MarkerOptions()
                .position(A965)
                .title("A965")
                .snippet("37.665589, 126.998533"));
        markerA966 = googleMap.addMarker(new MarkerOptions()
                .position(A966)
                .title("A966")
                .snippet("37.665589, 126.998533"));
        markerA967 = googleMap.addMarker(new MarkerOptions()
                .position(A967)
                .title("A967")
                .snippet("37.655863, 126.948334"));
        markerA968 = googleMap.addMarker(new MarkerOptions()
                .position(A968)
                .title("A968")
                .snippet("37.684764, 127.033184"));
        markerA969 = googleMap.addMarker(new MarkerOptions()
                .position(A969)
                .title("A969")
                .snippet("37.672825, 127.000322"));
        markerA970 = googleMap.addMarker(new MarkerOptions()
                .position(A970)
                .title("A970")
                .snippet("37.672825, 127.000322"));
        markerA971 = googleMap.addMarker(new MarkerOptions()
                .position(A971)
                .title("A971")
                .snippet("37.672825, 127.000322"));
        markerA972 = googleMap.addMarker(new MarkerOptions()
                .position(A972)
                .title("A972")
                .snippet("37.618854, 126.997597"));
        markerA973 = googleMap.addMarker(new MarkerOptions()
                .position(A973)
                .title("A973")
                .snippet("37.618854, 126.997597"));
        markerA974 = googleMap.addMarker(new MarkerOptions()
                .position(A974)
                .title("A974")
                .snippet("37.618854, 126.997597"));
        markerA975 = googleMap.addMarker(new MarkerOptions()
                .position(A975)
                .title("A975")
                .snippet("37.618854, 126.997597"));
        markerA976 = googleMap.addMarker(new MarkerOptions()
                .position(A976)
                .title("A976")
                .snippet("37.654422, 126.960031"));
        markerA977 = googleMap.addMarker(new MarkerOptions()
                .position(A977)
                .title("A977")
                .snippet("37.643244, 126.99675"));
        markerA978 = googleMap.addMarker(new MarkerOptions()
                .position(A978)
                .title("A978")
                .snippet("37.687958, 127.030267"));
        markerA979 = googleMap.addMarker(new MarkerOptions()
                .position(A979)
                .title("A979")
                .snippet("37.648403, 127.001931"));
        markerA980 = googleMap.addMarker(new MarkerOptions()
                .position(A980)
                .title("A980")
                .snippet("37.614661, 126.960153"));
        markerA981 = googleMap.addMarker(new MarkerOptions()
                .position(A981)
                .title("A981")
                .snippet("37.618406, 126.953842"));
        markerA982 = googleMap.addMarker(new MarkerOptions()
                .position(A982)
                .title("A982")
                .snippet("37.635956, 127.002875"));
        markerA983 = googleMap.addMarker(new MarkerOptions()
                .position(A983)
                .title("A983")
                .snippet("37.658069, 126.947175"));
        markerA984 = googleMap.addMarker(new MarkerOptions()
                .position(A984)
                .title("A984")
                .snippet("37.678456, 126.959056"));
        markerA985 = googleMap.addMarker(new MarkerOptions()
                .position(A985)
                .title("A985")
                .snippet("37.63775, 126.943467"));
        markerA986 = googleMap.addMarker(new MarkerOptions()
                .position(A986)
                .title("A986")
                .snippet("37.655863, 126.948334"));
        markerA987 = googleMap.addMarker(new MarkerOptions()
                .position(A987)
                .title("A987")
                .snippet("37.65489, 126.950466"));
        markerA988 = googleMap.addMarker(new MarkerOptions()
                .position(A988)
                .title("A988")
                .snippet("37.619197, 126.959125"));
        markerA989 = googleMap.addMarker(new MarkerOptions()
                .position(A989)
                .title("A989")
                .snippet("37.65806, 126.947158"));
        markerA990 = googleMap.addMarker(new MarkerOptions()
                .position(A990)
                .title("A990")
                .snippet("37.645175, 126.940099"));
        markerA991 = googleMap.addMarker(new MarkerOptions()
                .position(A991)
                .title("A991")
                .snippet("37.684764, 127.033184"));
        markerA992 = googleMap.addMarker(new MarkerOptions()
                .position(A992)
                .title("A992")
                .snippet("37.629065, 127.001085"));
        markerA993 = googleMap.addMarker(new MarkerOptions()
                .position(A993)
                .title("A993")
                .snippet("37.642509, 127.002254"));
        markerA994 = googleMap.addMarker(new MarkerOptions()
                .position(A994)
                .title("A994")
                .snippet("37.654958, 126.950514"));
        markerA995 = googleMap.addMarker(new MarkerOptions()
                .position(A995)
                .title("A995")
                .snippet("37.658106, 126.947103"));
        markerA996 = googleMap.addMarker(new MarkerOptions()
                .position(A996)
                .title("A996")
                .snippet("37.665589, 126.998533"));
        markerA997 = googleMap.addMarker(new MarkerOptions()
                .position(A997)
                .title("A997")
                .snippet("37.655992, 126.999014"));
        markerA998 = googleMap.addMarker(new MarkerOptions()
                .position(A998)
                .title("A998")
                .snippet("37.677539, 127.032314"));
        markerA999 = googleMap.addMarker(new MarkerOptions()
                .position(A999)
                .title("A999")
                .snippet("37.619228, 126.990711"));
        markerA1000 = googleMap.addMarker(new MarkerOptions()
                .position(A1000)
                .title("A1000")
                .snippet("37.708949, 126.976821"));



        /*
        markerSEOULTECH = googleMap.addMarker(new MarkerOptions()
                .position(SEOULTECH)
                .title("과기대")
                .snippet("과기과기대"));

        // on map ready end
        markerATOM = googleMap.addMarker(new MarkerOptions()
                .position(ATOM)
                .title("원자력병원")
                .snippet("병원병원"));
    */
    }


    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);

                currentPoint
                        = new LatLng(location.getLatitude(), location.getLongitude()); // 위도 경도 입력

                // 지오코더에서 위도와 경도 표기
                String markerTitle = getCurrentAddress(currentPoint);
                String markerSnippet = "위도 : " + String.valueOf(location.getLatitude())
                        + " 경도 : " + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocation2 = location;
            }
        }
    };


    private void startLocationUpdates() {
        if (!checkLocationServicesStatus()) {
            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        } else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);


            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mMap.setMyLocationEnabled(true);

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        if (checkPermission()) {
            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            if (mMap != null)
                mMap.setMyLocationEnabled(true);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mFusedLocationClient != null) {
            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }


    public String getCurrentAddress(LatLng latlng) {

        //지오코더, GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가. 네트워크 확인", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가. 네트워크 확인";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {

        if (currentMarker != null)
            currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(currentLatLng)
                .title(markerTitle)
                .snippet(markerSnippet)
                .draggable(true);

        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mMap.moveCamera(cameraUpdate);

    }


    public void setDefaultLocation() {

/*
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                textView.setText(message);
            }
        }
    }
*/
        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions
                .position(DEFAULT_LOCATION)
                .title(markerTitle)
                .snippet(markerSnippet)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);
    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        return false;

    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                // 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates();
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();

                } else {


                    // "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity2.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent GPSIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(GPSIntent, GPS_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");
                        needRequest = true;
                        return;
                    }
                }
                break;
        }
    }


}
