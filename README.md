# myKafka
Kafka里有两个topic，
producer 往里面写东西，consumer从里面把消息读出来，再写到test里面。

kafka需要跑起来，打开一个consumer，监控Topic "test"，效果是在Terminal里面看到kafka-try的main函数里面写到kafka里的10个随机字符串。

代码已经clean过了，需要重新gradle idea和gradle build

Two topics in Kafka, producer write into, consumer read out then write into test.
