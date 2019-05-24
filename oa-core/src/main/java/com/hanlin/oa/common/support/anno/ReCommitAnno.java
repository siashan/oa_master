package com.hanlin.oa.common.support.anno;

import java.lang.annotation.*;

/**
 * 重复提交校验
 *
 *
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/19 18:05
 * @Modified By:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReCommitAnno {
}
