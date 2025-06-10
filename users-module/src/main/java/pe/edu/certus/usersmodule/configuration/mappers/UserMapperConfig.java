package pe.edu.certus.usersmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.usersmodule.logic.adapters.mapper.UserMapper;

@Configuration
public class UserMapperConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
}
