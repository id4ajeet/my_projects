# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

```shell script
curl --location --request POST 'http://localhost:8080/phonebook' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "9090343432",
    "name": "Jay",
    "email": "jay@abc.in"
}'
```

```shell script
curl --location --request GET 'http://localhost:8080/phonebook/9090343432' \
--header 'Accept: application/json'
```

```shell script
curl --location --request GET 'http://localhost:8080/sync'
```

```shell script
curl --location --request POST 'http://localhost:8080/webhook' \
--header 'Content-Type: application/json' \
--data-raw '{
            "uuid": "ab94fdd8-8389-496f-a7f7-23ef23576ef3",
            "domain_name": "mockpbx.impactpbx.com",
            "caller_name": "",
            "caller_number": 35725262136,
            "destination_number": 35799123459,
            "direction": "outbound",
            "call_start": "2020-09-13T15:35:11.000Z",
            "ring_start": "2020-09-13T15:35:12.000Z",
            "answer_start": "2020-09-13T15:35:16.000Z",
            "call_end": "2020-09-13T15:36:12.000Z",
            "duration": 61,
            "recording": "8e7a132a-215c-4caa-9cc2-971fdeaab7f2",
            "click_to_call": false,
            "click_to_call_data": "",
            "action": "HANGUP"
        }'
```
