package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.wenslo.fluent.data.repository.LongIdRepository;
import com.github.wenslo.springbootdemo.model.system.Role;
import org.springframework.stereotype.Repository;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:27
 * @description
 */
@Repository
public interface RoleRepository extends LongIdRepository<Role, Long> {
}
