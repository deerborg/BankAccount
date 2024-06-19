package art.deerborg.bank.common.config.persist;

import art.deerborg.bank.common.service.AuditorAware;
import art.deerborg.bank.common.service.impl.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
