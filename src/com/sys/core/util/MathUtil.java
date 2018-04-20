package com.sys.core.util;

import java.math.BigDecimal;

/**
 * 数学计算
 * @author zzs
 *
 */
public class MathUtil {
	
	
    /**
     * BigDecimal进行加法运算  
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal addBigDecimal(BigDecimal b1, BigDecimal b2){   
    	 
            return MathUtil.getMinBigDecimal(b1.add(b2));
    }  
    
    /**
     * 减法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal subBigDecimal(BigDecimal b1, BigDecimal b2){   
    	
        return MathUtil.getMinBigDecimal(b1.subtract(b2));
    } 
    /**
     * 乘法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal mulBigDecimal(BigDecimal b1, BigDecimal b2){   
      	 
        return MathUtil.getMinBigDecimal(b1.multiply(b2));
    } 
    /**
     * 除法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal divBigDecimal(BigDecimal b1, BigDecimal b2){   
      	 
        return MathUtil.getMinBigDecimal(b1.divide(b2));
    } 
    /**
     * 四舍五入
     * @param b1
     * @return
     */
    public static BigDecimal roundBigDecimal(BigDecimal b1){   

    	BigDecimal b = new BigDecimal("1.00");  
        return b1.divide(b, 1, BigDecimal.ROUND_DOWN);
    } 
    
    
    /**
     * BigDecimal最小数据不能为负数 
     * @param b1
     * @return
     */
    public static BigDecimal getMinBigDecimal(BigDecimal b1){
    	
    	if(b1.doubleValue()<0){
    		
    		return new BigDecimal("0");
    	}
    	return b1;
    }
}
