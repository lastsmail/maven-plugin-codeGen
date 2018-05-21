<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${targetPackage}.mapper.${clsName}Mapper">
    <resultMap id="BaseResultMap" type="${targetPackage}.model.${clsName}">
    <#import "common.ftl" as com><#assign isHasPk=false >
    <#list fields as f>
        <#if (f.isPk) >
            <#assign isHasPk=true >
            <@com.createId idName="${f.name}" idType="${f.type.fullName}"  pkType="${f.type.lable}" />
            <id column="${f.name}" jdbcType="${f.type.lable}" property="${f.name?uncap_first}" />
        <#else >
            <result column="${f.name}" jdbcType="${f.type.lable}" property="<@com.coloum2Field f.name />" />
        </#if>
    </#list>
    </resultMap>


    <sql id="tableName">
        ${tableName}
    </sql>
<#noparse>
    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${criterion.condition}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                        #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Update_By_Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>

                                <when test="criterion.noValue">
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${criterion.condition}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                        #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
</#noparse>
    <sql id="Base_Column_List">
    <#list fields as f>
        ${f.name}<#if (f_has_next)>,</#if>
    </#list>
    </sql>
    <select id="selectByExample" parameterType="${targetPackage}.model.${clsName}Example" resultMap="BaseResultMap">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List" />
        from  <include refid="tableName" />
        <if test="_parameter != null">
            <include refid="Example_Where_Clause" />
        </if>
        <if test="orderByClause != null">
          <#noparse>order by ${orderByClause} </#noparse>
        </if>
    </select>


    <delete id="deleteByExample" parameterType="${targetPackage}.model.${clsName}Example">

        delete from <include refid="tableName" />
        <if test="_parameter != null">
            <include refid="Example_Where_Clause" />
        </if>
    </delete>
    <insert id="insert" parameterType="${targetPackage}.model.${clsName}">
        <#if (!pkItem.isCom)>
        <selectKey keyProperty= "${com.idName?uncap_first}"   order="AFTER" resultType="${com.pkType}">
            SELECT LAST_INSERT_ID()
        </selectKey>
        </#if>
        insert into <include refid="tableName" /> (
        <#list fields as f >
            ${f.name}<#if (f_has_next)>,</#if>
        </#list>
        )
        values (
            <@com.jdbcVal targetList=fields />
        )

    </insert>
    <insert id="insertSelective" parameterType="${targetPackage}.model.${clsName}">
    <#if (!pkItem.isCom)>
        <selectKey keyProperty= "${com.idName?uncap_first}"   order="AFTER" resultType="${com.pkType}">
            SELECT LAST_INSERT_ID()
        </selectKey>
    </#if>
        insert into <include refid="tableName" />
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <@com.conditionKey fields=fields />
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <@com.conditionVal fields=fields />
        </trim>
    </insert>
    <select id="countByExample" parameterType="${targetPackage}.model.${clsName}Example" resultType="java.lang.Integer">

        select count(*) from <include refid="tableName" />
        <if test="_parameter != null">
            <include refid="Example_Where_Clause" />
        </if>
    </select>
    <update id="updateByExampleSelective" parameterType="map">

        update <include refid="tableName" />
        <set>
            <@com.updateKey fields =fields />
        </set>
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause" />
        </if>
    </update>
    <update id="updateByExample" parameterType="map">
        update <include refid="tableName" />
        set
        <@com.updateKeyNotComma fields =fields />
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause" />
        </if>
    </update>

    <#if (isHasPk)>
        <select id="selectByPrimaryKey" parameterType= "${com.idType}" resultMap="BaseResultMap">
            select
            <include refid="Base_Column_List" />
            from <include refid="tableName" />
            where <@com.idCondition/>
        </select>
        <delete id="deleteByPrimaryKey" parameterType="${com.idType}">
            delete from <include refid="tableName" />
            where  <@com.idCondition/>
        </delete>
        <update id="updateByPrimaryKey" parameterType="${targetPackage}.model.${clsName}">

            update <include refid="tableName" />
            set
            <@com.updateKeyNotComma fields= fields />
            where <@com.recordIdCondition/>
        </update>
        <update id="updateByPrimaryKeySelective" parameterType="${targetPackage}.model.${clsName}">

            update<include refid="tableName" />
            <set>
                <@com.updateKey fields = fields />
            </set>
            where <@com.recordIdCondition/>
        </update>
    </#if>


</mapper>
