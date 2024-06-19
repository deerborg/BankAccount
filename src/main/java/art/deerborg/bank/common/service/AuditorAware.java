package art.deerborg.bank.common.service;

import java.util.Optional;

public interface AuditorAware<T> {
    Optional<String> getCurrentAuditor();
}
