
# TeleClimb-App
This repository holds code of application backend developed in Java for head judge in climbing competitions.

- Web app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Web
- Mobile app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Mobile


## Description:
The aim of the project is to build a system used for judging climbing competitions. Its main focus is automation of delivering and processing data. The project is designed to be a valuable asset in the market/an answer to the demand of the market – currently, there does not exist a system which operates in the described manner.
As far as physical layer is concerned, it is composed of a computer and a few mobile devices. Connection between all the components in the basic scenario should be provided through a local wireless network.


### Physical diagram of the system compiled with its components
![Physical diagram of the system compiled with its components](https://lh3.googleusercontent.com/fife/AAWUweVL1B61DO8RzhjLKkIZqc5VcdfUtMZbmgD_fnvDt-UxustAwflgBiAM03mswN4UnWWerdMCzyHb_7aDsHu_LlrO-6Lwk8mFu_3HbF1iqgsAzbHZxZinryOAzsalffmod3gzNKqwDOZ3pyZgItM-OWDju7ujqq9XoKxblhdMWSGZNLiQPSwp4ysveKWshdGo7EJC_X0mhE1_7N8dlIGH84j6uWVtSd3oe7fl3D42bVSAIqoetBfYL_QVbShWzDPkej7xjVWavSh6FUBUDEdIZQL84h8usJDsu4LErkE1HiYuMqEXqEEzB1JMaxJwww46JLA4nqblGg6lPln7wIpvHg6OAtLxC5c78UmrIhwN_4MRGywWNEDMDhp3gNU36AbUiIwBMqEMWFsu08wRSryW_NUsWrc5eM3Bb62JGmaTAwDYY_tGmOJZu8RgH2BTjMAysGBeYTLewEmPj_Xgc1mge83jwP0YW8it1omk2T6k3UbHD8Z6qgtYOfMH824GFr4f8IerE69TpCtZY2t1q7v3d5-kzlr7jvsnbCxzmkUViXTeOyTxJsNYP9rWAqSJ8fpRAmAODtRgHrcs5A1Ltu99sbsWdvvKeifn-qzm79ZD45C57UL-kKX5XJo65ydb1IQ9442JjpOS_0j6SYSLt_AS3Y1YYWhABLIGiuM4l2HiOTwx9w4YL4pTWjBQUYdY8oQEbzixsYCZJjcKl8WWBoGd0XV6qXOXvFVDA04wnIzK3kMnsPOe8a7nd-pf0h-one9RgIfTw5aUGoCdVIfaT9n29m71jNdc8YQ5Lq3CF4j1npKOhN_c0lrwbRK1B2g=w1919-h1004)


### The system will consist of:
- a web application (JavaScript, ReactJS), used by the main referee – his job is to manage the entire event. Its capabilities include: competition configuration, importing the list of competitors, conducting/managing rounds, exporting scores, etc.,
- a mobile application (JavaScript, ReactNative) will be utilised by the problem referees. Each referee will receive a prepared list of competitors, starts of which he will be marking. The results are going to be automatically sent to backend,
- backend API (Java, Spring Boot) puts up REST API. It shall possess the entire logic of how the system acts, in accordance with the rules of the Polish Alpinism Association. The data will be saved and downloaded from a built-in file database. Additionaly it will create logs copies, necessary to retrieve lost information on the starts, should any malfunction occur. 

