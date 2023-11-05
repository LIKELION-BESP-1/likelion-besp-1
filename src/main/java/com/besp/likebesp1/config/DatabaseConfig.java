package com.besp.likebesp1.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


//컨피그할때 어떤 경우는 특정 클래스를 상속받는 경우도 있고 아닌경우도 있다
@Configuration
public class DatabaseConfig {

    //클래스내에서 application.properties 파일에 접근하기위해서
    @Autowired
    ApplicationContext applicationContext;

    //hikari 설정하기  : application.properties 파일에서 히카리 정보 알아오기
    //히카리 : 자바에서 제공하는 디비커넥션풀이다.
    //이걸 사용해야 다량의 디비접속 처리가 가능하고 속도가 빠르다 

    /*
     * 기본 데이터소스는 HikariCP입니다.
     * 이 설정은 특별한 경우 HikariCP의 세부 설정을 변경하고자 할 때 사용됩니다.
     * 필요한 경우 주석을 해제하여 사용하십시오.
     */
//    @Bean  //@Bean 객체라는 의미임
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public HikariConfig hikariConfig() {
//        return new HikariConfig();
//        //application.properties 의 hikari설정을 읽어서 객체를 만들어 던져준다
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        //설정객체값으로 데이터소스를 만들어서 반환한다.
//        return new HikariDataSource(hikariConfig());
//    }


    //MyBatis factory -> SessionTermplate
    @Bean //객체 생성
    public SqlSessionFactory makeSqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("************************");
        //SqlSessionFactory -  Factory 공장객체를 먼저 만들어서 던진다
        final SqlSessionFactoryBean factory
                = new SqlSessionFactoryBean();

        //저객체와 application.properties 파일에 있는  datasource
        //spring.datasource.url=jdbc:oracle:thin:@127.0.0.1:1521:XE
		/*spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
		  spring.datasource.username=user01
		  spring.datasource.password=1234 
		 */

        factory.setDataSource(dataSource);
        //방법이 여러가지이다.
        //설정파일과 연동하기(mybatis-config.xml)파일과 연동
        PathMatchingResourcePatternResolver resolver
                = new PathMatchingResourcePatternResolver();
        //classpath - src/main/resource
        Resource configLocation =
                resolver.getResource("classpath:mybatis-config.xml");
        //배포버전 만들때 ~.xml로 시작하는 모든 파일은 src/main/resources 에
        //두지 않으면  우리가 프로젝트 위치에 직접 복사붙여넣기를 해야 한더
        factory.setConfigLocation(configLocation);
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate makeSqlSession(
            SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

}
