package p316_implement_auth_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.List;
import java.util.Map;


@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override // Overrides the configure() method to set the AuthenticationManager
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.authenticationManager(authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    InMemoryClientDetailsService service = new InMemoryClientDetailsService();
    BaseClientDetails cd = new BaseClientDetails();

    cd.setClientId("client");
    cd.setClientSecret("secret");
    cd.setScope(List.of("read"));
    cd.setAuthorizedGrantTypes(List.of("password", "refresh_token"));

    service.setClientDetailsStore(Map.of("client", cd));
    clients.withClientDetails(service);
  }

// -----------  Или по-простому через метод inMemory()
//  @Override
//  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.inMemory()
//        .withClient("client")
//        .secret("secret")
//        .authorizedGrantTypes("password", "refresh_token")
//        .scopes("read");
//  }

}