package sharding.po;

import java.io.Serializable;

import lombok.Data;

/**
 * 分表
 * @author yinjihuan
 *
 */
@Data
public class User implements Serializable {

	private Long id;

	private Long userId ;
	
	private String userName ;

	private Integer sex;

	
	
}
