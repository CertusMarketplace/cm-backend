package pe.edu.certus.usersmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.usersmodule.repository.adapters.mapper.UserPersistenceMapper;

@Configuration
public class UserMapperPersistenceConfig {
    @Bean
    public UserPersistenceMapper userPersistenceMapper() {
        return new UserPersistenceMapper();
    }
}
