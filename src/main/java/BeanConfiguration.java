import database.DocliDatabaseManager;
import database.LocalSqliteRepository;
import database.TodoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    DocliDatabaseManager manager() {
        return new DocliDatabaseManager();
    }

    @Bean
    TodoRepository localSqliteReposityorProvider(DocliDatabaseManager manager) {
        return new LocalSqliteRepository(manager);
    }

}
