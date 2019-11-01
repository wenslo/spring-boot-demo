package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.wenslo.fluent.data.repository.LongIdRepository;
import com.github.wenslo.springbootdemo.model.system.District;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018-12-31 20:17
 * @description 地区
 */
@Repository
public interface DistrictRepository extends LongIdRepository<District, Long> {
    /**
     * 根据code长度查询地区
     * @param codeLength code长度
     * @return 地区
     */
    @Query(value = "from District r where length(r.code) = ?1 ")
    Optional<List<District>> findByCodeLength(Integer codeLength);

    /**
     * 查询省，直辖市
     * @return 地区
     */
    Optional<List<District>> findByParentCodeIsNull();
}
