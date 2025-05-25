import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.weaving.AspectJWeavingEnabler;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan
@EnableAsync
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.DISABLED) // ⛔ отключаем LoadTimeWeaver
@Import(AspectJWeavingEnabler.class) // гарантированное отключение LTW
public class AppConfig {

    @Bean("dateFormatter")
    @Profile("ru")
    public SimpleDateFormat ruFormatter() {
        return new SimpleDateFormat("EEEE, d MMMM, yyyy", new Locale("ru"));
    }

    @Bean("dateFormatter")
    @Profile("en")
    public SimpleDateFormat enFormatter() {
        return new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.ENGLISH);
    }

    @Bean("isoFormatter")
    public SimpleDateFormat isoFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
