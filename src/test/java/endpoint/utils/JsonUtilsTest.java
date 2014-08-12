package endpoint.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import endpoint.SimpleObject;

public class JsonUtilsTest {

	private static final String SIMPLE_OBJECT_JSON = "{aInt : 1, aLong : 1, aDouble : 1.1, aBoolean : true, aDate : '2013/12/26 23:55:01', aString : object1}";

	@Test
	public void testFrom() {
		SimpleObject object = JsonUtils.from(null, SIMPLE_OBJECT_JSON, SimpleObject.class);
		object.assertObjectWithoutKey(1, 1l, 1.1, true, "2013/12/26 23:55:01", "object1");
	}

	@Test
	public void testFromArray() {
		String json = String.format("[%s, %s, %s]", SIMPLE_OBJECT_JSON, SIMPLE_OBJECT_JSON, SIMPLE_OBJECT_JSON);

		List<SimpleObject> objects = JsonUtils.fromList(null, json, SimpleObject.class);

		assertEquals(3, objects.size());

		objects.get(0).assertObjectWithoutKey(1, 1l, 1.1, true, "2013/12/26 23:55:01", "object1");
		objects.get(0).assertObjectWithoutKey(1, 1l, 1.1, true, "2013/12/26 23:55:01", "object1");
		objects.get(0).assertObjectWithoutKey(1, 1l, 1.1, true, "2013/12/26 23:55:01", "object1");
	}

	@Test
	public void testMapWithLongKey() {
		Map<Long, String> map = new HashMap<Long, String>();

		map.put(1l, "xpto1");
		map.put(2l, "xpto2");

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMap(null, json, Long.class, String.class);

		assertEquals("xpto1", map.get(1l));
		assertEquals("xpto2", map.get(2l));
	}

	@Test
	public void testMapWithComplexObjectValue() {
		Map<Long, SimpleObject> map = new HashMap<Long, SimpleObject>();

		map.put(1l, new SimpleObject("xpto1"));
		map.put(2l, new SimpleObject("xpto2"));

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMap(null, json, Long.class, SimpleObject.class);

		assertEquals("xpto1", map.get(1l).getAString());
		assertEquals("xpto2", map.get(2l).getAString());
	}

	@Test
	public void testMapWithListOfComplexObjectValue() throws NoSuchFieldException, SecurityException {
		Map<Long, List<SimpleObject>> map = new HashMap<Long, List<SimpleObject>>();

		map.put(1l, Arrays.asList(new SimpleObject("xpto1"), new SimpleObject("xpto2")));
		map.put(2l, Arrays.asList(new SimpleObject("xpto3"), new SimpleObject("xpto4")));

		String json = JsonUtils.to(map);

		map = JsonUtils.fromMapList(null, json, Long.class, SimpleObject.class);

		assertEquals("xpto1", map.get(1l).get(0).getAString());
		assertEquals("xpto2", map.get(1l).get(1).getAString());
		assertEquals("xpto3", map.get(2l).get(0).getAString());
		assertEquals("xpto4", map.get(2l).get(1).getAString());
	}
}
