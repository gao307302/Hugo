package org.example.extra;

import java.sql.Timestamp;
import java.util.Objects;

public class ExtraSqlStatic implements ExtraSql {

    @Override
    public void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType) {

        sb.append(" x_y = ST_GEOMFROMTEXT('Point(").append(geoTempL).append(" ").append(geoTempR).append(")', 4326)").append(";\n");
        sb.append("INSERT INTO harvest_logistics.t_warehouse_static_present(province_name, province_code, city_name, city_code, district_name, district_code, vas_id, bu_code, warehouse_branch, NAME, construct_status, construct_date, is_under_bond, depot_type, floor_area, build_area, lettable_build_area, land_due_date, x_y, unloading_front, fire_fighting_level, FLOOR, bearing, is_risk_depot, is_plant, is_accessories, platform_height, platform_width, is_ramp, is_slope, rain_slot_width, create_by, update_by, is_delete, create_time, update_time, city_cluster_id, built_build_area)" +
                        " SELECT province_name, province_code, city_name, city_code, district_name, district_code, vas_id, bu_code, warehouse_branch, NAME, construct_status, construct_date, is_under_bond, depot_type, floor_area, build_area, lettable_build_area, land_due_date, x_y, unloading_front, fire_fighting_level, FLOOR, bearing, is_risk_depot, is_plant, is_accessories, platform_height, platform_width, is_ramp, is_slope, rain_slot_width, create_by, update_by, is_delete, create_time, update_time, city_cluster_id, built_build_area" +
                        " FROM `t_warehouse_static`" +
                        " where vas_id = '").append(vasId).append("'")
                .append(";\n");
        sb.append("UPDATE `t_warehouse_static_present` SET vid = UUID()" +
                        " where vas_id = '").append(vasId).append("'")
                .append(";\n");
    }

    @Override
    public void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName) {

        sb.append(" x_y = ST_GEOMFROMTEXT('Point(").append(geoTempL).append(" ").append(geoTempR).append(")', 4326)");
        sb.append(" where vas_id = '").append(vasId).append("'").append(";\n");
        sb.append("update `t_warehouse_static_present` o, `t_warehouse_static` n " +
                "set " +
                "o.province_name = n.province_name," +
                "o.province_code = n.province_code," +
                "o.city_name = n.city_name," +
                "o.city_code = n.city_code," +
                "o.district_name = n.district_name," +
                "o.district_code = n.district_code," +
                "o.bu_code = n.bu_code," +
                "o.warehouse_branch = n.warehouse_branch," +
                "o.NAME = n.NAME," +
                "o.construct_status = n.construct_status," +
                "o.construct_date = n.construct_date," +
                "o.is_under_bond = n.is_under_bond," +
                "o.depot_type = n.depot_type," +
                "o.floor_area = n.floor_area," +
                "o.build_area = n.build_area," +
                "o.lettable_build_area = n.lettable_build_area," +
                "o.land_due_date = n.land_due_date," +
                "o.x_y = n.x_y," +
                "o.unloading_front = n.unloading_front," +
                "o.fire_fighting_level = n.fire_fighting_level," +
                "o.FLOOR = n.FLOOR," +
                "o.bearing = n.bearing," +
                "o.is_risk_depot = n.is_risk_depot," +
                "o.is_plant = n.is_plant," +
                "o.is_accessories = n.is_accessories," +
                "o.platform_height = n.platform_height," +
                "o.platform_width = n.platform_width," +
                "o.is_ramp = n.is_ramp," +
                "o.is_slope = n.is_slope," +
                "o.rain_slot_width = n.rain_slot_width," +
                "o.create_by = n.create_by," +
                "o.update_by = n.update_by," +
                "o.is_delete = n.is_delete," +
                "o.create_time = n.create_time," +
                "o.update_time = n.update_time," +
                "o. city_cluster_id = n. city_cluster_id," +
                "o. is_pay_tax = n. is_pay_tax," +
                "o. is_building_storage = n. is_building_storage," +
                "o. is_elevator_storage = n. is_elevator_storage," +
                "o. land_info = n. land_info," +
                "o.built_build_area = n.built_build_area");
        sb.append(" where o.vas_id = '").append(vasId).append("'")
                .append(" and n.vas_id = '").append(vasId).append("'")
                .append(";\n");
    }

    @Override
    public String insertEnding(StringBuilder sb) {

        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.city_code = b.adcode " +
                "and b.location_type = 2 " +
                "set w.province_code = b.parent_adcode ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.province_code = b.adcode " +
                "and b.location_type = 1 " +
                "set w.province_name = b.name ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location_business` b " +
                "on b.adcode = w.city_code " +
                "set w.city_cluster_id = b.city_cluster_id ");
        return null;
    }

    @Override
    public String updateEnding(StringBuilder sb) {
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.city_code = b.adcode " +
                "and b.location_type = 2 " +
                "set w.province_code = b.parent_adcode ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.province_code = b.adcode " +
                "and b.location_type = 1 " +
                "set w.province_name = b.name ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location_business` b " +
                "on b.adcode = w.city_code " +
                "set w.city_cluster_id = b.city_cluster_id ");
        return null;
    }

    @Override
    public String ending(StringBuilder sb) {
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.city_code = b.adcode " +
                "and b.location_type = 2 " +
                "set w.province_code = b.parent_adcode ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location` b " +
                "on w.province_code = b.adcode " +
                "and b.location_type = 1 " +
                "set w.province_name = b.name ");
        sb.append("update `t_warehouse_static` w " +
                "left join `b_location_business` b " +
                "on b.adcode = w.city_code " +
                "set w.city_cluster_id = b.city_cluster_id ");
        sb.append("update `t_warehouse_static_present` w " +
                "left join `b_location` b " +
                "on w.city_code = b.adcode " +
                "and b.location_type = 2 " +
                "set w.province_code = b.parent_adcode ");
        sb.append("update `t_warehouse_static_present` w " +
                "left join `b_location` b " +
                "on w.province_code = b.adcode " +
                "and b.location_type = 1 " +
                "set w.province_name = b.name ");
        sb.append("update `t_warehouse_static_present` w " +
                "left join `b_location_business` b " +
                "on b.adcode = w.city_code " +
                "set w.city_cluster_id = b.city_cluster_id ");
        return null;
    }

}
