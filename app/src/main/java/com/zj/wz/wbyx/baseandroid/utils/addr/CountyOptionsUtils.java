package com.zj.wz.wbyx.baseandroid.utils.addr;


import android.content.Context;
import android.content.res.AssetManager;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.wz.wbyx.baseandroid.utils.PLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CountyOptionsUtils
 * Author: 曹伟
 * Date: 2019/10/8 17:35
 * Description: 解析city生成options联动参数工具类
 */

public class CountyOptionsUtils {

    public static CountyOptionsUtils instance = null;
    private static ArrayList<Province> provinces = new ArrayList<>();       //省数据集合
    private static ArrayList<ArrayList<Province.City>> citys = new ArrayList<>();   //市数据集合
    private static ArrayList<ArrayList<ArrayList<Province.City.Area>>> Areas = new ArrayList<>();  //区数据集合

    public static ArrayList<ArrayList<Province.City>> getCitys() {
        return citys;
    }

    public static void setCitys(ArrayList<ArrayList<Province.City>> citys) {
        CountyOptionsUtils.citys = citys;
    }

    public static ArrayList<ArrayList<ArrayList<Province.City.Area>>> getAreas() {
        return Areas;
    }

    public static void setAreas(ArrayList<ArrayList<ArrayList<Province.City.Area>>> areas) {
        Areas = areas;
    }

    public static ArrayList<Province> getProvinces() {
        return provinces;
    }

    public static void setProvinces(ArrayList<Province> provinces) {
        CountyOptionsUtils.provinces = provinces;
    }


    public static CountyOptionsUtils getInstance() {
        if (instance == null) {
            synchronized (CountyOptionsUtils.class) {
                if (instance == null) {
                    instance = new CountyOptionsUtils();
                }
            }
        }
        return instance;
    }

    public static void init(Context context){
        String CityData = getJson(context, "city.json");//获取assets目录下的json文件数据
        parseData(CityData) ;
    }


    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            PLog.e("Exception : "+ e.getMessage());
        }
        return stringBuilder.toString();
    }



    public static void parseData(String result) {
        try {
            Gson gson = new Gson();
                Type listType = new TypeToken<List<Province>>() {}.getType();
                provinces = gson.fromJson(result, listType);
                fillCityAndArea(provinces);
        } catch (Exception e) {
            e.printStackTrace();
            PLog.e("Exception : "+ e.getMessage());
        }
    }

    /**
     * 填充市区数据集合
     * @param provinces
     */
    private static void fillCityAndArea(ArrayList<Province> provinces) {
        for (int i = 0 ; i < provinces.size() ; i++){
            ArrayList<Province.City> cityArrayList = new ArrayList<>();
            ArrayList<ArrayList<Province.City.Area>> areaArrayList = new ArrayList<>();
            /**
             * 省数据下无市数据，创建空数据，方式异常导致程序崩溃
             */
            if(provinces.get(i).getCities() == null || provinces.get(i).getCities().size() == 0){
                //添加城市假数据
                cityArrayList.add(new Province.City(""));
                //城市为空的时候，所对应的区必定为空，创建地区假数据
                ArrayList<Province.City.Area> areas = new ArrayList<>();
                areas.add(new Province.City.Area(""));
                areaArrayList.add(areas);
                citys.add(cityArrayList);
                Areas.add(areaArrayList);
            }else{
                for (int j = 0 ; j < provinces.get(i).getCities().size() ; j++){
                    //添加市集合
                    cityArrayList.add(provinces.get(i).getCities().get(j));
                    ArrayList<Province.City.Area> city_area = new ArrayList<>();
                    if(provinces.get(i).getCities().get(j).getAreas() == null
                            || provinces.get(i).getCities().get(j).getAreas().size() == 0){
                        //市下面无对应的区划分，创建加数据
                        city_area.add(new Province.City.Area(""));
                    }else{
                        for(int d = 0 ; d < provinces.get(i).getCities().get(j).getAreas().size() ; d++){
                            city_area.add(provinces.get(i).getCities().get(j).getAreas().get(d));
                        }
                    }
                    areaArrayList.add(city_area);
                }
            }

            citys.add(cityArrayList);
            Areas.add(areaArrayList);
        }
    }
}
