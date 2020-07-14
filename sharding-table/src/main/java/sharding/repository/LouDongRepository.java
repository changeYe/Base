package sharding.repository;

import org.apache.ibatis.annotations.Mapper;
import sharding.po.LouDong;

import java.util.List;


@Mapper
public interface LouDongRepository {
	
	Long addLouDong(LouDong louDong);
	
	List<LouDong> list();
}
