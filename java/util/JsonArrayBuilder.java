package util;

import org.json.simple.JSONArray;

import java.math.BigDecimal;

public class JsonArrayBuilder extends JSONArray
{
    @SuppressWarnings("unchecked")
    public JsonObjectBuilder addNewJsonObject()
    {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        this.add(jsonObjectBuilder);
        return jsonObjectBuilder;
    }

    @SuppressWarnings("unchecked")
    public JsonArrayBuilder addNewJsonArray()
    {
        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        this.add(jsonArrayBuilder);
        return jsonArrayBuilder;
    }

    @SuppressWarnings("unchecked")
    public void addValue(String value)
    {
        this.add(value);
    }

    @SuppressWarnings("unchecked")
    public void addValue(int value)
    {
        this.add(value);
    }

    @SuppressWarnings("unchecked")
    public void addValue(double value)
    {
        this.add(value);
    }

    @SuppressWarnings("unchecked")
    public void addValue(BigDecimal value)
    {
        this.add(value);
    }

    @SuppressWarnings("unchecked")
    public void addValue(boolean value)
    {
        this.add(value);
    }

    public JsonObjectBuilder getJsonObject(int index)
    {
        Object obj = getObject(index);
        if (obj != null && !(obj instanceof JsonObjectBuilder))
        {
            throw new RuntimeException("Not a JsonObjectBuilder object");
        }
        return (JsonObjectBuilder)obj;
    }

    public JsonArrayBuilder getJsonArray(int index)
    {
        Object obj = getObject(index);
        if (obj != null && !(obj instanceof JsonArrayBuilder))
        {
            throw new RuntimeException("Not a JsonObjectBuilder object");
        }
        return (JsonArrayBuilder)obj;
    }

    @SuppressWarnings("unchecked")
    public Object getObject(int index)
    {
        return get(index);
    }
}
