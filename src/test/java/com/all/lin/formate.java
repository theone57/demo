//package com.all.lin;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.format.DateTimeFormatter;
//
///**
// * @author linpu
// * @dateTime 2022-04-13 10:28
// * @email im.linpu@qq.com
// * @description
// */
//public class formate {
//
//    @Configuration
//    public class DateConverterConfig {
//        @Bean
//        public Converter<String, LocalDate> localDateConverter() {
//            return new Converter<String, LocalDate>() {
//                @Override
//                public LocalDate convert(String source) {
//                    return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                }
//            };
//        }
//
//        @Bean
//        public Converter<String, LocalDateTime> localDateTimeConverter() {
//            return new Converter<String, LocalDateTime>() {
//                @Override
//                public LocalDateTime convert(String source) {
//                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                }
//            };
//        }
//    }
//}
