package ch5_spring_security_in_action.p303_oauth2_github_sso_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ProjectConfig {

    // в этом классе мы не экстендим WebSecurityConfigurerAdapter
    // и не реализуем метод 'protected void configure(HttpSecurity http)'
    // из-за circular dependency с классом
    // 'OAuth2WebSecurityConfiguration -> WebMvcAutoConfiguration -> OAuth2WebSecurityConfiguration -> ...'
    // ...
    // Вместо этого мы используем второй класс конфигурации - SecondProjectConfig

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }


    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("31c663418786e60bfc30")
                .clientSecret("740c940a3cc6c5fb083685d98d2b856a6769e90b")
                .build();
    }

    /* - если не Google/GitHub/Facebook/Окта - сейчас здесь полная настройка для github
    //
    private ClientRegistration clientRegistration() {
        ClientRegistration cr = ClientRegistration.withRegistrationId("github")
                .clientId("31c663418786e60bfc30")
                .clientSecret("740c940a3cc6c5fb083685d98d2b856a6769e90b")
                .scope(new String[]{"read:user"})
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("id")
                .clientName("GitHub")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .build();
        return cr;
    }
    */

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.oauth2Login();
//
//        http.authorizeRequests()
//            .anyRequest().authenticated();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.oauth2Login();
//
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//    }
}
