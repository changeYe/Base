package com.ytq.design.pattern;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import com.ytq.design.pattern.po.User;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

/**
 * @author yuantongqin
 * description:
 * 2020/8/26
 */
public class ShardingTest {

    public static void main(String[] args) throws SQLException {
        String sql = "insert into user values(5,5,5,1)";
        System.out.println(sql);
        List<User> users = getUsers(sql);

        System.out.println(users);

    }


    static List<User> getUsers(final String sql) throws SQLException {
        List<User> result = new LinkedList<>();
        DataSource dataSource = targetDataSource();
        Connection connection = dataSource.getConnection();
        String s = "insert into user values(?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(s);
        for (int i = 1;i<10;i++){
            preparedStatement.setInt(1,i);
            preparedStatement.setInt(2,i);
            preparedStatement.setString(3,"hello"+i);
            preparedStatement.setInt(4,new Random().nextInt(2));
            preparedStatement.execute();
        }

//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                User user = new User();
//            //省略设置User对象的赋值语句
//                result.add(user);
//            }
//        }
        connection.close();
        return result;
    }


    public static DataSource targetDataSource() throws SQLException {
        // 创建分片规则配置，什么是分片
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 创建分表规则
        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration("user", "myds${0..1}.user${0..1}");

//        Properties properties = new Properties();
//        properties.setProperty("worker.id", "33");
//        KeyGeneratorConfiguration key = new KeyGeneratorConfiguration("SNOWFLAKE", "id", properties);
//        tableRuleConfig.setKeyGeneratorConfig(key);


        //根据性别分库，一共分为 2 个库
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("sex", "myds${sex % 2}"));

        //根据用户 ID 分表，一共分为 2 张表
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("id", "user${id % 2}"));
        shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfig);

        //通过工厂类创建具体的 DataSource
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());

    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> sources = new HashMap<>();
        sources.put("myds0", createDataSource("myds0"));
        sources.put("myds1", createDataSource("myds1"));
        return sources;
    }

    /**
     * 创建数据源
     */
    public static DataSource createDataSource(String dbName) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(Driver.class.getName());
        String url = String.format("jdbc:mysql://localhost:3306/%s", dbName);
        result.setUrl(url);
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }
}
