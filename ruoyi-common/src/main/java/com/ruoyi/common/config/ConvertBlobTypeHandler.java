package com.ruoyi.common.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * @author linmr
 * @description:
 * @date 2022/9/3
 */
public class ConvertBlobTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        byte[] bytes = parameter.getBytes(StandardCharsets.UTF_8);
        ps.setBlob(i, new ByteArrayInputStream(bytes), bytes.length);

    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        return getConvertResult(resultSet.getBlob(columnName));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

        return getConvertResult(resultSet.getBlob(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

        return getConvertResult(callableStatement.getBlob(columnIndex));
    }

    private String getConvertResult(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        return new String(blob.getBytes(1, (int) blob.length()), StandardCharsets.UTF_8);
    }
}
