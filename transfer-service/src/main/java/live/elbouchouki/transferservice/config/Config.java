package live.elbouchouki.transferservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        live.elbouchouki.core.exception.handler.GlobalExceptionHandler.class,
})
public class Config {
}
