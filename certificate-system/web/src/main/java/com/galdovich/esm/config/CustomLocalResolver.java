package com.galdovich.esm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * The type Custom local resolver.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@Configuration
public class CustomLocalResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Value("UTF-8")
    private String encoding;
    @Value("i18n/exception_message")
    private String fileName;

    private static final Locale EN_LOCALE = new Locale("en");
    private static final Locale RU_LOCALE = new Locale("ru");

    private static final List<Locale> LOCALES = List.of(RU_LOCALE, EN_LOCALE);

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        return headerLang == null || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    /**
     * Message source resource bundle message source.
     *
     * @return the resource bundle message source
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename(fileName);
        rs.setDefaultEncoding(encoding);
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}
