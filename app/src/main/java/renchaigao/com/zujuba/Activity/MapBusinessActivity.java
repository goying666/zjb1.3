package renchaigao.com.zujuba.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import renchaigao.com.zujuba.Json.MapLocation;
import renchaigao.com.zujuba.R;

public class MapBusinessActivity extends AppCompatActivity implements LocationSource,
        AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, PoiSearch.OnPoiSearchListener{
    MapView mMapView = null;
    final private String TAG = "MapBusinessActivity";
    //声明AMapLocationClient类对象
    public AMapLocationClient mlocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private MyLocationStyle myLocationStyle;
    private Marker locationMarker;
    private AMap aMap;
    private boolean isItemClickAction;
    private boolean isInputKeySearch;
    private String inputSearchKey;
    private ProgressDialog progDialog = null;
    private LatLonPoint searchLatlonPoint;
    private GeocodeSearch geocoderSearch;
    private OnLocationChangedListener mListener;
    private PoiItem firstItem;  private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private List<PoiItem> poiItems;// poi数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_business);

        init(savedInstanceState);

        initView();

        getPositioning();

    }
    private void setUpMap(){
        Log.e(TAG,"setUpMap");
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true); //设置默认定位按钮是否显示，非必需设置。
//       MyLocationStyle showMyLocation(boolean visible);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
//        //只定位一次。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
//        //定位一次，且将视角移动到地图中心点。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
//        //连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
//        //连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
//        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
//        //以下三种模式从5.1.0版本开始提供
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
//        //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
//        //连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);
//        //连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }
    private void init(Bundle savedInstanceState){
        Log.e(TAG,"init");
        mMapView = (MapView) findViewById(R.id.map_business);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

//            在可视范围一系列动作改变完成之后（例如：拖动、缩放）回调此方法。
            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                if (!isItemClickAction && !isInputKeySearch) {
                    geoAddress();
                    startJumpAnimation();
                }
                searchLatlonPoint = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
                isInputKeySearch = false;
                isItemClickAction = false;
            }
        });

        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {

            @Override
            public void onMapLoaded() {
                addMarkerInScreenCenter(null);
            }
        });
    }
    private void initView(){
        Log.e(TAG,"initView");

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        progDialog = new ProgressDialog(this);
    }
    private void addMarkerInScreenCenter(LatLng locationLatLng) {
        Log.e(TAG,"addMarkerInScreenCenter");
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f,0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        //设置Marker在屏幕上,不跟随地图移动
        locationMarker.setPositionByPixels(screenPosition.x,screenPosition.y);
        locationMarker.setZIndex(1);

    }
    /**
     * 屏幕中心marker 跳动
     */
    public void startJumpAnimation() {
        Log.e(TAG,"startJumpAnimation");

        if (locationMarker != null ) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = locationMarker.getPosition();
            Point point =  aMap.getProjection().toScreenLocation(latLng);
            point.y -= dip2px(this,125);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if(input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f)*(1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            locationMarker.setAnimation(animation);
            //开始动画
            locationMarker.startAnimation();

        } else {
            Log.e("ama","screenMarker is null");
        }
    }
    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 高德定位
    public void getPositioning() {

    }













    /**
     * 更新列表中的item
     * @param poiItems
     */
    private List<PoiItem> resultData;
//    private SearchResultAdapter searchResultAdapter;
//    private void updateListview(List<PoiItem> poiItems) {
//        resultData.clear();
//        searchResultAdapter.setSelectedPosition(0);
//        resultData.add(firstItem);
//        resultData.addAll(poiItems);
//
//        searchResultAdapter.setData(resultData);
//        searchResultAdapter.notifyDataSetChanged();
//    }





    @Override
    protected void onResume() {
        Log.e(TAG,"onResume");
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        Log.e(TAG,"onPause");
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
        deactivate();

        //停止定位后，本地定位服务并不会被销毁
//        mlocationClient.stopLocation();
    }
    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        Log.e(TAG,"deactivate");
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        //销毁定位客户端，同时销毁本地定位服务。
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(final AMapLocation amapLocation) {
        Log.e(TAG,"onLocationChanged");
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);

                LatLng curLatlng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());

                searchLatlonPoint = new LatLonPoint(curLatlng.latitude, curLatlng.longitude);

                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatlng, 16f));

                isInputKeySearch = false;

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,"run");
                    MapLocation location = new MapLocation();
                    location.setLatitude(amapLocation.getLatitude());
                    location.setLongitude(amapLocation.getLongitude());
                    location.setAddress(amapLocation.getAddress());
                    location.setCountry(amapLocation.getCountry());
                    location.setCity(amapLocation.getCity());
                    location.setDistrict(amapLocation.getDistrict());
                    location.setStreet(amapLocation.getStreet());
                    location.setStreetNum(amapLocation.getStreetNum());
                    location.setCityCode(amapLocation.getCityCode());
                    location.setAdCode(amapLocation.getAdCode());
                    location.setPoiName(amapLocation.getPoiName());
                    location.setAoiName(amapLocation.getAoiName());
                    location.setErrorCode(amapLocation.getErrorCode());
                    Log.e("map getLatitude", String.valueOf(amapLocation.getLatitude()));
                    Log.e("map getLongitude", String.valueOf(amapLocation.getLongitude()));
                    Log.e("map getAccuracy", String.valueOf(amapLocation.getAccuracy()));
                    Log.e("map getAltitude", String.valueOf(amapLocation.getAltitude()));
                    Log.e("map getBuildingId", String.valueOf(amapLocation.getBuildingId()));
                    Log.e("map getAddress", String.valueOf(amapLocation.getAddress()));
                    Log.e("map getPoiName", String.valueOf(amapLocation.getPoiName()));
                    Log.e("map getAoiName", String.valueOf(amapLocation.getAoiName()));
                }
            }).start();
        }
    @Override
    public void activate(OnLocationChangedListener listener) {
        Log.e(TAG,"activate");
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }
    //    根据给定的经纬度和最大结果数返回逆地理编码的结果列表。
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        Log.e(TAG,"onRegeocodeSearched");
        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                String address = result.getRegeocodeAddress().getProvince() + result.getRegeocodeAddress().getCity() + result.getRegeocodeAddress().getDistrict() + result.getRegeocodeAddress().getTownship();
                firstItem = new PoiItem("regeo", searchLatlonPoint, address, address);
                Toast.makeText(MapBusinessActivity.this,"result" +address,Toast.LENGTH_SHORT).show();
                doSearchQuery();
            }
        } else {
            Toast.makeText(MapBusinessActivity.this, "error code is " + rCode, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        Log.e(TAG,"onGeocodeSearched");

    }
    /**
     * POI搜索结果回调
     * @param poiResult 搜索结果
     * @param resultCode 错误码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int resultCode) {
        Log.e(TAG,"onPoiSearched");
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (poiResult != null && poiResult.getQuery() != null) {
                if (poiResult.getQuery().equals(query)) {
                    poiItems = poiResult.getPois();
                    if (poiItems != null && poiItems.size() > 0) {
                        for (int j=0;j<poiItems.size();j++){
                            Log.e("poiItems",poiItems.get(j).getAdName());
                        }
                    } else {
                        Toast.makeText(MapBusinessActivity.this, "无搜索结果", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(MapBusinessActivity.this, "无搜索结果", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.e(TAG,"onPoiItemSearched");

    }

    /**
     * 响应逆地理编码
     */
    public void geoAddress() {
        Log.e(TAG,"geoAddress");
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在加载...");
        progDialog.show();
        if (searchLatlonPoint != null){
            // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            RegeocodeQuery query = new RegeocodeQuery(searchLatlonPoint, 200, GeocodeSearch.AMAP);
            Log.e("geoAddress", "getLatLonType "+ query.getLatLonType().toString()+" getPoiType "+query.getPoiType().toString());
            geocoderSearch.getFromLocationAsyn(query);
        }
    }
    private String[] items = {"住宅区", "学校", "楼宇", "商场" };
    private String searchType = items[0];
    private String searchKey = "";
    /**
     * 开始进行poi搜索
     */
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        Log.e(TAG,"doSearchQuery");
        currentPage = 0;
        query = new PoiSearch.Query(searchKey, searchType, "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setCityLimit(true);
        query.setPageSize(20);
        query.setPageNum(currentPage);

        if (searchLatlonPoint != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(searchLatlonPoint, 1000, true));//
            poiSearch.searchPOIAsyn();
        }
    }
    public void dismissDialog() {
        Log.e(TAG,"dismissDialog");
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
