import com.github.wenslo.springbootdemo.model.system.User
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

var df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
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
        println("-----------------开始生成Permission-----------------")
        generatePermission(entity)

    }

    private fun generatePermission(entity: KClass<*>) {
        //TODO
    }

    private fun generateController(entity: KClass<*>) {
        //TODO
    }

    private fun generateCondition(entity: KClass<*>) {
        //TODO
    }

    private fun generateService(entity: KClass<*>) {
        //TODO
    }

    private fun generateRepository(entity: KClass<*>) {
        val entityName = entity.simpleName;
        val fileName = "${entityName}Repository";
        val text = """
import com.github.wenslo.fluent.data.repository.LongIdRepository;
import org.springframework.stereotype.Repository;
/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime ${df.format(LocalDateTime.now())}
 * @description
 */
@Repository
public interface $fileName extends LongIdRepository<$entityName, Long> {
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