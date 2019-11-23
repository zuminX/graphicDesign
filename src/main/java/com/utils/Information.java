package com.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 存放程序信息的类
 */
@Component("information")
@PropertySource(value = "classpath:properties/information.properties", encoding = "utf-8")
public class Information {
    public static String createTriangleError;
    public static String createCircleError;
    public static String createParallelogramError;
    public static String createRegularPentagonError;
    public static String theParameterIsInvalid;
    public static String inputNumberIsIllegal;
    public static String indexOutOfBounds;
    public static String circleName;
    public static String parallelogramName;
    public static String regularPentagonName;
    public static String triangleName;

    @Value("${information.createTriangleError}")
    public void setCreateTriangleError(String createTriangleError) {
        Information.createTriangleError = createTriangleError;
    }

    @Value("${information.createCircleError}")
    public void setCreateCircleError(String createCircleError) {
        Information.createCircleError = createCircleError;
    }

    @Value("${information.createParallelogramError}")
    public void setCreateParallelogramError(String createParallelogramError) {
        Information.createParallelogramError = createParallelogramError;
    }

    @Value("${information.createRegularPentagonError}")
    public void setCreateRegularPentagonError(String createRegularPentagonError) {
        Information.createRegularPentagonError = createRegularPentagonError;
    }

    @Value("${information.theParameterIsInvalid}")
    public void setTheParameterIsInvalid(String theParameterIsInvalid) {
        Information.theParameterIsInvalid = theParameterIsInvalid;
    }

    @Value("${information.inputNumberIsIllegal}")
    public void setInputNumberIsIllegal(String inputNumberIsIllegal) {
        Information.inputNumberIsIllegal = inputNumberIsIllegal;
    }

    @Value("${information.indexOutOfBounds}")
    public void setIndexOutOfBounds(String indexOutOfBounds) {
        Information.indexOutOfBounds = indexOutOfBounds;
    }

    @Value("${information.circleName}")
    public void setCircleName(String circleName) {
        Information.circleName = circleName;
    }

    @Value("${information.parallelogramName}")
    public void setParallelogramName(String parallelogramName) {
        Information.parallelogramName = parallelogramName;
    }

    @Value("${information.regularPentagonName}")
    public void setRegularPentagonName(String regularPentagonName) {
        Information.regularPentagonName = regularPentagonName;
    }

    @Value("${information.triangleName}")
    public void setTriangleName(String triangleName) {
        Information.triangleName = triangleName;
    }
}