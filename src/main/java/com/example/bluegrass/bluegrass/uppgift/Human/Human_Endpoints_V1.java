package com.example.bluegrass.bluegrass.uppgift.Human;

public class Human_Endpoints_V1 {

    public static final String REST_DIRECT_SAVE_HUMAN_V1_ENDPOINT = "direct:saveHuman";
    public static final String REST_ACTIVEMQ_SEND_V1_ENDPOINT = "activemq:restRoute";
    public static final String REST_DIRECT_GET_HUMAN_V1_ENDPOINT = "direct:getHuman";
    public static final String REST_DIRECT_GET_SPECIFIC_HUMAN_V1_ENDPOINT = "direct:getHumanSpecific";
    public static final String REST_ACTIVEMQ_HUMAN_TOPIC_V1_ENDPOINT = "activemq:HumanTopicV1";
    public static final String REST_LOG_HUMAN_TOPIC_V1_ENDPOINT = "log:HumanTopicLogV1";
    public static final String FILE_PATH_HUMAN_XML = "file:files/Human/XML?fileName=${header.CamelFileName}.xml";
    public static final String FILE_PATH_HUMAN_CSV = "file:files/Human/CSV?fileName=${header.CamelFileName}.csv";
    public static final String FILE_PATH_HUMAN_JSON = "file:files/Human/JSON?fileName=${header.CamelFileName}.json";
    public static final String LOG_BODY = "${body}";
}
