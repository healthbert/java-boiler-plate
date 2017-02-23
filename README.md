# Spring Boilerplate

Boilerplate project to quickly start developing Microservices using Spring Boot and Spring Cloud.

## Features

* Global error tracking to Raygun
* Spring Cloud Config client support, to get configuration from a remote configuration server (no more env vars!)
* Database Access via JPA (Hibernate) to MySql databases
* Cache via redis
* Code style validation on build against our [Style Guide](https://github.com/healthcarecom/styleguide/tree/master/coding/java) (via Checkstyle).
* TODO: move global error tracking to an outside module.

## Usage

Simply clone the repo into an empty folder, and remove the .git folder inside then push the folder into a new github repo.

### Linux shortcut

```shell
git clone --depth=1 git@github.com:healthcarecom/arch-spring-boilerplate.git new-service-folder
rm -rf new-service-folder/.git
```

## Naming convention

**product** and **service** terms are used in this project to refer to a wildcard to be replaced by a properly defined *service*, the idea is to encapsulate the services in a proper package according to its purpose and the *product* is serving.

Some examples of products inside Healthcare could be: sem, adp, core

So for example if we are creating a service that will serve content for a SEM site, we could name the service: sem-content-service, and do the proper renaming as explained in the in the following sections. The service will be part of a package called **com.healthcare.sem.contentservice**.

Try to stick to a product name, to avoid having services with ambiguous names.

### Code/Folders renaming

In order to enforce a project structure, we included a scafolding based in a wildcard service called **product-service**, in order to start using the boiler plate, we need to rename some folders and make some changes in the files to use this boilerplate as a base of a new project.

#### build.gradle

Modify the **baseName** property inside de jar object to reflect your product/service name.

Example:

```java
  baseName = "core-plans-service"
```

#### src/main/resources/bootstrap.yml

Modify the spring -> application -> name property to your product/service name.

Example:

```yaml
spring:
  application:
    name: "core-plans-service"
```

#### src/[main|test]/java/com/healthcare/{product}/{service}

Rename the **product** and **service** folders accordingly to your service, remember to follow the [package naming convention](https://github.com/healthcarecom/styleguide/blob/master/coding/java/README.md#package-name), as these folders must match the package name for your service.

Example:
```shell
src/main/java/com/healthcare/core/plansservice
src/test/java/com/healthcare/core/plansservice
```

