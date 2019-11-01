package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.wenslo.fluent.data.repository.LongIdRepository;
import com.github.wenslo.springbootdemo.enums.common.DeleteFlag;
import com.github.wenslo.springbootdemo.model.system.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018-12-31 20:15
 * @description 机构
 */
@Repository
public interface OrganizationRepository extends LongIdRepository<Organization, Long> {
    //    @Query("select o from Organization o JOIN fetch o.users where o.districtCode = ?1")
    @Query("from Organization o where o.districtCode like :districtCode%")
    public Optional<List<Organization>> findByDistrictCode(@Param("districtCode") String districtCode);

    public Optional<Organization> findByIdAndDeleteFlag(Long id, DeleteFlag deleteFlag);

    public Optional<List<Organization>> findTop30ByNameStartingWith(String name);

}
