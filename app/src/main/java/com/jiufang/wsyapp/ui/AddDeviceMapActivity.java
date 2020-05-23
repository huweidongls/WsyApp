package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MapAddressAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDeviceMapActivity extends BaseActivity {

    private Context context = AddDeviceMapActivity.this;

    @BindView(R.id.mapview)
    MapView mapView;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private BaiduMap baiduMap;
    private MapStatus mapStatus;
    private GeoCoder geoCoder;

    private boolean isFirstLoc = true;

    private MapAddressAdapter adapter;

    private BitmapDescriptor mCurrentMarker;
    private LocationClient locationClient;
    private double lat = 0.00;
    private double lng = 0.00;
    private MyLocationData locData;
    private BDLocationListener bdLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //mapView 销毁后不在处理新接收的位置
//            if (bdLocation == null || mapView == null){
//                return;
//            }
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(bdLocation.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
//                    .longitude(bdLocation.getLongitude()).build();
//            baiduMap.setMyLocationData(locData);
            Logger.e("123123", "lat--"+bdLocation.getLatitude()+"--lng--"+bdLocation.getLongitude());
//            List<Poi> list = bdLocation.getPoiList();// POI数据
//            if (list != null) {
//                for (Poi p : list) {
//                    Logger.e("123123", p.getName());
//                }
//            }

            // map view 销毁后不在处理新接收的位置
            if (bdLocation == null || mapView == null) {
                return;
            }
            locData = new MyLocationData.Builder()
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

        }
    };

    private void initLocation(){

        locationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setScanSpan(0);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        locationClient.setLocOption(option);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_map);

        StatusBarUtils.setStatusBar(AddDeviceMapActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceMapActivity.this);
        initData();

    }

    private void initData() {

        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.duihao);
//        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
//        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
//        baiduMap.setMyLocationConfiguration(config);
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                LatLng latLng = mapStatus.target;
                Logger.e("123123", "lat--"+latLng.latitude+"--lng--"+latLng.longitude+"--tostring--"+latLng.toString());
                latlngToAddress(latLng);
            }
        });

        geoCoder = GeoCoder.newInstance();
        // 设置地址或经纬度反编译后的监听,这里有两个回调方法
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    ToastUtil.showShort(context, "找不到该地址!");
                } else {
                    ToastUtil.showShort(context, result.getAddress());
                    List<PoiInfo> pois = result.getPoiList();
                    adapter = new MapAddressAdapter(pois);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                ToastUtil.showShort(context, result.getAddress());

            }
        });

    }

    // 百度地图通过坐标获取地址，（ 要签名打包才能得到地址）
    private void latlngToAddress(LatLng latlng) {
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        locationClient.unRegisterLocationListener(bdLocationListener);
        locationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initLocation();
        locationClient.registerLocationListener(bdLocationListener);
        locationClient.start();
    }

}
