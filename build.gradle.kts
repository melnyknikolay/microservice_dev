plugins { 
  java
  eclipse
  id("org.springframework.boot") version "2.1.6.RELEASE" apply false
}

  group = "it.discovery"

  apply(plugin = "java")

   java.sourceCompatibility = JavaVersion.VERSION_11
   java.targetCompatibility = JavaVersion.VERSION_11

   repositories {
     jcenter()
   }

   var springBootVersion = "2.1.6.RELEASE"
   
   dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))    
        compile("org.springframework.boot:spring-boot-starter-web")

        runtime("javax.xml.bind:jaxb-api:2.3.0")
        runtime("javax.annotation:javax.annotation-api:1.3.1")

        compileOnly("org.projectlombok:lombok:1.18.8")
        annotationProcessor("org.projectlombok:lombok:1.18.8")

        compile("org.hibernate:hibernate-entitymanager")
        compile("org.springframework.data:spring-data-jpa")
        runtime("com.h2database:h2:1.4.199")

        testCompile("org.junit.jupiter:junit-jupiter-api")
        testRuntime("org.junit.jupiter:junit-jupiter-engine")
        testCompile("org.springframework.boot:spring-boot-starter-test")
   } 

tasks.test {
    useJUnitPlatform()
}



