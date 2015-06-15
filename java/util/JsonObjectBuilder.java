package util;

import org.json.simple.JSONObject;

import java.math.BigDecimal;

public class JsonObjectBuilder extends JSONObject
{
    public JsonObjectBuilder putNewJsonObject(String key)
    {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        putObject(key, jsonObjectBuilder);
        return jsonObjectBuilder;
    }

    public JsonArrayBuilder putNewJsonArray(String key)
    {
        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        putObject(key, jsonArrayBuilder);
        return jsonArrayBuilder;
    }

    public void putValue(String key, String value)
    {
        putObject(key, value);
    }

    public void putValue(String key, int value)
    {
        putObject(key, value);
    }

    public void putValue(String key, double value)
    {
        putObject(key, value);
    }

    public void putValue(String key, BigDecimal value)
    {
        putObject(key, value);
    }

    public void putValue(String key, boolean value)
    {
        putObject(key, value);
    }

    @SuppressWarnings("unchecked")
    public void putObject(String key, Object value)
    {
        this.put(key, value);
    }

    public JsonObjectBuilder getJsonObject(String key)
    {
        Object obj = getObject(key);
        if (obj != null && !(obj instanceof JsonObjectBuilder))
        {
            throw new RuntimeException("Not a JsonObjectBuilder object");
        }
        return (JsonObjectBuilder)obj;
    }

    public JsonObjectBuilder getOrPutNewJsonObject(String key)
    {
        JsonObjectBuilder existingJsonObject = getJsonObject(key);
        if (existingJsonObject != null) return existingJsonObject;

        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        putObject(key, jsonObjectBuilder);
        return jsonObjectBuilder;
    }

    public JsonArrayBuilder getJsonArray(String key)
    {
        Object obj = getObject(key);
        if (obj != null && !(obj instanceof JsonArrayBuilder))
        {
            throw new RuntimeException("Not a JsonObjectBuilder object");
        }
        return (JsonArrayBuilder)obj;
    }

    public JsonArrayBuilder getOrPutNewJsonArray(String key)
    {
        JsonArrayBuilder existingJsonArray = getJsonArray(key);
        if (existingJsonArray != null) return existingJsonArray;

        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        putObject(key, jsonArrayBuilder);
        return jsonArrayBuilder;
    }

    @SuppressWarnings("unchecked")
    public Object getObject(String key)
    {
        return get(key);
    }
}
