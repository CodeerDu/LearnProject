package com.dj.templatepattern;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class OrderDao extends JdbcTemplate<Order>{

    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    public  List<Order> getById(String id){
        String sql = "Select * from t_order where id=?";
        Object[] valuse = {id};
        return executeQuery(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int rowNum) throws Exception {
                Order order = new Order() ;
                order.setDate(resultSet.getString("date"));
                order.setNumber(resultSet.getString("number"));
                order.setId(resultSet.getString("id"));
                return order;
            }
        },valuse);
    }

}
