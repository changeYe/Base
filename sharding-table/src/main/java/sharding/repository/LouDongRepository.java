package sharding.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sharding.po.LouDong;

@Mapper
public interface LouDongRepository {


	Long addLouDong(LouDong louDong);

	List<LouDong> list();
}
