package com.mobileai.dxc.db.mapper;

import com.mobileai.dxc.db.pojo.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IndentMapper {

    @Insert("insert into indent(user_id,seller_id,number,service_time,service) values(#{userId},#{sellerId},#{number},#{serviceTime},#{service})")
    void createOrder(@Param("userId") int userId, @Param("sellerId") int sellerid, @Param("number") int number,
            @Param("serviceTime") long serviceTime, @Param("service") String service);

    @Select("select order_id from indent where user_id = #{userId}")
    int selectOrderidByUserid(@Param("userId") int userId);

    @Update("update indent set order_status = #{orderStatus} where order_id = #{orderId}")
    void updateOrderStatus(@Param("orderStatus") int orderStatus, @Param("orderId") int orderId);

    @Select("select user_id from indent where order_id = #{orderId}")
    int selectUseridByOrderid(@Param("orderId") int orderId);

    @Update("update indent set refuse_reason = #{reason} where order_id = #{orderId}")
    void updateRefuseReasonByOrderid(@Param("reason") String reason, @Param("orderId") int orderId);

    @Select("select * from indent where order_id = #{orderId}")
    Order selectByOrderid(@Param("orderId") int orderId);

    @Select("select record_id from indent where service_time =#{serviceTime}")
    int selectIdByTime(@Param("serviceTime")long serviceTime);

}