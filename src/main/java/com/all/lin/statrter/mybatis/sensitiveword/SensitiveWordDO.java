package com.all.lin.statrter.mybatis.sensitiveword;

import com.all.lin.statrter.mybatis.core.dataobject.BaseDO;
import com.all.lin.statrter.mybatis.core.type.StringLiSTTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

/**
 * 敏感词 DO
 *
 * @author 永不言败
 */
@TableName(value = "system_sensitive_word", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordDO extends BaseDO {

//    /**
//     * 编号
//     */
//    @TableId
//    private Long id;
    /**
     * 敏感词
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 标签数组
     *
     * 用于实现不同的业务场景下，需要使用不同标签的敏感词。
     * 例如说，tag 有短信、论坛两种，敏感词 "推广" 在短信下是敏感词，在论坛下不是敏感词。
     * 此时，我们会存储一条敏感词记录，它的 name 为"推广"，tag 为短信。
     */
    @TableField(typeHandler = StringLiSTTypeHandler.class)
    private List<String> tags;
    /**
     * 状态
     *
     *
     */
    private Integer status;

}
