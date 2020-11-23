package com.ppyong.sample.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@Import(AuthorizationServerEndpointsConfiguration.class)
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final DataSource datasource;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
			.authenticationManager(this.authenticationManager)
			.accessTokenConverter(accessTokenConverter())
			.tokenStore(tokenStore())
            .pathMapping("/oauth/token", "/api/v1/login");
	}

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .jdbc(datasource);
//            .inMemory()
//                .withClient("client")
//                .secret(passwordEncoder.encode("secret"))
//                .scopes("read")
//                .authorizedGrantTypes("password");
    }

    @Bean
	public KeyPair keyPairBean() {
	    //RSA로 keypair를 생성해야만 동작한다(내부 구현)
        //keytool -genkey -alias mykey -keyalg RSA -keypass asdf1234 -storepass asdf1234 -keystore server.jks
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("server.jks"), "asdf1234".toCharArray())
                .getKeyPair("mykey", "asdf1234".toCharArray());
	  	return keyPair;
	}
}
