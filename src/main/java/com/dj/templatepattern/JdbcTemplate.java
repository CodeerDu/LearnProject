package com.dj.templatepattern;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<T> executeQuery(String sql,RowMapper<T> rowMapper,Object[] values){
        try {
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = this.createPreparedStatement(connection,sql);
            ResultSet resultSet = this.executeQuery(preparedStatement,values);
           List<T> result = this.parseResultSet(resultSet,rowMapper);
           this.closeResultSet(resultSet);
           this.closeStatement(preparedStatement);
           this.closeConnection(connection);
           return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
}

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private void closeStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

    private List<T> parseResultSet(ResultSet resultSet, RowMapper<T> rowMapper) throws Exception {
        List<T> result = new ArrayList<T>();
        int rowNum = 1;
        while (resultSet.next()){
            result.add(rowMapper.mapRow(resultSet,rowNum++));
        }

        return  result;
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement, Object[] values) throws Exception{
        for(int i=0;i<values.length;i++){
            preparedStatement.setObject(i,values[i]);
        }
        return preparedStatement.executeQuery();
    }

    private PreparedStatement createPreparedStatement(Connection connection, String sql) throws Exception{
        return connection.prepareStatement(sql);
    }

    private Connection getConnection() throws Exception{
        Connection connection = this.dataSource.getConnection();
        return connection;
    }
    }
