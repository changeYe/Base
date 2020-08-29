package sharding.algorithm;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/**
 * 自定义分片算法
 * PreciseShardingAlgorithm：精确分片算法、单一键作为分片键
 * 这里我们根据主键id，对数据进行分片
 * @author yinjihuan
 *
 * 范围分片算法：对应 RangeShardingAlgorithm，用于处理使用单一键作为分片键的
 * BETWEEN AND、>、<、>=、<=进行分片的场景。需要配合 StandardShardingStrategy 使用。
 *
 * 复合分片算法：对应 ComplexKeysShardingAlgorithm，用于处理使用多键作为分片键进行分片的场景，包含多个分片键的逻辑较复杂，
 * 需要应用开发者自行处理其中的复杂度。需要配合 ComplexShardingStrategy 使用。
 *
 *
 * Hint分片算法：对应 HintShardingAlgorithm，用于处理使用 Hint 行分片的场景。需要配合 HintShardingStrategy 使用。
 *
 */
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     *
     * @param availableTargetNames 所有可用的物理表名，也就是分片键将值分布到哪个表中
     * @param shardingValue 分片键的值
     * @return
     */
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
	    if(shardingValue.getValue()!= null && shardingValue.getValue() > 1000){
            return shardingValue.getLogicTableName();
        }
		for (String tableName : availableTargetNames) {
			if (tableName.endsWith(shardingValue.getValue() % 2 + "")) {
				return tableName;
			}
		}
		throw new IllegalArgumentException();
	}

}
