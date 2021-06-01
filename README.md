# Backend for JTrace(IS305, 2021 Spring)
**CZX** 

![logo](./picture/logo.png)

#### Description
Backend part is developed based on Spring boot framework. Using MySQL and MongoDB as database support which are both deployed in the form of docker images. Moreover, I try to use redis to store part of data in memory, which may shorten the query latency to some extent. 

Because this is the first time that I get into IM, so our IM function is simply realised using websocket. The server is acted as mediator, which establish connection with all users and forward chat messages to the target user.

For monitor and test, I use prometheus, grafana for monitoring and Jmeter for pressure test. The test result can be found in test document.

With github actions and docker compose, we can build, test and deploy the backend in remote ECS server automatically. 

Please refer to develop document for more details.

#### Framework and tools Usage
- Spring boot
- swagger2
- Log4j
- docker-compose
- MySQL
- MongoDB
- redis
- Prometheus
- Grafana
- websocket
- Jmeter

#### Team members

Thank the contributions from all team members

* [Snowfall99 (Celeste) (github.com)](https://github.com/Snowfall99)
* [RidiculousDoge (Reymond Gu) (github.com)](https://github.com/RidiculousDoge)
* [JasmineChen123 (Chen Xinran) (github.com)](https://github.com/JasmineChen123)
* [Tongzhixin (github.com)](https://github.com/Tongzhixin)
* [Lajizz (XiangzheWang) (github.com)](https://github.com/Lajizz)

#### Special Thanks To

- Boring Yang
- Obilly Yan

and others who made great contributions during the development and who participated in our tests and offerred valuable suggestions.

I want to add that I thank every User of JTrace, your supports help me willing to make this APP more better.
