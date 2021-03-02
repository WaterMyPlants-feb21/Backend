package com.lambdaschool.watermyplants.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity https) throws Exception{
        https.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createnewuser")
                .permitAll()
                .antMatchers("/users")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/plants")
                .hasAnyRole("ADMIN")
                .antMatchers("/plant/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/logout")
                .authenticated()
                .anyRequest().denyAll()
                .and().exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        https.csrf().disable();//required by h2

        https.logout().disable();//diable default spring logout
    }
}
