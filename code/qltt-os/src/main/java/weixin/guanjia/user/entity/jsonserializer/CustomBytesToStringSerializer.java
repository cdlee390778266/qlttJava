package weixin.guanjia.user.entity.jsonserializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;


public class CustomBytesToStringSerializer extends JsonSerializer<byte[]>
{
    public void serialize(byte[] bytes, JsonGenerator gen, SerializerProvider provider)throws IOException, JsonProcessingException
    {
    	String string= new String(bytes,"utf-8");
        gen.writeString(string);
    }
}
