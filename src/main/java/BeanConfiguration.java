import database.DocliDatabaseManager;
import database.ExceptionHandler;
import database.repository.LocalSqliteRepository;
import database.repository.TodoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ExceptionHandler exceptionHandlerProvider() {
        return new ExceptionHandler();
    }

    @Bean
    DocliDatabaseManager docliDatabaseManagerprovider(ExceptionHandler handler) {
        return new DocliDatabaseManager(handler);
    }

    @Bean
    TodoRepository localSqliteReposityorProvider(DocliDatabaseManager manager) {
        return new LocalSqliteRepository(manager);
    }

}
