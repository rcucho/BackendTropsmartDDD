package com.techelper.tropsmart_backend;

import com.techelper.tropsmart_backend.configuration.domain.security.JWTAuthorizationFilter;
import com.techelper.tropsmart_backend.configuration.infrastructure.JwtAuthEntryPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@EnableJpaAuditing
public class TropsmartBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TropsmartBackendApplication.class, args);
    }

    /*Setting SQLServer database
    * https://www.youtube.com/watch?v=LVlxBEB1EF4
    *  Commands:
    *   mvn install:install-file -Dfile=C:/sqljdbc_6.0/enu/jre7/sqljdbc41.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0-
            Dpackaging=packaging.jar
    *
    *   mvn install:install-file -Dfile=C:/sqljdbc_6.0/enu/jre7/sqljdbc41.jar -DpomFile=C:/Users/51931/IdeaProjects/ts-opensource-be/pom.xml
    *
    * */

    /*
     * Desplegar Springboot en heroku
     * 1.- Abrir heroku login
     * 2.- heroku create <APP NAME>
     * 3.- inicializar git
     * 4.- git remote add heroku <HEROKU APP REPOSITORY>
     * 5.- git add .
     * 6.- git commit -m "n version"
     * 7.- git push heroku master
     * 8.- heroku config:set DATABASE_URL='mysql://ueu35yi8gtvogkeu:LNOJKsZvSOqNhAcfJA4t@bqhh75ibqyxemcgdz60f-mysql.services.clever-cloud.com:3306/bqhh75ibqyxemcgdz60f'

     *
     *a
     * Se utilizo servicio de clevercloud para Base de datos MySql
     * */

    /**
     * run springboot: ./mvnw spring-boot:run
     */
    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

    @Autowired

    private JwtAuthEntryPoint authEntryPoint;

    @EnableWebSecurity
    @Configuration
    public class SecurityConfiguration {

        @Bean
        void corsConfigurationSource() {

            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("OPTIONS");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("DELETE");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);

        }


        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) ->web.ignoring().antMatchers("/index.html","/ts-api-docs","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui**", "/webjars/**");
        }


        /*
         * Ya esta configurado en httsecurity http
         * solo falta la configuracion web de swagger
         * */

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            /*http
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/authentication/**").permitAll()
                    .anyRequest().authenticated();
             */
            http
                    .csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests().
                    anyRequest().permitAll();

            return http.build();
        }
    }

}
