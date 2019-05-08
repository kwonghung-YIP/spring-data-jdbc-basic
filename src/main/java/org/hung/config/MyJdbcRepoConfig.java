package org.hung.config;

import java.util.Optional;

import org.hung.pojo.Address;
import org.hung.pojo.Customer;
import org.hung.pojo.Order;
import org.hung.repo.CustomerRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.data.relational.core.mapping.event.AfterLoadEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

@Configuration
@EnableJdbcRepositories(basePackages={"org.hung.repo"})
@EnableJdbcAuditing
public class MyJdbcRepoConfig {//extends JdbcConfiguration {

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAware<String>() {

			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.ofNullable(SecurityContextHolder.getContext())
						.map(SecurityContext::getAuthentication)
						.filter(Authentication::isAuthenticated)
						.map(Authentication::getName);
			}
		};
	}
	
	@Bean
	public NamingStrategy namingStrategy() {
		return new NamingStrategy() {

			@Override
			public String getReverseColumnName(RelationalPersistentProperty property) {
				if (Address.class.equals(property.getRawType()) && "deliveryAddress".equals(property.getName())) {
					return "order_ref";
				} else {
					return property.getOwner().getTableName();
				}
			}
			
		};
	}

}
