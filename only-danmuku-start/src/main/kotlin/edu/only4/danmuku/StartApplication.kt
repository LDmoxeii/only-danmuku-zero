package edu.only4.danmuku

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(proxyBeanMethods = false)
@EnableScheduling
@EnableJpaRepositories(basePackages = ["edu.only4.danmuku.adapter.domain.repositories"])
@EntityScan(basePackages = ["edu.only4.danmuku.domain.aggregates"])
class StartApplication

fun main(args: Array<String>) {
    runApplication<StartApplication>(*args)
}
