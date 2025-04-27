package org.base.server.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "myconfig")
class MyConfig {
    var name: String? = null
    var age: Int? = null
}