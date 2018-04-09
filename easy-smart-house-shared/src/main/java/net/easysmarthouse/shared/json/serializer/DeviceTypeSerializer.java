package net.easysmarthouse.shared.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.easysmarthouse.provider.device.DeviceType;

import java.io.IOException;

public class DeviceTypeSerializer extends StdSerializer<DeviceType> {

    public DeviceTypeSerializer() {
        super(DeviceType.class);
    }

    public DeviceTypeSerializer(Class<DeviceType> t) {
        super(t);
    }

    public DeviceTypeSerializer(Class<DeviceType> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(DeviceType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value.toString());
    }
}
