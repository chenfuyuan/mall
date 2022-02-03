package com.learn.project.common.web.util.jsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期序列化器
 *
 * @author chenfuyuan
 * @date 2022/1/21 18:42
 */
public class DateSerializer extends JsonSerializer<Date> {

    private final String pattern;

    public DateSerializer(String pattern) {
        super();
        this.pattern = pattern;
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String output = StringUtils.EMPTY;
        if (date != null) {
            output = new SimpleDateFormat(pattern).format(date);
        }
        jsonGenerator.writeString(output);
    }
}
