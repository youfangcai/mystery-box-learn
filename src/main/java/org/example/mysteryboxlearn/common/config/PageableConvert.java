package org.example.mysteryboxlearn.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.mysteryboxlearn.common.domain.vo.response.PageResult;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

// 自定义分页序列化器
@JsonComponent
@SuppressWarnings("unchecked")
public class PageableConvert {
    public static class Serializer extends JsonSerializer<Page<?>> {
        @Override
        public void serialize(Page<?> page, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            PageResult<?> pageResult = new PageResult<>()
                    .setTotalElements(page.getTotalElements())
                    .setTotalPages(page.getTotalPages())
                    .setSize(page.getSize())
                    .setNumber(page.getNumber())
                    .setContent((List<Object>) page.getContent());
            jsonGenerator.writeObject(pageResult);
        }
    }


    public static class Deserializer extends JsonDeserializer<Page<?>> {

        @Override
        public Page<?> deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException {
            return jsonParser.readValueAs(Page.class);
        }
    }
}
