import com.github.wenslo.springbootdemo.model.system.User
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

val df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
val nowTimeStr = df.format(LocalDateTime.now());
val author = "wenhailin"
val codeStore = "code";
fun main() {
    val generator = TemplateGenerator()
    val entity = User::class
    generator.generate(entity)
}

class TemplateGenerator {
    fun generate(entity: KClass<*>) {
        println("-----------------开始生成Condition-----------------")
        generateCondition(entity)
        println("-----------------开始生成Repository-----------------")
        generateRepository(entity)
        println("-----------------开始生成Service-----------------")
        generateService(entity)
        println("-----------------开始生成Controller-----------------")
        generateController(entity)

    }

    private fun generateController(entity: KClass<*>) {
        val entityName = entity.simpleName;
        val fileName = "${entityName}Controller";
        val text = """

import com.github.wenslo.fluent.core.domain.Response;
import com.github.wenslo.springbootdemo.controller.BaseController;
import com.github.wenslo.springbootdemo.model.system.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


/**
 * @author $author
 * @version 0.0.1
 * @createTime $nowTimeStr
 * @description
 */
@RestController
@RequestMapping("$entityName")
public class $fileName extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger($fileName.class);

    @Autowired
    private ${entityName}Service service;

    @RequestMapping("/save")
    public Response save(@RequestBody $entityName dto) {
        $entityName save = service.save(dto);
        return Response.success(save.getId());
    }


    @RequestMapping("/queryByPage")
    public Page<$entityName> queryByPage(@RequestBody ${entityName}Condition condition) {
        logger.debug("The currently operator is {}, condition is {}", getLoginUsername(), gson.toJson(condition));
        Page<$entityName> page = service.getByCondition(condition, condition.getPageable());
        return page;
    }


    @RequestMapping("/remove/{id}")
    public Response remove(@PathVariable Long id) {
        logger.debug("be remove user id is {}", id);
        servie.remove(id);
        return Response.SUCCESS;
    }

    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable Long id) {
        $entityName dto = service.get(id);
        return Response.success(dto);
    }
}

        """
        generateFile(fileName, text)
    }

    private fun generateService(entity: KClass<*>) {

        val entityName = entity.simpleName;
        val interfaceName = "${entityName}Service";
        val interfaceText = """
import com.github.wenslo.fluent.data.service.LongIdService;

/**
 * @author $author
 * @version 0.0.1
 * @createTime $nowTimeStr
 * @description
 */
public interface $interfaceName extends LongIdService<$entityName, ${entityName}Condition> {
}


        """
        generateFile(interfaceName, interfaceText)

        val implementationName = "${entityName}ServiceImpl";
        val implementationText = """

import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author $author
 * @version 0.0.1
 * @createTime $nowTimeStr
 * @description
 */
@Service
@Transactional
public class $implementationName extends LongIdServiceImpl<${entityName}, ${entityName}Condition> implements ${entityName}Service {

    @Override
    protected List<Predicate> conditionBuild(${entityName}Condition condition) {
        List<Predicate> conditionBuilder = super.conditionBuild(condition);
        return conditionBuilder;
    }
}

        """
        generateFile(implementationName, implementationText)
    }

    private fun generateRepository(entity: KClass<*>) {
        val entityName = entity.simpleName;
        val fileName = "${entityName}Repository";
        val text = """
import com.github.wenslo.fluent.data.repository.LongIdRepository;
import org.springframework.stereotype.Repository;
/**
 * @author $author
 * @version 0.0.1
 * @createTime $nowTimeStr
 * @description
 */
@Repository
public interface $fileName extends LongIdRepository<$entityName, Long> {
}
        """
        generateFile(fileName, text)
    }

    private fun generateCondition(entity: KClass<*>) {

        val entityName = entity.simpleName;
        val fileName = "${entityName}Condition";
        val text = """

import com.github.wenslo.fluent.core.condition.PageCondition;

/**
 * @author $author
 * @version 0.0.1
 * @createTime $nowTimeStr
 * @description
 */
public class $fileName extends PageCondition {
    
}

        """
        generateFile(fileName, text)
    }


    private fun generateFile(fileName: String, text: String) {
        val folder = File(codeStore)
        if (folder.exists() and folder.isDirectory) folder.delete()
        folder.mkdir()
        val file = File("$codeStore/$fileName.java")
        file.createNewFile()
        file.writeText(text)
    }
}