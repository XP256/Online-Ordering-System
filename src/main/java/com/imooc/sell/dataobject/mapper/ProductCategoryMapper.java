package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {

    /**
     *@Description: insertByMap
     *@Param: [map]
     *@return: int
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    /**
     *@Description: insertByObject
     *@Param: [productCategory]
     *@return: int
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    /**
     *@Description: findByCategoryType
     *@Param: [categoryType]
     *@return: com.imooc.sell.dataobject.ProductCategory
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            //column是数据库里的字段，映射到对象里的属性property
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    ProductCategory findByCategoryType(Integer categoryType);


    /**
     *@Description: findByCategoryName
     *@Param: [categoryName]
     *@return: java.util.List<com.imooc.sell.dataobject.ProductCategory>
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            //column是数据库里的字段，映射到对象里的属性property
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    List<ProductCategory> findByCategoryName(String categoryName);



    /**
     *@Description: updateByCategoryType
     *@Param: [categoryName, categoryType]
     *@return: int
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);


    /**
     *@Description: updateByObject
     *@Param: [categoryName, categoryType]
     *@return: int
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:08
     */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    /**
     *@Description: deleteByCategoryType
     *@Param: [categoryType]
     *@return: int
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 18:14
     */
    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);
    
    
    /**
     *@Description: selectByCategoryType
     *@Param: [categoryType]
     *@return: com.imooc.sell.dataobject.ProductCategory
     *@Author: XINPENG ZHU
     *@Date: 2018/8/24
     *@Time: 19:04
     */
    ProductCategory selectByCategoryType(Integer categoryType);
}
