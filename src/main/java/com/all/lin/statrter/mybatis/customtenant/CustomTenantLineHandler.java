package com.all.lin.statrter.mybatis.customtenant;

import com.all.lin.statrter.config.MybatisPlusConfig;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.google.common.collect.Lists;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.List;

/**
 * 多租户处理插件
 *
 * @author 向振华
 * @date 2021/04/26 13:37
 * @see MybatisPlusConfig
 */
public class CustomTenantLineHandler implements TenantLineHandler {

    /**
     * 忽略添加租户ID的表
     */
    private final static List<String> IGNORE_TABLE_NAMES = Lists.newArrayList(
            "t_country",
            "t_language",
            "system_sensitive_word"
    );

    /**
     * 获取租户ID值表达式(可从cookie、token、缓存中取)
     *
     * @return
     */
    @Override
    public Expression getTenantId() {
        return new LongValue(1L);
    }

    /**
     * 获取租户字段名(数据库的租户ID字段名)
     *
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    /**
     * 根据表名判断是否忽略拼接多租户条件
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return IGNORE_TABLE_NAMES.contains(tableName);
    }
}
//    Creating a new SqlSession
//        SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2fedfff1] was not registered for synchronization because synchronization is not active
//        Original SQL: SELECT id,tenant_id,frame_id,name,type,description,meta_created,meta_updated,meta_logic_flag FROM t_sec_role WHERE id=?
//        parser sql: SELECT id, tenant_id, frame_id, name, type, description, meta_created, meta_updated, meta_logic_flag FROM t_sec_role WHERE id = ? AND tenant_id = 1
//        2021-04-26 14:58:55.534  INFO 24980 --- [           main] com.zaxxer.hikari.HikariDataSource       : DatebookHikariCP - Starting...
//        2021-04-26 14:58:55.903  INFO 24980 --- [           main] com.zaxxer.hikari.HikariDataSource       : DatebookHikariCP - Start completed.
//        JDBC Connection [HikariProxyConnection@1100660981 wrapping com.mysql.cj.jdbc.ConnectionImpl@628fa8ea] will not be managed by Spring
//        ==>  Preparing: SELECT id, tenant_id, frame_id, name, type, description, meta_created, meta_updated, meta_logic_flag FROM t_sec_role WHERE id = ? AND tenant_id = 1
//        ==> Parameters: 40(Long)
//        <==    Columns: id, tenant_id, frame_id, name, type, description, meta_created, meta_updated, meta_logic_flag
//        <==        Row: 40, 1, 123, 一个角色啊, 2, haha, 2021-04-26 14:07:42, 2021-04-26 14:07:42, 1
//        <==      Total: 1
//        Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2fedfff1]
//        Role(id=40, sasTenantId=1, orgFrameId=123, name=一个角色啊, type=2, description=haha, metaCreated=Mon Apr 26 14:07:42 CST 2021, metaUpdated=Mon Apr 26 14:07:42 CST 2021, metaLogicFlag=1)