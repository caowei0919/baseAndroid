package com.zj.wz.wbyx.baseandroid.utils.addr;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * FileName: Province
 * Author: 曹伟
 * Date: 2019/10/8 17:42
 * Description: 省
 */

public class Province implements IPickerViewData {
    private String name ;   //名称
    private String province_id  ;   //id
    private List<City> city ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public List<City> getCities() {
        return city;
    }

    public void setCities(List<City> cities) {
        this.city = cities;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class City implements IPickerViewData {
        private String name ;   //名称
        private String province_id ;    //对应省id
        private String city_id ;    //市id
        private List<Area> area ;

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public List<Area> getAreas() {
            return area;
        }

        public void setAreas(List<Area> areas) {
            this.area = areas;
        }

        @Override
        public String getPickerViewText() {
            return this.name;
        }

        public static class Area implements IPickerViewData {
            private String name ; //名称
            private String city_id  ;   //对应城市id
            private String area_id ;    //区域id

            public Area(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            @Override
            public String getPickerViewText() {
                return this.name;
            }
        }
    }
}
